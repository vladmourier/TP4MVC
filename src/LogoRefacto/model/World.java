/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.model;

import LogoRefacto.model.Shapes.MovePattern;

/**
 *
 * @author Hassane
 */
public class World {
 
    PopulationTortue habitants;
    int worldWidth;
    int worldHeight;
    
    public World(PopulationTortue p, int width, int height)
    {
        this.habitants = p;
        this.worldHeight = height;
        this.worldWidth = width;
    }
    
    public World(int width, int height) {
        this(new PopulationTortue(), width, height);
    }
    
    public void avancerTortue(Tortue t, int dist){
        habitants.getTortue(t).avancer(dist);
    }
    
    public void gaucheTortue(Tortue t, int ang){
        habitants.getTortue(t).gauche(ang);
    }
    
    public void droiteTortue(Tortue t, int ang){
        habitants.getTortue(t).droite(ang);
        
    }
    
    public void drawPatternTortue(Tortue t, MovePattern mp){
        habitants.getTortue(t).drawPattern(mp);
    }
    
    public PopulationTortue getPopulation() {
        return habitants;
    }

    public int getWidth() {
        return worldWidth;
    }

    public int getHeight() {
        return worldHeight;
    }
}
