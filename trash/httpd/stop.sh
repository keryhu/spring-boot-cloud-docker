#!/bin/bash

docker rm -f `docker ps -a | grep justindav1s/httpd |  awk -F' ' '{print $1}'`

