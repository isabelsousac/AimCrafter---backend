#!/usr/bin/env bash

docker run --name aim_crafter_db -e POSTGRES_PASSWORD=postgrespw -e POSTGRES_USER=postgres -d -p 5432:5432 postgres