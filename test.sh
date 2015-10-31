#!/bin/bash
set -x

#jq : https://stedolan.github.io/jq/

DOCKER_DAEMON_IP=`docker-machine ip default`
BASKET_PORT=8083
PRODUCT_PORT=8081

ACCESS_TOKEN=`./zuul_resourceowner.sh`
echo ACCESS_TOKEN=${ACCESS_TOKEN}


curl -X GET http://${DOCKER_DAEMON_IP}:${PRODUCT_PORT}/product/1 | jq
curl -X GET http://${DOCKER_DAEMON_IP}:${PRODUCT_PORT}/product/2 | jq

curl -X DELETE http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/clearall | jq
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/create/1 | jq
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/create/2 | jq
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/list | jq
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/2 | jq
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/3 | jq
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/4 | jq
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/12 | jq
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/remove/3 | jq
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1 | jq
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/list | jq
sleep 1

curl -X POST http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/empty | jq
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1 | jq
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/clearall | jq
