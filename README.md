# JSONPlaceholder API Local

![Node.js](https://img.shields.io/badge/Node.js-18%2B-green)
![Status](https://img.shields.io/badge/Status-Active-brightgreen)
![License](https://img.shields.io/badge/License-ISC-blue)

API local com json-server para testes com Postman + Automação com Newman. Este projeto fornece um ambiente completo para teste de APIs RESTful localmente, com integração de testes automatizados via CLI.

---

## 📋 Índice

- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Como Executar](#como-executar)
- [Testes Automatizados](#testes-automatizados)
- [Endpoints](#endpoints)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias](#tecnologias)
- [Contribuindo](#contribuindo)

---

## 📦 Requisitos

- **Node.js** 18+ ([Download](https://nodejs.org/))
- **npm** 8+ (incluído no Node.js)
- Qualquer SO: Windows, macOS ou Linux

---

## 🚀 Instalação

### 1. Clonar/Acessar o projeto
```bash
cd JSONPlaceholder_API
```

### 2. Instalar dependências
```bash
npm install
```

---

## ▶️ Como Executar

### Opção 1: Apenas iniciar a API
```bash
npm run start
```
A API estará disponível em: `http://localhost:3003`

### Opção 2: Rodar testes apenas (API deve estar rodando)
```bash
npm run test
```

### Opção 3: Iniciar API + Rodar testes Newman (Recomendado)
```bash
npm run test:local
```
- Inicia a API automaticamente
- Aguarda a API estar pronta
- Executa os testes Newman
- Encerra a API
- Gera relatório JUnit em `tests/newman/results/junit-report.xml`

### Opção 4: Rodar TODOS os testes (Newman + Java)
```bash
npm run test:all
```
- Inicia a API automaticamente
- Executa testes Newman (17 assertions)
- Executa testes Java com Maven (3 testes JUnit)
- Gera relatórios em ambas as pastas
- Encerra a API

---

## 🧪 Testes Automatizados

### Estrutura dos Testes

Este projeto inclui dois tipos de testes:

#### 🔹 Newman Tests (Postman CLI)
- Localização: `tests/newman/`
- Coleção: `postman-collections/JSONPlaceholder_API.postman_collection.postman_collection.json`
- Framework: Postman + Newman CLI
- 17 assertions em 3 endpoints

#### 🔹 Java Tests (RestAssured)
- Localização: `tests/java/`
- Framework: JUnit 4.13.2 + RestAssured 5.3.2
- 3 testes simples em 2 classes
- Maven build system

### Como Executar Testes

#### Opção 1: Testes Newman com API automática
```bash
cd JSONPlaceholder_API
npm run test:local
```

#### Opção 2: Testes Java apenas
```bash
cd tests/java
mvn clean test
```

#### Opção 3: Todos os testes (Newman + Java)
```bash
cd JSONPlaceholder_API
npm run test:all
```

### Testes Incluídos

#### Newman Tests

1. **GET /posts** - Lista todos os posts
   - ✅ Status code 200
   - ✅ Array de posts
   - ✅ Campos obrigatórios (userId, id, title, body)
   - ✅ Tipos de dados corretos

2. **GET /posts/{id}** - Post específico
   - ✅ Status code 200
   - ✅ ID correto
   - ✅ Campos obrigatórios presentes
   - ✅ Tipos de dados corretos

3. **GET /users** - Lista de usuários
   - ✅ Status code 200
   - ✅ Array de usuários
   - ✅ Campos obrigatórios (id, name, email)
   - ✅ Tipos de dados corretos

#### Java Tests

1. **PostsTest.testGetAllPosts()** - JUnit
   - Valida GET /posts com status 200
   - Verifica lista não vazia
   - Valida campos obrigatórios do primeiro post

2. **PostsTest.testGetPostById()** - JUnit
   - Valida GET /posts/{id} com status 200
   - Verifica ID correto
   - Valida campos obrigatórios

3. **UsersTest.testGetAllUsers()** - JUnit
   - Valida GET /users com status 200
   - Verifica lista não vazia
   - Valida campos id, name, email do primeiro usuário

### Relatório de Testes
- **Newman**: `tests/newman/results/junit-report.xml`
- **Java**: `tests/java/target/surefire-reports/`
- **Formato**: JUnit XML (compatível com CI/CD)

---

## 📝 Endpoints Disponíveis

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `http://localhost:3003/posts` | Lista todos os posts |
| GET | `http://localhost:3003/posts/{id}` | Obtém um post específico |
| GET | `http://localhost:3003/users` | Lista todos os usuários |

---

## 📁 Estrutura do Projeto

```
teste automacao/
├── README.md                          # Este arquivo - Documentação completa
├── .gitignore                         # Git ignore rules
├── docs/                              # Documentação adicional
│
├── JSONPlaceholder_API/               # API Local com json-server
│   ├── start.js                       # Script de inicialização
│   ├── db.json                        # Dados mock da API
│   ├── package.json                   # Dependências e scripts Node
│   └── test-results/                  # (Deprecated) Relatórios antigos
│
├── postman-collections/               # Coleção Postman
│   ├── JSONPlaceholder_API.postman_collection.postman_collection.json
│   └── dev.postman_environment.json
│
└── tests/                              # Testes Automatizados
    ├── newman/                         # Testes Newman (Postman CLI)
    │   └── results/
    │       └── junit-report.xml
    │
    └── java/                           # Testes Java (RestAssured + JUnit)
        ├── pom.xml                     # Configuração Maven
        ├── src/
        │   └── test/java/com/jsonplaceholder/
        │       ├── config/
        │       │   └── BaseTest.java               # Classe base
        │       ├── models/
        │       │   ├── Post.java                   # POJO para posts
        │       │   └── User.java                   # POJO para usuários
        │       └── tests/
        │           ├── PostsTest.java              # Testes de posts
        │           └── UsersTest.java              # Testes de usuários
        └── target/                     # Build output (Maven)
            └── surefire-reports/       # Relatórios JUnit
```

---

## 🔧 Tecnologias Utilizadas

| Tecnologia | Versão | Função |
|------------|--------|--------|
| **Node.js** | 18+ | Runtime JavaScript |
| **json-server** | 0.17.4 | API REST Mock |
| **Newman** | 6.1.0 | Executor de testes Postman via CLI |
| **Postman** | - | Definição de testes Newman |
| **Java** | 11+ | Linguagem para testes avançados |
| **Maven** | 3.6+ | Build tool Java |
| **RestAssured** | 5.3.2 | Client HTTP fluente para testes |
| **JUnit** | 4.13.2 | Framework de testes Java |
| **Jackson** | 2.15.2 | Serialização JSON |
| **Concurrently** | 8.2.2 | Executar múltiplos processos |
| **Wait-on** | 7.2.0 | Aguardar disponibilidade de porta |

---

## 📚 Scripts NPM

```bash
# Iniciar a API
npm run start

# Rodar testes Newman (API deve estar rodando)
npm run test

# Rodar testes Newman com API automática
npm run test:local

# Rodar TODOS os testes (Newman + Java)
npm run test:all
```

---

## 🔍 Relatórios

### Newman Tests
```
tests/newman/results/junit-report.xml
```
- 3 requisições
- 17 assertions
- Formato: JUnit XML

### Java Tests
```
tests/java/target/surefire-reports/
```
- 3 testes JUnit
- Múltiplos formatos: TXT, XML, HTML
- Fácil integração com CI/CD

---

## 📝 Notas Importantes

- Os testes Newman são executados no ambiente `dev`
- A variável `{{baseUrl}}` aponta para `http://localhost:3003`
- Testes Java requerem Java 11+ e Maven 3.6+
- Relatórios são sobrescritos a cada execução dos testes
- API rodando em `http://localhost:3003` é pré-requisito para testes Java