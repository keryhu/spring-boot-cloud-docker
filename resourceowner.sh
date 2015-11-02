#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`

#curl -s booking_app:app_secret@${DOCKER_DAEMON_IP}:9999/auth/oauth/token  \
# -d grant_type=password \
# -d client_id=booking_app \
# -d scope=bookingapi \
# -d username=justin \
# -d password=toffee | jq

curl -s booking_app:app_secret@${DOCKER_DAEMON_IP}:9999/auth/oauth/token  \
 -d grant_type=password \
 -d client_id=booking_app \
 -d scope=bookingapi \
 -d username=justin \
 -d password=toffee | jq -r .access_token
