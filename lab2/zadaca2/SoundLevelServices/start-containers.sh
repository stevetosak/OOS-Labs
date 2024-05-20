#!/bin/bash

if [ ! -d "./resources" ]; then
	mkdir ./resources

else
	rm -rf ./resources/*
fi

touch ./resources/soundlevel.txt
touch ./resources/noisepollution.txt
touch ./resources/syncMeter.txt
touch ./resources/syncMonitor.txt

###                              Za testiranje
docker rm soundlevelservices-soundlevelmeter-1 soundlevelservices-soundlevelmonitor-1
docker rmi soundlevelservices-soundlevelmonitor soundlevelservices-soundlevelmeter
###

docker compose up
