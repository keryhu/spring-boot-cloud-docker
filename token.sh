#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`
KC_IP=9080

ACCESS_TOKEN=`curl -s iberia-services:568bb37a-db2c-4134-aac2-6deacb3e91a3@${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/master/protocol/openid-connect/token  \
 -d client_id=ios_app \
 -d grant_type=password \
 -d username=justin \
 -d scope=bookingi_api \
 -d password=toffee | jq -r .access_token`

echo ${ACCESS_TOKEN}
