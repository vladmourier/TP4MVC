/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.PopulationView;
import java.util.Iterator;
import java.util.Observable;

/**
 *
 * @author Vlad
 */
public class MainController extends AbstractController{

    private PopulationTortue populationTortue;
    private Iterator<Tortue> itTortue;
    private Tortue tortueCourante;
    
    
    public MainController() {
        populationTortue = new PopulationTortue();
        itTortue = populationTortue.iterator();
    }

    public Tortue getTortue(Tortue t)
    {
        return populationTortue.getTortue(t);
    }
   
    @Override
    public void addTortue(Tortue t)
    {
        populationTortue.addTortue(t);
        notifyView();
    }
    
    @Override
    public void avancerTortue(Tortue t, int dist) {
        getTortue(t).avancer(dist);
        notifyView();
    }
    
    @Override
    public void droiteTortue(Tortue t, int dist) {
        getTortue(t).droite(dist);
        notifyView();
    }
    
    
    @Override
    public void gaucheTortue(Tortue t, int dist) {
        getTortue(t).gauche(dist);
        notifyView();
    }

    
    @Override
    public void doPatternTortue(Tortue t, MovePattern mp) {
       getTortue(t).drawPattern(mp);
       notifyView();      
    }
    
    @Override
    public Tortue nextTortue() {
        if(itTortue.hasNext())
        {
            tortueCourante = itTortue.next();
        }else if(populationTortue.size()>0)
        {
            itTortue = populationTortue.iterator();
            tortueCourante= itTortue.next();
        }else
            tortueCourante= null;
        return tortueCourante;
    }
    
    @Override
    public Tortue getTortueCourante()
    {
       return tortueCourante;
                
    }
    
    private void notifyView() {
        setChanged();
        notifyObservers();
    }
    

    @Override
    public void setMode(String mode) {
        switch(mode){
            case MODE_AUTO:
                //TODO
                break;
            case MODE_MANUEL:
                //TODO
                break;
        }
    }

    
    @Override
    public void initializePopulation() {
        populationTortue.clear();
        tortueCourante = new Tortue();
        tortueCourante.setPosition(500 / 2, 400 / 2);
        populationTortue.addTortue(tortueCourante);
        notifyView();
    }


    @Override
    public void closeApplication() {
        System.exit(0);
    }

    @Override
    public PopulationTortue getPopulation() {
        return populationTortue;
    }

   

}
