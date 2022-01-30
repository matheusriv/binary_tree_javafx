package br.ufrn.imd.controle;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import br.ufrn.imd.modelo.*;

import java.util.Objects;

public final class GraficosRubroNegra extends Canvas {

	private ArvoreRubroNegra redBlackTree;  	
	private ArvoreRubroNegraPercursos redBlackIterator;  
	private Circulo insertCircle;        
	private int maxTreeHeight; 			

	public GraficosRubroNegra() {
		widthProperty().addListener(evt -> drawTree());
		heightProperty().addListener(evt -> drawTree());

		createTree();
	}

	public void createTree() {
		redBlackTree = new ArvoreRubroNegra(); 
		redBlackIterator = new ArvoreRubroNegraPercursos(redBlackTree);
		maxTreeHeight = 7;
		drawTree();
	}

	public void insert(Integer searchKey) {
		insertCircle = new Circulo(searchKey);
		redBlackTree.insert(insertCircle); 
		drawTree();
		if((redBlackTree.getHeight(redBlackTree.root))-1 == maxTreeHeight) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Altura máxima.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
	}
	
	public boolean search(Integer searchKey) {
		Integer number = null;
		try { 
			number = redBlackTree.search(searchKey); 
		} catch (NullPointerException e) { 
			redBlackTree.setResetColor(redBlackTree.root); 
		}

		drawTree();
		
		if(number != null) return true;
		return false;
	}

	public void delete(Integer searchKey) {
		redBlackTree.delete(searchKey);
		drawTree();
	}

	protected void drawTree() {
		double width = getWidth();
		double height = getHeight();

		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);

		if(redBlackTree.root != redBlackTree.TNULL) {
			int treeHeight = (redBlackTree.getHeight(redBlackTree.root))-1;
			// Get the tree height
			drawTree(gc, redBlackTree.getRoot(), 0, this.getWidth(), 0, this.getHeight()/treeHeight);
			drawCircles(gc, redBlackTree.getRoot(), 0, this.getWidth(), 0, this.getHeight()/treeHeight);
		}
	}
	
	protected void drawTree(GraphicsContext gc, NoArvore treeNode, double xMin, double xMax, double yMin, double yMax) {
		Point2D linePoint1; 	
		Point2D linePoint2;   
		Linha newLine = new Linha();  
		
		// If left node is not null then draw a line to it
		if(treeNode.esquerdo != redBlackTree.TNULL) {
			newLine.setLineHighlight(false);
			
			// Color the line if the tree circle is flagged for color 
			if(treeNode.esquerdo.realce) {
				newLine.setLineHighlight(true);
			}
			
			// Determine the start and end points of the line
			linePoint1 = new Point2D(((xMin+xMax)/2), yMin+yMax/2);
			linePoint2 = new Point2D(((xMin+(xMin+xMax)/2)/2), yMin+yMax+yMax/2);
			newLine.setPoint(linePoint1, linePoint2);// Set the points
			newLine.draw(gc);// Draw the line
			
			// Recurse left circle nodes
			drawTree(gc, treeNode.esquerdo, xMin, (xMin+xMax)/2, yMin+yMax, yMax);
		}

		// If right node is not null then draw a line to it
		if(treeNode.direito != redBlackTree.TNULL) {
			newLine.setLineHighlight(false);
			
			// Color the line if the tree circle is flagged for color 
			if(treeNode.direito.realce) {
				newLine.setLineHighlight(true);
			}
			
			// Determine the start and end points of the line
			linePoint1 = new Point2D((xMin+xMax)/2, yMin+yMax/2);
			linePoint2 = new Point2D((xMax + (xMin+xMax)/2) / 2, yMin+yMax + yMax/2);
			newLine.setPoint(linePoint1, linePoint2);
			newLine.draw(gc); // Draw the line
		
			// Recurse right circle nodes
			drawTree(gc, treeNode.direito, (xMin+xMax)/2, xMax, yMin+yMax, yMax);
		}
	}

	public void drawCircles(GraphicsContext gc, NoArvore treeNode, double xMin, double xMax, double yMin, double yMax) {
		// Create a new point
		Point2D point = new Point2D((xMin+xMax)/2, yMin+yMax/2);

		// treeNodes are flagged for highlight: Search and insertion nodes
		if(treeNode.realce || Objects.equals(treeNode.circuloRaiz, insertCircle)) {
			insertCircle = null;	 // Reset insert circle
			treeNode.realce = false; // Reset highlight flag
			treeNode.circuloRaiz.setCircleHighlight(true); // Highlight turned on
			treeNode.circuloRaiz.setPoint(point); 	
		} 
		else {
			treeNode.circuloRaiz.setCircleHighlight(false); // Highlight turned off
			treeNode.circuloRaiz.setPoint(point);
		}

		treeNode.circuloRaiz.setRedBlackBackground(treeNode.cor);
		treeNode.circuloRaiz.draw(gc);

		// Recurse left circles
		if(treeNode.esquerdo != redBlackTree.TNULL) {
			drawCircles(gc, treeNode.esquerdo, xMin, (xMin+xMax)/2, yMin+yMax, yMax);
		}

		// Recurse right circles
		if(treeNode.direito != redBlackTree.TNULL) {
			drawCircles(gc, treeNode.direito, (xMin+xMax)/2, xMax, yMin+yMax, yMax);
		}
	}
	
	public String getStringIterator() {
		return redBlackIterator.getStringTraversal();
	}

	public void setPreorder() {
		redBlackIterator.setPreorder();
	}

	public void setInorder() {
		redBlackIterator.setInorder();
	}

	public void setPostorder() {
		redBlackIterator.setPostorder();
	}
	
	public void makeEmpty() {
		redBlackTree.makeEmpty();
		maxTreeHeight = 7;
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
	}

	public void clearCanvas() {
		getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
}
	

