/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.MainController;
import LogoRefacto.view.MainView;
import LogoRefacto.view.ManualCommandsView;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

/**
 * Classe écoutant la barre d'outils
 *
 * @author Vlad & Hassane
 */
public class ManualCommandsListener extends CommandListener implements KeyEventDispatcher {

    public ManualCommandsListener(MainController mainController, MainView mainView) {
        super(mainController, mainView);
        //Ajout des touches
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals(ManualCommandsView.CMD_COLORLIST)) {
            mainView.setCurrentTortueColor(((JComboBox) e.getSource()).getSelectedIndex());
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() != KeyEvent.KEY_PRESSED) {
            return false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            droiteTortueCourante();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gaucheTortueCourante();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            avancerTortueCourante();
        } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
            //next tortue;
            mainController.nextTortue();
        }

        return true;
    }
}
