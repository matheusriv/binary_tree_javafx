package br.ufrn.imd.modelo;

import java.util.Objects;

public class ArvoreRubroNegra extends ArvoreBinariaBase {
	private NoArvore TNULL;
	
	public ArvoreRubroNegra() {
		TNULL = new NoArvore();
		TNULL.color = 0;
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
		node.color = 1;
	
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
			node.color = 0;
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
	
	public void delete(int data) {
		deleteNodeHelper(this.root, data);
	}
	
	protected void deleteNodeHelper(NoArvore node, int key) {
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
			System.out.println("Couldn't find key in the tree");
			return;
		}
	
		y = z;
		int yOriginalColor = y.color;
		if(z.esquerdo == TNULL) {
			x = z.direito;
			rbTransplant(z, z.direito);
		} else if (z.direito == TNULL) {
			x = z.esquerdo;
			rbTransplant(z, z.esquerdo);
		} else {
			y = minimum(z.direito);
			yOriginalColor = y.color;
			x = y.direito;
			if (y.pai == z) {
				x.pai = y;
			} else {
				rbTransplant(y, y.direito);
				y.direito = z.direito;
				y.direito.pai = y;
			}
	
			rbTransplant(z, y);
			y.esquerdo = z.esquerdo;
			y.esquerdo.pai = y;
			y.color = z.color;
		}
		if (yOriginalColor == 0) {
			fixDelete(x);
		}
	}
	
	// Balance the tree after deletion of a NoArvore
	protected void fixDelete(NoArvore x) {
		NoArvore s;
		while (x != root && x.color == 0) {
			if (x == x.pai.esquerdo) {
				s = x.pai.direito;
				if (s.color == 1) {
					s.color = 0;
					x.pai.color = 1;
					leftRotate(x.pai);
					s = x.pai.direito;
				}
	
				if (s.esquerdo.color == 0 && s.direito.color == 0) {
					s.color = 1;
					x = x.pai;
				} else {
					if (s.direito.color == 0) {
						s.esquerdo.color = 0;
						s.color = 1;
						rightRotate(s);
						s = x.pai.direito;
					}
	
					s.color = x.pai.color;
					x.pai.color = 0;
					s.direito.color = 0;
					leftRotate(x.pai);
					x = root;
				}
			} else {
				s = x.pai.esquerdo;
				if (s.color == 1) {
					s.color = 0;
					x.pai.color = 1;
					rightRotate(x.pai);
					s = x.pai.esquerdo;
				}
	
				if (s.direito.color == 0 && s.direito.color == 0) {
					s.color = 1;
					x = x.pai;
				} else {
					if (s.esquerdo.color == 0) {
						s.direito.color = 0;
						s.color = 1;
						leftRotate(s);
						s = x.pai.esquerdo;
					}
	
					s.color = x.pai.color;
					x.pai.color = 0;
					s.esquerdo.color = 0;
					rightRotate(x.pai);
					x = root;
				}
			}
		}
		x.color = 0;
	}
	
	protected void rbTransplant(NoArvore u, NoArvore v) {
		if (u.pai == null) {
			root = v;
		} else if (u == u.pai.esquerdo) {
			u.pai.esquerdo = v;
		} else {
			u.pai.direito = v;
		}
		v.pai = u.pai;
	}
	
	// Balance the NoArvore after insertion
	private void fixInsert(NoArvore k) {
		NoArvore u;
		while (k.pai.color == 1) {
			if (k.pai == k.pai.pai.direito) {
				u = k.pai.pai.esquerdo;
				if (u.color == 1) {
					u.color = 0;
					k.pai.color = 0;
					k.pai.pai.color = 1;
					k = k.pai.pai;
				} else {
					if (k == k.pai.esquerdo) {
						k = k.pai;
						rightRotate(k);
					}
					k.pai.color = 0;
					k.pai.pai.color = 1;
					leftRotate(k.pai.pai);
				}
			} else {
				u = k.pai.pai.direito;
	
				if (u.color == 1) {
					u.color = 0;
					k.pai.color = 0;
					k.pai.pai.color = 1;
					k = k.pai.pai;
				} else {
					if (k == k.pai.direito) {
						k = k.pai;
						leftRotate(k);
					}
					k.pai.color = 0;
					k.pai.pai.color = 1;
					rightRotate(k.pai.pai);
				}
			}
			if (k == root) {
				break;
			}
		}
		root.color = 0;
	}
	
	protected NoArvore minimum(NoArvore node) {
		while (node.esquerdo != TNULL) {
			node = node.esquerdo;
		}
		return node;
	}
	
	protected NoArvore maximum(NoArvore node) {
		while (node.direito != TNULL) {
			node = node.direito;
		}
		return node;
	}
	
	protected NoArvore successor(NoArvore x) {
		if (x.direito != TNULL) {
			return minimum(x.direito);
		}
		
		NoArvore y = x.pai;
		while (y != TNULL && x == y.direito) {
			x = y;
			y = y.pai;
		}
		return y;
	}
	
	protected NoArvore predecessor(NoArvore x) {
		if (x.esquerdo != TNULL) {
			return maximum(x.esquerdo);
		}
	
		NoArvore y = x.pai;
		while (y != TNULL && x == y.esquerdo) {
			x = y;
			y = y.pai;
		}
	
		return y;
	}
	
	protected void leftRotate(NoArvore x) {
		NoArvore y = x.direito;
		x.direito = y.esquerdo;
		if (y.esquerdo != TNULL) {
			y.esquerdo.pai = x;
		}
		y.pai = x.pai;
		if (x.pai == null) {
			this.root = y;
		} else if (x == x.pai.esquerdo) {
			x.pai.esquerdo = y;
		} else {
			x.pai.direito = y;
		}
		y.esquerdo = x;
		x.pai = y;
	}
	
	protected void rightRotate(NoArvore x) {
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