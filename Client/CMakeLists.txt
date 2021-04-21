cmake_minimum_required(VERSION 3.14)
project(project)

set(CMAKE_C_STANDARD 99)

FIND_PACKAGE(PkgConfig REQUIRED)
PKG_CHECK_MODULES(GTK3 REQUIRED gtk+-3.0)

INCLUDE_DIRECTORIES(${GTK3_INCLUDE_DIRS})
LINK_DIRECTORIES(${GTK3_LIBRARY_DIRS})
ADD_COMPILE_OPTIONS(${GTK3_CFLAGS_OTHER})
ADD_DEFINITIONS(${GTK3_CFLAGS_OTHER})
set(LIBRARIES ${LIBRARIES} ${GTK3_LIBRARIES})

SET(ALLEGRO_ROOT allegro5/)
INCLUDE_DIRECTORIES( ${ALLEGRO_ROOT}/include)
LINK_DIRECTORIES( /${ALLEGRO_ROOT}/lib)

set(FLAGS "-I${GTK3_INCLUDE_DIRS}")
message(STATUS "Flags: ${FLAGS}")
string(REPLACE ";" " -I" FLAGS "${FLAGS}")
set(CMAKE_C_FLAGS ${CMAKE_C_FLAGS} ${GTK3_FLAGS} ${FLAGS})

add_executable(project main.c src/socket/Socket.h src/socket/Socket.c src/socket/cJson.h src/socket/cJson.c src/logic/entity/DKJr.h src/logic/entity/Fruit.h src/logic/entity/Crocodile.h src/logic/lists/FruitNode.h src/logic/lists/FruitNode.c src/logic/lists/CrocodileNode.c src/logic/lists/CrocodileNode.h src/socket/Serializer.c src/socket/Serializer.h src/logic/Game.c src/logic/Game.h src/gui/gui.c src/gui/gui.h src/gui/allegro_window.c src/gui/allegro_window.h)
target_include_directories(project PUBLIC ${ALLEGRO_ROOT})
target_link_libraries(project ${GTK3_LIBRARIES}
        allegro
        allegro_color
        allegro_main
        allegro_primitives
        allegro_ttf
        allegro_acodec
        allegro_audio
        allegro_font
        allegro_image
        allegro_dialog)
