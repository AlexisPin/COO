package model;

public class Pion extends AbstractPiece implements Pions{

	public Pion(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMoveDiagOk(int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean move(int x, int y) {
		return false;
	}

}
