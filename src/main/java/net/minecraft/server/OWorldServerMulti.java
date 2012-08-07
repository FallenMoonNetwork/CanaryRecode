package net.minecraft.server;


import net.minecraft.server.OISaveHandler;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OWorldServer;
import net.minecraft.server.OWorldSettings;


public class OWorldServerMulti extends OWorldServer {

    // CanaryMod changed signature, pass a MapStorage instead of a whole freakin world!
    public OWorldServerMulti(OMinecraftServer var1, OISaveHandler var2, String var3, int var4, OWorldSettings var5, OMapStorage var6) {
        super(var1, var2, var3, var4, var5);
        this.z = var6;
    }
}
