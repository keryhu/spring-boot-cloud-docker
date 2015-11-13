#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`
KC_IP=9080

curl -s ios_app:7afd2bd8-8579-4edb-8490-216660d0ab89@${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/iberia.com/protocol/openid-connect/token  \
 -d scope=ios_app \
 -d grant_type=password \
 -d username=justin \
 -d password=toffee | jq .

ACCESS_TOKEN=`curl -s ios_app:7afd2bd8-8579-4edb-8490-216660d0ab89@${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/iberia.com/protocol/openid-connect/token  \
 -d scope=ro_product \
 -d grant_type=password \
 -d username=justin \
 -d password=toffee | jq -r .access_token`

echo ${ACCESS_TOKEN}
