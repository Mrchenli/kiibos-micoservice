FROM openjdk:8-jre-alpine
MAINTAINER kiibos "mrchenli081@163.com"

ARG APP_NAME_JAR 
ENV APP_JAR $APP_NAME_JAR
ADD ${APP_NAME_JAR} /
ENTRYPOINT ["sh","-c","java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /$APP_JAR"]

