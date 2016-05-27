/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.view.MainView;
import LogoRefacto.view.ManualCommandsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            mainView.setColor(((JComboBox)e.getSource()).getSelectedIndex());
        } else {
            String c = e.getActionCommand();
            if (c.equals(MainListener.CMD_AVANCER)) {
                try {
                    int v = Integer.parseInt(mainView.getInputValue());
                    mainController.avancerTortue(mainController.getTortueCourante(), v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
                }

            } else if (c.equals(MainListener.CMD_DROITE)) {
                try {
                    int v = Integer.parseInt(mainView.getInputValue());
                    mainController.droiteTortue(mainController.getTortueCourante(), v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
                }
            } else if (c.equals(MainListener.CMD_GAUCHE)) {
                try {
                    int v = Integer.parseInt(mainView.getInputValue());
                    mainController.gaucheTortue(mainController.getTortueCourante(), v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + mainView.getInputValue());
                }
            } // actions des boutons du bas
            else if (c.equals(MainListener.CMD_EFFACER)) {
                mainController.initializePopulation();
            }
        }
    }

}
