FROM eclipse-temurin:21-jre

COPY build/libs/app.jar /app.jar
COPY build/agent/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:/opentelemetry-javaagent.jar \
                -Dotel.traces.exporter=logging \
                -Dotel.logs.exporter=logging \
                -Dotel.metrics.exporter=logging \
                -jar /app.jar