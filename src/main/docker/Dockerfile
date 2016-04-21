############################################################
# Dockerfile RestMqttBridge
# Based on Java 8
############################################################
FROM java:8
MAINTAINER Thomas Fischl

############################################################

VOLUME /tmp
ADD application.properties application.properties
ADD com.github.thomasfischl.restmqttbridge-0.0.1.jar app.jar
RUN bash -c 'touch /app.jar'

############################################################

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]