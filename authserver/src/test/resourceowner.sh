#!/bin/bash

curl -v -s booking_app:app_secret@localhost:9999/auth/oauth/token  \
 -d grant_type=password \
 -d client_id=booking_app \
 -d scope=bookingapi \
 -d username=justin \
 -d password=toffee | jq