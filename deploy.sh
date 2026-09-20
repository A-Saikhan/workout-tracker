#!/bin/bash

set -euo pipefail

IMAGE_TAG="$1"

cd /home/saikhan/workout-tracker/workout-tracker

sed -i "s/^IMAGE_TAG=.*/IMAGE_TAG=$IMAGE_TAG/" .env

docker compose -f docker-compose.yml -f docker-compose.prod.yml pull backend

docker compose -f docker-compose.yml -f docker-compose.prod.yml up -d --no-build

for i in {1..30}; do
  echo "Testing api..."
  if curl -f -s -o /dev/null "localhost:8080/api/exercises?size=1"; then
    echo "API is running."
    exit 0
  else
    echo "Failed: Trying again in 5 seconds."
    sleep 5
  fi
done

exit 1