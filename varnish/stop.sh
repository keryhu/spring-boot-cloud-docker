#!/bin/bash

docker rm -f `docker ps -a | grep justindav1s/varnish |  awk -F' ' '{print $1}'`

