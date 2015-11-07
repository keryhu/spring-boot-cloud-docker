#!/bin/bash

docker run -d -p 80:80 \
    --link keycloakproxy:keycloakproxy \
    --name varnish \
    justindav1s/varnish

