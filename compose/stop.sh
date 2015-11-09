#!/bin/bash

echo Stopping the network tier
./net-stop.sh

echo Stopping the application tier
./app-stop.sh

echo Stopping the infrastructure tier
./infra-stop.sh

echo Stopping the data tier
./data-stop.sh









