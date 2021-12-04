import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window extends Frame implements KeyListener,WindowListener {
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	Game game;
	int width=800,height=600;
	
	Boolean left = false;
	Boolean space = false;
	Boolean right = false;
	Boolean up = false;
	Boolean down = false;

	Image im;
	Graphics graphics;
	public static void main(String[] args) {
		new Window();
	}
	
	Window() {
		super("Game");
		setSize(width,height);
		setVisible(true);
		this.addKeyListener(this);
		addWindowListener(this);
		im=this.createImage(width, height);
		graphics=im.getGraphics();
		game=new Game(this);
		game.run();
	}
	public void update(Graphics graphics) {	
		if (this.left==true) {
			game.player.moveLeft();

		}
		if (this.right==true) {
			game.player.moveRight(width);
		}
		if (this.up==true) {
			game.player.moveUp();
			game.accepted = true;
			System.out.println("up");

		}
		if (this.down==true) {
			game.player.moveDown(height);
			game.accepted = false;
			System.out.println("down");

		}
		if (this.space==true) {
			game.player.shoot();
			this.space=false;
			game.i = game.i+2;
			if (game.i>22 && game.scene =="realLife") {
				if (game.accepted==true) {
					game.scene="end";
				}
				else {
					game.accepted= true;
				}
			}
		}
		
		

		paint(graphics);
		
	}
	public void paint(Graphics graphics) {
		
		graphics.drawImage(im,0,0,null);
	}

	

	@Override
	public void keyReleased(KeyEvent e) {
		if (game.scene == "intro") {
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				game.nextScene();
			}
		}
		if (game.scene == "win") {
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {

				game.nextScene();
			}
		}
		if (game.scene == "gameOver") {
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				game.nextScene();
			}
		}
		if (game.scene == "game") {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				space = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				right = false;

			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				left = false;

			}
			
			
		}
		if (game.scene == "realLife") {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				space = false;

				
			}
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				up = false;

				
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				down = false;

				
			}
			
			
			
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (game.scene == "realLife") {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				space = true;
				
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				right = true;

			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				left = true;

			}
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				up = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				down = true;

			}
			
			
			
		}
		if (game.scene == "intro") {
			if (e.getKeyCode()==KeyEvent.VK_ENTER ) {
				game.nextScene();
			}
		}
		if (game.scene == "game") {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				space = false;
			}		
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				left = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				right = true;

			}
			
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

}
