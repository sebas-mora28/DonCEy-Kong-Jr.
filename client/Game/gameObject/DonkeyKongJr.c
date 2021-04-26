//
// Created by sebasmora on 24/4/21.

#include "DonkeyKongJr.h"


/**\
 * Name: updateDKJPosition
 * Description: updates DKJ position with the values given in parameter
 * @param posX_ x axis position
 * @param posY_ y axis position
 * @param facing_ player direction
 * @param jumping_ jumping flag
 * @param falling_ falling flag
 * @param onLiana_ onLiana flag
 */
void updateDKJPosition(int posX_, int posY_, int facing_, int jumping_, int falling_, int onLiana_){
    donkeyKongJr->posX = posX_;
    donkeyKongJr->posY = posY_;
    donkeyKongJr->facing = facing_;
    donkeyKongJr->jumping = jumping_;
    donkeyKongJr->falling = falling_;
    donkeyKongJr->onLiana = onLiana_;

    if(donkeyKongJr->jumping){
        if(donkeyKongJr->facing == RIGHT){
            donkeyKongJr->currentSprite = donkeyKongJr->right2;
        }
        if(donkeyKongJr->facing == LEFT){
            donkeyKongJr->currentSprite = donkeyKongJr->left2;
        }
        return;

    }

    DonkeyKongJrAnimation();

}


void setDefaultValues(){
    donkeyKongJr->posX = X_INITIAL;
    donkeyKongJr->posY = Y_INITIAL;
    donkeyKongJr->facing = RIGHT;
    donkeyKongJr->jumping = NOT_JUMPING;
    donkeyKongJr->falling = NO_FALLING;
    donkeyKongJr->onLiana = NO_IN_LIANA;
    donkeyKongJr->currentSprite = donkeyKongJr->right1;
}

void moveDKJ(int dir_x, int dir_y) {

    if(is_on_platform() || donkeyKongJr->falling || donkeyKongJr->jumping || donkeyKongJr->onLiana){
        if (dir_x == RIGHT && dir_y == NEUTRAL) {
            moveRight();
            return;
        }
        if (dir_x == LEFT && dir_y == NEUTRAL){
            moveLeft();
            return;
        }
        if(dir_x == NEUTRAL && dir_y == UP){
            moveUp();
            return;
        }
        if(dir_x == NEUTRAL && dir_y == DOWN){
            moveDown();
            return;
        }
    }
    donkeyKongJr->falling = 1;
}


/**
 * Name: onLiana
 * Description: Verifies whether a player is on a liana or not
 * @return 1 if player is in a liana or 0 otherwise
 */
int onLiana(){

    if(donkeyKongJr->posX >= LIANA1_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA1_X_1 +  LIANA_RANGE && donkeyKongJr->posY >= LIANA1_Y_0  && donkeyKongJr->posY <= LIANA1_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA1_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA1_X_1  - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA2_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA2_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA2_Y_0  && donkeyKongJr->posY <= LIANA2_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA2_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA2_X_1  - LIANA_RANGE;
        };
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA3_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA3_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA3_Y_0  && donkeyKongJr->posY <= LIANA3_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA3_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA3_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA4_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA4_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA4_Y_0  && donkeyKongJr->posY <= LIANA4_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA4_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA4_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA5_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA5_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA5_Y_0  && donkeyKongJr->posY <= LIANA5_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA5_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA5_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA6_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA6_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA6_Y_0  && donkeyKongJr->posY <= LIANA6_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA6_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA6_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA7_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA7_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA7_Y_0  && donkeyKongJr->posY <= LIANA7_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA7_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA7_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA8_X_0 -LIANA_RANGE && donkeyKongJr->posX <= LIANA8_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA8_Y_0  && donkeyKongJr->posY <= LIANA8_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA8_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA8_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA9_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA9_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA9_Y_0  && donkeyKongJr->posY <= LIANA9_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA9_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA9_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }
    if(donkeyKongJr->posX >= LIANA10_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA10_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA10_Y_0  && donkeyKongJr->posY <= LIANA10_Y_1){
        if(donkeyKongJr->facing == -1){
            donkeyKongJr->posX = LIANA10_X_0  + LIANA_RANGE;
        }else if(donkeyKongJr->facing == 1){
            donkeyKongJr->posX = LIANA10_X_1 - LIANA_RANGE;
        }
        donkeyKongJr->onLiana = 1;
        donkeyKongJr->falling = 0;
        return 1;
    }


    return 0;


}

/**
 * Name: moveUp
 * Description: Moves DJK up until DKJ reaches the top of the liana
 */
