#!/usr/bin/env bash

cd ../commercial-model
mvn clean install

cd ../product
mvn -DskipTests=true clean package docker:build

cd ../basket
mvn -DskipTests=true clean package docker:build

cd ../build