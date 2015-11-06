#!/bin/bash

DOCKER_DAEMON_IP=`docker-machine ip default`

curl -v ios_app:0acd956b-0d38-4fa3-9772-d768f104534c@${DOCKER_DAEMON_IP}/auth/realms/master/protocol/openid-connect/token  \
 -d client_id=ios_app \
 -d grant_type=password \
 -d username=justin \
 -d scope=bookingi_api \
 -d password=toffee | jq -r .access_token
