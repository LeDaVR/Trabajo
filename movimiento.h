#ifndef MOVIMIENTO_H
#define MOVIMIENTO_H
#include "autoSprite.h"
#include<SFML/Audio.hpp>
//------------------------------------------------------

class Tiempo{
	protected:
		Time *tiempo;
	public:
		Tiempo();
		~Tiempo();
		float getTime();
		void setTime();
		void esperarSeg(float tiemp);
};
//--------------------------------------------------
class SpriteArray:public Tiempo{
	protected:

		int aux,velocidadMov;
		int size;
		void redimensionar(const int);
		
	public:
		AutoSprite *escena;
		
		//constructores
		SpriteArray();
		SpriteArray(const SpriteArray&);
		~SpriteArray();
	
		
		void addSprite(AutoSprite);
		void posicionarSprite(const AutoSprite,const int);
		void removeSprite(const int);
		virtual void mostrar(sf::RenderWindow &);
		void setview(sf::RenderWindow&,int);
		virtual void mover(int,int)=0;
		bool checkPosition(int,int,int,int);

		int getSize() const ;
};
//--------------------------------------------------------------------------
class Escena : public SpriteArray{
	public:
		Escena();
		bool basecolision1(AutoSprite,int,char);
		bool basecolision2(AutoSprite,int,char);
		bool rightcolision(AutoSprite,int);
		bool leftcolision(AutoSprite,int);
		bool upcolision(AutoSprite,int);
		bool downcolision(AutoSprite,int);
		void moverentidad(int,int);
		void mover(int,int);
};

class Carrera : public 	Escena {
	private:
		AutoSprite fondo;
		AutoSprite cuy;
		AutoSprite aguila;
		AutoSprite tronco;
	public:
		Carrera();
		void moverAguila(int); //velocidad
		void movercuy(int,int,RenderWindow &);
		void setviewcuy(RenderWindow &);
		
};




//---------------------------------------------------------------------------
class Menu : public SpriteArray{
	protected:
		int filas,columnas;
	public:
		Menu();
		Menu(int,int);
		virtual void mover(int,int);
		void setTam(int,int);
};


//----------------------------------------------------------------------------
class Inventario : public Menu{
	private:
		sf::Font fuente;
		sf::Text texto;
		AutoSprite fondo;
		AutoSprite select;
		AutoSprite hacha;
		AutoSprite pala;
		AutoSprite combo;
		AutoSprite regadera;
		AutoSprite vacio;
		int dinero;
		std::string **matriz;
		int factual,cactual;
		

	public:
		Inventario(int,int);
		~Inventario();
	
		void mostrar(sf::RenderWindow&);
		void mostrarinventario(int&,int&);
		void mover(int,int);
		void addItem(AutoSprite autosprite);
		void removecurrentItem();
		void setDinero(int);
		void sell();
		
		int getDinero();
		std::string getselect() const ;
};

//---------------------------------------------------------------------------
class EscenarioPrincipal : public Escena{
	private:
		AutoSprite ppapa;
		AutoSprite pmaiz;
		AutoSprite ptomate;	
		AutoSprite fondo;
		AutoSprite persona;
		AutoSprite casaex;
		AutoSprite tierra;
		AutoSprite minijuegos;
		bool checkTerreno(float,float);
	public:
		EscenarioPrincipal();
		void changeTerreno(Inventario*);
		void nextDay();
			
};
//----------------------------------------------------------------------
class Tienda : public Menu{
	private:
		AutoSprite fondo;
		AutoSprite select;
		AutoSprite tomate;
		AutoSprite maiz;
		AutoSprite papa;
	public:
		Tienda(int,int);
		AutoSprite comprar(Inventario*);
};




/*
class Comer : public Minijuegos{
	public:
		Comer();
		void mover(int,int,int);
};
*/
//---------------------------------

#endif
