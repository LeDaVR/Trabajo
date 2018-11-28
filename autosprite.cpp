#include "autoSprite.h"

AutoSprite::AutoSprite::AutoSprite(){
	imagen="";
	posicion.x=0;
    posicion.y=0;
    dimensiones.x =0;
    dimensiones.y =0;
}
AutoSprite::AutoSprite(std::string archivo){
    textura.loadFromFile(archivo);
    sprite.setTexture(textura);
	imagen=archivo;
    posicion.x=0;
    posicion.y=0;
    dimensiones.x = textura.getSize().x;
    dimensiones.y = textura.getSize().y;
}

void AutoSprite::ajustarPosicion(float x,float y){
    posicion.x = x;
    posicion.y = y;
    sprite.setPosition(posicion);
}

void AutoSprite::escalar(float x,float y){
	sprite.setScale(x/textura.getSize().x,y/textura.getSize().y);
    dimensiones.x =x;
    dimensiones.y =y;
   
}

void AutoSprite::cambiarColor(int r,int g,int b,int a){
    color.r=r;
    color.g=g;
    color.b=b;
    color.a=a;
    sprite.setColor(color);
}

sf::Sprite AutoSprite::getSprite(){
    return sprite;
}

sf::Texture AutoSprite::getTexture(){
    return textura;
}

void AutoSprite::setImagen(std::string img){
	textura.loadFromFile(img);
    sprite.setTexture(textura);
	imagen=img;
}

std::string AutoSprite::getImagen(){
	return imagen;
}

void AutoSprite::pseudoDimensiones(float x,float y){
	dimensiones.x=x;
	dimensiones.y=y;
}

float AutoSprite::getPosicion(char a){
	if(a=='X')
    	return posicion.x;
	if(a=='Y')
    	return posicion.y;
    
}


float AutoSprite::getTamanio(char a){
    if(a=='X')
		return dimensiones.x;
	if(a=='Y')
		return dimensiones.y;
}



