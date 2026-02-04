import { useMemo, useState } from "react";

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

const initialForm = {
  nome: "",
  cargo: "",
  resumo: ""
};

export default function App() {
  const [formData, setFormData] = useState(initialForm);
  const [status, setStatus] = useState({ state: "idle", message: "" });
  const preview = useMemo(
    () => JSON.stringify({ ...formData, atualizadoEm: new Date().toISOString() }, null, 2),
    [formData]
  );

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setStatus({ state: "loading", message: "Enviando para o Cvcreator..." });

    try {
      const response = await fetch(`${apiBaseUrl}/api/curriculos`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error(`Resposta inesperada (${response.status})`);
      }

      setStatus({ state: "success", message: "Currículo enviado com sucesso!" });
      setFormData(initialForm);
    } catch (error) {
      setStatus({
        state: "error",
        message: `Não foi possível conectar ao backend: ${error.message}`
      });
    }
  };

  return (
    <div className="page">
      <header className="hero">
        <p className="tag">Cvcreator</p>
        <h1>Frontend React para criar currículos</h1>
        <p className="subtitle">
          Projeto inicial em React para conectar com o backend do Cvcreator via API.
        </p>
      </header>

      <main className="content">
        <section className="card">
          <h2>Enviar dados do currículo</h2>
          <form onSubmit={handleSubmit} className="form">
            <label>
              Nome completo
              <input
                type="text"
                name="nome"
                value={formData.nome}
                onChange={handleChange}
                placeholder="Ex: Maria Silva"
                required
              />
            </label>
            <label>
              Cargo desejado
              <input
                type="text"
                name="cargo"
                value={formData.cargo}
                onChange={handleChange}
                placeholder="Ex: Desenvolvedora Full Stack"
                required
              />
            </label>
            <label>
              Resumo profissional
              <textarea
                name="resumo"
                value={formData.resumo}
                onChange={handleChange}
                placeholder="Conte um pouco sobre sua experiência"
                rows={4}
                required
              />
            </label>
            <button type="submit" disabled={status.state === "loading"}>
              {status.state === "loading" ? "Enviando..." : "Enviar currículo"}
            </button>
            {status.message ? (
              <p className={`status ${status.state}`}>{status.message}</p>
            ) : null}
          </form>
        </section>

        <section className="card preview">
          <h2>Pré-visualização</h2>
          <pre>{preview}</pre>
          <p className="hint">
            Ajuste a URL do backend no arquivo <code>.env</code> com a chave
            <code>VITE_API_BASE_URL</code>.
          </p>
        </section>
      </main>
    </div>
  );
}
