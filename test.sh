#!/bin/bash

curl -X DELETE http://192.168.99.104:8080/basket/clearall | python -m json.tool
sleep 1

curl -X PUT http://192.168.99.104:8080/basket/create | python -m json.tool
sleep 1

curl -X PUT http://192.168.99.104:8080/basket/create | python -m json.tool
sleep 1

curl -X GET http://192.168.99.104:8080/basket/list | python -m json.tool
sleep 1

curl -X PUT http://192.168.99.104:8080/basket/1/add/2 | python -m json.tool
sleep 1

curl -X PUT http://192.168.99.104:8080/basket/1/add/3 | python -m json.tool
sleep 1

curl -X PUT http://192.168.99.104:8080/basket/1/add/4 | python -m json.tool
sleep 1

curl -X DELETE http://192.168.99.104:8080/basket/1/remove/3 | python -m json.tool
sleep 1

curl -X GET http://192.168.99.104:8080/basket/1 | python -m json.tool
sleep 1

curl -X GET http://192.168.99.104:8080/basket/list | python -m json.tool
sleep 1

curl -X POST http://192.168.99.104:8080/basket/1/empty | python -m json.tool
sleep 1

curl -X GET http://192.168.99.104:8080/basket/1 | python -m json.tool
sleep 1

curl -X DELETE http://192.168.99.104:8080/basket/clearall | python -m json.tool
