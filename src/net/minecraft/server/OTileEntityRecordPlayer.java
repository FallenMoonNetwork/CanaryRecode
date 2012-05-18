package net.minecraft.server;

import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OTileEntity;

public class OTileEntityRecordPlayer extends OTileEntity {

    public int a;

    public OTileEntityRecordPlayer() {
        super();
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.a = var1.f("Record");
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        if (this.a > 0) {
            var1.a("Record", this.a);
        }

    }
}
