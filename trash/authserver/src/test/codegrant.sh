#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`
LISTEN_PORT=8080

#http://192.168.99.101:8080/auth/oauth/authorize?response_type=code&client_id=booking_app&redirect_uri=http://example.com&scope=bookingapi&state=97536

CODE=viiUc8

curl -v booking_app:app_secret@192.168.99.101:8080/auth/oauth/token \
 -d grant_type=authorization_code \
 -d client_id=booking_app \
 -d redirect_uri=http://example.com \
 -d code=$CODE -s
