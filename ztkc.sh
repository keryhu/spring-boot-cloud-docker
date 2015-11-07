#!/bin/bash

#jq : https://stedolan.github.io/jq/

DOCKER_DAEMON_IP=`docker-machine ip default`
KC_IP=9080

ACCESS_TOKEN=`curl -v ios_app:0acd956b-0d38-4fa3-9772-d768f104534c@${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/master/protocol/openid-connect/token  \
 -d client_id=ios_app \
 -d grant_type=password \
 -d username=justin \
 -d scope=bookingi_api \
 -d password=toffee | jq -r .access_token`

echo ACCESS_TOKEN=${ACCESS_TOKEN}
echo "*********"
curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X GET http://${DOCKER_DAEMON_IP}:${KC_IP}/auth/realms/master/protocol/openid-connect/userinfo | jq
sleep 1
echo "*********"

curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}/api/basket/create/1
sleep 1

curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}/api/basket/create/2
sleep 1

curl  -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}/api/basket/1/add/2
sleep 1

curl  -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X DELETE http://${DOCKER_DAEMON_IP}/api/basket/clearall
