#!/bin/bash

KUB_HOME=/Users/justin/kubernetes

${KUB_HOME}/cluster/kubectl.sh create -f eureka-controller.yaml
sleep 5
${KUB_HOME}/cluster/kubectl.sh create -f eureka-service.yaml


echo "Services :"
${KUB_HOME}/cluster/kubectl.sh get services
echo "Replication Controllers :"
${KUB_HOME}/cluster/kubectl.sh get rc
echo "Pods :"
${KUB_HOME}/cluster/kubectl.sh get pods
