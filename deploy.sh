#!/bin/bash
DOCKER_DAEMON_IP=`docker-machine ip default`

docker run -d -p 8761:8761 --name eureka justindav1s/eureka
sleep 5

docker run -d -p 8888:8888 --name config justindav1s/config
sleep 10

docker run -d -p 9000:9000 --name hystrix justindav1s/hystrix
sleep 5

for NUM in 1
do
    echo Starting product_$NUM
    docker run -d -p 808${NUM}:8080 \
        --link eureka:eureka \
        --link config:config \
        --link mongo:mongo \
        --name product_$NUM \
        justindav1s/product
    sleep 5
done

for NUM in 3
do
    echo Starting basket_$NUM
    docker run -d -p 808${NUM}:8080 \
        -e "DOCKER_MACHINE_ADDR=${DOCKER_DAEMON_IP}" \
	    --link keycloak:keycloak \
        --link eureka:eureka \
        --link config:config \
        --link mongo:mongo \
        --name basket_$NUM \
        justindav1s/basket
    sleep 5
done

docker run -d -p 8080:8080 \
    -e "DOCKER_MACHINE_ADDR=${DOCKER_DAEMON_IP}" \
    --link eureka:eureka \
    --link config:config \
    --name zuul \
    justindav1s/zuul


docker run -d -p 80:80 \
    --link zuul:zuul \
    --name varnish \
    justindav1s/varnish



