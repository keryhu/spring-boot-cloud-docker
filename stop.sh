#!/bin/bash

docker rm -f `docker ps -a | grep justindav1s |  awk -F' ' '{print $1}'`

