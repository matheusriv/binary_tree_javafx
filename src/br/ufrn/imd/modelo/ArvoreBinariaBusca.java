package br.ufrn.imd.modelo;

import java.util.Objects;

public class ArvoreBinariaBusca extends ArvoreBinariaBase {
	
	public ArvoreBinariaBusca() {
		this.root = null;
	}

	public ArvoreBinariaBusca(Circulo rootCircle) {
		super(rootCircle);
	}
	
	public void insert(Circulo newCircle) {
		root = insertItem(root, newCircle);
	}
	
	protected NoArvore insertItem(NoArvore root, Circulo newCircle) {
		if(root == null) {
			root = new NoArvore(newCircle);
			return root;
		}

		if(newCircle.getSearchKey() < root.circuloRaiz.getSearchKey()) {
			root.esquerdo = insertItem(root.esquerdo, newCircle);
		} 
		else if(newCircle.getSearchKey() > root.circuloRaiz.getSearchKey()) {
			root.direito = insertItem(root.direito, newCircle);
		}

		return root;
	}
	
	public Integer search(Integer searchKey) {
		return retrieveItem(root, searchKey);
	}
	
	
	protected Integer retrieveItem(NoArvore root, Integer searchKey) {
		Integer treeItem;
		if(root == null) {
			treeItem = null;
		} 
		else {
			if(Objects.equals(searchKey, root.circuloRaiz.getSearchKey())) {
				root.realce = true;
				treeItem = root.circuloRaiz.getSearchKey();
			} 
			else if(searchKey < root.circuloRaiz.getSearchKey()) {
				root.esquerdo.realce = true;
				treeItem = retrieveItem(root.esquerdo, searchKey);
			} 
			else {
				root.direito.realce = true;
				treeItem = retrieveItem(root.direito, searchKey);
			}
		}
		return treeItem;
	}
	
	public void delete(Integer searchKey) {
		root = deleteItem(root, searchKey);
	}
	
	protected NoArvore deleteItem(NoArvore root, Integer searchKey) {
		if (root == null)
			return root;
	    // Find the node to be deleted
	    if (searchKey < root.circuloRaiz.getSearchKey())
	    	root.esquerdo = deleteItem(root.esquerdo, searchKey);
	    else if (searchKey > root.circuloRaiz.getSearchKey())
	    	root.direito = deleteItem(root.direito, searchKey);
	    else {
	    	// If the node is with only one child or no child
	    	if (root.esquerdo == null)
	    		return root.direito;
	    	else if (root.direito == null)
	    		return root.esquerdo;
	    	// If the node has two children
	    	// Place the inorder successor in position of the node to be deleted
	    	root.circuloRaiz.setSearchKey(minValue(root.direito));
	    	// Delete the inorder successor
	    	root.direito = deleteItem(root.direito, root.circuloRaiz.getSearchKey());
	    }
	    return root;
	  }

	int minValue(NoArvore root) {
		int minv = root.circuloRaiz.getSearchKey();
		while (root.esquerdo != null) {
			minv = root.esquerdo.circuloRaiz.getSearchKey();
			root = root.esquerdo;
		}
		return minv;
	 }
	
	public void setResetColor(NoArvore root) {
		 resetColor(root);
	}
	
	protected void resetColor(NoArvore root) {
		if(root != null) {
			root.realce = false;

			if(root.esquerdo != null) {
				root.esquerdo.realce = false;
			}

			if(root.direito != null) {
				root.direito.realce = false;
			}
			resetColor(root.esquerdo);
			resetColor(root.direito);
		}
	}

	public int getHeight(NoArvore root) {
		if(root == null)
			return 0;
		return Math.max(getHeight(root.esquerdo), getHeight(root.direito)) + 1;
	}
	
	public int getSize(NoArvore root) {
		if(root == null)
			return 0;
		return (getSize(root.esquerdo) + getSize(root.direito)) + 1;
	}
	
}
