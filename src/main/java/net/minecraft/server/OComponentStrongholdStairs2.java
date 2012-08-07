package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OComponentStrongholdPortalRoom;
import net.minecraft.server.OComponentStrongholdStairs;
import net.minecraft.server.OStructureStrongholdPieceWeight;

public class OComponentStrongholdStairs2 extends OComponentStrongholdStairs {

    public OStructureStrongholdPieceWeight a;
    public OComponentStrongholdPortalRoom b;
    public ArrayList c = new ArrayList();

    public OComponentStrongholdStairs2(int var1, Random var2, int var3, int var4) {
        super(0, var2, var3, var4);
    }

    @Override
    public OChunkPosition b_() {
        return this.b != null ? this.b.b_() : super.b_();
    }
}
