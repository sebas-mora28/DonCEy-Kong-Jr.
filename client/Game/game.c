//
// Created by sebasmora on 23/4/21.
//

#include <slcurses.h>
#include "game.h"



/**
 * Name: createWindow
 * Description: creates game windows, initialize all objects and start gameloop
 * @param id game id
 * @param observing indicates if players is a observer or not
 */
void createWindow(int id, int observing){

    gameId = id;
    isObserver = observing;

    if(SDL_Init(SDL_INIT_EVERYTHING) < 0){
        printf("Could not initialise SDL");
        exit(-1);
    };
    IMG_Init(IMG_INIT_PNG);
    if(TTF_Init() == -1){
        printf("TTF_INIT: %s\n", TTF_GetError());

    }
    TTF_Init();


    window = SDL_CreateWindow(
            "DonCEy-Kong-Jr",                  // window title
            SDL_WINDOWPOS_UNDEFINED,           // initial x position
            SDL_WINDOWPOS_UNDEFINED,           // initial y position
            700,                               // width, in pixels
            700,                               // height, in pixels
            SDL_WINDOW_ALLOW_HIGHDPI                  // flags - see below
    );

    renderer = SDL_CreateRenderer(window, -1, 0);




    SDL_RenderClear(renderer);

    SDL_RenderPresent(renderer);

    SDL_Delay(500);

    fruitList = initListFruits(fruitList);
    enemyList = initListEnemies(enemyList);

    initBackground();
    initAllGameObjets();
    gameLoop();

}


/**
 * Name: initAllGameObjects
 * Description: initializes donkey kong jr
 */
void initAllGameObjets(){
    donkeyKongJr = initDonkeyKongJr();
    Sans = TTF_OpenFont("../resources/OpenSans-Regular.ttf", 12);

}

/**
 * Name: initBackground
 * Description: initializes game background
 */
void initBackground(){
    background = loadTexture(BACKGROUND_IMAGE_PATH);

}


/**
 * Name : gameloop
 * Description: game loop, handles key event and renders all textures.
 */
void gameLoop(){
    SDL_Event event;
    bool quit = 0;


    while(!quit){


        while(SDL_PollEvent( &event) !=0 && !isObserver){


            switch (event.type) {
                case SDL_QUIT:
                    quit = 1;
                    break;

                case SDL_KEYDOWN:
                    switch (event.key.keysym.sym) {

                        case SDLK_a:
                            moveDKJ(LEFT, NEUTRAL);
                            break;

                        case SDLK_w:
                            if(donkeyKongJr->onLiana){
                                moveDKJ(NEUTRAL, UP);
                            }
                            break;

                        case SDLK_s:
                            if(donkeyKongJr->onLiana){
                                moveDKJ(NEUTRAL, DOWN);

                            }
                            break;

                        case SDLK_d:
                            moveDKJ(RIGHT, NEUTRAL);
                            break;

                        case SDLK_SPACE:
                            initJump();
                            break;

                        default:
                            break;
                    }

                default:
                    break;

            }
        }



            moveEnemy(enemyList);

            if(donkeyKongJr->jumping){
                jumping();
            }
            if(donkeyKongJr->falling){
                falling();

            }

            SDL_RenderClear(renderer);


            SDL_RenderCopy(renderer, background, NULL, NULL);


            renderTextures();
            collisions();
            hasWin();


            SDL_RenderPresent(renderer);
            SDL_Delay(DELAY);
    }
}





/**
 * Name: renderDonkeyKongJr
 * Description: renders donkey kong Jr texture
 */
void renderDonkeyKongJr(){
    SDL_Rect dstrect = {donkeyKongJr->posX, donkeyKongJr->posY, 80, 40};
    SDL_RenderCopy(renderer, donkeyKongJr->currentSprite, NULL, &dstrect);
    if(!isObserver){
        if(donkeyKongJr->posX != donkeyKongJr->prevPositionX || donkeyKongJr->posY != donkeyKongJr->prevPositionY){
            if(!isObserver){
                serializeMoveDKJ(donkeyKongJr->posX, donkeyKongJr->posY, donkeyKongJr->facing ,donkeyKongJr->jumping, donkeyKongJr->falling, donkeyKongJr->onLiana ,gameId);
            }
            donkeyKongJr->prevPositionX = donkeyKongJr->posX;
            donkeyKongJr->prevPositionY = donkeyKongJr->posY;
        }
    }

}




