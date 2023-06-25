package catane;

import java.io.Serializable;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class Port implements Serializable {
	// Le champ de la classe Port
	private HashMap<String, Integer> ressourcePorte = new HashMap<String, Integer>();
	private int proportion;
	private String typeDeRessource;
	private Point2D[] pointAccessible;

	// Le constructeur de la classe Porte
	public Port(int proportion, String typeDeRessource, Point2D[] pointAccessible) {
		this.proportion = proportion;
		this.typeDeRessource = typeDeRessource;
		this.pointAccessible = pointAccessible;
	}

	// Les différents ports ont des taux d'échange différents
	public int getProportion() {
		return proportion;
	}

	// Les différents ports ont des types d'échange différents
	public String getTypeDeRessource() {
		return typeDeRessource;
	}

	/**
	 * Seuls les joueurs ayant des constructeurs
	 * à l'un des quatre points correspondant au port
	 * peuvent utiliser ce port.
	 */
	public Point2D[] getPointAccessible() {
		return pointAccessible;
	}

	// Recevoir la ressource indiquée
	public void recevoirRessources(String r, int nb) {
		ressourcePorte.put(r, ressourcePorte.get(r) + nb);
	}

	// Perdre la ressource indiquée
	public void perdreRessource(String r, int nb) {
		ressourcePorte.put(r, ressourcePorte.get(r) - nb);
	}

}
