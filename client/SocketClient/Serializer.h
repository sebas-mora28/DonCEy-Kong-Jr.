
#ifndef PROJECT_SERIALIZER_H
#define PROJECT_SERIALIZER_H


#include "cJson.h"
#include "Socket.h"

void serializeNewGame(int id);

void serializeObserver(int id);

void serializeMoveDKJ(int posX, int posY ,int facing, int jumping, int falling, int onLiana ,int id);

void serializeFruitCaught(int liana, char* type, int id);

void serializeAttacked(int id);

void serializeWin(int win);

void moveEntity(int posX, int posY, int liana, int id);

#endif //PROJECT_SERIALIZER_H
