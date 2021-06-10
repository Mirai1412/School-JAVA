import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class space extends JPanel implements ActionListener {
	private BufferedImage img;
	private int x, y;
	private Timer timer;
	private final int START_X = 0;
	private final int START_Y = 250;

	public space() {
		this.setBackground(Color.black);
		this.setPreferredSize(1000, 1000);
		this.setDoubleBuffered(true);

		File file = new File("space.jpg");
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		timer = new Timer(20, this);
		timer.start();
		x = START_X;
		y = START_Y;
	}

	private void setPreferredSize(int i, int j) {
		// TODO Auto-generated method stub
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, x, y, this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		x += 2;
		y -= 1;
		repaint();
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.add(new space());
		frame.setTitle("space");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}