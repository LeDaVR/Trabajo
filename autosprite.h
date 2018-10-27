#ifndef	AUTOSPRITE_H
#define AUTOSPRITE_H
#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>
class AutoSprite{
	private:
		int ancho,largo;
	public:
		sf::Texture texture;
		std::string img;
		int x,y;
		sf::Sprite sprite;
		AutoSprite(std::string);
		void objeto(int,int);
		int getancho();
		int getlargo();
		void posajustar(int,int);
		void escalar(float,float);
};
bool downcolision(AutoSprite *,AutoSprite **,int,int);
bool upcolision(AutoSprite *,AutoSprite **,int,int);
bool leftcolision(AutoSprite *,AutoSprite **,int,int);
bool rightcolision(AutoSprite *,AutoSprite **,int,int);
void mover(AutoSprite *,AutoSprite **,int,int,int);
void moverentidad(AutoSprite *,AutoSprite **,int,int,int);
#endif
