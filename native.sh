#!/usr/bin/env bash
set -e
set -u
set -o pipefail

mvn -f ../hints/pom.xml  -DskipTests spring-javaformat:apply clean install
mvn -Pnative -DskipTests   native:compile
~/Desktop/twitter.sh && ./target/sample-twitter4j
