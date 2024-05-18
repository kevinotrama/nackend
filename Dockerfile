FROM ubuntu:latest

LABEL authors="aa"

# Instalación de herramientas necesarias para compilar
RUN apt-get update && \
    apt-get install -y openjdk-11-jdk maven && \
    apt-get clean;

WORKDIR /app

# Copia del código fuente
COPY . .

# Compilación del proyecto
RUN mvn clean package

EXPOSE 5000

# Ejecución de la aplicación
CMD ["java", "-jar", "target/trabajodegrado.jar"]