void renderTextures(){
    renderFruits();
    renderEnemies();
    renderDonkeyKongJr();
    renderScore();
    renderLives();

}


/**
 *
 */
void renderDKJKillAnimation(){
    donkeyKongJr->currentSprite = donkeyKongJr->dead;
    SDL_Rect dstrect = {donkeyKongJr->posX, donkeyKongJr->posY, 80, 40};
    SDL_RenderCopy(renderer, donkeyKongJr->currentSprite, NULL, &dstrect);
}



/**
 * Name: collisions
 * Description:
 */
void collisions(){
    enemiesCollision();
    fruitCollision();
}



/**
 * Name: enemiesCollision
 * Description: handles enemies collision, verify if DKJ collided a enemy
 */
void enemiesCollision() {

    Enemy *current = enemyList;

    while (current != NULL) {

        if (donkeyKongJr->posX >= current->posX - COLLISION_RANGE && donkeyKongJr->posX <= current->posX + COLLISION_RANGE && donkeyKongJr->posY >= current->posY - COLLISION_RANGE && donkeyKongJr->posY <= current->posY + COLLISION_RANGE) {
            renderDKJKillAnimation();
            SDL_RenderPresent(renderer);
            falling();
            if(!isObserver){
                serializeAttacked(gameId);
            }
            SDL_Delay(DELAY_ANIMATION);
            setDefaultValues();
            break;

        }

            current = current->next;
        }

}


/**
 * Name: fruitCollision
 * Description: handles enemies collision, verify if DKJ collided a fruit
 */
void fruitCollision(){

    Fruit* current = fruitList;

    while (current != NULL) {

        if (donkeyKongJr->posX >= current->posX - COLLISION_RANGE && donkeyKongJr->posX <= current->posX + COLLISION_RANGE && donkeyKongJr->posY >= current->posY - COLLISION_RANGE && donkeyKongJr->posY <= current->posY + COLLISION_RANGE) {
            if(current->sprite != NULL){
                current->sprite = NULL;
                if(!isObserver){

                    serializeFruitCaught(current->liana, current->type, gameId);
                }
            }
        }
            current = current->next;

        }
    }




/**
 * Name: renderFruits
 * Description: renders fruits textures
 */
void renderFruits(){
    if(fruitList != NULL){
        Fruit* temp = fruitList;
        while(temp != NULL){
            SDL_Rect dstrect = {temp->posX, temp->posY, 40, 40};
            SDL_RenderCopy(renderer, temp->sprite, NULL, &dstrect);
            temp = temp->next;

        }
    }


}

/**
 * Name: renderEnemies
 * Description: renders enemies textures
 */
void renderEnemies(){
    if(enemyList != NULL){
        Enemy * temp = enemyList;
        while(temp != NULL){
            SDL_Rect dstrect = {temp->posX, temp->posY, 40, 40};
            SDL_RenderCopy(renderer, temp->currentSprite, NULL, &dstrect);
            temp = temp->next;

        }

    }

}



/**
 *
 */
void updateScore(int newScore){
    donkeyKongJr->score = newScore;
}

void updateLives(int newLives){
    donkeyKongJr->lives = newLives;
}

/**
 * Name: renderScore
 * Description: render score texture
 */
void renderScore(){
    if(Sans == NULL){
        printf("%s \n", TTF_GetError());
    }
    SDL_Color White = {255, 255, 255};
    char* scoreLabel =  "Score";
    SDL_Surface* surfaceMessage = TTF_RenderText_Solid(Sans, scoreLabel, White);
    SDL_Texture* Message = SDL_CreateTextureFromSurface(renderer, surfaceMessage);
    SDL_Rect Message_rect = {0,0,100, 70};
    SDL_RenderCopy(renderer, Message, NULL, &Message_rect);

    char score_[20];
    sprintf(score_, "%d", donkeyKongJr->score);
    SDL_Surface* surfaceScore = TTF_RenderText_Solid(Sans, score_, White);
    SDL_Texture* scoreTexture = SDL_CreateTextureFromSurface(renderer, surfaceScore);
    SDL_Rect score_rect = {150,0,70, 70};
    SDL_RenderCopy(renderer, scoreTexture, NULL, &score_rect);
    SDL_FreeSurface(surfaceMessage);
    SDL_FreeSurface(surfaceScore);
}


/**
 * Name: renderScore
 * Description: render score texture
 */
