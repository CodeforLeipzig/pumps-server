FROM openjdk:17-oracle
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/*.jar /opt/pumps-server.jar
ENTRYPOINT exec java $JAVA_OPTS -jar pumps-server.jar