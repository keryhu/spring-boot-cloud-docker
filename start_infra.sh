#!/bin/bash
DOCKER_DAEMON_IP=`docker-machine ip default`

echo Starting Mongo
docker run -d -p 8761:8761 --name eureka justindav1s/eureka
sleep 5

echo Starting Postgres
docker run -d -p 8888:8888 --name config justindav1s/config
sleep 10

echo Starting Kafka
docker run -d -p 9000:9000 --name hystrix justindav1s/hystrix
sleep 5

echo Starting Logstash

echo Starting Keycloak
