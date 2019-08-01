# Avaliacao SAJADV

Application submited for Softplan Admission Challenge.

# Build this application

1. Go to project folder (spring-backend) and build the project:

```sh
./gradlew -x test build
```

2. Go to root folder and compose application to run.

```sh
docker-compose build
docker-compose up
```

3. Access application at localhost:4200

# For development

1. Go to project folder postgre-docker/ and compose docker:

```sh
   docker-compose build
   docker-compose up
```

2. Go to project folder spring-backend/src/main/resources/application.properties and change db addres line to:

```sh
   spring.datasource.url=jdbc:postgresql://localhost:5432/springbootdb
```

3. Go to project folder spring-backend/ , build and run:

```sh
   ./gradlew buil
   java -jar build/libs/gs-spring-boot-0.1.0.jar
```

4. Go to project folder angular-frontend/ , install and serve:

```sh
npm install
ng serve
```

5. Access application at localhost:4200
