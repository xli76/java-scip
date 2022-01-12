### Trouble shooting

1. nested exception is java.lang.UnsatisfiedLinkError: no jscip in java.library.path, 需要设置java.library.path
如果是gradle build需要配置

`
tasks.withType(JavaExec) {
	systemProperty "java.library.path", "src/main/resources/lib"
}
`

如果是java运行程序，需要加上-Djava.library.path=src/main/resources/lib

2. java.lang.UnsatisfiedLinkError: /app/libjscip.so: Error loading shared library libscip.so.7.0
需要设置LD_LIBRARY_PATH
如果是命令行，设置环境变量

`
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:src/main/resources/lib
`

如果是Dockerfile，需要加上ENV环境变量
`
ENV LD_LIBRARY_PATH="${LD_LIBRARY_PATH}:src/main/resources/lib"
`