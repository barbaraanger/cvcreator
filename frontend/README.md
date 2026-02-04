# Cvcreator Frontend (React)

Projeto React inicial para conectar com o backend do Cvcreator.

## Como rodar

1. Instale as dependências:
   ```bash
   npm install
   ```
2. Configure a URL do backend (opcional):
   ```bash
   echo "VITE_API_BASE_URL=http://localhost:8080" > .env
   ```
3. Inicie o servidor de desenvolvimento:
   ```bash
   npm run dev
   ```

## Endpoints esperados

Este frontend envia um POST para `VITE_API_BASE_URL/api/curriculos` com os dados do formulário.
Ajuste o endpoint conforme a API real do backend.