void moveUp(){
    if(donkeyKongJr->posX >= LIANA1_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA1_X_1 +  LIANA_RANGE && donkeyKongJr->posY >= LIANA1_Y_0  && donkeyKongJr->posY <= LIANA1_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA2_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA2_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA2_Y_0  && donkeyKongJr->posY <= LIANA2_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA3_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA3_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA3_Y_0  && donkeyKongJr->posY <= LIANA3_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA4_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA4_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA4_Y_0  && donkeyKongJr->posY <= LIANA4_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA5_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA5_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA5_Y_0  && donkeyKongJr->posY <= LIANA5_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA6_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA6_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA6_Y_0  && donkeyKongJr->posY <= LIANA6_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA7_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA7_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA7_Y_0  && donkeyKongJr->posY <= LIANA7_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA8_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA8_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA8_Y_0  && donkeyKongJr->posY <= LIANA8_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA9_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA9_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA9_Y_0  && donkeyKongJr->posY <= LIANA9_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA10_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA10_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA10_Y_0  && donkeyKongJr->posY <= LIANA10_Y_1){
        donkeyKongJr->posY -= 3;
        DonkeyKongJrAnimation();
    }
}


/**
 * Name: moveDown
 * Description: Moves DJK down until DKJ he reaches the end of the liana, after player start falling
 */
