package br.ufrn.imd.modelo;

import java.util.Objects;

public class ArvoreRubroNegra extends ArvoreBinariaBase {
	
	public NoArvore TNULL;
	
	public ArvoreRubroNegra() {
		TNULL = new NoArvore();
		TNULL.cor = 0;
		TNULL.esquerdo = null;
		TNULL.direito = null;
		this.root = TNULL;
	}
	
	public void insert(Circulo newCircle) {
		NoArvore node = new NoArvore();
		node.pai = null;
		node.circuloRaiz = newCircle;
		node.esquerdo = TNULL;
		node.direito = TNULL;
		node.cor = 1;
		
		NoArvore y = null;
		NoArvore x = this.root;

		while(x != TNULL) {
			y = x;
			if(node.circuloRaiz.getSearchKey() < x.circuloRaiz.getSearchKey()) {
				x = x.esquerdo;
			} else {
				x = x.direito;
			}
		}

		node.pai = y;
		if(y == null) {
			root = node;
		} else if(node.circuloRaiz.getSearchKey() < y.circuloRaiz.getSearchKey()) {
			y.esquerdo = node;
		} else {
			y.direito = node;
		}

		if(node.pai == null) {
			node.cor = 0;
			return;
		}

		if(node.pai.pai == null) {
			return;
		}

		fixInsert(node);
	}
	
	public Integer search(Integer searchKey) {
		return retrieveItem(root, searchKey);
	}
	
	
	protected Integer retrieveItem(NoArvore root, Integer searchKey) {
		Integer treeItem;
		if(root == null) {
			treeItem = null;
		} else {
			if(Objects.equals(searchKey, root.circuloRaiz.getSearchKey())) {
				root.realce = true;
				treeItem = root.circuloRaiz.getSearchKey();
			} else if(searchKey < root.circuloRaiz.getSearchKey()) {
				root.esquerdo.realce = true;
				treeItem = retrieveItem(root.esquerdo, searchKey);
			} else {
				root.direito.realce = true;
				treeItem = retrieveItem(root.direito, searchKey);
			}
		}
		return treeItem;
	}
	
	public void delete(Integer searchKey) {
		deleteNodeHelper(this.root, searchKey);
	}

	// Balance the tree after deletion of a NoArvore
	private void fixDelete(NoArvore x) {
		NoArvore s;
		while(x != root && x.cor == 0) {
			if(x == x.pai.esquerdo) {
				s = x.pai.direito;
				if(s.cor == 1) {
					s.cor = 0;
					x.pai.cor = 1;
					leftRotate(x.pai);
					s = x.pai.direito;
				}

				if(s.esquerdo.cor == 0 && s.direito.cor == 0) {
					s.cor = 1;
					x = x.pai;
				} else {
					if(s.direito.cor == 0) {
						s.esquerdo.cor = 0;
						s.cor = 1;
						rightRotate(s);
						s = x.pai.direito;
					}

					s.cor = x.pai.cor;
					x.pai.cor = 0;
					s.direito.cor = 0;
					leftRotate(x.pai);
					x = root;
				}
			} else {
				s = x.pai.esquerdo;
				if(s.cor == 1) {
					s.cor = 0;
					x.pai.cor = 1;
					rightRotate(x.pai);
					s = x.pai.esquerdo;
				}

				if(s.direito.cor == 0 && s.direito.cor == 0) {
					s.cor = 1;
					x = x.pai;
				} else {
					if(s.esquerdo.cor == 0) {
						s.direito.cor = 0;
						s.cor = 1;
						leftRotate(s);
						s = x.pai.esquerdo;
					}
					
					s.cor = x.pai.cor;
					x.pai.cor = 0;
					s.esquerdo.cor = 0;
					rightRotate(x.pai);
					x = root;
				}
			}
		}
		x.cor = 0;
	}

	private void rbTransplant(NoArvore u, NoArvore v) {
		if(u.pai == null) {
			root = v;
		} else if(u == u.pai.esquerdo) {
			u.pai.esquerdo = v;
		} else {
			u.pai.direito = v;
		}
		v.pai = u.pai;
	}

