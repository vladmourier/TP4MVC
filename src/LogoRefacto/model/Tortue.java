package LogoRefacto.model;

import LogoRefacto.model.Shapes.Carre;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Shapes.Polygone;
import LogoRefacto.model.Shapes.Spiral;
import java.util.Observable;

/**
 * ***********************************************************************
 *
 * Un petit Logo minimal qui devra etre ameliore par la suite
 *
 * Source originale : J. Ferber - 1999-2001
 *
 * Cours de DESS TNI - Montpellier II
 *
 * @version 2.0
 * @date 25/09/2001
 *
 *************************************************************************
 */
public class Tortue extends Observable {

    public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    protected int x, y;
    protected int dir;

    public Tortue(Tortue t) {
        int x = t.getX();
        int y = t.getY();
        int dir = t.getDir();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Tortue() {
        x = 0;
        y = 0;
        dir = -90;
    }

    public void reset() {
        x = 0;
        y = 0;
        dir = -90;
        setChanged();
        notifyObservers();
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
        setChanged();
        notifyObservers();
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(x + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(y + dist * Math.sin(ratioDegRad * dir));

        x = newX;
        y = newY;
        setChanged();
        notifyObservers();
    }

    public void droite(int ang) {
        dir = (dir + ang) % 360;
        setChanged();
        notifyObservers();
    }

    public void gauche(int ang) {
        dir = (dir - ang) % 360;
        setChanged();
        notifyObservers();
    }

    /**
     * quelques classiques
     */
    public void drawPattern(String MovePattern) throws Exception {
        MovePattern mp;
        switch (MovePattern) {
            case Carre.CARRE:
                mp = new Carre();
                break;
            case Carre.POLYGONE:
                mp = new Polygone(60, 8);
                break;
            case Carre.SPIRALE:
                mp = new Spiral(50, 40, 6);
                break;
            default:
                throw new Exception("Pattern inconnu");
        }
        mp.move(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDir() {
        return dir;
    }

//    public void baisserCrayon() {
//        crayon = true;
//    }
//
//    public void leverCrayon() {
//        crayon = false;
//    }
//
//    public void couleur(int n) {
//        coul = n % 12;
//    }
//
//    public void couleurSuivante() {
//        couleur(coul + 1);
//    }
//
//    public boolean isCrayon() {
//        return crayon;
//    }
//
//    public int getCoul() {
//        return coul;
//    }
//    public void setColor(int n) {
//        coul = n;
//    }
//
//    public int getColor() {
//        return coul;
//    }
}
