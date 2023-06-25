package catane;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Case implements Serializable {
    // Le champ de la classe Case
    private String nomCase;
    private int numberCase;
    private Point2D[] listPoint;

    // Le constructeur de la classe Case
    public Case(String nomCase, int numberCase, Point2D[] listPoint) {
        this.nomCase = nomCase;
        this.numberCase = numberCase;
        /*
         * 4 points 2D pour chaque case
         * Par example, le premier case a 4 points 2D ( [0,0], [0,1], [1,0], [1,1] )
         */
        this.listPoint = listPoint;
    }

    // Getter du nom de case
    public String getNomCase() {
        return nomCase;
    }

    // Getter du numéro de case
    public int getNumberCase() {
        return numberCase;
    }

    // Getter de la liste de points
    public Point2D[] getListPoint() {
        return listPoint;
    }

    // Setter du nom de case
    public void setNomCase(String nom) {
        this.nomCase = nom;
    }

    // Setter du numéro de case
    public void setNumberCase(int i) {
        this.numberCase = i;
    }

    // Setter de la liste des points
    public void setListPoint(Point2D[] newList) {
        this.listPoint = newList;
    }

}
