cmake_minimum_required (VERSION 2.8)
project (HelloJNI)

# per default compile a release version
if (NOT CMAKE_BUILD_TYPE)
  set (CMAKE_BUILD_TYPE "Release")
endif()

#
# find JNI and java
#
find_package (JNI REQUIRED)
find_package (Java REQUIRED)
include (UseJava)

set(JNI_LIBRARIES /Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home/lib/libjawt.dylib /Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home/lib/server/libjvm.dylib)
set(JNI_INCLUDE_DIRS /Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home/include /Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home/include/darwin)

message(${JNI_LIBRARIES})
message(${JNI_INCLUDE_DIRS})

include_directories (${JNI_INCLUDE_DIRS})
add_library (hello SHARED ${PROJECT_SOURCE_DIR}/src/hellojni_wrap.c)
set_target_properties(hello PROPERTIES LIBRARY_OUTPUT_DIRECTORY ${PROJECT_BINARY_DIR}/${CMAKE_BUILD_TYPE})

target_link_libraries (hello ${JNI_LIBRARIES})

add_jar (hellojar
        SOURCES ${PROJECT_SOURCE_DIR}/java/HelloJNI.java
        OUTPUT_NAME hello
        OUTPUT_DIR ${PROJECT_BINARY_DIR}/${CMAKE_BUILD_TYPE}
        )

add_jar (hello-example
        SOURCES ${PROJECT_SOURCE_DIR}/java/Hello.java
        INCLUDE_JARS hellojar
        OUTPUT_DIR ${PROJECT_BINARY_DIR}/${CMAKE_BUILD_TYPE}
        )
