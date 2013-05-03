package net.minecraft.server;


import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.vehicle.Minecart;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.api.world.position.Vector3D;
import net.canarymod.config.Configuration;
import net.canarymod.hook.entity.MinecartActivateHook;
import net.canarymod.hook.entity.VehicleCollisionHook;
import net.canarymod.hook.entity.VehicleDamageHook;
import net.canarymod.hook.entity.VehicleDestroyHook;
import net.canarymod.hook.entity.VehicleEnterHook;
import net.canarymod.hook.entity.VehicleMoveHook;


public abstract class EntityMinecart extends Entity {

    public boolean a; // CanaryMod: private -> public
    private final IUpdatePlayerListBox b;
    private String c;
    private static final int[][][] d = new int[][][] { { { 0, 0, -1}, { 0, 0, 1}}, { { -1, 0, 0}, { 1, 0, 0}}, { { -1, -1, 0}, { 1, 0, 0}}, { { -1, 0, 0}, { 1, -1, 0}}, { { 0, 0, -1}, { 0, -1, 1}}, { { 0, -1, -1}, { 0, 0, 1}}, { { 0, 0, 1}, { 1, 0, 0}}, { { 0, 0, 1}, { -1, 0, 0}}, { { 0, 0, -1}, { -1, 0, 0}}, { { 0, 0, -1}, { 1, 0, 0}}};
    private int e;
    private double f;
    private double g;
    private double h;
    private double i;
    private double j;

    public EntityMinecart(World world) {
        super(world);
        this.a = false;
        this.m = true;
        this.a(0.98F, 0.7F);
        this.N = this.P / 2.0F;
        this.b = world != null ? world.a(this) : null;
    }

    public static EntityMinecart a(World world, double d0, double d1, double d2, int i0) {
        switch (i0) {
            case 1:
                return new EntityMinecartChest(world, d0, d1, d2);

            case 2:
                return new EntityMinecartFurnace(world, d0, d1, d2);

            case 3:
                return new EntityMinecartTNT(world, d0, d1, d2);

            case 4:
                return new EntityMinecartMobSpawner(world, d0, d1, d2);

            case 5:
                return new EntityMinecartHopper(world, d0, d1, d2);

            default:
                return new EntityMinecartEmpty(world, d0, d1, d2);
        }
    }

    protected boolean f_() {
        return false;
    }

    protected void a() {
        this.ah.a(17, new Integer(0));
        this.ah.a(18, new Integer(1));
        this.ah.a(19, new Integer(0));
        this.ah.a(20, new Integer(0));
        this.ah.a(21, new Integer(6));
        this.ah.a(22, Byte.valueOf((byte) 0));
    }

    public AxisAlignedBB g(Entity entity) {
        return entity.L() ? entity.E : null;
    }

    public AxisAlignedBB D() {
        return null;
    }

    public boolean L() {
        return true;
    }

    public EntityMinecart(World world, double d0, double d1, double d2) {
        this(world);
        this.b(d0, d1 + (double) this.N, d2);
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.r = d0;
        this.s = d1;
        this.t = d2;
    }

