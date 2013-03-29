package net.minecraft.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.canarymod.Canary;

class DedicatedServerCommandThread extends Thread {

    final DedicatedServer a;

    DedicatedServerCommandThread(DedicatedServer dedicatedserver) {
        this.a = dedicatedserver;
    }

    public void run() {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));

        String s0;

        try {
            while (!this.a.ac() && this.a.m() && (s0 = bufferedreader.readLine()) != null) {
                // CanaryMod: Parse console commands
                if (!Canary.getServer().consoleCommand(s0)) {
                    this.a.a(s0, (ICommandSender) this.a);
                }
                //
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }
}
