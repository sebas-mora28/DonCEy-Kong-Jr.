#include <gtk-3.0/gtk/gtk.h>
#include <string.h>
#include "src/socket/Socket.h"
#include "src/socket/cJson.h"
#include "src/gui/allegro_window.h"

// global variables
GtkWidget *window;
GtkWidget *layout;
GtkWidget *button1;
GtkWidget *button2;

char *serverIp = "127.0.0.1";
int port = 4000;

void connect_player (GtkWidget *widget){
    printf("%s\n", "connecting_player");

    char *msj, *response;

    // create json
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "newGame");
    cJSON_AddStringToObject(root, "gameId", "1");

    // print json
    msj = cJSON_Print(root);
    printf("%s\n", msj);

    // print server response
    response = write_socket(serverIp,port, msj);
    printf("The answer is: \n");
    puts(response);

    // deserialize server json response
    cJSON *deserialize = cJSON_Parse(response);
    if(deserialize == NULL){
        const char *error_ptr = cJSON_GetErrorPtr();
        if (error_ptr != NULL){
            fprintf(stderr, "Error before: %s\n", error_ptr);
        }
    }

    cJSON *command = cJSON_GetObjectItemCaseSensitive(deserialize,"command");
    printf("The command is: ");
    msj = cJSON_GetStringValue(command);
    puts(msj);

    //const char* str = msj;
    if (strcmp ("play", msj) == 0) {
        printf("strings match\n");
        init_game();
    }
}

void connect_observer (GtkWidget *widget){
    /*printf("%s\n", "connecting_player");

    char *msj;
    cJSON *root;
    root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "command", "observe");

    // print json
    msj = cJSON_Print(root);
    printf("%s\n", msj);

    write_socket(serverIp,4000, msj);

    //free(msj);
    cJSON_Delete(root);*/
    gtk_widget_hide(window);
}

int main(int argc, char *argv[]){
    // initialize gtk
    gtk_init(&argc, &argv);

    // create window
    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    gtk_window_set_default_size(GTK_WINDOW(window), 800, 600);
    gtk_window_set_position(GTK_WINDOW(window), GTK_WIN_POS_CENTER);

    // Main container of the window
    layout = gtk_layout_new(NULL, NULL);
    gtk_container_add(GTK_CONTAINER (window), layout);
    gtk_widget_show(layout);

    // play and observe button
    button1 = gtk_button_new_with_label("PLAY");
    button2 = gtk_button_new_with_label("OBSERVE");
    gtk_widget_set_size_request(button1, 80, 35);
    gtk_widget_set_size_request(button2, 80, 35);

    // on_button_clicked_event
    g_signal_connect (button1, "clicked", G_CALLBACK(connect_player), NULL);
    g_signal_connect (button2, "clicked", G_CALLBACK(connect_observer), NULL);

    // window distribution
    gtk_layout_put(GTK_LAYOUT(layout), button1, 200, 450);
    gtk_layout_put(GTK_LAYOUT(layout), button2, 510, 450);

    // show and destroy window
    g_signal_connect_swapped(G_OBJECT(window), "destroy",G_CALLBACK(gtk_main_quit), NULL);
    gtk_widget_show_all(window);
    gtk_main();

    return 0;
}