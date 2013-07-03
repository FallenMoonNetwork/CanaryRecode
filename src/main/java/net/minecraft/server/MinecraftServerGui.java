package net.minecraft.server;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MinecraftServerGui extends JComponent {

    private static boolean a;
    private DedicatedServer b;

    public static void a(DedicatedServer dedicatedserver) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            ;
        }

        MinecraftServerGui minecraftservergui = new MinecraftServerGui(dedicatedserver);

        a = true;
        JFrame jframe = new JFrame("Minecraft server");

        jframe.add(minecraftservergui);
        jframe.pack();
        jframe.setLocationRelativeTo((Component) null);
        jframe.setVisible(true);
        jframe.addWindowListener(new MinecraftServerGuiINNER1(dedicatedserver));
    }

    public MinecraftServerGui(DedicatedServer dedicatedserver) {
        this.b = dedicatedserver;
        this.setPreferredSize(new Dimension(854, 480));
        this.setLayout(new BorderLayout());

        try {
            this.add(this.d(), "Center");
            this.add(this.b(), "West");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private JComponent b() {
        JPanel jpanel = new JPanel(new BorderLayout());

        jpanel.add(new StatsComponent(this.b), "North");
        jpanel.add(this.c(), "Center");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
        return jpanel;
    }

    private JComponent c() {
        PlayerListComponent playerlistcomponent = new PlayerListComponent(this.b);
        JScrollPane jscrollpane = new JScrollPane(playerlistcomponent, 22, 30);

        jscrollpane.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
        return jscrollpane;
    }

    private JComponent d() {
        JPanel jpanel = new JPanel(new BorderLayout());
        JTextArea jtextarea = new JTextArea();

        this.b.an().a().addHandler(new TextAreaLogHandler(jtextarea));
        JScrollPane jscrollpane = new JScrollPane(jtextarea, 22, 30);

        jtextarea.setEditable(false);
        JTextField jtextfield = new JTextField();

        jtextfield.addActionListener(new MinecraftServerGuiINNER2(this, jtextfield));
        jtextarea.addFocusListener(new MinecraftServerGuiINNER3(this));
        jpanel.add(jscrollpane, "Center");
        jpanel.add(jtextfield, "South");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
        return jpanel;
    }

    static DedicatedServer a(MinecraftServerGui minecraftservergui) {
        return minecraftservergui.b;
    }
}
