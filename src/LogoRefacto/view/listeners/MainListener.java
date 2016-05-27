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
        if (c.equals(MainView.CMD_QUITTER)) {
            mainController.closeApplication();
        }
    }

    @Override
    /**
     * la gestion des actions des boutons
     */
    public void actionPerformed(ActionEvent e) {
        current = e;
//        update(mainController.getTortueCourante());
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
