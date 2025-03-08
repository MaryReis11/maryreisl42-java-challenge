🚀 Java API Challenge - Loja Virtual
💻 Projeto
API REST Java com Spring Boot para gerenciar uma loja virtual

🌐 O projeto está disponível em: Acesse a documentação Swagger da API

🔨 Como executar a aplicação

Requisitos
🔧 Maven
🐳 Docker
☕ Java 21
🐳 Docker Compose
Passo a passo para rodar a aplicação
Clone o repositório

https://github.com/MaryReis11/maryreisl42-java-challenge.git
🧩 Tecnologias

- ☕ Java 21
- 🔧 Spring Boot
- 🐳 Docker
- 🔍 JUnit
- 📄 Swagger
- 📚 Arquitetura

A API segue a divisão de camadas e responsabilidades da Clean Architecture, com as seguintes camadas:

- 🔍 Repository: Responsável pela interação com os dados apenas em memória e pela persistência temporária dos objetos, simulando a comunicação com um banco de dados real.
- 📦 Model / DTOs: Contém as entidades e objetos de transferência de dados. Essas classes representam os dados que transitam entre as camadas e as APIs.
- ⚙️ Service: Contém a lógica de negócios da aplicação, com regras específicas para manipulação de produtos, categorias e carrinho de compras.
- 🌐 Controller: Responsável por expor a API REST, gerenciando as requisições HTTP e retornando as respostas adequadas com base nos dados manipulados pela camada de Service.
