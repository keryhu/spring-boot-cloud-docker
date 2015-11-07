#!/bin/bash

docker run -d -p 7080:8080 \
    --name keycloakproxy \
    justindav1s/keycloak_proxy

