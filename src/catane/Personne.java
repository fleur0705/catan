package catane;

import java.io.Serializable;
import java.util.HashMap;
import java.awt.geom.Point2D;
import java.util.Scanner;
import java.util.ArrayList;

public class Personne extends Joueur implements Serializable {
	// Le champ de la classe Personne qui hérite la classe abstraite Joueur
	private String name;
	private int score = 0;
	private int carteChevalierUtilise = 0;
	private HashMap<String, Integer> ressource = new HashMap<String, Integer>();
	private HashMap<String, Integer> carte = new HashMap<String, Integer>();

	// Le constructeur
	public Personne(String name) {
		super(name);
	}

	// Action

	/**
	 * Si la somme des dés n'égale pas 7,
	 * les joueurs occupant les cases correspondantes
	 * reçoivent des ressources.
	 */
	public void recevoirRessources(String r, int nb) {
		ressource.put(r, ressource.get(r) + nb);
		notifyObserver(ressource);
	}

	// Dépenser une argile et un bois pour construire une route
	public void buildRoute(double x, double y, double a, double b, Plateau p) {
		Point2D pointDebut = new Point2D.Double(x, y);
		Point2D pointFin = new Point2D.Double(a, b);
		p.buildRoute(pointDebut, pointFin, this);
		perdreRessource("Argile", 1);
		perdreRessource("Bois", 1);
		notifyObserver(p.getRoute());
	}

	// Dépenser une argile, un bois, une laine et un blé pour construire une colonie
	public void buildColonie(double x, double y, Plateau p) {
		Point2D point = new Point2D.Double(x, y);
		p.buildColonie(point, this);
		perdreRessource("Argile", 1);
		perdreRessource("Bois", 1);
		perdreRessource("Laine", 1);
		perdreRessource("Ble", 1);
		score += 1;
		notifyObserver(p.getColonie());
	}

	// Dépenser un mineral et un blé pour construire une ville
	public void buildVille(double x, double y, Plateau p) {
		Point2D point = new Point2D.Double(x, y);
		p.buildVille(point, this);
		perdreRessource("Mineral", 3);
		perdreRessource("Ble", 2);
		score += 2;
		notifyObserver(p.getVille());
	}

	// Échange des ressources entre les joueurs
	public void exchangeRessourcePlayer(String addRessoure, String minsRessource, int addNb, int minNb, Joueur joueur) {
		recevoirRessources(addRessoure, addNb);
		perdreRessource(minsRessource, minNb);
		joueur.recevoirRessources(minsRessource, minNb);
		joueur.perdreRessource(addRessoure, addNb);
		notifyObserver(this.ressource);
		notifyObserver(joueur.getRessource());
	}

	// Échange des ressources avec le port
	public void exchangeRessourcePort(String addRessoure, String minsRessource, int nb, Port port) {
		recevoirRessources(addRessoure, nb);
		port.perdreRessource(addRessoure, nb);

		perdreRessource(minsRessource, port.getProportion() * nb);
		port.recevoirRessources(minsRessource, port.getProportion() * nb);

		notifyObserver(ressource);
	}

	// Échange des ressources avec la banque.
	public void exchangeRessourceBanque(String addRessoure, String minsRessource, int nb, Banque banque) {
		recevoirRessources(addRessoure, nb);
		perdreRessource(minsRessource, 4 * nb);
		banque.recevoirRessources(minsRessource, 4 * nb);
		banque.attribuerRessource(addRessoure, nb);
		notifyObserver(ressource);
		notifyObserver(banque.getRessource());
	}

	// Utiliser la carte victoire et recevoir un point
	public void utiliserCarteVictoire() {
		score += 1;
		notifyObserver(score);
	}

	// Utiliser la carte construction pour construire une ville
	public void utiliserCarteProgresConstruction(double x1, double y1, double x2, double y2, Plateau p) {
		Point2D point1 = new Point2D.Double(x1, y1);
		Point2D point2 = new Point2D.Double(x2, y2);
		p.buildVille(point1, this);
		p.buildVille(point2, this);
		notifyObserver(p.getVille());
	}

	// Utiliser la carte invention pour obtenir une ressource aléatoire de la banque
	public void utiliserCarteProgresInvention(Banque banque) {
		for (int i = 0; i < 2; i++) {
			String s = banque.attribuerRessource();
			if (s != "Il n'y a plus de ressource.") {
				this.recevoirRessources(s, 1);
			}
		}
		notifyObserver(ressource);
	}

	// Utiliser la carte monopole pour récupérer une ressource d'un joueur désigné
	public void utiliserCarteProgresMonopole(ArrayList<Joueur> joueurs, String s) {
		for (Joueur joueur : joueurs) {
			joueur.setRessource(s, 0);
		}
		notifyObserver(ressource);
	}

	// Dépenser un mineral, une laine et un blé pour acheter une carte
	public void acheterCarte(Banque banque, String c) {
		perdreRessource("Mineral", 1);
		perdreRessource("Laine", 1);
		perdreRessource("Ble", 1);
		banque.attribuerCarte();
		if (c != "Il n'y a plus de carte.") {
			recevoirCarte(c);
		}
		notifyObserver(ressource);
		notifyObserver(carte);
	}

	// Recevoir une carte désigné
	public void recevoirCarte(String c) {
		carte.put(c, ressource.get(c) + 1);
		notifyObserver(carte);
	}

	// Perdre une carte désigné
	public void perdreCarte(String c) {
		carte.put(c, ressource.get(c) - 1);
		notifyObserver(ressource);
		notifyObserver(carte);
	}

	// Quand la somme des deux dés est égale à 7, le voleur apparaît.
	public boolean doitJeter() {
		int nbRessource = 0;
		for (Integer value : ressource.values()) {
			nbRessource += value;
		}
		/**
		 * Si le nombre total de ressources d'un joueur est supérieur ou égal à 8,
		 * il faut rejeter la moitié de ses ressources.
		 */
		if (nbRessource >= 8) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Si la méthode doitJeter retourne true,
	 * appeler cette méthode pour rejeter des ressources
	 */
	public void jeterRessource(Banque banque) {
		int nbRessource = 0;
		for (Integer value : ressource.values()) {
			nbRessource += value;
		}

		int nbJeter = nbRessource / 2;
		Scanner input = new Scanner(System.in);

		// Le joueur choisit une ressource pour la banque
		while (nbJeter > 0) {
			String s = input.next();
			int nb = input.nextInt();
			perdreRessource(s, nb);
			banque.recevoirRessources(s, nb);
			nbJeter -= nb;
		}
		input.close();
	}

	// Jeter une ressource spécifique
	public void perdreRessource(String r, int nb) {
		ressource.put(r, ressource.get(r) - nb);
		notifyObserver(ressource);
	}

	// Si la somme de deux dés est 7, ce joueur peut déplacer la position de voleur
	public void deplacerVoleur(int x, int y, Plateau plateau) {
		Point2D point = new Point2D.Double(x, y);
		plateau.setVoleur(point);
		notifyObserver(plateau.getVoleur());
	}

	/**
	 * Si la somme de deux dés est 7
	 * ce joueur peut preveler la ressource d'un joueur spécifié
	 */
	public void prevelerRessource(Joueur joueur, String r) {
		joueur.perdreRessource(r, 1);
		recevoirRessources(r, 1);
		notifyObserver(ressource);
	}

	// Utiliser la carte chevalier pour déplacer la position du voleur
	public void utiliserCarteChevalier(int x, int y, Plateau plateau) {
		carteChevalierUtilise += 1;
		deplacerVoleur(x, y, plateau);
		notifyObserver(carte);
	}

}
