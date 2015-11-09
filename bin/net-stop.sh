#!/bin/bash

docker-compose -p net -f ./compose_conf/docker-compose-net.yml stop
docker-compose -p net -f ./compose_conf/docker-compose-net.yml rm

