#include "movimiento.h"
#include <SFML/Audio.hpp>
#include <iostream>
#include <sstream>

Clock reloj;

//----------------------------------------------------------------CLASS SPRITEARRAY
//constructor defecto
SpriteArray::SpriteArray(){
	size=0;
	escena=new AutoSprite[size];

}

//constructor argumento
SpriteArray::SpriteArray(const SpriteArray &_escena){
	size=_escena.getSize();
	escena=new AutoSprite[size];
	for(int i=0;i<size;i++)
		escena[i]=_escena.escena[i];
}

//destructor
SpriteArray::~SpriteArray(){
	delete[] escena;
}

// redimensionar
void SpriteArray::redimensionar(const int _size){
		AutoSprite *temp=new AutoSprite[_size];
		int minsize=(_size>size)?size:_size;
		for(int i=0;i<minsize;i++)
			temp[i]=escena[i];
		delete[] escena;
		escena=temp;
		size=_size;
}

//aniadir
void SpriteArray::addSprite(AutoSprite autosprite){
	redimensionar(size+1);
	escena[size-1]=autosprite;
	
}

//aniadir en una posicion
void SpriteArray::posicionarSprite(const AutoSprite autosprite,const int pos){
	redimensionar(size+1);
	for(int i=size-1;i>pos;i--)
		escena[i]=escena[i-1];
	escena[pos]=autosprite;
}

//remover 
void SpriteArray::removeSprite(const int pos){
	for(int i=pos;i<size-1;i++)
		escena[i]=escena[i+1];
	redimensionar(size-1);
}

//ajustar vista
void SpriteArray::setview(sf::RenderWindow&a,int objeto){
	sf::View view(sf::Vector2f(350.f, 300.f), sf::Vector2f(640.f,480.f));
	int centerx=escena[objeto].getPosicion('X')+escena[objeto].getTamanio('X')/2;
	int centery=escena[objeto].getPosicion('Y')+escena[objeto].getTamanio('Y')/2;
	view.setCenter(sf::Vector2f(centerx,centery));
	a.setView(view);
}

//mostrar 
void SpriteArray::mostrar(sf::RenderWindow &a){
	a.clear();
	for(int i=0;i<size;i++)
		a.draw(escena[i].getSprite());
	a.display();
}

bool SpriteArray::checkPosition(int x1,int x2,int y1,int y2){
	if(escena[size-1].getPosicion('X')>x1&&
	   escena[size-1].getPosicion('X')<x2&&
	   escena[size-1].getPosicion('Y')>y1&&
	   escena[size-1].getPosicion('Y')<y2)
		return true;
	return false;
}

//size
int SpriteArray::getSize()const {
	return size;
}
//--------------------------------------------------------------CLASS ESCENA

Escena::Escena() : SpriteArray(){
	aux=0;
	velocidadMov=10; // a menor numero mas velocidad
}

bool Escena::basecolision1(AutoSprite a,int cantidad,char eje){
	char eje2;
	if(eje=='X')
		eje2='Y';
	else if(eje=='Y')
		eje2='X';
	for(int j=1;j<size;j++){
		if(escena[j].getTamanio('X')==0&&escena[j].getTamanio('Y')==0)
			continue;
		int arrx[5],x=0;
		for(int i=0;i<5;i++){
			arrx[i]=a.getTamanio(eje2)*i/4+a.getPosicion(eje2);
			if(arrx[i]>=escena[j].getPosicion(eje2)&&arrx[i]<=escena[j].getPosicion(eje2)+escena[j].getTamanio(eje2)&&&escena[j]!=&a)
				x++;
		}
		int aux1=a.getPosicion(eje)-cantidad;
		int aux2=escena[j].getPosicion(eje)+escena[j].getTamanio(eje);
		int aux3=a.getPosicion(eje)+a.getTamanio(eje);
		int aux4=escena[j].getTamanio(eje)+escena[j].getPosicion(eje);
		if(x>0&&aux1<=aux2&&aux3>aux4||a.getPosicion(eje)<escena[0].getPosicion(eje))
			return false;
	}
	return true;
}

