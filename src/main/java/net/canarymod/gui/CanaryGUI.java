package net.canarymod.gui;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Jos Kuijpers
 *
 */
public class CanaryGUI extends JComponent {
    
    private static final long serialVersionUID = 1L;

    public static void init() {
        
        CanaryGUI gui = new CanaryGUI();
        JFrame frame = new JFrame("Canary Server");
        frame.add(gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new CanaryWindowAdapter());
    }
    
    public CanaryGUI() {
        setPreferredSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        
        
    }
}
