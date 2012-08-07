package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OEntityWolf;
import net.minecraft.server.OSpawnListEntry;
import net.minecraft.server.OWorldGenTaiga1;
import net.minecraft.server.OWorldGenTaiga2;
import net.minecraft.server.OWorldGenerator;


public class OBiomeGenTaiga extends OBiomeGenBase {

    public OBiomeGenTaiga(int var1) {
        super(var1);
        this.K.add(new OSpawnListEntry(OEntityWolf.class, 8, 4, 4));
        this.I.z = 10;
        this.I.B = 1;
    }

    @Override
    public OWorldGenerator a(Random var1) {
        return (var1.nextInt(3) == 0 ? new OWorldGenTaiga1() : new OWorldGenTaiga2(false));
    }
}
