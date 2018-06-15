#!/usr/bin/env bash


set -e

eval $(docker-machine env default)

## add this in etc/envoronments
export DOCKER_HOST_IP=192.168.99.100
export DOCKER_HOST_IP=192.168.99.100
export SERVER_IP=192.168.99.100

echo "Docker Swarm IP" 
echo $DOCKER_HOST_IP
echo "Docker Host" 
echo $DOCKER_HOST
echo $SERVER_IP

echo "Build the project and docker images"


docker-compose up -d --build

