#!/bin/bash

MINION_IP=10.245.1.3
NODE_PORT=30303


curl -X DELETE http://${MINION_IP}:${NODE_PORT}/basket/clearall | python -m json.tool
sleep 1

curl -X PUT http://${MINION_IP}:${NODE_PORT}/basket/create | python -m json.tool
sleep 1

curl -X PUT http://${MINION_IP}:${NODE_PORT}/basket/create | python -m json.tool
sleep 1

curl -X GET http://${MINION_IP}:${NODE_PORT}/basket/list | python -m json.tool
sleep 1

curl -X PUT http://${MINION_IP}:${NODE_PORT}/basket/1/add/2 | python -m json.tool
sleep 1

curl -X PUT http://${MINION_IP}:${NODE_PORT}/basket/1/add/3 | python -m json.tool
sleep 1

curl -X PUT http://${MINION_IP}:${NODE_PORT}/basket/1/add/4 | python -m json.tool
sleep 1

curl -X PUT http://${MINION_IP}:${NODE_PORT}/basket/1/add/12 | python -m json.tool
sleep 1

curl -X DELETE http://${MINION_IP}:${NODE_PORT}/basket/1/remove/3 | python -m json.tool
sleep 1

curl -X GET http://${MINION_IP}:${NODE_PORT}/basket/1 | python -m json.tool
sleep 1

curl -X GET http://${MINION_IP}:${NODE_PORT}/basket/list | python -m json.tool
sleep 1

curl -X POST http://${MINION_IP}:${NODE_PORT}/basket/1/empty | python -m json.tool
sleep 1

curl -X GET http://${MINION_IP}:${NODE_PORT}/basket/1 | python -m json.tool
sleep 1

curl -X DELETE http://${MINION_IP}:${NODE_PORT}/basket/clearall | python -m json.tool
