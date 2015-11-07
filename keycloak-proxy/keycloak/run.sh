#!/usr/bin/env bash

cd /keycloak

java -Dlog4j.configuration=file:./log4j.properties -jar bin/launcher.jar conf/proxy.json