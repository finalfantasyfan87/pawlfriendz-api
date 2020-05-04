FROM openjdk:8-jdk-alpine

RUN addgroup -S pawls  && adduser -S pawls -G pawls

USER pawls:pawls

COPY target/*.jar pawls.jar

EXPOSE 8443

ENTRYPOINT ["java","-jar","pawls.jar"]