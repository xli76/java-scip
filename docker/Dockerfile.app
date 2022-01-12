# syntax=docker/dockerfile:1
FROM openjdk:17-alpine as java-scip

RUN addgroup -S app && adduser -S -G app app

RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g" /etc/apk/repositories; \
    apk add --no-cache gcc g++ cmake make

ADD docker/scipoptsuite-7.0.2.tgz /scip
WORKDIR /scip/scipoptsuite-7.0.2

RUN mkdir build; \
    cd build; \
    cmake ..; \
    make -j4

COPY docker/JSCIPOpt-master.zip /scip
WORKDIR /scip

RUN unzip JSCIPOpt-master.zip; \
    cd JSCIPOpt-master; \
    mkdir build; \
    cd build; \
    cmake .. -DSCIP_DIR=/scip/scipoptsuite-7.0.2/build; \
    make -j4

USER app

FROM gradle:7.3.3-jdk17-alpine AS build
# COPY --chown=gradle:gradle . /home/gradle/src
COPY . /home/gradle/src
# RUN addgroup -S app && adduser -S -G app app
USER root

RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g" /etc/apk/repositories; \
    apk add --no-cache git


WORKDIR /home/gradle/src

COPY --from=java-scip /scip/JSCIPOpt-master/build/Release/scip.jar /home/gradle/src/src/main/resources/lib/
COPY --from=java-scip /scip/JSCIPOpt-master/build/Release/libjscip.so /home/gradle/src/src/main/resources/lib/
COPY --from=java-scip /scip/scipoptsuite-7.0.2/build/lib/libscip.so.7.0 /home/gradle/src/src/main/resources/lib/

# USER app
ENV LD_LIBRARY_PATH="${LD_LIBRARY_PATH}:/home/gradle/src/src/main/resources/lib"
RUN gradle build --no-daemon

# FROM openjdk:17-alpine AS app

# EXPOSE 8080

# WORKDIR /app

# # COPY --from=build /home/gradle/src/algo-model-allocation-service/build/libs/*.jar .
# # COPY --from=build /home/gradle/src/start-docker.sh .

# # ENTRYPOINT ["/bin/sh", "-c", "start-docker.sh"]