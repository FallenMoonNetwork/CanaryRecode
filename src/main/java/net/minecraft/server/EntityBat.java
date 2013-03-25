package net.minecraft.server;


import java.util.Calendar;
import net.canarymod.api.entity.living.animal.CanaryBat;


public class EntityBat extends EntityAmbientCreature {

    private ChunkCoordinates a;

    public EntityBat(World world) {
        super(world);
        this.aH = "/mob/bat.png";
        this.a(0.5F, 0.9F);
        this.a(true);
        this.entity = new CanaryBat(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    protected float ba() {
        return 0.1F;
    }

    protected float aY() {
        return super.aY() * 0.95F;
    }

    protected String bb() {
        return this.h() && this.ab.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    protected String bc() {
        return "mob.bat.hurt";
    }

    protected String bd() {
        return "mob.bat.death";
    }

    public boolean L() {
        return false;
    }

    protected void o(Entity entity) {}

    protected void bg() {}

    public int aW() {
        return 6;
    }

    public boolean h() {
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

    protected boolean bh() {
        return true;
    }

    public void l_() {
        super.l_();
        if (this.h()) {
            this.x = this.y = this.z = 0.0D;
            this.v = (double) MathHelper.c(this.v) + 1.0D - (double) this.P;
        } else {
            this.y *= 0.6000000238418579D;
        }
    }

    protected void bo() {
        super.bo();
        if (this.h()) {
            if (!this.q.u(MathHelper.c(this.u), (int) this.v + 1, MathHelper.c(this.w))) {
                this.a(false);
                this.q.a((EntityPlayer) null, 1015, (int) this.u, (int) this.v, (int) this.w, 0);
            } else {
                if (this.ab.nextInt(200) == 0) {
                    this.aA = (float) this.ab.nextInt(360);
                }

                if (this.q.a(this, 4.0D) != null) {
                    this.a(false);
                    this.q.a((EntityPlayer) null, 1015, (int) this.u, (int) this.v, (int) this.w, 0);
                }
            }
        } else {
            if (this.a != null && (!this.q.c(this.a.a, this.a.b, this.a.c) || this.a.b < 1)) {
                this.a = null;
            }

            if (this.a == null || this.ab.nextInt(30) == 0 || this.a.e((int) this.u, (int) this.v, (int) this.w) < 4.0F) {
                this.a = new ChunkCoordinates((int) this.u + this.ab.nextInt(7) - this.ab.nextInt(7), (int) this.v + this.ab.nextInt(6) - 2, (int) this.w + this.ab.nextInt(7) - this.ab.nextInt(7));
            }

            double d0 = (double) this.a.a + 0.5D - this.u;
            double d1 = (double) this.a.b + 0.1D - this.v;
            double d2 = (double) this.a.c + 0.5D - this.w;

            this.x += (Math.signum(d0) * 0.5D - this.x) * 0.10000000149011612D;
            this.y += (Math.signum(d1) * 0.699999988079071D - this.y) * 0.10000000149011612D;
            this.z += (Math.signum(d2) * 0.5D - this.z) * 0.10000000149011612D;
            float f0 = (float) (Math.atan2(this.z, this.x) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f1 = MathHelper.g(f0 - this.A);

            this.bE = 0.5F;
            this.A += f1;
            if (this.ab.nextInt(100) == 0 && this.q.u(MathHelper.c(this.u), (int) this.v + 1, MathHelper.c(this.w))) {
                this.a(true);
            }
        }
    }

    protected boolean f_() {
        return false;
    }

    protected void a(float f0) {}

    protected void a(double d0, boolean flag0) {}

    public boolean at() {
        return true;
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            if (!this.q.I && this.h()) {
                this.a(false);
            }

            return super.a(damagesource, i0);
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

    public boolean bv() {
        int i0 = MathHelper.c(this.E.b);

        if (i0 >= 63) {
            return false;
        } else {
            int i1 = MathHelper.c(this.u);
            int i2 = MathHelper.c(this.w);
            int i3 = this.q.n(i1, i0, i2);
            byte b0 = 4;
            Calendar calendar = this.q.U();

            if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20) && (calendar.get(2) + 1 != 11 || calendar.get(5) > 3)) {
                if (this.ab.nextBoolean()) {
                    return false;
                }
            } else {
                b0 = 7;
            }

            return i3 > this.ab.nextInt(b0) ? false : super.bv();
        }
    }

    public void bJ() {}
}
