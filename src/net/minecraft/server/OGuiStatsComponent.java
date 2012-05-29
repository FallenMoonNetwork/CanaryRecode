package net.minecraft.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import javax.swing.JComponent;
import javax.swing.Timer;
import net.minecraft.server.OGuiStatsListener;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.ONetworkManager;

public class OGuiStatsComponent extends JComponent {

    private static final DecimalFormat a = new DecimalFormat("########0.000");
    private int[] b = new int[256];
    private int c = 0;
    private String[] d = new String[10];
    private final OMinecraftServer e;

    public OGuiStatsComponent(OMinecraftServer var1) {
        super();
        this.e = var1;
        this.setPreferredSize(new Dimension(356, 246));
        this.setMinimumSize(new Dimension(356, 246));
        this.setMaximumSize(new Dimension(356, 246));
        (new Timer(500, new OGuiStatsListener(this))).start();
        this.setBackground(Color.BLACK);
    }

    private void a() {
        long var1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.gc();
        this.d[0] = "Memory use: " + var1 / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.d[1] = "Threads: " + ONetworkManager.b + " + " + ONetworkManager.c;
        this.d[2] = "Avg tick: " + a.format(this.a(this.e.f) * 1.0E-6D) + " ms";
        this.d[3] = "Avg sent: " + (int) this.a(this.e.u) + ", Avg size: " + (int) this.a(this.e.v);
        this.d[4] = "Avg rec: " + (int) this.a(this.e.w) + ", Avg size: " + (int) this.a(this.e.x);
        if (this.e.worldServer != null) {
            for (int var3 = 0; var3 < this.e.worldServer.length; ++var3) {
                this.d[5 + var3] = "Lvl " + var3 + " tick: " + a.format(this.a(this.e.g[var3]) * 1.0E-6D) + " ms";
                if (this.e.worldServer[var3] != null && this.e.worldServer[var3].G != null) {
                    this.d[5 + var3] = this.d[5 + var3] + ", " + this.e.worldServer[var3].G.d();
                }
            }
        }

        this.b[this.c++ & 255] = (int) (this.a(this.e.v) * 100.0D / 12500.0D);
        this.repaint();
    }

    private double a(long[] var1) {
        long var2 = 0L;

        for (int var4 = 0; var4 < var1.length; ++var4) {
            var2 += var1[var4];
        }

        return (double) var2 / (double) var1.length;
    }

    public void paint(Graphics var1) {
        var1.setColor(new Color(16777215));
        var1.fillRect(0, 0, 356, 246);

        int var2;
        for (var2 = 0; var2 < 256; ++var2) {
            int var3 = this.b[var2 + this.c & 255];
            var1.setColor(new Color(var3 + 28 << 16));
            var1.fillRect(var2, 100 - var3, 1, var3);
        }

        var1.setColor(Color.BLACK);

        for (var2 = 0; var2 < this.d.length; ++var2) {
            String var4 = this.d[var2];
            if (var4 != null) {
                var1.drawString(var4, 32, 116 + var2 * 16);
            }
        }

    }

    // $FF: synthetic method
    static void a(OGuiStatsComponent var0) {
        var0.a();
    }

}
