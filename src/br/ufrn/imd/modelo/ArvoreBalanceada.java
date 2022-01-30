package br.ufrn.imd.modelo;

import java.util.Objects;

public class ArvoreBalanceada extends ArvoreBinariaBase {
	
	public ArvoreBalanceada() {
		this.root = null;
	}

	public ArvoreBalanceada(Circulo rootCircle) {
		super(rootCircle);
	}

	int altura(NoArvore N) {
		if (N == null)
			return 0;
		return N.altura;
	}

	protected NoArvore rightRotate(NoArvore y) {
		NoArvore x = y.esquerdo;
		NoArvore T2 = x.direito;
		x.direito = y;
		y.esquerdo = T2;
		y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
		x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
		return x;
	}

	protected NoArvore leftRotate(NoArvore x) {
		NoArvore y = x.direito;
		NoArvore T2 = y.esquerdo;
		y.esquerdo = x;
		x.direito = T2;
		x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
		y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
		return y;
	}

	// Get balance factor of a NoArvore
	protected int getBalanceFactor(NoArvore N) {
		if(N == null)
			return 0;
		return altura(N.esquerdo) - altura(N.direito);
	}
	
	public void insert(Circulo newCircle) {
		root = insertItem(root, newCircle);
	} 

	protected NoArvore insertItem(NoArvore root, Circulo newCircle) {
		// Find the position and insert the NoArvore
		if(root == null)
			return (new NoArvore(newCircle));
		if(newCircle.getSearchKey() < root.circuloRaiz.getSearchKey())
			root.esquerdo = insertItem(root.esquerdo, newCircle);
		else if(newCircle.getSearchKey() > root.circuloRaiz.getSearchKey())
			root.direito = insertItem(root.direito, newCircle);
		else
			return root;

		// Update the balance factor of each NoArvore
		// And, balance the tree
		root.altura = 1 + Math.max(altura(root.esquerdo), altura(root.direito));
		int balanceFactor = getBalanceFactor(root);
		if(balanceFactor > 1) {
			if(newCircle.getSearchKey() < root.esquerdo.circuloRaiz.getSearchKey()) {
				return rightRotate(root);
			} else if(newCircle.getSearchKey() > root.esquerdo.circuloRaiz.getSearchKey()) {
				root.esquerdo = leftRotate(root.esquerdo);
				return rightRotate(root);
			}
		}
		if(balanceFactor < -1) {
			if(newCircle.getSearchKey() > root.direito.circuloRaiz.getSearchKey()) {
				return leftRotate(root);
			} else if(newCircle.getSearchKey() < root.direito.circuloRaiz.getSearchKey()) {
				root.direito = rightRotate(root.direito);
				return leftRotate(root);
			}
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
	    if(root == null)
	    	return root;
	    if(searchKey < root.circuloRaiz.getSearchKey())
	    	root.esquerdo = deleteItem(root.esquerdo, searchKey);
	    else if(searchKey > root.circuloRaiz.getSearchKey())
	    	root.direito = deleteItem(root.direito, searchKey);
	    else {
	    	if((root.esquerdo == null) || (root.direito == null)) {
	    		NoArvore temp = null;
	    		if(temp == root.esquerdo)
	    			temp = root.direito;
	    		else
	    			temp = root.esquerdo;
	    		if(temp == null) {
	    			temp = root;
	    			root = null;
	    		} else
	    			root = temp;
	    	} else {
	    		NoArvore temp = nodeWithMimumValue(root.direito);
	    		root.circuloRaiz.setSearchKey(temp.circuloRaiz.getSearchKey());
	    		root.direito = deleteItem(root.direito, temp.circuloRaiz.getSearchKey());
	    	}
	    }
	    if(root == null)
	      return root;
		
		// Update the balance factor of each NoArvore and balance the tree
		root.altura = Math.max(altura(root.esquerdo), altura(root.direito)) + 1;
		int balanceFactor = getBalanceFactor(root);
		if(balanceFactor > 1) {
			if(getBalanceFactor(root.esquerdo) >= 0) {
				return rightRotate(root);
			} else {
				root.esquerdo = leftRotate(root.esquerdo);
				return rightRotate(root);
			}
		}
		if(balanceFactor < -1) {
			if(getBalanceFactor(root.direito) <= 0) {
				return leftRotate(root);
			} else {
				root.direito = rightRotate(root.direito);
				return leftRotate(root);
			}
		}
		return root;
	}
	
	protected NoArvore nodeWithMimumValue(NoArvore NoArvore) {
		NoArvore current = NoArvore;
		while(current.esquerdo != null)
			current = current.esquerdo;
		return current;
	}
	
	public void makeEmpty() {
		root = null;
	}
	
}