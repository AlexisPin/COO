package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Echiquier implements BoardGames{
	public Jeu jeuNoir;
	public Jeu jeuBlanc;
	private String message  = "La partie commence !";
	private List<Jeu> jeux = new ArrayList<Jeu>();
	private Jeu currentGame;
	private Jeu notCurrentGame;

	public Echiquier() {
		this.jeuBlanc = new Jeu(Couleur.BLANC);
		this.jeuNoir = new Jeu(Couleur.NOIR);
		this.jeux.add(jeuBlanc);
		this.jeux.add(jeuNoir);
		this.currentGame = jeux.get(0);
		this.notCurrentGame = jeux.get(1);
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
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			
			if(currentGame.isPieceHere(xInit, yInit)) {
				System.out.println("ok");
				 
					 if(currentGame.isMoveOk(xInit, yInit, xFinal, yFinal)) {
						 
						 if(!isIntermediatePiece(xInit,yInit,xFinal, yFinal)) {
							 
							 if(currentGame.isPieceHere(xFinal, yFinal)) {
								 if(true) {
									 ret = true;
									 currentGame.setCastling();
									 setMessage("OK : roque du roi");
								 }
							 }else if(notCurrentGame.isPieceHere(xFinal, yFinal)){
								 notCurrentGame.capture(xFinal, yFinal);
								 setMessage("OK : déplacement + capture");
								 ret = true;
							 }else {
								 ret = true;
								 setMessage("OK : déplacement sans capture");
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
		
	public boolean isIntermediatePiece(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = true;
		String currentPiece = currentGame.getPieceType(xInit, yInit);
		if(currentPiece.equals("Cavalier"))
		{
				ret = false;		
		}else {
			int nbEmptyCase = 0;
			List<Integer> Array = null;
			if(Math.abs(xInit-xFinal) == Math.abs(yInit-yFinal)) {
				List<Integer> xArray = Stream.iterate(xFinal > xInit ? xInit+1 : xInit-1, n -> xFinal > xInit ? n + 1 : n-1)
                        .limit(Math.abs(xFinal-xInit)-1)
                        .collect(Collectors.toList());
				List<Integer> yArray = Stream.iterate(yFinal > yInit ? yInit+1 : yInit-1, n -> yFinal > yInit ? n + 1 : n-1)
                        .limit(Math.abs(yFinal-yInit)-1)
                        .collect(Collectors.toList());
				for(int i = 0 ; i < xArray.size(); i++) {
					if(currentGame.isPieceHere(xArray.get(i), yArray.get(i)) || notCurrentGame.isPieceHere(xArray.get(i), yArray.get(i))) {
						if(getPieceColor(xArray.get(i), yArray.get(i)) != currentGame.getCouleur()) {
							currentGame.capture(xArray.get(i), yArray.get(i));
						}
					}else {
						nbEmptyCase += 1;
					}
				}
				Array = xArray;
			}else if(xInit == xFinal) {
				List<Integer> yArray = Stream.iterate(yFinal > yInit ? yInit+1 : yInit-1, n -> yFinal > yInit ? n + 1 : n-1)
                        .limit(Math.abs(yFinal-yInit)-1)
                        .collect(Collectors.toList());
				for(int i = 0 ; i < yArray.size(); i++) {
					if(currentGame.isPieceHere(xInit, yArray.get(i)) || notCurrentGame.isPieceHere(xInit, yArray.get(i))) {						
						if(getPieceColor(xInit, yArray.get(i)) != currentGame.getCouleur()) {
							currentGame.capture(xInit, yArray.get(i));
						}
					}else {
						nbEmptyCase += 1;
					}
				}
				Array = yArray;
			}else if(yInit == yFinal) {
				List<Integer> xArray = Stream.iterate(xFinal > xInit ? xInit+1 : xInit-1, n -> xFinal > xInit ? n + 1 : n-1)
                        .limit(Math.abs(xFinal-xInit)-1)
                        .collect(Collectors.toList());
				for(int i = 0 ; i < xArray.size(); i++) {
					if(currentGame.isPieceHere(xArray.get(i),yInit) || notCurrentGame.isPieceHere(xArray.get(i),yInit))
					if(getPieceColor(xArray.get(i),yInit) != currentGame.getCouleur()) {
						currentGame.capture(xArray.get(i),yInit);
						ret = true;
					}else {
						nbEmptyCase += 1;
					}
				}
				Array = xArray;
			}
			if(nbEmptyCase == Array.size()) {
				ret = false;
			}
		}
		return ret;
	}
	public void switchJoueur() {
		if(currentGame == jeux.get(0)){
			currentGame = jeux.get(1);
			notCurrentGame = jeux.get(0);
		}
		else {
			currentGame = jeux.get(0);
			notCurrentGame = jeux.get(1);
		}
		System.out.println("KO:c'est au tour du joueur " + currentGame.getCouleur());
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

	}
	

}
