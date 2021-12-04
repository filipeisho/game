import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.FontFormatException;

public class Game implements ImageObserver {
	//int y=50;

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}


	Graphics graphics;
	Window window;
	String scene;
	Boolean accepted = true;
	Font custom_font;
	int i = 0;
	String[] dialogs={"Filippo","Ya casi he terminado el space invaders de Programación Avanzada",
			"Josep","Yo lo entregué ayer y el profesor me dijo que quería tener una reunión conmigo.",
			"Josep","El año pasado hicieron el marcianitos y el profe pensó que me había copiado",
			"Filipp","Fuaaaa tengo que hacer otro juego ahora que ya lo he terminado?",
			"Josep","A mi me hizo implementar un background que se movía y un par de cosas más y me lo acepto",
			"Filippo", "Le dijiste que yo también estaba haciendo el mismo juego?",
			"Josep", "No... Enviale un mail y dile que tu también lo has hecho, tienes una reunión con el y le enseñas que no lo has copiado",
			"Filippo", "Buff ahora se lo envio",
			"Filippo", "No se si seguir implementando más cosas o dejarlo así y hablarlo con el primero",
			"Filippo", "....",
			"el Profesor mientras ve esto", "Ahora tengo que aceptar esto? Hmmm... Al menos ha sido original"
			

			
	};
	String dialog,name;

	List <Car> car_list = new ArrayList<Car>();
	List <Enemy> enemyList = new ArrayList<Enemy>();
	int total_cars = 25;
	Player player;
	Random r = new Random();
	Game(Window window) {
		this.window=window;
		this.graphics=window.graphics;
	}
	void nextScene() {
		if (scene == "intro") {
			gameInitialization();
		}
		else if (scene == "game") {
			scene = "gameover";
		}
		else if (scene == "gameOver") {
			scene = "transition";
		}
		else if (scene == "win") {
			scene = "transition";
		}
	}
	
	void run() {
		initialization();
		while (true) {
			if (scene == "intro") {
				movement_intro();
				paint_intro();
				window.repaint();
			}
			if (scene == "game") {
				player.checkCollision(enemyList);
				gameMovement();
				paintGame();
				window.repaint();

				if (enemyList.size()<=0) {
					scene="win";
				}
			}
			if (scene == "win") {
				paintWin();
				window.repaint();
			}
			if (scene == "gameOver") {
				paintGameOver();
				window.repaint();
			}
			if (scene == "transition") {
				paintTransition();
				window.repaint();
			}
			if (scene == "realLife") {
				paintRealLife();
				window.repaint();
			}
			if (scene == "end") {
				paintEnd();
				window.repaint();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void check_colisions_intro() {
		for(int i=0;i<enemyList.size();i++) {
			if (enemyList.get(i) != null){
				check_colision(i);
			}
		}
	}
	void check_colision(int i) {
		if (enemyList.get(i).x <= player.x + 30 && enemyList.get(i).x >= player.x - 30) {
			if (enemyList.get(i).y <= player.y + 20 && enemyList.get(i).y >= player.y - 20) {
				
			}
				
		}
	}

	void initialization() {
		scene = "intro";
		player = new Player(window.width/2-25,window.height-30,20);

		try {
		    //create the font to use. Specify the size!
		    try {
				custom_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/space_invaders.ttf")).deriveFont(20f);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			graphics.setFont(custom_font); 

		    //register the font
		    ge.registerFont(custom_font);
		} catch (IOException e) {
		    e.printStackTrace();
		} 
		

		int direction = 0;
		for(int i=0;i<25;i++) {
			direction = Math.abs(r.nextInt())%2;
			System.out.println(direction);
			if (direction==0) {
				car_list.add(new Car(Math.abs(r.nextInt())%300,50+i*20, Math.abs(r.nextInt())%5+10));
			}
			else { 
				car_list.add(new Car(700-Math.abs(r.nextInt())%300,50+i*20, -Math.abs(r.nextInt())%5-10));
			
			}
			int number_of_clones = Math.abs(r.nextInt())%8 + 1;
			for(int l = 1; l<2; l++) {
				if (direction==0) {

					car_list.add(new Car(-200*l,car_list.get(i).y, car_list.get(i).v));
				}
				else {
					car_list.add(new Car(700 + 200*l,car_list.get(i).y, car_list.get(i).v));

				}
				
			}
		
		}
	}
	void gameInitialization() {
		player = new Player(window.width/2-25,window.height-30,20);

		
		int j = 0;
		int l = 1;
		for(int i=0;i<100;i++) {
			if (i%10 == 0) {
				j++;
				l = 1;
			}
			
			enemyList.add(new Enemy(l*50,j*30,20));
			
			l++;
		}
		scene = "game";

	}

	void realLifeInitialization() {
		i = 0;
		player = new Player(window.width/2-25,window.height-30,20);
		player.icon = new ImageIcon(new File("src/Emissary.png").getAbsolutePath()).getImage();
		
	

	}
	void gameMovement() {
		player.moveBullets();
		int l = 1;
		int j = 0;
		for (int s = 0; s<enemyList.size();s++) {
			if (enemyList.get(s).x > window.width - 50 || enemyList.get(s).x < 20) {
				for(int i=0;i<enemyList.size();i++) {
					if (i%10 == 0) {
						j++;
						l = 1;
					}
					enemyList.get(i).v = - enemyList.get(i).v;
					enemyList.get(i).move();
					enemyList.get(i).y = enemyList.get(i).y + 50;
					
					l++;
				}
				break;
				
			}
			
		}
		for(int i=0;i<enemyList.size();i++) {
			if (i%8 == 0) {
				j++;
				l = 1;
			}
			
			enemyList.get(i).move();
			
			l++;
		}
		if (enemyList.size()> 0 && enemyList.get(enemyList.size()-1).y > window.height-50) {
			scene = "gameOver";
		}
		
		
		
	}
	
	
	void movement_intro() {
		
	for(int i=0;i<car_list.size();i++) {
			car_list.get(i).move();
			if (car_list.get(i).x < 0 || car_list.get(i).x > window.width) {
				if (car_list.get(i).v>0) {
					car_list.get(i).x = 0;
				}
				else {
					car_list.get(i).x = 700;

				}
			}
		}
		} 	
	
	void draw_controls() {
		graphics.setColor(Color.WHITE);
		graphics.drawString("Press enter to start the game", 200, window.height/2 + 250);
		Image icon = new ImageIcon(new File("src/logo.png").getAbsolutePath()).getImage();
		graphics.drawImage(icon, 250, 50,300,180, this);
			
	}
	void paint_intro() {
		Image icon = new ImageIcon(new File("src/background.png").getAbsolutePath()).getImage();
		graphics.drawImage(icon, 0, 0,1000,1000, this);
		for(int i=0;i<car_list.size();i++) {
			
				car_list.get(i).paint(graphics);
			
		
		}
		draw_controls();

			
		
	
	}
	void paintWin() {

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,window.width,window.height);
		graphics.setColor(Color.WHITE);
		graphics.drawString("You won!", 200, window.height/2 + 250);
		Image icon = new ImageIcon(new File("src/logo.png").getAbsolutePath()).getImage();
		graphics.drawImage(icon, 250, 50,300,180, this);

			
		
	
	}
	void paintGameOver() {

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,window.width,window.height);
		graphics.setColor(Color.WHITE);
		graphics.drawString("Game over", 200, window.height/2 + 250);
		Image icon = new ImageIcon(new File("src/logo.png").getAbsolutePath()).getImage();
		graphics.drawImage(icon, 250, 50,300,180, this);
		window.repaint();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene="transition";
			
		
	
	}
	void paintTransition() {
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,0,window.width,window.height);

		graphics.setColor(Color.WHITE);
		for (int i=0; i<=20; i++) {
			if (i%2==0) {
				graphics.setColor(Color.WHITE);
			}
			else {

				graphics.setColor(Color.BLACK);
			}
			graphics.fillRect(0,0,window.width,window.height);

				window.repaint();

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		scene = "realLife";
		realLifeInitialization();

		
	
	}
	
	void paintGame() {
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,window.width,window.height);
		for(int i=0;i<enemyList.size();i++) {
			
			enemyList.get(i).paint(graphics);
		
	
		}
		player.paint(graphics);
			
		
	
	}
	void paintRealLife() {
		 graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
		 Image icon = new ImageIcon(new File("src/classroom.jpg").getAbsolutePath()).getImage();
		 
		graphics.drawImage(icon, 0, 0,800,600, this);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,window.height/2+200,800,100);
		graphics.setColor(Color.BLACK);
		graphics.drawString("Filippo Facioni", 20, window.height/2 );
		Image character = new ImageIcon(new File("src/filip.png").getAbsolutePath()).getImage();
		graphics.drawImage(character,0,window.height/2+70, 150, 150, this);
		//player.paint(graphics);
		
		graphics.setColor(Color.BLACK);
		graphics.drawString("Josep Boncompte", 600, window.height/2 );
		Image character2 = new ImageIcon(new File("src/boncom.png").getAbsolutePath()).getImage();
		graphics.drawImage(character2,650,window.height/2+65, 150, 150, this);
	
		graphics.setColor(Color.WHITE);
		if (i<22) {
		if (dialogs[i] == "Josep"){
				graphics.drawString("Josep", 700, window.height/2+230 );

		}
		
		else if (dialogs[i] == "Filippo"){
			graphics.drawString(dialogs[i], 30, window.height/2+230 );

		}
		else if (dialogs[i] =="el Profesor mientras ve esto"){
			graphics.drawString("el Profesor mientras ve esto", 350, window.height/2+230 );

		}
		if (dialogs[i+1].length()> 60) {
			String[] parts = {dialogs[i+1].substring(0, 60),dialogs[i+1].substring(60)};
			graphics.drawString(parts[0], 30, window.height/2+270 );
			graphics.drawString(parts[1], 30, window.height/2+290 );

		}
		else {
			graphics.drawString(dialogs[i+1], 30, window.height/2+270 );

		}
		}
		else {
			graphics.setColor(Color.WHITE);
			graphics.fillRect(190,window.height/2-200,400,80);
			graphics.setColor(Color.BLACK);

			graphics.drawString("Aceptar el trabajo?",200,window.height/2-190);
		
			if (accepted == true) {
				graphics.setColor(Color.BLUE);
				graphics.drawString("* Si",225,window.height/2-150);
				graphics.setColor(Color.BLACK);
				graphics.drawString("* No",225,window.height/2-130);

			}
			else {
				graphics.setColor(Color.BLACK);
				graphics.drawString("* Si",225,window.height/2-150);
				graphics.setColor(Color.BLUE);
				graphics.drawString("* No",225,window.height/2-130);
			}
			
			


		}
		window.repaint();


			
		
	
	}
	void paintEnd() {
		graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
		 Image icon = new ImageIcon(new File("src/proxy-image.gif").getAbsolutePath()).getImage();
		 
		graphics.drawImage(icon, 0, 0,800,600, this);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,window.height/2+200,800,100);
		graphics.setColor(Color.WHITE);
		graphics.drawString("Esa noche lo decidiste, le pondrías un 10.", 30, window.height/2+230 );
		
		
	}
}
