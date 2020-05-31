FROM openjdk:11

ARG JAR_FILE
ADD target/${JAR_FILE} get-subscription.jar

CMD java \
    -Dspring.profiles.active=${SPRING_PROFILE} \
    -Dserver.port=${SERVER_PORT} \
    -jar \
    get-subscription.jar