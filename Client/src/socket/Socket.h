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

char* listen_socket(char *ip, int port);
char* write_socket(char *ip, int port, char *msj);

#endif //PROJECT_SOCKET_H
