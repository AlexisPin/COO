package model;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

import tools.ChessPiecesFactory;
import tools.ChessSinglePieceFactory;

public class Jeu {
	private Couleur couleur;
	private List<Pieces> pieces;
	private int previousMovedIndexPiece;
	private Pieces previousMovedPiece;
	private Stack<Pieces> listCapturedPiece = new Stack<Pieces>();
	public Boolean possibleCapture = false;
	public Boolean possibleCastling = false;
	
	public Jeu(Couleur couleur) {
		this.couleur = couleur;
		this.pieces = ChessPiecesFactory.newPieces(couleur);
	}
	
	public boolean capture(int xCatch, int yCatch) {
		boolean ret = false;
		if(isPieceHere(xCatch, yCatch)) {
			Pieces currentPiece = findPiece(xCatch, yCatch);
			int currentPieceIndex  = pieces.indexOf(currentPiece);
			if(currentPiece.capture()) {
				ret = true;
				listCapturedPiece.add(currentPiece);
				//pieces.remove(currentPieceIndex);
			}
		}
		return ret;
	}

	public boolean isPieceHere(int x, int y) {
		boolean ret = false;
        for(Pieces piece : pieces) {
        	if(piece.getX() == x && piece.getY() == y ) {
        		ret = true;
        	}
        }
		return ret;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public Coord getKingCoord() {
		Coord kingCoord = null;
		for(Pieces piece : pieces) {
			if(getPieceType(piece.getX(), piece.getY()) == Roi.class.getSimpleName()) {
				kingCoord = new Coord(piece.getX(),piece.getY());
			}
		}
		return kingCoord;
	}
	
	/**              
	*  @return une vue de la liste des pi�ces en cours        
	* ne donnant que des acc�s en lecture sur des PieceIHM        
	* (type piece + couleur + liste de coordonn�es)              
	*/
	public List<PieceIHM> getPiecesIHM(){               
		PieceIHM newPieceIHM = null;               
		List<PieceIHM>  list = new LinkedList<PieceIHM>(); 
		
		for (Pieces piece : pieces){ 
			boolean existe = false; 
			// si le type de piece existe d�j� dans la liste de PieceIHM
			// ajout des coordonn�es de la pi�ce dans la liste de Coord de ce type 
			// si elle est toujours en jeu (x et y != -1)
			for ( PieceIHM pieceIHM : list){                                   
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){ 
					existe = true; 
					if (piece.getX() != -1){ 
						pieceIHM.add(new Coord(piece.getX(), piece.getY()));      
					}     
				}       
			} 
			// sinon, cr�ation d'une nouvelle PieceIHM si la pi�ce est toujours en jeu
			if (! existe) { 
				if (piece.getX()!= -1){  
					newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(), piece.getCouleur()); 
					newPieceIHM.add(new Coord(piece.getX(), piece.getY())); 
					list.add(newPieceIHM);     
				}    
			}   
		} 
		return list;        
	}   
	public String getPieceType(int x, int y) {
		return findPiece(x, y).getClass().getSimpleName();
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = false;
		if(isPieceHere(xInit, yInit)) {
			Pieces currentPiece = findPiece(xInit, yInit);
			if(currentPiece.isMoveOk(xFinal, yFinal)) {
				ret = true;
			}
			if(currentPiece instanceof Pion)
			{					
				if(((Pion) currentPiece).isMoveDiagOk(xFinal, yFinal) && possibleCapture) {	
					possibleCapture = false;
					ret = true;
				}
			}
		}
		return ret;
	} 
	
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = false;
		Pieces currentPiece = findPiece(xInit, yInit);
		this.previousMovedPiece = ChessSinglePieceFactory.newPiece(currentPiece.getCouleur(), getPieceType(xInit, yInit), xInit, yInit);
		this.previousMovedIndexPiece  = pieces.indexOf(currentPiece);
		if(currentPiece.move(xFinal, yFinal)) ret = true;
		if(isPawnPromotion(xFinal, yFinal)) {
			pawnPromotion(xFinal, yFinal, "Dame");
		}
		return ret;
	}
	
	public boolean isPawnPromotion(int xFinal, int yFinal) {
		boolean ret = false;
		Pieces currentPieces = findPiece(xFinal, yFinal);
		if(currentPieces instanceof Pions) {
			ret = ((Pions) currentPieces).isLastRow();
		}
		return ret;
	}
	
	public boolean pawnPromotion(int xFinal, int yFinal, String type) {
		boolean ret = false;
		if(type.equals("Dame") || type.equals("Tour") || type.equals("Fou") || type.equals("Cavalier")) {
			int currentPieceIndex  = pieces.indexOf(findPiece(xFinal, yFinal));
			pieces.remove(currentPieceIndex);
			pieces.add(currentPieceIndex, ChessSinglePieceFactory.newPiece(couleur, type, xFinal, yFinal));
			ret =  true;			
		}
		return ret;
	}
	
	public Couleur getPieceColor(int x, int y) {
		Pieces currentPiece = findPiece(x, y);
		Couleur pieceColor = currentPiece.getCouleur();
		return pieceColor;
	}
	
	public void setPossbileCapture() {
		this.possibleCapture = true;
	}
	
	public void undoCapture() {
		Pieces lastCapturedPiece = listCapturedPiece.pop();
		pieces.add(lastCapturedPiece);
	}
	
	public void undoMove() {
		pieces.remove(previousMovedIndexPiece);
		pieces.add(previousMovedIndexPiece, previousMovedPiece);
	}
	
	public void setCastling() {
		Coord kingCoord = getKingCoord();
	}
	
	private Pieces findPiece(int x, int y) {
		Pieces ret = null;
		if(isPieceHere(x, y)) {
	        for(Pieces piece : pieces) {
	        	if(piece.getX() == x && piece.getY() == y ) {
	        		ret = piece;
	        	}
	        }
		}
		return ret;
	}

	@Override
	public String toString() {
		String ret = "";
		for(Pieces piece : pieces) {
			ret += piece.toString();
		}
		return ret;
	}
	
	
	public List<Pieces> getList(){
		return pieces;
	}
	public static void main(String[] args) {
		Jeu jeu = new Jeu(Couleur.BLANC);
		boolean piece = jeu.move(3, 6, 3, 5);
		boolean pieceHere = jeu.isPieceHere(3, 5);
		System.out.println(piece);
		jeu.undoMove();
		System.out.println(jeu);
		boolean piece2 = jeu.move(3, 6, 3, 5);
		boolean piece3 = jeu.move(3, 5, 3, 4);
		boolean piece4 = jeu.move(3, 4, 3, 3);
		boolean piece5 = jeu.move(3, 3, 3, 2);
		boolean piece6 = jeu.move(3, 2, 3, 1);
		boolean piece7 = jeu.move(3, 1, 3, 0);
		boolean promo = jeu.pawnPromotion(3, 0,"Tour");
		Pieces piecePromo = jeu.findPiece(3, 0);
		System.out.println(piecePromo);
		System.out.println(jeu.isPieceHere(3, 0));
		System.out.println(jeu.getKingCoord());
		boolean roi = jeu.move(4, 7, 4, 6);
		System.out.println(roi);
		System.out.println(jeu.getKingCoord());
		System.out.println(jeu);
	}

	
}
