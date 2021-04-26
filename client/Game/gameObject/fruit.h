//
// Created by sebasmora on 23/4/21.
//

#ifndef CLIENT_FRUIT_H
#define CLIENT_FRUIT_H

#include <SDL2/SDL.h>
#include "../../constants.h"



typedef struct Fruit {
    int posX;
    int posY;
    int liana;
    char* type;
    struct Fruit *next;
    SDL_Texture *sprite;
} Fruit;



Fruit *fruitList;


void deleteFruit(int liana, char* type);
Fruit* initListFruits(Fruit* list);
Fruit* addFruit(Fruit* list, Fruit* fruit);
Fruit* getFruit();

#endif //CLIENT_FRUIT_H
