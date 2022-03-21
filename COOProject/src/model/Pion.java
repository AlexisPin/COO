package model;

import tools.ChessPiecePos;

public class Pion extends AbstractPiece implements Pions{

	public Pion(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		int deltaY = 0;
		int additionalY = 0;
		if(this.couleur == Couleur.NOIR) {
			deltaY = 1;
			for (int j = 0; j < (ChessPiecePos.PIONNOIR.coords).length; j++) {
				if(getX() == ChessPiecePos.PIONNOIR.coords[j].x && getY() == ChessPiecePos.PIONNOIR.coords[j].y) {
					additionalY = 1;
				}	
				
			}
		}else if(this.couleur == Couleur.BLANC) {
			deltaY = -1;
			for (int j = 0; j < (ChessPiecePos.PIONBLANC.coords).length; j++) {
				if(getX() == ChessPiecePos.PIONBLANC.coords[j].x && getY() == ChessPiecePos.PIONBLANC.coords[j].y) {
					additionalY = -1;
				}	
			}
		}
		if(getX() == xFinal && (yFinal == getY() + deltaY || yFinal == getY() + deltaY + additionalY)) {
			ret = true;
		}
		return ret;
	}

	@Override
	public boolean isMoveDiagOk(int xFinal, int yFinal) {

		return false;
	}
	
	@Override
	public boolean move(int x, int y) {
		return super.move(x, y);
	}

}
