package LogoRefacto.model;

import LogoRefacto.model.Shapes.Carre;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Shapes.Polygone;
import LogoRefacto.model.Shapes.Spiral;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

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
public class Tortue {

    public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    public class Chemin {

        Position origine;
        Position destination;

        public Chemin(Position origine, Position destination) {
            this.origine = origine;
            this.destination = destination;
        }

        public Position getOrigine() {
            return origine;
        }

        public void setOrigine(Position origine) {
            this.origine = origine;
        }

        public Position getDestination() {
            return destination;
        }

        public void setDestination(Position destination) {
            this.destination = destination;
        }

    }
    protected Position position;
    protected ArrayList<Chemin> trace;
    protected int dir;
    protected int label;
    private static int index = 0;

    public Tortue(int x, int y, int dir) {
        trace = new ArrayList<>();
        position = new Position(x, y);
        this.dir = dir;
        this.label = index;
        index++;
    }

    public Tortue(Tortue t) {
        trace = new ArrayList<>();
        int x = t.getX();
        int y = t.getY();
        int dir = t.getDir();
        position = new Position(x, y);

        this.dir = dir;
        this.label = t.label;
    }

    public Tortue() {
        trace = new ArrayList<>();
        position = new Position(0, 0);
        dir = -90;
        this.label = index;
        index++;
    }

    public void reset() {
        position = new Position(0, 0);
        dir = -90;
    }

    public void setPosition(int newX, int newY) {
        position = new Position(newX, newY);
    }

    public void addtoTrace(int x, int y) {
        trace.add(new Chemin(new Position(getX(), getY()), new Position(x, y)));
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(position.x + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(position.y + dist * Math.sin(ratioDegRad * dir));
        addtoTrace(newX, newY);
        position = new Position(newX, newY);
    }

    public void droite(int ang) {
        dir = (dir + ang) % 360;
    }

    public void gauche(int ang) {
        dir = (dir - ang) % 360;
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
        mp.moveTurtle(this);
    }

    public void drawPattern(MovePattern mp) {
        mp.moveTurtle(this);
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tortue other = (Tortue) obj;
        if (this.label == other.label) {
            return true;
        }
        return false;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public ArrayList<Chemin> getTrace() {
        return trace;
    }
}
