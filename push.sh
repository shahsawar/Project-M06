#!/bin/bash

fecha=`date +"%Y/%m/%d-%H:%M"`

git add .
git commit -m "$1 $fecha"
git push
