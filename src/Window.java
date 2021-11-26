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
	public void update(Graphics graphics) {	//millora la sincronia del pintat
		paint(graphics);
	}
	public void paint(Graphics graphics) {
		graphics.drawImage(im,0,0,null);
	}

	

	@Override
	public void keyReleased(KeyEvent e) {
		//Per a saber quina tecla et pitjen, la variable 'e' rebuda cont� la tecla.
		if (game.scene == "intro") {
			if (e.getKeyCode()==KeyEvent.VK_ENTER ) {
				game.nextScene();
			}
		}
		if (game.scene == "game") {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				game.player.shoot();

			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				game.player.moveLeft();

			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				game.player.moveRight();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

}
