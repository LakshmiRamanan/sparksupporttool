#!/bin/bash

DOCKER_COMMON_REPO=common-docker/scripts

if [ -d $DOCKER_COMMON_REPO ]
then
   echo "Update common-docker repository"
   cd common-docker
   git pull
   cd ..
else
   echo "Git Clone from common-docker repository"
   git clone https://github.intuit.com/PTG-Services/spark-support-tool.git
fi

cp -f ./common-docker/oneclick-common.sh ./oneclick-common-temp.sh
chmod 777 oneclick-common-temp.sh
./oneclick-common-temp.sh $*