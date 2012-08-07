package net.minecraft.server;


import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OStructureStrongholdPieces;
import net.minecraft.server.OWorld;


class OStructureStrongholdStart extends OStructureStart {

    public OStructureStrongholdStart(OWorld var1, Random var2, int var3, int var4) {
        super();
        OStructureStrongholdPieces.a();
        OComponentStrongholdStairs2 var5 = new OComponentStrongholdStairs2(0, var2, (var3 << 4) + 2, (var4 << 4) + 2);

        this.a.add(var5);
        var5.a(var5, this.a, var2);
        ArrayList var6 = var5.c;

        while (!var6.isEmpty()) {
            int var7 = var2.nextInt(var6.size());
            OStructureComponent var8 = (OStructureComponent) var6.remove(var7);

            var8.a(var5, this.a, var2);
        }

        this.d();
        this.a(var1, var2, 10);
    }
}
