#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`

curl -v ios_app:0230bddd-42b7-4d3a-8811-5f9ab1ceec78@${DOCKER_DAEMON_IP}:8080/auth/realms/master/protocol/openid-connect/token  \
 -d client_id=ios_app \
 -d grant_type=password \
 -d username=justin \
 -d scope=bookingi_api \
 -d password=toffee | jq -r .access_token
