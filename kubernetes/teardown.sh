#!/bin/bash

KUB_HOME=/Users/justin/kubernetes

${KUB_HOME}/cluster/kubectl.sh delete rc eureka
${KUB_HOME}/cluster/kubectl.sh delete service eureka

${KUB_HOME}/cluster/kubectl.sh delete rc config
${KUB_HOME}/cluster/kubectl.sh delete service config

${KUB_HOME}/cluster/kubectl.sh delete rc product-controller
${KUB_HOME}/cluster/kubectl.sh delete service product-service

./status.sh
