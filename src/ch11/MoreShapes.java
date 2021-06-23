package ch11;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MoreShapes extends JFrame {
	public MoreShapes() {
		this.setSize(600,180);
		this.setTitle("java 2D Shapes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new Mypanel();
		this.add(panel);
		this.setVisible(true);
	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MoreShapes();
	}
}

class Mypanel extends JPanel{
	ArrayList<Shape> shapeArray = new ArrayList<Shape>();
	public Mypanel(){
		//Shape rect = new Rectangle2D.Float(10,10,70,80);
		shapeArray.add(new Rectangle2D.Float(20,10,70,80));	
		shapeArray.add(new RoundRectangle2D.Float(110,10,70,80,20,20));	
		shapeArray.add(new Ellipse2D.Float(200,10,80,80));	
		shapeArray.add(new Arc2D.Float(310,10,80,80,90,90,Arc2D.OPEN));	
		shapeArray.add(new Arc2D.Float(410,10,80,80,90,90,Arc2D.CHORD));	
		shapeArray.add(new Arc2D.Float(510,10,80,80,90,90,Arc2D.PIE));	
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		for (Shape s : shapeArray) {
			g2.draw(s);
		}
	}
}