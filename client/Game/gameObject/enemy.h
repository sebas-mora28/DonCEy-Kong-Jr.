//
// Created by sebasmora on 23/4/21.
//

#ifndef CLIENT_ENEMY_H
#define CLIENT_ENEMY_H


#include "SDL2/SDL.h"
#include "../../constants.h"
#include "../../SocketClient/cJson.h"

typedef struct Enemy {
    int posX;
    int posY;
    int speed;
    int liana;
    char* type;
    int state;
    struct Enemy *next;
    SDL_Texture *down0;
    SDL_Texture *down1;
    SDL_Texture *up0;
    SDL_Texture *up1;
    SDL_Texture *currentSprite;
    int num_sprite;
    int goingDown;
} Enemy;
Enemy *enemyList;



Enemy* initListEnemies(Enemy * list);
Enemy* addEnemy(Enemy* list, Enemy * fruit);
Enemy* getEnemy(Enemy* list, int liana, char* type);
void moveEnemy(Enemy* enemy, int gameId);
void moveEnemyRed(Enemy* enemy);
void moveEnemyBlue(Enemy* enemy);
void down(Enemy* enemy);
void up(Enemy* enemy);
void serializeMoveEnemy(Enemy* list, int id);
void updateEnemiesPosition(cJSON* enemiesInfo);

#endif //CLIENT_ENEMY_H
