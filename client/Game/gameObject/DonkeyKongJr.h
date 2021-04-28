//
// Created by sebasmora on 23/4/21.
//

#ifndef CLIENT_DONKEYKONGJR_H
#define CLIENT_DONKEYKONGJR_H

#include "SDL.h"
#include "../../constants.h"

typedef struct DKJ{
    int posX;
    int posY;
    int facing;
    int jumping;
    int falling;
    int lives;
    int currentSpriteNum;
    int onLiana;
    int positionStartJumping;
    int prevPositionX;
    int prevPositionY;
    SDL_Texture* currentSprite;
    SDL_Texture *left1;
    SDL_Texture *left2;
    SDL_Texture *left3;
    SDL_Texture *right1;
    SDL_Texture *right2;
    SDL_Texture *right3;
    SDL_Texture *win;
    SDL_Texture *dead;
} DKJ;

DKJ* donkeyKongJr;

DKJ* initDonkeyKongJr();
void moveDKJ(int dir_x, int dir_y);
void moveLeft();
void moveRight();
int onLiana();
int is_on_platform();
void jumping();
void falling();
void initJump();
void DonkeyKongJrAnimation();
void updateDKJPosition(int posX_, int posY_, int facing_, int jumping_, int falling_, int onLiana);
void setDefaultValues();
void moveUp();
void moveDown();


#endif //CLIENT_DONKEYKONGJR_H