void renderLives(){
    if(Sans == NULL){
        printf("%s \n", TTF_GetError());
    }
    SDL_Color White = {255, 255, 255};

    SDL_Surface* surfaceMessage = TTF_RenderText_Solid(Sans, "Lives", White);
    SDL_Texture* Message = SDL_CreateTextureFromSurface(renderer, surfaceMessage);
    SDL_Rect Message_rect = {300,0,100, 70};

    char lives_[20];
    sprintf(lives_, "%d", donkeyKongJr->lives);
    SDL_Surface* surfaceLives = TTF_RenderText_Solid(Sans, lives_, White);
    SDL_Texture* LivesTexture = SDL_CreateTextureFromSurface(renderer, surfaceLives);
    SDL_Rect lives_rect = {450,0,70, 70};
    SDL_RenderCopy(renderer, LivesTexture, NULL, &lives_rect);
    SDL_RenderCopy(renderer, Message, NULL, &Message_rect);
    SDL_FreeSurface(surfaceMessage);
    SDL_FreeSurface(surfaceLives);
}





/**
 * Name: hasWin
 * Description: Verifies if a player won
 */
void hasWin(){
    if(donkeyKongJr->posX >= WINNER_POSITION_X_0 && donkeyKongJr->posX <= WINNER_POSITION_X_1 && donkeyKongJr->posY >= WINNER_POSITION_Y_0 && donkeyKongJr->posY <= WINNER_POSITION_Y_1){
        if(!isObserver){
            serializeWin(gameId);
        }
        donkeyKongJr->currentSprite = donkeyKongJr->win;
        renderDonkeyKongJr();
        //SDL_Delay(DELAY_ANIMATION);
        setDefaultValues();

        Enemy *temp = enemyList;
        while(temp != NULL){
            temp->speed++;
            temp = temp->next;
        }
    }


}


/**
 *
 */
void gameOver(int score_, int lives_){
    donkeyKongJr->score = score_;
    donkeyKongJr->lives = lives_;
    setDefaultValues();

}


/**
 * Name: loadTexture
 * Description: loads textures from a given path
 * @param path texture path
 * @return texture
 */
SDL_Texture * loadTexture(const char* path){

    SDL_Texture *texture = NULL;
    SDL_Surface *surface = IMG_Load(path);

    if(!surface){
        printf("Could not load image %s\n", IMG_GetError());
    }
    texture = SDL_CreateTextureFromSurface(renderer, surface);
    SDL_FreeSurface(surface);

    return texture;
}



/**
 * Name: setFruitTexture
 * Description:
 * @param fruit fruits linked list
 * @param type fruit type
 */
void setFruitTexture(Fruit* fruit, char* type){
    if(strcmp(type, "apple") == 0 ){
        fruit->sprite = loadTexture(APPLE_IMAGE_PATH);
    }else if(strcmp(type, "banana") == 0){
        fruit->sprite =  loadTexture(BANANA_IMAGE_PATH);
    }else if(strcmp(type, "mango") == 0){
        fruit->sprite =  loadTexture(MANGO_IMAGE_PATH);
    }
}


/**
 * Name: putFruit
 * Description: creates a new fruit and add it to fruits linked list
 * @param liana liana numbers
 * @param type fruit type
 */
void putFruit(int liana, char* type){

    Fruit* fruit = (Fruit*) malloc(sizeof(Fruit));
    setFruitTexture(fruit, type);
    fruit->liana = liana;
    fruit->type = type;
    fruit->posX = setPositionX(liana) + DIFF_X_FRUIT;
    fruit->posY = setPositionY(liana) + DIFF_FRUIT_Y;


    fruitList = addFruit(fruitList, fruit);

}



/**
 * Name: setEnemyTextures
 * Description:
 * @param enemy enemy linked list
 * @param type enemy list
 */
void setEnemyTextures(Enemy* enemy, char* type){

    if(strcmp(type, "blue") == 0){
        enemy->down0 = loadTexture(ENEMY_BLUE_DOWN0_PATH);
        enemy->down1 = loadTexture(ENEMY_BLUE_DOWN1_PATH);
        enemy->up0 = loadTexture(ENEMY_BLUE_UP0_PATH);
        enemy->up1 = loadTexture(ENEMY_BLUE_UP1_PATH);

    }else if(strcmp(type, "red") == 0){
        enemy->down0 = loadTexture(ENEMY_RED_DOWN0_PATH);
        enemy->down1 = loadTexture(ENEMY_RED_DOWN1_PATH);
        enemy->up0 = loadTexture(ENEMY_RED_UP0_PATH);
        enemy->up1 = loadTexture(ENEMY_RED_UP1_PATH);
    }
}



