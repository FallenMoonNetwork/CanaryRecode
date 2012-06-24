package net.minecraft.server;

import net.minecraft.server.OBiomeEndDecorator;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityEnderman;
import net.minecraft.server.OSpawnListEntry;

public class OBiomeGenEnd extends OBiomeGenBase {

    public OBiomeGenEnd(int var1) {
        super(var1);
        this.J.clear();
        this.K.clear();
        this.L.clear();
        this.J.add(new OSpawnListEntry(OEntityEnderman.class, 10, 4, 4));
        this.A = (byte) OBlock.v.bO;
        this.B = (byte) OBlock.v.bO;
        this.I = new OBiomeEndDecorator(this);
    }
}
