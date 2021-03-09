#!/bin/bash

fecha=`date +"%Y/%m/%d-%H:%M"`

git add .
#Commit with the message provided
git commit -m "$1 $fecha"
git push
