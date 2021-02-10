#!/bin/sh

application_name=spring-boot-eureka-registry
server_port=8761

echo "Starting application [$application_name], Environment [$1] Node [$2]"

logpath=../logs/process_1
outfile="${logpath}/process.log"
if [ -f $outfile ]; then
    rm $outfile
fi
mkdir -p ${logpath} && touch $outfile
java -Djava.ext.dirs=../lib -jar ../lib/$application_name-*.jar --server.port=${server_port} --logging.file.path=${logpath} --spring.config.location=file:../env/application-$1.yml --spring.profiles.active=$2 >> $outfile 2>&1 &

sleep 5s
ps -fu $USER | grep -w $application_name | grep -v grep | awk '{print "Running Instance PID ["$2"] StartTime ["$5"] ["$11"] ["$12"]"}'