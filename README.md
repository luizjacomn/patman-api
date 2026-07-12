# Patman API

API REST desenvolvida em **Java 17** e **Spring Boot** para gerenciamento de pacientes.

O projeto implementa um **CRUD** completo de pacientes, documentação da **_API_**, testes e versionamento do banco de dados, seguindo boas práticas de desenvolvimento e arquitetura.

---

## Tecnologias

* Java 17
* Spring Boot 3
* Spring Data JPA
* Spring Security (Basic Auth)
* Spring Actuator (Métricas)
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
* Documentação automática da **_API_** (**_OpenApi_**)

---

## Arquitetura

O projeto foi organizado em camadas para promover baixo acoplamento e facilitar manutenção e evolução.

```text
Domain ->
    Controller -> Mapper -> Service -> Repository -> Entity
```

As entidades representam o domínio da aplicação, enquanto os **DTOs** são utilizados apenas na camada de apresentação para entrada e saída de dados.

---

## Banco de dados

O banco de dados (**_PostgreSQL_**) é versionado utilizando **_Flyway_**, garantindo controle de versões do esquema e reprodutibilidade entre ambientes.

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

http://localhost:8080/swagger-ui/index.html

A especificação OpenAPI também pode ser acessada em:

http://localhost:8080/v3/api-docs/v1

---

## Testes

* Foram criados testes de integração para todos os endpoints do **CRUD** de pacientes;
* Foram criados arquivos `.http` na pasta `/requests` para versionar a evolução das chamadas.

---

## Considerações sobre escalabilidade

A aplicação foi desenvolvida considerando práticas que facilitam sua evolução para ambientes de produção.

* API stateless;
* Versionamento de banco utilizando **_Flyway_**;
* Estrutura preparada para execução em containers **_Docker_**;
* Arquitetura em camadas para facilitar evolução e manutenção.

Evolução:
* Paginação para a listagem de pacientes;
* Caso necessário, implementação de cache.

---

## Considerações sobre segurança

A aplicação possui uma camada básica de segurança (**HTTP Basic**) atualmente.

Foi criado um arquivo (`src/main/resources/db/load-users.sql`) que contém o _insert_ para um usuário a ser usado para testar
a aplicação, visto que foi criado apenas o **CRUD** para a entidade `Patient`.

Credenciais padrão:
```text
Usuário: admin
Senha: patman
```

Como evolução natural da solução, recomenda-se:

* Evolução para autenticação baseada em **_JWT_**;
* Configuração de expiração e renovação de tokens;
* Controle de permissões baseado em perfis;
* Limitação de requisições (**_Rate Limiting_**) para proteção contra abuso;
* Auditoria de autenticação e ações sensíveis.

---

## Considerações sobre manutenção

Foram adotadas práticas visando facilitar a manutenção do projeto:

* Configurações por ambiente (`default` e `dev`);
* Separação de responsabilidades em camadas;
* Utilização de **DTOs** para desacoplamento da camada **HTTP**;
* Tratamento global de exceções;
* Validação utilizando **_Bean Validation_**;
* Versionamento do banco de dados (**_Flyway_**);
* Documentação automática via **_OpenAPI_**;
* Configuração por variáveis de ambiente (`.env`);
* Código preparado para evolução da autenticação sem alterações nas regras de negócio;
* Separação por versões: `/v1`.

Evolução:
* Implementação de CI/CD (**_pipeline_** com verificação de testes, qualidade (**_SonarQube_**, por exemplo) e _deploy_);
* Abstração de componentes (_repositories_, _services_, etc.), para facilitar uma eventual troca, caso necessário;
* Aplicação de padrões de projeto a medida que a aplicação cresça.
