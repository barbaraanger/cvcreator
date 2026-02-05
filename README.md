# CV Creator (API)

Gerador de currículo via endpoints HTTP usando Spring Boot. **Não há frontend por enquanto** — a interação é feita via requisições à API.

## Requisitos

- Java 23 (ou compatível com o `toolchain` configurado no Gradle).
- Gradle Wrapper (já incluído no repositório).

## Como executar

1. Inicie a aplicação:

```bash
./gradlew bootRun
```

2. Envie requisições HTTP para montar o currículo e gerar os arquivos. Exemplo completo com `curl`:

```bash
curl -X POST http://localhost:8080/api/curriculo/dados-pessoais \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Souza",
    "tituloCargo": "Engenheira de Software",
    "cidade": "São Paulo",
    "estado": "SP",
    "pais": "Brasil",
    "email": "maria@exemplo.com",
    "telefone": "+55 11 99999-0000",
    "links": ["https://linkedin.com/in/maria", "https://github.com/maria"]
  }'

curl -X POST http://localhost:8080/api/curriculo/resumo \
  -H "Content-Type: application/json" \
  -d '{"resumo": "Profissional com 8 anos de experiência em desenvolvimento backend."}'

curl -X POST http://localhost:8080/api/curriculo/experiencias \
  -H "Content-Type: application/json" \
  -d '{
    "cargo": "Engenheira de Software",
    "empresa": "Empresa X",
    "periodo": "2021 - Atual",
    "descricao": "Desenvolvimento de APIs e serviços internos."
  }'

curl -X POST http://localhost:8080/api/curriculo/formacoes \
  -H "Content-Type: application/json" \
  -d '{
    "curso": "Ciência da Computação",
    "instituicao": "Universidade Y",
    "periodo": "2014 - 2018",
    "nivel": "Graduação"
  }'

curl -X POST http://localhost:8080/api/curriculo/cursos \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Arquitetura de Software",
    "instituicao": "Instituto Z",
    "periodo": "2020",
    "cargaHoraria": 40
  }'

curl -X POST http://localhost:8080/api/curriculo/habilidades \
  -H "Content-Type: application/json" \
  -d '{"tipo": "tecnologia", "habilidades": ["Java", "Spring Boot", "SQL"]}'

curl -X POST http://localhost:8080/api/curriculo/habilidades \
  -H "Content-Type: application/json" \
  -d '{"tipo": "idioma", "habilidades": ["Inglês", "Espanhol"]}'

curl -X POST http://localhost:8080/api/curriculo/gerar
```

## Endpoints disponíveis

- `POST /api/curriculo/dados-pessoais`: adiciona nome, cargo, endereço e contato.
- `POST /api/curriculo/resumo`: adiciona o resumo profissional.
- `POST /api/curriculo/experiencias`: adiciona uma experiência profissional.
- `POST /api/curriculo/formacoes`: adiciona uma formação acadêmica.
- `POST /api/curriculo/cursos`: adiciona um curso complementar.
- `POST /api/curriculo/habilidades`: adiciona habilidades por tipo (`tecnologia`, `metodologia`, `idioma`).
- `POST /api/curriculo/gerar`: gera o currículo em HTML e PDF.

## Saída gerada

Após executar `gerar`, o sistema cria dois arquivos no diretório do projeto:

- `Resume - <Nome>.html`
- `Resume - <Nome>.pdf`

## Observações

- O frontend ainda não está integrado; tudo é feito via API.
