#include "movimiento.h"
bool upcolision(AutoSprite *autosprite,AutoSprite **a,int cantidad,int len){
	for(int j=0;j<len;j++){
		int arrx[5],x=0;
		for(int i=0;i<5;i++){
			arrx[i]=autosprite->getancho()*i/4+autosprite->x;
			if(arrx[i]>=a[j]->x&&arrx[i]<=a[j]->x+a[j]->getancho()&&a[j]!=autosprite)
				x++;
		}
		if(x>0&&autosprite->y-cantidad<=a[j]->y+a[j]->getlargo()&&autosprite->y+autosprite->getlargo()>a[j]->y+a[j]->getlargo()){
			return false;
		}
	}
	return true;
}
bool downcolision(AutoSprite *autosprite,AutoSprite **a,int cantidad,int len){
	for(int j=0;j<len;j++){
		int arrx[5],x=0;
		for(int i=0;i<5;i++){
			arrx[i]=autosprite->getancho()*i/4+autosprite->x;
			if(arrx[i]>=a[j]->x&&arrx[i]<=a[j]->x+a[j]->getancho()&&a[j]!=autosprite)
				x++;
		}
		if(x>0&&autosprite->y+autosprite->getlargo()+cantidad>=a[j]->y&&autosprite->y<a[j]->y){
			return false;
		}
	}
	return true;
}
bool leftcolision(AutoSprite *autosprite,AutoSprite **a,int cantidad,int len){
	for(int j=0;j<len;j++){
		int arry[5],y=0;
		for(int i=0;i<5;i++){
			arry[i]=autosprite->getlargo()*i/4+autosprite->y;
			if(arry[i]>=a[j]->y&&arry[i]<=a[j]->y+a[j]->getlargo()&&a[j]!=autosprite)
				y++;
		}
		if(y>0&&autosprite->x-cantidad<=a[j]->x+a[j]->getancho()&&autosprite->x+autosprite->getancho()>a[j]->x+a[j]->getancho()){
			return false;
		}
	}
	return true;
}
bool rightcolision(AutoSprite *autosprite,AutoSprite **a,int cantidad,int len){
	for(int j=0;j<len;j++){
		int arry[5],y=0;
		for(int i=0;i<5;i++){
			arry[i]=autosprite->getlargo()*i/4+autosprite->y;
			if(arry[i]>=a[j]->y&&arry[i]<=a[j]->y+a[j]->getlargo()&&a[j]!=autosprite)
				y++;
		}
		if(y>0&&autosprite->x+autosprite->getancho()+cantidad>=a[j]->x&&autosprite->x<a[j]->x){
			return false;
		}
	}
	return true;
}
void mover(AutoSprite *autosprite,AutoSprite **a,int cx,int cy,int len){
	int x=autosprite->x;
	int y=autosprite->y;
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)&&upcolision(autosprite,a,cy,len)){
		y-=cy;
		autosprite->posajustar(x,y);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)&&downcolision(autosprite,a,cy,len)){
		y+=cy;	
		autosprite->posajustar(x,y);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)&&leftcolision(autosprite,a,cx,len)){
		x-=cx;
		autosprite->posajustar(x,y);		
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)&&rightcolision(autosprite,a,cx,len)){
		x+=cx;	
		autosprite->posajustar(x,y);
	}
}
void moverentidad(AutoSprite *autosprite,AutoSprite **a,int cx,int cy,int len){
	int ran=rand()%100+1;
	int x=autosprite->x;
	int y=autosprite->y;
	if (upcolision(autosprite,a,cy,len)&&ran<1){
		y-=cy;
		autosprite->posajustar(x,y);
	}else
	if (downcolision(autosprite,a,cy,len)&&ran<2){
		y+=cy;	
		autosprite->posajustar(x,y);
	}else
	if (leftcolision(autosprite,a,cx,len)&&ran<3){
		x-=cx;
		autosprite->posajustar(x,y);		
	}else
	if (rightcolision(autosprite,a,cx,len)&&ran<4){
		x+=cx;	
		autosprite->posajustar(x,y);
	}
}
