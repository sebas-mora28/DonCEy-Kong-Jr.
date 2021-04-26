//
// Created by emarin20 on 4/20/21.
//

#ifndef PROJECT_SOCKET_H
#define PROJECT_SOCKET_H

#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include "../constants.h"
#include <stdlib.h>
#include <pthread.h>
#include "../Game/game.h"
#include "cJson.h"


void initClient();

void connectionRefused();
_Noreturn void *listen_socket();
void write_socket(char* msj);
int createSocket();
void writeServer(char* msj);

#endif //PROJECT_SOCKET_H
