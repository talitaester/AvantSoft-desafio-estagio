# Student API 🎓

Uma API RESTful para gerenciamento de estudantes desenvolvida em Spring Boot com PostgreSQL.

## 📋 Descrição

API simples para cadastro e gerenciamento de estudantes, permitindo operações CRUD completas. Desenvolvida como parte de um desafio técnico.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## 📁 Estrutura do Projeto

```
student-api/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── studentapi/
│                       ├── StudentApiApplication.java
│                       ├── config/
│                       │   └── CorsConfig.java
│                       ├── controller/
│                       │   └── StudentController.java
│                       ├── service/
│                       │   └── StudentService.java
│                       ├── repository/
│                       │   └── StudentRepository.java
│                       ├── model/
│                       │   └── Student.java
│                       └── dto/
│                           └── StudentResponse.java
├── pom.xml
└── README.md
```

## 🛠️ Configuração e Execução

### Pré-requisitos
- Java 17
- Maven
- PostgreSQL

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/student-api.git
cd student-api
```

### 2. Configurar Banco de Dados
```sql
CREATE DATABASE studentdb;
```

### 3. Configurar application.properties
Edite `src/main/resources/application.properties`:
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=password

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Server
server.port=8080

# Logging
logging.level.com.example.studentapi=DEBUG
```

### 4. Executar a Aplicação
```bash
mvn spring-boot:run
```

## 📡 Endpoints da API

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

### PUT /students/{id} - Atualizar estudante
**Request:**
```json
{
    "name": "João Silva Santos",
    "grade": 9.0
}
```

**Response:**
```json
{
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "name": "João Silva Santos",
    "grade": 9.0,
    "uniqueLetter": "j"
}
```

### DELETE /students/{id} - Deletar estudante
**Response:**
```
"Student deleted successfully"
```

## 🎯 Funcionalidades Especiais

### Letra Única (uniqueLetter)
Para cada estudante, a API retorna a **primeira letra não repetida** no nome:
- "Gabriel" → "g" (g não se repete)
- "Anna" → "_" (todas as letras se repetem)
- "Carlos" → "c" (c não se repete)

### Validações
- **Nome**: Campo obrigatório
- **Nota**: Deve estar entre 0 e 10

## 📝 Exemplos de JSON para Teste

### Estudante 1 - Nome com letra única
```json
{
    "name": "Gabriel",
    "grade": 8.5
}
```
**Resultado esperado:** `"uniqueLetter": "g"`

### Estudante 2 - Nome com letras repetidas
```json
{
    "name": "Anna",
    "grade": 9.0
}
```
**Resultado esperado:** `"uniqueLetter": "_"`

### Estudante 3 - Nome com múltiplas palavras
```json
{
    "name": "Carlos Eduardo",
    "grade": 7.2
}
```
**Resultado esperado:** `"uniqueLetter": "c"`

### Estudante 4 - Nome curto
```json
{
    "name": "Bob",
    "grade": 6.8
}
```
**Resultado esperado:** `"uniqueLetter": "b"`

### Estudante 5 - Nota máxima
```json
{
    "name": "Fernanda",
    "grade": 10.0
}
```
**Resultado esperado:** `"uniqueLetter": "f"`

### Estudante 6 - Nome com acentos
```json
{
    "name": "João Pédro",
    "grade": 8.9
}
```
**Resultado esperado:** `"uniqueLetter": "j"`

## 🧪 Testando com cURL

### Criar estudante:
```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Gabriel","grade":8.5}'
```

### Listar estudantes:
```bash
curl http://localhost:8080/students
```

### Buscar por ID:
```bash
curl http://localhost:8080/students/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

### Atualizar estudante:
```bash
curl -X PUT http://localhost:8080/students/a1b2c3d4-e5f6-7890-abcd-ef1234567890 \
  -H "Content-Type: application/json" \
  -d '{"name":"Gabriel Silva","grade":9.0}'
```

### Deletar estudante:
```bash
curl -X DELETE http://localhost:8080/students/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

## 🐳 Docker Support

### docker-compose.yml
```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    container_name: student-postgres
    environment:
      POSTGRES_DB: studentdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  app:
    build: .
    container_name: student-api
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/studentdb
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
    ports:
      - "8080:8080"
    depends_on:
      - postgres

volumes:
  postgres_data:
```

### Dockerfile
```dockerfile
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Para executar com Docker:
```bash
docker-compose up --build
```

## 🔧 Desenvolvimento

### Executar em modo desenvolvimento:
```bash
mvn spring-boot:run
```

### Compilar o projeto:
```bash
mvn clean compile
```

### Executar testes:
```bash
mvn test
```

### Empacotar para produção:
```bash
mvn clean package
```

## 📊 Status Codes

- `200 OK` - Requisição bem-sucedida
- `201 Created` - Recurso criado com sucesso
- `400 Bad Request` - Dados inválidos
- `404 Not Found` - Estudante não encontrado
- `500 Internal Server Error` - Erro no servidor

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

Seu Nome - [seu.email@example.com](mailto:seu.email@example.com)

## 🙏 Agradecimentos

- Spring Boot Team
- PostgreSQL Community
- Todos os contribuidores

---

**⭐️ Se este projeto te ajudou, deixe uma estrela no repositório!**

A API estará disponível em: **http://localhost:8080** 🚀

---

### 📞 Suporte

Se você tiver alguma dúvida ou problema, abra uma [issue](https://github.com/seu-usuario/student-api/issues) no GitHub.

**Happy Coding!** 💻
