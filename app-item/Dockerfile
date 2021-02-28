FROM openjdk:12
VOLUME /tmp
EXPOSE 8002
ADD ./target/app-pedido-0.0.1-SNAPSHOT.jar app-pedido.jar
ENTRYPOINT ["java","-jar","/app-pedido.jar"]