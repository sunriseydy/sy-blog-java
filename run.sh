#!/usr/bin/env bash

source .local.env

mvn -DskipTests=true clean package

nohup java -jar target/sy-blog-java-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &