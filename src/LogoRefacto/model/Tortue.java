package LogoRefacto.model;
/** ***********************************************************************
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
 ************************************************************************* */
public class Tortue {

    public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    protected int x, y;
    protected int dir;
    protected boolean crayon;
    protected int coul;

    public void setColor(int n) {
        coul = n;
    }

    public int getColor() {
        return coul;
    }

    public Tortue() {
        reset();
    }

    public void reset() {
        x = 0;
        y = 0;
        dir = -90;
        coul = 0;
        crayon = true;
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(x + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(y + dist * Math.sin(ratioDegRad * dir));

        x = newX;
        y = newY;
    }

    public void droite(int ang) {
        dir = (dir + ang) % 360;
    }

    public void gauche(int ang) {
        dir = (dir - ang) % 360;
    }

    public void baisserCrayon() {
        crayon = true;
    }

    public void leverCrayon() {
        crayon = false;
    }

    public void couleur(int n) {
        coul = n % 12;
    }

    public void couleurSuivante() {
        couleur(coul + 1);
    }

    /** quelques classiques */
    public void carre() {
        for (int i = 0; i < 4; i++) {
            avancer(100);
            droite(90);
        }
    }

    public void poly(int n, int a) {
        for (int j = 0; j < a; j++) {
            avancer(n);
            droite(360 / a);
        }
    }

    public void spiral(int n, int k, int a) {
        for (int i = 0; i < k; i++) {
            couleur(coul + 1);
            avancer(n);
            droite(360 / a);
            n = n + 1;
        }
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

    public boolean isCrayon() {
        return crayon;
    }

    public int getCoul() {
        return coul;
    }

}
