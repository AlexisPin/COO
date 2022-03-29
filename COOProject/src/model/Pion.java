package model;


public abstract class Pion extends AbstractPiece implements Pions{

	public Pion(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public final boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(getY() != yFinal) {
			ret = specified(xFinal,yFinal);
		}
		return ret;
	}

	@Override
	public final boolean isMoveDiagOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(getY() != yFinal) {
			ret = specifiedDiag(xFinal,yFinal);	
		}
		return ret;
	}
	
	protected abstract boolean specifiedDiag(int xFinal, int yFinal);

	@Override
	public boolean move(int x, int y) {
		return super.move(x, y);
	}
	
	protected abstract boolean specified(int x,int y);

}
