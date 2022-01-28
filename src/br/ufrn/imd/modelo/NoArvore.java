package br.ufrn.imd.modelo;

public class NoArvore {
	public Circulo circuloRaiz;
	public NoArvore pai;
	public NoArvore esquerdo;
	public NoArvore direito;
	public int altura;
	public boolean realce;
	
	public NoArvore() {};
	
	public NoArvore(Circulo rootCircle) {
		this.circuloRaiz = rootCircle;
		this.esquerdo = null;
		this.direito = null;
		altura = 1;
	}

}
