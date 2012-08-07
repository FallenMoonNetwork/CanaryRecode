package net.minecraft.server;


import net.minecraft.server.OMinecraftServer;


public class OThreadSleepForever extends Thread {

    // $FF: synthetic field
    final OMinecraftServer a;

    public OThreadSleepForever(OMinecraftServer var1) {
        super();
        this.a = var1;
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(2147483647L);
                }
            } catch (InterruptedException var2) {
                ;
            }
        }
    }
}
