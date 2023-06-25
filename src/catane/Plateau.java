package catane;

import Observer.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Plateau extends Observable implements Serializable {
	// Le champ de la classe Plateau
	private HashMap<Point2D[], Joueur> route = new HashMap<Point2D[], Joueur>();
	private HashMap<Point2D, Joueur> colonie = new HashMap<Point2D, Joueur>();
	private HashMap<Point2D, Joueur> ville = new HashMap<Point2D, Joueur>();
	private Point2D voleur;

	// Infrastructures

	// Getter de la route
	public HashMap<Point2D[], Joueur> getRoute() {
		return route;
	}

	// Getter de la colonie
	public HashMap<Point2D, Joueur> getColonie() {
		return colonie;
	}

	// Getter de la ville
	public HashMap<Point2D, Joueur> getVille() {
		return ville;
	}

	// Getter de la position du voleur
	public Point2D getVoleur() {
		return voleur;
	}

	/**
	 * Construire la route
	 * La route sera représenté par deux coordonnées,
	 * l'une le début, l'autre la fin
	 * 
	 * @param debutPoint la coordonnée du début de la route
	 * @param finPoint   la coordonnée de la fin de la route
	 * @param joueur     le joueur qui construit la route
	 */
	public void buildRoute(Point2D debutPoint, Point2D finPoint, Joueur joueur) {
		int nbRoute = 0;
		for (Joueur value : route.values()) {
			if (value == joueur) {
				nbRoute += 1;
			}
		}

		Point2D[] listPoint = { debutPoint, finPoint };
		if (nbRoute < 15 && route.containsKey(listPoint) == false) {
			if (Math.abs((int) debutPoint.getX() - (int) finPoint.getX()) >= 2
					|| Math.abs((int) debutPoint.getY() - (int) finPoint.getY()) >= 2) {
				System.out.println("Les coordination sont invalides, veuillez saisir à nouveau.");
			} else {
				route.put(listPoint, joueur);
			}
		} else {
			System.out.println("Le nombre a atteint la limite ou coordination invalide.");
		}
	}

	// Construire la colonie selon le point 2D indiqué
	public void buildColonie(Point2D point, Joueur joueur) {
		int nbColonie = 0;
		for (Joueur value : colonie.values()) {
			if (value == joueur) {
				nbColonie += 1;
			}
		}
		if (nbColonie < 5 && colonie.containsKey(point) == false) {
			/**
			 * Un joueur ne peut pas construire des colonies
			 * sur des points adjacents de ses colonies déjà existés.
			 */
			ArrayList<Point2D> listPoint = new ArrayList<>();
			for (Point2D key : colonie.keySet()) {
				if (colonie.get(key).equals(joueur)) {
					listPoint.add(key);
				}
			}
			for (Point2D pointExistant : listPoint) {
				double x = pointExistant.getX();
				double y = pointExistant.getY();
				for (double i = x - 1; i <= x + 1; i++) {
					for (double j = y - 1; j <= y + 1; j++) {
						Point2D pointAdjacent = new Point2D.Double(i, j);
						if (point == pointAdjacent) {
							System.out.println("Coordination invalide.");
							break;
						} else {
							continue;
						}
					}
				}
			}
			colonie.put(point, joueur);
		} else {
			System.out.println("Le nombre a atteint la limite ou coordination invalide.");
		}
	}

	// Construire la ville selon le point 2D indiqué
	public void buildVille(Point2D point, Joueur joueur) {
		/**
		 * La ville est construite basée sur une colonie
		 * Il faut donc juger d'abord s'il y a une colonie de ce joueur
		 */
		int nbVille = 0;
		for (Joueur value : ville.values()) {
			if (value == joueur) {
				nbVille += 1;
			}
		}
		if (nbVille < 4 && ville.containsKey(point) == false && colonie.containsKey(point) == true) {
			ville.put(point, joueur);
			colonie.remove(point, joueur);
		} else {
			System.out.println("Le nombre a atteint la limite ou coordination invalide.");
		}

	}

	// Setter de la position du voleur
	public void setVoleur(Point2D point) {
		voleur = point;
	}

	public void afficheTout() {
		// Représentation textuelle du plateau
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				Point2D point2d = new Point2D.Double(i + 1, j + 1);
				System.out.print(" [ ");
				if (colonie.get(point2d) != null) {
					System.out.print("colonie");
				} else {
					System.out.print("*");
				}
				if (ville.get(point2d) != null) {
					System.out.print("ville");
				} else {
					System.out.print("*");
				}
				if (voleur.equals(point2d)) {
					System.out.print("voleur");
				} else {
					System.out.print("*");
				}
				System.out.print(" ] ");
			}
			System.out.println("\n");
		}
	}

}
