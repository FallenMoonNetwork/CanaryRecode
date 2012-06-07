package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.server.OComponentVillageRoadPiece;
import net.minecraft.server.OComponentVillageStartPiece;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OStructureVillagePieces;
import net.minecraft.server.OWorld;

class OStructureVillageStart extends OStructureStart {

    private boolean c = false;

    public OStructureVillageStart(OWorld var1, Random var2, int var3, int var4, int var5) {
        super();
        ArrayList var7 = OStructureVillagePieces.a(var2, var5);
        OComponentVillageStartPiece var8 = new OComponentVillageStartPiece(var1.a(), 0, var2, (var3 << 4) + 2, (var4 << 4) + 2, var7, var5);
        this.a.add(var8);
        var8.a(var8, this.a, var2);
        ArrayList var9 = var8.f;
        ArrayList var10 = var8.e;

        int var11;
        while (!var9.isEmpty() || !var10.isEmpty()) {
            OStructureComponent var12;
            if (!var9.isEmpty()) {
                var11 = var2.nextInt(var9.size());
                var12 = (OStructureComponent) var9.remove(var11);
                var12.a(var8, this.a, var2);
            } else {
                var11 = var2.nextInt(var10.size());
                var12 = (OStructureComponent) var10.remove(var11);
                var12.a(var8, this.a, var2);
            }
        }

        this.d();
        var11 = 0;
        Iterator var14 = this.a.iterator();

        while (var14.hasNext()) {
            OStructureComponent var13 = (OStructureComponent) var14.next();
            if (!(var13 instanceof OComponentVillageRoadPiece)) {
                ++var11;
            }
        }

        this.c = var11 > 2;
    }

    @Override
    public boolean a() {
        return this.c;
    }
}
