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
	Font custom_font;
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
		else if (scene == "gameover") {
			scene = "intro";
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
				gameMovement();
				paint_game();
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
		for(int i=0;i<car_list.size();i++) {
			if (car_list.get(i) != null){
				check_colision(i);
			}
		}
	}
	void check_colision(int i) {
		if (car_list.get(i).x <= player.x + 30 && car_list.get(i).x >= player.x - 30) {
			if (car_list.get(i).y <= player.y + 20 && car_list.get(i).y >= player.y - 20) {
				player = new Player(window.width/2-25,window.height-30,10);
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
			
			enemyList.add(new Enemy(l*30,j*30,4));
			
			l++;
		}
		scene = "game";

	}
	void gameMovement() {
		player.moveBullets();
		int l = 1;
		int j = 0;
		
		if (enemyList.get(9).x > window.width - 50 || enemyList.get(0).x < 20) {
			for(int i=0;i<100;i++) {
				if (i%10 == 0) {
					j++;
					l = 1;
				}
				enemyList.get(i).v = - enemyList.get(i).v;
				enemyList.get(i).move();
				enemyList.get(i).y = enemyList.get(i).y + 50;
				
				l++;
			}
			
		}
		for(int i=0;i<100;i++) {
			if (i%8 == 0) {
				j++;
				l = 1;
			}
			
			enemyList.get(i).move();
			
			l++;
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
	
	void paint_game() {
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,window.width,window.height);
		for(int i=0;i<enemyList.size();i++) {
			
			enemyList.get(i).paint(graphics);
		
	
		}
		player.paint(graphics);
			
		
	
	}
}
