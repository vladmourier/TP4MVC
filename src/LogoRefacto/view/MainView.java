/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import LogoRefacto.Controller.AbstractController;
import LogoRefacto.model.Shapes.MovePattern;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlad
 */
public class MainView extends JFrame implements IView {

    public static final String CMD_AVANCER = "Avancer";
    public static final String CMD_DROITE = "Droite";
    public static final String CMD_GAUCHE = "Gauche";
    public static final String CMD_EFFACER = "Effacer";
    public static final String CMD_QUITTER = "Quitter";
    public static final String CMD_CREER_TORTUE = "Créer tortue";
    public static final String CMD_SUPPRIMER_TORTUE = "Supprimer tortue";
    public static final String CMD_NEXT_TORTUE = "Tortue suivante";

    private PopulationView populationView;
    private ProcedureBarView procedureBarView;

    // Boutons
    private ManualCommandsView toolBar;
    private JPanel buttonPanel;
    // les boutons du bas
    private JMenu menuCommandes;

    public MainView(AbstractController controller) {

        populationView = new PopulationView();
        Init();
        populationView.paintComponent(getGraphics());
        controller.addObserver(populationView);
        controller.initializePopulation();
    }

    public void lockToolbar(boolean b) {
        if (b) toolBar.lock();
        else toolBar.unlock();
    }

    public void Init() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Boutons
        toolBar = new ManualCommandsView();
        buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel, "North");

        // Menus
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Effacer", CMD_EFFACER, KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", CMD_QUITTER, KeyEvent.VK_Q);

        menuCommandes = new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", CMD_AVANCER, -1);
        addMenuItem(menuCommandes, "Droite", CMD_DROITE, -1);
        addMenuItem(menuCommandes, "Gauche", CMD_GAUCHE, -1);
        addMenuItem(menuCommandes, "Ajouter Tortue", CMD_CREER_TORTUE, -1);
        addMenuItem(menuCommandes, "Supprimer Tortue", CMD_SUPPRIMER_TORTUE, -1);

//        addMenuItem(menuCommandes, "Lever Crayon", CMD_LEVER, -1);
//        addMenuItem(menuCommandes, "Baisser Crayon", CMD_BAISSER, -1);
        JMenu menuHelp = new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // les boutons du bas
        procedureBarView = new ProcedureBarView();

        getContentPane().add(procedureBarView, "South");

        populationView = new PopulationView(); //500, 400);
        populationView.setBackground(Color.white);
        populationView.setSize(new Dimension(600, 400));
        populationView.setPreferredSize(new Dimension(600, 400));

        getContentPane().add(populationView, "Center");

        pack();
        setVisible(true);
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
        populationView.reset();
    }

    public String getInputValue() {
        return toolBar.getInputValue();
    }

    public PopulationView getFeuille() {
        return populationView;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JPanel getProcedureBarView() {
        return procedureBarView;
    }

    @Override
    public void clearView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeWindow() {
        System.exit(0);
    }

    @Override
    public void displayPopulation(PopulationTortue t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePopulation(PopulationTortue t) {
        populationView.showTurtles(getGraphics());
    }

    @Override
    public void updateTortue(Tortue t) {
        populationView.showTurtles(getGraphics());
    }

    @Override
    public void addTortue(Tortue t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String showChooseBox() {
        String[] option = {
            AbstractController.MODE_MANUEL,
            AbstractController.MODE_AUTO
        };
        int n = JOptionPane.showOptionDialog(this, "Sélectionnez le mode de fonctionnement", "Choix du mode", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        return n != -1 ? option[n] : option[0];
    }

//    public void SetColor()
    protected Color decodeColor(int c) {
        switch (c) {
            case 0:
                return (Color.black);
            case 1:
                return (Color.blue);
            case 2:
                return (Color.cyan);
            case 3:
                return (Color.darkGray);
            case 4:
                return (Color.red);
            case 5:
                return (Color.green);
            case 6:
                return (Color.lightGray);
            case 7:
                return (Color.magenta);
            case 8:
                return (Color.orange);
            case 9:
                return (Color.gray);
            case 10:
                return (Color.pink);
            case 11:
                return (Color.yellow);
            default:
                return (Color.black);
        }
    }

    public void setColor(int n) {
        populationView.setCurrentColor(decodeColor(n));
    }

    public static List<String> getActionCommands() {
        Field[] forName = MainView.class.getDeclaredFields();
        ArrayList<String> s = new ArrayList<>();
        for (Field f : forName) {
            s.add(f.getName());
        }
        return s;
    }
}
