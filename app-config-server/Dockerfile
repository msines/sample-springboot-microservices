FROM openjdk:12
VOLUME /tmp
EXPOSE 8888
ADD ./target/app-config-server-0.0.1-SNAPSHOT.jar app-config-server.jar
ENTRYPOINT ["java","-jar","/app-config-server.jar"]