package net.minecraft.server;

import net.minecraft.server.OStructureStrongholdPieceWeight;

final class OStructureStrongholdPieceWeight2 extends OStructureStrongholdPieceWeight {

    OStructureStrongholdPieceWeight2(Class var1, int var2, int var3) {
        super(var1, var2, var3);
    }

    @Override
    public boolean a(int var1) {
        return super.a(var1) && var1 > 4;
    }
}
