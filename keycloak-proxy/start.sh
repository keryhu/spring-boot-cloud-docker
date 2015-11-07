#!/bin/bash

docker run -d -p 7080:8080 \
    --name keycloak_proxy \
    justindav1s/keycloak_proxy

