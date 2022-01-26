package br.ufrn.imd.modelo;

public class NoArvore {
	public Circulo circuloRaiz;
	public NoArvore esquerdo;
	public NoArvore direito;
	public boolean realce;
	
	public NoArvore(Circulo rootCircle) {
		this.circuloRaiz = rootCircle;
		this.esquerdo = null;
		this.direito = null;
	}

}
