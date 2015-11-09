#!/bin/bash

docker rm -f `docker ps -a | grep justindav1s/zuul_keycloak |  awk -F' ' '{print $1}'`

