package ch11;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ballgame extends JPanel implements KeyListener {
	private ball ball;
	private Racquet1 racquet1;
	private Racquet2 racquet2;
	private Score score;

	public ballgame() {
		ball = new ball(this, Color.black);
		this.setBackground(Color.green);
		racquet1 = new Racquet1(this, 0, 150, Color.red);
		racquet2 = new Racquet2(this, 575, 150, Color.blue);
		score = new Score(this.getWidth(), this.getHeight());
		this.setFocusable(true);
		this.addKeyListener(this);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		racquet1.keyPressed(e);
		racquet2.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		racquet1.keyReleased(e);
		racquet2.keyReleased(e);
	}

	void move() {
		ball.move();
		racquet1.move();
		racquet2.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect(280, 0, 4, 600);
		g.fillOval(271, 175, 20, 20);
		g.drawOval(220, 125, 120, 120);
		g.drawOval(220, 126, 119, 119);
		g.drawOval(221, 126, 118, 118);
		g.drawRect(0, 100, 80, 180);
		g.drawRect(503, 100, 80, 180);
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
		score.draw(g2d);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("PingPong Game");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		ballgame game = new ballgame();
		frame.add(game);
		frame.setVisible(true);
		while (true) {
			game.move();
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class Score {
		private int GAME_WIDTH;
		private int GAME_HEIGHT;
		protected int player1 = 0;
		protected int player2 = 0;

		public Score(int gameWidth, int gameHeight) {
			GAME_WIDTH = gameWidth;
			GAME_HEIGHT = gameHeight;
		}

		public void draw(Graphics g) {
			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
			g.drawString(String.valueOf(player1), 230, 50);
			g.drawString(String.valueOf(player2), 300, 50);
		}

		public void userScore(int player1, int player2) {
			this.player1 = player1;
			this.player2 = player2;
		}
	}

	class ball {
		private static final int RADIUS = 20;
		private int x = 150, y = 150, xSpeed = 3, ySpeed = 3;
		private ballgame game;
		private Color color;

		int player1 = 0, player2 = 0;

		public ball(ballgame game, Color color) {
			this.game = game;
			this.color = color;
		}

		void move() {
			if (x + xSpeed < 0) {
				xSpeed = 3;
				score.userScore(player1, ++player2);
			}
			if (x + xSpeed > game.getWidth() - 2 * RADIUS) {

				xSpeed = -3;
				score.userScore(++player1, player2);
			}
			if (y + ySpeed < 0) {
				ySpeed = 3;
			}
			if (y + ySpeed > game.getHeight() - 2 * RADIUS) {
				ySpeed = -3;
			}
			if (collision())
				xSpeed = -xSpeed;
			x += xSpeed;
			y += ySpeed;
		}

		private boolean collision() {
			return game.racquet1.getBounds().intersects(getBounds())
					|| game.racquet2.getBounds().intersects(getBounds());
		}

		public void draw(Graphics2D g) {
			g.setColor(color);
			g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
		}

		public Rectangle getBounds() {
			return new Rectangle(x, y, 2 * WIDTH, 2 * HEIGHT);
		}
	}

	class Racquet1 {
		private static final int WIDTH = 10;
		private static final int HEIGHT = 80;
		private int x = 0, y = 0;
		private int xSpeed = 0;
		private int ySpeed = 0;
		private ballgame game;
		private Color color;

		public Racquet1(ballgame game, int x, int y, Color color) {
			this.game = game;
			this.x = x;
			this.y = y;
			this.color = color;
		}

		void move() {
			if (y + ySpeed > 0 && y + ySpeed < 285)
				y += ySpeed;
		}

		public void draw(Graphics2D g) {
			g.setColor(color);
			g.fillRect(x, y, WIDTH, HEIGHT);
		}

		public void keyReleased(KeyEvent e) {
			ySpeed = 0;
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W)
				ySpeed = -5;
			else if (e.getKeyCode() == KeyEvent.VK_S)
				ySpeed = 5;

		}

		public Rectangle getBounds() {
			return new Rectangle(x, y, WIDTH, HEIGHT);

		}

	}

	class Racquet2 {
		private static final int WIDTH = 10;
		private static final int HEIGHT = 80;
		private int x = 0, y = 0;
		private int xSpeed = 0;
		private int ySpeed = 0;
		private ballgame game;
		private Color color;

		public Racquet2(ballgame game, int x, int y, Color color) {
			this.game = game;
			this.x = x;
			this.y = y;
			this.color = color;
		}

		void move() {
			if (y + ySpeed > 0 && y + ySpeed < 285)
				y += ySpeed;
		}

		public void draw(Graphics2D g) {
			g.setColor(color);
			g.fillRect(x, y, WIDTH, HEIGHT);
		}

		public void keyReleased(KeyEvent e) {
			ySpeed = 0;
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				ySpeed = -5;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				ySpeed = 5;

		}

		public Rectangle getBounds() {
			return new Rectangle(x, y, WIDTH, HEIGHT);
		}
	}
}