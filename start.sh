#!/bin/bash

set -e

JAR_FILE="surge-dashboard.jar"

JAVA_OPTS="${JAVA_OPTS} -Duser.timezone=GMT+08"
JAVA_OPTS="${JAVA_OPTS} -Dlogging.file.path=/data/logs"

JAVA_OPTS="${JAVA_OPTS} -Dapp.feign.remote.surge.x-key=${SURGE_KEY}"
JAVA_OPTS="${JAVA_OPTS} -Dapp.feign.remote.surge.url=${SURGE_URL}"

java ${JAVA_OPTS} -jar /app/${JAR_FILE}