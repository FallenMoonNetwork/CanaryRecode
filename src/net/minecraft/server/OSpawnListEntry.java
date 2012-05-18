package net.minecraft.server;

import net.minecraft.server.OWeightedRandomChoice;

public class OSpawnListEntry extends OWeightedRandomChoice {

    public Class a;
    public int b;
    public int c;

    public OSpawnListEntry(Class var1, int var2, int var3, int var4) {
        super(var2);
        this.a = var1;
        this.b = var3;
        this.c = var4;
    }
}
