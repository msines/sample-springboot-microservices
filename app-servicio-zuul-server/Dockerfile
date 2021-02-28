FROM openjdk:12
VOLUME /tmp
EXPOSE 8090
ADD ./target/app-zuul-server-0.0.1-SNAPSHOT.jar app-zuul-server.jar
ENTRYPOINT ["java","-jar","/app-zuul-server.jar"]