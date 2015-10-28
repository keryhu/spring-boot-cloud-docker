#!/bin/bash

. ~/jdk8.sh

cd eureka
mvn clean package docker:build
sleep 10

cd ../config
mvn clean package docker:build
sleep 10

cd ../zuul
mvn clean package docker:build
sleep 10

cd ../hystrix
mvn clean package docker:build
sleep 10

cd ../commercial-model
mvn clean install

cd ../product
mvn clean package docker:build
sleep 2
mvn clean package docker:build
sleep 10

cd ../basket
mvn clean package docker:build

cd ..


