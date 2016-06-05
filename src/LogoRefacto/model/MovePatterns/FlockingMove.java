/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model.MovePatterns;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Position;
import LogoRefacto.model.Tortue;
import LogoRefacto.model.TortueFlocking;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hassane
 */
public class FlockingMove extends RandomPattern {

    private final int distance_safe_space = 50;//Distance à respecter avec les autres tortues
    private final int distance_vision = 200; // distance en pixel jusqu'oÃ¹ la tortue peut voir
    private final int angle_vision = 50; // en degrÃ©s
    private int vitesse = 0;// en nombre de pixels
    private PopulationTortue population;
    private int distance; //distance à parcourir
    private int default_vitesse = 50;

    public FlockingMove(PopulationTortue population) {
        super();
        this.population = new PopulationTortue(population);

    }

    @Override
    public int moveTurtle(Tortue t) {
        int dist = 0;
        List<Tortue> visibles = getTortueVisible(t);

        if (visibles.size() > 0) {
            t.setDir(getDirMoyenneVisibles(visibles));
            dist = updateDistance(t, getVitesseMoyenneVisibles(visibles));
        } else {
            dist = super.moveTurtle(t, defaultDistance);
        }

        return dist;
    }

    private List<Tortue> getTortueVisible(Tortue t) {
        ArrayList<Tortue> visiblesTortues = new ArrayList<>();
        for (Tortue voisin : population) {
            if (canSee(t, voisin)) {
                visiblesTortues.add(voisin);
            }
        }
        return visiblesTortues;
    }

    private int updateDistance(Tortue t, int vitesse) {
        t.avancer(vitesse);
        return vitesse;
    }

    public int getDirMoyenneVisibles(List<Tortue> visibles) {
        int sommeDir = 0;
        for (Tortue tf : visibles) {
            sommeDir += tf.getDir();
        }
        return visibles.size() > 0 ? sommeDir / visibles.size() : 0;
    }

    public int getVitesseMoyenneVisibles(List<Tortue> visibles) {
        int sommeVitesse = 0;
        for (Tortue tf : visibles) {
            int vitesse = default_vitesse;
            if (tf instanceof TortueFlocking) {
                vitesse = ((TortueFlocking) tf).getVitesse();
            }
            sommeVitesse += vitesse;
        }
        return visibles.size() > 0 ? sommeVitesse / visibles.size() : 0;
    }

    public boolean canSee(Tortue t, Tortue voisin) {
        if (voisin.equals(t)) {
            return false;
        }

        boolean closeEnough = Position.getDistance(t.getPosition(), voisin.getPosition()) <= distance_vision;

        //double tan = (((double) t.getY() - (double) getY()) / ((double) t.getX() - (double) getX()));
        //int angle = (int) (Math.atan(tan) / ratioDegRad);
        double angle = getAngleBetween(voisin, t);
        boolean correctAngle = Math.abs(angle - t.getDir()) % 180 < angle_vision / 2;
        if (!(closeEnough && correctAngle)) {
            System.out.println("La tortue " + t.getLabel() + " ne peut voir" + " la tortue " + voisin.getLabel());
        } else {
            System.out.println("La tortue " + t.getLabel() + " peut voir" + " la tortue " + voisin.getLabel());
        }
        return closeEnough && correctAngle;
    }

    private double getAngleBetween(Tortue vois, Tortue courant) {
        //Coordonnées de la tortue relaves à this
        int x = courant.getX() - vois.getX();
        int y = courant.getY() - vois.getY();
        if (x == 0 && y == 0) {
            return 0;
        }
        if (x == 0) {
            if (y > 0) {
                return 270;
            } else {
                return 90;
            }
        } else {
            double quotient = ((double) y) / ((double) x);
            double angle = Math.atan(quotient);
            if (x < 0) {
                angle += Math.PI;
            }
            if (angle < 0) {
                angle += 2 * Math.PI;
            }
            return 180 * angle / Math.PI;
        }
    }
}
