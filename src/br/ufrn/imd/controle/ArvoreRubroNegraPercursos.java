package br.ufrn.imd.controle;

import java.util.LinkedList;
import br.ufrn.imd.modelo.ArvoreRubroNegra;
import br.ufrn.imd.modelo.NoArvore;

public final class ArvoreRubroNegraPercursos {
	
	private ArvoreRubroNegra redBlackTree;
	private NoArvore currentNode;
	private LinkedList<NoArvore> list;
	
	public ArvoreRubroNegraPercursos(ArvoreRubroNegra redBlackTree) {
		this.redBlackTree = redBlackTree;
		currentNode = null;
		list = new LinkedList<>();
	}
	
	public String getStringTraversal() {
		StringBuilder output = new StringBuilder();
		while(!list.isEmpty()) {
			output.append(next()).append(" ");
		}
		return output.toString();
	}
	
	public Integer next() {
		currentNode = list.remove();
		return currentNode.circuloRaiz.getSearchKey();
	}
	
	/**
	 * Sets the tree traversal to in-order
	 */
	public void setPreorder() {
		list.clear();
		preorder(redBlackTree.root);
	}
	
	private void preorder(NoArvore treeNode) {
		if(treeNode != redBlackTree.TNULL) {
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
		inorder(redBlackTree.root);
	}
	
	private void inorder(NoArvore treeNode) {
		if(treeNode != redBlackTree.TNULL) {
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
		postorder(redBlackTree.root);
	}
	
	private void postorder(NoArvore treeNode) {
		if(treeNode != redBlackTree.TNULL) {
			postorder(treeNode.esquerdo);
			postorder(treeNode.direito);
			list.add(treeNode);
		}
	}
}