bool Escena::basecolision2(AutoSprite a,int cantidad,char eje){
	char eje2;
	if(eje=='X')
		eje2='Y';
	else if(eje=='Y')
		eje2='X';
	for(int j=1;j<size;j++){
		if(escena[j].getTamanio('X')==0&&escena[j].getTamanio('Y')==0)
			continue;
		int arrx[5],x=0;
		for(int i=0;i<5;i++){
			arrx[i]=a.getTamanio(eje2)*i/4+a.getPosicion(eje2);
			if(arrx[i]>=escena[j].getPosicion(eje2)&&arrx[i]<=escena[j].getPosicion(eje2)+escena[j].getTamanio(eje2)&&&escena[j]!=&a)
				x++;
		}
		int aux1=a.getPosicion(eje)+a.getTamanio(eje)+cantidad;
		int aux2=escena[j].getPosicion(eje);
		int aux3=a.getPosicion(eje);
		int aux4=escena[j].getPosicion(eje);
		if(x>0&&aux1>=aux2&&aux3<aux4||a.getPosicion(eje)+a.getTamanio(eje)>escena[0].getPosicion(eje)+escena[0].getTamanio(eje))
			return false;				
	}
	return true;
}

bool Escena::rightcolision(AutoSprite a,int cantidad){
	return Escena::basecolision2(a,cantidad,'X');
}
bool Escena::leftcolision(AutoSprite a,int cantidad){
	return basecolision1(a,cantidad,'X');
}
bool Escena::upcolision(AutoSprite a,int cantidad){
	return basecolision1(a,cantidad,'Y');
}
bool Escena::downcolision(AutoSprite a,int cantidad){
	return basecolision2(a,cantidad,'Y');
}

//mover personaje
void Escena::mover(int velocidadx,int velocidady){
	int x=escena[size-1].getPosicion('X');
	int y=escena[size-1].getPosicion('Y');
	std::string img =escena[size-1].getImagen();

	if(aux<velocidadMov){
		img[4]='1';
	}
	else if (aux<velocidadMov*2){
		img[4]='2';
	}
	else if (aux<velocidadMov*3){
		img[4]='0';
	}
	else{
		aux=0;
	}	
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)&&upcolision(escena[size-1],velocidady)){
		y-=velocidady;
		escena[size-1].ajustarPosicion(x,y);
		img[5]='u';
		escena[size-1].setImagen(img);
		aux++;
	}
	else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)&&downcolision(escena[size-1],velocidady)){
		y+=velocidady;	
		escena[size-1].ajustarPosicion(x,y);
		img[5]='d';
		escena[size-1].setImagen(img);
		aux++;
	}
	else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)&&leftcolision(escena[size-1],velocidadx)){
		x-=velocidadx;
		escena[size-1].ajustarPosicion(x,y);		
		img[5]='l';
		escena[size-1].setImagen(img);
		aux++;
	}

	else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)&&rightcolision(escena[size-1],velocidadx)){
		x+=velocidadx;	
		escena[size-1].ajustarPosicion(x,y);
		img[5]='r';
		escena[size-1].setImagen(img);
		aux++;
	}
}

//----------------------------------------------------CLASS ESCENARIOPRINCIPAL

//constructor
EscenarioPrincipal::EscenarioPrincipal() : Escena(){
	ppapa.setImagen("img/ppapa.png");
	pmaiz.setImagen("img/pmaiz.png");
	ptomate.setImagen("img/ptomate.png");
	fondo.setImagen("img/fondo.jpg");
	persona.setImagen("img/0dpersona.png");
	casaex.setImagen("img/casaex.jpg");
	tierra.setImagen("img/0dtierra.jpg");
	minijuegos.setImagen("img/carreraFachada.jpg");
	fondo.escalar(1200,1200);
	persona.escalar(50,100);
    persona.ajustarPosicion(600,200);
    casaex.ajustarPosicion(700,0);
    casaex.escalar(500,300);
	minijuegos.escalar(150,200);
	minijuegos.ajustarPosicion(1050,1000);
    addSprite(fondo);
    addSprite(casaex);
	addSprite(minijuegos);
    addSprite(persona);
    for(int i=0;i<6;i++)
    	for(int j=0;j<6;j++){
    		tierra.ajustarPosicion(j*100,600+i*100);
    		posicionarSprite(tierra,size-1);
		}
	escena[size-2].setImagen("img/ts3tierra.jpg");
}

