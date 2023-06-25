package GUI;

import Observer.Observable;
import Observer.Observer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.BorderLayout;

public class MenuVision extends JFrame implements ActionListener, MouseListener, Observer {
    private final JButton commencer;
    private final JButton quitter;
    public int nbJoueurs = 0;
    private ArrayList<String> nomsJoueurs = new ArrayList<>();

    MenuVision() {
        commencer = new JButton("Commencer le jeu");
        quitter = new JButton("Quitter le jeu");

        /**
         * En cliquant sur "Commencer le jeu", une nouvelle fenêtre s'affiche,
         * et on peut choisir le nombre de joueurs
         */
        commencer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frm2 = new JFrame();

                frm2.setTitle("Créer des joueurs");
                frm2.setLayout(null);
                JPanel jp = new JPanel();
                JLabel l2 = new JLabel("Choisir le nombre de joueurs : ");

                JComboBox<String> cmb = new JComboBox<String>();
                cmb.addItem("--Choisir--");
                cmb.addItem("3 personnes");
                cmb.addItem("4 personnes");

                /**
                 * En cliquant sur "Confirmer", on entre dans une nouvelle fenêtre,
                 * où on peut paramétrer et personnaliser le jeu,
                 * en choisissant le type de joueur (humain/IA)
                 * et saisissant le nom de joueur
                 */
                JButton confirmer = new JButton("Confirmer");
                confirmer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JFrame parametre = new JFrame();
                        parametre.setLayout(new BorderLayout());
                        parametre.setTitle("Paramètre du jeu");

                        parametre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        parametre.setSize(400, 400);
                        JPanel panel = new JPanel();
                        JPanel panel2 = new JPanel();
                        panel.setPreferredSize(new Dimension(0, 300));
                        panel2.setPreferredSize(new Dimension(0, 50));

                        nbJoueurs = cmb.getSelectedIndex() + 2;

                        if (nbJoueurs == 3) {
                            frm2.dispose();
                            setJComboBox(3, panel);
                            parametre.add(panel, BorderLayout.NORTH);
                            setButton(parametre, panel2);
                            parametre.add(panel2, BorderLayout.SOUTH);
                            parametre.setLocationRelativeTo(null);
                            parametre.setVisible(true);
                        } else if (nbJoueurs == 4) {
                            frm2.dispose();
                            setJComboBox(4, panel);
                            parametre.add(panel, BorderLayout.NORTH);
                            setButton(parametre, panel2);
                            parametre.add(panel2, BorderLayout.SOUTH);
                            parametre.setLocationRelativeTo(null);
                            parametre.setVisible(true);
                        }
                    }
                });
                confirmer.setBounds(150, 150, 115, 33);

                jp.add(l2);
                jp.add(cmb);
                jp.setBounds(0, 50, 400, 70);
                frm2.add(jp, BorderLayout.NORTH);
                frm2.add(confirmer, BorderLayout.CENTER);
                frm2.setSize(400, 400);
                frm2.setLocationRelativeTo(null);
                frm2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frm2.setVisible(true);
            }
        });

        commencer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                commencer.setFont(new Font("", Font.BOLD, 20));
            }

            public void mouseReleased(MouseEvent e) {
                commencer.setFont(new Font("", Font.PLAIN, 20));
            }

            public void mouseEntered(MouseEvent e) {
                commencer.setBorder(BorderFactory.createLineBorder(Color.orange));
            }

            public void mouseExited(MouseEvent e) {
                commencer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

        });

        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        quitter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                quitter.setFont(new Font("", Font.BOLD, 20));
            }

            public void mouseReleased(MouseEvent e) {
                quitter.setFont(new Font("", Font.PLAIN, 20));
            }

            public void mouseEntered(MouseEvent e) {
                quitter.setBorder(BorderFactory.createLineBorder(Color.orange));
            }

            public void mouseExited(MouseEvent e) {
                quitter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        });

        this.add(commencer);
        this.add(quitter);

        commencer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quitter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        commencer.setFocusPainted(false);
        quitter.setFocusPainted(false);
        commencer.setFont(new Font("", Font.PLAIN, 20));
        quitter.setFont(new Font("", Font.PLAIN, 20));
        commencer.setForeground(Color.BLACK);
        quitter.setForeground(Color.BLACK);
        commencer.setBackground(Color.BLACK);
        quitter.setBackground(Color.BLACK);
        commencer.setBounds(new Rectangle(790, 510, 200, 30));
        quitter.setBounds(new Rectangle(790, 580, 200, 30));

        setLayout(null);
        setTitle("Les Colons de Catane");
        setSize(1000, 780);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("src/GUI/Images/Catan_1024x768.jpg");
        Container s = getContentPane();
        s.add(commencer);
        s.add(quitter);

        JLabel background = new JLabel(image);
        background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        ((JComponent) getContentPane()).setOpaque(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Créer des JTextField selon le nombre de joueurs choisi
     * 
     * @param j  nombre de joueurs
     * @param jp panel auquel le JComboBos<String> s'ajoute
     */
    public void setJComboBox(int j, JPanel jp) {

        for (int i = 0; i < j; i++) {
            JComboBox<String> cmb = new JComboBox<String>();
            JLabel l3 = new JLabel("Jouer avec AI ou humain : ");

            cmb.addItem("--Choisir--");
            cmb.addItem("AI");
            cmb.addItem("Humain");

            JLabel l4 = new JLabel("Saisir le nom : ");
            jp.add(l3);
            jp.add(cmb);
            jp.add(l4);

            JTextField txtfield = new JTextField();
            txtfield.setEditable(true);
            txtfield.setColumns(10);
            jp.add(txtfield);
            jp.setVisible(true);

            JButton ok = new JButton("OK");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nom = txtfield.getText();
                    nomsJoueurs.add(nom);
                }
            });
            jp.add(ok);
        }

    }

    /**
     * Création de deux boutons
     * Nouveau jeu : lancer la partie, entrer dans le plateau
     * Retour : retour vers la fenêtre d'accueil
     */
    public void setButton(JFrame frame, JPanel panel) {
        JButton nouveauJeu = new JButton("Nouveau jeu");

        nouveauJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame plateau = new PlateauVision(nbJoueurs, nomsJoueurs);
                plateau.setVisible(true);

            }
        });
        nouveauJeu.setLayout(null);
        nouveauJeu.setBounds(250, 300, 115, 33);

        JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });
        retour.setLayout(null);
        retour.setBounds(50, 300, 115, 33);

        panel.add(nouveauJeu);
        panel.add(retour);

    }

    public ArrayList<String> getNomsJoueurs() {
        return this.nomsJoueurs;
    }

    @Override
    public void update(Observable observable, Object arg) {
        repaint();
    }
}
