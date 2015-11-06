#!/bin/bash

docker run -p 80:80 \
    --link keycloak:keycloak \
    --link zuul:zuul \
    --name httpd \
    justindav1s/httpd

