FROM openjdk:12
VOLUME /tmp
ADD ./target/app-usuarios-0.0.1-SNAPSHOT.jar app-usuarios.jar
ENTRYPOINT ["java","-jar","/app-usuarios.jar"]