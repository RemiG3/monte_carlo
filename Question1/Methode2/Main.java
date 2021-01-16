import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.awt.geom.Ellipse2D;

class Main{
	
	
	public static void main(String[] args){
		
		boolean withDraw = true;
		double rayon = 10;
		
		DrawMehod draw = null;
		if(withDraw)
			draw = new DrawMehod(600, 500, 500, 1);
		
		long startTime = System.currentTimeMillis();
		
		double c = 0;
		long n = 10_000_000;
		for(long i = 1; i < n; i++){
			double xi = Math.random()*rayon;
			double yi = Math.random()*rayon;
			
			if(withDraw){
				draw.addPoint(xi*50, yi*50);
				if(c%(long)(n/100) == 0){
					draw.repaint();
					//try{ Thread.sleep(50); } catch(InterruptedException  e){}
				}
			}
			
			if((xi*xi) + (yi*yi) <= (rayon * rayon))
				c++;
		}
		
		double result = (c / n)*4;
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);
	}
	
}



class DrawMehod extends JFrame{
	
	private volatile ArrayBlockingQueue<double[]> points;
	private int frameSize, squareSize, circleSize, pointSize;
	
	public DrawMehod(int frameSize, int squareSize, int circleSize, int pointSize){
		setSize(frameSize, frameSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		points = new ArrayBlockingQueue<double[]>(100_000_000);
		this.frameSize = frameSize;
		this.squareSize = squareSize;
		this.circleSize = circleSize;
		this.pointSize = pointSize;
	}
	
	public void addPoint(double x, double y){
		points.add(new double[]{x, y});
	}
	
	public void paint(Graphics g){
		//super.paint(g);
		g.drawOval(-circleSize, frameSize-circleSize, circleSize*2, circleSize*2);
		g.drawRect(0, frameSize-squareSize, squareSize, squareSize);
		
		Graphics2D g2 = (Graphics2D) g;
		for(double[] point : points){
			g2.fill(new Ellipse2D.Double(point[0], frameSize-point[1], pointSize, pointSize));
		}
	}
	
}

