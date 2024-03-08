FROM openjdk:8
EXPOSE 8080
ADD target/attendance-query-service.jar attendance-query-service.jar 
ENTRYPOINT ["java","-jar","/order-service.jar"]