//interaccion del terreno
void EscenarioPrincipal::changeTerreno(Inventario* inv){
	std::string img=inv->getselect();
	std::string imgparcela;
	int pos=1;
	for(int i=0;i<6;i++){
		for(int j=0;j<6;j++){
			pos++;
			imgparcela=escena[pos+1].getImagen();
			if(checkTerreno(i,j)){
				if(img=="img/combo.png"){
					escena[1+pos].setImagen("img/0dtierra.jpg");
					escena[1+pos].pseudoDimensiones(0,0);
				}
				if(img=="img/pala.png"&&imgparcela=="img/0dtierra.jpg")
					escena[1+pos].setImagen("img/ds0tierra.jpg");

				if(img=="img/regadera.png"&&imgparcela[5]=='s'&&imgparcela[6]<51){
					imgparcela[5]='m';
					escena[1+pos].setImagen(imgparcela);
				}
				if(imgparcela[4]=='d'&&(img=="img/papa.png"||img=="img/tomate.png"||img=="img/maiz.png")){
					imgparcela[4]=img[4];
					escena[1+pos].setImagen(imgparcela);
					inv->removecurrentItem();
				}
				if(img=="img/hacha.png"&&imgparcela[6]=='3'){
					if(imgparcela[4]=='m')
						inv->addItem(pmaiz);
					else if(imgparcela[4]=='t')
						inv->addItem(ptomate);
					else if(imgparcela[4]=='p')
						inv->addItem(ppapa);
					escena[1+pos].setImagen("img/0dtierra.jpg");
					escena[1+pos].pseudoDimensiones(0,0);
				}
			}
		}
	}
}
bool EscenarioPrincipal::checkTerreno(float i,float j){
	std::string direccion=escena[size-1].getImagen();
	int x1=50;
	int x2=110;
	int y1=550;
	int y2=610;
	std::string direcciones="lrud";
	for(int m=0;m<4;m++){
		char dactual=direcciones[m];
		if(m==1){
			x1=-90;x2=-50;
		}
		if(m==2){
			x1=0;x2=50;y1=650;y2=710;
		}
		if(m==3){
			y1=490;y2=550;
		}
		if(checkPosition(j*100+x1,j*100+x2,y1+i*100,y2+i*100)&&
		   dactual==direccion[5])
	  		return true;
	}
	return false;
}

void EscenarioPrincipal::nextDay(){
	std::string posimg;
	int pos=1;
	for(int i=0;i<6;i++){
		for(int j=0;j<6;j++){
			pos++;
			posimg=escena[pos+1].getImagen();
			if(posimg=="img/dm0tierra.jpg")
				escena[pos+1].setImagen("img/ds0tierra.jpg");
			else if(posimg[5]=='m'){
				posimg[5]='s';
				if(posimg[6]=='0'){
					escena[pos+1].pseudoDimensiones(100,100);
					posimg[6]='1';
				}
				else if(posimg[6]=='1')
					posimg[6]='2';
				else if(posimg[6]=='2')
					posimg[6]='3';
				escena[pos+1].setImagen(posimg);
			}
				
		}
	}
}

// clase Carrera ------------------
Carrera::Carrera(){
	fondo.setImagen("img/fondocarrera.jpg");
	tronco.setImagen("img/tronco.png");
	aguila.setImagen("img/aguila.jpg");
	cuy.setImagen("img/0rvenado.png");
	fondo.escalar(3000,480);
	tronco.escalar(60,50);
	tronco.ajustarPosicion(600,430);
	aguila.escalar(250,150);
	aguila.ajustarPosicion(400,200);
	cuy.escalar(110,65);
	cuy.ajustarPosicion(0,415);
	addSprite(fondo);
	addSprite(tronco);
	addSprite(aguila);
	addSprite(cuy);
}

void Carrera::moverAguila(int velocidad){
	int y = escena[2].getPosicion('Y');
	int x = escena[2].getPosicion('X')-velocidad;
	escena[2].ajustarPosicion(x,y);	
}

void Carrera::setviewcuy(sf::RenderWindow&a){
	sf::View view(sf::Vector2f(150.f,480.f), sf::Vector2f(640.f,480.f));
	view.setCenter(sf::Vector2f(escena[3].getPosicion('X')+200,240));
	a.setView(view);
}

void Carrera::movercuy(int numero,int velocidady,sf::RenderWindow &a){
	int x=escena[3].getPosicion('X')+numero;
	int y=escena[3].getPosicion('Y');
	std::string img=escena[3].getImagen();
	if(aux<velocidadMov){
		img[4]='1';
	}
	else if (aux<velocidadMov*2){
		img[4]='2';
	}
	else if (aux<velocidadMov*3){
		img[4]='0';
	}
	else{
		aux=0;
	}	
	if(rightcolision(escena[3],1)){
		escena[3].ajustarPosicion(x,y);
		escena[3].setImagen(img);
		aux++;
	}

	if(escena[3].getPosicion('X')>=1600)
		escena[3].ajustarPosicion(200,y);
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)){
			for(int i=0;i<10;i++){
				y-=velocidady+i;
				x+=8;
				escena[3].ajustarPosicion(x,y);	
				setviewcuy(a);
					int b=i;
				mostrar(a);
			}
			for(int i=0;i<10;i++){
				y+=velocidady+i;
				x+=8;
				escena[3].ajustarPosicion(x,y);
				setviewcuy(a);
				mostrar(a);
			}
	}
}


