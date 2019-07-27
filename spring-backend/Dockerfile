
#FROM gradle:jdk10 as builder

#COPY . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN sudo ./gradlew -x test build


FROM openjdk:10-jre-slim
#EXPOSE 8080
#COPY --from=builder /home/gradle/src/build/libs/gs-spring-boot-0.1.0.jar /app/

COPY /build/libs/gs-spring-boot-0.1.0.jar /app/
WORKDIR /app
CMD java -jar gs-spring-boot-0.1.0.jar




