/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Vlad
 */
public class MainListener implements ActionListener {

    public static final String CMD_AVANCER = "Avancer";
    public static final String CMD_DROITE = "Droite";
    public static final String CMD_GAUCHE = "Gauche";
    public static final String CMD_EFFACER = "Effacer";
    public static final String CMD_QUITTER = "Quitter";
    
    AbstractController mainController;
    MainView mainView;

    ActionEvent current;

    public MainListener(AbstractController mc, MainView mainV) {
        mainController = mc;
        this.mainView = mainV;
        mainController.setMode(selectMode());
//        switch (selectMode()) {
//            case AbstractController.MODE_AUTO:
//                mainController.setMode(AbstractController.MODE_AUTO);
//                break;
//            case AbstractController.MODE_MANUEL:
//                mainController.setMode(AbstractController.MODE_MANUEL);
//                break;
//        }
    }

    public void update(Tortue tortueCourante) {
        String c = current.getActionCommand();
        // actions des boutons du haut
        if (c.equals(CMD_AVANCER)) {
            try {
                int v = Integer.parseInt(mainView.getInputValue());
                mainController.avancerTortue(tortueCourante, v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
            }

        } else if (c.equals(CMD_DROITE)) {
            try {
                int v = Integer.parseInt(mainView.getInputValue());
                mainController.droiteTortue(tortueCourante, v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
            }
        } else if (c.equals(CMD_GAUCHE)) {
            try {
                int v = Integer.parseInt(mainView.getInputValue());
                mainController.gaucheTortue(tortueCourante, v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
            }
        } // actions des boutons du bas
        else if (c.equals(CMD_EFFACER)) {
            mainController.initializePopulation();
        } else if (c.equals(CMD_QUITTER)) {
            mainController.closeApplication();
        } else {

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

    public String selectMode() {
        return mainView.showChooseBox();
    }
}
