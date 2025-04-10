# Usa una base image Java
FROM eclipse-temurin:21-jdk-jammy

# Crea una directory per l'app
WORKDIR /app

# Copia il jar generato
COPY target/NoteLock-0.0.1-SNAPSHOT.jar app.jar

# Copia il file di configurazione 
COPY application.properties /app/application.properties

# Espone la porta su cui gira l'app
EXPOSE 8080

# Avvia l'app con il file di configurazione custom
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/application.properties"]
