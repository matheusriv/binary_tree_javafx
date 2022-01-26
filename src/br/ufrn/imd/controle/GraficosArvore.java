package br.ufrn.imd.controle;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import br.ufrn.imd.modelo.*;

import java.util.Objects;

public final class GraficosArvore extends Canvas {

	private static final Integer[] NUMBERS_ARRAY = { 50, 25, 30, 12, 10, 75, 70, 80, 110 };

	private ArvoreBinariaBusca tree;  	
	private ArvoreIterator treeIterator;  
	private Circulo insertCircle;        
	private int maxTreeHeight; 			

	public GraficosArvore() {
		widthProperty().addListener(evt -> drawTree());
		heightProperty().addListener(evt -> drawTree());

		createTree();
	}
	
	public void setTree(ArvoreBinariaBusca root) {  
		tree = root; 
	}

	public void createTree() {
		tree = new ArvoreBinariaBusca(); 
		maxTreeHeight = 7;

		for(Integer number : NUMBERS_ARRAY) {
			Circulo circle = new Circulo(number);
			tree.insert(circle);
		}

		drawTree();
	}

	public void search(Integer searchKey) {
		try { 
			tree.search(searchKey); 
		} catch (NullPointerException e) { 
			tree.setResetColor(tree.root); 
		}

		drawTree();
	}

	public String printTree() {
		StringBuilder outputString = new StringBuilder();
		// Add the next tree iterator to the output
		while(treeIterator.hasNext()) {
			outputString.append(treeIterator.next()).append(" ");
		}

		return outputString.toString(); // return the output string
	}

	public void setPreorder() {
		treeIterator = new ArvoreIterator(tree);
		treeIterator.setPreorder();
	}

	public void setInorder() {
		treeIterator = new ArvoreIterator(tree);
		treeIterator.setInorder();
	}

	public void setPostorder() {
		treeIterator = new ArvoreIterator(tree);
		treeIterator.setPostorder();
	}

	public void insert(Integer searchKey) {
		insertCircle = new Circulo(searchKey);
		tree.insert(insertCircle);
		drawTree();
		if(tree.getHeight(tree.getRoot()) == maxTreeHeight) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Altura máxima.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
	}

	public void delete(Integer searchKey) {
		try {
			tree.delete(searchKey);
		} catch (ArvoreException e) {} ;
		
		drawTree();
	}

	public void makeEmpty() {
		tree.makeEmpty();
		maxTreeHeight = 7;
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
	}

	protected void drawTree() {
		double width = getWidth();
		double height = getHeight();

		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);

		// If the tree is not empty; draw the lines and circles
		if (tree.root != null) {
			int treeHeight = tree.getHeight(tree.root);
			// Get the tree height
			drawTree(gc, tree.getRoot(), 0, this.getWidth(), 0, this.getHeight() / treeHeight);
			drawCircles(gc, tree.getRoot(), 0, this.getWidth(), 0, this.getHeight() / treeHeight);
		}
	}
	
	protected void drawTree(GraphicsContext gc, NoArvore treeNode, double xMin, double xMax, double yMin, double yMax) {
		Point2D linePoint1; 	
		Point2D linePoint2;   
		Linha newLine = new Linha();  
		
		// If left node is not null then draw a line to it
		if(treeNode.esquerdo != null) {
			newLine.setHighlighter(false);
			
			if(treeNode.esquerdo.realce) {
				newLine.setHighlighter(true);
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
		if(treeNode.direito != null) {
			newLine.setHighlighter(false);
			
			// Color the line if the tree circle is flagged for color 
			if(treeNode.direito.realce) {
				newLine.setHighlighter(true);
			}
	
			// Determine the start and end points of the line
			linePoint1 = new Point2D((xMin+xMax)/2, yMin+yMax/2);
			linePoint2 = new Point2D((xMax + (xMin+xMax)/2) / 2, yMin+yMax + yMax/2);
			newLine.setPoint(linePoint1, linePoint2);
			newLine.draw(gc);// Draw the line
		
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
			treeNode.circuloRaiz.setHighlight(true); // Highlight turned on
			treeNode.circuloRaiz.setPoint(point); 	
		} 
		else {
			treeNode.circuloRaiz.setHighlight(false); // Highlight turned off
			treeNode.circuloRaiz.setPoint(point);
		}

		// Draw the circle
		treeNode.circuloRaiz.draw(gc);

		// Recurse left circles
		if(treeNode.esquerdo != null) {
			drawCircles(gc, treeNode.esquerdo, xMin, (xMin+xMax)/2, yMin+yMax, yMax);
		}

		// Recurse right circles
		if(treeNode.direito != null) {
			drawCircles(gc, treeNode.direito, (xMin+xMax)/2, xMax, yMin+yMax, yMax);
		}
	}

	public void clearCanvas() {
		getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
}
	

