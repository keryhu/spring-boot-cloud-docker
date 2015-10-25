#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`

curl -X DELETE http://${DOCKER_DAEMON_IP}:8080/basket/clearall | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:8080/basket/create | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:8080/basket/create | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:8080/basket/list | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:8080/basket/1/add/2 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:8080/basket/1/add/3 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:8080/basket/1/add/4 | python -m json.tool
sleep 1

curl -X PUT http://${DOCKER_DAEMON_IP}:8080/basket/1/add/12 | python -m json.tool
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:8080/basket/1/remove/3 | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:8080/basket/1 | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:8080/basket/list | python -m json.tool
sleep 1

curl -X POST http://${DOCKER_DAEMON_IP}:8080/basket/1/empty | python -m json.tool
sleep 1

curl -X GET http://${DOCKER_DAEMON_IP}:8080/basket/1 | python -m json.tool
sleep 1

curl -X DELETE http://${DOCKER_DAEMON_IP}:8080/basket/clearall | python -m json.tool
