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
		if(xInit != xFinal || yInit != yFinal) {
			 if(!isIntermediatePiece(xInit,yInit,xFinal, yFinal)) {
				 if(currentGame.isMoveOk(xInit, yInit, xFinal, yFinal)) {		 
					 if(currentGame.isPieceHere(xFinal, yFinal)) {
						 if(currentGame.possibleCastling) {
							 ret = true;
							 setMessage("OK : roque du roi");
						 }else {
							 setMessage("Une de vos piËce se trouve dÈj‡ ‡ cette position");
						 }
					 }else if(notCurrentGame.isPieceHere(xFinal, yFinal)){
						 if((currentGame.getPieceType(xInit, yInit).equals("PionNoir") || currentGame.getPieceType(xInit, yInit).equals("PionBlanc")) &&  xInit == xFinal) {
							 setMessage("Capture impossible");
						 }else {
							 notCurrentGame.capture(xFinal, yFinal);
							 setMessage("OK : d√©placement + capture");					 
							 ret = true;
						 }
					 }else {
						 ret = true;
						 setMessage("OK : d√©placement sans capture");
					 }
				}else {
				setMessage("KO:la position finale ne correspond pas √† algo de d√©placement l√©gal de la pi√®ce");
				 	}
			 }else {
				 setMessage("Une piËce se trouve sur la trajectoire");
			 }
		}else {
			setMessage("La position final ne peut Ítre identique ‡ la position initial");
		}
		return ret;
	}
	
	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = false;
		currentGame.move(xInit, yInit, xFinal, yFinal);
		ret = true;
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
			int positionEnemyPiece = 0;
			List<Integer> Array = null;
			if(Math.abs(xInit-xFinal) == Math.abs(yInit-yFinal) && Math.abs(xInit-xFinal) != 1 && Math.abs(yInit-yFinal) != 1) {
				List<Integer> xArray = Stream.iterate(xFinal > xInit ? xInit+1 : xInit-1, n -> xFinal > xInit ? n + 1 : n-1)
                        .limit(Math.abs(xFinal-xInit)-1)
                        .collect(Collectors.toList());
				List<Integer> yArray = Stream.iterate(yFinal > yInit ? yInit+1 : yInit-1, n -> yFinal > yInit ? n + 1 : n-1)
                        .limit(Math.abs(yFinal-yInit)-1)
                        .collect(Collectors.toList());
				for(int i = 0 ; i < xArray.size(); i++) {
					if(currentGame.isPieceHere(xArray.get(i), yArray.get(i))) {
						positionEnemyPiece = 1;
					}
					else if(notCurrentGame.isPieceHere(xArray.get(i), yArray.get(i))) {
						if(positionEnemyPiece == 0) {
							currentGame.setPossbileCapture();
						};
					}
					else {
						nbEmptyCase += 1;
					}
				}
				Array = xArray;
			}else if(xInit == xFinal) {
				List<Integer> yArray = Stream.iterate(yFinal > yInit ? yInit+1 : yInit-1, n -> yFinal > yInit ? n + 1 : n-1)
                        .limit(Math.abs(yFinal-yInit)-1)
                        .collect(Collectors.toList());
				for(int i = 0 ; i < yArray.size(); i++) {
					if(currentGame.isPieceHere(xInit, yArray.get(i))){
						positionEnemyPiece = 1;
					}
					else if(notCurrentGame.isPieceHere(xInit, yArray.get(i))) {
						if(positionEnemyPiece == 0) {
							currentGame.setPossbileCapture();
						}
					}			
					else {
						nbEmptyCase += 1;
					}
				}
				Array = yArray;
			}else if(yInit == yFinal) {
				List<Integer> xArray = Stream.iterate(xFinal > xInit ? xInit+1 : xInit-1, n -> xFinal > xInit ? n + 1 : n-1)
                        .limit(Math.abs(xFinal-xInit)-1)
                        .collect(Collectors.toList());
				for(int i = 0 ; i < xArray.size(); i++) {
					if(currentGame.isPieceHere(xArray.get(i),yInit)) {
						positionEnemyPiece = 1;
					}
					else if(notCurrentGame.isPieceHere(xArray.get(i),yInit)){
						if(positionEnemyPiece == 0) {
							currentGame.setPossbileCapture();
						}
					}
					else {
						nbEmptyCase += 1;
					}
				}
				Array = xArray;
			}else if(Math.abs(xInit-xFinal) == 1 && Math.abs(yInit-yFinal) == 1){
				if(notCurrentGame.isPieceHere(xFinal, yFinal) && (currentGame.getPieceType(xInit, yInit).equals("PionBlanc") || currentGame.getPieceType(xInit, yInit).equals("PionNoir"))) {
					currentGame.setPossbileCapture();
				}
				Array = Stream.iterate(0, n->n+1)
                        .limit(0)
                        .collect(Collectors.toList());;
			}else {
				Array = Stream.iterate(0, n->n+1)
                        .limit(0)
                        .collect(Collectors.toList());;
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
