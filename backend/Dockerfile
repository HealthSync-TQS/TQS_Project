FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

# Copie o código-fonte para o contêiner
COPY . /app

# Copie o arquivo JAR gerado
COPY target/*.jar app.jar

# Comando padrão para iniciar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]