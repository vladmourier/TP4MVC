/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

import java.util.ArrayList;

/**
 * Une tortue adoptant un comportement de 'flocking'
 * @author Vlad & Hassane
 */
public class TortueFlocking extends Tortue {

    private final int distance_safe_space = 50;//Distance à respecter avec les autres tortues
    private final int distance_vision = 200; // distance en pixel jusqu'oÃ¹ la tortue peut voir
    private final int angle_vision = 100; // en degrÃ©s
    private int vitesse = 0;// en nombre de pixels

    private ArrayList<TortueFlocking> voisins;
    private ArrayList<TortueFlocking> visibles;

    public TortueFlocking(int x, int y, int dir) {
        super(x, y, dir);
        voisins = new ArrayList<>();
        visibles = new ArrayList<>();
    }

    public TortueFlocking(Tortue t) {
        super(t);
        voisins = new ArrayList<>();
        visibles = new ArrayList<>();
    }

    public TortueFlocking() {
        super();
        voisins = new ArrayList<>();
        visibles = new ArrayList<>();
    }

    private void updateVisibles() {
        if (visibles.size() > 0) {
            visibles.clear();
        }
        for (TortueFlocking tf : voisins) {
            if (this.canSee(tf)) {
                visibles.add(tf);
            }
        }
    }
    
    private void updateVitesse(int v){
        this.vitesse = v;
    }

    public void updateForFlocking(){
        updateVisibles();
        updateVitesse(getVitesseMoyenneVisibles());
        setDir(getDirMoyenneVisibles());
    }
    
    public boolean canSee(Tortue t) {
        if (this.equals(t)) {
            return true;
        }
        boolean closeEnough = Position.getDistance(getPosition(), t.getPosition()) <= distance_vision;

        double tan = (((double) t.getY() - (double) getY()) / ((double) t.getX() - (double) getX()));

        int angle = (int) (Math.atan(tan) / ratioDegRad);

        boolean correctAngle = Math.abs(angle) % 180 < Math.abs(angle_vision / 2) % 180;
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
//        updateForFlocking();
        int newX = (int) Math.round(position.x + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(position.y + dist * Math.sin(ratioDegRad * dir));
        addtoTrace(newX, newY);
        position = new Position(newX, newY);

    }

    public int getDirMoyenneVisibles() {
        int sommeDir = 0;
        for (TortueFlocking tf : visibles) {
            sommeDir += tf.getDir();
        }
        return sommeDir / visibles.size();
    }

    public int getVitesseMoyenneVisibles() {
        int sommeVitesse = 0;
        for (TortueFlocking tf : visibles) {
            sommeVitesse += tf.getVitesse();
        }
        return sommeVitesse / visibles.size();
    }

    private boolean isCorrectAngle(Tortue t, int angle) {
        boolean b = false;
        int correctAngle;
        //on divise l'écran en 4 cadres autour de la position de la tortue
        if (t.getX() > getX() && t.getY() > getY())//inf droit
        {
            //Dans ce cas là l'angle calculé par la tangente est le bon (normalement)
        }
        if (t.getX() < getX() && t.getY() < getY())//sup gauche
        {

        }
        if (t.getX() < getX() && t.getY() > getY())//inf gauche
        {

        }
        if (t.getX() > getX() && t.getY() < getY())//sup droit
        {
            correctAngle = -angle;
        }

        return b;
    }
}
