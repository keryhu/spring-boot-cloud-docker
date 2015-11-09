#!/bin/bash

#jq : https://stedolan.github.io/jq/

DOCKER_DAEMON_IP=`docker-machine ip default`
KC_IP=9080

ACCESS_TOKEN=`curl -s iberia.com:75ada485-dac9-40b6-be1d-b7c193d1e3c4@${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/Iberia_Commercial/protocol/openid-connect/token  \
 -d grant_type=password \
 -d username=ios_app \
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
