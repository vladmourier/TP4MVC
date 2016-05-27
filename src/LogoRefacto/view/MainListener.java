/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Shapes.Carre;
import LogoRefacto.model.Shapes.MovePattern;
import LogoRefacto.model.Shapes.Polygone;
import LogoRefacto.model.Shapes.Spiral;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import LogoRefacto.view.PopulationView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad
 */
public class MainListener implements ActionListener {

    AbstractController mainController;
    MainView mainView;
    
    
    ActionEvent current;

    
    public MainListener(AbstractController mc, MainView mainV) {
        mainController = mc;
        this.mainView = mainV;
        switch(selectMode()){
            case AbstractController.MODE_AUTO:
                mainController.setMode(AbstractController.MODE_AUTO);
                break;
            case AbstractController.MODE_MANUEL:
                mainController.setMode(AbstractController.MODE_MANUEL);
                break;
        }
    }

    
    public void update(Tortue tortueCourante) {
        String c = current.getActionCommand();
        // actions des boutons du haut
        if (c.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int v = Integer.parseInt(mainView.getInputValue());
                mainController.avancerTortue(tortueCourante, v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
            }

        } else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(mainView.getInputValue());
                mainController.droiteTortue(tortueCourante, v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
            }
        } else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(mainView.getInputValue());
                mainController.gaucheTortue(tortueCourante, v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
            }
        } // actions des boutons du bas
        else if (c.equals("Effacer")) {
            mainController.initializePopulation();
        } else if (c.equals("Quitter")) {
            mainController.closeApplication();
        } else {
            Field[] forName = MovePattern.class.getDeclaredFields();
            ArrayList<String> s = new ArrayList<>();
            for (Field f : forName) {
                s.add(f.getName());
            }
            if (s.contains(c)) {
                try {
                    mainController.doPatternTortue(tortueCourante, getPatternMove(c));
                } catch (Exception ex) {
                    Logger.getLogger(MainListener.class.getName()).log(Level.SEVERE, null, ex);
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
        update(mainController.getTortueCourante());
    }

    public void createTortue() {
        // Creation de la tortue
        Tortue tortue = new Tortue();
        tortue.setPosition(500 / 2, 400 / 2);

        // Deplacement de la tortue au centre de la feuille
        mainController.addTortue(tortue);


    }
 
    
   private MovePattern getPatternMove(String pattern)
   {
       MovePattern mp = null;
        switch (pattern) {
            case Carre.CARRE:
                mp = new Carre();
                break;
            case Carre.POLYGONE:
                mp = new Polygone(60, 8);
                break;
            case Carre.SPIRALE:
                mp = new Spiral(50, 40, 6);
                break;
        }
        return mp;
   }
    public String selectMode() {
        return mainView.showChooseBox();
    }
}
