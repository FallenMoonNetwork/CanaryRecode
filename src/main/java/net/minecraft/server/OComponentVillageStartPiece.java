package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OComponentVillageWell;
import net.minecraft.server.OStructureVillagePieceWeight;
import net.minecraft.server.OWorldChunkManager;

public class OComponentVillageStartPiece extends OComponentVillageWell {

    public OWorldChunkManager a;
    public int b;
    public OStructureVillagePieceWeight c;
    public ArrayList d;
    public ArrayList e = new ArrayList();
    public ArrayList f = new ArrayList();

    public OComponentVillageStartPiece(OWorldChunkManager var1, int var2, Random var3, int var4, int var5, ArrayList var6, int var7) {
        super(0, var3, var4, var5);
        this.a = var1;
        this.d = var6;
        this.b = var7;
    }

    public OWorldChunkManager a() {
        return this.a;
    }
}
