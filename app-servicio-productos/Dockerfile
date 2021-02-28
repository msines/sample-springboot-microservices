FROM openjdk:12
VOLUME /tmp
ADD ./target/app-articulos-0.0.1-SNAPSHOT.jar app-articulos.jar
ENTRYPOINT ["java","-jar","/app-articulos.jar"]