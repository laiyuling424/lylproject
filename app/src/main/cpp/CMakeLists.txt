cmake_minimum_required(VERSION 3.4.1)

# cmakelists 设置c++11
INCLUDE(CheckCXXCompilerFlag)
CHECK_CXX_COMPILER_FLAG("-std=c++11" COMPILER_SUPPORTS_CXX11)
CHECK_CXX_COMPILER_FLAG("-std=c++0x" COMPILER_SUPPORTS_CXX0X)
if (COMPILER_SUPPORTS_CXX11)
    SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
elseif (COMPILER_SUPPORTS_CXX0X)
    SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++0x")
else ()
    MESSAGE(STATUS "The compiler ${CMAKE_CXX_COMPILER} has no C++11 support. Please use a different C++ compiler.")

endif ()
# cmakelists 设置c++11

# 需要引入我们头文件,以这个配置的目录为基准
include_directories(${CMAKE_SOURCE_DIR}/../jniLibs/include)

# 添加共享库搜索路径
LINK_DIRECTORIES(${CMAKE_SOURCE_DIR}/../jniLibs/armeabi)

# 指定源文件目录
AUX_SOURCE_DIRECTORY(${CMAKE_SOURCE_DIR} SRC_LIST)
file(GLOB bzip bzip/*.c)

include_directories(bzip)

add_library(
        native-lib

        SHARED
        # Provides a relative path to your source file(s).
        ${SRC_LIST}

        ${bzip}
)


find_library(
        log-lib

        log)

target_link_libraries(
        native-lib

        opencv_java3

        log)