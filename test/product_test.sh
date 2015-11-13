#!/bin/bash

#jq : https://stedolan.github.io/jq/

DOCKER_DAEMON_IP=`docker-machine ip default`
KC_IP=9080

ACCESS_TOKEN=`curl -s ios_app:7afd2bd8-8579-4edb-8490-216660d0ab89@${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/iberia.com/protocol/openid-connect/token  \
 -d grant_type=password \
 -d username=justin \
 -d password=toffee | jq -r .access_token`

#echo "*********"
curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X GET http://${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/master/protocol/openid-connect/userinfo | jq
#sleep 1
#echo "*********"

FLAG="-v"

#printf  "\n\n********* GET /api/product/3 \n"
#curl ${FLAG} -X GET http://${DOCKER_DAEMON_IP}/api/product/3 | jq

printf  "\n\n********* Authorization GET /api/product/3 \n"
curl ${FLAG} -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}/api/product/3 |jq

printf  "\n\n********* Authorization GET /api/product/3 \n"
curl ${FLAG} -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X GET http://${DOCKER_DAEMON_IP}/api/product/3 | jq

sleep 1
