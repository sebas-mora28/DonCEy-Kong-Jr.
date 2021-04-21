#include "Socket.h"
#include "cJson.h"


int client;
struct sockaddr_in server;
char server_reply[2000];
pthread_t pthread_read;
pthread_t pthread_send;

void initClient(){
    client = createSocket();
    pthread_create(&pthread_send, NULL, &listen_socket, NULL);



}

int createSocket(){
    int sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1){
        printf("Could not create socket");
    }
    printf("Socket created\n");

    server.sin_addr.s_addr = inet_addr(IP);
    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);

    // connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) != 0){
        perror("connect failed. Error");
        exit(1);
    }
    printf("Connected\n");
    return sock;

}

void *listen_socket(){
    while(1){

        if (read(client, server_reply, 2000) > 0) {
            printf("Pasa");
            printf("Server %s \n", server_reply);

        };
    }
 }


void write_socket(char *msj) {

    char sendBuff[2500];
    bzero(sendBuff, sizeof(sendBuff));
    strcpy(sendBuff, msj);
    printf("Envia %s \n", sendBuff);
    write(client, msj, strlen(msj));
    char *line_delimeter = "\n";
    write(client, line_delimeter, strlen(line_delimeter));
}




