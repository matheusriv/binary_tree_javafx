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
	private static final Color WHITE_COLOR = Color.rgb(255, 255, 255);
	private static final Color ORANGE_COLOR = Color.rgb(255, 95, 41); 
	private static final Color BLUE_COLOR = Color.rgb(13, 126, 255);
	private static final Color RED_COLOR = Color.rgb(255, 48, 48);

	public static final int RAIO = 20;
	private Integer searchKey;
	
	private Point2D point;
	private Color backgroundColor;
	private Color borderColor;
	private Color fontColor;

	public Circulo(Integer searchKey) {
		this.searchKey = searchKey;
		this.fontColor = WHITE_COLOR;
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
		gc.fillOval(point.getX()-RAIO, point.getY()-RAIO, 2*RAIO, 2*RAIO);
		// Outline the circle border
		gc.setStroke(borderColor);
		gc.strokeOval(point.getX()-RAIO, point.getY()-RAIO, 2*RAIO, 2*RAIO);
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
	
	public void setSearchKey(Integer searchKey) {
		this.searchKey = searchKey;
	}
	
	public Point2D getPoint() {
		return point;
	}
	
	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	public void setRedBlackBackground(int color) {
		if(color == 1) {
			this.backgroundColor = RED_COLOR;
		}
		else {
			this.backgroundColor = BLACK_COLOR;
		}
	}
	
	public void setCircleHighlight(boolean highlight) {
		if(highlight) {
			this.backgroundColor = BLUE_COLOR;
			this.borderColor = ORANGE_COLOR;
		} 
		else {
			this.backgroundColor = ORANGE_COLOR;
			this.borderColor = BLACK_COLOR;
		}
	}
	
}
