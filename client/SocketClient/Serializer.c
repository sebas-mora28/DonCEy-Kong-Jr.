#include "Serializer.h"


/**
 * Name: serializeNewGame
 * Description: serializes new game into a JSON object command and send it to server
 * @param id game id
 */
void serializeNewGame(int id){
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "newGame");
    cJSON_AddNumberToObject(root, "gameId", id);
    char* msj = cJSON_PrintUnformatted(root);
    write_socket(msj);
}


/**
 * Name: serializeObserver
 * Description: serializes observer into a JSON object command and send it to server
 * @param id game id
 */
void serializeObserver(int id){
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "observer");
    cJSON_AddNumberToObject(root, "gameId", id);
    char* msj = cJSON_PrintUnformatted(root);
    write_socket(msj);

}


/**
 * Name: serializeMoveDKJ
 * Description: serializes moveDKJ into a JSON object command and send it to server
 * @param posX position in x axis
 * @param posY position in y axis
 * @param facing DKJ direction
 * @param jumping jumping flag
 * @param falling falling flag
 * @param onLiana onLiana flag
 * @param id game id
 */
void serializeMoveDKJ(int posX, int posY, int facing, int jumping, int falling,  int onLiana,  int id){
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "moveDKJ");
    cJSON_AddNumberToObject(root, "posX", posX);
    cJSON_AddNumberToObject(root, "posY", posY);
    cJSON_AddNumberToObject(root, "gameId", id);
    cJSON_AddNumberToObject(root, "facing", facing);
    cJSON_AddNumberToObject(root, "jumping", jumping);
    cJSON_AddNumberToObject(root, "falling", falling);
    cJSON_AddNumberToObject(root, "onLiana", onLiana);
    char* msj = cJSON_PrintUnformatted(root);
    write_socket(msj);

}

/**
 * Name: serializeFruitCaught
 * Description: serializes fruitCaught into a JSON object command and send it to server
 * @param liana
 * @param type
 * @param id
 */
void serializeFruitCaught(int liana, char* type, int id){
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "fruit");
    cJSON_AddNumberToObject(root, "liana", liana);
    cJSON_AddStringToObject(root, "type", type);
    cJSON_AddNumberToObject(root, "gameId", id);
    char* msj = cJSON_PrintUnformatted(root);
    write_socket(msj);
}

/**
 * Name: serializeAttacked
 * Description: serializes attacked into a JSON object command and send it to server
 * @param id game id
 */
void serializeAttacked(int id){
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "attacked");
    cJSON_AddNumberToObject(root, "gameId", id);
    char* msj = cJSON_PrintUnformatted(root);
    write_socket(msj);
}

/**
 * Name: serializeWin
 * Description: serializes win into a JSON object command and send it to server
 * @param id
 */
void serializeWin(int id){
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "win");
    cJSON_AddNumberToObject(root, "gameId", id);
    char* msj = cJSON_PrintUnformatted(root);
    write_socket(msj);
}

void moveEntity(int posX, int posY, int liana, int id){
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "moveEntity");
    cJSON_AddNumberToObject(root, "gameId", id );
    cJSON_AddNumberToObject(root, "liana", liana );
    cJSON_AddNumberToObject(root, "posX", posX );
    cJSON_AddNumberToObject(root, "posY", posY );
    char* msj = cJSON_PrintUnformatted(root);
    write_socket(msj);
}
