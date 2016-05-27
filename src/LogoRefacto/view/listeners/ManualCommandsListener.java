/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.MainView;
import LogoRefacto.view.ManualCommandsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JComboBox;

/**
 *
 * @author Vlad
 */
public class ManualCommandsListener implements ActionListener {

    AbstractController mainController;
    MainView mainView;

    public ManualCommandsListener(AbstractController mainController, MainView mainView) {
        this.mainController = mainController;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ManualCommandsView.CMD_COLORLIST)) {
            mainView.setColor(((JComboBox) e.getSource()).getSelectedIndex());
        } else {
            String c = e.getActionCommand();
            if (c.equals(MainView.CMD_CREER_TORTUE)) {
                Random r = new Random();
                mainController.addTortue(new Tortue(r.nextInt(300), r.nextInt(200), -90));
            } else if (c.equals(MainView.CMD_SUPPRIMER_TORTUE)) {
                if (mainController.getPopulation().size() > 1) {
                    removeTortue(1);
                }
            } else if (c.equals(MainView.CMD_AVANCER)) {
                try {
                    int v = Integer.parseInt(mainView.getInputValue());
                    mainController.avancerTortue(mainController.getTortueCourante(), v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
                }

            } else if (c.equals(MainView.CMD_DROITE)) {
                try {
                    int v = Integer.parseInt(mainView.getInputValue());
                    mainController.droiteTortue(mainController.getTortueCourante(), v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
                }
            } else if (c.equals(MainView.CMD_GAUCHE)) {
                try {
                    int v = Integer.parseInt(mainView.getInputValue());
                    mainController.gaucheTortue(mainController.getTortueCourante(), v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
                }
            } // actions des boutons du bas
            else if (c.equals(MainView.CMD_EFFACER)) {
                mainController.initializePopulation();
            }
        }
    }

    private void removeTortue(int n) {
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
        if(target!= null)mainController.removeTortue(target);
    }
}
