//
// Created by sebasmora on 23/4/21.
//

#include "fruit.h"




/**
 * Name: deleteFruit
 * Description: deletes a given fruit from en fruit linked list
 * @param liana number liana
 * @param type fruit type
 */
void deleteFruit(int liana, char* type){

    Fruit* current = fruitList;

    while(current != NULL){

        if(current->liana == liana && current->sprite != NULL){
            current->sprite = NULL;
            break;
        }
        current = current->next;

    }

}


/**
 * Name: initListFruits
 * Description: initialize fruits list
 * @param list node fruits linked list
 * @return fruits list
 */
Fruit* initListFruits(Fruit* list){
    list = NULL;
    return list;
}


/**
 * Name: addFruit
 * Description: adds a new node to fruits linked list
 * @param list fruit list
 * @param enemy new node
 * @return enemy linked list
 */
Fruit* addFruit(Fruit* list, Fruit* fruit){

    fruit->next = NULL;

    if(list == NULL) {
        list = fruit;
        return list;
    }
    else {
        Fruit* temp = list;
        while(temp->next != NULL){
            temp = temp->next;
        }
        temp->next = fruit;
        return list;
    }

}