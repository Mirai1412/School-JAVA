import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import javax.swing.JFrame;

public class Paint extends Mypanel {

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gp = new GradientPaint(0, 20, Color.WHITE, 0, 70, Color.RED);
		for (Shape s : shapeArray) {
			g2.setPaint(gp);
			g2.fill(s);
		}
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setSize(600, 180);
		frame.setTitle("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Paint());
		frame.setVisible(true);
	}
}