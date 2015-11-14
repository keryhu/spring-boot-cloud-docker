#!/usr/bin/env bash

DOCKER_DAEMON_IP=`docker-machine ip default`

nohup /Users/justin/Java/kafka_2.9.1-0.8.2.2/bin/kafka-console-consumer.sh \
    --topic product-logs \
    --zookeeper ${DOCKER_DAEMON_IP}:2181 >> product.logs 2>&1 &