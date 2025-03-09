# 🚀 Java API Challenge - Loja Virtual

💻 **Projeto API REST Java com Spring Boot para gerenciar uma loja virtual.**

🌐 **Acesse a documentação Swagger da API:**  
A documentação interativa da API está disponível no link: http://localhost:8080/swagger-ui/index.html#/ e pode ser acessada diretamente no navegador ao rodar a aplicação.

## 🔧 Requisitos

- **Maven** - 
- **Docker** - 
- **Java 21** - 

## ⚙️ Como executar a aplicação

### **1. Clone o repositório**
Clone o repositório para sua máquina local:

```bash
git clone https://github.com/MaryReis11/maryreisl42-java-challenge.git

### 2. Navegue até o diretório do projeto
cd maryreisl42-java-challenge

### 3. Executando com Maven
Para rodar a aplicação diretamente com Maven, execute:
bash
Copiar
Editar
mvn spring-boot:run

### 1. Executando com Docker
docker build -t loja-virtual-api .

### 2: Execute o container com o comando:
docker run -p 8080:8080 loja-virtual-api

Agora, você pode acessar a aplicação na URL: http://localhost:8080.

🧩 Tecnologias Utilizadas
☕ Java 21 - A versão mais recente do Java.
🔧 Spring Boot - Framework para desenvolvimento de aplicações Java.
🐳 Docker - Para containerização da aplicação.
🔍 JUnit - Framework para testes automatizados.
📄 Swagger - Para documentação interativa da API.
🏗️ Arquitetura
A API foi desenvolvida seguindo a Clean Architecture, garantindo uma boa organização do código e separação de responsabilidades. As camadas principais são:

🔍 Repository
Responsável pela interação com os dados, utilizando uma abordagem em memória para simular um banco de dados real.

📦 Model / DTOs
Contém as entidades e objetos de transferência de dados (DTOs). Representa os dados que transitam entre as camadas e as APIs.

⚙️ Service
Contém a lógica de negócios da aplicação, com regras específicas para manipulação de produtos, categorias e carrinho de compras.

🌐 Controller
Responsável por expor a API REST. Gerencia as requisições HTTP e retorna as respostas adequadas, utilizando as informações manipuladas pela camada Service.

