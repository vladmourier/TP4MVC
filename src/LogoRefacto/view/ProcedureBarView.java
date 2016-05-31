/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogoRefacto.view;

import LogoRefacto.model.Shapes.Carre;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Vlad & Hassane
 */
public class ProcedureBarView extends JPanel {
    private JButton b20;
    private JButton b21;
    private JButton b22;
    
    public ProcedureBarView(LayoutManager layout) {
        super(layout);
    }

    public ProcedureBarView() {
        super(new GridLayout());
        b20 = new JButton(Carre.CARRE);
        this.add(b20);
        b21 = new JButton(Carre.POLYGONE);
        this.add(b21);
        b22 = new JButton(Carre.SPIRALE);
        this.add(b22);
        setVisible(true);
    }

    public JButton getB20() {
        return b20;
    }

    public JButton getB21() {
        return b21;
    }

    public JButton getB22() {
        return b22;
    }

}
