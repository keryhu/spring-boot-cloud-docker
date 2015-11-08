#!/bin/bash

. ~/jdk8.sh

cd eureka
mvn clean package docker:build

cd ../config
mvn clean package docker:build

cd ../hystrix
mvn clean package docker:build

cd ../commercial-model
mvn clean install

cd ../product
mvn -DskipTests=true clean package docker:build 

cd ../basket
mvn -DskipTests=true clean package docker:build

cd ../zuul
mvn clean package docker:build

cd ../keycloak-proxy
./build.sh

cd ../varnish
./build.sh

cd ..


