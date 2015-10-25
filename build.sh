#!/bin/bash

cd Eureka
mvn clean package docker:build docker:start
sleep 10

cd ../Config
mvn clean package docker:build docker:start
sleep 10

cd ../hystrix
mvn clean package docker:build docker:start
sleep 10

cd ../Product
mvn clean package docker:build docker:start
sleep 2
mvn clean package docker:build docker:start
sleep 10

cd ../Basket
mvn clean package docker:build docker:start

cd ..


