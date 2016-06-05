/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.Controller.AutoFlockingController;
import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;
import LogoRefacto.model.TortueFlocking;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * De la même manière qu'une PopulationTortue gère un ensemble de tortues, la
 * PopulationView gère un ensemble de TortueView, les représentations des
 * tortues dans cette vue
 *
 * @author Vlad & Hassane
 */
public class PopulationView extends JPanel implements Observer {

    private ArrayList<ITortueView> tortuesView; // la liste des tortues enregistrees
    private Color currentColor = Color.BLACK;

    public PopulationView() {
        this.tortuesView = new ArrayList<>();
    }

    public PopulationView(ArrayList<Tortue> tortuesList) {
        tortuesView = new ArrayList<>();
    }

    public void addTortue(Tortue t) {
        if (t instanceof TortueFlocking) {
            addFlockingTortue((TortueFlocking) t);
            return;
        }
        ITortueView tv = new TortueView(t);
        tortuesView.add(tv);
        repaint();

    }

    public void addFlockingTortue(TortueFlocking t) {
        ITortueView tv = new FlockngTortueView(t);
        tortuesView.add(tv);
        repaint();

    }

    public void reset() {
        for (ITortueView t : tortuesView) {
            t.reset();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = currentColor != null ? currentColor : g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for (int i =0; i<tortuesView.size(); i++) {
            tortuesView.get(i).drawTurtle(g);
        }
    }

    @Override
    public synchronized void update(Observable o, Object argTortue) {
        AbstractController controller = (AbstractController) o;
        updatePopulation(((AutoFlockingController)controller).getWorld().getAllReflectPopulation());
        //updatePopulation(controller.getPopulation());
        
        
        
    }

    public void updatePopulation(PopulationTortue p) {
        List<Tortue> currentTortues = getCurrentTortues();
        //Mise à jour des tortues (et ajout si necessaire):
        for (Tortue updatedTortue : p) {
            if (currentTortues.contains(updatedTortue)) {
                int index = currentTortues.indexOf(updatedTortue);
                tortuesView.get(index).updateTortue(updatedTortue);
            } else {
                addTortue(updatedTortue);
            }
        }
        //Suppression des tortues non présentes :
        ArrayList<ITortueView> toDelete = new ArrayList<>();
        tortuesView.remove(null);// ajouté car des fois tortueview contient null 
        for (ITortueView t : tortuesView) {
            if (!p.contains(t.getTortue())) {
                toDelete.add(t);
            }
        }
        tortuesView.removeAll(toDelete);

        repaint();

    }

    public void updateTortue(Tortue t) {
        for (ITortueView v : tortuesView) {
            if (v.getTortue().equals(t)) {
                v.updateTortue(t);
                repaint();
                return;
            }
        }
        //Nouvelle tortue non enregistré :
        addTortue(t);

    }

    private List<Tortue> getCurrentTortues() {
        List<Tortue> list = new ArrayList<>();
        for (ITortueView t : tortuesView) {
            list.add(t.getTortue());
        }
        return list;
    }

    public void setTortueColor(Tortue t, Color color) {
        for (ITortueView tortueV : tortuesView) {
            if (t.equals(tortueV.getTortue())) {
                tortueV.setColor(color);
                tortueV.drawTurtle(getGraphics());
            }
        }
    }
}
