package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanaryHorse;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.api.world.position.Vector3D;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.VehicleEnterHook;
import net.canarymod.hook.entity.VehicleExitHook;
import net.canarymod.hook.entity.VehicleMoveHook;

import java.util.Iterator;
import java.util.List;

public class EntityHorse extends EntityAnimal implements IInvBasic {

    private static final IEntitySelector bu = new EntityHorseBredSelector();
    private static final Attribute bv = (new RangedAttribute("horse.jumpStrength", 0.7D, 0.0D, 2.0D)).a("Jump Strength").a(true);
    private static final String[] bw = new String[]{null, "textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse/armor/horse_armor_diamond.png"};
    private static final String[] bx = new String[]{"", "meo", "goo", "dio"};
    private static final int[] by = new int[]{0, 5, 7, 11};
    private static final String[] bz = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
    private static final String[] bA = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
    private static final String[] bB = new String[]{null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
    private static final String[] bC = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
    private int bD;
    private int bE;
    private int bF;
    public int bp;
    public int bq;
    protected boolean br;
    public AnimalChest bG; // CanaryMod: private => public
    private boolean bH;
    protected int bs;
    protected float bt;
    private boolean bI;
    private float bJ;
    private float bK;
    private float bL;
    private float bM;
    private float bN;
    private float bO;
    private int bP;
    private String bQ;
    private String[] bR = new String[3];

    public EntityHorse(World world) {
        super(world);
        this.a(1.4F, 1.6F);
        this.ag = false;
        this.l(false);
        this.k().a(true);
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(1, new EntityAIPanic(this, 1.2D));
        this.c.a(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
        this.c.a(2, new EntityAIMate(this, 1.0D));
        this.c.a(4, new EntityAIFollowParent(this, 1.0D));
        this.c.a(6, new EntityAIWander(this, 0.7D));
        this.c.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.c.a(8, new EntityAILookIdle(this));
        this.cH();
        this.entity = new CanaryHorse(this); // CanaryMod: wrap entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, Integer.valueOf(0));
        this.ah.a(19, Byte.valueOf((byte)0));
        this.ah.a(20, Integer.valueOf(0));
        this.ah.a(21, String.valueOf(""));
        this.ah.a(22, Integer.valueOf(0));
    }

    public void p(int i0) {
        this.ah.b(19, Byte.valueOf((byte)i0));
        this.cJ();
    }

    public int bT() {
        return this.ah.a(19);
    }

    public void q(int i0) {
        this.ah.b(20, Integer.valueOf(i0));
        this.cJ();
    }

    public int bU() {
        return this.ah.c(20);
    }

    public String an() {
        if (this.bB()) {
            return this.bA();
        }
        else {
            int i0 = this.bT();

            switch (i0) {
                case 0:
                default:
                    return StatCollector.a("entity.horse.name");

                case 1:
                    return StatCollector.a("entity.donkey.name");

                case 2:
                    return StatCollector.a("entity.mule.name");

                case 3:
                    return StatCollector.a("entity.zombiehorse.name");

                case 4:
                    return StatCollector.a("entity.skeletonhorse.name");
            }
        }
    }

    private boolean w(int i0) {
        return (this.ah.c(16) & i0) != 0;
    }

    private void b(int i0, boolean flag0) {
        int i1 = this.ah.c(16);

        if (flag0) {
            this.ah.b(16, Integer.valueOf(i1 | i0));
        }
        else {
            this.ah.b(16, Integer.valueOf(i1 & ~i0));
        }
    }

    public boolean bV() {
        return !this.g_();
    }

    public boolean bW() {
        return this.w(2);
    }

    public boolean ca() {
        return this.bV();
    }

    public String cb() {
        return this.ah.e(21);
    }

    public void b(String s0) {
        this.ah.b(21, s0);
    }

    public float cc() {
        int i0 = this.b();

        return i0 >= 0 ? 1.0F : 0.5F + (float)(-24000 - i0) / -24000.0F * 0.5F;
    }

    public void a(boolean flag0) {
        if (flag0) {
            this.a(this.cc());
        }
        else {
            this.a(1.0F);
        }
    }

    public boolean cd() {
        return this.br;
    }

    public void i(boolean flag0) {
        this.b(2, flag0);
    }

    public void j(boolean flag0) {
        this.br = flag0;
    }

    public boolean bG() {
        return !this.cy() && super.bG();
    }

    protected void o(float f0) {
        if (f0 > 6.0F && this.cg()) {
            this.o(false);
        }
    }

    public boolean ce() {
        return this.w(8);
    }

    public int cf() {
        return this.ah.c(22);
    }

    public int d(ItemStack itemstack) {
        return itemstack == null ? 0 : (itemstack.d == Item.ce.cv ? 1 : (itemstack.d == Item.cf.cv ? 2 : (itemstack.d == Item.cg.cv ? 3 : 0)));
    }

    public boolean cg() {
        return this.w(32);
    }

    public boolean ch() {
        return this.w(64);
    }

    public boolean ci() {
        return this.w(16);
    }

    public boolean cj() {
        return this.bH;
    }

    public void r(int i0) {
        this.ah.b(22, Integer.valueOf(i0));
        this.cJ();
    }

    public void k(boolean flag0) {
        this.b(16, flag0);
    }

    public void l(boolean flag0) {
        this.b(8, flag0);
    }

    public void m(boolean flag0) {
        this.bH = flag0;
    }

    public void n(boolean flag0) {
        this.b(4, flag0);
    }

    public int ck() {
        return this.bs;
    }

    public void s(int i0) {
        this.bs = i0;
    }

    public int t(int i0) {
        int i1 = MathHelper.a(this.ck() + i0, 0, this.cq());

        this.s(i1);
        return i1;
    }

    public boolean a(DamageSource damagesource, float f0) {
        Entity entity = damagesource.i();

        return this.n != null && this.n.equals(entity) ? false : super.a(damagesource, f0);
    }

    public int aQ() {
        return by[this.cf()];
    }

    public boolean M() {
        return this.n == null;
    }

    public boolean cl() {
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.w);

        this.q.a(i0, i1);
        return true;
    }

    public void cm() {
        if (!this.q.I && this.ce()) {
            this.b(Block.az.cF, 1);
            this.l(false);
        }
    }

    private void cF() {
        this.cM();
        this.q.a((Entity)this, "eating", 1.0F, 1.0F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
    }

    protected void b(float f0) {
        if (f0 > 1.0F) {
            this.a("mob.horse.land", 0.4F, 1.0F);
        }

        int i0 = MathHelper.f(f0 * 0.5F - 3.0F);

        if (i0 > 0) {
            this.a(DamageSource.h, (float)i0);
            if (this.n != null) {
                this.n.a(DamageSource.h, (float)i0);
            }

            int i1 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.v - 0.2D - (double)this.C), MathHelper.c(this.w));

            if (i1 > 0) {
                StepSound stepsound = Block.s[i1].cS;

                this.q.a((Entity)this, stepsound.e(), stepsound.c() * 0.5F, stepsound.d() * 0.75F);
            }
        }
    }

