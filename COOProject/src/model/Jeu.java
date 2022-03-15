package model;

import java.util.List;
import java.util.ListIterator;

import tools.ChessPiecesFactory;

public class Jeu {
	public Couleur couleur;
	private List<Pieces> pieces;
	
	public Jeu(Couleur couleur) {
		this.pieces = ChessPiecesFactory.newPieces(couleur);
	}
	
	public boolean capture(int xCatch, int yCatch) {
		boolean ret;
		if(isPieceHere(xCatch, yCatch)) {
			ret = true;
		}else {
			ret = false;
		}
		return ret;
	}

	private boolean isPieceHere(int x, int y) {
		boolean ret = false;
        ListIterator<Pieces> piecesIterator = pieces.listIterator();
        while (piecesIterator.hasNext()) {
            if(piecesIterator.next().getX() == x && piecesIterator.next().getY() == y) {
            	ret = true;
            	break;
            };
        }
		return ret;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public Coord getKingCoord() {
		return new Coord(pieces.get(7).getX(),pieces.get(7).getX());
	}
	
	public List<PieceIHM> getPiecesIHM() {
		return null;
	}
	
	public String getPieceType(int x, int y) {
		return this.getClass().getSimpleName();
	}
	
	public boolean isMoveOk(int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return false;
	} 
	
	public boolean isPawnPromotion(int xFinal, int yFinal) {
		return false;
	}
	
	public boolean move(int xInit, int yInit, int xFinal, int yFinal  ) {
		boolean ret = false;
		if(isMoveOk(xFinal, yFinal)) {
			ret = true;
		}
		return ret;
	}
	
	public boolean pawnPromotion(int xFinal, int yFinal, String type) {
		boolean ret;
		if(isPawnPromotion(xFinal, yFinal)) {
			ret =  true;
		} else {
			ret = false;
		}
		return ret;
	}
	
	public void setPossbileCapture() {
		
	}
	
	public void undoCapture() {
		
	}
	
	public void undoMove() {
		
	}
	
	public void setCastling() {
		
	}

	@Override
	public String toString() {
		return "Jeu [couleur=" + couleur + "]";
	}
	
	
}
