package br.ufrn.imd.controle;

import java.util.LinkedList;
import br.ufrn.imd.modelo.ArvoreBinariaBase;
import br.ufrn.imd.modelo.NoArvore;

public final class ArvorePercursos {
	
	private ArvoreBinariaBase binaryTree;
	private NoArvore currentNode;
	private LinkedList<NoArvore> list;
	
	public ArvorePercursos(ArvoreBinariaBase binaryTree) {
		this.binaryTree = binaryTree;
		currentNode = null;
		list = new LinkedList<>();
	}
	
	public String getStringTraversal() {
		StringBuilder output = new StringBuilder();
		while(!list.isEmpty()) {
			currentNode = list.remove();
			output.append(currentNode.circuloRaiz.getSearchKey()).append(" ");
		}
		return output.toString();
	}
	
	/**
	 * Sets the tree traversal to in-order
	 */
	public void setPreorder() {
		list.clear();
		preorder(binaryTree.root);
	}
	
	private void preorder(NoArvore treeNode) {
		if(treeNode != null) {
			list.add(treeNode);
			preorder(treeNode.esquerdo);
			preorder(treeNode.direito);
		}
	}
	
	/**
	 * Sets the tree traversal to in-order
	 */
	public void setInorder() {
		list.clear();
		inorder(binaryTree.root);
	}
	
	private void inorder(NoArvore treeNode) {
		if(treeNode != null) {
			inorder(treeNode.esquerdo);
			list.add(treeNode);
			inorder(treeNode.direito);
		}
	}
	
	/**
	 * Sets the tree to traverse in post-order
	 */
	public void setPostorder() {
		list.clear();
		postorder(binaryTree.root);
	}
	
	private void postorder(NoArvore treeNode) {
		if(treeNode != null) {
			postorder(treeNode.esquerdo);
			postorder(treeNode.direito);
			list.add(treeNode);
		}
	}
}