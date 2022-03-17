package model;


import java.util.LinkedList;
import java.util.List;

import tools.ChessPiecesFactory;
import tools.ChessSinglePieceFactory;

public class Jeu {
	private Couleur couleur;
	private List<Pieces> pieces;
	private int previousMovedIndexPiece;
	private Pieces previousMovedPiece;
	
	public Jeu(Couleur couleur) {
		this.couleur = couleur;
		this.pieces = ChessPiecesFactory.newPieces(couleur);
	}
	
	public boolean capture(int xCatch, int yCatch) {
		boolean ret = true;
		if(isPieceHere(xCatch, yCatch)) {
			ret = true;
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
		return new Coord(pieces.get(7).getX(),pieces.get(7).getY());
	}
	
	/**              
	*  @return une vue de la liste des pièces en cours        
	* ne donnant que des accès en lecture sur des PieceIHM        
	* (type piece + couleur + liste de coordonnées)              
	*/

	public List<PieceIHM> getPiecesIHM(){               
		PieceIHM newPieceIHM = null;               
		List<PieceIHM>  list = new LinkedList<PieceIHM>(); 
		
		for (Pieces piece : pieces){ 
			boolean existe = false; 
			// si le type de piece existe déjà dans la liste de PieceIHM
			// ajout des coordonnées de la pièce dans la liste de Coord de ce type 
			// si elle est toujours en jeu (x et y != -1)
			for ( PieceIHM pieceIHM : list){                                   
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){ 
					existe = true; 
					if (piece.getX() != -1){ 
						pieceIHM.add(new Coord(piece.getX(), piece.getY()));      
					}     
				}       
			} 
			// sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
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
		Pieces currentPiece = findPiece(xInit, yInit);
		if(currentPiece.isMoveOk(xFinal, yFinal)) {
			ret = true;
		}
		return ret;
	} 
	
	public boolean move(int xInit, int yInit, int xFinal, int yFinal  ) {
		boolean ret = false;
		if(isMoveOk(xInit, yInit, xFinal, yFinal)) {
			ret = true;
			Pieces currentPiece = findPiece(xInit, yInit);
			this.previousMovedPiece = ChessSinglePieceFactory.newPiece(currentPiece.getCouleur(), getPieceType(xInit, yInit), xInit, yInit);
			this.previousMovedIndexPiece  = pieces.indexOf(currentPiece);
			currentPiece.move(xFinal, yFinal);
		}
		return ret;
	}
	
	public boolean isPawnPromotion(int xFinal, int yFinal) {
		boolean ret = false;
		Pieces currentPieces = findPiece(xFinal, yFinal);
		String name = currentPieces.getClass().getSimpleName();
		if(couleur == Couleur.BLANC) {
			if(name == Pion.class.getSimpleName()) {
				if(currentPieces.getY() == 0) {
					ret = true;
				}
			}
		}else if(couleur == Couleur.NOIR) {
			if(name == Pion.class.getSimpleName()) {
				if(currentPieces.getY() == 7) {
					ret = true;
				}
			}
		}
		return ret;
	}
	
	public boolean pawnPromotion(int xFinal, int yFinal, String type) {
		boolean ret = false;
		if(isPawnPromotion(xFinal, yFinal)) {
			ret =  true;
			int currentPieceIndex  = pieces.indexOf(findPiece(xFinal, yFinal));
			pieces.remove(currentPieceIndex);
			pieces.add(currentPieceIndex, ChessSinglePieceFactory.newPiece(couleur, type, xFinal, yFinal));
		}
		return ret;
	}
	
	public Couleur getPieceColor(int x, int y) {
		Pieces currentPiece = findPiece(x, y);
		Couleur pieceColor = currentPiece.getCouleur();
		return pieceColor;
	}
	
	public void setPossbileCapture() {
		
	}
	
	public void undoCapture() {
		
	}
	
	public void undoMove() {
		pieces.remove(previousMovedIndexPiece);
		pieces.add(previousMovedIndexPiece, previousMovedPiece);
	}
	
	public void setCastling() {
		
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
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu(Couleur.BLANC);
		boolean piece = jeu.move(3, 6, 3, 5);
		boolean pieceHere = jeu.isPieceHere(3, 4);
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
	}
	
}
