docker run -d --name jaeger \
  -p 16686:16686 \
  -p 4317:4317 \
  -p 4318:4318 \
  jaegertracing/all-in-one

java -javaagent:./build/agent/opentelemetry-javaagent.jar -Dotel.traces.exporter=otlp -Dotel.logs.exporter=logging -Dotel.metrics.exporter=logging -Dotel.service.name=java-agent-service -Dotel.exporter.otel.endpoint=http://localhost:4318 -jar ./build/libs/app.jar
