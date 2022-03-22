package model;

public class Roi extends AbstractPiece {

	public Roi(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		Integer nextCoord[] = {xFinal, yFinal};
		if(xFinal != this.getX() || yFinal != this.getY()) {
			int deltaX = 1;
			int deltaY = 1;
			for(int i = 0; i < 8; i++ ) {
				if(i == 3) {
					deltaX = -1;
					deltaY = 1;
				}
				if(i == 6) {
					deltaX = 0;
					deltaY = 1;
				}
				if(i > 6) {
					deltaY = -1;
				}
				Integer comb[] = {getX() + deltaX, getY() + deltaY};
				deltaY -= 1;
				if(comb[0] > -1 && comb[0] < 8 && comb[1] < 8 && comb[1] > -1) {
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
