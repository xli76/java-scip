#!/bin/sh

system_arch() {
    platform=$1
    case $platform in
        linux/amd64)
        echo 'x86'
        ;;
        linux/arm64)
        echo 'arm64'
        ;;
    esac
}

build_jni() {
    os=$1
    platform=$2
    echo Building allocate:java-scip for os $os and platform $platform

    docker build --platform=$platform -t allocate:java-scip . -f docker/Dockerfile.dep.${os}

    echo Copy dependency

    docker create --name extract allocate:java-scip

    platform_dir="`system_arch ${platform}`"
#    docker cp extract:/scip/JSCIPOpt-master/build/Release/scip.jar src/main/resources/lib/
    docker cp extract:/scip/JSCIPOpt-master/build/Release/libjscip.so src/main/resources/lib/${os}/${platform_dir}/
    docker cp extract:/scip/scipoptsuite-7.0.2/build/lib/libscip.so.7.0.2.0 src/main/resources/lib/${os}/${platform_dir}/
    mv src/main/resources/lib/${os}/${platform_dir}/libscip.so.7.0.2.0 src/main/resources/lib/${os}/${platform_dir}/libscip.so.7.0
    docker rm -f extract

    echo Finished
}

# intel chip
build_jni alpine linux/amd64
# # m1 chip
# build_jni centos linux/arm64