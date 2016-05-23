/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import static LogoRefacto.Application.HGAP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 *
 * @author Vlad
 */
public class MainView extends JFrame {

    public static final String CMD_BTN_AVANCER = "Avancer";
    public static final String CMD_BTN_DROITE = "Droite";
    public static final String CMD_BTN_GAUCHE = "Gauche";
    public static final String CMD_BTN_LEVER = "Lever";
    public static final String CMD_BTN_BAISSER = "Baisser";
    public static final String CMD_BTN_EFFACER = "Efacer";
    public static final String CMD_BTN_QUITTER = "Quitter";

    private FeuilleView feuille;
    private JTextField inputValue;

    // Boutons
    private JToolBar toolBar;
    private JPanel buttonPanel;
    // les boutons du bas
    private JPanel p2;
    private JMenu menuCommandes;

    public MainView(FeuilleView feuille) {
        this.feuille = feuille;
        logoInit();
        feuille.paintComponent(getGraphics());
    }

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Boutons
        toolBar = new JToolBar();
        buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel, "North");

        addButton(toolBar, "Effacer", "Nouveau dessin", "/icons/index.png");

        toolBar.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45", 5);
        toolBar.add(inputValue);
        addButton(toolBar, CMD_BTN_AVANCER, "Avancer 50", null);
        addButton(toolBar, CMD_BTN_DROITE, "Droite 45", null);
        addButton(toolBar, CMD_BTN_GAUCHE, "Gauche 45", null);
        addButton(toolBar, CMD_BTN_LEVER, "Lever Crayon", null);
        addButton(toolBar, CMD_BTN_BAISSER, "Baisser Crayon", null);

        String[] colorStrings = {"noir", "bleu", "cyan", "gris fonce", "rouge",
            "vert", "gris clair", "magenta", "orange",
            "gris", "rose", "jaune"};

        // Create the combo box
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        JComboBox colorList = new JComboBox(colorStrings);
        toolBar.add(colorList);

        colorList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int n = cb.getSelectedIndex();
//                feuille.setColor(n);
            }
        });

        // Menus
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Effacer", CMD_BTN_EFFACER, KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", CMD_BTN_QUITTER, KeyEvent.VK_Q);

        menuCommandes = new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", CMD_BTN_AVANCER, -1);
        addMenuItem(menuCommandes, "Droite", CMD_BTN_DROITE, -1);
        addMenuItem(menuCommandes, "Gauche", CMD_BTN_GAUCHE, -1);
        addMenuItem(menuCommandes, "Lever Crayon", CMD_BTN_LEVER, -1);
        addMenuItem(menuCommandes, "Baisser Crayon", CMD_BTN_BAISSER, -1);

        JMenu menuHelp = new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // les boutons du bas
        p2 = new JPanel(new GridLayout());
        JButton b20 = new JButton("Proc1");
        p2.add(b20);
        JButton b21 = new JButton("Proc2");
        p2.add(b21);
        JButton b22 = new JButton("Proc3");
        p2.add(b22);

        getContentPane().add(p2, "South");

        feuille = new FeuilleView(); //500, 400);
        feuille.setBackground(Color.white);
        feuille.setSize(new Dimension(600, 400));
        feuille.setPreferredSize(new Dimension(600, 400));

        getContentPane().add(feuille, "Center");

        pack();
        setVisible(true);
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

    private void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
        }
    }

    public void reset() {
        feuille.reset();
    }

    public String getInputValue() {
        String s = inputValue.getText();
        return (s);
    }

    public FeuilleView getFeuille() {
        return feuille;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JPanel getP2() {
        return p2;
    }

}
