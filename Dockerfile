FROM openjdk:17.0.2-oracle
ARG JAR_FILE=target/booking-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]