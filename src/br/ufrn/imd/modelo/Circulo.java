package br.ufrn.imd.modelo;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class Circulo {
	
	final Font font = Font.font("Cooper Black", FontWeight.BOLD, 16);
	final FontMetrics fm = new FontMetrics(font);
	
	private static Color UI_DEFAULT_COLOR = Color.rgb(99, 99, 99);  // grey
	private static Color HIGHLIGHT_COLOR = Color.rgb(49, 116, 222); // blue
	private static Color WHITE_COLOR = Color.rgb(252, 252, 252);    // white

	public static int RADIUS = 20;
	private Integer searchKey;

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
		this.backgroundColor = HIGHLIGHT_COLOR;
		this.borderColor = UI_DEFAULT_COLOR;
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
		gc.setFill(getFontColor());
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
	
	public int getRadius() {
		return RADIUS;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	public Color getFontColor() {
		return this.fontColor;
	}
	
	public void setHighlighter(boolean highlight) {
		if(highlight) {
			this.fontColor = HIGHLIGHT_COLOR;
			this.backgroundColor = Color.rgb(155, 244, 167); // green
			this.borderColor = HIGHLIGHT_COLOR;
		} 
		else {
			this.fontColor = WHITE_COLOR;
			this.backgroundColor = HIGHLIGHT_COLOR;
			this.borderColor = UI_DEFAULT_COLOR;
		}
	}

	@Override
	public String toString() {
		return "Search Key# " + searchKey  + 
				" (x,y) = ("  + point.getX() + ", " + point.getY() + ")";
	}
}
