#!/bin/bash

docker run -d -p 8761:8761 --name eureka justindav1s/eureka
sleep 5

docker run -d -p 8888:8888 --name config justindav1s/config
sleep 10

docker run -d -p 9000:9000 --name hystrix justindav1s/hystrix
sleep 5


for NUM in 1 2
do
    echo Starting product_$NUM
    docker run -d -p :8080 --name product_$NUM --link eureka:eureka --link config:config justindav1s/product
    sleep 5
done


docker run -d -p 8080:8080 --name basket --link eureka:eureka --link config:config justindav1s/basket



