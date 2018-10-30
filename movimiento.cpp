#include "movimiento.h"
bool upcolision(AutoSprite *a,AutoSprite **b,int cantidad,int len){
	for(int j=1;j<len;j++){
		int arrx[5],x=0;
		for(int i=0;i<5;i++){
			arrx[i]=a->getTamanioX()*i/4+a->getPosicionX();
			if(arrx[i]>=b[j]->getPosicionX()&&arrx[i]<=b[j]->getPosicionX()+b[j]->getTamanioX()&&b[j]!=a)
				x++;
		}
		if(x>0&&a->getPosicionY()-cantidad<=b[j]->getPosicionY()+b[j]->getTamanioY()&&a->getPosicionY()+a->getTamanioY()>b[j]->getTamanioY()+b[j]->getPosicionY())
			return false;
	}
	return true;
}
bool downcolision(AutoSprite *a,AutoSprite **b,int cantidad,int len){
	for(int j=1;j<len;j++){
		int arrx[5],x=0;
		for(int i=0;i<5;i++){
			arrx[i]=a->getTamanioX()*i/4+a->getPosicionX();
			if(arrx[i]>=b[j]->getPosicionX()&&arrx[i]<=b[j]->getPosicionX()+b[j]->getTamanioX()&&b[j]!=a)
				x++;
		}
		if(x>0&&a->getPosicionY()+a->getTamanioY()+cantidad>=b[j]->getPosicionY()&&a->getPosicionY()<b[j]->getPosicionY())
			return false;				
	}
	return true;
}
bool leftcolision(AutoSprite *a,AutoSprite **b,int cantidad,int len){
	for(int j=1;j<len;j++){
		int arry[5],y=0;
		for(int i=0;i<5;i++){
			arry[i]=a->getTamanioY()*i/4+a->getPosicionY();
			if(arry[i]>=b[j]->getPosicionY()&&arry[i]<=b[j]->getPosicionY()+b[j]->getTamanioY()&&b[j]!=a)
				y++;
		}
		if(y>0&&a->getPosicionX()-cantidad<=b[j]->getPosicionX()+b[j]->getTamanioX()&&a->getPosicionX()+a->getTamanioX()>b[j]->getPosicionX()+b[j]->getTamanioX())
			return false;			
	}
	return true;
}
bool rightcolision(AutoSprite *a,AutoSprite **b,int cantidad,int len){
	for(int j=1;j<len;j++){
		int arry[5],y=0;
		for(int i=0;i<5;i++){
			arry[i]=a->getTamanioY()*i/4+a->getPosicionY();
			if(arry[i]>=b[j]->getPosicionY()&&arry[i]<=b[j]->getPosicionY()+b[j]->getTamanioY()&&b[j]!=a)
				y++;
		}
		if(y>0&&a->getPosicionX()+a->getTamanioX()+cantidad>=b[j]->getPosicionX()&&a->getPosicionX()<b[j]->getPosicionX())
			return false;	
	}
	return true;
}
void mover(AutoSprite *autosprite,AutoSprite **a,int cx,int cy,int len){
	int x=autosprite->getPosicionX();
	int y=autosprite->getPosicionY();
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)&&upcolision(autosprite,a,cy,len)){
		y-=cy;
		autosprite->ajustarPosicion(x,y);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)&&downcolision(autosprite,a,cy,len)){
		y+=cy;	
		autosprite->ajustarPosicion(x,y);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)&&leftcolision(autosprite,a,cx,len)){
		x-=cx;
		autosprite->ajustarPosicion(x,y);		
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)&&rightcolision(autosprite,a,cx,len)){
		x+=cx;	
		autosprite->ajustarPosicion(x,y);
	}
}
void moverentidad(AutoSprite *autosprite,AutoSprite **a,int cx,int cy,int len){
	int ran=rand()%100+1;
	int x=autosprite->getPosicionX();
	int y=autosprite->getPosicionY();
	if (upcolision(autosprite,a,cy,len)&&ran==1){
		y-=cy;
		autosprite->ajustarPosicion(x,y);
	}else
	if (downcolision(autosprite,a,cy,len)&&ran==2){
		y+=cy;	
		autosprite->ajustarPosicion(x,y);
	}else
	if (leftcolision(autosprite,a,cx,len)&&ran==3){
		x-=cx;
		autosprite->ajustarPosicion(x,y);		
	}else
	if (rightcolision(autosprite,a,cx,len)&&ran==4){
		x+=cx;	
		autosprite->ajustarPosicion(x,y);
	}
}
void mostrar(sf::RenderWindow &a,AutoSprite **arr,int len){
	for(int i=0;i<len;i++)
		a.draw(arr[i]->getSprite());
}
