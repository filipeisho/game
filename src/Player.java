import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Player implements ImageObserver{
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	int x,y,v,a;
	Image icon;
	List <Bullet> bulletList = new ArrayList<Bullet>();

	Player(int x,int y,int v) {
		this.x=x;this.y=y;this.v=v;
		 icon = new ImageIcon(new File("src/Beholder.png").getAbsolutePath()).getImage();

	}

	
	void moveLeft() {
		if (x > 20 ) {
			x-=v;

		}
	}
	
	void moveUp() {
		if (y > 20 ) {
			y-=v;

		}
	}
	void moveDown(int height) {
		if (y < height-20 ) {
			y+=v;

		}
	}
	
	void moveRight(int width) {
		if (x < width-20) {
			x+=v;

		}
	}
	void checkCollision(List <Enemy> enemyList) {
		for(int i=0; i<bulletList.size();i++) {
			if (bulletList.get(i).check_collision(enemyList) == true){
				bulletList.remove(i);
			}
		}
	}
	
	void shoot() {	
		bulletList.add(new Bullet(x,y,10));
	}
	
	void moveBullets() {
		for (int i = 0; i<bulletList.size(); i++) {
			bulletList.get(i).move();
		}
	}
	
	void paint(Graphics graphics) {
		
		for (int i = 0; i<bulletList.size(); i++) {
			bulletList.get(i).paint(graphics);
		}
		
		graphics.drawImage(icon, x,y, this);
			
	}
}
