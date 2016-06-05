package LogoRefacto;

// package logo;
import LogoRefacto.Controller.MainController;
import LogoRefacto.view.MainView;

import javax.swing.*;

/**
 * ***********************************************************************
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
 *************************************************************************
 */
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                MainController mc = new MainController(1200, 720);

                MainView mv = new MainView(mc);

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
