/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

/**
 *
 * @author Vlad & Hassane
 */
public class TortueFlocking extends Tortue {

    private final int distance_safe_space = 50;//Distance à respecter avec les autres tortues
    private final int distance_vision = 200; // distance en pixel jusqu'oÃ¹ la tortue peut voir
    private final int angle_vision = 170; // en degrÃ©s
    private int vitesse = 0;// en nombre de pixels

    public TortueFlocking(int x, int y, int dir) {
        super(x, y, dir);
    }

    public TortueFlocking(TortueFlocking t) {
        super(t);
        this.vitesse = t.vitesse;

    }

    public TortueFlocking() {
        super();
    }

    private void updateVitesse(int v) {
        this.vitesse = v;
    }

    public int getDistance_vision() {
        return distance_vision;
    }

    public int getAngle_vision() {
        return angle_vision;
    }

    public int getVitesse() {
        return vitesse;
    }

    @Override
    public void avancer(int dist) {
        this.vitesse = dist;
        super.avancer(dist);

    }

    @Override
    public String toString() {
        return super.toString() + "TortueFlocking{" + "distance_safe_space=" + distance_safe_space + ", distance_vision=" + distance_vision + ", angle_vision=" + angle_vision + ", vitesse=" + vitesse + '}';
    }
    
}
