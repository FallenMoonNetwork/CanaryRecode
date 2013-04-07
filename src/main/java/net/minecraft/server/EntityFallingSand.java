package net.minecraft.server;


import java.util.ArrayList;
import java.util.Iterator;
import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.entity.CanaryFallingBlock;
import net.canarymod.hook.entity.DamageHook;


public class EntityFallingSand extends Entity {

    public int a;
    public int b;
    public int c;
    public boolean d;
    private boolean f;
    private boolean g;
    public int h; // CanaryMod: private => public
    public float i; // CanaryMod: private => public
    public NBTTagCompound e;

    public EntityFallingSand(World world) {
        super(world);
        this.c = 0;
        this.d = true;
        this.f = false;
        this.g = false;
        this.h = 40;
        this.i = 2.0F;
        this.e = null;
        this.entity = new CanaryFallingBlock(this); // CanaryMod: Wrap Entity
    }

    public EntityFallingSand(World world, double d0, double d1, double d2, int i0) {
        this(world, d0, d1, d2, i0, 0);
    }

    public EntityFallingSand(World world, double d0, double d1, double d2, int i0, int i1) {
        super(world);
        this.c = 0;
        this.d = true;
        this.f = false;
        this.g = false;
        this.h = 40;
        this.i = 2.0F;
        this.e = null;
        this.a = i0;
        this.b = i1;
        this.m = true;
        this.a(0.98F, 0.98F);
        this.N = this.P / 2.0F;
        this.b(d0, d1, d2);
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.r = d0;
        this.s = d1;
        this.t = d2;
        this.entity = new CanaryFallingBlock(this); // CanaryMod: Wrap Entity
    }

    protected boolean f_() {
        return false;
    }

    protected void a() {}

    public boolean K() {
        return !this.M;
    }

    public void l_() {
        if (this.a == 0) {
            this.w();
        } else {
            this.r = this.u;
            this.s = this.v;
            this.t = this.w;
            ++this.c;
            this.y -= 0.03999999910593033D;
            this.d(this.x, this.y, this.z);
            this.x *= 0.9800000190734863D;
            this.y *= 0.9800000190734863D;
            this.z *= 0.9800000190734863D;
            if (!this.q.I) {
                int i0 = MathHelper.c(this.u);
                int i1 = MathHelper.c(this.v);
                int i2 = MathHelper.c(this.w);

                if (this.c == 1) {
                    if (this.q.a(i0, i1, i2) != this.a) {
                        this.w();
                        return;
                    }

                    this.q.i(i0, i1, i2);
                }

                if (this.F) {
                    this.x *= 0.699999988079071D;
                    this.z *= 0.699999988079071D;
                    this.y *= -0.5D;
                    if (this.q.a(i0, i1, i2) != Block.ag.cz) {
                        this.w();
                        if (!this.f && this.q.a(this.a, i0, i1, i2, true, 1, (Entity) null, (ItemStack) null) && !BlockSand.a_(this.q, i0, i1 - 1, i2) && this.q.f(i0, i1, i2, this.a, this.b, 3)) {
                            if (Block.r[this.a] instanceof BlockSand) {
                                ((BlockSand) Block.r[this.a]).a_(this.q, i0, i1, i2, this.b);
                            }

                            if (this.e != null && Block.r[this.a] instanceof ITileEntityProvider) {
                                TileEntity tileentity = this.q.r(i0, i1, i2);

                                if (tileentity != null) {
                                    NBTTagCompound nbttagcompound = new NBTTagCompound();

                                    tileentity.b(nbttagcompound);
                                    Iterator iterator = this.e.c().iterator();

                                    while (iterator.hasNext()) {
                                        NBTBase nbtbase = (NBTBase) iterator.next();

                                        if (!nbtbase.e().equals("x") && !nbtbase.e().equals("y") && !nbtbase.e().equals("z")) {
                                            nbttagcompound.a(nbtbase.e(), nbtbase.b());
                                        }
                                    }

                                    tileentity.a(nbttagcompound);
                                    tileentity.k_();
                                }
                            }
                        } else if (this.d && !this.f) {
                            this.a(new ItemStack(this.a, 1, Block.r[this.a].a(this.b)), 0.0F);
                        }
                    }
                } else if (this.c > 100 && !this.q.I && (i1 < 1 || i1 > 256) || this.c > 600) {
                    if (this.d) {
                        this.a(new ItemStack(this.a, 1, Block.r[this.a].a(this.b)), 0.0F);
                    }

                    this.w();
                }
            }
        }
    }

    protected void a(float f0) {
        if (this.g) {
            int i0 = MathHelper.f(f0 - 1.0F);

            if (i0 > 0) {
                ArrayList arraylist = new ArrayList(this.q.b((Entity) this, this.E));
                DamageSource damagesource = this.a == Block.cl.cz ? DamageSource.m : DamageSource.n;
                Iterator iterator = arraylist.iterator();

                while (iterator.hasNext()) {
                    Entity entity = (Entity) iterator.next();
                    // CanaryMod: DamageHook (FallingBlock/Anvil)
                    DamageHook hook = new DamageHook(null, entity.getCanaryEntity(), new CanaryDamageSource(damagesource), Math.min(MathHelper.d((float) i0 * this.i), this.h));

                    Canary.hooks().callHook(hook);
                    if (!hook.isCanceled()) {
                        entity.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), Math.min(hook.getDamageDealt(), this.h));
                    }
                    //
                }

                if (this.a == Block.cl.cz && (double) this.ab.nextFloat() < 0.05000000074505806D + (double) i0 * 0.05D) {
                    int i1 = this.b >> 2;
                    int i2 = this.b & 3;

                    ++i1;
                    if (i1 > 2) {
                        this.f = true;
                    } else {
                        this.b = i2 | i1 << 2;
                    }
                }
            }
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Tile", (byte) this.a);
        nbttagcompound.a("TileID", this.a);
        nbttagcompound.a("Data", (byte) this.b);
        nbttagcompound.a("Time", (byte) this.c);
        nbttagcompound.a("DropItem", this.d);
        nbttagcompound.a("HurtEntities", this.g);
        nbttagcompound.a("FallHurtAmount", this.i);
        nbttagcompound.a("FallHurtMax", this.h);
        if (this.e != null) {
            nbttagcompound.a("TileEntityData", this.e);
        }
    }

    protected void a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.b("TileID")) {
            this.a = nbttagcompound.e("TileID");
        } else {
            this.a = nbttagcompound.c("Tile") & 255;
        }

        this.b = nbttagcompound.c("Data") & 255;
        this.c = nbttagcompound.c("Time") & 255;
        if (nbttagcompound.b("HurtEntities")) {
            this.g = nbttagcompound.n("HurtEntities");
            this.i = nbttagcompound.g("FallHurtAmount");
            this.h = nbttagcompound.e("FallHurtMax");
        } else if (this.a == Block.cl.cz) {
            this.g = true;
        }

        if (nbttagcompound.b("DropItem")) {
            this.d = nbttagcompound.n("DropItem");
        }

        if (nbttagcompound.b("TileEntityData")) {
            this.e = nbttagcompound.l("TileEntityData");
        }

        if (this.a == 0) {
            this.a = Block.I.cz;
        }
    }

    public void a(boolean flag0) {
        this.g = flag0;
    }

    public void a(CrashReportCategory crashreportcategory) {
        super.a(crashreportcategory);
        crashreportcategory.a("Immitating block ID", Integer.valueOf(this.a));
        crashreportcategory.a("Immitating block data", Integer.valueOf(this.b));
    }
}
