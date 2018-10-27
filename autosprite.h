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
#endif
