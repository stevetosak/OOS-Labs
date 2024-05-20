#!/bin/bash

readFilePath="./resources/syncMeter.txt"
writeFilePath="./resources/syncMonitor.txt"

while true; do
#lock
	signal=`cat $readFilePath`
	while [ "$signal" == "WAIT" ]; do
		signal=`cat $readFilePath`
	done
#lock
	echo "WAIT" > "$writeFilePath"
	
	echo "Calculating average..."
	java SoundLevelMonitor
	avgNoise=`cat ./resources/noisepollution.txt`
	echo "Average of sound levels measured: ${avgNoise}"
	> ./resources/soundlevel.txt
	echo "DONE" > "$writeFilePath"
	sleep 4
done
