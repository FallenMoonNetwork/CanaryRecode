package net.minecraft.server;


import net.minecraft.server.OMaterial;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;


public class OTileEntityNote extends OTileEntity {

    public byte a = 0;
    public boolean b = false;

    public OTileEntityNote() {
        super();
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("note", this.a);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.a = var1.d("note");
        if (this.a < 0) {
            this.a = 0;
        }

        if (this.a > 24) {
            this.a = 24;
        }

    }

    public void c() {
        this.a = (byte) ((this.a + 1) % 25);
        this.G_();
    }

    public void a(OWorld var1, int var2, int var3, int var4) {
        if (var1.d(var2, var3 + 1, var4) == OMaterial.a) {
            OMaterial var5 = var1.d(var2, var3 - 1, var4);
            byte var6 = 0;

            if (var5 == OMaterial.e) {
                var6 = 1;
            }

            if (var5 == OMaterial.o) {
                var6 = 2;
            }

            if (var5 == OMaterial.q) {
                var6 = 3;
            }

            if (var5 == OMaterial.d) {
                var6 = 4;
            }

            var1.e(var2, var3, var4, var6, this.a);
        }
    }
}
