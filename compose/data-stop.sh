#!/bin/bash

docker-compose -p data -f ./compose_conf/docker-compose-data.yml stop
docker-compose -p data -f ./compose_conf/docker-compose-data.yml rm
