//
// Created by sebasmora on 23/4/21.
//

#ifndef CLIENT_ENEMY_H
#define CLIENT_ENEMY_H


#include "SDL2/SDL.h"
#include "../../constants.h"

typedef struct Enemy {
    int posX;
    int posY;
    int speed;
    int liana;
    char* type;
    struct Enemy *next;
    SDL_Texture *down0;
    SDL_Texture *down1;
    SDL_Texture *up0;
    SDL_Texture *up1;
    SDL_Texture *currentSprite;
    int num_sprite;
    int goingDown;
} Enemy;


Enemy* initListEnemies(Enemy * list);
Enemy* addEnemy(Enemy* list, Enemy * fruit);
void moveEnemy(Enemy* enemy);
void moveEnemyRed(Enemy* enemy);
void moveEnemyBlue(Enemy* enemy);
void moveEntity(int posX, int posY, int liana, int id);


#endif //CLIENT_ENEMY_H
