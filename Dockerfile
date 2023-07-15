FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar djag-test-app.jar
ENTRYPOINT ["java","-jar","/djag-test-app.jar"]