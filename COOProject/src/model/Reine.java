package model;

public class Reine extends AbstractPiece {

	public Reine(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(xFinal != getX() && yFinal != getY()) {
			if(xFinal == getX() || yFinal == getY()) {
				ret = true;
			}
			else if(Math.abs(getX()-xFinal) == Math.abs(getY()-yFinal)) {
				ret = true;
			}
		}
		return ret;
	}

}
