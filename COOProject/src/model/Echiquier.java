package model;

import java.util.ArrayList;
import java.util.List;

public class Echiquier implements BoardGames{
	public Jeu jeuNoir;
	public Jeu jeuBlanc;
	private String message  = "La partie commence !";
	private List<Jeu> jeux = new ArrayList<Jeu>();
	private Jeu currentGame;

	public Echiquier() {
		this.jeuBlanc = new Jeu(Couleur.BLANC);
		this.jeuNoir = new Jeu(Couleur.NOIR);
		this.jeux.add(jeuBlanc);
		this.jeux.add(jeuNoir);
		this.currentGame = jeux.get(0);
	}

	@Override
	public boolean isEnd() {
		boolean ret  = false;
		return ret;
	}

	public String getMessage() {
		return message;
	}
	
	private void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public Couleur getColorCurrentPlayer() {
		return currentGame.getCouleur();
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		Couleur pieceColor = null;
		for(Jeu jeu : jeux) {
			if(jeu.isPieceHere(x, y)) {
				 pieceColor = jeu.getPieceColor(x, y);
			}
		}
		return pieceColor;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = false;
			if(currentGame.isPieceHere(xInit, yInit)) {
				 if(Coord.coordonnees_valides(xFinal, yFinal)) {
					 if(currentGame.isMoveOk(xInit, yInit, xFinal, yFinal)) {
						 
						 if(!currentGame.isIntermediatePiece(xInit,yInit,xFinal, yFinal)) {
							 
							 if(currentGame.isPieceHere(xFinal, yFinal)) {
								 
								 if(currentGame.getPieceColor(xFinal, yFinal) != currentGame.getCouleur()) {
									 ret = true;
									 currentGame.capture(xFinal, yFinal);
									 setMessage("OK : déplacement + capture");
								 }else {
									 currentGame.setCastling();
								 }
							 }else {
								 setMessage("OK : déplacement sans capture");
								 ret = true;
							 }
						 }
					 }else {
						setMessage("KO:la position finale ne correspond pas à algo de déplacement légal de la pièce");
					 }
				 }
			}
		return ret;
	}
	
	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = false;
		if(isMoveOk(xInit, yInit, xFinal, yFinal)) {
			currentGame.move(xInit, yInit, xFinal, yFinal);
			ret = true;
		}
		return ret;
	}
		
	
	public void switchJoueur() {
		if(currentGame == jeux.get(0)){
			currentGame = jeux.get(1);
		}
		else {
			currentGame = jeux.get(0);
		}
		System.out.println("KO:c'est au tour de l'autre joueur");
	}

	@Override
	public String toString() {
		String ret = "";
		for(Jeu jeu : jeux) {
			ret += jeu.toString();
		}
		return ret;
	}
	
	public List<PieceIHM> getPiecesIHM() {
		List<PieceIHM> concat_list = new ArrayList<PieceIHM>();
		List<PieceIHM> list = null;
		for(Jeu jeu : jeux) {
			list =  jeu.getPiecesIHM();
			concat_list.addAll(list);
		}
		return concat_list;    
	}
	

	public static void main(String[] args) {
		Echiquier echiquier = new Echiquier();
		System.out.println(echiquier);
		//System.out.println(echiquier.getPieceColor(0, 0));
		System.out.println(echiquier.getColorCurrentPlayer());
		echiquier.switchJoueur();
		System.out.println(echiquier.getColorCurrentPlayer());
		System.out.println(echiquier.move(0, 1, 0, 2));
		echiquier.switchJoueur();
		System.out.println(echiquier.move(0, 6, 0, 5));
		System.out.println(echiquier.getPiecesIHM());
	}
	

}
