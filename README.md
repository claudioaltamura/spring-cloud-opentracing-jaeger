# Spring Cloud Opentracing Jaeger
Spring Cloud Opentracing Jaeger example

## Build

    mvn clean compile

## Run

    docker-compose up -d

    mvn spring-boot:run

## Stop

    docker-compose down -v

## Examples

    curl http://localhost:8080/simple
    curl http://lcoalhost:8080/combined

    http://localhost:16686/search
