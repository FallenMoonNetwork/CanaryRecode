package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OEnumToolMaterial;
import net.minecraft.server.OItemTool;


public class OItemSpade extends OItemTool {

    private static OBlock[] bU = new OBlock[] { OBlock.u, OBlock.v, OBlock.E, OBlock.F, OBlock.aS, OBlock.aU, OBlock.aW, OBlock.aA, OBlock.bc, OBlock.by };

    public OItemSpade(int var1, OEnumToolMaterial var2) {
        super(var1, 1, var2, bU);
    }

    @Override
    public boolean a(OBlock var1) {
        return var1 == OBlock.aS ? true : var1 == OBlock.aU;
    }

}
