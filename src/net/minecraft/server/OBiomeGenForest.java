package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OEntityWolf;
import net.minecraft.server.OSpawnListEntry;
import net.minecraft.server.OWorldGenerator;

public class OBiomeGenForest extends OBiomeGenBase {

    public OBiomeGenForest(int var1) {
        super(var1);
        this.K.add(new OSpawnListEntry(OEntityWolf.class, 5, 4, 4));
        this.I.z = 10;
        this.I.B = 2;
    }

    public OWorldGenerator a(Random var1) {
        return (var1.nextInt(5) == 0 ? this.P : (var1.nextInt(10) == 0 ? this.O : this.N));
    }
}
