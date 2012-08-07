package net.minecraft.server;


import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OEntityGhast;
import net.minecraft.server.OEntityLavaSlime;
import net.minecraft.server.OEntityPigZombie;
import net.minecraft.server.OSpawnListEntry;


public class OBiomeGenHell extends OBiomeGenBase {

    public OBiomeGenHell(int var1) {
        super(var1);
        this.J.clear();
        this.K.clear();
        this.L.clear();
        this.J.add(new OSpawnListEntry(OEntityGhast.class, 50, 4, 4));
        this.J.add(new OSpawnListEntry(OEntityPigZombie.class, 100, 4, 4));
        this.J.add(new OSpawnListEntry(OEntityLavaSlime.class, 1, 4, 4));
    }
}
