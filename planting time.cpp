#include <SFML/Audio.hpp>
#include <SFML/Graphics.hpp>
using namespace sf;
using namespace std;
class texturec{
	public:
		Texture texture;
		string img;
		int ancho,largo;
		Sprite sprite;
		texturec(string _img,int _x,int _y){
			texture.loadFromFile(_img);
			ancho=_x;
			largo=_y;
			sprite.setTexture(texture);
}
};
void mostrarmenu(RenderWindow &a,texturec *arr,int len){
	for(int i=0;i<len;i++){
		a.draw(arr[i].sprite);
	}
}
int main()
{
    // Create the main window
    sf::RenderWindow window(sf::VideoMode(800, 600), "SFML window");
    // Load a sprite to display
	texturec jugar("img/MENUJUGAR.jpg",350,100);
	jugar.sprite.setPosition(jugar.ancho,jugar.largo);
	texturec creditos("img/MENUCREDITOS.jpg",350,250);
	creditos.sprite.setPosition(creditos.ancho,creditos.largo);
	texturec salir("img/MENUSALIR.jpg",350,400);
	salir.sprite.setPosition(salir.ancho,salir.largo);
	texturec select("img/SELECCIONAR.png",jugar.ancho,jugar.largo);
	select.sprite.setPosition(select.ancho,select.largo);
	texturec fondo("img/fondo.jpg",800,600);
	texturec arr[]={fondo,jugar,creditos,salir,select};
	texturec creditos1("img/fondo.jpg",400,200);
	int mostrar=0;
	texturec arr2[]={creditos1};
    // Create a graphical text to display
    /*sf::Font font;
    if (!font.loadFromFile("arial.ttf"))
        return EXIT_FAILURE;
    sf::Text text("Hello SFML", font, 50);
    // Load a music to play
    sf::Music music;
    if (!music.openFromFile("nice_music.ogg"))
        return EXIT_FAILURE;
    // Play the music
    music.play();*/
    // Start the game loop
    while (window.isOpen())
    {
 		sf::Event event;
        while (window.pollEvent(event))
        {

            if (event.type == sf::Event::Closed)
                window.close();
            if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)){
					if(select.largo==250){
            			arr[4].sprite.setPosition(350,100);
            			select.largo=100;
            			break;
					}else if(select.largo==400){
            			arr[4].sprite.setPosition(350,250);
            			select.largo=250;
					}		
			}
			if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)){
					if(select.largo==250){
            			arr[4].sprite.setPosition(350,400);
            			select.largo=400;
            			break;
					}else if(select.largo==100){
            			arr[4].sprite.setPosition(350,250);
            			select.largo=250;
					}		
			}
			if (sf::Keyboard::isKeyPressed(sf::Keyboard::Return)&&select.largo==400)
			    window.close();
			if (sf::Keyboard::isKeyPressed(sf::Keyboard::Return)&&select.largo==250){
				mostrar=1;
			}
			   
        }
        
		if(mostrar==0){
			window.clear();
			mostrarmenu(window,arr,5);
			window.display();
	        // Process events
		}else if(mostrar==1){
			window.clear();
			mostrarmenu(window,arr2,1);
			window.display();
		}
		
    }
    return EXIT_SUCCESS;
}
