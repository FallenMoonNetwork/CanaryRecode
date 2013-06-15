package net.minecraft.server;


import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.canarymod.config.Configuration;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.hook.entity.DimensionSwitchHook;
import net.canarymod.hook.entity.EntityMountHook;


public abstract class Entity {

    private static int b = 0;
    public int k;
    public double l;
    public boolean m;
    public Entity n;
    public Entity o;
    public boolean p;
    public World q;
    public double r; // Previous Position X
    public double s; // Previous Position Y
    public double t; // Previous Position Z
    public double u; // Position X
    public double v; // Position Y
    public double w; // Position Z
    public double x; // Motion X
    public double y; // Motion Y
    public double z; // Motion Z
    public float A; // Rotation
    public float B; // Pitch
    public float C;
    public float D;
    public final AxisAlignedBB E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    protected boolean K;
    public boolean L;
    public boolean M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public float R;
    public float S;
    public float T;
    private int c;
    public double U;
    public double V;
    public double W;
    public float X;
    public float Y;
    public boolean Z;
    public float aa;
    protected Random ab;
    public int ac;
    public int ad;
    public int d; // CanaryMod: private -> public (FireTicks)
    protected boolean ae;
    public int af;
    private boolean e;
    protected boolean ag;
    protected DataWatcher ah;
    private double f;
    private double g;
    public boolean ai;
    public int aj;
    public int ak;
    public int al;
    public boolean am;
    public boolean an;
    public int ao;
    protected boolean ap;
    protected int aq;
    public int ar;
    protected int as;
    private boolean h;
    private UUID i;
    public EnumEntitySize at;

    // CanaryMod
    protected CanaryEntity entity;
    protected CompoundTag metadata = null;
    //

    public Entity(World world) {
        this.k = b++;
        this.l = 1.0D;
        this.m = false;
        this.E = AxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.F = false;
        this.I = false;
        this.J = false;
        this.L = true;
        this.M = false;
        this.N = 0.0F;
        this.O = 0.6F;
        this.P = 1.8F;
        this.Q = 0.0F;
        this.R = 0.0F;
        this.S = 0.0F;
        this.T = 0.0F;
        this.c = 1;
        this.X = 0.0F;
        this.Y = 0.0F;
        this.Z = false;
        this.aa = 0.0F;
        this.ab = new Random();
        this.ac = 0;
        this.ad = 1;
        this.d = 0;
        this.ae = false;
        this.af = 0;
        this.e = true;
        this.ag = false;
        this.ah = new DataWatcher();
        this.ai = false;
        this.as = 0;
        this.h = false;
        this.i = UUID.randomUUID();
        this.at = EnumEntitySize.b;
        this.q = world;
        this.b(0.0D, 0.0D, 0.0D);
        if (world != null) {
            this.ar = world.t.h;
        }

        this.ah.a(0, Byte.valueOf((byte) 0));
        this.ah.a(1, Short.valueOf((short) 300));
        this.a();
        entity = new CanaryEntity(this) {

            @Override
            public Entity getHandle() {
                return entity;
            }
        };
    }

    protected abstract void a();

    public DataWatcher u() {
        return this.ah;
    }

    public boolean equals(Object object) {
        return object instanceof Entity ? ((Entity) object).k == this.k : false;
    }

    public int hashCode() {
        return this.k;
    }

    public void w() {
        this.M = true;
    }

    protected void a(float f0, float f1) {
        if (f0 != this.O || f1 != this.P) {
            this.O = f0;
            this.P = f1;
            this.E.d = this.E.a + (double) this.O;
            this.E.f = this.E.c + (double) this.O;
            this.E.e = this.E.b + (double) this.P;
        }

        float f2 = f0 % 2.0F;

        if ((double) f2 < 0.375D) {
            this.at = EnumEntitySize.a;
        } else if ((double) f2 < 0.75D) {
            this.at = EnumEntitySize.b;
        } else if ((double) f2 < 1.0D) {
            this.at = EnumEntitySize.c;
        } else if ((double) f2 < 1.375D) {
            this.at = EnumEntitySize.d;
        } else if ((double) f2 < 1.75D) {
            this.at = EnumEntitySize.e;
        } else {
            this.at = EnumEntitySize.f;
        }
    }

    protected void b(float f0, float f1) {
        this.A = f0 % 360.0F;
        this.B = f1 % 360.0F;
    }

    public void b(double d0, double d1, double d2) {
        this.u = d0;
        this.v = d1;
        this.w = d2;
        float f0 = this.O / 2.0F;
        float f1 = this.P;

        this.E.b(d0 - (double) f0, d1 - (double) this.N + (double) this.X, d2 - (double) f0, d0 + (double) f0, d1 - (double) this.N + (double) this.X + (double) f1, d2 + (double) f0);
    }

    public void l_() {
        this.x();
    }

