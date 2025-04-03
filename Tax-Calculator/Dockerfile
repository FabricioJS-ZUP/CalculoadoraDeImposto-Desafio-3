# image for yhw java
FROM eclipse-temurin:17
#nome da imagem
LABEL maintainer="fabricio.sullivan@zup.com.br"
#pasta onde sera salvo
WORKDIR /app
#acão a ser feita
COPY target/Tax-Calculator-0.0.1-SNAPSHOT.jar /app/Tax-Calculator-Copy-To-Docker.jar

ENTRYPOINT ["java", "-jar", "Tax-Calculator-Copy-To-Docker.jar"]

