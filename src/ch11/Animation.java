package ch11;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Animation extends JPanel implements ActionListener {

	private BufferedImage img;
	private int x, y;
	private int WIDTH = 1000, HEIGHT = 1000;
	private final int Start_X = 1000;
	private final int Start_Y = 1000;
	private Timer timer;

	public Animation() {

		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(500, 300));
		this.setDoubleBuffered(true);

		File file = new File("space.png");
		System.out.println(file.getAbsolutePath()); // 파일 찾는 경로 표시.

		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		timer = new Timer(10, this);
		timer.start();

		x = Start_X;
		y = Start_Y;

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(img, x, y, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		/*
		 * 이미지의 x, y 좌표 설정.
		 */

		x -= 1;
		y -= 1;

		if (x > WIDTH) {
			x = Start_X;
			y = Start_Y;
		}

		repaint();

	}

	public static void main(String args[]) {
		JFrame frame = new JFrame();

		frame.add(new Animation());
		frame.setTitle("Animation Test");
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}