# Test for OpenTelemetry with Quarkus #

Run services:
* `docker-compose up -d`
* `cd label-service && mvn clean quarkus:dev`
* `cd opentelemetry-quickstart && mvn clean quarkus:dev`

Call service:
* `curl -X GET http://localhost:8080/fruit/id/1`

Watch jaeger dashboard:
* Open in browser http://localhost:16686/