//
// Created by sebasmora on 25/4/21.
//

#ifndef CLIENT_MENUI_H
#define CLIENT_MENUI_H


#include <stdio.h>


int connectionState;
void menu();
void serializeNewGame(int gameId);
void serializeObserver(int gameId);
void connectionRefused();


#endif //CLIENT_MENUI_H
