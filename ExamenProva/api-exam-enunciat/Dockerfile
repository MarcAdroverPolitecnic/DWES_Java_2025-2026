# Etapa de compilació
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .
# Donem permisos d'execució al wrapper de maven i compilem el jar
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Etapa d'execució
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]