#!/bin/bash

$HIVE_HOME/bin/hive --service hiveserver2 &

# !connect jdbc:hive2://localhost:10000 root
