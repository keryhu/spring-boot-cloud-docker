#!/bin/bash

docker rm -f `docker ps -a | grep justindav1s/keycloak_proxy |  awk -F' ' '{print $1}'`

