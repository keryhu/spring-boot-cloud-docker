#!/bin/bash

KUB_HOME=/Users/justin/kubernetes

${KUB_HOME}/cluster/kubectl.sh delete rc eureka
${KUB_HOME}/cluster/kubectl.sh delete service eureka

${KUB_HOME}/cluster/kubectl.sh get services
${KUB_HOME}/cluster/kubectl.sh get rc
