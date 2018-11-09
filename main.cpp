#include "movimiento.h"

int main(){
    RenderWindow juego(VideoMode(800,600),"Litle Farm");
    AutoSprite fondo("img/fondo.jpg");
    fondo.escalar(1000,1000);
	AutoSprite persona("img/0dpersona.png");
	persona.escalar(100,100);
    persona.ajustarPosicion(600,200);
    AutoSprite llama2("img/llama.png");
    llama2.escalar(100,100);
    llama2.ajustarPosicion(100,100);
    AutoSprite casaex("img/images.jpg");
    casaex.ajustarPosicion(300,300);
    casaex.escalar(400,500);
    
    
    SpriteArray escena;
    escena.addSprite(fondo);
    escena.addSprite(persona);
    escena.addSprite(llama2);
    escena.addSprite(casaex);

    AutoSprite fondocasa("img/casa.jpg");
    fondocasa.escalar(600,600);
    SpriteArray casa;
    casa.addSprite(fondocasa);
    persona.ajustarPosicion(10,10);
    casa.addSprite(persona);
    int escenario=0;    
    while(juego.isOpen()){
        Event event;
        while(juego.pollEvent(event)){
            switch(event.type){
            case Event::Closed:
                juego.close();
			
        	}
    	}
    	switch(escenario){
    		case(0):{
    			escena.mover(1,3);
        		escena.mostrar(juego);
				escena.setview(juego,1);
				if(escena.upcolision(escena.escena[1],3)==false&&sf::Keyboard::isKeyPressed(sf::Keyboard::X)&&800<=escena.escena[1].getPosicionY()-3)
					escenario=1;
				break;
			}
			case(1):{
				casa.mover(1,3);
        		casa.mostrar(juego);
				casa.setview(juego,1);
				if(sf::Keyboard::isKeyPressed(sf::Keyboard::Escape))
					escenario=0;
				break;
			}
		}
	}  	
    return 0;
}
