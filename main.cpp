#include "movimiento.h"

int main(){
    RenderWindow juego(VideoMode(800,600),"Litle Farm");
    AutoSprite **arr=new AutoSprite*[3];
    arr[0]=new AutoSprite("fondo.jpg");
    arr[1]=new AutoSprite("personaje.png");
    arr[2]=new AutoSprite("llama.png");
    arr[0]->escalar(800,600);
    arr[1]->escalar(100,120);
    arr[1]->cambiarColor(61,207,222,255);
    arr[2]->escalar(100,120);
    arr[2]->ajustarPosicion(400,300);
    while(juego.isOpen()){
        Event event;
        while(juego.pollEvent(event)){
            switch(event.type){
            case Event::Closed:
                juego.close();
			
        	}
    	}
    	juego.clear();
    	
        mostrar(juego,arr,3);
        mover(arr[1],arr,1,1,3);
        juego.display();
	}  	
    delete[] arr;
    return 0;
}
