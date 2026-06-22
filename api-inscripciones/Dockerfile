# Usamos una imagen de Java 21 ligera
FROM eclipse-temurin:21-jre-alpine

# Definimos el directorio de trabajo
WORKDIR /app

# Copiamos el JAR que genera Maven (ajusta el nombre según tu pom.xml)
COPY target/*.jar app.jar

# Exponemos el puerto de tu API
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]