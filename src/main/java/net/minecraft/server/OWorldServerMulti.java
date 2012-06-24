package net.minecraft.server;

import net.minecraft.server.OISaveHandler;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OWorldServer;
import net.minecraft.server.OWorldSettings;

public class OWorldServerMulti extends OWorldServer {

    public OWorldServerMulti(OMinecraftServer var1, OISaveHandler var2, String var3, int var4, OWorldSettings var5, OWorldServer var6) {
        super(var1, var2, var3, var4, var5);
        this.z = var6.z;
    }
}
