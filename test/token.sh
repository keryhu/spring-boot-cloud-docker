#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`
KC_IP=9080

ACCESS_TOKEN=`curl -s iberia.com:75ada485-dac9-40b6-be1d-b7c193d1e3c4@${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/Iberia_Commercial/protocol/openid-connect/token  \
 -d grant_type=password \
 -d username=ios_app \
 -d password=toffee | jq -r .access_token`

echo ${ACCESS_TOKEN}
