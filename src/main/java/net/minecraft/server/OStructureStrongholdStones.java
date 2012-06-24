package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OStructurePieceBlockSelector;
import net.minecraft.server.OStructureStrongholdPieceWeight2;

class OStructureStrongholdStones extends OStructurePieceBlockSelector {

    private OStructureStrongholdStones() {
        super();
    }

    @Override
    public void a(Random var1, int var2, int var3, int var4, boolean var5) {
        if (!var5) {
            this.a = 0;
            this.b = 0;
        } else {
            this.a = OBlock.bm.bO;
            float var6 = var1.nextFloat();
            if (var6 < 0.2F) {
                this.b = 2;
            } else if (var6 < 0.5F) {
                this.b = 1;
            } else if (var6 < 0.55F) {
                this.a = OBlock.bl.bO;
                this.b = 2;
            } else {
                this.b = 0;
            }
        }

    }

    // $FF: synthetic method
    OStructureStrongholdStones(OStructureStrongholdPieceWeight2 var1) {
        this();
    }
}
