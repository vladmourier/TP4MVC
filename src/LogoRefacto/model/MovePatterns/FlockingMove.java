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
import LogoRefacto.model.World;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hassane
 */
public class FlockingMove extends RandomPattern{
    
    private int distance_safe_space = 30;//Distance à respecter avec les autres tortues
    private int default_distance_vision = 50; // distance en pixel jusqu'oÃ¹ la tortue peut voir
    private int default_angle_vision = 170; // en degrÃ©s
    private int default_vitesse=1;
    private TortueFlocking defaultTortue;
    private World world;
    
    public FlockingMove(World world)
    {
        super();
        this.world=world; 
        defaultTortue = null;
    }
    
    public FlockingMove(TortueFlocking t, World world)
    {
        this(world);
        this.defaultTortue = new TortueFlocking(t);
        default_distance_vision = t.getDistance_vision();
    }
    
    
    
    @Override
    public int moveTurtle(Tortue t) {
        int dist = 0;
        List<Tortue> visibles = getTortueVisible(t);

        if (visibles.size() > 0) {
            t.setDir(getDirMoyenneVisibles(visibles, t));
            int vit = getVitesseMoyenneVisibles(visibles, (TortueFlocking) t);
            dist = updateDistance(t, vit);
        } else {
            dist = super.moveTurtle(t, defaultDistance);
        }

        return dist;
    }
    
   
    
    public List<Tortue> getTortueVisible(Tortue t) {
        ArrayList<Tortue> visiblesTortues = new ArrayList<>();
        List<Tortue> p = world.getAllReflectPopulation().getList();
        for(int i=0; i<p.size(); i++)
        {
            Tortue voisin = p.get(i);
            if(canSee(t, voisin))
            {
                visiblesTortues.add(voisin);
            }
        }
        return visiblesTortues;
    }

    private int updateDistance(Tortue t, int vitesse) {
        t.avancer(vitesse);
        return vitesse;
    }

    public int getDirMoyenneVisibles(List<Tortue> visibles, Tortue t) {
        int sommeDir = 0;
        for (Tortue tf : visibles) {
            sommeDir += tf.getDir();
        }
        return visibles.size() > 0 ? sommeDir / visibles.size() : 0;
    }

    public int getVitesseMoyenneVisibles(List<Tortue> visibles, TortueFlocking t) {
        int sommeVitesse = 0;
        Tortue proche = null;
        int distance_min = Integer.MAX_VALUE;
        for (Tortue tf : visibles) {
            int distance = (int) Position.getDistance(tf.getPosition(), t.getPosition());
            if(proche == null || distance<distance_min )
            {
             proche = tf;
             distance_min = distance;
            }
//            double coeff = 1.0;
            int vitesse = default_vitesse;
            if (tf instanceof TortueFlocking) {
                vitesse = ((TortueFlocking) tf).getVitesse();
            }
            sommeVitesse += vitesse;
        }
        int s =visibles.size() > 0 ? (sommeVitesse / visibles.size()) : 0;
        if(s>(distance_min-distance_safe_space)){
            s = distance_min - distance_safe_space;
        } else if (distance_min>3*t.getDistance_vision()/4){
            s += s/4;
        }
        return s;
    }

    public boolean canSee(Tortue t, Tortue voisin) {
        if (voisin.equals(t)) {
            return false;
        }
        if(voisin.getPosition().equals(t.getPosition())){
            return false;
        }
        boolean closeEnough = Position.getDistance(t.getPosition(), voisin.getPosition()) <= default_distance_vision;

        double a = getAngleBetween(voisin,t);
        double c = Math.abs(a-t.getDir());
        if(c>180) c = 360 - c; 
        boolean correctAngle = c<default_angle_vision/2;
        if (!(closeEnough && correctAngle)) {
            System.out.println("La tortue " + t.getLabel() + " ne peut voir" + " la tortue " + voisin.getLabel());
        } else {
                System.out.println("La tortue " + t.getLabel() + " peut voir" + " la tortue " + voisin.getLabel());
        }
        return closeEnough && correctAngle;
    }

    private double getAngleBetween(Tortue vois, Tortue courant) {
        //Coordonnées de la tortue relaves à this
        int x = vois.getX() - courant.getX();
        int y = vois.getY() - courant.getY();
        if (x == 0 && y == 0) {
            return 0;
        }
        if (x == 0) {
            if (y < 0) {
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
