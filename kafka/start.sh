#!/bin/bash

docker run -d -p 2181:2181 -p 9092:9092 --name kafka --env ADVERTISED_HOST=`docker-machine ip default` --env ADVERTISED_PORT=9092 spotify/kafka
