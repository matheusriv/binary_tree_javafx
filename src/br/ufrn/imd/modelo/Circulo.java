package br.ufrn.imd.modelo;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class Circulo {
	
	final Font font =  Font.font("Cooper Black", FontWeight.BOLD, 16);
	final FontMetrics fm = new FontMetrics(font);
	
	private static final Color BLACK_COLOR = Color.rgb(0, 0, 0); 
	private static final Color ORANGE_COLOR = Color.rgb(255, 95, 41); 
	private static final Color WHITE_COLOR = Color.rgb(255, 255, 255);
	private static final Color BLUE_COLOR = Color.rgb(13, 126, 255);

	public static final int RADIUS = 20;
	private final Integer searchKey;
	
	// The circle attributes
	private Point2D point;
	private Color backgroundColor;
	private Color borderColor;
	private Color fontColor;

	public Circulo(Integer searchKey) {
		this.searchKey = searchKey;
		this.backgroundColor = WHITE_COLOR;
	}
	
	public Circulo(Integer searchKey, Point2D point) {
		this.searchKey = searchKey;
		this.point = point;
		this.backgroundColor = ORANGE_COLOR;
		this.borderColor = BLACK_COLOR;
		this.fontColor = WHITE_COLOR;
	}

	public void draw(GraphicsContext gc) {
		gc.setLineWidth(3); 
		// Create a circle 
		gc.setFill(backgroundColor);
		gc.fillOval(point.getX()-RADIUS, point.getY()-RADIUS, 2*RADIUS, 2*RADIUS);
		// Outline the circle border
		gc.setStroke(borderColor);
		gc.strokeOval(point.getX()-RADIUS, point.getY()-RADIUS, 2*RADIUS, 2*RADIUS);
		// Draw the id number inside the circle
		gc.setFont(font);
		gc.setFill(fontColor);
		gc.fillText(getStringKey(), point.getX() - (fm.computeStringWidth(getStringKey())/2), point.getY() + (fm.ascent/4));
	}

	private String getStringKey() {
		return Integer.toString(getSearchKey());
	}
	
	public Integer getSearchKey() {
		return this.searchKey;
	}
	
	public Point2D getPoint() {
		return point;
	}
	
	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	public void setHighlight(boolean highlight) {
		if(highlight) {
			this.fontColor = WHITE_COLOR;
			this.backgroundColor = BLUE_COLOR;
			this.borderColor = ORANGE_COLOR;
		} 
		else {
			this.fontColor = WHITE_COLOR;
			this.backgroundColor = ORANGE_COLOR;
			this.borderColor = BLACK_COLOR;
		}
	}

	/**
	 * Overrides the default toString method and gets the String representation
	 * of a circle.
	 * @return A String representation of the circle object.
	 */
	@Override
	public String toString() {
		return "Search Key# " + searchKey  + 
				" (x,y) = ("  + point.getX() + ", " + point.getY() + ")";
	}
}
