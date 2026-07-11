# Patman API

API REST desenvolvida em **Java 17** e **Spring Boot** para gerenciamento de pacientes.

O projeto implementa um CRUD completo de pacientes, documentação da API, testes e versionamento do banco de dados, seguindo boas práticas de desenvolvimento e arquitetura.

---

## Tecnologias

* Java 17
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* Flyway
* Springdoc OpenAPI (Swagger)
* Docker / Docker Compose
* JUnit 5 (Testes de integração)
* Test Containers
* Maven

---

## Funcionalidades

* Cadastro de pacientes
* Edição de paciente
* Listagem de pacientes (com filtros)
* Consulta de pacientes
* Exclusão de pacientes
* Validação de dados de entrada
* Tratamento global de exceções
* Documentação automática da API (OpenApi)

---

## Arquitetura

O projeto foi organizado em camadas para promover baixo acoplamento e facilitar manutenção e evolução.

```text
Domain
    ↓
    Controller
        ↓
    Mapper
        ↓
    Service
        ↓
    Repository
        ↓
    Entity
```

As entidades representam o domínio da aplicação, enquanto DTOs são utilizados apenas na camada de apresentação para entrada e saída de dados.

---

## Banco de dados

O banco de dados é versionado utilizando **Flyway**, garantindo controle de versões do esquema e reprodutibilidade entre ambientes.

As migrations são executadas automaticamente na inicialização da aplicação.

---

## Configuração

* Copie o arquivo de exemplo:

```bash
cp .env.example .env
```
* Preencha as variáveis;

* Suba o banco de dados:

```bash
docker compose up -d
```

---

## Documentação

Após iniciar a aplicação, a documentação estará disponível em:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Testes

* Foram criados testes de integração para todos os endpoints do CRUD de pacientes;
* Foram criados arquivos `.http` na pasta `/requests` para versionar a evolução das chamadas.

---

## Considerações sobre escalabilidade

A aplicação foi desenvolvida considerando práticas que facilitam sua evolução para ambientes de produção.

* API stateless;
* Versionamento de banco utilizando Flyway;
* Estrutura preparada para execução em containers Docker;
* Arquitetura em camadas para facilitar evolução e manutenção.

Evolução:
* Pool de conexões HikariCP.
* Paginação preparada para consultas;
* Caso necessário, implementação de cache.

---

## Considerações sobre segurança

A aplicação não possui camada de segurança atualmente.

Como evolução natural da solução, recomenda-se:

* Implementação de pelo menos uma camada mínima de segurança (HTTP Basic por exemplo);
* Evolução para autenticação baseada em **JWT**;
* Configuração de expiração e renovação de tokens;
* Controle de permissões baseado em perfis e permissões;
* Limitação de requisições (Rate Limiting) para proteção contra abuso;
* Auditoria de autenticação e ações sensíveis.

---

## Considerações sobre manutenção

Foram adotadas práticas visando facilitar a manutenção do projeto:

* Configurações por ambiente (default e dev);
* Separação de responsabilidades em camadas;
* Utilização de DTOs para desacoplamento da camada HTTP;
* Tratamento global de exceções;
* Validação utilizando Bean Validation;
* Versionamento do banco de dados (Flyway);
* Documentação automática via OpenAPI;
* Configuração por variáveis de ambiente (.env);
* Código preparado para evolução da autenticação sem alterações nas regras de negócio (separação por versões: /v1).
