#!/bin/bash


docker run --name postgres -e POSTGRES_DATABASE=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password -e POSTGRES_ROOT_PASSWORD=root_password -d justindav1s/postgres

sleep 10

docker run -d -p 9080:8080 --name keycloak --link postgres:postgres justindav1s/keycloak-postgres

