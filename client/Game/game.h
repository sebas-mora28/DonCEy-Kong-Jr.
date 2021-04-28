//
// Created by sebasmora on 23/4/21.
//

#ifndef CLIENT_GAME_CUH
#define CLIENT_GAME_CUH

#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <stdio.h>
#include <SDL_ttf.h>
#include "../constants.h"
#include "gameObject/DonkeyKongJr.h"
#include "gameObject/fruit.h"
#include "gameObject/enemy.h"


TTF_Font* Sans;
SDL_Window *window;
SDL_Renderer *renderer;
SDL_Texture *background;
int score;
int gameId;
int isObserver;
void serializeMoveDKJ(int posX, int posY,int facing, int jumping, int falling, int onLiana ,int id);
void serializeWin(int win);
void serializeAttacked(int id);
void serializeFruitCaught(int liana, char* type, int id);
void createWindow(int id, int observer);
void gameLoop();
void initAllGameObjets();
void initBackground();
void collisions();
void fruitCollision();
void enemiesCollision();
void hasWin();
SDL_Texture* loadTexture(const char* path);
void setFruitTexture(Fruit* fruit, char* type);
void setEnemyTextures(Enemy * enemy, char* type);
void putFruit(int liana, char* type);
int setPositionX(int liana);
int setPositionY(int liana);
void putEnemy(int liana, char* type, int speed);
void renderEnemies();
void renderFruits();
void renderDonkeyKongJr();
void renderScore();
void renderLives();
void updateScore(int score);
void updateLives(int lives);
void gameOver(int score_, int lives_);
void renderTextures();
void loadGameObjects(cJSON* enemies, cJSON* fruits, int posX_DKJ, int posY_DKJ);


#endif //CLIENT_GAME_CUH
