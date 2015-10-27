#!/bin/bash

KUB_HOME=/Users/justin/kubernetes

echo ""
echo "Services :"
${KUB_HOME}/cluster/kubectl.sh get services
echo "Replication Controllers :"
${KUB_HOME}/cluster/kubectl.sh get rc
echo "Pods :"
${KUB_HOME}/cluster/kubectl.sh get pods
