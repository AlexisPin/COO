package model;

public class Fou extends AbstractPiece {

	public Fou(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(xFinal != this.getX() && yFinal != this.getY()) {
			if(Math.abs(getX()-xFinal) == Math.abs(getY()-yFinal)) {
				if(getX()+xFinal < 8 && getX()+xFinal > -1 && getY()+yFinal < 8 && getY()+yFinal > -1) {
					ret = true;
				}
			}
		}
		return ret;
	}
}
