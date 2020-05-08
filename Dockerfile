  
FROM openjdk:8-jdk-alpine

RUN addgroup -S pawfriendz  && adduser -S pawfriendz -G pawfriendz

USER pawfriendz:pawfriendz

COPY target/*.jar pawfriendz.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","pawfriendz.jar"]