package br.ufrn.imd.modelo;

import java.util.Objects;
import br.ufrn.imd.controle.ArvoreException;

public class ArvoreBinariaBusca {
	
	public NoArvore root;
	
	public ArvoreBinariaBusca() {
		root = null;
	}
	
	public ArvoreBinariaBusca(Circulo rootCircle) {
		root = new NoArvore(rootCircle);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void makeEmpty() {
		root = null;
	}

	public NoArvore getRoot() throws ArvoreException {
		if(root == null) {
			throw new ArvoreException("Árvore Vazia");
		}
		return root;
	}
	
	public void insert(Circulo newCircle) {
		root = insertItem(root, newCircle);
	}
	
	protected NoArvore insertItem(NoArvore tNo, Circulo newCircle) {
		NoArvore newSubTree;
		
		if(tNo == null) {
			tNo = new NoArvore(newCircle);
			return tNo;
		}
		
		Circulo nodeItem = tNo.circuloRaiz;

		if(Objects.equals(newCircle.getSearchKey(), nodeItem.getSearchKey())) {
		    return tNo;
        }

		if(newCircle.getSearchKey() < nodeItem.getSearchKey()) {
			newSubTree = insertItem(tNo.esquerdo, newCircle);
			tNo.esquerdo = newSubTree;
			return tNo;
		}
		
		newSubTree = insertItem(tNo.direito, newCircle);
		tNo.direito = newSubTree;
		return tNo;
	}
	
	public Integer search(Integer searchKey) {
		return retrieveItem(root, searchKey);
	}
	
	
	protected Integer retrieveItem(NoArvore tNo, Integer searchKey) {
		Integer treeItem;
		if (tNo == null) {
			treeItem = null;
		} 
		else {
			tNo.realce = true;
			Circulo nodeItem = tNo.circuloRaiz;
			if(Objects.equals(searchKey, nodeItem.getSearchKey())) {
				tNo.realce = true;
				treeItem = tNo.circuloRaiz.getSearchKey();
			} 
			else if(searchKey < nodeItem.getSearchKey()) {
				tNo.esquerdo.realce = true;
				treeItem = retrieveItem(tNo.esquerdo, searchKey);
			} 
			else {
				tNo.direito.realce = true;
				treeItem = retrieveItem(tNo.direito, searchKey);
			}
		}
		return treeItem;
	}
	
	public void delete(Integer searchKey) throws ArvoreException {
		root = deleteItem(root, searchKey);
	}
	
	protected NoArvore deleteItem(NoArvore tNo, Integer searchKey) {
		NoArvore newSubTree;
		
		if(tNo == null) {
			throw new ArvoreException("Item não achado");
		}
		
		Circulo nodeItem = tNo.circuloRaiz;
		if(Objects.equals(searchKey, nodeItem.getSearchKey())) {
			tNo = deleteNode(tNo);

		} 
		else if(searchKey < nodeItem.getSearchKey()) {
			newSubTree = deleteItem(tNo.esquerdo, searchKey);
			tNo.esquerdo = newSubTree;
		}
		else {
			newSubTree = deleteItem(tNo.direito, searchKey);
			tNo.direito = newSubTree;
		}

		return tNo;
	}
	
	protected NoArvore deleteNode(NoArvore tNo) {
		Circulo replacementItem;

		if((tNo.esquerdo == null) && (tNo.direito == null)) {
			return null;
		}
		else if(tNo.esquerdo == null) {
			return tNo.direito;
		}
		else if(tNo.direito == null) {
			return tNo.esquerdo;
		} 
		else {
			replacementItem = findLeftmost(tNo.direito);
			tNo.circuloRaiz = replacementItem;
			tNo.direito = deleteLeftmost(tNo.direito);
			return tNo;
		}
	}

	protected Circulo findLeftmost(NoArvore tNo) {
		if(tNo.esquerdo == null) {
			return tNo.circuloRaiz;
		}
		return findLeftmost(tNo.esquerdo);
	}
	
	protected NoArvore deleteLeftmost(NoArvore tNo) {
		if(tNo.esquerdo == null) {
			return tNo.direito;
		}
		tNo.esquerdo = deleteLeftmost(tNo.esquerdo);
		return tNo;
	}
	
	public void setResetColor(NoArvore tNo) {
		 resetColor(tNo);
	}
	
	protected void resetColor(NoArvore tNo) {
		if(tNo != null) {
			tNo.realce = false;

			if(tNo.esquerdo != null) {
				tNo.esquerdo.realce = false;
			}

			if(tNo.direito != null) {
				tNo.direito.realce = false;
			}
			resetColor(tNo.esquerdo);
			resetColor(tNo.direito);
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
	
	public void setRootItem(Circulo newItem) {
		root = new NoArvore(newItem);
	}
	

}
