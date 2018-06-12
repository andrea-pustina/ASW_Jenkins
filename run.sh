#!/bin/bash

#docker stop $(docker ps -a -q)
#docker system prune -a --force

docker-compose build
docker-compose up

