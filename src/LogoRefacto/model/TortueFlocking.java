/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

/**
 *
 * @author Vlad
 */
public class TortueFlocking extends Tortue {
    private final int distance_vision = 100; // distance en pixel jusqu'oÃ¹ la tortue peut voir
    private final int angle_vision = 180; // en degrÃ©s
    private final double vitesse=  0;

    public TortueFlocking(int x, int y, int dir) {
        super(x, y, dir);
    }

    public TortueFlocking(Tortue t) {
        super(t);
    }

    public TortueFlocking() {
        super();
    }
    
    public boolean canSee (Tortue t){
        boolean closeEnough = Position.getDistance(getPosition(), t.getPosition()) <= distance_vision;
        
        double tan = (Math.abs(t.getY() - getY()))/(Math.abs(t.getX() - getX()));
        
        int angle = (int) (Math.atan(tan)/ratioDegRad);
        
        boolean correctAngle = Math.abs(angle - dir)< angle_vision/2;
        
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
    
    
}
