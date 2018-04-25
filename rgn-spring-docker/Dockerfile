FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE

ENV JAVA_OPTS=""

COPY ${JAR_FILE} app.jar
# http://www.thezonemanager.com/2015/07/whats-so-special-about-devurandom.html
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "${JAVA_OPTS}", "-jar", "/app.jar"]

# https://stackoverflow.com/questions/37904682/how-do-i-use-docker-environment-variable-in-entrypoint-array
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom "${JAVA_OPTS}"  -jar /app.jar
