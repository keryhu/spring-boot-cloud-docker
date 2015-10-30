#!/bin/bash

set -x

DOCKER_DAEMON_IP=`docker-machine ip default`
BASKET_PORT=32770
PRODUCT_PORT=32768

curl -X GET http://${DOCKER_DAEMON_IP}:${PRODUCT_PORT}/product/1 | python -m json.tool
curl -X GET http://${DOCKER_DAEMON_IP}:${PRODUCT_PORT}/product/2 | python -m json.tool

curl -X DELETE http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/clearall | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/create/1 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/create/2 | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/list | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/2 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/3 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/4 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/add/12 | python -m json.tool
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/remove/3 | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1 | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/list | python -m json.tool
sleep 1

curl -X POST http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1/empty | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/1 | python -m json.tool
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:${BASKET_PORT}/basket/clearall | python -m json.tool
