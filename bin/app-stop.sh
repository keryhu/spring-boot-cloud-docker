#!/bin/bash

docker-compose -p app -f ./compose_conf/docker-compose-app.yml stop
docker-compose -p app -f ./compose_conf/docker-compose-app.yml rm