    private int cG() {
        int i0 = this.bT();

        return this.ce() && (i0 == 1 || i0 == 2) ? 17 : 2;
    }

    private void cH() {
        AnimalChest animalchest = this.bG;

        this.bG = new AnimalChest("HorseChest", this.cG());
        this.bG.a(this.an());
        if (animalchest != null) {
            animalchest.b(this);
            int i0 = Math.min(animalchest.j_(), this.bG.j_());

            for (int i1 = 0; i1 < i0; ++i1) {
                ItemStack itemstack = animalchest.a(i1);

                if (itemstack != null) {
                    this.bG.a(i1, itemstack.m());
                }
            }

            animalchest = null;
        }

        this.bG.a(this);
        this.cI();
    }

    private void cI() {
        if (!this.q.I) {
            this.n(this.bG.a(0) != null);
            if (this.cv()) {
                this.r(this.d(this.bG.a(1)));
            }
        }
    }

    public void a(InventoryBasic inventorybasic) {
        int i0 = this.cf();
        boolean flag0 = this.co();

        this.cI();
        if (this.ac > 20) {
            if (i0 == 0 && i0 != this.cf()) {
                this.a("mob.horse.armor", 0.5F, 1.0F);
            }

            if (!flag0 && this.co()) {
                this.a("mob.horse.leather", 0.5F, 1.0F);
            }
        }
    }

    public boolean bs() {
        this.cl();
        return super.bs();
    }

