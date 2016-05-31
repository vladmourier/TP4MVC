/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

import java.util.ArrayList;

/**
 *
 * @author Vlad
 */
public class TortueFlocking extends Tortue {

    private final int distance_vision = 200; // distance en pixel jusqu'oÃ¹ la tortue peut voir
    private final int angle_vision = 100; // en degrÃ©s
    private final double vitesse = 0;

    private ArrayList<TortueFlocking> voisins;

    public TortueFlocking(int x, int y, int dir) {
        super(x, y, dir);
        voisins = new ArrayList<>();
    }

    public TortueFlocking(Tortue t) {
        super(t);
        voisins = new ArrayList<>();
    }

    public TortueFlocking() {
        super();
        voisins = new ArrayList<>();
    }

    public boolean canSee(Tortue t) {
        if (this.equals(t)) {
            return true;
        }
        boolean closeEnough = Position.getDistance(getPosition(), t.getPosition()) <= distance_vision;

        double tan =  (((double)t.getY() - (double)getY()) / ( (double)t.getX() - (double)getX()));

        int angle = (int) (Math.atan(tan) / ratioDegRad);

        boolean correctAngle = Math.abs(angle - dir) < Math.abs(angle_vision / 2 - dir);
        if (!(closeEnough && correctAngle)) {
            System.out.println("La tortue " + label + " ne peut voir" + " la tortue " + t.getLabel());
        } else {
            System.out.println("La tortue " + label + " peut voir" + " la tortue " + t.getLabel());
        }
        return closeEnough && correctAngle;
    }

    public int getDistance_vision() {
        return distance_vision;
    }

    public int getAngle_vision() {
        return angle_vision;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void addVoisin(TortueFlocking tf) {
        if (!voisins.contains(tf) && !this.equals(tf)) {
            voisins.add(tf);
        }
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(position.x + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(position.y + dist * Math.sin(ratioDegRad * dir));
        addtoTrace(newX, newY);
        position = new Position(newX, newY);
        for (TortueFlocking tf : voisins) {
            canSee(tf);
        }

    }

}
