#!/bin/bash

#http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=justin&redirect_uri=http://example.com&scope=bookingapi&state=97536

CODE=CYYFaS

curl -v booking_app:app_secret@localhost:9999/auth/oauth/token \
 -d grant_type=authorization_code \
 -d client_id=booking_app \
 -d redirect_uri=http://example.com \
 -d code=$CODE -s