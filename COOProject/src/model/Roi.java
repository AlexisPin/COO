package model;

public class Roi extends AbstractPiece {

	public Roi(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(xFinal != getX() || yFinal != getY()) {
			if(Math.abs(this.getX() - xFinal) == 1 ||  Math.abs(this.getY() - yFinal) == 1) {
				ret = true;
			}
		}
		return ret;
	}
}
