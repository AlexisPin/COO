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
import java.util.Arrays;
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
import model.PieceIHM;
import tools.ChessImageProvider;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

	private ChessGameControlers chessGameControler;
	JLabel chessPiece;
	JPanel chessBoard;
	JLayeredPane layeredPane;
	int xAdjustment;
	int yAdjustment;
	Coord initCoord;
	Coord finalCoord;
	JLabel piece;
	JPanel panel;
	
	public ChessGameGUI(String name, ChessGameControlers chessGameControler, Dimension boardSize) {
		this.chessGameControler = chessGameControler;

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

	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println(chessGameControler.getMessage() + "\n");	

		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg1;


		String[][] damier = new String[8][8];
		
		// création d'un tableau 2D avec les noms des pièces
		for(PieceIHM pieceIHM : piecesIHM) {

			Couleur color = pieceIHM.getCouleur();
			String type = pieceIHM.getTypePiece(); 
			List<Coord> previousCoord = pieceIHM.getList();
			for(Coord coord : pieceIHM.getList()) {
				piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(type, color)));
				panel = (JPanel)chessBoard.getComponent(coord.y*8 + coord.x);
				if(panel.getComponentCount() < 1) {
					panel.add(piece);
				}
			}			
		}
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
		  initCoord = getCoord(c);
		  if (c instanceof JPanel) 
		  return;
		 
		  Point parentLocation = c.getParent().getLocation();
		  xAdjustment = parentLocation.x - e.getX();
		  yAdjustment = parentLocation.y - e.getY();
		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	private Coord getCoord(Component c) {
		  Component square = c instanceof JLabel ? c.getParent() : c;
		  Container ech = square.getParent();
		  Component[] cases = ech.getComponents();
		  int indexCases = Arrays.asList(cases).indexOf(square);
		  int x = indexCases%8;
		  int y = indexCases/8;
		  return new Coord(x,y);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(chessPiece == null) return;
		 
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		  finalCoord = getCoord(c);
		  System.out.println(finalCoord);
		  if(chessGameControler.move(initCoord, finalCoord)) {	  
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
		  }

	}

}
