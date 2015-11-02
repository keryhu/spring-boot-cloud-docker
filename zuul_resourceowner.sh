#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`

curl -s booking_app:app_secret@${DOCKER_DAEMON_IP}:8080/auth/oauth/token  \
 -d grant_type=password \
 -d client_id=booking_app \
 -d scope=bookingapi \
 -d resourceid=basket \
 -d username=justin \
 -d password=toffee | jq -r .access_token
