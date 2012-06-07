package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OFacing;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OTileEntity;

public class OTileEntityPiston extends OTileEntity {

    private int a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private float f;
    private float g;
    private static List h = new ArrayList();

    public OTileEntityPiston() {
        super();
    }

    public OTileEntityPiston(int var1, int var2, int var3, boolean var4, boolean var5) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
    }

    public int c() {
        return this.a;
    }

    @Override
    public int k() {
        return this.b;
    }

    public boolean e() {
        return this.d;
    }

    public int f() {
        return this.c;
    }

    public float a(float var1) {
        if (var1 > 1.0F) {
            var1 = 1.0F;
        }

        return this.g + (this.f - this.g) * var1;
    }

    private void a(float var1, float var2) {
        if (!this.d) {
            --var1;
        } else {
            var1 = 1.0F - var1;
        }

        OAxisAlignedBB var3 = OBlock.ac.b(this.k, this.l, this.m, this.n, this.a, var1, this.c);
        if (var3 != null) {
            List var4 = this.k.b((OEntity) null, var3);
            if (!var4.isEmpty()) {
                h.addAll(var4);
                Iterator var5 = h.iterator();

                while (var5.hasNext()) {
                    OEntity var6 = (OEntity) var5.next();
                    var6.a((double) (var2 * OFacing.b[this.c]), (double) (var2 * OFacing.c[this.c]), (double) (var2 * OFacing.d[this.c]));
                }

                h.clear();
            }
        }

    }

    public void g() {
        if (this.g < 1.0F && this.k != null) {
            this.g = this.f = 1.0F;
            this.k.q(this.l, this.m, this.n);
            this.j();
            if (this.k.a(this.l, this.m, this.n) == OBlock.ac.bO) {
                this.k.b(this.l, this.m, this.n, this.a, this.b);
            }
        }

    }

    @Override
    public void q_() {
        this.g = this.f;
        if (this.g >= 1.0F) {
            this.a(1.0F, 0.25F);
            this.k.q(this.l, this.m, this.n);
            this.j();
            if (this.k.a(this.l, this.m, this.n) == OBlock.ac.bO) {
                this.k.b(this.l, this.m, this.n, this.a, this.b);
            }

        } else {
            this.f += 0.5F;
            if (this.f >= 1.0F) {
                this.f = 1.0F;
            }

            if (this.d) {
                this.a(this.f, this.f - this.g + 0.0625F);
            }

        }
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.a = var1.f("blockId");
        this.b = var1.f("blockData");
        this.c = var1.f("facing");
        this.g = this.f = var1.h("progress");
        this.d = var1.o("extending");
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("blockId", this.a);
        var1.a("blockData", this.b);
        var1.a("facing", this.c);
        var1.a("progress", this.g);
        var1.a("extending", this.d);
    }

}
