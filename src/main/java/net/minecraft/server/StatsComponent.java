package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Collection;

public class StatsComponent extends JComponent {

    private static final DecimalFormat a = new DecimalFormat("########0.000");
    private int[] b = new int[256];
    private int c;
    private String[] d = new String[11];
    private final MinecraftServer e;

    public StatsComponent(MinecraftServer minecraftserver) {
        this.e = minecraftserver;
        this.setPreferredSize(new Dimension(456, 246));
        this.setMinimumSize(new Dimension(456, 246));
        this.setMaximumSize(new Dimension(456, 246));
        (new Timer(500, new StatsComponentINNER1(this))).start();
        this.setBackground(Color.BLACK);
    }

    private void a() {
        long i0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.gc();
        this.d[0] = "Memory use: " + i0 / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.d[1] = "Threads: " + TcpConnection.a.get() + " + " + TcpConnection.b.get();
        this.d[2] = "Avg tick: " + a.format(this.a(this.e.j) * 1.0E-6D) + " ms";
        this.d[3] = "Avg sent: " + (int)this.a(this.e.f) + ", Avg size: " + (int)this.a(this.e.g);
        this.d[4] = "Avg rec: " + (int)this.a(this.e.h) + ", Avg size: " + (int)this.a(this.e.i);

        // CanaryMod: Multiworld
        Collection<World> worlds = Canary.getServer().getWorldManager().getAllWorlds();
        if (worlds != null) {
            int i = 0;
            for (World world : worlds) {
                WorldServer level = (WorldServer)(((CanaryWorld)world).getHandle());
                this.d[5 + i] = "World " + world.getName() + " lvl " + i + " tick " + a.format(world.getNanoTick(i) * 1.0E-6D) + " ms";
                if (world != null && level.b != null) {
                    this.d[5 + i] += ", " + level.b.d();
                }
                i++;
            }
        }
        //

        double d0 = 12500.0D;

        this.b[this.c++ & 255] = (int)(this.a(this.e.g) * 100.0D / 12500.0D);
        this.repaint();
    }

    private double a(long[] along) {
        long i0 = 0L;

        for (int i1 = 0; i1 < along.length; ++i1) {
            i0 += along[i1];
        }

        return (double)i0 / (double)along.length;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(new Color(16777215));
        graphics.fillRect(0, 0, 456, 246);

        int i0;

        for (i0 = 0; i0 < 256; ++i0) {
            int i1 = this.b[i0 + this.c & 255];

            graphics.setColor(new Color(i1 + 28 << 16));
            graphics.fillRect(i0, 100 - i1, 1, i1);
        }

        graphics.setColor(Color.BLACK);

        for (i0 = 0; i0 < this.d.length; ++i0) {
            String s0 = this.d[i0];

            if (s0 != null) {
                graphics.drawString(s0, 32, 116 + i0 * 16);
            }
        }
    }

    static void a(StatsComponent statscomponent) {
        statscomponent.a();
    }
}
