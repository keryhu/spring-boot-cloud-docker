#!/bin/bash

#jq : https://stedolan.github.io/jq/


DOCKER_DAEMON_IP=`docker-machine ip default`
LISTEN_PORT=8080

ACCESS_TOKEN=`./zuul_resourceowner.sh`
#ACCESS_TOKEN=2ae50c5c-7603-4033-8b73-b9a1e99ebccc
echo ACCESS_TOKEN=${ACCESS_TOKEN}

curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X GET http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/auth/user | jq
sleep 1

curl -v -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/create/1 | jq
sleep 1

curl  -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1/add/2 | jq
sleep 1

curl  -H  "Authorization: Bearer ${ACCESS_TOKEN}" -X DELETE http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/clearall | jq
