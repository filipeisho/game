import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Enemy implements ImageObserver {
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	Random r = new Random();
	int x,y,v;
	Image icon;
	BufferedImage[] sprites;
	Enemy(int x,int y,int v) {
		this.x=x;this.y=y;this.v=v;
		BufferedImage bigImg;
		try {
			bigImg = ImageIO.read(new File("src/ships_sprite.png"));
			final int width = 8;
			final int height = 8;
			final int rows = 5;
			final int cols = 5;
			sprites = new BufferedImage[rows * cols];

			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			    
			        sprites[(i * cols) + j] = bigImg.getSubimage(
			            j * width,
			            i * height,
			            width,
			            height
			        );
			    }
			}		
			
			icon = sprites[Math.abs(r.nextInt())%5];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void move() {
		
		x+=v;
	}
	void dead() {
		x+=v;
	}
	
	
	void paint(Graphics graphics) {
		
			graphics.drawImage(icon, x, y,40,40, this);
			
		
		
	}
}
