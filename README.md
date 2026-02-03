# 🚀 EventFlow API

> Sistema RESTful para gestão de eventos, controle de capacidade e emissão de ingressos.

Este projeto foi desenvolvido como requisito avaliativo da disciplina de **Desenvolvimento Backend**, com foco na implementação de uma arquitetura em camadas bem definida, regras de negócio consistentes e documentação automatizada.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura e Padrões](#-arquitetura-e-padrões)
- [Modelo de Dados (DER)](#-modelo-de-dados-der)
- [Regras de Negócio](#-regras-de-negócio)
- [Como Executar](#-como-executar)
- [Documentação da API](#-documentação-da-api)
- [Autores](#-autores)

---

## 📖 Sobre o Projeto

O **EventFlow API** resolve o problema de descentralização na gestão de eventos técnicos e workshops. O sistema permite que organizadores cadastrem eventos, definam locais com capacidade limitada e gerenciem a venda de ingressos, garantindo integridade dos dados e evitando *overbooking*.

### Funcionalidades Principais
* **CRUD Completo:** Organizadores, Locais, Eventos, Participantes e Ingressos.
* **Gestão de Status:** Controle do ciclo de vida do evento (Aberto, Esgotado, Cancelado, Encerrado).
* **Venda de Ingressos:** Validação de unicidade (um participante não pode comprar duas vezes para o mesmo evento).
* **Mock Data:** O sistema inicia com dados populados para facilitar testes.

---

## 🛠 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3** (Web, Data JPA, Validation)
* **PostgreSQL** (Banco de dados produção) / **H2 Database** (Dev/Testes)
* **Lombok** (Redução de boilerplate)
* **MapStruct** (Conversão de Entidades ↔ DTOs)
* **OpenAPI / Swagger** (Documentação viva da API)
* **JUnit 5 & Mockito** (Testes Unitários)

---

## 🏗 Arquitetura e Padrões

O projeto segue estritamente a arquitetura em camadas (**Layered Architecture**) para garantir desacoplamento e manutenção:

```text
src/main/java/EventFlow/eventflow
│
├── config          # Configurações (Swagger, Beans, MockData)
├── resource        # Controladores REST (Camada de Apresentação)
├── service         # Regras de Negócio
├── repository      # Acesso a Dados (Spring Data JPA)
├── entity          # Modelo de Domínio (ORM)
├── dto             # Objetos de Transferência de Dados (Request/Response)
│   ├── request
│   └── response
└── mapper          # Conversores (MapStruct)