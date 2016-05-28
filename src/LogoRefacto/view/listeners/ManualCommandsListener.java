/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view.listeners;

import LogoRefacto.Controller.AbstractController;
import LogoRefacto.Controller.MainController;
import LogoRefacto.Controller.ManualController;
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
public class ManualCommandsListener extends CommandListener {

    public ManualCommandsListener(MainController mainController, MainView mainView) {
        super(mainController, mainView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals(ManualCommandsView.CMD_COLORLIST)) {
            mainView.setColor(((JComboBox) e.getSource()).getSelectedIndex());
        }
    }

}
