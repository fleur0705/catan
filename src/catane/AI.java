package catane;

import java.util.HashMap;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class AI extends Personne implements Serializable {
	// Le champ de la classe AI qui hérite la classe Personne
	private static int ID;
	String name = "AI";
	private HashMap<String, Integer> ressource = new HashMap<String, Integer>();
	private HashMap<String, Integer> carte = new HashMap<String, Integer>();
	private int carteChevalierUtilise = 0;
	private int score = 0;
	String[] ressources = { "Bois", "Laine", "Ble", "Argile", "Mineral" };
	String[] cartes = { "Chevaliers", "Progrès: Construction de Route", "Progrès: Invention", "Progrès: Monopole",
			"Victoire" };

	// Le constructeur de la classe AI
	public AI(String name) {
		super(name);
	}

	// Recevoir des ressources aléatoires
	public void recevoirRessources(int nb) {
		int r = (int) (1 + Math.random() * (5 - 1 + 1));
		ressource.put(ressources[r], ressource.get(ressources[r]) + nb);
		notifyObserver(ressource);
	}

	/**
	 * Dépenser les ressources correspondantes, construire des routes, des colonies
	 * et des villes
	 */
	public void buildRoute(Plateau p) {
		double x = (double) (1 + Math.random() * (6 - 1 + 1));
		double y = (double) (1 + Math.random() * (6 - 1 + 1));
		double a = (double) (1 + Math.random() * (6 - 1 + 1));
		double b = (double) (1 + Math.random() * (6 - 1 + 1));
		Point2D pointDebut = new Point2D.Double(x, y);
		Point2D pointFin = new Point2D.Double(a, b);
		p.buildRoute(pointDebut, pointFin, this);
		perdreRessource("Argile", 1);
		perdreRessource("Bois", 1);
		notifyObserver(ressource);
		notifyObserver(p.getRoute());
	}

	// Construire une colonie sur un point aléatoire
	public void buildColonie(Plateau p) {
		double x = (double) (1 + Math.random() * (6 - 1 + 1));
		double y = (double) (1 + Math.random() * (6 - 1 + 1));
		Point2D point = new Point2D.Double(x, y);
		p.buildColonie(point, this);
		perdreRessource("Argile", 1);
		perdreRessource("Bois", 1);
		perdreRessource("Laine", 1);
		perdreRessource("Ble", 1);
		score += 1;
		notifyObserver(p.getColonie());
	}

	// Construire une ville sur un point aléatoire
	public void buildVille(Plateau p) {
		double x = (double) (1 + Math.random() * (6 - 1 + 1));
		double y = (double) (1 + Math.random() * (6 - 1 + 1));
		Point2D point = new Point2D.Double(x, y);
		p.buildVille(point, this);
		perdreRessource("Mineral", 3);
		perdreRessource("Ble", 2);
		score += 2;
		notifyObserver(p.getVille());
	}

	/**
	 * Utiliser la carte de développement et constuire la ville
	 * dans un point aléatoire
	 */
	public void utiliserCarteProgresConstruction(Plateau p) {
		double x1 = (double) (1 + Math.random() * (6 - 1 + 1));
		double y1 = (double) (1 + Math.random() * (6 - 1 + 1));
		double x2 = (double) (1 + Math.random() * (6 - 1 + 1));
		double y2 = (double) (1 + Math.random() * (6 - 1 + 1));

		Point2D point1 = new Point2D.Double(x1, y1);
		Point2D point2 = new Point2D.Double(x2, y2);
		p.buildVille(point1, this);
		p.buildVille(point2, this);
		notifyObserver(p.getVille());
	}

	/**
	 * Utiliser la carte de Monopole et preveler une ressource aléatoire de chaque
	 * joueur
	 * 
	 * @param joueurs liste des joueurs
	 */
	public void utiliserCarteProgresMonopole(Joueur[] joueurs) {
		int s = (int) (1 + Math.random() * (5 - 1 + 1));
		for (Joueur joueur : joueurs) {
			joueur.setRessource(ressources[s], 0);
			notifyObserver(joueur.getRessource());
		}
	}

	/**
	 * Si la somme des deux dés est 7,
	 * les événements de voleur seront activées
	 */
	public void perdreRessource(String r, int nb) {
		ressource.put(r, ressource.get(r) - nb);
		notifyObserver(ressource);
	}

	// Récupérer la ressource au hasard de chaque joueur
	public void prevelerRessource(Joueur joueur) {
		int r = (int) (1 + Math.random() * (5 - 1 + 1));
		joueur.perdreRessource(ressources[r], 1);
		recevoirRessources(ressources[r], 1);
		notifyObserver(ressource);
		notifyObserver(joueur.getRessource());
	}

	/**
	 * Utiliser la carte chevalier et choisir un point aléatoire
	 * pour déplacer le voleur
	 */
	public void utiliserCarteChevalier(Plateau plateau) {
		double x = (double) (1 + Math.random() * (6 - 1 + 1));
		double y = (double) (1 + Math.random() * (6 - 1 + 1));
		carteChevalierUtilise += 1;
		deplacerVoleur((int) x, (int) y, plateau);
		notifyObserver(plateau.getVoleur());
	}

}
