package net.minecraft.server;


import java.util.Calendar;
import net.canarymod.api.entity.living.animal.CanaryBat;


public class EntityBat extends EntityAmbientCreature {

    private ChunkCoordinates h;

    public EntityBat(World world) {
        super(world);
        this.a(0.5F, 0.9F);
        this.a(true);
        this.entity = new CanaryBat(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    protected float aW() {
        return 0.1F;
    }

    protected float aX() {
        return super.aX() * 0.95F;
    }

    protected String r() {
        return this.bF() && this.ab.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    protected String aK() {
        return "mob.bat.hurt";
    }

    protected String aL() {
        return "mob.bat.death";
    }

    public boolean L() {
        return false;
    }

    protected void n(Entity entity) {}

    protected void bf() {}

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(6.0D);
    }

//MERGE: Deprecated? - Chris
    public int ax() {
        return maxHealth == 0 ? 6 : maxHealth; // CanaryMod: custom Max Health
    }

    public boolean bF() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void a(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    protected boolean bb() {
        return true;
    }

    public void l_() {
        super.l_();
        if (this.bF()) {
            this.x = this.y = this.z = 0.0D;
            this.v = (double) MathHelper.c(this.v) + 1.0D - (double) this.P;
        } else {
            this.y *= 0.6000000238418579D;
        }
    }

    protected void be() {
        super.be();
        if (this.bF()) {
            if (!this.q.u(MathHelper.c(this.u), (int) this.v + 1, MathHelper.c(this.w))) {
                this.a(false);
                this.q.a((EntityPlayer) null, 1015, (int) this.u, (int) this.v, (int) this.w, 0);
            } else {
                if (this.ab.nextInt(200) == 0) {
                    this.aP = (float) this.ab.nextInt(360);
                }

                if (this.q.a(this, 4.0D) != null) {
                    this.a(false);
                    this.q.a((EntityPlayer) null, 1015, (int) this.u, (int) this.v, (int) this.w, 0);
                }
            }
        } else {
            if (this.h != null && (!this.q.c(this.h.a, this.h.b, this.h.c) || this.h.b < 1)) {
                this.h = null;
            }

            if (this.h == null || this.ab.nextInt(30) == 0 || this.h.e((int) this.u, (int) this.v, (int) this.w) < 4.0F) {
                this.h = new ChunkCoordinates((int) this.u + this.ab.nextInt(7) - this.ab.nextInt(7), (int) this.v + this.ab.nextInt(6) - 2, (int) this.w + this.ab.nextInt(7) - this.ab.nextInt(7));
            }

            double d0 = (double) this.h.a + 0.5D - this.u;
            double d1 = (double) this.h.b + 0.1D - this.v;
            double d2 = (double) this.h.c + 0.5D - this.w;

            this.x += (Math.signum(d0) * 0.5D - this.x) * 0.10000000149011612D;
            this.y += (Math.signum(d1) * 0.699999988079071D - this.y) * 0.10000000149011612D;
            this.z += (Math.signum(d2) * 0.5D - this.z) * 0.10000000149011612D;
            float f0 = (float) (Math.atan2(this.z, this.x) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f1 = MathHelper.g(f0 - this.A);

            this.bf = 0.5F;
            this.A += f1;
            if (this.ab.nextInt(100) == 0 && this.q.u(MathHelper.c(this.u), (int) this.v + 1, MathHelper.c(this.w))) {
                this.a(true);
            }
        }
    }

    protected boolean e_() {
        return false;
    }

    protected void b(float f0) {}

    protected void a(double d0, boolean flag0) {}

    public boolean as() {
        return true;
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ap()) {
            return false;
        } else {
            if (!this.q.I && this.bF()) {
                this.a(false);
            }

            return super.a(damagesource, f0);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.ah.b(16, Byte.valueOf(nbttagcompound.c("BatFlags")));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("BatFlags", this.ah.a(16));
    }

    public boolean bo() {
        int i0 = MathHelper.c(this.E.b);

        if (i0 >= 63) {
            return false;
        } else {
            int i1 = MathHelper.c(this.u);
            int i2 = MathHelper.c(this.w);
            int i3 = this.q.n(i1, i0, i2);
            byte b0 = 4;
            Calendar calendar = this.q.W();

            if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20) && (calendar.get(2) + 1 != 11 || calendar.get(5) > 3)) {
                if (this.ab.nextBoolean()) {
                    return false;
                }
            } else {
                b0 = 7;
            }

            return i3 > this.ab.nextInt(b0) ? false : super.bo();
        }
    }
}
