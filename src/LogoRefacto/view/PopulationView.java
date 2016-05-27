/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Vlad
 */
public class PopulationView extends JPanel implements Observer{

    private ArrayList<TortueView> tortuesView; // la liste des tortues enregistrees
    
    public PopulationView() {
        this.tortuesView = new ArrayList<>();
    }

    public PopulationView(ArrayList<Tortue> tortuesList) {
        tortuesView = new ArrayList<>();
    }

    public void addTortue(Tortue t) {
        TortueView tv = new TortueView(t);
        tortuesView.add(tv);
       repaint();
        
    }


    public void reset() {
        for (TortueView t : tortuesView) {
            t.reset();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for (TortueView t : tortuesView) {
            t.drawTurtle(g);
        }
    }

 
    @Override
    public void update(Observable o, Object argTortue) {
        AbstractController controller = (AbstractController) o;
        updatePopulation(controller.getPopulation());            
        
    }
    
    public void updatePopulation(PopulationTortue p)
    {
        List<Tortue> currentTortues = getCurrentTortues();
        //Mise à jour des tortues (et ajout si necessaire):
        for(Tortue updatedTortue : p)
        {
            if(currentTortues.contains(updatedTortue))
            {
                int index = currentTortues.indexOf(updatedTortue);
                tortuesView.get(index).updatePosition(updatedTortue.getX(), updatedTortue.getY(), updatedTortue.getDir());
            }else {
                addTortue(updatedTortue);
            }
        }
        //Suppression des tortues non présentes :
        for(TortueView t : tortuesView)
        {
            if(!p.contains(t.getTortue()))
                tortuesView.remove(t);
        }
        repaint();
        
    }

    public void updateTortue(Tortue t)
    {
        for(TortueView v : tortuesView)
        {
            if(v.getTortue().equals(t))
            {
                v.updatePosition(t.getX(), t.getY(), t.getDir());
                repaint();
                return;
            }
        }
        //Nouvelle tortue non enregistré :
        addTortue(t);
        
    }

    private List<Tortue> getCurrentTortues() {
        List<Tortue> list = new ArrayList<>();
        for(TortueView t : tortuesView)
        {
            list.add(t.getTortue());
        }
        return list;
    }
    
    
}
