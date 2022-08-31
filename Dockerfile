FROM openjdk:8
EXPOSE 8084
ADD target/emailmsg-0.0.1-SNAPSHOT.jar emailmsg-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/emailmsg-0.0.1-SNAPSHOT.jar"]

