#!/bin/bash

CONTAINER_ID=`docker ps | grep authserver | awk -F' ' '{print $1}'`

docker kill ${CONTAINER_ID}
docker rm ${CONTAINER_ID}

cd authserver; mvn clean package docker:build; cd ..
docker run -d -p 9999:9999 --name authserver justindav1s/authserver

docker ps -a
