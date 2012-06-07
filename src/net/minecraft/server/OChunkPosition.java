package net.minecraft.server;

import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVec3D;

public class OChunkPosition {

    public final int a;
    public final int b;
    public final int c;

    public OChunkPosition(int var1, int var2, int var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public OChunkPosition(OVec3D var1) {
        this(OMathHelper.b(var1.a), OMathHelper.b(var1.b), OMathHelper.b(var1.c));
    }

    @Override
    public boolean equals(Object var1) {
        if (!(var1 instanceof OChunkPosition)) {
            return false;
        } else {
            OChunkPosition var2 = (OChunkPosition) var1;
            return var2.a == this.a && var2.b == this.b && var2.c == this.c;
        }
    }

    @Override
    public int hashCode() {
        return this.a * 8976890 + this.b * 981131 + this.c;
    }
}
