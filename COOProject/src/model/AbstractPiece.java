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
		coord.x = -1;
		coord.y = -1;
		return true;
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
		boolean ret = false;
		if(isMoveOk(x, y)) {
			coord.x = x;
			coord.y = y;
			ret = true;
		}

		return ret;
	}

	@Override
	public String toString() {
		return "[coord=" + coord + ", name=" + name + "]";
	}
	
	public boolean isValidCoord(int x, int y) {
		boolean ret = false;
		if(x < 8 && x > -1 && y< 8 && y> -1 && x != getX() && y != getY()) {
			ret = true;
		}
		return ret;
	}
}