//------------------------------------------------------CLASS MENU

//constructor
Menu::Menu(){
	filas=0;
	columnas=0;
}
Menu::Menu(int filas,int columnas){
	setTam(filas,columnas);
}
void Menu::setTam(int filas,int columnas){
	this->filas=filas;
	this->columnas=columnas;
}
//mover menu
void Menu::mover(int velocidadx,int velocidady){
	int x=escena[size-1].getPosicion('X');
	int y=escena[size-1].getPosicion('Y');
	int fx=escena[1].getPosicion('X');
	int fy=escena[1].getPosicion('Y');
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)&&y>fy){
			y-=velocidady;
			escena[size-1].ajustarPosicion(x,y);
			Tiempo::esperarSeg(0.4);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)&&y<fy+velocidady*(filas-1)){
			y+=velocidady;	
			escena[size-1].ajustarPosicion(x,y);
			Tiempo::esperarSeg(0.4);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)&&x>fx){
			x-=velocidadx;
			escena[size-1].ajustarPosicion(x,y);
			Tiempo::esperarSeg(0.4);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)&&x<fx+velocidadx*(columnas-1)){
			x+=velocidadx;	
			escena[size-1].ajustarPosicion(x,y);
			Tiempo::esperarSeg(0.4);
	}
}


//--------------CLASS TIEMPO
Tiempo::Tiempo(){
	tiempo = new Time;
	*tiempo = reloj.getElapsedTime();
}

Tiempo::~Tiempo(){
	delete tiempo;
}

float Tiempo::getTime(){ // todo esto para que salga 1.1 osea un decimal :v
	float temp=tiempo->asSeconds();
	temp =temp *100000;
	int a = temp/100000;
	temp = temp-(a*100000);
	int b = temp/10000;
	temp = a+b*0.1;
	return temp;
}

void Tiempo::setTime(){
	*tiempo = reloj.getElapsedTime();
}

void Tiempo::esperarSeg(float tiemp){
	*tiempo = reloj.getElapsedTime() ;
	float aux = getTime()+tiemp;

		while(getTime() < aux){

			*tiempo = reloj.getElapsedTime() ;
		}
}

//---------------------------------------------------CLASS INVENTARIO

//constructor de inventario
Inventario::Inventario(int f,int c): Menu(f,c){

	fuente.loadFromFile("coolveticarg.ttf");
	texto.setFont(fuente);
	factual=0;
	cactual=0;
	dinero=1000;
	matriz = new std::string*[f];
	for(int i=0;i<f;i++)
		matriz[i]=new std::string[c];
	for(int i=0;i<f;i++)
		for(int j=0;j<c;j++)
			matriz[i][j]=" ";
	regadera.setImagen("img/regadera.png");regadera.ajustarPosicion(50+600,25);regadera.escalar(100,100);
	pala.setImagen("img/pala.png");pala.ajustarPosicion(50+400,25);pala.escalar(100,100);
	hacha.setImagen("img/hacha.png");hacha.ajustarPosicion(50,25);hacha.escalar(100,100);
	combo.setImagen("img/combo.png");combo.ajustarPosicion(50+200,25);combo.escalar(100,100);
	fondo.setImagen("img/invmenu.jpg");
	select.setImagen("img/SELECCIONAR.png");
	select.escalar(100,100);
	select.ajustarPosicion(50,25);
	vacio.setImagen(" ");vacio.escalar(100,100);
	addSprite(fondo);
	addSprite(hacha);     matriz[0][0]=hacha.getImagen();
	addSprite(combo);     matriz[0][1]=combo.getImagen();
	addSprite(pala);      matriz[0][2]=pala.getImagen();
	addSprite(regadera);  matriz[0][3]=regadera.getImagen();
	for(int i=1;i<filas;i++)
		for(int j=0;j<columnas;j++){
				matriz[i][j]=vacio.getImagen();
				vacio.ajustarPosicion(50+200*j,25+150*i);
				addSprite(vacio);
		}
	addSprite(select);
}

