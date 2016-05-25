package LogoRefacto;

// package logo;
import LogoRefacto.Controller.MainController;
import LogoRefacto.Controller.SwingController;
import LogoRefacto.model.PopulationTortue;
import LogoRefacto.model.Tortue;
import LogoRefacto.view.PopulationView;
import LogoRefacto.view.MainView;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.io.*;

/** ***********************************************************************
 *
 * Un petit Logo minimal qui devra etre ameliore par la suite
 *
 * J. Ferber - 1999-2001
 *
 * Cours de DESS TNI - Montpellier II
 *
 * @version 2.0
 * @date 25/09/
 *
 *
 ************************************************************************* */
public class Application {

    public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(5, 1);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Tortue t;
                PopulationTortue fd = new PopulationTortue();

                PopulationView feuille = new PopulationView();

                MainView mv = new MainView(feuille);
                
                SwingController sc = new SwingController(fd, mv);

                MainController mc = new MainController(sc);

                for (Component c : mv.getToolBar().getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton) c).addActionListener(sc);
                    }
                }

                for (Component c : mv.getButtonPanel().getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton) c).addActionListener(sc);
                    }
                }

                for (Component c : mv.getProcedureBarView().getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton) c).addActionListener(sc);
                    }
                }
                
                sc.createTortue();
                
                mv.setVisible(true);

            }
        });

    }

//    public Application() {
//        super("un logo tout simple");
//
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent arg0) {
//                super.windowClosing(arg0);
//                System.exit(0);
//            }
//        });
//    }
}
