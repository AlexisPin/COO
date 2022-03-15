package model;

public abstract class AbstractPiece implements Pieces{
	
	public Couleur couleur;
	public Coord coord;
	public String name;
	
	
	public AbstractPiece(Couleur couleur,Coord coord) {
		this.couleur = couleur;
		this.coord = coord;
		this.name = getClass().getSimpleName();
	}
	
	public boolean capture() {
		return false;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	
	public int getX() {
		return coord.x;
	}
	public int getY() {
		return coord.y;
	}
	
	abstract public boolean isMoveOk(int xFinal,int  yFinal);
	
	public boolean move(int x, int y) {
		return false;
	}

	@Override
	public String toString() {
		return "AbstractPiece [couleur=" + couleur + ", coord=" + coord + ", name=" + name + "]";
	}
	
	
	
	
}
