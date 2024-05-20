#!/bin/bash

sensor_address="soundlevelservices-networking-soundlevelmeter-1"
monitor_address="soundlevelservices-networking-soundlevelmonitor-1"

send_to="$1"
target_port="2000"
message="$2"

target_address=""

if [ "$send_to" == "sensor" ]; then
	target_address="$sensor_address"
elif [ "$send_to" == "monitor" ]; then
	target_address="$monitor_address"
fi

echo -n "$message" | nc -w 1 "$target_address" "$target_port" 

echo "tcp_messenger: sent message $message to address $target_address."