# 🐾 Track API - v0.1

API para cadastro e rastreamento de pets através do QR Code da coleira.  
Esta é a **versão 0.1**, contendo funcionalidades iniciais de autenticação e gerenciamento de animais.

---

## 🚀 Funcionalidades

### 👤 Usuários
- Registrar novo usuário  
- Fazer login e receber **token JWT**

### 🐶 Pets
- Cadastrar pet  
- Listar pets cadastrados  
- Buscar pet por ID  
- Excluir/Desativar pet por ID  
- *(Em desenvolvimento)*: rastreamento via scanner da coleira (**QR Code**)

---

## 🛠️ Tecnologias

- **Java 17**  
- **Spring Boot**  
- **MySQL**  
- **Docker & Docker Compose**  
- **Swagger UI** *(documentação e testes de endpoints)*

---

## 📦 Como Rodar o Projeto

1. Clone este repositório:
   ```bash
   git clone git@github.com:GabrielRogerioMessias/track-pet-api-v1.git

---

2. Acesse a pasta do projeto:
   ```bash
   cd track-api
3. Certifique-se de ter o Docker instalado e em execução.

---

5. Execute o build e inicialização dos containers:
   ```bash
   docker-compose up --build


---


5. Acesse a documentação no navegador:
   👉 http://localhost:8080/swagger-ui/index.html#/
---

## ⚙️ Configuração do .env
Crie um arquivo .env na raiz do projeto com as seguintes variáveis (substitua pelos seus dados):
   ```bash
   # Banco de Dados Local
   DB_URL=jdbc:mysql://localhost:3306/[NOME DO SEU BANCO]
   DB_USERNAME=[Seu Usuário]
   DB_PASSWORD=[Sua Senha]

   # Banco de Dados (Docker)
   MYSQL_ROOT_PASSWORD=root
   MYSQL_DATABASE=track_api_db
   MYSQL_USER=root
   MYSQL_PASSWORD=1234

   # Segurança / JWT
   API_SECRET=sua-secret-key

   # Spring Boot (Docker)
   SPRING_PROFILES_ACTIVE=docker
   SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/track_api_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   SPRING_DATASOURCE_USERNAME=track_user
   SPRING_DATASOURCE_PASSWORD=senha

---

## 📖 Documentação
A API conta com uma interface Swagger para explorar e testar os endpoints disponíveis:
👉 http://localhost:8080/swagger-ui/index.html#/

---

📌 Status do Projeto
🚧 Versão 0.1 - Em desenvolvimento



