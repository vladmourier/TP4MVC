package LogoRefacto;

// package logo;
import LogoRefacto.Controller.MainController;
import LogoRefacto.view.listeners.MainListener;
import LogoRefacto.view.MainView;
import LogoRefacto.view.listeners.ManualCommandsListener;
import LogoRefacto.view.listeners.ProcedureBarListener;
import java.awt.*;

import javax.swing.*;


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
                
                MainController mc = new MainController();
                
                MainView mv = new MainView(mc);
                
                MainListener sc = new MainListener(mc, mv);
                
                ProcedureBarListener pbl = new ProcedureBarListener(mc);
                
                ManualCommandsListener mcl = new ManualCommandsListener(mc , mv);
                
                for (Component c : mv.getToolBar().getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton) c).addActionListener(mcl);
                    } else if (c instanceof JComboBox){
                        ((JComboBox) c).addActionListener(mcl);
                    }
                }

                for (Component c : mv.getButtonPanel().getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton) c).addActionListener(sc);
                    }
                }

                for (Component c : mv.getProcedureBarView().getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton) c).addActionListener(pbl);
                    }
                }
                
               
                
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
