package net.minecraft.server;


import java.util.Random;


class StructureStrongholdStones extends StructurePieceBlockSelector {

    private StructureStrongholdStones() {}

    public void a(Random random, int i0, int i1, int i2, boolean flag0) {
        if (flag0) {
            this.a = Block.br.cF;
            float f0 = random.nextFloat();

            if (f0 < 0.2F) {
                this.b = 2;
            } else if (f0 < 0.5F) {
                this.b = 1;
            } else if (f0 < 0.55F) {
                this.a = Block.bq.cF;
                this.b = 2;
            } else {
                this.b = 0;
            }
        } else {
            this.a = 0;
            this.b = 0;
        }
    }

    StructureStrongholdStones(StructureStrongholdPieceWeight2 structurestrongholdpieceweight2) {
        this();
    }
}
