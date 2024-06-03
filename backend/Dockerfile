FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package


FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/*jar /app/target/app.jar


# Comando padrão para iniciar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "/app/target/app.jar"]