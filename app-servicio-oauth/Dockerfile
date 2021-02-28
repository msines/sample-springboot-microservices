FROM openjdk:12
VOLUME /tmp
EXPOSE 9100
ADD ./target/app-oauth-0.0.1-SNAPSHOT.jar app-oauth.jar
ENTRYPOINT ["java","-jar","/app-oauth.jar"]