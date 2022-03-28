package model;

public class Fou extends AbstractPiece {

	public Fou(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(isValidCoord(xFinal, yFinal)) {
			if(Math.abs(getX()-xFinal) == Math.abs(getY()-yFinal)) {
					ret = true;
			}
		}
		return ret;
	}
}