    public void x() {
        this.q.C.a("entityBaseTick");
        if (this.o != null && this.o.M) {
            this.o = null;
        }

        this.Q = this.R;
        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        this.D = this.B;
        this.C = this.A;
        int i0;

        if (!this.q.I && this.q instanceof WorldServer) {
            this.q.C.a("portal");

            i0 = this.y();
            if (this.ap) {
                //CanaryMod moved allow-nether to per-world config
                if (Configuration.getWorldConfig(getCanaryWorld().getFqName()).isNetherAllowed()) {
                    if (this.o == null && this.aq++ >= i0) {
                        this.aq = i0;
                        this.ao = this.aa();
                        byte b0;

                        if (this.q.t.h == -1) {
                            b0 = 0;
                        } else {
                            b0 = -1;
                        }

                        this.c(b0);
                    }

                    this.ap = false;
                }
            } else {
                if (this.aq > 0) {
                    this.aq -= 4;
                }

                if (this.aq < 0) {
                    this.aq = 0;
                }
            }

            if (this.ao > 0) {
                --this.ao;
            }

            this.q.C.b();
        }

        if (this.ah() && !this.G()) {
            int i1 = MathHelper.c(this.u);

            i0 = MathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
            int i2 = MathHelper.c(this.w);
            int i3 = this.q.a(i1, i0, i2);

            if (i3 > 0) {
                this.q.a("tilecrack_" + i3 + "_" + this.q.h(i1, i0, i2), this.u + ((double) this.ab.nextFloat() - 0.5D) * (double) this.O, this.E.b + 0.1D, this.w + ((double) this.ab.nextFloat() - 0.5D) * (double) this.O, -this.x * 4.0D, 1.5D, -this.z * 4.0D);
            }
        }

        this.H();
        if (this.q.I) {
            this.d = 0;
        } else if (this.d > 0) {
            if (this.ag) {
                this.d -= 4;
                if (this.d < 0) {
                    this.d = 0;
                }
            } else {
                if (this.d % 20 == 0) {
                    // CanaryMod: call DamageHook (FireTick)
                    DamageHook hook = new DamageHook(null, entity, new CanaryDamageSource(DamageSource.b), 1);

                    Canary.hooks().callHook(hook);

                    if (!hook.isCanceled()) {
                        this.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
                    }
                    //
                }

                --this.d;
            }
        }

        if (this.I()) {
            this.z();
            this.T *= 0.5F;
        }

        if (this.v < -64.0D) {
            this.B();
        }

        if (!this.q.I) {
            this.a(0, this.d > 0);
            this.a(2, this.o != null);
        }

        this.e = false;
        this.q.C.b();
    }

    public int y() {
        return 0;
    }

