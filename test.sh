#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`
LISTEN_PORT=32788


curl -X DELETE http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/clearall | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/create | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/create | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/list | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1/add/2 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1/add/3 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1/add/4 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1/add/12 | python -m json.tool
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1/remove/3 | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1 | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/list | python -m json.tool
sleep 1

curl -X POST http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1/empty | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/1 | python -m json.tool
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:${LISTEN_PORT}/basket/clearall | python -m json.tool
