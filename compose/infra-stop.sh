#!/bin/bash

docker-compose -p infra -f ./compose_conf/docker-compose-infra.yml stop
docker-compose -p infra -f ./compose_conf/docker-compose-infra.yml rm
