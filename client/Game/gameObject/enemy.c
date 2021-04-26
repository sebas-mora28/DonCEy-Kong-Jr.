//
// Created by sebasmora on 23/4/21.
//

#include "enemy.h"




/**
 * Name: initListEnemies
 * Description: initialize enemies list
 * @param list node enemy linked list
 * @return enemy list
 */
Enemy* initListEnemies(Enemy* list) {
    list = NULL;
    return list;
}


/**
 * Name: addEnemy
 * Description: adds a new node to enemy linked list
 * @param list enemy list
 * @param enemy new node
 * @return enemy linked list
 */
Enemy* addEnemy(Enemy* list, Enemy* enemy) {

    enemy->next = NULL;

    if (list == NULL) {
        list = enemy;
        return list;

    } else {
        Enemy *temp = list;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = enemy;
        return list;
    }
}


/**
 * Name: moveEnemy
 * Description: Handles enemy movement according to its color
 * @param enemyList enemy list
 */
void moveEnemy(Enemy* enemyList) {
    Enemy *current = enemyList;
    while (current != NULL) {

        if (strcmp(current->type, "blue") == 0) {
            moveEnemyBlue(current);

        } else if (strcmp(current->type, "red") == 0) {
            moveEnemyRed(current);

        }

        current = current->next;
    }

}


/**
 * Name: down
 * Description: Moves the enemy down, besides handles enemy animation
 * @param enemy enemy struct
 */
void down(Enemy* enemy){
    enemy->posY +=  enemy->speed;
    if(enemy->num_sprite == 0){
        enemy->currentSprite = enemy->down1;
        enemy->num_sprite = 1;
    }
    else if(enemy->num_sprite == 1){
        enemy->currentSprite = enemy->down0;
        enemy->num_sprite = 0;

    }

}


/**
 * Name: up
 * Description: Moves the enemy up, besides handles enemy animation
 * @param enemy enemy struct
 */
void up(Enemy* enemy){
    enemy->posY -=  enemy->speed;
    if(enemy->num_sprite == 0){
        enemy->currentSprite = enemy->up1;
        enemy->num_sprite = 1;
    }
    else if(enemy->num_sprite == 1){
        enemy->currentSprite = enemy->up0;
        enemy->num_sprite = 0;

    }

}


/**
 * Name: moveEnemyRed
 * Description: handles red enemy movement. When the enemy reaches liana's end  heads back up
 * @param enemy enemy struct
 */
void moveEnemyRed(Enemy* enemy) {
    int liana = enemy->liana;
    if (liana == LIANA1) {
        if (enemy->posY < LIANA1_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA1_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA2) {
        if (enemy->posY < LIANA2_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA2_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA3) {
        if (enemy->posY < LIANA3_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA3_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA4) {
        if (enemy->posY < LIANA4_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA4_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA5) {
        if (enemy->posY < LIANA5_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA5_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA6) {
        if (enemy->posY < LIANA6_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA6_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA7) {
        if (enemy->posY < LIANA7_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA7_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA8) {
        if (enemy->posY < LIANA8_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA8_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA9) {
        if (enemy->posY < LIANA9_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA9_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
    if (liana == LIANA10) {
        if (enemy->posY < LIANA10_Y_1 && enemy->goingDown ) { down(enemy); }
        else if(enemy->posY < LIANA10_Y_0){ enemy->goingDown = 1; down(enemy);}
        else { enemy->goingDown = 0; up(enemy);}
    }
}


/**
 * Name: moveEnemyBlue
 * Description: handles red enemy movement. When the enemy reaches liana's end  falls indefinitely
 * @param enemy enemy struct
 */
void moveEnemyBlue(Enemy* enemy){

    if(enemy->posY < HEIGHT){
        if(enemy->posY > LIANA1_Y_1 && enemy->liana == LIANA1){
            enemy->speed++;
        }
        if(enemy->posY > LIANA2_Y_1 && enemy->liana == LIANA2){
            enemy->speed++;
        }
        if(enemy->posY > LIANA3_Y_1 && enemy->liana == LIANA3){
            enemy->speed++;
        }
        if(enemy->posY > LIANA4_Y_1 && enemy->liana == LIANA4){
            enemy->speed++;
        }
        if(enemy->posY > LIANA5_Y_1 && enemy->liana == LIANA5){
            enemy->speed++;
        }
        if(enemy->posY > LIANA6_Y_1 && enemy->liana == LIANA6){
            enemy->speed++;
        }
        if(enemy->posY > LIANA7_Y_1 && enemy->liana == LIANA7){
            enemy->speed++;
        }
        if(enemy->posY > LIANA8_Y_1 && enemy->liana == LIANA8){
            enemy->speed++;
        }
        if(enemy->posY > LIANA9_Y_1 && enemy->liana == LIANA9){
            enemy->speed++;
        }
        if(enemy->posY > LIANA10_Y_1 && enemy->liana == LIANA10){
            enemy->speed++;
        }
        down(enemy);
    }else {
        enemy->speed = 0;
    }



}







