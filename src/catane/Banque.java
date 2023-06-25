package catane;

import java.io.Serializable;
import java.util.HashMap;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Banque implements Serializable {
	// Le champ de la classe Banque
	private HashMap<String, Integer> ressourceBanque = new HashMap<String, Integer>();
	private HashMap<String, Integer> carteBanque = new HashMap<String, Integer>();
	private Joueur chevalierLePlusPuissant;
	private Joueur joueurRouteLaPlusLongue;
	private int nbRouteLaPlusLongue;

	// Initialiser toutes les ressources et cartes
	public Banque() {
		ressourceBanque.put("Bois", 19);
		ressourceBanque.put("Laine", 19);
		ressourceBanque.put("Ble", 19);
		ressourceBanque.put("Argile", 19);
		ressourceBanque.put("Mineral", 19);

		carteBanque.put("Chevaliers", 14);
		carteBanque.put("Progrès: Construction de Route", 2);
		carteBanque.put("Progrès: Invention", 2);
		carteBanque.put("Progrès: Monopole", 2);
		carteBanque.put("Victoire", 5);
	}

	// Setter de la ressource
	public HashMap<String, Integer> getRessource() {
		return ressourceBanque;
	}

	// Setter de la carte
	public HashMap<String, Integer> getCarte() {
		return carteBanque;
	}

	// Recevoir la ressource du joueur
	public void recevoirRessources(String r, int nb) {
		ressourceBanque.put(r, ressourceBanque.get(r) + nb);
	}

	// Attribuer les ressources
	public void attribuerRessource(String r, int nb) {
		ressourceBanque.put(r, ressourceBanque.get(r) - nb);
	}

	// Attribuer une ressource aléatoire à chaque joueur
	public String attribuerRessource() {
		// Avoir un nombre aléatoire
		int r = (int) (1 + Math.random() * (5 - 1 + 1));
		/*
		 * Chaque numéro correspond à une ressource,
		 * et si le nombre de ressources actuellement détenues par la banque est
		 * supérieur ou égal à 1, la ressource est distribuée à un joueur.
		 */
		if (r == 1 && ressourceBanque.get("Bois") >= 1) {
			String s = "Bois";
			ressourceBanque.put(s, ressourceBanque.get(s) - 1);
			return s;
		} else if (r == 2 && ressourceBanque.get("Laine") >= 1) {
			String s = "Laine";
			ressourceBanque.put(s, ressourceBanque.get(s) - 1);
			return s;
		} else if (r == 3 && ressourceBanque.get("Ble") >= 1) {
			String s = "Ble";
			ressourceBanque.put(s, ressourceBanque.get(s) - 1);
			return s;
		} else if (r == 4 && ressourceBanque.get("Argile") >= 1) {
			String s = "Argile";
			ressourceBanque.put(s, ressourceBanque.get(s) - 1);
			return s;
		} else if (r == 5 && ressourceBanque.get("Mineral") >= 1) {
			String s = "Mineral";
			ressourceBanque.put(s, ressourceBanque.get(s) - 1);
			return s;
		} else {
			return "Il n'y a plus de ressource.";
		}
	}

	// Attribuer une carte aléatoire à chaque joueur
	public String attribuerCarte() {
		// Obtenir un nombre aléatoire
		int r = (int) (1 + Math.random() * (5 - 1 + 1));
		/*
		 * Chaque numéro correspond à une carte,
		 * et si le nombre de cartes actuellement détenues par la banque est supérieur
		 * ou égal à 1, cette carte est distribuée à un joueur.
		 */
		if (r == 1 && carteBanque.get("Chevaliers") >= 1) {
			String c = "Chevaliers";
			carteBanque.put(c, carteBanque.get(c) - 1);
			return c;
		} else if (r == 2 && carteBanque.get("Progrès: Construction de Route") >= 1) {
			String c = "Progrès: Construction de Route";
			carteBanque.put(c, carteBanque.get(c) - 1);
			return c;
		} else if (r == 3 && carteBanque.get("Progrès: Invention") >= 1) {
			String c = "Progrès: Invention";
			carteBanque.put(c, carteBanque.get(c) - 1);
			return c;
		} else if (r == 4 && carteBanque.get("Progrès: Monopole") >= 1) {
			String c = "Progrès: Monopole";
			carteBanque.put(c, carteBanque.get(c) - 1);
			return c;
		} else if (r == 5 && carteBanque.get("Victoire") >= 1) {
			String c = "Victoire";
			carteBanque.put(c, carteBanque.get(c) - 1);
			return c;
		} else {
			return "Il n'y a plus de carte.";
		}
	}

	/*
	 * Parcourir Hashmap route，
	 * si la longueur des routes consécutives d'un joueur
	 * a dépassé la longueur la plus grande actuellement enregistrée,
	 * deux points seront enlevés au joueur précédent qui avait la route la plus
	 * longue,
	 * et deux points seront ajoutés au joueur qui l'a maintenant.
	 */
	
	public boolean routeLaPlusLongue(Plateau plateau) {
		Joueur nomJoueur = null;
		int maxPre = 0;
		for (Joueur joueur : plateau.getRoute().values()) {
			int maxSec = 0;
			for (Point2D[] routePre : plateau.getRoute().keySet()) {
				for (Point2D[] routeSec : plateau.getRoute().keySet()) {
					if (routePre[1] == routeSec[0]) {
						maxSec++;
					}
				}
				if (maxSec > maxPre) {
					maxPre = maxSec;
				}
			}
			nomJoueur = joueur;
		}
		if (maxPre > nbRouteLaPlusLongue) {
			joueurRouteLaPlusLongue.setScore(joueurRouteLaPlusLongue.getScore() - 2);
			joueurRouteLaPlusLongue = nomJoueur;
			joueurRouteLaPlusLongue.setScore(joueurRouteLaPlusLongue.getScore() + 2);
			nbRouteLaPlusLongue = maxPre;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Si un joueur a utilisé le plus de cartes chevalier
	 * et au moins trois fois
	 * 2 points seront ajouté à ce joueur.
	 * Si un autre joueur le dépasse,
	 * le nouveau joueur acquiert 2 points,
	 * tandis que l'ancien joueur perd 2 points.
	 */

	public boolean chevalierLePlusPuissant(ArrayList<Joueur> joueurs) {
		int max = 0;
		for (Joueur joueur : joueurs) {
			int nbChevalier = joueur.getCarteChevalierUtilise();
			if (nbChevalier > max && nbChevalier >= 3 && joueur != chevalierLePlusPuissant) {
				max = nbChevalier;
				Joueur ancienChevalier = chevalierLePlusPuissant;
				chevalierLePlusPuissant = joueur;
				joueur.setScore(joueur.getScore() + 2);
				ancienChevalier.setScore(ancienChevalier.getScore() - 2);
				return true;
			}
		}
		return false;
	}

	// Vérifier s'il y a un joueur qui a 10 points.
	public boolean gagne(ArrayList<Joueur> joueurs) {
		// Si un joueur atteint 10 points, il gagne
		for (Joueur joueur : joueurs) {
			if (joueur.getScore() >= 10) {
				return true;
			}
		}
		return false;
	}
}
