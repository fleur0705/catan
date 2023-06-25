package catane;

import java.util.Collection;
import java.util.Scanner;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

public class Runner implements Serializable {
    // Le champ de la classe Runner
    ArrayList<Joueur> joueurs;
    String victor;
    ArrayList<Port> ports;
    String[] cartes = { "Chevaliers", "Progrès: Construction de Route", "Progrès: Invention", "Progrès: Monopole",
            "Victoire" };
    // Le nombre de joueur
    Scanner scanner = new Scanner(System.in);
    int nbJoueur = scanner.nextInt();
    // Le mom de joueur
    for(
    int x = 0;x<nbJoueur;x++)
    {
        String name = scanner.nextLine();
        Personne personne = new Personne(name);
        joueurs.add(personne);
    }

    // Créer le plateau 6x6
    Plateau plateau = new Plateau();

    // Créer les cases
    String[] listCase = { "Forêt", "Forêt", "Forêt", "Pré", "Pré", "Pré", "Champs", "Champs", "Champs",
            "Colline",
            "Colline", "Colline", "Montagne", "Montagne", "Montagne" };
    String desertCase = "Desert";
    int[] listNumber = { 6, 10, 11, 8, 4, 9, 5, 12, 3, 10, 6, 9, 8, 5,
            2 };Collection.shuffle(listCase);Collection.shuffle(listNumber);
    ArrayList<Case> cases;for(
    int i = 0;i<listCase.size();i++)
    {
        Point2D p1 = new Point2D.Double(i, i);
        Point2D p2 = new Point2D.Double(i, i + 1);
        Point2D p3 = new Point2D.Double(i + 1, i);
        Point2D p4 = new Point2D.Double(i + 1, i + 1);
        Point2D[] listPoint = { p1, p2, p3, p4 };
        Case c = new Case(listCase[i], listNumber[i], listPoint);
        cases.add(c);
    }

    // Créer les ports
    Point2D point1 = new Point2D.Double(0, 0);
    Point2D point2 = new Point2D.Double(0, 1);
    Point2D point3 = new Point2D.Double(1, 0);
    Point2D point4 = new Point2D.Double(1, 1);
    Point2D[] pointList1 = { point1, point2, point3, point4 };
    Port port1 = new Port(2, "Pierre", pointList1);ports.add(port1);

    Point2D point5 = new Point2D.Double(2, 0);
    Point2D point6 = new Point2D.Double(3, 0);
    Point2D point7 = new Point2D.Double(2, 1);
    Point2D point8 = new Point2D.Double(3, 1);
    Point2D[] pointList2 = { point5, point6, point7, point8 };
    Port port2 = new Port(2, "Argile", pointList2);ports.add(port2);

    Point2D point9 = new Point2D.Double(4, 1);
    Point2D point10 = new Point2D.Double(4, 0);
    Point2D[] pointList3 = { point6, point9, point8, point1 };
    Port port3 = new Port(3, "", pointList3);ports.add(port3);

    Point2D point11 = new Point2D.Double(0, 2);
    Point2D point12 = new Point2D.Double(1, 2);
    Point2D[] pointList4 = { point2, point4, point11, point12 };
    Port port4 = new Port(2, "Ble", pointList4);ports.add(port4);

    Point2D point13 = new Point2D.Double(3, 2);
    Point2D point14 = new Point2D.Double(4, 2);
    Point2D point15 = new Point2D.Double(3, 3);
    Point2D point16 = new Point2D.Double(4, 3);
    Point2D[] pointList5 = { point13, point14, point15, point16 };
    Port port5 = new Port(2, "Bois", pointList5);ports.add(port5);

    Point2D point17 = new Point2D.Double(0, 3);
    Point2D point18 = new Point2D.Double(0, 4);
    Point2D point19 = new Point2D.Double(1, 3);
    Point2D point20 = new Point2D.Double(1, 4);
    Point2D[] pointList6 = { point17, point18, point19, point20 };
    Port port6 = new Port(3, "", pointList6);ports.add(port6);

