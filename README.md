# CV Creator (CLI)

Gerador de currículo via linha de comando usando Spring Boot + Spring Shell. **Não há frontend por enquanto** — a interação é feita pelo terminal.

## Requisitos

- Java 23 (ou compatível com o `toolchain` configurado no Gradle).
- Gradle Wrapper (já incluído no repositório).

## Como executar

1. Inicie a aplicação:

```bash
./gradlew bootRun
```

2. No prompt do Spring Shell, cadastre os dados e gere o currículo. Exemplo completo:

```bash
dados-pessoais \
  --nome "Maria Souza" \
  --titulo "Engenheira de Software" \
  --cidade "São Paulo" \
  --estado "SP" \
  --pais "Brasil" \
  --email "maria@exemplo.com" \
  --telefone "+55 11 99999-0000" \
  --links "https://linkedin.com/in/maria" "https://github.com/maria"

resumo --resumo "Profissional com 8 anos de experiência em desenvolvimento backend."

experiencia \
  --cargo "Engenheira de Software" \
  --empresa "Empresa X" \
  --periodo "2021 - Atual" \
  --descricao "Desenvolvimento de APIs e serviços internos."

formacao \
  --curso "Ciência da Computação" \
  --instituicao "Universidade Y" \
  --periodo "2014 - 2018" \
  --nivel "Graduação"

curso \
  --titulo "Arquitetura de Software" \
  --instituicao "Instituto Z" \
  --periodo "2020" \
  --carga-horaria 40

habilidade --tipo tecnologia --habilidades "Java" "Spring Boot" "SQL"

habilidade --tipo idioma --habilidades "Inglês" "Espanhol"

gerar
```

## Comandos disponíveis

- `dados-pessoais`: adiciona nome, cargo, endereço e contato.
- `resumo`: adiciona o resumo profissional.
- `experiencia`: adiciona uma experiência profissional.
- `formacao`: adiciona uma formação acadêmica.
- `curso`: adiciona um curso complementar.
- `habilidade`: adiciona habilidades por tipo (`tecnologia`, `metodologia`, `idioma`).
- `gerar`: gera o currículo em HTML e PDF.

## Saída gerada

Após executar `gerar`, o sistema cria dois arquivos no diretório do projeto:

- `Resume - <Nome>.html`
- `Resume - <Nome>.pdf`

## Observações

- Caso a aplicação não reconheça um comando, use `help` no prompt do Spring Shell.
- O frontend ainda não está integrado; tudo é feito via CLI.
