#!/bin/bash

if [ ! -d "./resources" ]; then
	mkdir ./resources

else
	rm -rf ./resources/*
fi



touch ./resources/soundlevel.txt
touch ./resources/noisepollution.txt

###                              Za testiranje
docker rm soundlevelservices-networking-soundlevelmeter-1 soundlevelservices-networking-soundlevelmonitor-1 
docker rmi soundlevelservices-networking-soundlevelmonitor soundlevelservices-networking-soundlevelmeter
###

docker compose up
