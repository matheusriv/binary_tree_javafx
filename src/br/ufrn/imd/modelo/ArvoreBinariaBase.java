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
	
	public void makeEmpty() {
		root = null;
	}

	public NoArvore getRoot() {
		return root;
	}
}
