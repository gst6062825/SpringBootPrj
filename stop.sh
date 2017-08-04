#!/bin/bash
PID=$(ps -ef | grep flow.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo "人口治安后台服务未启动"
else
    kill $PID
    echo "人口治安后台服务已关闭"
fi
