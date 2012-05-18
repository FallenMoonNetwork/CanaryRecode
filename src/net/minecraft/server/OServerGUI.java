package net.minecraft.server;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import net.minecraft.server.OGuiLogOutputHandler;
import net.minecraft.server.OGuiStatsComponent;
import net.minecraft.server.OICommandListener;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OPlayerListBox;
import net.minecraft.server.OServerGuiCommandListener;
import net.minecraft.server.OServerGuiFocusAdapter;
import net.minecraft.server.OServerWindowAdapter;

public class OServerGUI extends JComponent implements OICommandListener {

    public static Logger a = Logger.getLogger("Minecraft");
    private OMinecraftServer b;

    public static void a(OMinecraftServer var0) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var3) {
            ;
        }

        OServerGUI var1 = new OServerGUI(var0);
        JFrame var2 = new JFrame("Minecraft server");
        var2.add(var1);
        var2.pack();
        var2.setLocationRelativeTo((Component) null);
        var2.setVisible(true);
        var2.addWindowListener(new OServerWindowAdapter(var0));
    }

    public OServerGUI(OMinecraftServer var1) {
        super();
        this.b = var1;
        this.setPreferredSize(new Dimension(854, 480));
        this.setLayout(new BorderLayout());

        try {
            this.add(this.c(), "Center");
            this.add(this.a(), "West");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private JComponent a() {
        JPanel var1 = new JPanel(new BorderLayout());
        var1.add(new OGuiStatsComponent(this.b), "North");
        var1.add(this.b(), "Center");
        var1.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
        return var1;
    }

    private JComponent b() {
        OPlayerListBox var1 = new OPlayerListBox(this.b);
        JScrollPane var2 = new JScrollPane(var1, 22, 30);
        var2.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
        return var2;
    }

    private JComponent c() {
        JPanel var1 = new JPanel(new BorderLayout());
        JTextArea var2 = new JTextArea();
        a.addHandler(new OGuiLogOutputHandler(var2));
        JScrollPane var3 = new JScrollPane(var2, 22, 30);
        var2.setEditable(false);
        JTextField var4 = new JTextField();
        var4.addActionListener(new OServerGuiCommandListener(this, var4));
        var2.addFocusListener(new OServerGuiFocusAdapter(this));
        var1.add(var3, "Center");
        var1.add(var4, "South");
        var1.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
        return var1;
    }

    public void b(String var1) {
        a.info(var1);
    }

    public String d() {
        return "CONSOLE";
    }

    // $FF: synthetic method
    static OMinecraftServer a(OServerGUI var0) {
        return var0.b;
    }

}
