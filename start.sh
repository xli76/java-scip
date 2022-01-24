#!/bin/bash

os_name() {
    case "`uname -s`" in
        Darwin)
        echo 'macosx'
        ;;
        Linux)
        echo 'linux'
        ;;
    esac
}

os_arch() {
    case "`arch`" in
        x86_64)
        echo 'x86'
        ;;
        i386)
        echo 'x86'
        ;;
        aarch64)
        echo 'arm64'
        ;;
        arm64)
        echo 'arm64'
        ;;
    esac
}

os_name="`os_name`"
os_arch="`os_arch`"

export JAVA_LIBRARY_PATH=src/main/resources/lib/${os_name}/${os_arch}

java -Djava.library.path=${JAVA_LIBRARY_PATH} -jar build/libs/java-scip-0.0.1-SNAPSHOT.jar > svc.log 2>&1 &