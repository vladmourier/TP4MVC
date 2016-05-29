/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.MainController;
import LogoRefacto.Controller.ManualController;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Vlad
 */
public abstract class CommandListener implements ActionListener {

    protected MainController mainController;
    protected MainView mainView;

    public CommandListener(MainController mainController, MainView mainView) {
        this.mainController = mainController;
        this.mainView = mainView;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals(MainView.CMD_CREER_TORTUE)) {
            Random r = new Random();
            mainController.addTortue(new Tortue(r.nextInt(600), r.nextInt(400), -90));
            if (mainController.getCurrentController() instanceof ManualController) {
                mainView.lockToolbar(false);
            }
        } else if (c.equals(MainView.CMD_SUPPRIMER_TORTUE)) {
            supprimerTortue();
        } else if (c.equals(MainView.CMD_AVANCER)) {
            avancerTortueCourante();
        } else if (c.equals(MainView.CMD_DROITE)) {
            droiteTortueCourante();
        } else if (c.equals(MainView.CMD_GAUCHE)) {
            gaucheTortueCourante();
        } else if (c.equals(MainView.CMD_EFFACER)) {
            mainController.initializePopulation();
        } else if (c.equals(MainView.CMD_NEXT_TORTUE)) {
            mainController.nextTortue();
        } else if (c.equals(MainView.CMD_QUITTER)) {
            mainController.closeApplication();
        }
    }

    protected void removeTortue(int n) {
        Iterator<Tortue> it = mainController.getPopulation().iterator();
        int i = 0;
        Tortue target = null;
        while (it.hasNext()) {
            Tortue t = it.next();
            if (i == n) {
                target = t;
            }
            i++;
        }
        if (target != null) {
            mainController.removeTortue(target);
        }
    }

    protected void avancerTortueCourante() {
        int v = Integer.parseInt(mainView.getInputValue());
        try {
            mainController.avancerTortue(mainController.getTortueCourante(), v);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
        } catch (NullPointerException nex) {//Si il n'y a pas de tortue courante (elle a été supprimée) on passe à la suivante
            mainController.nextTortue();
            mainController.avancerTortue(mainController.getTortueCourante(), v);
        }

    }

    protected void supprimerTortue() {
        int pop_size = mainController.getPopulation().size();
        if (pop_size > 0) {
            removeTortue(0);
        }
        if (pop_size == 1) {
            mainView.lockToolbar(true);
        }
    }

    protected void gaucheTortueCourante() {
        try {
            int v = Integer.parseInt(mainView.getInputValue());
            mainController.gaucheTortue(mainController.getTortueCourante(), v);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
        }
    }

    protected void droiteTortueCourante() {
        try {
            int v = Integer.parseInt(mainView.getInputValue());
            mainController.droiteTortue(mainController.getTortueCourante(), v);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
        }
    }
}
