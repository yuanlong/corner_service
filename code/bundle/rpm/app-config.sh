#!/bin/bash

echo "run app-config.sh target = $TARGET"

auto_config $TARGET/cornerservice.bundle.war-1.0-SNAPSHOT.war
auto_config $TARGET/web-deploy.jar

##echo "start config task-uber ..."
##auto_config $TARGET/cornerservice.task-uber.jar
