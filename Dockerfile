# Usando a imagem do Maven para construir o projeto
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando os arquivos do repositório para dentro do container
COPY . .

# Compilando o projeto e criando o JAR
RUN mvn clean package -DskipTests

# Usando a imagem do OpenJDK para rodar a aplicação
FROM openjdk:21-jdk-slim

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando o JAR gerado para o container
COPY --from=builder /app/target/LojaVirtual-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta 8080 para acesso externo
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]