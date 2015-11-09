#!/bin/bash

docker run -d -p 8080:8080 --name zuul_keycloak --link eureka:eureka --link config:config justindav1s/zuul_keycloak

