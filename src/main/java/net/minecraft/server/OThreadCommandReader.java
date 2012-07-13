package net.minecraft.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.canarymod.Canary;
import net.canarymod.CanaryMod;
import net.canarymod.Logman;
import net.minecraft.server.OMinecraftServer;

public class OThreadCommandReader extends Thread {

    // $FF: synthetic field
    final OMinecraftServer a;

    public OThreadCommandReader(OMinecraftServer var1) {
        super();
        this.a = var1;
    }

    @Override
    public void run() {
        BufferedReader var1 = new BufferedReader(new InputStreamReader(System.in));
        String var2 = null;

        try {
            while (!this.a.i && OMinecraftServer.a(this.a) && (var2 = var1.readLine()) != null) {
                try{
                    // CanaryMod start - onConsoleCommand
                    if (!Canary.getServer().consoleCommand(var2)) {
                        this.a.a(var2, this.a);
                    }
                    // CanaryMod end - onConsoleCommand
                }
                catch(Throwable t){ //So if something goes wrong the console will still respond to commands
                    Logman.logStackTrace("Unhandled Exception in OThreadCommandReader: ", t);
                }
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }
}
