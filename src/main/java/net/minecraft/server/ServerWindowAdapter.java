package net.minecraft.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;

final class ServerWindowAdapter extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent windowevent) {
        Canary.getServer().initiateShutdown();

        while (!Canary.getServer().isRunning()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException interruptedexception) {
                if (Configuration.getServerConfig().isDebugMode()) {
                    Canary.logStackTrace("", interruptedexception);
                }
            }
        }

        System.exit(0);
    }
}
