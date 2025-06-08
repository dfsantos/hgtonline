FROM eclipse-temurin:21-alpine AS build-env

WORKDIR /app

# Etapa 1 - Copia configs e baixa dependências
COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY gradlew ./
RUN ./gradlew dependencies

# Etapa 2 - Copia o código-fonte
COPY src src

# Etapa 3 - Compila classes e testClasses
RUN ./gradlew testClasses
