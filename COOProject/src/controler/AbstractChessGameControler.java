package controler;

import model.Coord;
import model.Couleur;
import model.observable.ChessGame;


/**
 * @author francoise.perrin
 * 
 * le controleur illustre le DP Strategy vis-à-vis de la Vue
 * 
 * Méthodes communes des controleurs
 * dont le travail essentiel est de faire communiquer
 * la vue et le modèle pour gérer le déplacement des pièces
 * déplacement figé à ce niveau de la hiérarchie dans un Template Method 
 * les petites lignes étant implémentées dans les classes dérivées
 *
 */
public abstract class AbstractChessGameControler implements ChessGameControlers {

	protected ChessGame chessGame;	 

	public AbstractChessGameControler(ChessGame chessGame) {
		super();
		this.chessGame = chessGame;	 
	}

	/* (non-Javadoc)
	 * @see controler.ChessGameControlers#move(model.Coord, model.Coord)
	 * 
	 * Cette méthode illustre le DP "Template Method" 
	 * avec une partie commune implémentée dans cette classe
	 * et une partie variable implémentée dans les classes dérivées
	 */

	final public boolean move(Coord initCoord, Coord finalCoord) {
		boolean ret = false;
		String promotionType = null; 

		// si c'est bien au tour du joueur courant de jouer
		if (this.isPlayerOK(initCoord)) {

			// Déplacement métier
			ret = this.moveModel(initCoord, finalCoord);	 

			// Actions différentes selon les types de controleur
			if (ret) {	
				this.endMove(initCoord, finalCoord, promotionType);
			}

		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see controler.AbstractChessGameControler#isPlayerOK(model.Coord)
	 * 
	 * cette méthode vérifie que la couleur de la pièce que l'utilisateur
	 * tente de déplacer est bien celle du jeu courant
	 * la vue se servira de cette information pour empêcher tout déplacement sur le damier
	 */
	public abstract boolean isPlayerOK(Coord initCoord) ;

	// Déplacement métier
	protected  boolean moveModel(Coord initCoord, Coord finalCoord)  {	
		return chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);	
	}

	protected abstract void endMove(Coord initCoord, Coord finalCoord, String promotionType) ;


	public boolean isEnd(){
		return this.chessGame.isEnd();		
	}

	public String getMessage() {
		String ret = null;		 
		ret = this.chessGame.getMessage();	 
		return ret;
	}

	public String toString() {
		return this.chessGame.toString();
	}

	
	protected Couleur getColorCurrentPlayer(){		
		return this.chessGame.getColorCurrentPlayer();		
	}	

	protected Couleur getPieceColor(Coord initCoord){		
		return this.chessGame.getPieceColor(initCoord.x, initCoord.y);		
	}	
	
}
