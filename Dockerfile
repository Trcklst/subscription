FROM maven:3.6.2-jdk-11-slim as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
COPY ./get-subscription-ws ./get-subscription-ws
COPY ./get-subscription-api ./get-subscription-api
RUN mvn install
RUN cp get-subscription-ws/target/get-subscription-*.jar get-subscription.jar

FROM openjdk:11
WORKDIR /app
COPY --from=maven /app/get-subscription.jar ./get-subscription.jar
CMD java \
-Dspring.profiles.active=${SPRING_PROFILE} \
-Dserver.port=${SERVER_PORT} \
-jar \
/app/get-subscription.jar