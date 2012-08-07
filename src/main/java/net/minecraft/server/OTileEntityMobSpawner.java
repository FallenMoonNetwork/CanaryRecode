package net.minecraft.server;


import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OEntityList;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket132TileEntityData;
import net.minecraft.server.OTileEntity;


public class OTileEntityMobSpawner extends OTileEntity {

    public int a = -1;
    public String d = "Pig"; // CanaryMod private -> public
    public double b;
    public double c = 0.0D;
    public int reset = 600;

    public OTileEntityMobSpawner() {
        super();
        this.a = 20;
    }

    public void a(String var1) {
        this.d = var1;
    }

    public boolean c() {
        return this.k.a(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D, 16.0D) != null;
    }

    @Override
    public void q_() {
        this.c = this.b;
        if (this.c()) {
            double var1 = (this.l + this.k.r.nextFloat());
            double var3 = (this.m + this.k.r.nextFloat());
            double var5 = (this.n + this.k.r.nextFloat());

            this.k.a("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
            this.k.a("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);

            for (this.b += (1000.0F / (this.a + 200.0F)); this.b > 360.0D; this.c -= 360.0D) {
                this.b -= 360.0D;
            }

            if (!this.k.F) {
                if (this.a == -1) {
                    this.e();
                }

                if (this.a > 0) {
                    --this.a;
                    return;
                }

                byte var7 = 4;

                for (int var8 = 0; var8 < var7; ++var8) {
                    OEntityLiving var9 = ((OEntityLiving) OEntityList.a(this.d, this.k));

                    if (var9 == null) {
                        return;
                    }

                    int var10 = this.k.a(var9.getClass(), OAxisAlignedBB.b(this.l, this.m, this.n, (this.l + 1), (this.m + 1), (this.n + 1)).b(8.0D, 4.0D, 8.0D)).size();

                    if (var10 >= 6) {
                        this.e();
                        return;
                    }

                    if (var9 != null) {
                        double var11 = this.l + (this.k.r.nextDouble() - this.k.r.nextDouble()) * 4.0D;
                        double var13 = (this.m + this.k.r.nextInt(3) - 1);
                        double var15 = this.n + (this.k.r.nextDouble() - this.k.r.nextDouble()) * 4.0D;

                        var9.c(var11, var13, var15, this.k.r.nextFloat() * 360.0F, 0.0F);
                        if (var9.l()) {
                            this.k.b(var9);
                            this.k.f(2004, this.l, this.m, this.n, 0);
                            var9.aC();
                            this.e();
                        }
                    }
                }
            }

            super.q_();
        }
    }

    private void e() { // CanaryMod  de-randomized the delay
        // this.a = 200 + this.k.r.nextInt(600);
        this.a = reset;
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.d = var1.j("EntityId");
        this.a = var1.e("Delay");
        this.reset = var1.e("Reset"); // CanaryMod - retrieve delay from tag
        if (this.reset == 0) {
            reset = (short) 600;
        }
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("EntityId", this.d);
        var1.a("Delay", (short) this.a);
        var1.a("Reset", (short) this.reset); // CanaryMod - store delay in the tag
    }

    @Override
    public OPacket d() {
        int var1 = OEntityList.a(this.d);

        return new OPacket132TileEntityData(this.l, this.m, this.n, 1, var1);
    }
}
