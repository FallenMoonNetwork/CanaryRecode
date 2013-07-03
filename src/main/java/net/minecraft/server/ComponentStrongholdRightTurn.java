package net.minecraft.server;


import java.util.List;
import java.util.Random;


public class ComponentStrongholdRightTurn extends ComponentStrongholdLeftTurn {

    public ComponentStrongholdRightTurn(int i0, Random random,
            StructureBoundingBox structureboundingbox, int i1) {
        super(i0, random, structureboundingbox, i1);
    }

    public void a(StructureComponent structurecomponent, List list,
            Random random) {
        if (this.g != 2 && this.g != 3) {
            this.b((ComponentStrongholdStairs2) structurecomponent, list,
                    random, 1, 1);
        } else {
            this.c((ComponentStrongholdStairs2) structurecomponent, list,
                    random, 1, 1);
        }
    }

    public boolean a(World world, Random random,
            StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random,
                    StructureStrongholdPieces.b());
            this.a(world, random, structureboundingbox, this.a, 1, 1, 0);
            if (this.g != 2 && this.g != 3) {
                this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, 0, 0,
                        false);
            } else {
                this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, 0, 0,
                        false);
            }

            return true;
        }
    }
}
