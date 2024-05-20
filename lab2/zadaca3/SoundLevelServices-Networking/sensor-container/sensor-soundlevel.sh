#!/bin/bash
#writeFilePath="./resources/syncMeter.txt"
#readFilePath="./resources/syncMonitor.txt"


listen_port="2000"
netcat="/bin/nc"
tcp_messenger="./sync/tcp_messenger.sh"

#echo "WAIT" > "$writeFilePath"

$tcp_messenger "monitor" "WAIT"

echo "pomina wait sensor"

while true; do
#lock
echo "vleze vvo prv while sensor"
	signal=$($netcat -l -p "$listen_port" -q 1)
	echo "sensor signal pred while $signal"
	echo "$signal pred while"
	while [ "$signal" == "WAIT" ]; do
		signal=$($netcat -l -p "$listen_port" -q 1)
		echo "$signal vo while "
	done
#lock
	$tcp_messenger "monitor" "WAIT"

	echo "Writing sensor values..."
	java SoundLevelSensor
	soundlevels=`cat ./resources/soundlevel.txt`
	echo "Sound levels measured: [ ${soundlevels}]"

	$tcp_messenger "monitor" "DONE"
	sleep 2.5
done
