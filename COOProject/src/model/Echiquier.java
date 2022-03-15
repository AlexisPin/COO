package model;

import java.util.List;

public class Echiquier implements BoardGames{

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		return null;
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isMoveOk(int xInt, int yInt, int xFinal, int yFinal) {
		return false;
	}
		
	
	public void switchJoueur() {
		
	}

	@Override
	public String toString() {
		return "Echiquier";
	}
	
	public List<PieceIHM> getPiecesIHM() {
		return null;
		
	}
	
}
