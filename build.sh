#!/bin/bash

cd Eureka
mvn clean package docker:build docker:start
sleep 10

cd ../Config
mvn clean package docker:build docker:start
sleep 10

cd ../Product
mvn clean package docker:build docker:start

cd ..

#docker-compose up
