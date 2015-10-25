#!/bin/bash

cd eureka
mvn clean package docker:build docker:start
sleep 10

cd ../config
mvn clean package docker:build docker:start
sleep 10

cd ../hystrix
mvn clean package docker:build docker:start
sleep 10

cd ../commercial-model
mvn clean install

cd ../product
mvn clean package docker:build docker:start
sleep 2
mvn clean package docker:build docker:start
sleep 10

cd ../basket
mvn clean package docker:build docker:start

cd ..


