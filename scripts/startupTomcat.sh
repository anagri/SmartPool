#!/bin/bash

/usr/local/apache-tomcat-7.0.29/bin/startup.sh

until [ "`curl --silent --show-error --connect-timeout 1 -I http://localhost:9090 | grep 'Coyote'`" != "" ];
do
  echo --- sleeping for 5 seconds
  sleep 5
done

echo Tomcat is ready!


