#!/bin/bash

CONTAINER_ID=`docker ps | grep basket | awk -F' ' '{print $1}'`
docker kill ${CONTAINER_ID}
docker rm ${CONTAINER_ID}

CONTAINER_ID=`docker ps | grep eureka | awk -F' ' '{print $1}'`
docker kill ${CONTAINER_ID}
docker rm ${CONTAINER_ID}
docker run -d -p 8761:8761 --name eureka justindav1s/eureka

CONTAINER_ID=`docker ps | grep zuul | awk -F' ' '{print $1}'`
docker kill ${CONTAINER_ID}
docker rm ${CONTAINER_ID}
docker run -d -p 8080:8080 --name zuul --link eureka:eureka --link config:config justindav1s/zuul

CONTAINER_ID=`docker ps | grep product | awk -F' ' '{print $1}'`
docker kill ${CONTAINER_ID}
docker rm ${CONTAINER_ID}
docker run -d -p 8081:8080 --name product --link eureka:eureka --link config:config --link mongo:mongo justindav1s/product

cd basket; mvn clean package docker:build; cd ..
docker run -d -p 8083:8080 --name basket --link eureka:eureka --link config:config --link mongo:mongo justindav1s/basket

docker ps -a
