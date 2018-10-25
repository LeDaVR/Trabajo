#include <SFML/Audio.hpp>
#include <SFML/Graphics.hpp>
using namespace sf;
using namespace std;
class AutoSprite{
	public:
		Texture texture;
		string img;
		int ancho,largo,x,y;
		Sprite sprite;
		AutoSprite(string _img){
			texture.loadFromFile(_img);
			sprite.setTexture(texture);
		}	
		void posdimensiones(int _ancho,int _largo,int _x,int _y){
			ancho=_ancho;
			largo=_largo;
			x=_x;
			y=_y;
			sprite.setPosition(x,y);
		}
		void posajustar(int _x,int _y){
			x=_x;y=_y;
			sprite.setPosition(x,y);
		}
};
void mostrarset(RenderWindow &a,AutoSprite *arr,int len){
	for(int i=0;i<len;i++){
		a.draw(arr[i].sprite);
	}
}
void movermenu(AutoSprite &autosprite,int cx,int cy,int limitsup,int limitinf){
	int x=autosprite.x;
	int y=autosprite.y;
	if (Keyboard::isKeyPressed(Keyboard::Up)&&y!=limitsup){
		y-=cy;	
		autosprite.posajustar(x,y);		
	}
	if (Keyboard::isKeyPressed(Keyboard::Down)&&y!=limitinf){
		y+=cy;	
		autosprite.posajustar(x,y);
	}
}
int main()
{
    sf::RenderWindow window(sf::VideoMode(800, 600), "SFML window");
  	AutoSprite fondo("img/fondo.jpg");  
	AutoSprite jugar("img/MENUJUGAR.jpg");
	AutoSprite creditos("img/MENUCREDITOS.jpg");
	AutoSprite salir("img/MENUSALIR.jpg");	
	AutoSprite select("img/SELECCIONAR.png");		
	jugar.posajustar(350,100);
	creditos.posajustar(350,250);
	salir.posajustar(350,400);
	select.posajustar(350,100);
	AutoSprite menuprincipal[]={fondo,jugar,creditos,salir,select};
	AutoSprite menucreditos[]={fondo,salir,select};
	menucreditos[2].posajustar(350,399);
	int mostrar=0;	
    while (window.isOpen())
    {
   		Event event; 	
		switch(mostrar){
			case(0):{
				window.clear();
				mostrarset(window,menuprincipal,5);
				window.display();
				break;
			}
			case(1):{
				window.clear();
				mostrarset(window,menucreditos,3);
				window.display();
				break;
			}
		}
        while (window.pollEvent(event))
        {
            if (event.type == Event::Closed)
                window.close();
			if(mostrar==0){
				movermenu(menuprincipal[4],0,150,100,400);
				if (Keyboard::isKeyPressed(Keyboard::Return)&&menuprincipal[4].y==400)
					window.close();
				if (Keyboard::isKeyPressed(Keyboard::Return)&&menuprincipal[4].y==250){
					mostrar=1;
				}
			}
        }
    }
    return EXIT_SUCCESS;
}
