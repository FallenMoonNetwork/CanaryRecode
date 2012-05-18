package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OPacket;
import net.minecraft.server.OWorld;

public class OItemMapBase extends OItem {

    protected OItemMapBase(int var1) {
        super(var1);
    }

    public boolean t_() {
        return true;
    }

    public OPacket c(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        return null;
    }
}
