#!/usr/bin/env bash
PRJ_PID=/home/fingate/tomcat/auth/auth.pid

kill -15 `cat $PRJ_PID`
rm -f $PRJ_PID