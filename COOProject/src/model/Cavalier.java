package model;


public class Cavalier extends AbstractPiece {

	public Cavalier(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(xFinal != getX() && yFinal != getY()) {
			if((Math.abs(this.getX() - xFinal) == 2 &&  Math.abs(this.getY() - yFinal) == 1) || (Math.abs(this.getY() - yFinal) == 2 && Math.abs(this.getX() - xFinal) == 1 )) {
				ret = true;
			}
		}
		return ret;
	}
}