void Inventario::mostrar(sf::RenderWindow& a){
	a.clear();
	for(int i=0;i<size;i++)
		a.draw(escena[i].getSprite());
	std::ostringstream ss;
	ss<<dinero;
	texto.setString("DINERO: "+ss.str());
	a.draw(texto);
	a.display();
}
//cambia el escenario al inventario
void Inventario::mostrarinventario(int &escenario,int &escenariotemp){
	if(sf::Keyboard::isKeyPressed(sf::Keyboard::Return)){
		escenariotemp=escenario;
		escenario=3;		
	}
}

//mover inventario
void Inventario::mover(int velocidadx,int velocidady){
	int x=escena[size-1].getPosicion('X');
	int y=escena[size-1].getPosicion('Y');
	int fx=escena[1].getPosicion('X');
	int fy=escena[1].getPosicion('Y');
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)&&y>fy){
			y-=velocidady;
			escena[size-1].ajustarPosicion(x,y);
			factual--;
			Tiempo::esperarSeg(0.3);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)&&y<fy+velocidady*(filas-1)){
			y+=velocidady;	
			escena[size-1].ajustarPosicion(x,y);
			factual++;
			Tiempo::esperarSeg(0.3);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)&&x>fx){
			x-=velocidadx;
			escena[size-1].ajustarPosicion(x,y);
			cactual--;
			Tiempo::esperarSeg(0.3);
	}
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)&&x<fx+velocidadx*(columnas-1)){
			x+=velocidadx;	
			escena[size-1].ajustarPosicion(x,y);
			cactual++;
			esperarSeg(0.3);
	}
}

//aniadir objeto al inventario
void Inventario::addItem(AutoSprite item){
	AutoSprite iditem=item;
	iditem.escalar(100,100);
	for(int i=0;i<size;i++)
		std::cout<<escena[i].getPosicion('X')<<"   "<<escena[i].getPosicion('Y')<<std::endl;
	for(int i=1;i<filas;i++)
		for(int j=0;j<columnas;j++)
			if(matriz[i][j]==" "){
				matriz[i][j]=iditem.getImagen();
				iditem.ajustarPosicion(50+200*j,25+150*i);
				escena[4*i+j+1]=iditem;	
				std::cout<<escena[4*i+j+1].getImagen()<<std::endl;
				return;
			}
}
void Inventario::setDinero(int _dinero){
	dinero=_dinero;
}
int Inventario::getDinero(){
	return dinero;
}

//seleccion actual

std::string Inventario::getselect()const{
	return matriz[factual][cactual];
}

void Inventario::removecurrentItem(){
	std::cout<<(4*factual)+cactual+1<<escena[(4*factual)+cactual+1].getImagen()<<" "<<size<<std::endl;
	matriz[factual][cactual]=" ";
	int spriteactual=(4*factual)+cactual+1;
	AutoSprite transparente("img/transparente.png");
	escena[spriteactual].setImagen(transparente.getImagen());
}

void Inventario::sell(){
	std::string currentimg=matriz[factual][cactual];
	if(currentimg=="img/ptomate.png"||currentimg=="img/ptomate.png"||currentimg=="img/pmaiz.png"){
		removecurrentItem();
		dinero+=300;
	}
}
//destructor
Inventario::~Inventario(){
	for(int i=0;i<filas;i++)
		delete[] matriz[i];
	delete[] matriz;
}

//-------------------------------------------------CLASS TIENDA

//constructor de tienda
Tienda::Tienda(int filas,int columnas) : Menu(filas,columnas){
	fondo.setImagen("img/fondotienda.jpg");
	select.setImagen("img/SELECCIONAR.png");
	tomate.setImagen("img/tomate.png");
	papa.setImagen("img/papa.png");
	maiz.setImagen("img/maiz.png");
	tomate.ajustarPosicion(100,480);
	papa.ajustarPosicion(300,480);
	maiz.ajustarPosicion(500,480);
	tomate.escalar(100,100);
	papa.escalar(100,100);
	maiz.escalar(100,100);
	select.ajustarPosicion(100,480);
	select.escalar(100,100);
	addSprite(fondo);
	addSprite(tomate);
	addSprite(papa);
	addSprite(maiz);
	addSprite(select);
}


//retorna el autosprite comprado
AutoSprite Tienda::comprar(Inventario* inv){
	for(int i=1;i<size-1;i++){
		if(escena[size-1].getPosicion('X')==escena[i].getPosicion('X')&&inv->getDinero()>=200){
			inv->setDinero(inv->getDinero()-200);
			return escena[i];
		}
	}
	AutoSprite vacio(" ");
	return vacio;
}


