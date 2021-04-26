cmake_minimum_required(VERSION 3.19)
project(client)

cmake_minimum_required(VERSION 3.7)


set(CMAKE_C_STANDARD 11)
find_package(SDL2 REQUIRED)
include_directories(${SDL2_INCLUDE_DIRS})
set(CMAKE_C_FLAGS "-pthread")





add_executable(client main.c Game/game.c Game/game.h constants.h SocketClient/cJson.c SocketClient/cJson.h SocketClient/Socket.c SocketClient/Socket.h SocketClient/Serializer.c SocketClient/Serializer.h
        Game/gameObject/DonkeyKongJr.h Game/gameObject/fruit.h  Game/gameObject/fruit.c Game/gameObject/enemy.c Game/gameObject/enemy.h Game/gameObject/DonkeyKongJr.c menu.h menu.c)
target_link_libraries(client ${SDL2_LIBRARIES} SDL2_image SDL2_ttf)