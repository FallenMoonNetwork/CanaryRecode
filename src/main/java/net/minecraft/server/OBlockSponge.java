package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockSponge extends OBlock {

    protected OBlockSponge(int var1) {
        super(var1, OMaterial.l);
        this.bN = 48;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {}

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {}
}
