package net.minecraft.server;

import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OMinecraftServer;

public class OConvertProgressUpdater implements OIProgressUpdate {

    private long b;
    // $FF: synthetic field
    final OMinecraftServer a;

    public OConvertProgressUpdater(OMinecraftServer var1) {
        super();
        this.a = var1;
        this.b = System.currentTimeMillis();
    }

    @Override
    public void a(String var1) {
    }

    @Override
    public void a(int var1) {
        if (System.currentTimeMillis() - this.b >= 1000L) {
            this.b = System.currentTimeMillis();
            OMinecraftServer.a.info("Converting... " + var1 + "%");
        }

    }

    @Override
    public void b(String var1) {
    }
}