void moveDown(){
    if(donkeyKongJr->posX >= LIANA1_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA1_X_1 +  LIANA_RANGE && donkeyKongJr->posY >= LIANA1_Y_0  && donkeyKongJr->posY <= LIANA1_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA2_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA2_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA2_Y_0  && donkeyKongJr->posY <= LIANA2_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA3_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA3_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA3_Y_0  && donkeyKongJr->posY <= LIANA3_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA4_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA4_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA4_Y_0  && donkeyKongJr->posY <= LIANA4_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA5_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA5_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA5_Y_0  && donkeyKongJr->posY <= LIANA5_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA6_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA6_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA6_Y_0  && donkeyKongJr->posY <= LIANA6_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA7_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA7_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA7_Y_0  && donkeyKongJr->posY <= LIANA7_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA8_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA8_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA8_Y_0  && donkeyKongJr->posY <= LIANA8_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA9_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA9_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA9_Y_0  && donkeyKongJr->posY <= LIANA9_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else if(donkeyKongJr->posX >= LIANA10_X_0 - LIANA_RANGE && donkeyKongJr->posX <= LIANA10_X_1 + LIANA_RANGE && donkeyKongJr->posY >= LIANA10_Y_0  && donkeyKongJr->posY <= LIANA10_Y_1){
        donkeyKongJr->posY += 3;
        DonkeyKongJrAnimation();
    }else {
        donkeyKongJr->falling = 1;
        donkeyKongJr->onLiana = 0;

    }

}

/**
 * Name: moveRight
 * Description: handles DKJ right move
 */
void moveRight(){

    if(donkeyKongJr->onLiana){
        if(donkeyKongJr->facing == RIGHT){
            donkeyKongJr->currentSprite = donkeyKongJr->left1;
            donkeyKongJr->posX += 35;
            donkeyKongJr->facing = -1;;
        }
        if(is_on_platform()){
            donkeyKongJr->onLiana = 0;
        }
        return;
    }

    if(donkeyKongJr->falling){
        donkeyKongJr->posY += 5;
    }
    if(donkeyKongJr->jumping){
        donkeyKongJr->posY -= 5;
    }

    donkeyKongJr->facing = 1;
    donkeyKongJr->posX += 5;
    DonkeyKongJrAnimation();

}


/**
 * Name: moveLeft
 * Description: handles DKJ left move
 */
void moveLeft(){

    if(donkeyKongJr->onLiana){
        if(donkeyKongJr->facing == LEFT){
            donkeyKongJr->currentSprite = donkeyKongJr->right1;
            donkeyKongJr->posX -= 35;
            donkeyKongJr->facing = 1;
        }
        if(is_on_platform()){
            donkeyKongJr->onLiana = 0;
        }
        return;
    }

    if(donkeyKongJr->falling){
        donkeyKongJr->posY += 5;
    }
    if(donkeyKongJr->jumping){
        donkeyKongJr->posY -= 5;
    }
    donkeyKongJr->facing = -1;
    donkeyKongJr->posX -= 5;
    DonkeyKongJrAnimation();

}



/**
 * Name: DonkeyKongJrAnimation
 * Description: Handles Donkey Kong Jr animations
 */
void DonkeyKongJrAnimation(){
    if(donkeyKongJr->facing == RIGHT){
        if (donkeyKongJr->currentSpriteNum == 1) {
            donkeyKongJr->currentSprite = donkeyKongJr->right1;
            donkeyKongJr->currentSpriteNum = 2;
        }
        else if (donkeyKongJr->currentSpriteNum == 2) {
            donkeyKongJr->currentSprite = donkeyKongJr->right2;
            donkeyKongJr->currentSpriteNum = 3;
        }
        else if(donkeyKongJr->currentSpriteNum == 3){
            donkeyKongJr->currentSprite = donkeyKongJr->right3;
            donkeyKongJr->currentSpriteNum = 1;
        }

    }

    if(donkeyKongJr->facing == LEFT){
        if (donkeyKongJr->currentSpriteNum == 1) {
            donkeyKongJr->currentSprite = donkeyKongJr->left1;
            donkeyKongJr->currentSpriteNum++;
        }
        else if (donkeyKongJr->currentSpriteNum == 2) {
            donkeyKongJr->currentSprite = donkeyKongJr->left2;
            donkeyKongJr->currentSpriteNum++;
        }
        else if(donkeyKongJr->currentSpriteNum == 3){
            donkeyKongJr->currentSprite = donkeyKongJr->left3;
            donkeyKongJr->currentSpriteNum = 1;
        }
    }


}



/**
 * Name: intiJump
 * Description: starts player jumping move
 */
void initJump(){
    if(!donkeyKongJr->jumping && !donkeyKongJr->falling) {
        donkeyKongJr->falling = 0;
        donkeyKongJr->positionStartJumping = donkeyKongJr->posY;
        donkeyKongJr->jumping = 1;
    }
    if(donkeyKongJr->onLiana){
        donkeyKongJr->jumping = 1;
        if(donkeyKongJr->facing == RIGHT){
            donkeyKongJr->posX -= LIANA_RANGE + 1;
            donkeyKongJr->onLiana = 0;
            donkeyKongJr->facing = -1;
            donkeyKongJr->currentSprite = donkeyKongJr->left2;
        }else if(donkeyKongJr->facing == LEFT){
            if(donkeyKongJr->onLiana){
                donkeyKongJr->posX += LIANA_RANGE + 1;
                donkeyKongJr->onLiana = 0;
                donkeyKongJr->facing = 1;
                donkeyKongJr->currentSprite = donkeyKongJr->right2;
            }
        }
    }

}


/**
 * Name: falling
 * Description: starts player falling move
 */
void falling(){

    if(!onLiana()){
        donkeyKongJr->posY += 3;
        if(donkeyKongJr->facing == RIGHT){
            donkeyKongJr->posX += 3;
            donkeyKongJr->currentSprite = donkeyKongJr->right2;
        }
        if(donkeyKongJr->facing == LEFT){
            donkeyKongJr->posX -= 3;
            donkeyKongJr->currentSprite = donkeyKongJr->left2;
        }
        if (is_on_platform()) {
            donkeyKongJr->falling = 0;
            donkeyKongJr->onLiana = 0;
        }
        if(donkeyKongJr->posY > HEIGHT){
            setDefaultValues();
        }

    }else {
        donkeyKongJr->falling =0;
        donkeyKongJr->onLiana =1;
        if(donkeyKongJr->facing == RIGHT){
            donkeyKongJr->currentSprite = donkeyKongJr->right1;
        }
        if(donkeyKongJr->facing == LEFT){
            donkeyKongJr->currentSprite = donkeyKongJr->left1;
        }

    }


}

/**
 * Name: jumping
 * Description: handles jumping move
 */
void jumping(){

    int diff = donkeyKongJr->positionStartJumping - donkeyKongJr->posY;
    if(diff > 60){
        donkeyKongJr->jumping = 0;
        donkeyKongJr->falling = 1;
    }else {
        if(donkeyKongJr->facing == 1){
                donkeyKongJr->posX += 1;

        }else if(donkeyKongJr->facing == -1){
                donkeyKongJr->posX -= 1;
            }
        donkeyKongJr->posY -= 4;
    }


}




/**
 * Name: is_on_platform
 * Description: Verifies whether a player is on platform or not
 * @return 1 if the player is on a platform or 0 otherwise
 */
int is_on_platform(){
    int currentPosX = donkeyKongJr->posX;
    int currentPosY = donkeyKongJr->posY;


    if(currentPosX >= Platform1_X_0  && currentPosX <= Platform1_X_1 && currentPosY >= Platform1_Height_0 && currentPosY <= Platform1_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform2_X_0  && currentPosX <= Platform2_X_1 && currentPosY >= Platform2_Height_0 && currentPosY <= Platform2_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform3_X_0  && currentPosX <= Platform3_X_1 && currentPosY >= Platform3_Height_0 && currentPosY <= Platform3_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform4_X_0  && currentPosX <= Platform4_X_1 && currentPosY >= Platform4_Height_0 && currentPosY <= Platform4_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform5_X_0  && currentPosX <= Platform5_X_1 && currentPosY >= Platform5_Height_0 && currentPosY <= Platform5_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform6_X_0  && currentPosX <= Platform6_X_1 && currentPosY >= Platform6_Height_0 && currentPosY <= Platform6_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform7_X_0  && currentPosX <= Platform7_X_1 && currentPosY >= Platform7_Height_0 && currentPosY <= Platform7_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform8_X_0  && currentPosX <= Platform8_X_1 && currentPosY >= Platform8_Height_0 && currentPosY <= Platform8_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform9_X_0  && currentPosX <= Platform9_X_1 && currentPosY >= Platform9_Height_0 && currentPosY <= Platform9_Height_1){
        return 1;
    }
    else if(currentPosX >= Platform10_X_0  && currentPosX <= Platform10_X_1 && currentPosY >= Platform10_Height_0 && currentPosY <= Platform10_Height_1){
        return 1;
    }

    return 0;

}


