package model;

public class Fou extends AbstractPiece {

	public Fou(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(xFinal != this.getX() && yFinal != this.getY()) {

			if(Math.abs(getX()-xFinal) == Math.abs(getY()-yFinal)) {
				if(isValidCoord(xFinal, yFinal)) {
					ret = true;
				}
			}
		}
		return ret;
	}
}
