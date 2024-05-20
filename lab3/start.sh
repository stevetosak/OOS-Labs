#!/bin/bash

docker compose build

docker compose up --scale tcp_client=5 --scale udp_client=5



