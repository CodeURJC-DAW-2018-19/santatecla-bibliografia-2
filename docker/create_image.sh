#!/bin/bash
# -*- ENCODING: UTF-8 -*-
cd ../Ejemplo

cd ..
docker build -t api -f docker/Dockerfile .
cd docker/
docker-compose up