    protected EntityHorse a(Entity entity, double d0) {
        double d1 = Double.MAX_VALUE;
        Entity entity1 = null;
        List list = this.q.a(entity, entity.E.a(d0, d0, d0), bu);
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Entity entity2 = (Entity)iterator.next();
            double d2 = entity2.e(entity.u, entity.v, entity.w);

            if (d2 < d1) {
                entity1 = entity2;
                d1 = d2;
            }
        }

        return (EntityHorse)entity1;
    }

    public double cn() {
        return this.a(bv).e();
    }

    protected String aP() {
        this.cM();
        int i0 = this.bT();

        return i0 == 3 ? "mob.horse.zombie.death" : (i0 == 4 ? "mob.horse.skeleton.death" : (i0 != 1 && i0 != 2 ? "mob.horse.death" : "mob.horse.donkey.death"));
    }

    protected int s() {
        boolean flag0 = this.ab.nextInt(4) == 0;
        int i0 = this.bT();

        return i0 == 4 ? Item.aZ.cv : (i0 == 3 ? (flag0 ? 0 : Item.bo.cv) : Item.aH.cv);
    }

    protected String aO() {
        this.cM();
        if (this.ab.nextInt(3) == 0) {
            this.cO();
        }

        int i0 = this.bT();

        return i0 == 3 ? "mob.horse.zombie.hit" : (i0 == 4 ? "mob.horse.skeleton.hit" : (i0 != 1 && i0 != 2 ? "mob.horse.hit" : "mob.horse.donkey.hit"));
    }

    public boolean co() {
        return this.w(4);
    }

    protected String r() {
        this.cM();
        if (this.ab.nextInt(10) == 0 && !this.bc()) {
            this.cO();
        }

        int i0 = this.bT();

        return i0 == 3 ? "mob.horse.zombie.idle" : (i0 == 4 ? "mob.horse.skeleton.idle" : (i0 != 1 && i0 != 2 ? "mob.horse.idle" : "mob.horse.donkey.idle"));
    }

    protected String cp() {
        this.cM();
        this.cO();
        int i0 = this.bT();

        return i0 != 3 && i0 != 4 ? (i0 != 1 && i0 != 2 ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
    }

    protected void a(int i0, int i1, int i2, int i3) {
        StepSound stepsound = Block.s[i3].cS;

        if (this.q.a(i0, i1 + 1, i2) == Block.aX.cF) {
            stepsound = Block.aX.cS;
        }

        if (!Block.s[i3].cU.d()) {
            int i4 = this.bT();

            if (this.n != null && i4 != 1 && i4 != 2) {
                ++this.bP;
                if (this.bP > 5 && this.bP % 3 == 0) {
                    this.a("mob.horse.gallop", stepsound.c() * 0.15F, stepsound.d());
                    if (i4 == 0 && this.ab.nextInt(10) == 0) {
                        this.a("mob.horse.breathe", stepsound.c() * 0.6F, stepsound.d());
                    }
                }
                else if (this.bP <= 5) {
                    this.a("mob.horse.wood", stepsound.c() * 0.15F, stepsound.d());
                }
            }
            else if (stepsound == Block.h) {
                this.a("mob.horse.soft", stepsound.c() * 0.15F, stepsound.d());
            }
            else {
                this.a("mob.horse.wood", stepsound.c() * 0.15F, stepsound.d());
            }
        }
    }

    protected void az() {
        super.az();
        this.aX().b(bv);
        this.a(SharedMonsterAttributes.a).a(53.0D);
        this.a(SharedMonsterAttributes.d).a(0.22499999403953552D);
    }

    public int bv() {
        return 6;
    }

    public int cq() {
        return 100;
    }

    protected float ba() {
        return 0.8F;
    }

    public int o() {
        return 400;
    }

    private void cJ() {
        this.bQ = null;
    }

    public void f(EntityPlayer entityplayer) {
        if (!this.q.I && (this.n == null || this.n == entityplayer) && this.bW()) {
            this.bG.a(this.an());
            entityplayer.a(this, (IInventory)this.bG);
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.h();

        if (itemstack != null && itemstack.d == Item.bE.cv) {
            return super.a(entityplayer);
        }
        else if (!this.bW() && this.cy()) {
            return false;
        }
        else if (this.bW() && this.bV() && entityplayer.ah()) {
            this.f(entityplayer);
            return true;
        }
        else if (this.ca() && this.n != null) {
            return super.a(entityplayer);
        }
        else {
            if (itemstack != null) {
                boolean flag0 = false;

                if (this.cv()) {
                    byte b0 = -1;

                    if (itemstack.d == Item.ce.cv) {
                        b0 = 1;
                    }
                    else if (itemstack.d == Item.cf.cv) {
                        b0 = 2;
                    }
                    else if (itemstack.d == Item.cg.cv) {
                        b0 = 3;
                    }

                    if (b0 >= 0) {
                        if (!this.bW()) {
                            this.cD();
                            return true;
                        }

                        this.f(entityplayer);
                        return true;
                    }
                }

                if (!flag0 && !this.cy()) {
                    float f0 = 0.0F;
                    short short1 = 0;
                    byte b1 = 0;

                    if (itemstack.d == Item.V.cv) {
                        f0 = 2.0F;
                        short1 = 60;
                        b1 = 3;
                    }
                    else if (itemstack.d == Item.ba.cv) {
                        f0 = 1.0F;
                        short1 = 30;
                        b1 = 3;
                    }
                    else if (itemstack.d == Item.W.cv) {
                        f0 = 7.0F;
                        short1 = 180;
                        b1 = 3;
                    }
                    else if (itemstack.d == Block.cB.cF) {
                        f0 = 20.0F;
                        short1 = 180;
                    }
                    else if (itemstack.d == Item.l.cv) {
                        f0 = 3.0F;
                        short1 = 60;
                        b1 = 3;
                    }
                    else if (itemstack.d == Item.bR.cv) {
                        f0 = 4.0F;
                        short1 = 60;
                        b1 = 5;
                        if (this.bW() && this.b() == 0) {
                            flag0 = true;
                            this.bX();
                        }
                    }
                    else if (itemstack.d == Item.av.cv) {
                        f0 = 10.0F;
                        short1 = 240;
                        b1 = 10;
                        if (this.bW() && this.b() == 0) {
                            flag0 = true;
                            this.bX();
                        }
                    }

                    if (this.aN() < this.aT() && f0 > 0.0F) {
                        this.f(f0);
                        flag0 = true;
                    }

                    if (!this.bV() && short1 > 0) {
                        this.a(short1);
                        flag0 = true;
                    }

                    if (b1 > 0 && (flag0 || !this.bW()) && b1 < this.cq()) {
                        flag0 = true;
                        this.t(b1);
                    }

                    if (flag0) {
                        this.cF();
                    }
                }

                if (!this.bW() && !flag0) {
                    if (itemstack != null && itemstack.a(entityplayer, (EntityLivingBase)this)) {
                        return true;
                    }

                    this.cD();
                    return true;
                }

                if (!flag0 && this.cw() && !this.ce() && itemstack.d == Block.az.cF) {
                    this.l(true);
                    this.a("mob.chickenplop", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
                    flag0 = true;
                    this.cH();
                }

                if (!flag0 && this.ca() && !this.co() && itemstack.d == Item.aC.cv) {
                    this.f(entityplayer);
                    return true;
                }

                if (flag0) {
                    if (!entityplayer.bG.d && --itemstack.b == 0) {
                        entityplayer.bn.a(entityplayer.bn.c, (ItemStack)null);
                    }

                    return true;
                }
            }

            if (this.ca() && this.n == null) {
                if (itemstack != null && itemstack.a(entityplayer, (EntityLivingBase)this)) {
                    return true;
                }
                else {
                    this.h(entityplayer);
                    return true;
                }
            }
            else {
                return super.a(entityplayer);
            }
        }
    }

    private void h(EntityPlayer entityplayer) {
        entityplayer.A = this.A;
        entityplayer.B = this.B;
        this.o(false);
        this.p(false);
        if (!this.q.I) {
            // CanaryMod: VehicleEnter/VehicleExit
            CancelableHook hook = null;
            if (this.n == null) {
                hook = new VehicleEnterHook((Vehicle)this.entity, entityplayer.getCanaryHuman());
            }
            else if (this.n == entityplayer) {
                hook = new VehicleExitHook((Vehicle)this.entity, entityplayer.getCanaryHuman());
            }
            if (hook != null) {
                hook.call();
                if (!hook.isCanceled()) {
                    entityplayer.a((Entity)this);
                }
            }
            //
        }
    }

    public boolean cv() {
        return this.bT() == 0;
    }

    public boolean cw() {
        int i0 = this.bT();

        return i0 == 2 || i0 == 1;
    }

    protected boolean bc() {
        return this.n != null && this.co() ? true : this.cg() || this.ch();
    }

    public boolean cy() {
        int i0 = this.bT();

        return i0 == 3 || i0 == 4;
    }

    public boolean cz() {
        return this.cy() || this.bT() == 2;
    }

    public boolean c(ItemStack itemstack) {
        return false;
    }

    private void cL() {
        this.bp = 1;
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        if (!this.q.I) {
            this.cE();
        }
    }

    public void c() {
        if (this.ab.nextInt(200) == 0) {
            this.cL();
        }

        super.c();
        if (!this.q.I) {
            if (this.ab.nextInt(900) == 0 && this.aB == 0) {
                this.f(1.0F);
            }

            if (!this.cg() && this.n == null && this.ab.nextInt(300) == 0 && this.q.a(MathHelper.c(this.u), MathHelper.c(this.v) - 1, MathHelper.c(this.w)) == Block.z.cF) {
                this.o(true);
            }

            if (this.cg() && ++this.bD > 50) {
                this.bD = 0;
                this.o(false);
            }

            if (this.ci() && !this.bV() && !this.cg()) {
                EntityHorse entityhorse = this.a(this, 16.0D);

                if (entityhorse != null && this.e(entityhorse) > 4.0D) {
                    PathEntity pathentity = this.q.a(this, entityhorse, 16.0F, true, false, false, true);

                    this.a(pathentity);
                }
            }
        }
    }

    public void l_() {
        super.l_();
        if (this.q.I && this.ah.a()) {
            this.ah.e();
            this.cJ();
        }

        if (this.bE > 0 && ++this.bE > 30) {
            this.bE = 0;
            this.b(128, false);
        }

        if (!this.q.I && this.bF > 0 && ++this.bF > 20) {
            this.bF = 0;
            this.p(false);
        }

        if (this.bp > 0 && ++this.bp > 8) {
            this.bp = 0;
        }

        if (this.bq > 0) {
            ++this.bq;
            if (this.bq > 300) {
                this.bq = 0;
            }
        }

        this.bK = this.bJ;
        if (this.cg()) {
            this.bJ += (1.0F - this.bJ) * 0.4F + 0.05F;
            if (this.bJ > 1.0F) {
                this.bJ = 1.0F;
            }
        }
        else {
            this.bJ += (0.0F - this.bJ) * 0.4F - 0.05F;
            if (this.bJ < 0.0F) {
                this.bJ = 0.0F;
            }
        }

        this.bM = this.bL;
        if (this.ch()) {
            this.bK = this.bJ = 0.0F;
            this.bL += (1.0F - this.bL) * 0.4F + 0.05F;
            if (this.bL > 1.0F) {
                this.bL = 1.0F;
            }
        }
        else {
            this.bI = false;
            this.bL += (0.8F * this.bL * this.bL * this.bL - this.bL) * 0.6F - 0.05F;
            if (this.bL < 0.0F) {
                this.bL = 0.0F;
            }
        }

        this.bO = this.bN;
        if (this.w(128)) {
            this.bN += (1.0F - this.bN) * 0.7F + 0.05F;
            if (this.bN > 1.0F) {
                this.bN = 1.0F;
            }
        }
        else {
            this.bN += (0.0F - this.bN) * 0.7F - 0.05F;
            if (this.bN < 0.0F) {
                this.bN = 0.0F;
            }
        }
    }

    private void cM() {
        if (!this.q.I) {
            this.bE = 1;
            this.b(128, true);
        }
    }

    private boolean cN() {
        return this.n == null && this.o == null && this.bW() && this.bV() && !this.cz() && this.aN() >= this.aT();
    }

    public void e(boolean flag0) {
        this.b(32, flag0);
    }

    public void o(boolean flag0) {
        this.e(flag0);
    }

    public void p(boolean flag0) {
        if (flag0) {
            this.o(false);
        }

        this.b(64, flag0);
    }

    private void cO() {
        if (!this.q.I) {
            this.bF = 1;
            this.p(true);
        }
    }

    public void cD() {
        this.cO();
        String s0 = this.cp();

        if (s0 != null) {
            this.a(s0, this.ba(), this.bb());
        }
    }

    public void cE() {
        this.a(this, this.bG);
        this.cm();
    }

    private void a(Entity entity, AnimalChest animalchest) {
        if (animalchest != null && !this.q.I) {
            for (int i0 = 0; i0 < animalchest.j_(); ++i0) {
                ItemStack itemstack = animalchest.a(i0);

                if (itemstack != null) {
                    this.a(itemstack, 0.0F);
                }
            }
        }
    }

    public boolean g(EntityPlayer entityplayer) {
        this.b(entityplayer.c_());
        this.i(true);
        return true;
    }

    public void e(float f0, float f1) {
        if (this.n != null && this.co()) {
            double prevX = this.r, prevY = this.s, prevZ = this.t;
            float prevR = this.A, prevP = this.B;
            this.C = this.A = this.n.A;
            this.B = this.n.B * 0.5F;
            this.b(this.A, this.B);
            this.aP = this.aN = this.A;
            f0 = ((EntityLivingBase)this.n).be * 0.5F;
            f1 = ((EntityLivingBase)this.n).bf;
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
                this.bP = 0;
            }

            if (this.F && this.bt == 0.0F && this.ch() && !this.bI) {
                f0 = 0.0F;
                f1 = 0.0F;
            }

            if (this.bt > 0.0F && !this.cd() && this.F) {
                this.y = this.cn() * (double)this.bt;
                if (this.a(Potion.j)) {
                    this.y += (double)((float)(this.b(Potion.j).c() + 1) * 0.1F);
                }

                this.j(true);
                this.an = true;
                if (f1 > 0.0F) {
                    float f2 = MathHelper.a(this.A * 3.1415927F / 180.0F);
                    float f3 = MathHelper.b(this.A * 3.1415927F / 180.0F);

                    this.x += (double)(-0.4F * f2 * this.bt);
                    this.z += (double)(0.4F * f3 * this.bt);
                    this.a("mob.horse.jump", 0.4F, 1.0F);
                }

                this.bt = 0.0F;
            }

            this.Y = 1.0F;
            this.aR = this.bg() * 0.1F;
            if (!this.q.I) {
                this.i((float)this.a(SharedMonsterAttributes.d).e());
                super.e(f0, f1);
                // TODO: This may be the move point
                if (Math.floor(this.r) != Math.floor(this.u) || Math.floor(this.s) != Math.floor(this.v) || Math.floor(this.t) != Math.floor(this.w)) {
                    Vector3D from = new Vector3D(this.r, this.s, this.t);
                    Vector3D to = new Vector3D(this.u, this.v, this.w);
                    VehicleMoveHook vmh = (VehicleMoveHook)new VehicleMoveHook((Vehicle)this.entity, from, to).call();
                    if (vmh.isCanceled()) {
                        this.x = 0.0D;
                        this.y = 0.0D;
                        this.z = 0.0D;
                        this.b(this.r, this.s, this.t, prevR, prevP);
                        this.r = prevX;
                        this.s = prevY;
                        this.t = prevZ;
                        this.V(); // Update rider
                        if (this.n instanceof EntityPlayerMP) {
                            double ox = Math.cos((double)this.A * 3.141592653589793D / 180.0D) * 0.4D;
                            double oz = Math.sin((double)this.A * 3.141592653589793D / 180.0D) * 0.4D;
                            ((EntityPlayerMP)this.n).a.b(new Packet13PlayerLookMove(this.u + ox, this.v + this.Y() + this.n.X(), this.v + this.X(), this.w + oz, this.n.A, this.n.B, this.F));
                            this.n.x = 0.0D;
                            this.n.y = 0.0D;
                            this.n.z = 0.0D;
                        }
                    }
                }
            }

            if (this.F) {
                this.bt = 0.0F;
                this.j(false);
            }

            this.aF = this.aG;
            double d0 = this.u - this.r;
            double d1 = this.w - this.t;
            float f4 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

            if (f4 > 1.0F) {
                f4 = 1.0F;
            }

            this.aG += (f4 - this.aG) * 0.4F;
            this.aH += this.aG;
        }
        else {
            this.Y = 0.5F;
            this.aR = 0.02F;
            super.e(f0, f1);
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("EatingHaystack", this.cg());
        nbttagcompound.a("ChestedHorse", this.ce());
        nbttagcompound.a("HasReproduced", this.cj());
        nbttagcompound.a("Bred", this.ci());
        nbttagcompound.a("Type", this.bT());
        nbttagcompound.a("Variant", this.bU());
        nbttagcompound.a("Temper", this.ck());
        nbttagcompound.a("Tame", this.bW());
        nbttagcompound.a("OwnerName", this.cb());
        if (this.ce()) {
            NBTTagList nbttaglist = new NBTTagList();

            for (int i0 = 2; i0 < this.bG.j_(); ++i0) {
                ItemStack itemstack = this.bG.a(i0);

                if (itemstack != null) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                    nbttagcompound1.a("Slot", (byte)i0);
                    itemstack.b(nbttagcompound1);
                    nbttaglist.a((NBTBase)nbttagcompound1);
                }
            }

            nbttagcompound.a("Items", (NBTBase)nbttaglist);
        }

        if (this.bG.a(1) != null) {
            nbttagcompound.a("ArmorItem", (NBTBase)this.bG.a(1).b(new NBTTagCompound("ArmorItem")));
        }

        if (this.bG.a(0) != null) {
            nbttagcompound.a("SaddleItem", (NBTBase)this.bG.a(0).b(new NBTTagCompound("SaddleItem")));
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.o(nbttagcompound.n("EatingHaystack"));
        this.k(nbttagcompound.n("Bred"));
        this.l(nbttagcompound.n("ChestedHorse"));
        this.m(nbttagcompound.n("HasReproduced"));
        this.p(nbttagcompound.e("Type"));
        this.q(nbttagcompound.e("Variant"));
        this.s(nbttagcompound.e("Temper"));
        this.i(nbttagcompound.n("Tame"));
        if (nbttagcompound.b("OwnerName")) {
            this.b(nbttagcompound.i("OwnerName"));
        }

        AttributeInstance attributeinstance = this.aX().a("Speed");

        if (attributeinstance != null) {
            this.a(SharedMonsterAttributes.d).a(attributeinstance.b() * 0.25D);
        }

        if (this.ce()) {
            NBTTagList nbttaglist = nbttagcompound.m("Items");

            this.cH();

            for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.b(i0);
                int i1 = nbttagcompound1.c("Slot") & 255;

                if (i1 >= 2 && i1 < this.bG.j_()) {
                    this.bG.a(i1, ItemStack.a(nbttagcompound1));
                }
            }
        }

        ItemStack itemstack;

        if (nbttagcompound.b("ArmorItem")) {
            itemstack = ItemStack.a(nbttagcompound.l("ArmorItem"));
            if (itemstack != null && v(itemstack.d)) {
                this.bG.a(1, itemstack);
            }
        }

        if (nbttagcompound.b("SaddleItem")) {
            itemstack = ItemStack.a(nbttagcompound.l("SaddleItem"));
            if (itemstack != null && itemstack.d == Item.aC.cv) {
                this.bG.a(0, itemstack);
            }
        }
        else if (nbttagcompound.n("Saddle")) {
            this.bG.a(0, new ItemStack(Item.aC));
        }

        this.cI();
    }

    public boolean a(EntityAnimal entityanimal) {
        if (entityanimal == this) {
            return false;
        }
        else if (entityanimal.getClass() != this.getClass()) {
            return false;
        }
        else {
            EntityHorse entityhorse = (EntityHorse)entityanimal;

            if (this.cN() && entityhorse.cN()) {
                int i0 = this.bT();
                int i1 = entityhorse.bT();

                return i0 == i1 || i0 == 0 && i1 == 1 || i0 == 1 && i1 == 0;
            }
            else {
                return false;
            }
        }
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        EntityHorse entityhorse = (EntityHorse)entityageable;
        EntityHorse entityhorse1 = new EntityHorse(this.q);
        int i0 = this.bT();
        int i1 = entityhorse.bT();
        int i2 = 0;

        if (i0 == i1) {
            i2 = i0;
        }
        else if (i0 == 0 && i1 == 1 || i0 == 1 && i1 == 0) {
            i2 = 2;
        }

        if (i2 == 0) {
            int i3 = this.ab.nextInt(9);
            int i4;

            if (i3 < 4) {
                i4 = this.bU() & 255;
            }
            else if (i3 < 8) {
                i4 = entityhorse.bU() & 255;
            }
            else {
                i4 = this.ab.nextInt(7);
            }

            int i5 = this.ab.nextInt(5);

            if (i5 < 4) {
                i4 |= this.bU() & '\uff00';
            }
            else if (i5 < 8) {
                i4 |= entityhorse.bU() & '\uff00';
            }
            else {
                i4 |= this.ab.nextInt(5) << 8 & '\uff00';
            }

            entityhorse1.q(i4);
        }

        entityhorse1.p(i2);
        double d0 = this.a(SharedMonsterAttributes.a).b() + entityageable.a(SharedMonsterAttributes.a).b() + (double)this.cP();

        entityhorse1.a(SharedMonsterAttributes.a).a(d0 / 3.0D);
        double d1 = this.a(bv).b() + entityageable.a(bv).b() + this.cQ();

        entityhorse1.a(bv).a(d1 / 3.0D);
        double d2 = this.a(SharedMonsterAttributes.d).b() + entityageable.a(SharedMonsterAttributes.d).b() + this.cR();

        entityhorse1.a(SharedMonsterAttributes.d).a(d2 / 3.0D);
        return entityhorse1;
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        Object object = super.a(entitylivingdata);
        boolean flag0 = false;
        int i0 = 0;
        int i1;

        if (object instanceof EntityHorseGroupData) {
            i1 = ((EntityHorseGroupData)object).a;
            i0 = ((EntityHorseGroupData)object).b & 255 | this.ab.nextInt(5) << 8;
        }
        else {
            if (this.ab.nextInt(10) == 0) {
                i1 = 1;
            }
            else {
                int i2 = this.ab.nextInt(7);
                int i3 = this.ab.nextInt(5);

                i1 = 0;
                i0 = i2 | i3 << 8;
            }

            object = new EntityHorseGroupData(i1, i0);
        }

        this.p(i1);
        this.q(i0);
        if (this.ab.nextInt(5) == 0) {
            this.c(-24000);
        }

        if (i1 != 4 && i1 != 3) {
            this.a(SharedMonsterAttributes.a).a((double)this.cP());
            if (i1 == 0) {
                this.a(SharedMonsterAttributes.d).a(this.cR());
            }
            else {
                this.a(SharedMonsterAttributes.d).a(0.17499999701976776D);
            }
        }
        else {
            this.a(SharedMonsterAttributes.a).a(15.0D);
            this.a(SharedMonsterAttributes.d).a(0.20000000298023224D);
        }

        if (i1 != 2 && i1 != 1) {
            this.a(bv).a(this.cQ());
        }
        else {
            this.a(bv).a(0.5D);
        }

        this.g(this.aT());
        return (EntityLivingData)object;
    }

    protected boolean bf() {
        return true;
    }

    public void u(int i0) {
        if (this.co()) {
            if (i0 < 0) {
                i0 = 0;
            }
            else {
                this.bI = true;
                this.cO();
            }

            if (i0 >= 90) {
                this.bt = 1.0F;
            }
            else {
                this.bt = 0.4F + 0.4F * (float)i0 / 90.0F;
            }
        }
    }

    public void W() {
        super.W();
        if (this.bM > 0.0F) {
            float f0 = MathHelper.a(this.aN * 3.1415927F / 180.0F);
            float f1 = MathHelper.b(this.aN * 3.1415927F / 180.0F);
            float f2 = 0.7F * this.bM;
            float f3 = 0.15F * this.bM;

            this.n.b(this.u + (double)(f2 * f0), this.v + this.Y() + this.n.X() + (double)f3, this.w - (double)(f2 * f1));
            if (this.n instanceof EntityLivingBase) {
                ((EntityLivingBase)this.n).aN = this.aN;
            }
        }
    }

    private float cP() {
        return 15.0F + (float)this.ab.nextInt(8) + (float)this.ab.nextInt(9);
    }

    private double cQ() {
        return 0.4000000059604645D + this.ab.nextDouble() * 0.2D + this.ab.nextDouble() * 0.2D + this.ab.nextDouble() * 0.2D;
    }

    private double cR() {
        return (0.44999998807907104D + this.ab.nextDouble() * 0.3D + this.ab.nextDouble() * 0.3D + this.ab.nextDouble() * 0.3D) * 0.25D;
    }

    public static boolean v(int i0) {
        return i0 == Item.ce.cv || i0 == Item.cf.cv || i0 == Item.cg.cv;
    }

    public boolean e() {
        return false;
    }
}
