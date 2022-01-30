package br.ufrn.imd.modelo;

public class ArvoreBinariaBase {
	
	public NoArvore root;
	
	public ArvoreBinariaBase() {
		root = null;
	}
	
	public ArvoreBinariaBase(Circulo rootCircle) {
		root = new NoArvore(rootCircle);
	}
	
	public boolean isEmpty() {
		return root == null;
	}

	public NoArvore getRoot() {
		return root;
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
	
	/**
	 * Gets the height of the tree
	 * @param root
	 */
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
