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
bool upcolision(AutoSprite &autosprite,AutoSprite &a,int cantidad){
	int arrx[5],x=0;
	for(int i=0;i<5;i++){
		arrx[i]=autosprite.ancho*i/4+autosprite.x;
		if(arrx[i]>=a.x&&arrx[i]<=a.x+a.ancho)
			x++;
	}
	if(x>0&&autosprite.y-cantidad<=a.y+a.largo&&autosprite.y+autosprite.largo>a.y+a.largo){
		return false;
	}
	return true;
}
bool downcolision(AutoSprite &autosprite,AutoSprite &a,int cantidad){
	int arrx[5],x=0;
	for(int i=0;i<5;i++){
		arrx[i]=autosprite.ancho*i/4+autosprite.x;
		if(arrx[i]>=a.x&&arrx[i]<=a.x+a.ancho)
			x++;
	}
	if(x>0&&autosprite.y+autosprite.largo+cantidad>=a.y&&autosprite.y<a.y){
		return false;
	}
	return true;
}
bool leftcolision(AutoSprite &autosprite,AutoSprite &a,int cantidad){
	int arry[5],y=0;
	for(int i=0;i<5;i++){
		arry[i]=autosprite.largo*i/4+autosprite.y;
		if(arry[i]>=a.y&&arry[i]<=a.y+a.largo)
			y++;
	}
	if(y>0&&autosprite.x-cantidad<=a.x+a.ancho&&autosprite.x+autosprite.ancho>a.x+a.ancho){
		return false;
	}
	return true;
}
bool rightcolision(AutoSprite &autosprite,AutoSprite &a,int cantidad){
	int arry[5],y=0;
	for(int i=0;i<5;i++){
		arry[i]=autosprite.largo*i/4+autosprite.y;
		if(arry[i]>=a.y&&arry[i]<=a.y+a.largo)
			y++;
	}
	if(y>0&&autosprite.x+autosprite.ancho+cantidad>=a.x&&autosprite.x<a.x){
		return false;
	}
	return true;
}
void mover(AutoSprite &autosprite,AutoSprite &a,int cx,int cy){
	int x=autosprite.x;
	int y=autosprite.y;
	if (Keyboard::isKeyPressed(Keyboard::Up)&&upcolision(autosprite,a,cy)){
		y-=cy;
		autosprite.posajustar(x,y);
	}
	if (Keyboard::isKeyPressed(Keyboard::Down)&&downcolision(autosprite,a,cy)){
		y+=cy;	
		autosprite.posajustar(x,y);
	}
	if (Keyboard::isKeyPressed(Keyboard::Left)&&leftcolision(autosprite,a,cx)){
		x-=cx;
		autosprite.posajustar(x,y);		
	}
	if (Keyboard::isKeyPressed(Keyboard::Right)&&rightcolision(autosprite,a,cx)){
		x+=cx;	
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
	AutoSprite personaje("img/1.png");
	AutoSprite estorbo("img/angry.png");
	AutoSprite juego[]={fondo,personaje,estorbo};
	juego[1].posdimensiones(169,271,0,0);
	juego[2].posdimensiones(100,100,400,300);
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
				mostrarset(window,juego,3);
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
				if (Keyboard::isKeyPressed(Keyboard::Return)&&menuprincipal[4].y==100){
					mostrar=1;
				}
			}
			if(mostrar==1){
				mover(juego[1],juego[2],3,3);
			}
        }
    }
    return EXIT_SUCCESS;
}
