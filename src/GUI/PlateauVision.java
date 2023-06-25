package GUI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import Observer.Observable;
import Observer.Observer;

import javax.swing.*;

public class PlateauVision extends JFrame implements Observer {
    private static final int largeur = 900;
    private static final int hauteur = 800;
    int nbJoueurs = 0;

    PlateauVision(int nbJoueurs, ArrayList<String> nomsJoueurs) {
        this.nbJoueurs = nbJoueurs;

        // Création du panel pour afficher le plateau
        JPanel panelPlateau = new JPanel();
        panelPlateau.setOpaque(true);
        panelPlateau.setBackground(new Color(155, 118, 83));
        panelPlateau.setPreferredSize(new Dimension(300, 200));
        panelPlateau.setLayout(new GridLayout(4, 4));

        // Création manuelle des cases du plateau
        JButton foret6 = new JButton("Forêt" + "\n" + "6");
        setButton(foret6);
        setImage("src/GUI/Images/forêt.png", foret6);

        JButton pre10 = new JButton("Pré 10");
        setButton(pre10);
        setImage("src/GUI/Images/pré.png", pre10);

        JButton champs11 = new JButton("Champs 11");
        setButton(champs11);
        setImage("src/GUI/Images/champs.png", champs11);

        JButton pre8 = new JButton("Pré 8");
        setButton(pre8);
        setImage("src/GUI/Images/pré.png", pre8);

        JButton champs4 = new JButton("Champs 4");
        setButton(champs4);
        setImage("src/GUI/Images/champs.png", champs4);

        JButton colline9 = new JButton("Colline 9");
        setButton(colline9);
        setImage("src/GUI/Images/colline.png", colline9);

        JButton foret5 = new JButton("Forêt 5");
        setButton(foret5);
        setImage("src/GUI/Images/forêt.png", foret5);

        JButton montagne12 = new JButton("Montagne 12");
        setButton(montagne12);
        setImage("src/GUI/Images/montagne.png", montagne12);

        JButton montagne3 = new JButton("Montagne 3");
        setButton(montagne3);
        setImage("src/GUI/Images/montagne.png", montagne3);

        JButton desert = new JButton("Désert");
        setButton(desert);
        setImage("src/GUI/Images/desert.png", desert);

        JButton champs10 = new JButton("Champs 10");
        setButton(champs10);
        setImage("src/GUI/Images/champs.png", champs10);

        JButton colline6 = new JButton("Colline 6");
        setButton(colline6);
        setImage("src/GUI/Images/colline.png", colline6);

        JButton colline99 = new JButton("Colline 9");
        setButton(colline99);
        setImage("src/GUI/Images/colline.png", colline99);

        JButton montagne8 = new JButton("Montagne 8");
        setButton(montagne8);
        setImage("src/GUI/Images/montagne.png", montagne8);

        JButton pre5 = new JButton("Pré 5");
        setButton(pre5);
        setImage("src/GUI/Images/pré.png", pre5);

        JButton foret2 = new JButton("Forêt 2");
        setButton(foret2);
        setImage("src/GUI/Images/forêt.png", foret2);

        panelPlateau.add(foret6);
        panelPlateau.add(pre10);
        panelPlateau.add(champs11);
        panelPlateau.add(pre8);
        panelPlateau.add(champs4);
        panelPlateau.add(colline9);
        panelPlateau.add(foret5);
        panelPlateau.add(montagne12);
        panelPlateau.add(montagne3);
        panelPlateau.add(desert);
        panelPlateau.add(champs10);
        panelPlateau.add(colline6);
        panelPlateau.add(colline99);
        panelPlateau.add(montagne8);
        panelPlateau.add(pre5);
        panelPlateau.add(foret2);

        // Création du panel pour afficher les informations des joueurs
        JPanel panelJoueur = new JPanel();
        panelJoueur.setOpaque(false);
        panelJoueur.setPreferredSize(new Dimension(0, 150));

        /**
         * Création des bouttons selon le nombre de joueurs choisi
         * Afficher le nom du joueur saisi par l'utilisateur
         */
        for (int i = 0; i < nbJoueurs; i++) {
            JButton buttonJoueur = new JButton();
            buttonJoueur.setPreferredSize(new Dimension(140, 140));
            buttonJoueur.setLayout(null);
            String numero = Integer.toString(i + 1);
            JLabel numeroJoueur = new JLabel("Joueur " + numero);
            numeroJoueur.setBounds(40, 5, 140, 30);
            JLabel labelNom = new JLabel("Nom : " + nomsJoueurs.get(i));
            labelNom.setBounds(10, 40, 140, 30);
            buttonJoueur.add(numeroJoueur);
            buttonJoueur.add(labelNom);
            panelJoueur.add(buttonJoueur);
        }

        // Création du boutton "Menu"
        JPanel panelParametre = new JPanel();
        panelParametre.setOpaque(false);
        panelParametre.setLayout(new BorderLayout());
        panelParametre.setPreferredSize(new Dimension(100, 0));
        JButton parametre = new JButton("Menu");
        panelParametre.add(parametre, BorderLayout.SOUTH);

        // Création du boutton "Action"
        JPanel panelJeu = new JPanel();
        panelJeu.setLayout(new BorderLayout());
        panelJeu.setOpaque(false);
        panelJeu.setPreferredSize(new Dimension(100, 0));
        JButton jeuAction = new JButton("Action");
        panelJeu.add(jeuAction, BorderLayout.SOUTH);

        setSize(largeur, hauteur);
        setTitle("Plateau");
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(146, 182, 213));
        setLayout(new BorderLayout());
        add(panelPlateau, BorderLayout.CENTER);
        add(panelJoueur, BorderLayout.NORTH);
        add(panelParametre, BorderLayout.WEST);
        add(panelJeu, BorderLayout.EAST);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        /**
         * Demander à l'utilisateur de confirmer son choix
         * avant de fermer la fenêtre
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de quitter ?", "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public void setButton(JButton button) {
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setContentAreaFilled(false);
        button.setFont(new Font("", Font.PLAIN, 20));
    }

    public void setImage(String path, JButton button) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image adaptedImage = image.getScaledInstance(169, 147, Image.SCALE_DEFAULT);
        icon = new ImageIcon(adaptedImage);
        button.setIcon(icon);
    }

    /**
     * Attribuer les types de terrain et les nombres aux cases
     * 
     * @param i           le nombre qui sera affiché sur la case
     * @param typeTerrain le type de terrain attribué
     * @param path        path d'image de fond
     */
    public void setCases(int i, String typeTerrain, String path) {
        JButton boutton = new JButton(typeTerrain + String.valueOf(i));
        setButton(boutton);
        setImage(path, boutton);
    }

    @Override
    public void update(Observable observable, Object arg) {
        repaint();
    }

}
