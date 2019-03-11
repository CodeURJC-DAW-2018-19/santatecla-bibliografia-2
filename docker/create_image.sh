#!/bin/bash
# -*- ENCODING: UTF-8 -*-
cd ../Ejemplo
mvn clean install
cd ..
docker build -t api -f docker/Dockerfile .
cd docker/
docker-compose up
