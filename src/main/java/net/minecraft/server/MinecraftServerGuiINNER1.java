package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

final class MinecraftServerGuiINNER1 extends WindowAdapter {

    final DedicatedServer a;

    MinecraftServerGuiINNER1(DedicatedServer dedicatedserver) {
        this.a = dedicatedserver;
    }

    public void windowClosing(WindowEvent windowevent) {
        this.a.p();

        while (!this.a.ae()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedexception) {
                // CanaryMod: Don't show stack unless needed
                if (Configuration.getServerConfig().isDebugMode()) {
                    Canary.logStacktrace("", interruptedexception);
                }
                //
            }
        }

        System.exit(0);
    }
}