    protected void z() {
        if (!this.ag) {
            // CanaryMod: call DamageHook (Lava)
            DamageHook hook = new DamageHook(null, entity, new CanaryDamageSource(DamageSource.c), 4);

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                this.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
                this.d(15);
            }
            //
        }
    }

    public void d(int i0) {
        int i1 = i0 * 20;

        i1 = EnchantmentProtection.a(this, i1);
        if (this.d < i1) {
            this.d = i1;
        }
    }

    public void A() {
        this.d = 0;
    }

    protected void B() {
        this.w();
    }

    public boolean c(double d0, double d1, double d2) {
        AxisAlignedBB axisalignedbb = this.E.c(d0, d1, d2);
        List list = this.q.a(this, axisalignedbb);

        return !list.isEmpty() ? false : !this.q.d(axisalignedbb);
    }

    public void d(double d0, double d1, double d2) {
        if (this.Z) {
            this.E.d(d0, d1, d2);
            this.u = (this.E.a + this.E.d) / 2.0D;
            this.v = this.E.b + (double) this.N - (double) this.X;
            this.w = (this.E.c + this.E.f) / 2.0D;
        } else {
            this.q.C.a("move");
            this.X *= 0.4F;
            double d3 = this.u;
            double d4 = this.v;
            double d5 = this.w;

            if (this.K) {
                this.K = false;
                d0 *= 0.25D;
                d1 *= 0.05000000074505806D;
                d2 *= 0.25D;
                this.x = 0.0D;
                this.y = 0.0D;
                this.z = 0.0D;
            }

            double d6 = d0;
            double d7 = d1;
            double d8 = d2;
            AxisAlignedBB axisalignedbb = this.E.c();
            boolean flag0 = this.F && this.ag() && this instanceof EntityPlayer;

            if (flag0) {
                double d9;

                for (d9 = 0.05D; d0 != 0.0D && this.q.a(this, this.E.c(d0, -1.0D, 0.0D)).isEmpty(); d6 = d0) {
                    if (d0 < d9 && d0 >= -d9) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d9;
                    } else {
                        d0 += d9;
                    }
                }

                for (; d2 != 0.0D && this.q.a(this, this.E.c(0.0D, -1.0D, d2)).isEmpty(); d8 = d2) {
                    if (d2 < d9 && d2 >= -d9) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d9;
                    } else {
                        d2 += d9;
                    }
                }

                while (d0 != 0.0D && d2 != 0.0D && this.q.a(this, this.E.c(d0, -1.0D, d2)).isEmpty()) {
                    if (d0 < d9 && d0 >= -d9) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d9;
                    } else {
                        d0 += d9;
                    }

                    if (d2 < d9 && d2 >= -d9) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d9;
                    } else {
                        d2 += d9;
                    }

                    d6 = d0;
                    d8 = d2;
                }
            }

            List list = this.q.a(this, this.E.a(d0, d1, d2));

            for (int i0 = 0; i0 < list.size(); ++i0) {
                d1 = ((AxisAlignedBB) list.get(i0)).b(this.E, d1);
            }

            this.E.d(0.0D, d1, 0.0D);
            if (!this.L && d7 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.F || d7 != d1 && d7 < 0.0D;

            int i1;

            for (i1 = 0; i1 < list.size(); ++i1) {
                d0 = ((AxisAlignedBB) list.get(i1)).a(this.E, d0);
            }

            this.E.d(d0, 0.0D, 0.0D);
            if (!this.L && d6 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (i1 = 0; i1 < list.size(); ++i1) {
                d2 = ((AxisAlignedBB) list.get(i1)).c(this.E, d2);
            }

            this.E.d(0.0D, 0.0D, d2);
            if (!this.L && d8 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d10;
            double d11;
            double d12;
            int i2;

            if (this.Y > 0.0F && flag1 && (flag0 || this.X < 0.05F) && (d6 != d0 || d8 != d2)) {
                d10 = d0;
                d11 = d1;
                d12 = d2;
                d0 = d6;
                d1 = (double) this.Y;
                d2 = d8;
                AxisAlignedBB axisalignedbb1 = this.E.c();

                this.E.c(axisalignedbb);
                list = this.q.a(this, this.E.a(d6, d1, d8));

                for (i2 = 0; i2 < list.size(); ++i2) {
                    d1 = ((AxisAlignedBB) list.get(i2)).b(this.E, d1);
                }

                this.E.d(0.0D, d1, 0.0D);
                if (!this.L && d7 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (i2 = 0; i2 < list.size(); ++i2) {
                    d0 = ((AxisAlignedBB) list.get(i2)).a(this.E, d0);
                }

                this.E.d(d0, 0.0D, 0.0D);
                if (!this.L && d6 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (i2 = 0; i2 < list.size(); ++i2) {
                    d2 = ((AxisAlignedBB) list.get(i2)).c(this.E, d2);
                }

                this.E.d(0.0D, 0.0D, d2);
                if (!this.L && d8 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (!this.L && d7 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                } else {
                    d1 = (double) (-this.Y);

                    for (i2 = 0; i2 < list.size(); ++i2) {
                        d1 = ((AxisAlignedBB) list.get(i2)).b(this.E, d1);
                    }

                    this.E.d(0.0D, d1, 0.0D);
                }

                if (d10 * d10 + d12 * d12 >= d0 * d0 + d2 * d2) {
                    d0 = d10;
                    d1 = d11;
                    d2 = d12;
                    this.E.c(axisalignedbb1);
                }
            }

            this.q.C.b();
            this.q.C.a("rest");
            this.u = (this.E.a + this.E.d) / 2.0D;
            this.v = this.E.b + (double) this.N - (double) this.X;
            this.w = (this.E.c + this.E.f) / 2.0D;
            this.G = d6 != d0 || d8 != d2;
            this.H = d7 != d1;
            this.F = d7 != d1 && d7 < 0.0D;
            this.I = this.G || this.H;
            this.a(d1, this.F);
            if (d6 != d0) {
                this.x = 0.0D;
            }

            if (d7 != d1) {
                this.y = 0.0D;
            }

            if (d8 != d2) {
                this.z = 0.0D;
            }

            d10 = this.u - d3;
            d11 = this.v - d4;
            d12 = this.w - d5;
            if (this.f_() && !flag0 && this.o == null) {
                int i3 = MathHelper.c(this.u);

                i2 = MathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
                int i4 = MathHelper.c(this.w);
                int i5 = this.q.a(i3, i2, i4);

                if (i5 == 0) {
                    int i6 = this.q.e(i3, i2 - 1, i4);

                    if (i6 == 11 || i6 == 32 || i6 == 21) {
                        i5 = this.q.a(i3, i2 - 1, i4);
                    }
                }

                if (i5 != Block.aJ.cz) {
                    d11 = 0.0D;
                }

                this.R = (float) ((double) this.R + (double) MathHelper.a(d10 * d10 + d12 * d12) * 0.6D);
                this.S = (float) ((double) this.S + (double) MathHelper.a(d10 * d10 + d11 * d11 + d12 * d12) * 0.6D);
                if (this.S > (float) this.c && i5 > 0) {
                    this.c = (int) this.S + 1;
                    if (this.G()) {
                        float f0 = MathHelper.a(this.x * this.x * 0.20000000298023224D + this.y * this.y + this.z * this.z * 0.20000000298023224D) * 0.35F;

                        if (f0 > 1.0F) {
                            f0 = 1.0F;
                        }

                        this.a("liquid.swim", f0, 1.0F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.4F);
                    }

                    this.a(i3, i2, i4, i5);
                    Block.r[i5].b(this.q, i3, i2, i4, this);
                }
            }

            this.C();
            boolean flag2 = this.F();

            if (this.q.e(this.E.e(0.001D, 0.001D, 0.001D))) {
                this.e(1);
                if (!flag2) {
                    ++this.d;
                    if (this.d == 0) {
                        this.d(8);
                    }
                }
            } else if (this.d <= 0) {
                this.d = -this.ad;
            }

            if (flag2 && this.d > 0) {
                this.a("random.fizz", 0.7F, 1.6F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.4F);
                this.d = -this.ad;
            }

            this.q.C.b();
        }
    }

    protected void C() {
        int i0 = MathHelper.c(this.E.a + 0.001D);
        int i1 = MathHelper.c(this.E.b + 0.001D);
        int i2 = MathHelper.c(this.E.c + 0.001D);
        int i3 = MathHelper.c(this.E.d - 0.001D);
        int i4 = MathHelper.c(this.E.e - 0.001D);
        int i5 = MathHelper.c(this.E.f - 0.001D);

        if (this.q.e(i0, i1, i2, i3, i4, i5)) {
            for (int i6 = i0; i6 <= i3; ++i6) {
                for (int i7 = i1; i7 <= i4; ++i7) {
                    for (int i8 = i2; i8 <= i5; ++i8) {
                        int i9 = this.q.a(i6, i7, i8);

                        if (i9 > 0) {
                            Block.r[i9].a(this.q, i6, i7, i8, this);
                        }
                    }
                }
            }
        }
    }

    protected void a(int i0, int i1, int i2, int i3) {
        StepSound stepsound = Block.r[i3].cM;

        if (this.q.a(i0, i1 + 1, i2) == Block.aW.cz) {
            stepsound = Block.aW.cM;
            this.a(stepsound.e(), stepsound.c() * 0.15F, stepsound.d());
        } else if (!Block.r[i3].cO.d()) {
            this.a(stepsound.e(), stepsound.c() * 0.15F, stepsound.d());
        }
    }

    public void a(String s0, float f0, float f1) {
        this.q.a(this, s0, f0, f1);
    }

    protected boolean f_() {
        return true;
    }

    protected void a(double d0, boolean flag0) {
        if (flag0) {
            if (this.T > 0.0F) {
                this.a(this.T);
                this.T = 0.0F;
            }
        } else if (d0 < 0.0D) {
            this.T = (float) ((double) this.T - d0);
        }
    }

    public AxisAlignedBB D() {
        return null;
    }

    protected void e(int i0) {
        if (!this.ag) {
            // CanaryMod: call DamageHook (onfire)
            DamageHook hook = new DamageHook(null, entity, new CanaryDamageSource(DamageSource.a), i0);

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                this.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
            }
            //
        }
    }

    public final boolean E() {
        return this.ag;
    }

    protected void a(float f0) {
        if (this.n != null) {
            this.n.a(f0);
        }
    }

    public boolean F() {
        return this.ae || this.q.F(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) || this.q.F(MathHelper.c(this.u), MathHelper.c(this.v + (double) this.P), MathHelper.c(this.w));
    }

    public boolean G() {
        return this.ae;
    }

    public boolean H() {
        if (this.q.a(this.E.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), Material.h, this)) {
            if (!this.ae && !this.e) {
                float f0 = MathHelper.a(this.x * this.x * 0.20000000298023224D + this.y * this.y + this.z * this.z * 0.20000000298023224D) * 0.2F;

                if (f0 > 1.0F) {
                    f0 = 1.0F;
                }

                this.a("liquid.splash", f0, 1.0F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.4F);
                float f1 = (float) MathHelper.c(this.E.b);

                int i0;
                float f2;
                float f3;

                for (i0 = 0; (float) i0 < 1.0F + this.O * 20.0F; ++i0) {
                    f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    f3 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    this.q.a("bubble", this.u + (double) f2, (double) (f1 + 1.0F), this.w + (double) f3, this.x, this.y - (double) (this.ab.nextFloat() * 0.2F), this.z);
                }

                for (i0 = 0; (float) i0 < 1.0F + this.O * 20.0F; ++i0) {
                    f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    f3 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    this.q.a("splash", this.u + (double) f2, (double) (f1 + 1.0F), this.w + (double) f3, this.x, this.y, this.z);
                }
            }

            this.T = 0.0F;
            this.ae = true;
            this.d = 0;
        } else {
            this.ae = false;
        }

        return this.ae;
    }

    public boolean a(Material material) {
        double d0 = this.v + (double) this.e();
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.d((float) MathHelper.c(d0));
        int i2 = MathHelper.c(this.w);
        int i3 = this.q.a(i0, i1, i2);

        if (i3 != 0 && Block.r[i3].cO == material) {
            float f0 = BlockFluid.d(this.q.h(i0, i1, i2)) - 0.11111111F;
            float f1 = (float) (i1 + 1) - f0;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    public float e() {
        return 0.0F;
    }

    public boolean I() {
        return this.q.a(this.E.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.i);
    }

    public void a(float f0, float f1, float f2) {
        float f3 = f0 * f0 + f1 * f1;

        if (f3 >= 1.0E-4F) {
            f3 = MathHelper.c(f3);
            if (f3 < 1.0F) {
                f3 = 1.0F;
            }

            f3 = f2 / f3;
            f0 *= f3;
            f1 *= f3;
            float f4 = MathHelper.a(this.A * 3.1415927F / 180.0F);
            float f5 = MathHelper.b(this.A * 3.1415927F / 180.0F);

            this.x += (double) (f0 * f5 - f1 * f4);
            this.z += (double) (f1 * f5 + f0 * f4);
        }
    }

    public float c(float f0) {
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.w);

        if (this.q.f(i0, 0, i1)) {
            double d0 = (this.E.e - this.E.b) * 0.66D;
            int i2 = MathHelper.c(this.v - (double) this.N + d0);

            return this.q.q(i0, i2, i1);
        } else {
            return 0.0F;
        }
    }

    public void a(World world) {
        this.q = world;
    }

    public void a(double d0, double d1, double d2, float f0, float f1) {
        this.r = this.u = d0;
        this.s = this.v = d1;
        this.t = this.w = d2;
        this.C = this.A = f0;
        this.D = this.B = f1;
        this.X = 0.0F;
        double d3 = (double) (this.C - f0);

        if (d3 < -180.0D) {
            this.C += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.C -= 360.0F;
        }

        this.b(this.u, this.v, this.w);
        this.b(f0, f1);
    }

    public void b(double d0, double d1, double d2, float f0, float f1) {
        this.U = this.r = this.u = d0;
        this.V = this.s = this.v = d1 + (double) this.N;
        this.W = this.t = this.w = d2;
        this.A = f0;
        this.B = f1;
        this.b(this.u, this.v, this.w);
    }

    public float d(Entity entity) {
        float f0 = (float) (this.u - entity.u);
        float f1 = (float) (this.v - entity.v);
        float f2 = (float) (this.w - entity.w);

        return MathHelper.c(f0 * f0 + f1 * f1 + f2 * f2);
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.u - d0;
        double d4 = this.v - d1;
        double d5 = this.w - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double f(double d0, double d1, double d2) {
        double d3 = this.u - d0;
        double d4 = this.v - d1;
        double d5 = this.w - d2;

        return (double) MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double e(Entity entity) {
        double d0 = this.u - entity.u;
        double d1 = this.v - entity.v;
        double d2 = this.w - entity.w;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void b_(EntityPlayer entityplayer) {}

    public void f(Entity entity) {
        if (entity.n != this && entity.o != this) {
            double d0 = entity.u - this.u;
            double d1 = entity.w - this.w;
            double d2 = MathHelper.a(d0, d1);

            if (d2 >= 0.009999999776482582D) {
                d2 = (double) MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.05000000074505806D;
                d1 *= 0.05000000074505806D;
                d0 *= (double) (1.0F - this.aa);
                d1 *= (double) (1.0F - this.aa);
                this.g(-d0, 0.0D, -d1);
                entity.g(d0, 0.0D, d1);
            }
        }
    }

    public void g(double d0, double d1, double d2) {
        this.x += d0;
        this.y += d1;
        this.z += d2;
        this.an = true;
    }

    protected void J() {
        this.J = true;
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            this.J();
            return false;
        }
    }

    public boolean K() {
        return false;
    }

    public boolean L() {
        return false;
    }

    public void c(Entity entity, int i0) {}

    public boolean c(NBTTagCompound nbttagcompound) {
        String s0 = this.P();

        if (!this.M && s0 != null) {
            nbttagcompound.a("id", s0);
            this.e(nbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public boolean d(NBTTagCompound nbttagcompound) {
        String s0 = this.P();

        if (!this.M && s0 != null && this.n == null) {
            nbttagcompound.a("id", s0);
            this.e(nbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void e(NBTTagCompound nbttagcompound) {
        try {
            nbttagcompound.a("Pos", (NBTBase) this.a(new double[] { this.u, this.v + (double) this.X, this.w}));
            nbttagcompound.a("Motion", (NBTBase) this.a(new double[] { this.x, this.y, this.z}));
            nbttagcompound.a("Rotation", (NBTBase) this.a(new float[] { this.A, this.B}));
            nbttagcompound.a("FallDistance", this.T);
            nbttagcompound.a("Fire", (short) this.d);
            nbttagcompound.a("Air", (short) this.ak());
            nbttagcompound.a("OnGround", this.F);
            nbttagcompound.a("Dimension", this.ar);
            nbttagcompound.a("Invulnerable", this.h);
            nbttagcompound.a("PortalCooldown", this.ao);
            nbttagcompound.a("UUIDMost", this.i.getMostSignificantBits());
            nbttagcompound.a("UUIDLeast", this.i.getLeastSignificantBits());
            // CanaryMod add level name
            nbttagcompound.a("LevelName", getCanaryWorld().getName());
            // CanaryMod: allow the saving of persistent metadata
            if (metadata != null) {
                nbttagcompound.a("Canary", ((CanaryCompoundTag)metadata).getHandle());
            }
            // CanaryMod end
            this.b(nbttagcompound);
            if (this.o != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound("Riding");

                if (this.o.c(nbttagcompound1)) {
                    nbttagcompound.a("Riding", (NBTBase) nbttagcompound1);
                }
            }
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Saving entity NBT");
            CrashReportCategory crashreportcategory = crashreport.a("Entity being saved");

            this.a(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    public void f(NBTTagCompound nbttagcompound) {
        try {
            NBTTagList nbttaglist = nbttagcompound.m("Pos");
            NBTTagList nbttaglist1 = nbttagcompound.m("Motion");
            NBTTagList nbttaglist2 = nbttagcompound.m("Rotation");

            this.x = ((NBTTagDouble) nbttaglist1.b(0)).a;
            this.y = ((NBTTagDouble) nbttaglist1.b(1)).a;
            this.z = ((NBTTagDouble) nbttaglist1.b(2)).a;
            if (Math.abs(this.x) > 10.0D) {
                this.x = 0.0D;
            }

            if (Math.abs(this.y) > 10.0D) {
                this.y = 0.0D;
            }

            if (Math.abs(this.z) > 10.0D) {
                this.z = 0.0D;
            }

            this.r = this.U = this.u = ((NBTTagDouble) nbttaglist.b(0)).a;
            this.s = this.V = this.v = ((NBTTagDouble) nbttaglist.b(1)).a;
            this.t = this.W = this.w = ((NBTTagDouble) nbttaglist.b(2)).a;
            this.C = this.A = ((NBTTagFloat) nbttaglist2.b(0)).a;
            this.D = this.B = ((NBTTagFloat) nbttaglist2.b(1)).a;
            this.T = nbttagcompound.g("FallDistance");
            this.d = nbttagcompound.d("Fire");
            this.g(nbttagcompound.d("Air"));
            this.F = nbttagcompound.n("OnGround");
            this.ar = nbttagcompound.e("Dimension");
            this.h = nbttagcompound.n("Invulnerable");
            this.ao = nbttagcompound.e("PortalCooldown");
            if (nbttagcompound.b("UUIDMost") && nbttagcompound.b("UUIDLeast")) {
                this.i = new UUID(nbttagcompound.f("UUIDMost"), nbttagcompound.f("UUIDLeast"));
            }

            this.b(this.u, this.v, this.w);
            this.b(this.A, this.B);
            // CanaryMod: allow the saving of persistent metadata
            this.metadata = nbttagcompound.b("Canary") ? new CanaryCompoundTag(nbttagcompound.l("Canary")) : new CanaryCompoundTag("Canary"); 
            // CanaryMod: END
            this.a(nbttagcompound);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Loading entity NBT");
            CrashReportCategory crashreportcategory = crashreport.a("Entity being loaded");

            this.a(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    protected final String P() {
        return EntityList.b(this);
    }

    protected abstract void a(NBTTagCompound nbttagcompound);

    protected abstract void b(NBTTagCompound nbttagcompound);

    protected NBTTagList a(double... adouble) {
        NBTTagList nbttaglist = new NBTTagList();
        double[] adouble1 = adouble;
        int i0 = adouble.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            double d0 = adouble1[i1];

            nbttaglist.a((NBTBase) (new NBTTagDouble((String) null, d0)));
        }

        return nbttaglist;
    }

    protected NBTTagList a(float... afloat) {
        NBTTagList nbttaglist = new NBTTagList();
        float[] afloat1 = afloat;
        int i0 = afloat.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            float f0 = afloat1[i1];

            nbttaglist.a((NBTBase) (new NBTTagFloat((String) null, f0)));
        }

        return nbttaglist;
    }

    public EntityItem b(int i0, int i1) {
        return this.a(i0, i1, 0.0F);
    }

    public EntityItem a(int i0, int i1, float f0) {
        return this.a(new ItemStack(i0, i1, 0), f0);
    }

    public EntityItem a(ItemStack itemstack, float f0) {
        EntityItem entityitem = new EntityItem(this.q, this.u, this.v + (double) f0, this.w, itemstack);

        entityitem.b = 10;
        this.q.d((Entity) entityitem);
        return entityitem;
    }

    public boolean R() {
        return !this.M;
    }

    public boolean S() {
        for (int i0 = 0; i0 < 8; ++i0) {
            float f0 = ((float) ((i0 >> 0) % 2) - 0.5F) * this.O * 0.8F;
            float f1 = ((float) ((i0 >> 1) % 2) - 0.5F) * 0.1F;
            float f2 = ((float) ((i0 >> 2) % 2) - 0.5F) * this.O * 0.8F;
            int i1 = MathHelper.c(this.u + (double) f0);
            int i2 = MathHelper.c(this.v + (double) this.e() + (double) f1);
            int i3 = MathHelper.c(this.w + (double) f2);

            if (this.q.u(i1, i2, i3)) {
                return true;
            }
        }

        return false;
    }

    public boolean a_(EntityPlayer entityplayer) {
        return false;
    }

    public AxisAlignedBB g(Entity entity) {
        return null;
    }

    public void T() {
        if (this.o.M) {
            this.o = null;
        } else {
            this.x = 0.0D;
            this.y = 0.0D;
            this.z = 0.0D;
            this.l_();
            if (this.o != null) {
                this.o.U();
                this.g += (double) (this.o.A - this.o.C);

                for (this.f += (double) (this.o.B - this.o.D); this.g >= 180.0D; this.g -= 360.0D) {
                    ;
                }

                while (this.g < -180.0D) {
                    this.g += 360.0D;
                }

                while (this.f >= 180.0D) {
                    this.f -= 360.0D;
                }

                while (this.f < -180.0D) {
                    this.f += 360.0D;
                }

                double d0 = this.g * 0.5D;
                double d1 = this.f * 0.5D;
                float f0 = 10.0F;

                if (d0 > (double) f0) {
                    d0 = (double) f0;
                }

                if (d0 < (double) (-f0)) {
                    d0 = (double) (-f0);
                }

                if (d1 > (double) f0) {
                    d1 = (double) f0;
                }

                if (d1 < (double) (-f0)) {
                    d1 = (double) (-f0);
                }

                this.g -= d0;
                this.f -= d1;
                this.A = (float) ((double) this.A + d0);
                this.B = (float) ((double) this.B + d1);
            }
        }
    }

    public void U() {
        if (this.n != null) {
            if (!(this.n instanceof EntityPlayer) || !((EntityPlayer) this.n).cg()) {
                this.n.U = this.U;
                this.n.V = this.V + this.W() + this.n.V();
                this.n.W = this.W;
            }

            this.n.b(this.u, this.v + this.W() + this.n.V(), this.w);
        }
    }

    public double V() {
        return (double) this.N;
    }

    public double W() {
        return (double) this.P * 0.75D;
    }

    public void a(Entity entity) {
        this.f = 0.0D;
        this.g = 0.0D;
        if (entity == null) {
            if (this.o != null) {
                this.b(this.o.u, this.o.E.b + (double) this.o.P, this.o.w, this.A, this.B);
                this.o.n = null;
            }

            this.o = null;
        } else {
            if (this.o != null) {
                this.o.n = null;
            }
            // CanaryMod: EntityMount
            EntityMountHook hook = null;

            if (this instanceof EntityLiving && entity instanceof EntityLiving) {
                hook = new EntityMountHook((net.canarymod.api.entity.living.EntityLiving) entity.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving) this.getCanaryEntity());
            }
            if (hook == null || !hook.isCanceled()) {
                this.o = entity;
                entity.n = this;
            }
            //
        }
    }

    public void h(Entity entity) {
        double d0 = this.u;
        double d1 = this.v;
        double d2 = this.w;

        if (entity != null) {
            d0 = entity.u;
            d1 = entity.E.b + (double) entity.P;
            d2 = entity.w;
        }

        for (double d3 = -1.5D; d3 < 2.0D; ++d3) {
            for (double d4 = -1.5D; d4 < 2.0D; ++d4) {
                if (d3 != 0.0D || d4 != 0.0D) {
                    int i0 = (int) (this.u + d3);
                    int i1 = (int) (this.w + d4);
                    AxisAlignedBB axisalignedbb = this.E.c(d3, 1.0D, d4);

                    if (this.q.a(axisalignedbb).isEmpty()) {
                        if (this.q.w(i0, (int) this.v, i1)) {
                            this.b(this.u + d3, this.v + 1.0D, this.w + d4, this.A, this.B);
                            return;
                        }

                        if (this.q.w(i0, (int) this.v - 1, i1) || this.q.g(i0, (int) this.v - 1, i1) == Material.h) {
                            d0 = this.u + d3;
                            d1 = this.v + 1.0D;
                            d2 = this.w + d4;
                        }
                    }
                }
            }
        }

        this.b(d0, d1, d2, this.A, this.B);
    }

    public float X() {
        return 0.1F;
    }

    public Vec3 Y() {
        return null;
    }

    public void Z() {
        if (this.ao > 0) {
            this.ao = this.aa();
        } else {
            double d0 = this.r - this.u;
            double d1 = this.t - this.w;

            if (!this.q.I && !this.ap) {
                this.as = Direction.a(d0, d1);
            }

            this.ap = true;
        }
    }

    public int aa() {
        return 900;
    }

    public ItemStack[] ad() {
        return null;
    }

    public void c(int i0, ItemStack itemstack) {}

    public boolean ae() {
        return this.d > 0 || this.f(0);
    }

    public boolean af() {
        return this.o != null || this.f(2);
    }

    public boolean ag() {
        return this.f(1);
    }

    public void b(boolean flag0) {
        this.a(1, flag0);
    }

    public boolean ah() {
        return this.f(3);
    }

    public void c(boolean flag0) {
        this.a(3, flag0);
    }

    public boolean ai() {
        return this.f(5);
    }

    public void d(boolean flag0) {
        this.a(5, flag0);
    }

    public void e(boolean flag0) {
        this.a(4, flag0);
    }

    protected boolean f(int i0) {
        return (this.ah.a(0) & 1 << i0) != 0;
    }

    protected void a(int i0, boolean flag0) {
        byte b0 = this.ah.a(0);

        if (flag0) {
            this.ah.b(0, Byte.valueOf((byte) (b0 | 1 << i0)));
        } else {
            this.ah.b(0, Byte.valueOf((byte) (b0 & ~(1 << i0))));
        }
    }

    public int ak() {
        return this.ah.b(1);
    }

    public void g(int i0) {
        this.ah.b(1, Short.valueOf((short) i0));
    }

    public void a(EntityLightningBolt entitylightningbolt) {
        this.e(5);
        ++this.d;
        if (this.d == 0) {
            this.d(8);
        }
    }

    public void a(EntityLiving entityliving) {}

    protected boolean i(double d0, double d1, double d2) {
        int i0 = MathHelper.c(d0);
        int i1 = MathHelper.c(d1);
        int i2 = MathHelper.c(d2);
        double d3 = d0 - (double) i0;
        double d4 = d1 - (double) i1;
        double d5 = d2 - (double) i2;
        List list = this.q.a(this.E);

        if (list.isEmpty() && !this.q.v(i0, i1, i2)) {
            return false;
        } else {
            boolean flag0 = !this.q.v(i0 - 1, i1, i2);
            boolean flag1 = !this.q.v(i0 + 1, i1, i2);
            boolean flag2 = !this.q.v(i0, i1 - 1, i2);
            boolean flag3 = !this.q.v(i0, i1 + 1, i2);
            boolean flag4 = !this.q.v(i0, i1, i2 - 1);
            boolean flag5 = !this.q.v(i0, i1, i2 + 1);
            byte b0 = 3;
            double d6 = 9999.0D;

            if (flag0 && d3 < d6) {
                d6 = d3;
                b0 = 0;
            }

            if (flag1 && 1.0D - d3 < d6) {
                d6 = 1.0D - d3;
                b0 = 1;
            }

            if (flag3 && 1.0D - d4 < d6) {
                d6 = 1.0D - d4;
                b0 = 3;
            }

            if (flag4 && d5 < d6) {
                d6 = d5;
                b0 = 4;
            }

            if (flag5 && 1.0D - d5 < d6) {
                d6 = 1.0D - d5;
                b0 = 5;
            }

            float f0 = this.ab.nextFloat() * 0.2F + 0.1F;

            if (b0 == 0) {
                this.x = (double) (-f0);
            }

            if (b0 == 1) {
                this.x = (double) f0;
            }

            if (b0 == 2) {
                this.y = (double) (-f0);
            }

            if (b0 == 3) {
                this.y = (double) f0;
            }

            if (b0 == 4) {
                this.z = (double) (-f0);
            }

            if (b0 == 5) {
                this.z = (double) f0;
            }

            return true;
        }
    }

    public void al() {
        this.K = true;
        this.T = 0.0F;
    }

    public String am() {
        String s0 = EntityList.b(this);

        if (s0 == null) {
            s0 = "generic";
        }

        return StatCollector.a("entity." + s0 + ".name");
    }

    public Entity[] an() {
        return null;
    }

    public boolean i(Entity entity) {
        return this == entity;
    }

    public float ao() {
        return 0.0F;
    }

    public boolean ap() {
        return true;
    }

    public boolean j(Entity entity) {
        return false;
    }

    public String toString() {
        return String.format("%s[\'%s\'/%d, l=\'%s\', x=%.2f, y=%.2f, z=%.2f]", new Object[] { this.getClass().getSimpleName(), this.am(), Integer.valueOf(this.k), this.q == null ? "~NULL~" : this.q.M().k(), Double.valueOf(this.u), Double.valueOf(this.v), Double.valueOf(this.w)});
    }

    public boolean aq() {
        return this.h;
    }

    public void k(Entity entity) {
        this.b(entity.u, entity.v, entity.w, entity.A, entity.B);
    }

    public void a(Entity entity, boolean flag0) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        entity.e(nbttagcompound);
        this.f(nbttagcompound);
        this.ao = entity.ao;
        this.as = entity.as;
    }

    public void c(int i0) {
        if (!this.q.I && !this.M) {
            this.q.C.a("changeDimension");
            MinecraftServer minecraftserver = MinecraftServer.D();
            int i1 = this.ar;
            WorldServer worldserver = minecraftserver.getWorld(getCanaryWorld().getName(), i0);
            WorldServer worldserver1 = minecraftserver.getWorld(getCanaryWorld().getName(), i1);

            // CanaryMod: Dimension switch hook.
            Location goingTo = this.simulatePortalUse(i0, MinecraftServer.D().getWorld(this.getCanaryWorld().getName(), i0));
            CancelableHook hook = new DimensionSwitchHook(this.getCanaryEntity(), this.getCanaryEntity().getLocation(), goingTo);

            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return;
            }//

            this.ar = i0;
            this.q.e(this);
            this.M = false;
            this.q.C.a("reposition");
            minecraftserver.ad().a(this, i1, worldserver, worldserver1);
            this.q.C.c("reloading");
            Entity entity = EntityList.a(EntityList.b(this), worldserver1);

            if (entity != null) {
                entity.a(this, true);
                worldserver1.d(entity);
            }

            this.M = true;
            this.q.C.b();
            worldserver.i();
            worldserver1.i();
            this.q.C.b();
        }
    }

    public float a(Explosion explosion, World world, int i0, int i1, int i2, Block block) {
        return block.a(this);
    }

    public boolean a(Explosion explosion, World world, int i0, int i1, int i2, int i3, float f0) {
        return true;
    }

    public int ar() {
        return 3;
    }

    public int as() {
        return this.as;
    }

    public boolean at() {
        return false;
    }

    public void a(CrashReportCategory crashreportcategory) {
        crashreportcategory.a("Entity Type", (Callable) (new CallableEntityType(this)));
        crashreportcategory.a("Entity ID", Integer.valueOf(this.k));
        crashreportcategory.a("Entity Name", (Callable) (new CallableEntityName(this)));
        crashreportcategory.a("Entity\'s Exact location", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.u), Double.valueOf(this.v), Double.valueOf(this.w)}));
        crashreportcategory.a("Entity\'s Block location", CrashReportCategory.a(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)));
        crashreportcategory.a("Entity\'s Momentum", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z)}));
    }

    public boolean aw() {
        return true;
    }

    public String ax() {
        return this.am();
    }

    /**
     * CanaryMod Get this entities current World (dimension)
     *
     * @return
     */
    public CanaryWorld getCanaryWorld() {
        return q.getCanaryWorld();
    }

    public void setDimension(CanaryWorld dim) {
        this.q = dim.getHandle();
    }

    public CanaryEntity getCanaryEntity() {
        return entity;
    }

    public UUID getEntityUUID() {
        return this.i;
    }

    /**
     * Applies this Entities Properties to an NBT Tag. Initially implemented for
     * getting properties for MobSpawnerEntry.
     * @param tag tag to apply this entities properties to.
     */
    public void getNBTProperties(NBTTagCompound tag) {
        this.b(tag);
    }

    public void setNBTProperties(NBTTagCompound tag) {
        this.a(tag);
    }

    public CompoundTag getMetaData() {
        this.metadata = this.metadata == null ? new CanaryCompoundTag("Canary") : metadata;
        return this.metadata;
    }

    // CanaryMod: Simulates the use of a Portal by the Player to determine the location going to
    protected final Location simulatePortalUse(int dimensionTo, WorldServer oworldserverTo) {
        double y = this.u;
        float rotX = this.A;
        float rotY = this.B;
        double x = this.t;
        double z = this.v;
        double adjust = 8.0D;

        if (dimensionTo == -1) {
            x /= adjust;
            z /= adjust;
        } else if (dimensionTo == 0) {
            x *= adjust;
            z *= adjust;
        } else {
            ChunkCoordinates ochunkcoordinates;

            if (dimensionTo == 1) {
                ochunkcoordinates = oworldserverTo.J();
            } else {
                ochunkcoordinates = oworldserverTo.l();
            }
            x = (double) ochunkcoordinates.a;
            y = (double) ochunkcoordinates.b;
            z = (double) ochunkcoordinates.c;
            rotX = 90.0F;
            rotY = 0.0F;
        }
        if (dimensionTo != 1) {
            x = (double) MathHelper.a((int) x, -29999872, 29999872);
            z = (double) MathHelper.a((int) z, -29999872, 29999872);
        }
        return new Location(oworldserverTo.getCanaryWorld(), x, y, z, rotX, rotY);
    }

    //
}
