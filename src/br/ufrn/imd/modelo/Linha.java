package br.ufrn.imd.modelo;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Linha {
	
	private static Color UI_DEFAULT_COLOR = Color.rgb(99, 99, 99);
	private static Color HIGHLIGHT_COLOR = Color.rgb(49, 116, 222);
	
	private Point2D point1, point2;
	private Color color;
	
	
	public Linha() {
		this.color = UI_DEFAULT_COLOR;
	}
	
	public Linha(Point2D point, Point2D point2) {
		this.point1 = point;
		this.point2 = point;
		this.color = UI_DEFAULT_COLOR;
	}

	/**
	 * Draws the line at a specified position
	 * @param gc The graphics object to use for drawing to a component
	 */
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
	
	public void setPoint(Point2D point, Point2D point2) {
		this.point1 = point;
		this.point2 = point2;
	}
	
	public void setHighlighter(boolean highlight) {
		if(highlight) {
			this.color = HIGHLIGHT_COLOR;
		} 
		else {
			this.color = UI_DEFAULT_COLOR;
		}
	}

	@Override
	public String toString() {
		return " (x,y) = ("  + point1.getX() + ", " + point1.getY() + ")"
			+  " (x,y) = ("  + point2.getX() + ", " + point2.getY()+ ")";
	}
}
