package net.minecraft.server;


import java.util.Calendar;
import net.canarymod.api.entity.living.monster.CanaryZombie;


public class EntityZombie extends EntityMob {

    private int d = 0;

    public EntityZombie(World world) {
        super(world);
        this.aH = "/mob/zombie.png";
        this.bI = 0.23F;
        this.aC().b(true);
        this.bo.a(0, new EntityAISwimming(this));
        this.bo.a(1, new EntityAIBreakDoor(this));
        this.bo.a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.bI, false));
        this.bo.a(3, new EntityAIAttackOnCollide(this, EntityVillager.class, this.bI, true));
        this.bo.a(4, new EntityAIMoveTwardsRestriction(this, this.bI));
        this.bo.a(5, new EntityAIMoveThroughVillage(this, this.bI, false));
        this.bo.a(6, new EntityAIWander(this, this.bI));
        this.bo.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.bo.a(7, new EntityAILookIdle(this));
        this.bp.a(1, new EntityAIHurtByTarget(this, true));
        this.bp.a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        this.bp.a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16.0F, 0, false));
        this.entity = new CanaryZombie(this); // CanaryMod: Wrap Entity
    }

    protected int ay() {
        return 40;
    }

    public float bE() {
        return super.bE() * (this.h_() ? 1.5F : 1.0F);
    }

    protected void a() {
        super.a();
        this.u().a(12, Byte.valueOf((byte) 0));
        this.u().a(13, Byte.valueOf((byte) 0));
        this.u().a(14, Byte.valueOf((byte) 0));
    }

    public int aW() {
        return maxHealth == 0 ? 20 : maxHealth; // CanaryMod: custom Max Health
    }

    public int aZ() {
        int i0 = super.aZ() + 2;

        if (i0 > 20) {
            i0 = 20;
        }

        return i0;
    }

    protected boolean bh() {
        return true;
    }

    public boolean h_() {
        return this.u().a(12) == 1;
    }

    public void a(boolean flag0) {
        this.u().b(12, Byte.valueOf((byte) 1));
    }

    public boolean m() {
        return this.u().a(13) == 1;
    }

    public void i(boolean flag0) {
        this.u().b(13, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    public void c() {
        if (this.q.v() && !this.q.I && !this.h_()) {
            float f0 = this.c(1.0F);

            if (f0 > 0.5F && this.ab.nextFloat() * 30.0F < (f0 - 0.4F) * 2.0F && this.q.l(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w))) {
                boolean flag0 = true;
                ItemStack itemstack = this.p(4);

                if (itemstack != null) {
                    if (itemstack.g()) {
                        itemstack.b(itemstack.j() + this.ab.nextInt(2));
                        if (itemstack.j() >= itemstack.l()) {
                            this.a(itemstack);
                            this.c(4, (ItemStack) null);
                        }
                    }

                    flag0 = false;
                }

                if (flag0) {
                    this.d(8);
                }
            }
        }

        super.c();
    }

    public void l_() {
        if (!this.q.I && this.o()) {
            int i0 = this.q();

            this.d -= i0;
            if (this.d <= 0) {
                this.p();
            }
        }

        super.l_();
    }

    public boolean m(Entity entity) {
        boolean flag0 = super.m(entity);

        if (flag0 && this.bG() == null && this.ae() && this.ab.nextFloat() < (float) this.q.r * 0.3F) {
            entity.d(2 * this.q.r);
        }

        return flag0;
    }

    public int c(Entity entity) {
        ItemStack itemstack = this.bG();
        float f0 = (float) (this.aW() - this.aX()) / (float) this.aW();
        int i0 = 3 + MathHelper.d(f0 * 4.0F);

        if (itemstack != null) {
            i0 += itemstack.a((Entity) this);
        }

        return i0;
    }

    protected String bb() {
        return "mob.zombie.say";
    }

    protected String bc() {
        return "mob.zombie.hurt";
    }

    protected String bd() {
        return "mob.zombie.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.zombie.step", 0.15F, 1.0F);
    }

    protected int be() {
        return Item.bn.cp;
    }

    public EnumCreatureAttribute bF() {
        return EnumCreatureAttribute.b;
    }

    protected void l(int i0) {
        switch (this.ab.nextInt(3)) {
            case 0:
                this.b(Item.p.cp, 1);
                break;

            case 1:
                this.b(Item.bL.cp, 1);
                break;

            case 2:
                this.b(Item.bM.cp, 1);
        }
    }

    protected void bH() {
        super.bH();
        if (this.ab.nextFloat() < (this.q.r == 3 ? 0.05F : 0.01F)) {
            int i0 = this.ab.nextInt(3);

            if (i0 == 0) {
                this.c(0, new ItemStack(Item.r));
            } else {
                this.c(0, new ItemStack(Item.g));
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.h_()) {
            nbttagcompound.a("IsBaby", true);
        }

        if (this.m()) {
            nbttagcompound.a("IsVillager", true);
        }

        nbttagcompound.a("ConversionTime", this.o() ? this.d : -1);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.n("IsBaby")) {
            this.a(true);
        }

        if (nbttagcompound.n("IsVillager")) {
            this.i(true);
        }

        if (nbttagcompound.b("ConversionTime") && nbttagcompound.e("ConversionTime") > -1) {
            this.a(nbttagcompound.e("ConversionTime"));
        }
    }

    public void a(EntityLiving entityliving) {
        super.a(entityliving);
        if (this.q.r >= 2 && entityliving instanceof EntityVillager) {
            if (this.q.r == 2 && this.ab.nextBoolean()) {
                return;
            }

            EntityZombie entityzombie = new EntityZombie(this.q);

            entityzombie.k(entityliving);
            this.q.e((Entity) entityliving);
            entityzombie.bJ();
            entityzombie.i(true);
            if (entityliving.h_()) {
                entityzombie.a(true);
            }

            this.q.d((Entity) entityzombie);
            this.q.a((EntityPlayer) null, 1016, (int) this.u, (int) this.v, (int) this.w, 0);
        }
    }

    public void bJ() {
        this.h(this.ab.nextFloat() < au[this.q.r]);
        if (this.q.s.nextFloat() < 0.05F) {
            this.i(true);
        }

        this.bH();
        this.bI();
        if (this.p(4) == null) {
            Calendar calendar = this.q.V();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.ab.nextFloat() < 0.25F) {
                this.c(4, new ItemStack(this.ab.nextFloat() < 0.1F ? Block.bj : Block.be));
                this.bq[4] = 0.0F;
            }
        }
    }

    public boolean a_(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.cd();

        if (itemstack != null && itemstack.b() == Item.au && itemstack.k() == 0 && this.m() && this.a(Potion.t)) {
            if (!entityplayer.ce.d) {
                --itemstack.a;
            }

            if (itemstack.a <= 0) {
                entityplayer.bK.a(entityplayer.bK.c, (ItemStack) null);
            }

            if (!this.q.I) {
                this.a(this.ab.nextInt(2401) + 3600);
            }

            return true;
        } else {
            return false;
        }
    }

    public void a(int i0) { // CanaryMod: protected => public
        this.d = i0;
        this.u().b(14, Byte.valueOf((byte) 1));
        this.o(Potion.t.H);
        this.d(new PotionEffect(Potion.g.H, i0, Math.min(this.q.r - 1, 0)));
        this.q.a((Entity) this, (byte) 16);
    }

    public boolean o() {
        return this.u().a(14) == 1;
    }

    public void p() { // CanaryMod: protected => public
        EntityVillager entityvillager = new EntityVillager(this.q);

        entityvillager.k(this);
        entityvillager.bJ();
        entityvillager.q();
        if (this.h_()) {
            entityvillager.a(-24000);
        }

        this.q.e((Entity) this);
        this.q.d((Entity) entityvillager);
        entityvillager.d(new PotionEffect(Potion.k.H, 200, 0));
        this.q.a((EntityPlayer) null, 1017, (int) this.u, (int) this.v, (int) this.w, 0);
    }

    protected int q() {
        int i0 = 1;

        if (this.ab.nextFloat() < 0.01F) {
            int i1 = 0;

            for (int i2 = (int) this.u - 4; i2 < (int) this.u + 4 && i1 < 14; ++i2) {
                for (int i3 = (int) this.v - 4; i3 < (int) this.v + 4 && i1 < 14; ++i3) {
                    for (int i4 = (int) this.w - 4; i4 < (int) this.w + 4 && i1 < 14; ++i4) {
                        int i5 = this.q.a(i2, i3, i4);

                        if (i5 == Block.bt.cz || i5 == Block.W.cz) {
                            if (this.ab.nextFloat() < 0.3F) {
                                ++i0;
                            }

                            ++i1;
                        }
                    }
                }
            }
        }

        return i0;
    }

    // CanaryMod
    public int getConvertTicks() {
        return this.d;
    }

    public void stopConversion() {
        this.u().b(12, Byte.valueOf((byte) 0));
        this.d = -1;
    }
    //
}
