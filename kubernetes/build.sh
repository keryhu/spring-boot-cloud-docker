#!/bin/bash

KUB_HOME=/Users/justin/kubernetes

${KUB_HOME}/cluster/kubectl.sh create -f eureka-controller.yaml
sleep 5
${KUB_HOME}/cluster/kubectl.sh create -f eureka-service.yaml
sleep 5

${KUB_HOME}/cluster/kubectl.sh create -f config-controller.yaml
sleep 5
${KUB_HOME}/cluster/kubectl.sh create -f config-service.yaml
sleep 5

${KUB_HOME}/cluster/kubectl.sh create -f product-controller.yaml
sleep 5
${KUB_HOME}/cluster/kubectl.sh create -f product-service.yaml
sleep 5

${KUB_HOME}/cluster/kubectl.sh create -f basket-pod.yaml
sleep 5
${KUB_HOME}/cluster/kubectl.sh create -f basket-controller.yaml
sleep 5
${KUB_HOME}/cluster/kubectl.sh create -f basket-service.yaml
sleep 5

./status.sh
