# syntax=docker/dockerfile:1
FROM gradle:4.9.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN addgroup -S app && adduser -S -G app app


RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g" /etc/apk/repositories; \
    apk add --no-cache git

USER app

RUN gradle build --no-daemon

FROM openjdk:8-jre-slim AS app

EXPOSE 8080

WORKDIR /app

# COPY --from=build /home/gradle/src/algo-model-allocation-service/build/libs/*.jar .
# COPY --from=build /home/gradle/src/start-docker.sh .

# ENTRYPOINT ["/bin/sh", "-c", "start-docker.sh"]