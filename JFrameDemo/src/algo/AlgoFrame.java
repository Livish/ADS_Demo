package algo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlgoFrame extends JFrame {

	private int canvasWidth;
	private int canvasHeight;

	//构造函数
	public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
		super(title);

		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;

		AlgoCanvas canvas = new AlgoCanvas();
		setContentPane(canvas);
		pack();

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	//构造函数
	public AlgoFrame(String title) {
		this(title, 1024, 768);
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	//To Do 设置自己的函数
	private Circle[] circles;

	public void render(Circle[] circles) {
		this.circles = circles;
		//调用自动调用AlgoCanvas
		repaint();
	}

	//内部类AlgoCanvas --画布
	private class AlgoCanvas extends JPanel {
		public AlgoCanvas() {
			super(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;

			// 抗锯齿Anti- Anliased
			RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.addRenderingHints(hints);

			// 具体绘制
//			AlgoVisHelper.setStrokeWidth(g2d, 5);
//			
//			AlgoVisHelper.setColor(g2d,Color.BLUE);
//			AlgoVisHelper.fillCircle(g2d, canvasWidth/2, canvasHeight/2, 200);
//			
//			AlgoVisHelper.setColor(g2d,Color.red);
//			AlgoVisHelper.strokeCircle(g2d, canvasWidth/2, canvasHeight/2, 200);
			
			//To Do 绘制自己的数据
			AlgoVisHelper.setStrokeWidth(g2d, 1);
			AlgoVisHelper.setColor(g2d, Color.RED);
			for(Circle circle:circles) {
				if(!circle.isFilled) {
					AlgoVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
				}else {
					AlgoVisHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
				}
			}
				

		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(canvasWidth, canvasHeight);
		}
	}

}
