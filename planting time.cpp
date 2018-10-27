#include "autosprite.h"
#include "movimiento.h"
using namespace sf;
using namespace std;
void mostrarset(RenderWindow &a,AutoSprite **arr,int len){
	for(int i=0;i<len;i++){
		a.draw(arr[i]->sprite);
	}
}
void movermenu(AutoSprite *autosprite,int cx,int cy,int limitsup,int limitinf){
	int x=autosprite->x;
	int y=autosprite->y;
	if (Keyboard::isKeyPressed(Keyboard::Up)&&y!=limitsup){
		y-=cy;
		autosprite->posajustar(x,y);
	}
	if (Keyboard::isKeyPressed(Keyboard::Down)&&y!=limitinf){
		y+=cy;
		autosprite->posajustar(x,y);
	}
}
int main()
{
	
    RenderWindow window(VideoMode(800, 600), "SFML window");
    //menu principal
	AutoSprite **menuprincipal=new AutoSprite*[5];
	menuprincipal[0]= new AutoSprite ("img/fondo.jpg");
	menuprincipal[1]= new AutoSprite ("img/MENUJUGAR.jpg");
	menuprincipal[2]= new AutoSprite ("img/MENUCREDITOS.jpg");
	menuprincipal[3]= new AutoSprite ("img/MENUSALIR.jpg");	
	menuprincipal[4]= new AutoSprite ("img/SELECCIONAR.png");
	menuprincipal[1]->posajustar(350,100);
	menuprincipal[2]->posajustar(350,250);
	menuprincipal[3]->posajustar(350,400);
	menuprincipal[4]->posajustar(350,100);
	//escenario1
	AutoSprite **juego=new AutoSprite*[4];
	juego[0]=new AutoSprite("img/fondo.jpg");	
	juego[1]=new AutoSprite("img/angry.png");
	juego[2]=new AutoSprite("img/angry.png");
	juego[3]=new AutoSprite("img/ike.png");
	juego[1]->objeto(100,100);
	juego[2]->objeto(250,250);
	juego[3]->objeto(400,400);
	juego[3]->escalar(0.1,0.1);	
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
				mostrarset(window,juego,4);
				window.display();
				break;
			}
		}
		if(mostrar==1){
				mover(juego[3],juego,3,3,4);
				moverentidad(juego[1],juego,3,3,4);
				moverentidad(juego[2],juego,3,3,4);
				if (Keyboard::isKeyPressed(Keyboard::Escape))
					mostrar=0;
		}
        while (window.pollEvent(event))
        {
            if (event.type == Event::Closed)
                window.close();
			if(mostrar==0){
				movermenu(menuprincipal[4],0,150,100,400);
				if (Keyboard::isKeyPressed(Keyboard::Return)&&menuprincipal[4]->y==400)
					window.close();
				if (Keyboard::isKeyPressed(Keyboard::Return)&&menuprincipal[4]->y==100){
					mostrar=1;
				}
			}
			
        }
    }
    delete[] juego;
    delete[] menuprincipal;
    return EXIT_SUCCESS;
}
