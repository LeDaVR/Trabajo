#ifndef MOVIMIENTO_H
#define MOVIMIENTO_H
#include "autoSprite.h"
bool upcolision(AutoSprite *,AutoSprite **,int,int);
bool downcolision(AutoSprite *,AutoSprite **,int,int);
bool leftcolision(AutoSprite *,AutoSprite **,int,int);
bool rightcolision(AutoSprite *,AutoSprite **,int,int);
void mover(AutoSprite *,AutoSprite **,int,int,int);
void moverentidad(AutoSprite *,AutoSprite **,int,int,int);
void mostrar(sf::RenderWindow &,AutoSprite **,int);
#endif
