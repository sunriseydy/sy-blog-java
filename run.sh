#!/usr/bin/env bash
set -o errexit # 只要发生错误，就终止执行
set -o pipefail # 只要一个子命令失败，整个管道命令就失败，脚本就会终止执行

export SPRING_PROFILES_ACTIVE=default
source .env.local

if [ "$1" != "-l" ]; then
  git pull

  mvn -DskipTests=true clean package
fi

if [ "$SPRING_PROFILES_ACTIVE" == "default" ]; then
  java -jar -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE target/sy-blog-java-0.0.1-SNAPSHOT.jar
else
  nohup java -jar -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE target/sy-blog-java-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
fi