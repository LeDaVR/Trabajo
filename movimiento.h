#ifndef MOVIMIENTO_H
#define MOVIMIENTO_H
#include "autoSprite.h"
#include<SFML/Audio.hpp>


class SpriteArray{
	private:

		int aux,velocidadMov;
		SoundBuffer buffer;
    	Sound sonido;
		Music cancion;

		int size;
		void redimensionar(const int);
		
	public:
		//constructores
		SpriteArray();
		SpriteArray(SpriteArray&);
		~SpriteArray();
	
		AutoSprite *escena;
		void addSprite(const AutoSprite);
		void posicionarSprite(const AutoSprite,const int);
		void removeSprite(const int);
		void mostrar(sf::RenderWindow &);
		void mover(int,int);
		void moverentidad(int,int);
		void setview(RenderWindow&,int);
		bool upcolision(AutoSprite,int);
		bool downcolision(AutoSprite,int);
		bool leftcolision(AutoSprite,int);
		bool rightcolision(AutoSprite,int);

		int getSize() const ;
};

#endif
