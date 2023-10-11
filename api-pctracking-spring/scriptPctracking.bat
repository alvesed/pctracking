#!/bin/bash
while [ true ]
do
  echo "pctracking request"
  curl -X POST -H 'Content-Type: application/json' -d '{}' http://ec2-52-1-121-229.compute-1.amazonaws.com/pctracking
  sleep 60
done