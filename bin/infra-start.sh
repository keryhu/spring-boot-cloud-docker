#!/bin/bash

export DOCKER_DAEMON_IP=`docker-machine ip default`

docker-compose -p infra -f ./compose_conf/docker-compose-infra.yml up -d

