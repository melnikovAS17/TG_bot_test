

FROM openjdk:11

WORKDIR /app

COPY ./dispatcher.jar /app/dispatcher.jar

#EXPOSE 8080

CMD ["java", "-jar", "/app/dispatcher.jar"]
