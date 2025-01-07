FROM eclipse-temurin:21-jre

COPY build/libs/app.jar /app.jar
COPY build/agent/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:/opentelemetry-javaagent.jar \
                -jar /app.jar