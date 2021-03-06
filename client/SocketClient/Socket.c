#include "Socket.h"



int client;
struct sockaddr_in server;
char server_reply[2000];
pthread_t pthread_read;
pthread_t pthread_send;


/**
 * Nombre: initClient
 * Descripcion: Inicializa el socket del cliente, ademas del thread para la funcion que recibe la informacion
 *              del servidor
 */
void initClient(){
    client = createSocket();
    pthread_create(&pthread_send, NULL, &listen_socket, NULL);



}
/**
 * Nombre: createSocket
 * Descripcion: creates socket client
 * @return socket client
 */
int createSocket(){
    int sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1){
        printf("Could not create socket");
    }


    server.sin_addr.s_addr = inet_addr(IP);
    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);

    // connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) != 0){
        perror("connect failed. Error");
        exit(1);
    }
    return sock;

}


/**
 * Nombre: listen_socket
 * Descripcion: listens for server reply and parse it to identify which command was received
 * @return void
 */
_Noreturn void *listen_socket(){
    while(1){

        if (read(client, server_reply, 2000) > 0) {
            //printf("Server %s \n", server_reply);
            cJSON *serverReplyCommand = cJSON_Parse(server_reply);
            char* command = cJSON_GetObjectItem(serverReplyCommand, "command")->valuestring;


            if(strcmp(command,"putFruit") == 0){
                int liana = cJSON_GetObjectItem(serverReplyCommand, "liana")->valueint;
                char* type = cJSON_GetObjectItem(serverReplyCommand, "type")->valuestring;
                putFruit(liana, type);
            }
            if(strcmp(command,"lives") == 0){
                int newLives = cJSON_GetObjectItem(serverReplyCommand, "lives")->valueint;
                printf("vidas %d", newLives);
                updateLives(newLives);
            }
            if(strcmp(command,"score") == 0){
                int newScore = cJSON_GetObjectItem(serverReplyCommand, "score")->valueint;
                updateScore(newScore);
            }
            if(strcmp(command,"putEnemy") == 0){
                int liana = cJSON_GetObjectItem(serverReplyCommand, "liana")->valueint;
                char* type = cJSON_GetObjectItem(serverReplyCommand, "type")->valuestring;
                int speed =  cJSON_GetObjectItem(serverReplyCommand, "speed")->valueint;
                putEnemy(liana, type, speed);
            }

            if(strcmp(command,"deleteFruit") == 0){
                int liana = cJSON_GetObjectItem(serverReplyCommand, "liana")->valueint;
                char* type = cJSON_GetObjectItem(serverReplyCommand, "type")->valuestring;
                deleteFruit(liana, type);
            }

            if(strcmp(command,"connectionRefused") == 0){
                connectionRefused();
            }

            if(strcmp(command,"moveDKJ") == 0){
                int posX = cJSON_GetObjectItem(serverReplyCommand, "posX")->valueint;
                int posY = cJSON_GetObjectItem(serverReplyCommand, "posY")->valueint;
                int facing = cJSON_GetObjectItem(serverReplyCommand, "facing")->valueint;
                int jumping = cJSON_GetObjectItem(serverReplyCommand, "jumping")->valueint;
                int falling = cJSON_GetObjectItem(serverReplyCommand, "falling")->valueint;
                int onLiana = cJSON_GetObjectItem(serverReplyCommand, "onLiana")->valueint;
                updateDKJPosition(posX, posY, facing, jumping, falling, onLiana);
            }




        };
        //printf("----------------------------------------------------------- \n\n");
    }
 }


 /**
  * Name: write_socket
  * Description: sends information to server
  * @param msj message
  */
void write_socket(char *msj) {

    char sendBuff[2500];
    bzero(sendBuff, sizeof(sendBuff));
    strcpy(sendBuff, msj);
    //printf("Envia %s \n", sendBuff);
    write(client, msj, strlen(msj));
    char *line_delimeter = "\n";
    write(client, line_delimeter, strlen(line_delimeter));
}