    public double W() {
        return (double) this.P * 0.0D - 0.30000001192092896D;
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (!this.q.I && !this.M) {
            if (this.aq()) {
                return false;
            } else {
                // CanaryMod: VehicleDamage
                net.canarymod.api.entity.Entity attk = null;

                if (damagesource.h() != null) {
                    attk = damagesource.h().getCanaryEntity();
                }
                VehicleDamageHook hook = new VehicleDamageHook((Vehicle) this.entity, attk, new CanaryDamageSource(damagesource), i0);

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return false;
                }
                i0 = hook.getDamageDealt();
                //

                this.j(-this.k());
                this.i(10);
                this.J();
                this.h(this.i() + i0 * 10);
                boolean flag0 = damagesource.i() instanceof EntityPlayer && ((EntityPlayer) damagesource.i()).ce.d;

                if (flag0 || this.i() > 40) {
                    if (this.n != null) {
                        this.n.a((Entity) this);
                    }

                    if (flag0 && !this.c()) {
                        this.w();
                    } else {
                        this.a(damagesource);
                    }
                }

                return true;
            }
        } else {
            return true;
        }
    }

    public void a(DamageSource damagesource) {
        // CanaryMod: VehicleDestroy
        VehicleDestroyHook vdh = new VehicleDestroyHook((Vehicle) this.entity);

        Canary.hooks().callHook(vdh);
        //
        this.w();
        ItemStack itemstack = new ItemStack(Item.aA, 1);

        if (this.c != null) {
            itemstack.c(this.c);
        }

        this.a(itemstack, 0.0F);
    }

    public boolean K() {
        return !this.M;
    }

    public void w() {
        super.w();
        if (this.b != null) {
            this.b.a();
        }
    }

    public void l_() {
        if (this.b != null) {
            this.b.a();
        }

        if (this.j() > 0) {
            this.i(this.j() - 1);
        }

        if (this.i() > 0) {
            this.h(this.i() - 1);
        }

        if (this.v < -64.0D) {
            this.B();
        }

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

        if (this.q.I) {
            if (this.e > 0) {
                double d0 = this.u + (this.f - this.u) / (double) this.e;
                double d1 = this.v + (this.g - this.v) / (double) this.e;
                double d2 = this.w + (this.h - this.w) / (double) this.e;
                double d3 = MathHelper.g(this.i - (double) this.A);

                this.A = (float) ((double) this.A + d3 / (double) this.e);
                this.B = (float) ((double) this.B + (this.j - (double) this.B) / (double) this.e);
                --this.e;
                this.b(d0, d1, d2);
                this.b(this.A, this.B);
            } else {
                this.b(this.u, this.v, this.w);
                this.b(this.A, this.B);
            }
        } else {
            this.r = this.u;
            this.s = this.v;
            this.t = this.w;
            this.y -= 0.03999999910593033D;
            int i1 = MathHelper.c(this.u);

            i0 = MathHelper.c(this.v);
            int i2 = MathHelper.c(this.w);

            // CanaryMod: VehicleMove
            if (Math.floor(this.r) != Math.floor(this.u) || Math.floor(this.s) != Math.floor(this.v) || Math.floor(this.t) != Math.floor(this.w)) {
                Vector3D from = new Vector3D(this.r, this.s, this.t);
                Vector3D to = new Vector3D(this.u, this.v, this.w);
                VehicleMoveHook vmh = new VehicleMoveHook((Vehicle) this.entity, from, to);

                Canary.hooks().callHook(vmh);
                // Can't handle canceling yet... if (vmh.isCanceled()) { }
            }
            //

            if (BlockRailBase.d_(this.q, i1, i0 - 1, i2)) {
                --i0;
            }

            double d4 = 0.4D;
            double d5 = 0.0078125D;
            int i3 = this.q.a(i1, i0, i2);

            if (BlockRailBase.d_(i3)) {
                int i4 = this.q.h(i1, i0, i2);

                this.a(i1, i0, i2, d4, d5, i3, i4);
                if (i3 == Block.cx.cz) {
                    // CanaryMod: MinecartActivate
                    MinecartActivateHook mah = new MinecartActivateHook((Minecart) this.getCanaryEntity(), (i1 & 8) != 0);

                    Canary.hooks().callHook(mah);
                    if (!mah.isCanceled()) {
                        this.a(i1, i0, i2, (i4 & 8) != 0);
                    }
                    //
                }
            } else {
                this.b(d4);
            }

            this.C();
            this.B = 0.0F;
            double d6 = this.r - this.u;
            double d7 = this.t - this.w;

            if (d6 * d6 + d7 * d7 > 0.001D) {
                this.A = (float) (Math.atan2(d7, d6) * 180.0D / 3.141592653589793D);
                if (this.a) {
                    this.A += 180.0F;
                }
            }

            double d8 = (double) MathHelper.g(this.A - this.C);

            if (d8 < -170.0D || d8 >= 170.0D) {
                this.A += 180.0F;
                this.a = !this.a;
            }

            this.b(this.A, this.B);
            List list = this.q.b((Entity) this, this.E.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && !list.isEmpty()) {
                for (int i5 = 0; i5 < list.size(); ++i5) {
                    Entity entity = (Entity) list.get(i5);

                    if (entity != this.n && entity.L() && entity instanceof EntityMinecart) {
                        entity.f((Entity) this);
                    }
                }
            }

            if (this.n != null && this.n.M) {
                if (this.n.o == this) {
                    this.n.o = null;
                }

                this.n = null;
            }
        }
    }

    public void a(int i0, int i1, int i2, boolean flag0) {}

    protected void b(double d0) {
        if (this.x < -d0) {
            this.x = -d0;
        }

        if (this.x > d0) {
            this.x = d0;
        }

        if (this.z < -d0) {
            this.z = -d0;
        }

        if (this.z > d0) {
            this.z = d0;
        }

        if (this.F) {
            this.x *= 0.5D;
            this.y *= 0.5D;
            this.z *= 0.5D;
        }

        this.d(this.x, this.y, this.z);
        if (!this.F) {
            this.x *= 0.949999988079071D;
            this.y *= 0.949999988079071D;
            this.z *= 0.949999988079071D;
        }
    }

    protected void a(int i0, int i1, int i2, double d0, double d1, int i3, int i4) {
        this.T = 0.0F;
        Vec3 vec3 = this.a(this.u, this.v, this.w);

        this.v = (double) i1;
        boolean flag0 = false;
        boolean flag1 = false;

        if (i3 == Block.X.cz) {
            flag0 = (i4 & 8) != 0;
            flag1 = !flag0;
        }

        if (((BlockRailBase) Block.r[i3]).e()) {
            i4 &= 7;
        }

        if (i4 >= 2 && i4 <= 5) {
            this.v = (double) (i1 + 1);
        }

        if (i4 == 2) {
            this.x -= d1;
        }

        if (i4 == 3) {
            this.x += d1;
        }

        if (i4 == 4) {
            this.z += d1;
        }

        if (i4 == 5) {
            this.z -= d1;
        }

        int[][] aint = d[i4];
        double d2 = (double) (aint[1][0] - aint[0][0]);
        double d3 = (double) (aint[1][2] - aint[0][2]);
        double d4 = Math.sqrt(d2 * d2 + d3 * d3);
        double d5 = this.x * d2 + this.z * d3;

        if (d5 < 0.0D) {
            d2 = -d2;
            d3 = -d3;
        }

        double d6 = Math.sqrt(this.x * this.x + this.z * this.z);

        if (d6 > 2.0D) {
            d6 = 2.0D;
        }

        this.x = d6 * d2 / d4;
        this.z = d6 * d3 / d4;
        double d7;
        double d8;

        if (this.n != null) {
            d7 = this.n.x * this.n.x + this.n.z * this.n.z;
            d8 = this.x * this.x + this.z * this.z;
            if (d7 > 1.0E-4D && d8 < 0.01D) {
                this.x += this.n.x * 0.1D;
                this.z += this.n.z * 0.1D;
                flag1 = false;
            }
        }

        if (flag1) {
            d7 = Math.sqrt(this.x * this.x + this.z * this.z);
            if (d7 < 0.03D) {
                this.x *= 0.0D;
                this.y *= 0.0D;
                this.z *= 0.0D;
            } else {
                this.x *= 0.5D;
                this.y *= 0.0D;
                this.z *= 0.5D;
            }
        }

        d7 = 0.0D;
        d8 = (double) i0 + 0.5D + (double) aint[0][0] * 0.5D;
        double d9 = (double) i2 + 0.5D + (double) aint[0][2] * 0.5D;
        double d10 = (double) i0 + 0.5D + (double) aint[1][0] * 0.5D;
        double d11 = (double) i2 + 0.5D + (double) aint[1][2] * 0.5D;

        d2 = d10 - d8;
        d3 = d11 - d9;
        double d12;
        double d13;

        if (d2 == 0.0D) {
            this.u = (double) i0 + 0.5D;
            d7 = this.w - (double) i2;
        } else if (d3 == 0.0D) {
            this.w = (double) i2 + 0.5D;
            d7 = this.u - (double) i0;
        } else {
            d12 = this.u - d8;
            d13 = this.w - d9;
            d7 = (d12 * d2 + d13 * d3) * 2.0D;
        }

        this.u = d8 + d2 * d7;
        this.w = d9 + d3 * d7;
        this.b(this.u, this.v + (double) this.N, this.w);
        d12 = this.x;
        d13 = this.z;
        if (this.n != null) {
            d12 *= 0.75D;
            d13 *= 0.75D;
        }

        if (d12 < -d0) {
            d12 = -d0;
        }

        if (d12 > d0) {
            d12 = d0;
        }

        if (d13 < -d0) {
            d13 = -d0;
        }

        if (d13 > d0) {
            d13 = d0;
        }

        this.d(d12, 0.0D, d13);
        if (aint[0][1] != 0 && MathHelper.c(this.u) - i0 == aint[0][0] && MathHelper.c(this.w) - i2 == aint[0][2]) {
            this.b(this.u, this.v + (double) aint[0][1], this.w);
        } else if (aint[1][1] != 0 && MathHelper.c(this.u) - i0 == aint[1][0] && MathHelper.c(this.w) - i2 == aint[1][2]) {
            this.b(this.u, this.v + (double) aint[1][1], this.w);
        }

        this.h();
        Vec3 vec31 = this.a(this.u, this.v, this.w);

        if (vec31 != null && vec3 != null) {
            double d14 = (vec3.d - vec31.d) * 0.05D;

            d6 = Math.sqrt(this.x * this.x + this.z * this.z);
            if (d6 > 0.0D) {
                this.x = this.x / d6 * (d6 + d14);
                this.z = this.z / d6 * (d6 + d14);
            }

            this.b(this.u, vec31.d, this.w);
        }

        int i5 = MathHelper.c(this.u);
        int i6 = MathHelper.c(this.w);

        if (i5 != i0 || i6 != i2) {
            d6 = Math.sqrt(this.x * this.x + this.z * this.z);
            this.x = d6 * (double) (i5 - i0);
            this.z = d6 * (double) (i6 - i2);
        }

        if (flag0) {
            double d15 = Math.sqrt(this.x * this.x + this.z * this.z);

            if (d15 > 0.01D) {
                double d16 = 0.06D;

                this.x += this.x / d15 * d16;
                this.z += this.z / d15 * d16;
            } else if (i4 == 1) {
                if (this.q.u(i0 - 1, i1, i2)) {
                    this.x = 0.02D;
                } else if (this.q.u(i0 + 1, i1, i2)) {
                    this.x = -0.02D;
                }
            } else if (i4 == 0) {
                if (this.q.u(i0, i1, i2 - 1)) {
                    this.z = 0.02D;
                } else if (this.q.u(i0, i1, i2 + 1)) {
                    this.z = -0.02D;
                }
            }
        }
    }

    protected void h() {
        if (this.n != null) {
            this.x *= 0.996999979019165D;
            this.y *= 0.0D;
            this.z *= 0.996999979019165D;
        } else {
            this.x *= 0.9599999785423279D;
            this.y *= 0.0D;
            this.z *= 0.9599999785423279D;
        }
    }

    public Vec3 a(double d0, double d1, double d2) {
        int i0 = MathHelper.c(d0);
        int i1 = MathHelper.c(d1);
        int i2 = MathHelper.c(d2);

        if (BlockRailBase.d_(this.q, i0, i1 - 1, i2)) {
            --i1;
        }

        int i3 = this.q.a(i0, i1, i2);

        if (BlockRailBase.d_(i3)) {
            int i4 = this.q.h(i0, i1, i2);

            d1 = (double) i1;
            if (((BlockRailBase) Block.r[i3]).e()) {
                i4 &= 7;
            }

            if (i4 >= 2 && i4 <= 5) {
                d1 = (double) (i1 + 1);
            }

            int[][] aint = d[i4];
            double d3 = 0.0D;
            double d4 = (double) i0 + 0.5D + (double) aint[0][0] * 0.5D;
            double d5 = (double) i1 + 0.5D + (double) aint[0][1] * 0.5D;
            double d6 = (double) i2 + 0.5D + (double) aint[0][2] * 0.5D;
            double d7 = (double) i0 + 0.5D + (double) aint[1][0] * 0.5D;
            double d8 = (double) i1 + 0.5D + (double) aint[1][1] * 0.5D;
            double d9 = (double) i2 + 0.5D + (double) aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2.0D;
            double d12 = d9 - d6;

            if (d10 == 0.0D) {
                d0 = (double) i0 + 0.5D;
                d3 = d2 - (double) i2;
            } else if (d12 == 0.0D) {
                d2 = (double) i2 + 0.5D;
                d3 = d0 - (double) i0;
            } else {
                double d13 = d0 - d4;
                double d14 = d2 - d6;

                d3 = (d13 * d10 + d14 * d12) * 2.0D;
            }

            d0 = d4 + d10 * d3;
            d1 = d5 + d11 * d3;
            d2 = d6 + d12 * d3;
            if (d11 < 0.0D) {
                ++d1;
            }

            if (d11 > 0.0D) {
                d1 += 0.5D;
            }

            return this.q.U().a(d0, d1, d2);
        } else {
            return null;
        }
    }

    protected void a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.n("CustomDisplayTile")) {
            this.k(nbttagcompound.e("DisplayTile"));
            this.l(nbttagcompound.e("DisplayData"));
            this.m(nbttagcompound.e("DisplayOffset"));
        }

        if (nbttagcompound.b("CustomName") && nbttagcompound.i("CustomName").length() > 0) {
            this.c = nbttagcompound.i("CustomName");
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        if (this.s()) {
            nbttagcompound.a("CustomDisplayTile", true);
            nbttagcompound.a("DisplayTile", this.m() == null ? 0 : this.m().cz);
            nbttagcompound.a("DisplayData", this.o());
            nbttagcompound.a("DisplayOffset", this.q());
        }

        if (this.c != null && this.c.length() > 0) {
            nbttagcompound.a("CustomName", this.c);
        }
    }

    public void f(Entity entity) {
        if (!this.q.I) {
            if (entity != this.n) {
                // CanaryMod: VehicleCollision
                VehicleCollisionHook vch = new VehicleCollisionHook((Vehicle) this.entity, entity.getCanaryEntity());

                Canary.hooks().callHook(vch);
                if (vch.isCanceled()) {
                    return;
                }
                //
                if (entity instanceof EntityLiving && !(entity instanceof EntityPlayer) && !(entity instanceof EntityIronGolem) && this.l() == 0 && this.x * this.x + this.z * this.z > 0.01D && this.n == null && entity.o == null) {
                    // CanaryMod: VehicleEnter (Animal/Mob)
                    VehicleEnterHook veh = new VehicleEnterHook((Vehicle) this.entity, (EntityLiving) entity.getCanaryEntity());

                    Canary.hooks().callHook(veh);
                    if (!veh.isCanceled()) {
                        entity.a((Entity) this);
                    }
                    //
                }

                double d0 = entity.u - this.u;
                double d1 = entity.w - this.w;
                double d2 = d0 * d0 + d1 * d1;

                if (d2 >= 9.999999747378752E-5D) {
                    d2 = (double) MathHelper.a(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D) {
                        d3 = 1.0D;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= 0.10000000149011612D;
                    d1 *= 0.10000000149011612D;
                    d0 *= (double) (1.0F - this.aa);
                    d1 *= (double) (1.0F - this.aa);
                    d0 *= 0.5D;
                    d1 *= 0.5D;
                    if (entity instanceof EntityMinecart) {
                        double d4 = entity.u - this.u;
                        double d5 = entity.w - this.w;
                        Vec3 vec3 = this.q.U().a(d4, 0.0D, d5).a();
                        Vec3 vec31 = this.q.U().a((double) MathHelper.b(this.A * 3.1415927F / 180.0F), 0.0D, (double) MathHelper.a(this.A * 3.1415927F / 180.0F)).a();
                        double d6 = Math.abs(vec3.b(vec31));

                        if (d6 < 0.800000011920929D) {
                            return;
                        }

                        double d7 = entity.x + this.x;
                        double d8 = entity.z + this.z;

                        if (((EntityMinecart) entity).l() == 2 && this.l() != 2) {
                            this.x *= 0.20000000298023224D;
                            this.z *= 0.20000000298023224D;
                            this.g(entity.x - d0, 0.0D, entity.z - d1);
                            entity.x *= 0.949999988079071D;
                            entity.z *= 0.949999988079071D;
                        } else if (((EntityMinecart) entity).l() != 2 && this.l() == 2) {
                            entity.x *= 0.20000000298023224D;
                            entity.z *= 0.20000000298023224D;
                            entity.g(this.x + d0, 0.0D, this.z + d1);
                            this.x *= 0.949999988079071D;
                            this.z *= 0.949999988079071D;
                        } else {
                            d7 /= 2.0D;
                            d8 /= 2.0D;
                            this.x *= 0.20000000298023224D;
                            this.z *= 0.20000000298023224D;
                            this.g(d7 - d0, 0.0D, d8 - d1);
                            entity.x *= 0.20000000298023224D;
                            entity.z *= 0.20000000298023224D;
                            entity.g(d7 + d0, 0.0D, d8 + d1);
                        }
                    } else {
                        this.g(-d0, 0.0D, -d1);
                        entity.g(d0 / 4.0D, 0.0D, d1 / 4.0D);
                    }
                }
            }
        }
    }

    public void h(int i0) {
        this.ah.b(19, Integer.valueOf(i0));
    }

    public int i() {
        return this.ah.c(19);
    }

    public void i(int i0) {
        this.ah.b(17, Integer.valueOf(i0));
    }

    public int j() {
        return this.ah.c(17);
    }

    public void j(int i0) {
        this.ah.b(18, Integer.valueOf(i0));
    }

    public int k() {
        return this.ah.c(18);
    }

    public abstract int l();

    public Block m() {
        if (!this.s()) {
            return this.n();
        } else {
            int i0 = this.u().c(20) & '\uffff';

            return i0 > 0 && i0 < Block.r.length ? Block.r[i0] : null;
        }
    }

    public Block n() {
        return null;
    }

    public int o() {
        return !this.s() ? this.p() : this.u().c(20) >> 16;
    }

    public int p() {
        return 0;
    }

    public int q() {
        return !this.s() ? this.r() : this.u().c(21);
    }

    public int r() {
        return 6;
    }

    public void k(int i0) {
        this.u().b(20, Integer.valueOf(i0 & '\uffff' | this.o() << 16));
        this.a(true);
    }

    public void l(int i0) {
        Block block = this.m();
        int i1 = block == null ? 0 : block.cz;

        this.u().b(20, Integer.valueOf(i1 & '\uffff' | i0 << 16));
        this.a(true);
    }

    public void m(int i0) {
        this.u().b(21, Integer.valueOf(i0));
        this.a(true);
    }

    public boolean s() {
        return this.u().a(22) == 1;
    }

    public void a(boolean flag0) {
        this.u().b(22, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    public void a(String s0) {
        this.c = s0;
    }

    public String am() {
        return this.c != null ? this.c : super.am();
    }

    public boolean c() {
        return this.c != null;
    }

    public String t() {
        return this.c;
    }
}
