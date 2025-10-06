# Student API 

Uma API RESTful para gerenciamento de estudantes desenvolvida em Spring Boot com PostgreSQL.

##  Descrição

API simples para cadastro e gerenciamento de estudantes, permitindo operações CRUD completas. Desenvolvida como parte de um desafio técnico.

##  Tecnologias

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**


##  Configuração e Execução

### Pré-requisitos
- Java 17
- Maven
- PostgreSQL

### 1. Clonar o repositório
```bash
git clone https://github.com/talitaester/AvantSoft-desafio-estagio.git
```

### 2. Configurar Banco de Dados
```bash
docker-compose up -d
```
Isso vai subir um banco de dados Postgres que usaremos na nossa apliação. Esse banco por padrão está rodando na porta 5432.


### 3. Executar a Aplicação
```bash
mvn spring-boot:run
```
A API está rodando na porta 8089.

## 4. Endpoints da API

### POST /students - Criar estudante
**Request:**
```json
{
    "name": "João Silva",
    "grade": 8.5
}
```

**Response:**
```json
{
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "name": "João Silva",
    "grade": 8.5,
    "uniqueLetter": "j"
}
```

### GET /students - Listar todos os estudantes
**Response:**
```json
[
    {
        "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
        "name": "João Silva",
        "grade": 8.5,
        "uniqueLetter": "j"
    },
    {
        "id": "b2c3d4e5-f6g7-8901-bcde-f23456789012",
        "name": "Maria Santos",
        "grade": 9.2,
        "uniqueLetter": "m"
    }
]
```

### GET /students/{id} - Buscar estudante por ID
**Response:**
```json
{
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "name": "João Silva",
    "grade": 8.5,
    "uniqueLetter": "j"
}
```











