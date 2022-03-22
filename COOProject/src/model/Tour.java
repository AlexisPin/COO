package model;

public class Tour extends AbstractPiece {

	public Tour(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(xFinal == coord.x || yFinal == coord.y) {
			ret = true;
		}
		return ret;
	}

}
