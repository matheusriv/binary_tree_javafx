package br.ufrn.imd.modelo;

import java.util.Objects;

import br.ufrn.imd.controle.ArvoreException;

public final class ArvoreBinariaBusca extends ArvoreBinariaBase {

	public ArvoreBinariaBusca() {
		this.root = null;
	}

	public ArvoreBinariaBusca(Circulo circuloRaiz) {
		super(circuloRaiz);
	}

	public void insertItem(Circulo newCircle) {
		root = insertItem(root, newCircle);
	}
	
	protected NoArvore insertItem(NoArvore tNode, Circulo newCircle) {
		NoArvore newSubtree;
		
		if (tNode == null) {
			tNode = new NoArvore(newCircle);
			return tNode;
		}
		
		Circulo nodeItem = tNode.circuloRaiz;

		if(Objects.equals(newCircle.getSearchKey(), nodeItem.getSearchKey())) {
		    return tNode;
        }

		if (newCircle.getSearchKey() < nodeItem.getSearchKey()) {
			newSubtree = insertItem(tNode.esquerdo, newCircle);
			tNode.esquerdo = newSubtree;
			return tNode;
		}
		
		newSubtree = insertItem(tNode.direito, newCircle);
		tNode.direito = newSubtree;
		return tNode;
	}
	
	public Integer retrieveItem(Integer searchKey) {
		return retrieveItem(root, searchKey);
	}
	
	
	protected Integer retrieveItem(NoArvore tNode, Integer searchKey) {
		Integer treeItem;
		if (tNode == null) {
			treeItem = null;

		} else {
			tNode.realce = true;
			Circulo nodeItem = tNode.circuloRaiz;
			if (Objects.equals(searchKey, nodeItem.getSearchKey())) {
				tNode.realce = true;
				treeItem = tNode.circuloRaiz.getSearchKey();
			} else if (searchKey < nodeItem.getSearchKey()) {
				tNode.esquerdo.realce = true;
				treeItem = retrieveItem(tNode.esquerdo, searchKey);
			} else {
				tNode.direito.realce = true;
				treeItem = retrieveItem(tNode.direito, searchKey);
			}
		}

		return treeItem;
	}
	
	public void deleteItem(Integer searchKey) throws ArvoreException {
		root = deleteItem(root, searchKey);
	}
	
	protected NoArvore deleteItem(NoArvore tNode, Integer searchKey) {
		NoArvore newSubtree;
		
		if (tNode == null) {
			throw new ArvoreException("tree.TreeException: Item not found");
		}
		
		Circulo nodeItem = tNode.circuloRaiz;
		if (Objects.equals(searchKey, nodeItem.getSearchKey())) {
			tNode = deleteNode(tNode);

		} else if (searchKey < nodeItem.getSearchKey()) {
			newSubtree = deleteItem(tNode.esquerdo, searchKey);
			tNode.esquerdo = newSubtree;
		}

		else {
			newSubtree = deleteItem(tNode.direito, searchKey);
			tNode.direito = newSubtree;
		}

		return tNode;
	}
	
	protected NoArvore deleteNode(NoArvore tNode) {

		Circulo replacementItem;

		if ((tNode.esquerdo == null) && (tNode.direito == null)) {
			return null;
		}

		else if (tNode.esquerdo == null) {
			return tNode.direito;
		}

		else if (tNode.direito == null) {
			return tNode.esquerdo;
		} else {

			replacementItem = findLeftmost(tNode.direito);
			tNode.circuloRaiz = replacementItem;
			tNode.direito = deleteLeftmost(tNode.direito);
			return tNode;
		}
	}

	protected Circulo findLeftmost(NoArvore tNode) {
		if (tNode.esquerdo == null) {
			return tNode.circuloRaiz;
		}
		return findLeftmost(tNode.esquerdo);
	}
	
	protected NoArvore deleteLeftmost(NoArvore tNode) {
		if (tNode.esquerdo == null) {
			return tNode.direito;
		}
		tNode.esquerdo = deleteLeftmost(tNode.esquerdo);
		return tNode;
	}
	
	public void setResetColor(NoArvore tNode) {
		 resetColor(tNode);
	}
	
	
	protected void resetColor(NoArvore tNode) {
		if (tNode != null) {
			tNode.realce = false;

			if (tNode.esquerdo != null) {
				tNode.esquerdo.realce = false;
			}

			if (tNode.direito != null) {
				tNode.direito.realce = false;
			}
			resetColor(tNode.esquerdo);
			resetColor(tNode.direito);
		}
	}

	public int getHeight(NoArvore root) {
		if (root == null)
			return 0;
		return Math.max(getHeight(root.esquerdo), getHeight(root.direito)) + 1;
	}
	
	public int getSize(NoArvore root) {
		if (root == null)
			return 0;
		return (getSize(root.esquerdo) + getSize(root.direito)) + 1;
	}
	
	@Override
	public void setRootItem(Circulo newItem) {
		root = new NoArvore(newItem);
	}
}
