FROM eclipse-temurin:21-jre

COPY target/java-spring-boot-mongodb-starter-1.0.0.jar /java-spring-boot-mongodb-starter-1.0.0.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:/opentelemetry-javaagent.jar \
                -jar /java-spring-boot-mongodb-starter-1.0.0.jar