/**
 * Name: putEnemy
 * Description: creates a new enemy and add it to enemy linked list
 * @param liana
 * @param type
 * @param speed
 */
void putEnemy(int liana, char* type, int speed){

    Enemy *enemy = (Enemy*) malloc(sizeof(Enemy));
    setEnemyTextures(enemy, type);
    enemy->liana = liana;
    enemy->type = type;
    enemy->speed = speed;
    enemy->posX = setPositionX(liana) + DIFF_X_ENEMIES;
    enemy->posY = setPositionY(liana) + DIFF_ENEMY_Y;
    enemy->currentSprite = enemy->down0;
    enemy->goingDown = 1;
    enemy->num_sprite = 0;
    enemyList = addEnemy(enemyList, enemy);

}


/**
 * Name: setPositionX
 * Description: returns position in X axis according to liana number given in parameters
 * @param liana liana number
 * @return position x axis
 */
int setPositionX(int liana){
    if(liana == LIANA1){
        return (LIANA1_X_1 + LIANA1_X_0)/2;
    }else if(liana == LIANA2){
        return  (LIANA2_X_1 + LIANA2_X_0)/2;
    }else if(liana == LIANA3){
        return  (LIANA3_X_1 + LIANA3_X_0)/2;
    }else if(liana == LIANA4){
        return (LIANA4_X_1 + LIANA4_X_0)/2;
    }else if(liana == LIANA5){
        return  (LIANA5_X_1 + LIANA5_X_0)/2;
    }else if(liana == LIANA6){
        return  (LIANA6_X_1 + LIANA6_X_0)/2;
    }else if(liana == LIANA7){
        return  (LIANA7_X_1 + LIANA7_X_0)/2;
    }else if(liana == LIANA8){
        return  (LIANA8_X_1 + LIANA8_X_0)/2;
    }else if(liana == LIANA9){
        return  (LIANA9_X_1 + LIANA9_X_0)/2;
    }else if(liana == LIANA10){
        return  (LIANA10_X_1 + LIANA10_X_0)/2;
    }

}


/**
 * Name: setPositionY
 * Description: returns position in Y axis according to liana number given in parameters
 * @param liana liana number
 * @return position Y axis
 */
int setPositionY(int liana){
    if(liana == LIANA1){
        return LIANA1_Y_0;
    }else if(liana == LIANA2){
        return  LIANA2_Y_0;
    }else if(liana == LIANA3){
        return LIANA3_Y_0 ;
    }else if(liana == LIANA4){
        return  LIANA4_Y_0;
    }else if(liana == LIANA5){
        return LIANA5_Y_0;
    }else if(liana == LIANA6){
        return  LIANA6_Y_0;
    }else if(liana == LIANA7){
        return  LIANA7_Y_0;
    }else if(liana == LIANA8){
        return  LIANA8_Y_0;
    }else if(liana == LIANA9){
        return  LIANA9_Y_0;
    }else if(liana == LIANA10){
        return  LIANA10_Y_0;
    }

}



/**
 * Name: initialize Donkey Kong Jr information
 * @return DKJ struct
 */
DKJ* initDonkeyKongJr(){
    DKJ* player = (DKJ*) malloc(sizeof(DKJ));
    player->posX = X_INITIAL;
    player->posY = Y_INITIAL;
    player->facing = FACING_RIGHT; //Comienza viendo por la derecha
    player->jumping = 0;
    player->falling = 0;
    player->onLiana = 0;
    player->lives = 3;
    player->score = 0;
    player->prevPositionX = X_INITIAL;
    player->prevPositionY = Y_INITIAL;
    player->currentSpriteNum = 1;


    player->left1 = loadTexture(DKJ_WALK_LEFT1_PATH);
    player->left2 = loadTexture(DKJ_WALK_LEFT2_PATH);
    player->left3 = loadTexture(DKJ_WALK_LEFT3_PATH);
    player->right1 = loadTexture(DKJ_WALK_RIGHT1_PATH);
    player->right2 = loadTexture(DKJ_WALK_RIGHT2_PATH);
    player->right3 = loadTexture(DKJ_WALK_RIGHT3_PATH);
    player->win = loadTexture(DKJ_WIN_PATH);
    player->dead = loadTexture(DKJ_DEAD_PATH);
    player->currentSprite = player->right1;



    return player;
}




