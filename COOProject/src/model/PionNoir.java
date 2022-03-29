package model;

public class PionNoir extends Pion{

	public PionNoir(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean specified(int xFinal, int yFinal) {
		boolean ret = false;
		ret = getX() == xFinal && ((getY() == 1 && getY() - yFinal == -2) ||  getY() - yFinal == -1);
		return ret;
	}

	@Override
	protected boolean specifiedDiag(int xFinal, int yFinal) {
		boolean ret = false;
		ret = Math.abs(getX()- xFinal) == 1 && getY() - yFinal == -1;
		return ret;
	}

}
