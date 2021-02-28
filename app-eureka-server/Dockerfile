FROM openjdk:12
VOLUME /tmp
EXPOSE 8761
ADD ./target/app-eureka-server-0.0.1-SNAPSHOT.jar app-eureka-server.jar
ENTRYPOINT ["java","-jar","/app-eureka-server.jar"]