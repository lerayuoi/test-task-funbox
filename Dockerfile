FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
RUN addgroup -S spring && adduser -S spring -G spring
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo-0.0.1-SNAPSHOT.jar"]