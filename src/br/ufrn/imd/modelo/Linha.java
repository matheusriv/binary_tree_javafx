package br.ufrn.imd.modelo;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Linha {
	
	private static final Color BLACK_COLOR = Color.rgb(0, 0, 0);    
	private static final Color ORANGE_COLOR = Color.rgb(255, 95, 41); 
	
	private Point2D point1, point2;
	private Color color;
	
	public Linha() {
		this.color = BLACK_COLOR;
	}
	
	public Linha(Point2D point1, Point2D point2) {
		this.point1 = point1;
		this.point2 = point1;
		this.color = BLACK_COLOR;
	}

	public void draw(GraphicsContext gc) {
		gc.setLineWidth(4);
		gc.setStroke(color);
		gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}
	
	public Point2D getPoint1() {
		return point1;
	}
	
	public Point2D getPoint2() {
		return point2;
	}
	
	public void setPoint(Point2D point1, Point2D point2) {
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public void setLineHighlight(boolean highlight) {
		if(highlight) {
			this.color = ORANGE_COLOR;
		} else { 
			this.color = BLACK_COLOR;
		}
	}
		
		
	
}
