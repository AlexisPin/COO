package model;


public class Cavalier extends AbstractPiece {

	public Cavalier(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if(xFinal != this.getX() && yFinal != this.getY()) {
			int deltaX = 2;
			int deltaY = 1;
			int minusPlus = 1;
			int leftRight = 0;
			Integer nextCoord[] = {xFinal, yFinal};

			for(int i = 0; i < 8; i++ ) {
				if(i == 4) {
					deltaX = 1;
					deltaY = 2;
				}
				if(leftRight == 2) {
					leftRight = 0;
					minusPlus *= -1;
				}
				Integer comb[] = {getX() + deltaX*minusPlus, getY() + deltaY};
				++leftRight;
				deltaY *= -1;
				if(comb[0] > 0 && comb[0] < 8 && comb[1] < 8 && comb[1] > 0) {
					if(comb[0] == nextCoord[0] && comb[1] == nextCoord[1]) {
						ret = true;
						break;
					}
				}
			}
		}
		return ret;
	}
}
