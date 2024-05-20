#!/bin/bash
writeFilePath="./resources/syncMeter.txt"
readFilePath="./resources/syncMonitor.txt"

echo "WAIT" > "$writeFilePath"

while true; do
#lock
	signal=`cat $readFilePath`
	while [ "$signal" == "WAIT" ]; do
		signal=`cat $readFilePath`
	done
#lock
	
	echo "WAIT" > "$writeFilePath"
	echo "Writing sensor values..."
	java SoundLevelSensor
	
	soundlevels=`cat ./resources/soundlevel.txt`
	echo "Sound levels measured: [ ${soundlevels}]"
	echo "DONE" > "$writeFilePath"
	sleep 2.5
done
