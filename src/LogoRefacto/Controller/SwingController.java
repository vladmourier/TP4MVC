/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.Controller;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad
 */
public class SwingController implements Controller, ActionListener {

    PopulationTortue fcourante;
    MainView mainV;

    ActionEvent current;

    public SwingController(PopulationTortue fcourante, MainView mainV) {
        this.fcourante = fcourante;
        this.mainV = mainV;
    }

    @Override
    public void control() {
        String c = current.getActionCommand();

        // actions des boutons du haut
        if (c.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int v = Integer.parseInt(mainV.getInputValue());
                fcourante.getCourante().avancer(v);
                mainV.getFeuille().getCourante().avancer(fcourante.getCourante().getX(), fcourante.getCourante().getY());
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainV.getInputValue());
            }

        } else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(mainV.getInputValue());
                fcourante.getCourante().droite(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainV.getInputValue());
            }
        } else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(mainV.getInputValue());
                fcourante.getCourante().gauche(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainV.getInputValue());
            }
        } // actions des boutons du bas
        else if (c.equals("Effacer")) {
            clearView();
        } else if (c.equals("Quitter")) {
            closeWindow();
        } else {
            Field[] forName = MovePattern.class.getDeclaredFields();
            ArrayList<String> s = new ArrayList<>();
            for (Field f : forName) {
                s.add(f.getName());
            }
            if (s.contains(c)) {
                try {
                    fcourante.getCourante().drawPattern(c);
                } catch (Exception ex) {
                    Logger.getLogger(SwingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    /**
     * la gestion des actions des boutons
     */
    public void actionPerformed(ActionEvent e) {
        current = e;
        control();
        mainV.repaint();
    }

    public void createTortue() {
        // Creation de la tortue
        Tortue tortue = new Tortue();

        // Deplacement de la tortue au centre de la feuille
        fcourante.addTortue(tortue);
        tortue.setPosition(500 / 2, 400 / 2);

        mainV.getFeuille().addTortueView(tortue);

    }

    @Override
    public void clearView() {
        mainV.reset();
        mainV.repaint();

        // Replace la tortue au centre
        Dimension size = mainV.getSize();
        fcourante.getCourante().setPosition(size.width / 2, size.height / 2);
    }

    @Override
    public void closeWindow() {
        System.exit(0);
    }

    @Override
    public void displayPopulation(PopulationTortue t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePopulation(PopulationTortue t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTortue(Tortue t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTortue(Tortue t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
