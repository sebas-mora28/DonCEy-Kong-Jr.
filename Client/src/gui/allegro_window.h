
#ifndef PROJECT_ALLEGRO_WINDOW_H
#define PROJECT_ALLEGRO_WINDOW_H

#include <stdio.h>
#include <allegro5/allegro.h>

static int WIDTH = 700;
static int HEIGHT = 700;

static ALLEGRO_DISPLAY* display = NULL;
static ALLEGRO_EVENT_QUEUE* event_queue = NULL;
static ALLEGRO_TIMER* timer = NULL;

int init_game();

#endif //PROJECT_ALLEGRO_WINDOW_H
