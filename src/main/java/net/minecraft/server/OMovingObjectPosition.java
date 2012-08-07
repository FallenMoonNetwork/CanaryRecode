package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEnumMovingObjectType;
import net.minecraft.server.OVec3D;

public class OMovingObjectPosition {

    public OEnumMovingObjectType a;
    public int b;
    public int c;
    public int d;
    public int e;
    public OVec3D f;
    public OEntity g;

    public OMovingObjectPosition(int var1, int var2, int var3, int var4, OVec3D var5) {
        super();
        this.a = OEnumMovingObjectType.a;
        this.b = var1;
        this.c = var2;
        this.d = var3;
        this.e = var4;
        this.f = OVec3D.b(var5.a, var5.b, var5.c);
    }

    public OMovingObjectPosition(OEntity var1) {
        super();
        this.a = OEnumMovingObjectType.b;
        this.g = var1;
        this.f = OVec3D.b(var1.bm, var1.bn, var1.bo);
    }
}
