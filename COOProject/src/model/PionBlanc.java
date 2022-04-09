package model;

public class PionBlanc extends Pion {

	public PionBlanc(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean specified(int x, int y) {
		boolean ret = false;
		ret = getX() == x && ((getY() == 6 && getY() - y == 2) ||  getY() - y == 1);
		return ret;
	}

	@Override
	protected boolean specifiedDiag(int xFinal, int yFinal) {
		boolean ret = false;
		ret = Math.abs(getX() - xFinal) == 1 && getY() - yFinal == 1;
		return ret;
	}

	public boolean isLastRow() {
		boolean ret  =false;
		if(getY() == 0) {
			ret = true;
		}
		return ret;
	}
}
