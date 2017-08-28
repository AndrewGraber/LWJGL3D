#!/bin/bash

BLUE='\033[1;34m'
GREEN='\033[1;32m'
NC='\033[0m'

printf "\n${BLUE}CREATING DIRECTORIES${NC}\n\n"
mkdir -p libs
mkdir -p libs/lwjgl
mkdir -p libs/slick-util
printf "\n${BLUE}DOWNLOADING LWJGL${NC}\n\n"
wget -O ./libs/lwjgl/lwjgl-2.9.1.zip "https://downloads.sourceforge.net/project/java-game-lib/Official%20Releases/LWJGL%202.9.1/lwjgl-2.9.1.zip"
cd ./libs/lwjgl
printf "\n${BLUE}EXTRACTING LWJGL${NC}\n\n"
unzip lwjgl-2.9.1.zip
mv ./lwjgl-2.9.1/jar/lwjgl.jar ./
mv ./lwjgl-2.9.1/jar/lwjgl_util.jar ./
mv ./lwjgl-2.9.1/native/ ./
printf "\n${BLUE}CLEANING UP${NC}\n\n"
rm -rf ./lwjgl-2.9.1
rm lwjgl-2.9.1.zip
cd ../../
printf "\n${BLUE}DOWNLOADING SLICK-UTIL${NC}\n\n"
wget -O ./libs/slick-util/slick-util.jar "http://slick.ninjacave.com/slick-util.jar"
printf "\n${GREEN}DEPENDENCIES NOW DOWNLOADED${NC}\n"
printf "${GREEN}They can be found in ./libs${NC}\n"
