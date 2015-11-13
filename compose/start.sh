#!/bin/bash

echo Starting the data tier
./data-start.sh
sleep 5

echo Starting the infrastructure tier
./infra-start.sh
sleep 30

echo Starting the application tier
./app-start.sh
sleep 5

echo Starting the network tier
./net-start.sh
sleep 5


