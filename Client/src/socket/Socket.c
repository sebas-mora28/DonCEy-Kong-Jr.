#include "Socket.h"

char* listen_socket(char *ip, int port){
    int sock;
    struct sockaddr_in server;
    char server_reply[2000];

    // create socket
    sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1){
        printf("Could not create socket");
    }
    printf("Socket created\n");

    server.sin_addr.s_addr = inet_addr(ip);
    server.sin_family = AF_INET;
    server.sin_port = htons(port);

    // connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0){
        perror("connect failed. Error");
        return 1;
    }
    printf("Connected\n");

    // keep listening
    int working = 1;
    while(working){
        // receive a reply from the server
        if(recv(sock , server_reply , 2000 , 0) < 0){
            printf("recv failed");
            break;
        }
    }

    char *msj = server_reply;

    close(sock);
    return msj;
}

char* write_socket(char *ip, int port, char *msj){
    int sock;
    struct sockaddr_in server;
    char server_reply[2000];

    // create socket
    sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1){
        printf("Could not create socket");
    }
    printf("Socket created\n");

    server.sin_addr.s_addr = inet_addr(ip);
    server.sin_family = AF_INET;
    server.sin_port = htons(port);

    // connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0){
        perror("connect failed. Error");
        return 1;
    }
    printf("Connected\n");

    // keep communicating with server
    int working = 1;
    while(working){
        // send some data
        if(send(sock , msj , strlen(msj) , 0) < 0){
            printf("Send failed");
            return 1;
        }
        printf("Data send\n");

        // receive a reply from the server
        if(recv(sock , server_reply , 2000 , 0) < 0){
            printf("recv failed");
            break;
        }
        //printf("Server reply: ");
        //puts(server_reply);
        working = 0;
    }

    close(sock);
    msj = server_reply;
    return msj;
}
