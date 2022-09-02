FROM openjdk:11
EXPOSE 8084
ADD target/emailmsg.jar emailmsg.jar
ENTRYPOINT ["java","-jar","/emailmsg.jar"]