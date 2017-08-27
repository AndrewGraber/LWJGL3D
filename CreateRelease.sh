#!/bin/bash

BLUE='\033[1;34m'
GREEN='\033[1;32m'
NC='\033[0m'

./gradlew clean
printf "\n${BLUE}COMPILING PROJECT${NC}\n\n"
./gradlew build
printf "\n${BLUE}ADDING NATIVES${NC}\n\n"
./gradlew includeNatives
printf "\n${BLUE}MOVING LIBRARIES${NC}\n\n"
mkdir -p ./build/libs/libs/lwjgl
mkdir -p ./build/libs/libs/slick-util
cp /usr/lib/lwjgl/2.9.1/jar/lwjgl.jar ./build/libs/libs/lwjgl
cp /usr/lib/lwjgl/2.9.1/jar/lwjgl_util.jar ./build/libs/libs/lwjgl
cp /usr/lib/slick-util/slick-util.jar ./build/libs/libs/slick-util
printf "\n${BLUE}COMPRESSING FILES${NC}\n\n"
tar -zcvf project-release.tar.gz ./build/libs
zip -r project-release.zip ./build/libs/
mkdir -p out
mv project-release.tar.gz ./out
mv project-release.zip ./out
#./gradlew clean
printf "\n${GREEN}DONE CREATING RELEASE FILES!${NC}\n"
printf "\n${GREEN}FILES CAN BE FOUND IN ./out/${NC}\n\n"
