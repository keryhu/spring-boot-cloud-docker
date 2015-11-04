#!/bin/bash


docker run --name postgres -e POSTGRES_DATABASE=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password -e POSTGRES_ROOT_PASSWORD=root_password -d postgres

sleep 10

docker run -p 8080:8080 --name keycloak --link postgres:postgres jboss/keycloak-postgres

