package net.minecraft.server;

import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityWaterMob;
import net.minecraft.server.OIMob;
import net.minecraft.server.OMaterial;

public enum OEnumCreatureType {

    a("monster", 0, OIMob.class, 70, OMaterial.a, false), b("creature", 1, OEntityAnimal.class, 15, OMaterial.a, true), c(
            "waterCreature",
            2,
            OEntityWaterMob.class,
            5,
            OMaterial.g,
            true);
    private final Class d;
    private final int e;
    private final OMaterial f;
    private final boolean g;
    // $FF: synthetic field
    private static final OEnumCreatureType[] h = new OEnumCreatureType[] { a, b, c };

    private OEnumCreatureType(String var1, int var2, Class var3, int var4, OMaterial var5, boolean var6) {
        this.d = var3;
        this.e = var4;
        this.f = var5;
        this.g = var6;
    }

    public Class a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public OMaterial c() {
        return this.f;
    }

    public boolean d() {
        return this.g;
    }

}
