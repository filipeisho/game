import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Bullet implements ImageObserver {
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	Random r = new Random();
	int x,y,v;
	Image icon;
	Bullet(int x,int y,int v) {
		this.x=x;this.y=y;this.v=v;
	}
	void move() {
		y-=v;
	}
	boolean check_collision(List <Enemy> enemyList) {
		for (int i = 0; i<enemyList.size(); i++) {
			if (enemyList.get(i).x <= x + 30 && enemyList.get(i).x >= x - 30) {
				if (enemyList.get(i).y <= y + 20 && enemyList.get(i).y >= y - 20) {
					//enemyList.get(i).icon = enemyList.get(i).sprites[0];
					enemyList.remove(i);
					return true;
					
				}
					
			}
		}
		return false;
		
	}
	
	
	void paint(Graphics graphics) {
		graphics.setColor(Color.WHITE);

		
			graphics.fillRect(x, y,3,20);
			
		
		
	}
}
