#!/bin/bash

#jq : https://stedolan.github.io/jq/


DOCKER_DAEMON_IP=`docker-machine ip default`

ACCESS_TOKEN=`./keycloak/getkeycloaktoken.sh`
echo ACCESS_TOKEN=${ACCESS_TOKEN}
echo "*********"
curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X GET http://${DOCKER_DAEMON_IP}/auth/realms/master/protocol/openid-connect/userinfo | jq
sleep 1
echo "*********"

curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}/basket/create/1
sleep 1

curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}/basket/create/2
sleep 1

curl  -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}/basket/1/add/2
sleep 1

curl  -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X DELETE http://${DOCKER_DAEMON_IP}/basket/clearall
