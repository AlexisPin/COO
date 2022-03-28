package model;

public class Tour extends AbstractPiece {

	public Tour(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(isValidCoord(xFinal, yFinal)) {
			if(xFinal == getX() || yFinal == getY()) {
				ret = true;
			}
		}
		return ret;
	}

}
