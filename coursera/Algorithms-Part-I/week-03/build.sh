#!/bin/bash

DIR_SRC="src"
DIR_OUT="out"
DIR_LIB="libs"

if [ ! -d ${DIR_SRC} ]; then
    echo "Source dir ${DIR_SRC} does not exists."
    exit 1
fi

if ! which "javac" > /dev/null 2>&1; then
    echo "Can not find the Java compiler."
    exit 2
fi

if [ ! -d ${DIR_OUT} ]; then
    mkdir ${DIR_OUT}
fi

javac -J-Duser.language=en -cp ${DIR_LIB}/* -d ${DIR_OUT} `find ${DIR_SRC} -name "*.java"`