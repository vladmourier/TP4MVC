/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import static LogoRefacto.Application.HGAP;
import static LogoRefacto.view.MainView.CMD_BTN_AVANCER;
import static LogoRefacto.view.MainView.CMD_BTN_BAISSER;
import static LogoRefacto.view.MainView.CMD_BTN_DROITE;
import static LogoRefacto.view.MainView.CMD_BTN_GAUCHE;
import static LogoRefacto.view.MainView.CMD_BTN_LEVER;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Vlad
 */
public class ManualCommandsView extends JToolBar {

    public static final String CMD_COLORLIST = "COLORLIST";
    private JTextField inputValue;
    JComboBox colorList;

    public ManualCommandsView() {
        addButton(this, "Effacer", "Nouveau dessin", "/icons/index.png");
        this.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45", 5);
        this.add(inputValue);
        addButton(this, CMD_BTN_AVANCER, "Avancer 50", null);
        addButton(this, CMD_BTN_DROITE, "Droite 45", null);
        addButton(this, CMD_BTN_GAUCHE, "Gauche 45", null);
        addButton(this, CMD_BTN_LEVER, "Lever Crayon", null);
        addButton(this, CMD_BTN_BAISSER, "Baisser Crayon", null);

        String[] colorStrings = {"noir", "bleu", "cyan", "gris fonce", "rouge",
            "vert", "gris clair", "magenta", "orange",
            "gris", "rose", "jaune"};

        // Create the combo box
        this.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        this.add(colorLabel);
        colorList = new JComboBox(colorStrings);
        colorList.setActionCommand(CMD_COLORLIST);
        this.add(colorList);

    }

    //utilitaires pour installer des boutons et des menus
    private void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);
                b = (JButton) p.add(new JButton(im));
            } else {
                b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
    }

    public String getInputValue() {
        return inputValue.getText();
    }

    public JComboBox getColorList() {
        return colorList;
    }

}
