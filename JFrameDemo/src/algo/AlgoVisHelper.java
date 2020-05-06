package algo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class AlgoVisHelper {
	
	private AlgoVisHelper() {
		
	}
	
	public static void setStrokeWidth(Graphics2D g2d, int w) {
		int strokeWidth = w;
		g2d.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	}
	public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
		Ellipse2D cicle = new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		g2d.draw(cicle);
	}
	public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
		Ellipse2D cicle = new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		g2d.fill(cicle);
	}
	
	public static void setColor(Graphics2D g2d, Color color) {
		g2d.setColor(color);
	}
	
	public static void pause(int t) {
		try {
			Thread.sleep(t);
		}catch(InterruptedException e) {
			System.out.println("Error in sleeping");
		}
	}
}
  