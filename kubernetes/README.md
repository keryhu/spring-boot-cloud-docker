if you've been developing against a local docker-machine as I have, you'll need to side load those images into your minion

first download them locally, then upload to docker on your minion


MINION IP : 10.245.1.3
docker save -o eureka.tar justindav1s/eureka
scp eureka.tar vagrant@10.245.1.3
vagrant ssh minion-1
sudo docker load -i eureka.tar

repeat for config, hystrix, product and basket
