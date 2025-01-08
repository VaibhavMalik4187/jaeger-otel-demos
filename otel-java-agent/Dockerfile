FROM eclipse-temurin:21-jre

COPY build/libs/app.jar /app.jar
COPY sdk-config.yml /sdk-config.yml
COPY build/agent/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:/opentelemetry-javaagent.jar \
                -jar /app.jar