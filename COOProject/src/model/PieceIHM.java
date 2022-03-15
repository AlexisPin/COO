package model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author francoise.perrin
 * Classe qui permet de retourner des informations sur les pièces
 * en vue d'une utilisation par une IHM
 * 
 */
public  class PieceIHM {
	 
	String type;
	Couleur couleur;
	List<Coord> list;
	
	PieceIHM(String type, Couleur couleur) {
		this.type = type;
		this.couleur = couleur;
		list = new LinkedList<Coord>();
	}
	
	public void add(Coord coord){
		list.add(coord);
	}

	/**
	 * @return the type
	 */
	public String getTypePiece() {
		return type;
	}

	/**
	 * @return the couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * @return the list
	 */
	public List<Coord> getList() {
		return list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PieceIHM [type=" + type + ", couleur=" + couleur + ", list="
				+ list + "]";
	}
}