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

import net.canarymod.api.gui.GUIControl;

public class ServerGUI extends JComponent implements GUIControl {

    private static boolean a = false;
    private static JFrame jframe;
    public static ServerGUI servergui = new ServerGUI();

    public static GUIControl a() {

        a = true;
        jframe = new JFrame("Minecraft server");

        jframe.add(servergui);
        jframe.pack();
        jframe.setLocationRelativeTo((Component) null);
        jframe.setVisible(true);
        jframe.addWindowListener(new ServerWindowAdapter());

        return servergui;

    }

    public ServerGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            ;
        }
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

        jpanel.add(new GuiStatsComponent(), "North");
        jpanel.add(this.c(), "Center");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
        return jpanel;
    }

    private JComponent c() {
        PlayerListBox playerlistbox = new PlayerListBox();
        JScrollPane jscrollpane = new JScrollPane(playerlistbox, 22, 30);

        jscrollpane.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
        return jscrollpane;
    }

    private JComponent d() {
        JPanel jpanel = new JPanel(new BorderLayout());
        JTextArea jtextarea = GuiLogOutputHandler.getJTextArea();

        JScrollPane jscrollpane = new JScrollPane(jtextarea, 22, 30);

        jtextarea.setEditable(false);
        JTextField jtextfield = new JTextField();

        jtextfield.addActionListener(new ServerGuiCommandListener(this, jtextfield));
        jtextarea.addFocusListener(new ServerGuiFocusAdapter(this));
        jpanel.add(jscrollpane, "Center");
        jpanel.add(jtextfield, "South");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
        return jpanel;
    }

    static DedicatedServer a(ServerGUI servergui) {
        return null;
    }

    @Override
    public void start() {
        ServerGUI.a();
    }

    @Override
    public void closeWindow() {
        if (jframe != null) {
            jframe.dispose();
        }
        jframe = null;
    }

    public static boolean isLoaded() {
        return a;
    }

}
