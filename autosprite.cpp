#include "autosprite.h"
#include <stdlib.h>
using namespace std;
using namespace sf;
AutoSprite::AutoSprite(string _img){
		texture.loadFromFile(_img);
		sprite.setTexture(texture);
		ancho=0;largo=0;
		x=0;y=0;
}
void AutoSprite::objeto(int _x,int _y){
	ancho=texture.getSize().x;
	largo=texture.getSize().y;
	this->x=_x;
	this->y=_y;
	sprite.setPosition(x,y);
}
int AutoSprite::getancho(){
	return ancho;
}
int AutoSprite::getlargo(){
	return largo;
}
void AutoSprite::posajustar(int _x,int _y){
	x=_x;y=_y;
	sprite.setPosition(x,y);
}
void AutoSprite::escalar(float _x,float _y){
	sprite.setScale(_x,_y);
	ancho*=_x;
	largo*=_y;	
}

