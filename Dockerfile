# ================================
# STAGE 1: Build con Maven
# ================================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiamos primero el pom.xml para aprovechar la caché de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests -B

# ================================
# STAGE 2: Imagen final ligera
# ================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Usuario no-root por seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copiamos solo el JAR generado
COPY --from=builder /app/target/*.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

# Puerto por defecto de Spring Boot
EXPOSE 8080

# Arranque de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]