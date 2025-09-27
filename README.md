# Research Paper Management API

A Spring Boot application to manage research papers with JWT-based authentication.


## ⚙️ Project Setup
 unzip the project:
```bash
cd research-paper-management-api

mvn spring-boot:run

```
---

## 🔑 Authentication

Login to get a JWT token (valid for 2 days):

```bash
curl --request POST   --url http://localhost:8080/auth/login   --header 'Content-Type: application/json'   --data '{"username":"admin","password":"1234"}'
```

Response:
```json
{
  "token": "<jwt-token>",
  "expiresIn": 172800
}
```

Use the token in the `Authorization` header for all protected endpoints:

```
Authorization: Bearer <jwt-token>
```

## 📄 Research Paper APIs

### 1. Create a Research Paper
```bash
curl --request POST   --url http://localhost:8080/api/research-papers   --header 'Authorization: Bearer <jwt-token>'   --header 'Content-Type: application/json'   --data '{
    "name": "KLP Papers",
    "description": "Exploring ML techniques",
    "abstract": "A deep dive into supervised learning"
  }'
```

---

### 2. Get All Research Papers
```bash
curl --request GET   --url http://localhost:8080/api/research-papers   --header 'Authorization: Bearer <jwt-token>'
```

---

### 3. Fuzzy Search Research Papers (with pagination + filters)
```bash
curl --request GET   --url 'http://localhost:8080/api/research-papers?name=KLP%20Papers&status=REVIEW&page=0&size=10'   --header 'Authorization: Bearer <jwt-token>'
```
