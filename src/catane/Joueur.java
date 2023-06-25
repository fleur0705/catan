package catane;

import java.io.Serializable;
import Observer.Observable;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class Joueur extends Observable implements Serializable {
	// Le champs de la classe abstracte Joueur
	private static int ID;
	private String name;
	private int score = 0;
	private int carteChevalierUtilise = 0;

	private HashMap<String, Integer> ressource = new HashMap<String, Integer>();
	private HashMap<String, Integer> carte = new HashMap<String, Integer>();

	// Le constructeur de la classe abstracte Joueur
	public Joueur(String name) {
		this.name = name;
		ID++;
		ressource.put("Bois", 0);
		ressource.put("Laine", 0);
		ressource.put("Ble", 0);
		ressource.put("Argile", 0);
		ressource.put("Mineral", 0);

		carte.put("Chevaliers", 0);
		carte.put("Progrès: Construction de Route", 0);
		carte.put("Progrès: Invention", 0);
		carte.put("Progrès: Monopole", 0);
		carte.put("Victoire", 0);
	}

	// Information de joueur

	// Getter d'identifiant du joueur
	public int getID() {
		return ID;
	}

	// Getter du nom du joueur
	public String getName() {
		return name;
	}

	// Getter du score du joueur
	public int getScore() {
		return score;
	}

	// Getter de la carte chevalier
	public int getCarteChevalierUtilise() {
		return carteChevalierUtilise;
	}

	// Getter de la liste des ressources
	public HashMap<String, Integer> getRessource() {
		return ressource;
	}

	// Getter de la liste des cartes
	public HashMap<String, Integer> getCarte() {
		return carte;
	}

	// Setter du score du joueur
	public void setScore(int newScore) {
		score = newScore;
	}

	// Setter de la ressource du joueur
	public void setRessource(String r, int nb) {
		ressource.put(r, nb);
	}

	// Action

	// Jeter les deux des et retourne la somme
	public int coupDeDes() {
		int a = (int) (Math.random() * 6) + 1;
		int b = (int) (Math.random() * 6) + 1;
		notifyObserver(a + b);
		return a + b;
	}

	// Si le résultat du dés n'égale pas 7


	public abstract void recevoirRessources(String r, int nb);

	public abstract void buildRoute(double x, double y, double a, double b, Plateau p);

	public abstract void buildColonie(double x, double y, Plateau p);

	public abstract void buildVille(double x, double y, Plateau p);

	public abstract void exchangeRessourcePlayer(String addRessoure, String minsRessource, int addNb, int minNb,
			Joueur joueur);

	public abstract void exchangeRessourcePort(String addRessoure, String minsRessource, int nb, Port port);

	public abstract void exchangeRessourceBanque(String addRessoure, String minsRessource, int nb, Banque banque);

	public abstract void utiliserCarteVictoire();

	public abstract void utiliserCarteProgresConstruction(double x1, double y1, double x2, double y2, Plateau p);

	public abstract void utiliserCarteProgresInvention(Banque banque);

	public abstract void utiliserCarteProgresMonopole(ArrayList<Joueur> joueurs, String s);

	public abstract void acheterCarte(Banque banque, String c);

	public abstract void recevoirCarte(String c);

	public abstract void perdreCarte(String c);

	// Si on a 7 aux dés
	
	public abstract boolean doitJeter();

	public abstract void jeterRessource(Banque banque);

	public abstract void perdreRessource(String r, int nb);

	public abstract void deplacerVoleur(int x, int y, Plateau plateau);

	public abstract void prevelerRessource(Joueur joueur, String r);

	public abstract void utiliserCarteChevalier(int x, int y, Plateau plateau);

	public void afficherInfoDeJoueur() {
		System.out.println("---Infomation de Joueur---");
		System.out.println("ID: " + ID);
		System.out.println("Le nom: " + name);
		System.out.println("Le score: " + score);
		System.out.println("Ressource: " + score);
		for (String r : ressource.keySet()) {
			System.out.print(r + " : " + ressource.get(r) + "; ");
		}
		System.out.println("Carte: " + score);
		for (String c : carte.keySet()) {
			System.out.print(c + " : " + carte.get(c) + "; ");
		}
	}
}
