#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`

curl -v ios_app:dc674937-3f8c-411a-bd4f-bd354f963296@${DOCKER_DAEMON_IP}:8080/auth/realms/master/protocol/openid-connect/token  \
 -d client_id=ios_app \
 -d grant_type=password \
 -d username=justin \
 -d scope=bookingi_api \
 -d password=toffee | jq -r .access_token
