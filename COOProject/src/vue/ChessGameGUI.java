package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controler.ChessGameControlers;
import model.Coord;
import model.Couleur;
import model.Echiquier;
import model.PieceIHM;
import tools.ChessImageProvider;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

	private ChessGameControlers chessGameControler;
	private Dimension boardSize;
	JLabel chessPiece;
	JPanel chessBoard;
	JLayeredPane layeredPane;
	int xAdjustment;
	int yAdjustment;
	Coord initCoord;
	Coord finalCoord;
	
	public ChessGameGUI(String name, ChessGameControlers chessGameControler, Dimension boardSize) {
		this.chessGameControler = chessGameControler;
		this.boardSize = boardSize;
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout( new GridLayout(8, 8) );
		chessBoard.setPreferredSize( boardSize );
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		
		for (int i = 0; i < 64; i++) {
			  JPanel square = new JPanel( new BorderLayout() );
			  chessBoard.add( square );
			 
			  int row = (i / 8) % 2;
			  if (row == 0)
			  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
			  else
			  square.setBackground( i % 2 == 0 ? Color.white : Color.black );
			  }
		
		Couleur pieceColor = Couleur.NOIR;
		 String[] pieceType = {"Tour", "Cavalier", "Fou","Reine","Roi","Fou","Cavalier","Tour","Pion"};
		 JLabel piece;
		 JPanel panel;
		 int delta = 0;
		 int currentPiece = 0;
		 for (int i = 0; i < 32; i++) {
			 int numeroCarre = i+delta;
			 piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(pieceType[currentPiece], pieceColor)));
			 panel = (JPanel)chessBoard.getComponent(numeroCarre);
			 panel.add(piece);
			 if(i == 15) {
				 pieceColor = Couleur.BLANC;
				 delta = 32;

			 }
			 if(currentPiece < 8) {
				 currentPiece +=1;
			 }
			 if(numeroCarre == 55) {
				 currentPiece =0;
			 }
		 }
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println(chessGameControler.getMessage() + "\n");	
		System.out.println("changement");	
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null) return;
		 chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
		 //chessGameControler.move(, me.getY() + yAdjustment/700);
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
	public void mousePressed(MouseEvent e) {
		  chessPiece = null;
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		  if (c instanceof JPanel) 
		  return;
		 
		  Point parentLocation = c.getParent().getLocation();
		  xAdjustment = parentLocation.x - e.getX();
		  yAdjustment = parentLocation.y - e.getY();
		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		  initCoord = new Coord(e.getX()/87,e.getY()/87);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(chessPiece == null) return;
		 
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		  if (c instanceof JLabel){
		  Container parent = c.getParent();
		  parent.remove(0);
		  parent.add( chessPiece );
		  }
		  else {
		  Container parent = (Container)c;
		  parent.add( chessPiece );
		  }
		  chessPiece.setVisible(true);
		  finalCoord = new Coord(e.getX()/87,e.getY()/87);
		  chessGameControler.move(initCoord, finalCoord);
	}

}
