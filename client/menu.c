//
// Created by sebasmora on 25/4/21.
//

#include "menu.h"
#include "Game/game.h"
#include <unistd.h>



/**
 *  Nombre: menu
 *  Descripcion: Inicializa el menu del juego
 */
void menu(){

    connectionState = 1;
    printf("Bienvenido a DonCEy Kong Jr \n");
    int userReply;
    printf("Ingrese:   1 : Partida nueva  2 : Observar partida \n");
    scanf("%d", &userReply);


    int gameId;
    if(userReply == 1) {
        printf("Ingrese la partida que desea jugar \n");
        printf(" 1 : Partida 1    2 : Partida 2 \n");
        scanf("%d", &gameId);
        serializeNewGame(gameId);
        sleep(1);
        if(connectionState){
            createWindow(gameId, 0);
        }else{
            printf("Lo siento la partida está ocupada, vuelva a intentarlo \n");
            menu();
        }



    }

    if(userReply == 2){
        printf("Ingrese la partida que desea observar \n ");
        printf(" 1 : Partida 1    2 : Partida 2 \n");
        scanf("%d", &gameId);
        serializeObserver(gameId);
        sleep(1);
        if(connectionState){
            createWindow(gameId, 1);
        }else{
            printf("Lo siento, la cantidad de observadores es máxima, vuelva a intentarlo \n");
            menu();
        }

    }
}


/**
 * Nombre: connectionRefused
 * Descripcion: Actualiza el estado de la conexion;
 */
void connectionRefused(){
    printf("Connection rechazada \n");
    connectionState = 0;
}