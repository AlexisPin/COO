package model;

public class Pion extends AbstractPiece implements Pions{

	public Pion(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(getX() == xFinal && yFinal == getY() + 1) {
			ret = true;
		}
		return ret;
	}

	@Override
	public boolean isMoveDiagOk(int xFinal, int yFinal) {

		return false;
	}
	
	public boolean move(int x, int y) {
		return false;
	}

}
