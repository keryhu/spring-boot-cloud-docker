#!/bin/bash

. ~/jdk8.sh

cd eureka
mvn clean package docker:build

cd ../config
mvn clean package docker:build

cd ../zuul
mvn clean package docker:build

cd ../hystrix
mvn clean package docker:build

cd ../commercial-model
mvn clean install

cd ../product
mvn clean package docker:build

cd ../basket
mvn clean package docker:build

cd ..


