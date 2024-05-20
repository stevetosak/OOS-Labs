#!/bin/bash

#readFilePath="./resources/syncMeter.txt"
#writeFilePath="./resources/syncMonitor.txt"

listen_port="2000"
tcp_messenger="./sync/tcp_messenger.sh"
netcat="/bin/nc"

signal=""

while true; do
#lock
	signal=$($netcat -l -p "$listen_port")
	echo "monitor - signal pred while $signal"
	echo "monitor pred while signal: $signal"
	while [ "$signal" == "WAIT" ] ; do
		signal=$($netcat -l -p "$listen_port" -q 1)
		echo "monitor - $signal"
	done
#lock
	#echo "WAIT" > "$writeFilePath"
	$tcp_messenger "sensor" "WAIT"
	echo "Calculating average..."
	java SoundLevelMonitor
	avgNoise=`cat ./resources/noisepollution.txt`
	echo "Average of sound levels measured: ${avgNoise}"
	> ./resources/soundlevel.txt
	#echo "DONE" > "$writeFilePath"
	$tcp_messenger "sensor" "DONE"
	sleep 4
done
