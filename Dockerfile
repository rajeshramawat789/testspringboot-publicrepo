FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /demo-spring-boot-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx2g","-jar","/demo-spring-boot-service.jar"]
