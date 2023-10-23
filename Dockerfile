FROM maven:3.8.7-eclipse-temurin-19-alpine

LABEL maintainer="Thiago Ribeiro <thiagobcoelho25@gmail.com>"

WORKDIR /app

COPY . .

RUN mvn package

#EXPOSE 8080

CMD ["java", "-jar", "target/estagio_pokemon_triagil-0.0.1-SNAPSHOT.jar"]