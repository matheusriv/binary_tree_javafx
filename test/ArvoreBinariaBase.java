package br.ufrn.imd.modelo;

import br.ufrn.imd.controle.ArvoreException;

/**
 * An abstract base class for a binary tree.
 * @author Eric Canull
 */
public abstract class ArvoreBinariaBase {

	/**
	 * Inherited by the concrete BST class.
	 */
	public NoArvore root;
	
	/**
	 * An abstract base class for the BST.
	 */
	public ArvoreBinariaBase() {
		root = null;
	}
	
	/**
	 * An abstract base class for the BST.
	 * @param rootCircle
	 * @Overload Default constructor
	 */
	public ArvoreBinariaBase(Circulo rootCircle) {
		root = new NoArvore(rootCircle);
	}
	
	/**
	 * Checks if the tree is empty.
	 * @return <code>true</code> if the tree has items
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Makes the tree empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Gets the root item.
	 * @return A shape.Circle object representing the tree root
	 * @throws ArvoreException
	 */
	public NoArvore getRoot() throws ArvoreException {
		if (root == null) {
			throw new ArvoreException("tree.TreeException: Empty Tree");
		}
		
		return root;
	}
	
	/**
	 * Sets the root item. 
	 * @param newCircle a shape.Circle object with a tree search key
	 */
	public abstract void setRootItem(Circulo newCircle);

}