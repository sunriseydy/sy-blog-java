#!/usr/bin/env bash

export BLOG_INIT_CACHE=false
export WP_SOURCE_TYPE=tp-db

export WP_PASSWORD=1
export WP_USERNAME=1

export SPRING_TYPECHO_DATASOURCE_URL='jdbc:mysql://localhost:3306/typecho?useUnicode=true&characterEncoding=utf-8&useSSL=false&useTimezone=true&serverTimezone=Asia/Shanghai'
export SPRING_TYPECHO_DATASOURCE_USERNAME=root
export SPRING_TYPECHO_DATASOURCE_PASSWORD=root

export SPRING_REDIS_HOST='localhost'
export SPRING_REDIS_PORT=6379
export SPRING_REDIS_DATABASE=2

mvn -T 4 -DskipTests=true package
java -jar target/sy-blog-java-0.0.1-SNAPSHOT.jar