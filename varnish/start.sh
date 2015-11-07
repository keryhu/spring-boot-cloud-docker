#!/bin/bash

docker run -d -p 80:80 \
    --link zuul_keycloak:zuul_keycloak \
    --name varnish \
    justindav1s/varnish

