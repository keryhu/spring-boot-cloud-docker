#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`
ACCESS_TOKEN=`./keycloak/getkeycloaktoken.sh`
echo ACCESS TOKEN=${ACCESS_TOKEN}

curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" http://${DOCKER_DAEMON_IP}/auth/realms/master/protocol/openid-connect/userinfo | jq
