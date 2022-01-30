package br.ufrn.imd.modelo;

public class NoArvore {
	public Circulo circuloRaiz;
	public NoArvore esquerdo;
	public NoArvore direito;
	public NoArvore pai;
	public int cor;
	public int altura;
	public boolean realce;
	
	public NoArvore() {};
	
	public NoArvore(Circulo circuloRaiz) {
		this.circuloRaiz = circuloRaiz;
		this.esquerdo = null;
		this.direito = null;
		altura = 1;
	}

}
