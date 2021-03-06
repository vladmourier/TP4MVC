/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Tortue;
import java.util.Observable;

/**
 *
 * @author Vlad
 */
public abstract class AbstractController extends Observable{
    
    public static final String MODE_MANUEL = "MANUEL";
    public static final String MODE_AUTO = "AUTO";
        
    public abstract void setMode(String mode);

    
    public abstract void initializePopulation();

    public abstract void closeApplication();

    public abstract void addTortue(Tortue t);
    public abstract void removeTortue(Tortue t);
    public abstract void avancerTortue(Tortue t, int v);
    public abstract void droiteTortue(Tortue t, int v);
    public abstract void gaucheTortue(Tortue t, int v);
    public abstract void doPatternTortue(Tortue t, MovePattern mp);
    public abstract Tortue getTortueCourante();
    public abstract Tortue nextTortue();

    public abstract PopulationTortue getPopulation();
}
