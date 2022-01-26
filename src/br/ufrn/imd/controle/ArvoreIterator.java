package br.ufrn.imd.controle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import br.ufrn.imd.modelo.ArvoreBinariaBusca;
import br.ufrn.imd.modelo.NoArvore;

public final class ArvoreIterator implements Iterator<Integer> {
	
	private ArvoreBinariaBusca binaryTree;
	private NoArvore currentNode;
	private LinkedList<NoArvore> queue;
	
	public ArvoreIterator(ArvoreBinariaBusca binaryTree) {
		this.binaryTree = binaryTree;
		currentNode = null;
		queue = new LinkedList<>();
	}
	
	/**
	 * Determines if there are elements in the queue. 
	 * @Return true if the iteration has more elements
	 */
	public boolean hasNext() {
		return !queue.isEmpty();
	}
	
	/**
	 * Gets the next element in the queue.
	 * @return An <code>Integer</code> numbered search key.
	 */
	public Integer next() throws NoSuchElementException {
		try {
			currentNode = queue.remove();
			return currentNode.circuloRaiz.getSearchKey();
		} catch (QueueException e) {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Unsupported remove operation. Throws an exception when invoked.
	 */
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Sets the tree traversal to in-order
	 */
	public void setPreorder() {
		queue.clear();
		preorder(binaryTree.root);
	}
	
	/**
	 * Recursively traverses the tree in-order
	 * @param treeNode A tree with nodes
	 */
	private void preorder(NoArvore treeNode) {
		if (treeNode != null) {
			queue.add(treeNode);
			preorder(treeNode.esquerdo);
			preorder(treeNode.direito);
		}
	}
	
	/**
	 * Sets the tree traversal to in-order
	 */
	public void setInorder() {
		queue.clear();
		inorder(binaryTree.root);
	}
	
	/**
	 * Recursively traverses the tree in-order
	 * @param treeNode A tree with nodes
	 */
	private void inorder(NoArvore treeNode) {
		if (treeNode != null) {
			inorder(treeNode.esquerdo);
			queue.add(treeNode);
			inorder(treeNode.direito);
		}
	}
	
	/**
	 * Sets the tree to traverse in post-order
	 */
	public void setPostorder() {
		queue.clear();
		postorder(binaryTree.root);
	}
	
	/**
	 * Recursively traverses the tree post-order
	 * @param treeNode A tree with nodes
	 */
	private void postorder(NoArvore treeNode) {
		if (treeNode != null) {
			postorder(treeNode.esquerdo);
			postorder(treeNode.direito);
			queue.add(treeNode);
		}
	}
}