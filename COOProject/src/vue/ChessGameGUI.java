package vue;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controler.ChessGameControlers;
import model.Coord;
import model.Couleur;
import model.PieceIHM;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

	private ChessGameControlers chessGameControler;
	private Dimension boardSize;

	public ChessGameGUI(String name, ChessGameControlers chessGameControler, Dimension boardSize) {
		this.chessGameControler = chessGameControler;
		this.boardSize = boardSize;
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println(chessGameControler.getMessage() + "\n");	

		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg1;


		String[][] damier = new String[8][8];
		
		// création d'un tableau 2D avec les noms des pièces
		for(PieceIHM pieceIHM : piecesIHM) {

			Couleur color = pieceIHM.getCouleur();
			String stColor = (Couleur.BLANC == color ? "Blanc" : "Noir" );
			String type = (pieceIHM.getTypePiece()).substring(0, 2);
			
			for(Coord coord : pieceIHM.getList()) {
				damier[coord.y][coord.x] = type + stColor+"S";
			}			
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
