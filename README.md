🚀 Java API Challenge - Loja Virtual
💻 Projeto
API REST Java com Spring Boot para gerenciar uma loja virtual.

🌐 Acesse a documentação Swagger da API
A documentação interativa da API está disponível e pode ser acessada diretamente no navegador ao rodar a aplicação.

🔧 Requisitos
Maven - Para gerenciar as dependências e build do projeto.
Docker - Para containerizar a aplicação.
Java 21 - Versão do Java para execução da aplicação.
Docker Compose - Para orquestrar os containers Docker.
⚙️ Como executar a aplicação
1. Clone o repositório
Clone o repositório para sua máquina local:

bash
Copiar
Editar
git clone https://github.com/MaryReis11/maryreisl42-java-challenge.git
2. Navegue até o diretório do projeto
bash
Copiar
Editar
cd maryreisl42-java-challenge
3. Executando com Maven
Para rodar a aplicação diretamente com Maven, execute:

bash
Copiar
Editar
mvn spring-boot:run
4. Executando com Docker (opcional)
Você também pode executar a aplicação em um container Docker, se preferir. Use o seguinte comando:

bash
Copiar
Editar
docker-compose up
🧩 Tecnologias Utilizadas
☕ Java 21 - A versão mais recente do Java.
🔧 Spring Boot - Framework para desenvolvimento de aplicações Java.
🐳 Docker - Para containerização da aplicação.
🔍 JUnit - Framework para testes automatizados.
📄 Swagger - Para documentação interativa da API.
🏗️ Arquitetura
A API foi desenvolvida seguindo a Clean Architecture, garantindo uma boa organização do código e separação de responsabilidades. As camadas principais são:

1. 🔍 Repository
Responsável pela interação com os dados, utilizando uma abordagem em memória para simular um banco de dados real.
2. 📦 Model / DTOs
Contém as entidades e objetos de transferência de dados (DTOs). Representa os dados que transitam entre as camadas e as APIs.
3. ⚙️ Service
Contém a lógica de negócios da aplicação, com regras específicas para manipulação de produtos, categorias e carrinho de compras.
4. 🌐 Controller
Responsável por expor a API REST. Gerencia as requisições HTTP e retorna as respostas adequadas, utilizando as informações manipuladas pela camada Service.