    Point2D point21 = new Point2D.Double(2, 3);
    Point2D point22 = new Point2D.Double(2, 4);
    Point2D[] pointList7 = { point21, point22, point19, point20 };
    Port port7 = new Port(2, "Mouton", pointList7);ports.add(port7);

    Point2D point23 = new Point2D.Double(3, 4);
    Point2D point24 = new Point2D.Double(4, 4);
    Point2D[] pointList8 = { point15, point16, point23, point24 };
    Port port8 = new Port(3, "", pointList8);ports.add(port8);

    // Créer le banque
    Banque banque = new Banque();

    // Lancer le jeu
    while(true)
    {
        for (Joueur joueur : joueurs) {
            // Chaque joueur fait sa construction
            System.out.println("Est-ce que vous voulez construire un colone? (oui/non)");
            String actionColonie = scanner.nextLine();
            if (actionColonie == "oui") {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                joueur.buildColonie(x, y, plateau);
                joueur.setScore(joueur.getScore() + 1);
            }
            System.out.println("Est-ce que vous voulez construire une ville? (oui/non)");
            String actionVille = scanner.nextLine();
            if (actionVille == "oui") {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                joueur.buildVille(x, y, plateau);
                joueur.setScore(joueur.getScore() + 1);
            }
            // Chaque joueur utilise une carte indiquée
            System.out.println("Est-ce que vous voulez utiliser des cartes? (oui/non)");
            String actionCarte = scanner.nextLine();
            if (actionCarte == "oui") {
                System.out.println("Vous voulez utiliser combien de carte? ");
                int nbCarte = scanner.nextInt();
                for (int i = 0; i < nbCarte; i++) {
                    System.out.println("Vous voulez utiliser quelle carte? ");
                    String carteUtilise = scanner.nextLine();
                    if (carteUtilise == "Victoire") {
                        joueur.utiliserCarteVictoire();
                    } else if (carteUtilise == "Progrès: Monopole") {
                        System.out.println("Quelle ressource?");
                        String monoRessource = scanner.nextLine();
                        joueur.utiliserCarteProgresMonopole(joueurs, monoRessource);
                    } else if (carteUtilise == "Progrès: Invention") {
                        joueur.utiliserCarteProgresInvention(banque);
                    } else if (carteUtilise == "Progrès: Construction de Route") {
                        System.out.println("Saisir les coordinations ");
                        System.out.println("Point debut");
                        int x1 = scanner.nextInt();
                        int y1 = scanner.nextInt();
                        System.out.println("Point fin");
                        int x2 = scanner.nextInt();
                        int y2 = scanner.nextInt();
                        joueur.utiliserCarteProgresConstruction(x1, y1, x2, y2, plateau);
                    } else {
                        System.out.println("Saisir les coordinations ");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        joueur.utiliserCarteChevalier(x, y, plateau);
                    }
                }
            }
            // Acheter une carte
            System.out.println("Est-ce que vous voulez acheter des cartes? (oui/non)");
            String acheterCarte = scanner.nextLine();
            if (acheterCarte == "oui") {
                System.out.println("Vous voulez acheter quelle carte? (oui/non)");
                String carte = scanner.nextLine();
                joueur.acheterCarte(banque, carte);
            }
            // Demander au joueur s'il veut faire l'échange et si oui, avec qui
            System.out.println("Est-ce que vous voulez echange des cartes? (oui/non)");
            String echangeCarte = scanner.nextLine();
            if (echangeCarte == "oui") {
                String addRessoure = scanner.nextLine();
                String minsRessource = scanner.nextLine();
                int addNb = scanner.nextInt();
                int minNb = scanner.nextInt();
                System.out.println("Vous voulez echanger avec qui? (Joueur / Banque / Port)");
                String objetEchange = scanner.nextLine();
                if (objetEchange == "Joueur") {
                    System.out.println("Le nom de Joueur");
                    String nomDeJoueur = scanner.nextLine();
                    for (Joueur j : joueurs) {
                        if (j.getName() == nomDeJoueur) {
                            joueur.exchangeRessourcePlayer(addRessoure, minsRessource, addNb, minNb, j);
                        }
                    }
                } else if (objetEchange == "Banque") {
                    joueur.exchangeRessourceBanque(addRessoure, minsRessource, addNb, banque);
                } else {
                    System.out.println("Saisir le numero de port:");
                    int numeroPort = scanner.nextInt();
                    for (Point2D p : plateau.getVille().keySet()) {
                        if (ports.contains(p) && plateau.getVille().get(p) == joueur) {
                            joueur.exchangeRessourcePort(addRessoure, minsRessource, addNb, ports.get(numeroPort));
                        } else {
                            for (Point2D p1 : plateau.getColonie().keySet()) {
                                if (ports.contains(p1) && plateau.getColonie().get(p1) == joueur) {
                                    joueur.exchangeRessourcePort(addRessoure, minsRessource, addNb,
                                            ports.get(numeroPort));
                                } else {
                                    System.out.println("Vous ne pouvez pas echanger avec ce port.");
                                }
                            }
                        }
                    }
                }
            }
            // Jeter les deux dés
            int sumDeDes = joueur.coupDeDes();
            /*
             * Si la somme des deux dés est égale à 7, une invasion de bandits se produit.
             * 
             * Tout d'abord, chaque joueur compte les cartes ressources qu'il a en main et
             * si le nombre dépasse 7 (en commençant par 8),
             * il doit défausser la moitié du nombre de ressources à la banque, en
             * arrondissant après la virgule. C'est-à-dire 8 écart 4, 9 écart 4, 10 écart 5.
             * 
             * Le joueur au tour actuel doit déplacer la pièce Bandit et l'appuyer sur un
             * numéro de point de ressource et tirer au hasard une ressource du joueur qui
             * construit une maison autour de ce point de ressource.
             * S'il y a plus d'un joueur qui construit une maison à ce point de ressource,
             * un seul peut être choisi pour être tiré au sort.
             */
            if (sumDeDes == 7) {
                for (Joueur j : joueurs) {
                    if (j.doitJeter()) {
                        j.jeterRessource(banque);
                    }
                }
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                joueur.deplacerVoleur(x, y, plateau);
                int ID = scanner.nextInt();
                String ressourcePrelever = scanner.nextLine();
                joueur.prevelerRessource(joueurs.get(ID), ressourcePrelever);
            } else {
                /*
                 * Si un nombre autre que 7 est jeté,
                 * le terrain correspondant au nombre sur le panneau produit des ressources et
                 * le joueur ayant une maison construite à côté de ce terrain peut prendre les
                 * ressources correspondantes dans la banque
                 */
                // Parcourir toutes les case
                for (Case c : cases) {
                    Point2D[] points = c.getListPoint();
                    // Parcours chaque point de chaque case
                    for (Point2D p : points) {
                        HashMap<Point2D, Joueur> colonie = plateau.getColonie();
                        HashMap<Point2D, Joueur> ville = plateau.getVille();
                        /*
                         * Vérifier si le joueur a construit une colonie sur ce point,
                         * si la banque a assez de ressources, si le voleur est sur ce point (le voleur
                         * ne peut pas frayer sur un point où il est).
                         */
                        if (colonie.containsKey(p) && banque.getRessource().get(c.getNomCase()) >= 1
                                && plateau.getVoleur() == p) {
                            Joueur j = colonie.get(p);
                            j.setRessource(c.getNomCase(), 1);
                            banque.attribuerRessource(c.getNomCase(), 1);
                        }
                        if (ville.containsKey(p) && banque.getRessource().get(c.getNomCase()) >= 1
                                && plateau.getVoleur() == p) {
                            Joueur j = ville.get(p);
                            j.setRessource(c.getNomCase(), 2);
                            banque.attribuerRessource(c.getNomCase(), 2);
                        }
                    }
                }
            }
        }
        // Vérifier s'il y a un joueur qui a 10 points.
        for (Joueur joueur : joueurs) {
            // Le joueur qui atteint 10 points gagne
            if (joueur.getScore() == 10) {
                victor = joueur.getName();
                break;
            }
        }
    }System.out.println("Victor: "+victor);

}

}