	private void deleteNodeHelper(NoArvore node, Integer key) {
		NoArvore z = TNULL;
		NoArvore x, y;
		while(node != TNULL) {
			if(node.circuloRaiz.getSearchKey() == key) {
				z = node;
			}

			if(node.circuloRaiz.getSearchKey() <= key) {
				node = node.direito;
			} else {
				node = node.esquerdo;
			}
		}

		if(z == TNULL) {
			//System.out.println("Couldn't find key in the tree");
			return;
		}

		y = z;
		int yOriginalcor = y.cor;
		if(z.esquerdo == TNULL) {
			x = z.direito;
			rbTransplant(z, z.direito);
		} else if(z.direito == TNULL) {
			x = z.esquerdo;
			rbTransplant(z, z.esquerdo);
		} else {
			y = minimum(z.direito);
			yOriginalcor = y.cor;
			x = y.direito;
			if(y.pai == z) {
				x.pai = y;
			} else {
				rbTransplant(y, y.direito);
				y.direito = z.direito;
				y.direito.pai = y;
			}

			rbTransplant(z, y);
			y.esquerdo = z.esquerdo;
			y.esquerdo.pai = y;
			y.cor = z.cor;
		}
		if (yOriginalcor == 0) {
			fixDelete(x);
		}
	}

	// Balance the NoArvore after insertion
	private void fixInsert(NoArvore k) {
		NoArvore u;
		while(k.pai.cor == 1) {
			if(k.pai == k.pai.pai.direito) {
				u = k.pai.pai.esquerdo;
				if(u.cor == 1) {
					u.cor = 0;
					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					k = k.pai.pai;
				} else {
					if(k == k.pai.esquerdo) {
						k = k.pai;
						rightRotate(k);
					}
					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					leftRotate(k.pai.pai);
				}
			} else {
				u = k.pai.pai.direito;
				
				if(u.cor == 1) {
					u.cor = 0;
					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					k = k.pai.pai;
				} else {
					if(k == k.pai.direito) {
						k = k.pai;
						leftRotate(k);
					}
					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					rightRotate(k.pai.pai);
				}
			}
			if(k == root) {
				break;
			}
		}
		root.cor = 0;
	}
	
	public void printTree() {
		printHelper(this.root, "", true);
	}

	private void printHelper(NoArvore root, String indent, boolean last) {
		if(root != TNULL) {
			System.out.print(indent);
			if(last) {
				System.out.print("R----");
				indent += "   ";
			} else {
				System.out.print("L----");
				indent += "|  ";
			}
			String scor = root.cor == 1 ? "RED" : "BLACK";
			System.out.println(root.circuloRaiz.getSearchKey() + "(" + scor + ")");
			printHelper(root.esquerdo, indent, false);
			printHelper(root.direito, indent, true);
		}
	}

	public NoArvore minimum(NoArvore node) {
		while(node.esquerdo != TNULL) {
			node = node.esquerdo;
		}
		return node;
	}

	public NoArvore maximum(NoArvore node) {
		while(node.direito != TNULL) {
			node = node.direito;
		}
		return node;
	}

	public NoArvore successor(NoArvore x) {
		if(x.direito != TNULL) {
			return minimum(x.direito);
		}

		NoArvore y = x.pai;
		while(y != TNULL && x == y.direito) {
			x = y;
			y = y.pai;
		}
		return y;
	}

	public NoArvore predecessor(NoArvore x) {
		if(x.esquerdo != TNULL) {
			return maximum(x.esquerdo);
		}

		NoArvore y = x.pai;
		while(y != TNULL && x == y.esquerdo) {
			x = y;
			y = y.pai;
		}

		return y;
	}

	public void leftRotate(NoArvore x) {
		NoArvore y = x.direito;
		x.direito = y.esquerdo;
		if(y.esquerdo != TNULL) {
			y.esquerdo.pai = x;
		}
		y.pai = x.pai;
		if(x.pai == null) {
			this.root = y;
		} else if(x == x.pai.esquerdo) {
			x.pai.esquerdo = y;
		} else {
			x.pai.direito = y;
		}
		y.esquerdo = x;
		x.pai = y;
	}

	public void rightRotate(NoArvore x) {
		NoArvore y = x.esquerdo;
		x.esquerdo = y.direito;
		if (y.direito != TNULL) {
			y.direito.pai = x;
		}
		y.pai = x.pai;
		if (x.pai == null) {
			this.root = y;
		} else if (x == x.pai.direito) {
			x.pai.direito = y;
		} else {
			x.pai.esquerdo = y;
		}
		y.direito = x;
		x.pai = y;
	}
	
	public void makeEmpty() {
		TNULL = new NoArvore();
		TNULL.cor = 0;
		TNULL.esquerdo = null;
		TNULL.direito = null;
		this.root = TNULL;
	}
	
}
