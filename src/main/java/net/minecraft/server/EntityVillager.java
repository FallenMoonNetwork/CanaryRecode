package net.minecraft.server;

import net.canarymod.api.CanaryVillagerTrade;
import net.canarymod.api.entity.living.humanoid.CanaryVillager;
import net.canarymod.api.entity.living.humanoid.Villager;
import net.canarymod.hook.entity.VillagerTradeUnlockHook;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class EntityVillager extends EntityAgeable implements IMerchant, INpc {

    private int bq;
    private boolean br;
    private boolean bs;
    public Village bp; // CanaryMod: package => public
    private EntityPlayer bt;
    private MerchantRecipeList bu;
    private int bv;
    private boolean bw;
    private int bx;
    private String by;
    private boolean bz;
    private float bA;
    private static final Map bB = new HashMap();
    private static final Map bC = new HashMap();

    public EntityVillager(World world) {
        this(world, 0);
    }

    public EntityVillager(World world, int i0) {
        super(world);
        this.p(i0);
        this.a(0.6F, 1.8F);
        this.k().b(true);
        this.k().a(true);
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.c.a(1, new EntityAITradePlayer(this));
        this.c.a(1, new EntityAILookAtTradePlayer(this));
        this.c.a(2, new EntityAIMoveIndoors(this));
        this.c.a(3, new EntityAIRestrictOpenDoor(this));
        this.c.a(4, new EntityAIOpenDoor(this, true));
        this.c.a(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.c.a(6, new EntityAIVillagerMate(this));
        this.c.a(7, new EntityAIFollowGolem(this));
        this.c.a(8, new EntityAIPlay(this, 0.32D));
        this.c.a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.c.a(9, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
        this.c.a(9, new EntityAIWander(this, 0.6D));
        this.c.a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.entity = new CanaryVillager(this); // CanaryMod: Wrap Entity
    }

    protected void az() {
        super.az();
        this.a(SharedMonsterAttributes.d).a(0.5D);
    }

    public boolean bf() {
        return true;
    }

    protected void bk() {
        if (--this.bq <= 0) {
            this.q.A.a(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w));
            this.bq = 70 + this.ab.nextInt(50);
            this.bp = this.q.A.a(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w), 32);
            if (this.bp == null) {
                this.bR();
            }
            else {
                ChunkCoordinates chunkcoordinates = this.bp.a();

                this.b(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, (int)((float)this.bp.b() * 0.6F));
                if (this.bz) {
                    this.bz = false;
                    this.bp.b(5);
                }
            }
        }

        if (!this.bW() && this.bv > 0) {
            --this.bv;
            if (this.bv <= 0) {
                if (this.bw) {
                    if (this.bu.size() > 1) {
                        Iterator iterator = this.bu.iterator();

                        while (iterator.hasNext()) {
                            MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();

                            if (merchantrecipe.g()) {
                                merchantrecipe.a(this.ab.nextInt(6) + this.ab.nextInt(6) + 2);
                            }
                        }
                    }

                    this.q(1);
                    this.bw = false;
                    if (this.bp != null && this.by != null) {
                        this.q.a((Entity)this, (byte)14);
                        this.bp.a(this.by, 1);
                    }
                }

                this.c(new PotionEffect(Potion.l.H, 200, 0));
            }
        }

        super.bk();
    }

    public boolean a(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.h();
        boolean flag0 = itemstack != null && itemstack.d == Item.bE.cv;

        if (!flag0 && this.T() && !this.bW() && !this.g_()) {
            if (!this.q.I) {
                this.a_(entityplayer);
                entityplayer.a((IMerchant)this, this.bA());
            }

            return true;
        }
        else {
            return super.a(entityplayer);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Integer.valueOf(0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Profession", this.bT());
        nbttagcompound.a("Riches", this.bx);
        if (this.bu != null) {
            nbttagcompound.a("Offers", this.bu.a());
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.p(nbttagcompound.e("Profession"));
        this.bx = nbttagcompound.e("Riches");
        if (nbttagcompound.b("Offers")) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.l("Offers");

            this.bu = new MerchantRecipeList(nbttagcompound1);
        }
    }

    protected boolean t() {
        return false;
    }

    protected String r() {
        return this.bW() ? "mob.villager.haggle" : "mob.villager.idle";
    }

    protected String aO() {
        return "mob.villager.hit";
    }

    protected String aP() {
        return "mob.villager.death";
    }

    public void p(int i0) {
        this.ah.b(16, Integer.valueOf(i0));
    }

    public int bT() {
        return this.ah.c(16);
    }

    public boolean bU() {
        return this.br;
    }

    public void i(boolean flag0) {
        this.br = flag0;
    }

    public void j(boolean flag0) {
        this.bs = flag0;
    }

    public boolean bV() {
        return this.bs;
    }

    public void b(EntityLivingBase entitylivingbase) {
        super.b(entitylivingbase);
        if (this.bp != null && entitylivingbase != null) {
            this.bp.a(entitylivingbase);
            if (entitylivingbase instanceof EntityPlayer) {
                byte b0 = -1;

                if (this.g_()) {
                    b0 = -3;
                }

                this.bp.a(((EntityPlayer)entitylivingbase).c_(), b0);
                if (this.T()) {
                    this.q.a((Entity)this, (byte)13);
                }
            }
        }
    }

    public void a(DamageSource damagesource) {
        if (this.bp != null) {
            Entity entity = damagesource.i();

            if (entity != null) {
                if (entity instanceof EntityPlayer) {
                    this.bp.a(((EntityPlayer)entity).c_(), -2);
                }
                else if (entity instanceof IMob) {
                    this.bp.h();
                }
            }
            else if (entity == null) {
                EntityPlayer entityplayer = this.q.a(this, 16.0D);

                if (entityplayer != null) {
                    this.bp.h();
                }
            }
        }

        super.a(damagesource);
    }

    public void a_(EntityPlayer entityplayer) {
        this.bt = entityplayer;
    }

    public EntityPlayer m_() {
        return this.bt;
    }

    public boolean bW() {
        return this.bt != null;
    }

    public void a(MerchantRecipe merchantrecipe) {
        merchantrecipe.f();
        this.a_ = -this.o();
        this.a("mob.villager.yes", this.ba(), this.bb());
        if (merchantrecipe.a((MerchantRecipe)this.bu.get(this.bu.size() - 1))) {
            this.bv = 40;
            this.bw = true;
            if (this.bt != null) {
                this.by = this.bt.c_();
            }
            else {
                this.by = null;
            }
        }

        if (merchantrecipe.a().d == Item.bJ.cv) {
            this.bx += merchantrecipe.a().b;
        }
    }

    public void a_(ItemStack itemstack) {
        if (!this.q.I && this.a_ > -this.o() + 20) {
            this.a_ = -this.o();
            if (itemstack != null) {
                this.a("mob.villager.yes", this.ba(), this.bb());
            }
            else {
                this.a("mob.villager.no", this.ba(), this.bb());
            }
        }
    }

    public MerchantRecipeList b(EntityPlayer entityplayer) {
        if (this.bu == null) {
            this.q(1);
        }

        return this.bu;
    }

    private float p(float f0) {
        float f1 = f0 + this.bA;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void q(int i0) {
        if (this.bu != null) {
            this.bA = MathHelper.c((float)this.bu.size()) * 0.2F;
        }
        else {
            this.bA = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;

        merchantrecipelist = new MerchantRecipeList();
        int i1;

        label50:
        switch (this.bT()) {
            case 0:
                a(merchantrecipelist, Item.V.cv, this.ab, this.p(0.9F));
                a(merchantrecipelist, Block.ag.cF, this.ab, this.p(0.5F));
                a(merchantrecipelist, Item.bm.cv, this.ab, this.p(0.5F));
                a(merchantrecipelist, Item.aX.cv, this.ab, this.p(0.4F));
                b(merchantrecipelist, Item.W.cv, this.ab, this.p(0.9F));
                b(merchantrecipelist, Item.bh.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.l.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.be.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.bg.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.k.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.bn.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.n.cv, this.ab, this.p(0.5F));
                if (this.ab.nextFloat() < this.p(0.5F)) {
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(Block.K, 10), new ItemStack(Item.bJ), new ItemStack(Item.ar.cv, 4 + this.ab.nextInt(2), 0)));
                }
                break;

            case 1:
                a(merchantrecipelist, Item.aM.cv, this.ab, this.p(0.8F));
                a(merchantrecipelist, Item.aN.cv, this.ab, this.p(0.8F));
                a(merchantrecipelist, Item.bI.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Block.as.cF, this.ab, this.p(0.8F));
                b(merchantrecipelist, Block.R.cF, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.aS.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.aU.cv, this.ab, this.p(0.2F));
                if (this.ab.nextFloat() < this.p(0.07F)) {
                    Enchantment enchantment = Enchantment.c[this.ab.nextInt(Enchantment.c.length)];
                    int i2 = MathHelper.a(this.ab, enchantment.d(), enchantment.b());
                    ItemStack itemstack = Item.bY.a(new EnchantmentData(enchantment, i2));

                    i1 = 2 + this.ab.nextInt(5 + i2 * 10) + 3 * i2;
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(Item.aN), new ItemStack(Item.bJ, i1), itemstack));
                }
                break;

            case 2:
                b(merchantrecipelist, Item.bC.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.bF.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.aE.cv, this.ab, this.p(0.4F));
                b(merchantrecipelist, Block.bi.cF, this.ab, this.p(0.3F));
                int[] aint = new int[]{Item.s.cv, Item.B.cv, Item.ag.cv, Item.ak.cv, Item.j.cv, Item.E.cv, Item.i.cv, Item.D.cv};
                int[] aint1 = aint;
                int i3 = aint.length;

                i1 = 0;

                while (true) {
                    if (i1 >= i3) {
                        break label50;
                    }

                    int i4 = aint1[i1];

                    if (this.ab.nextFloat() < this.p(0.05F)) {
                        merchantrecipelist.add(new MerchantRecipe(new ItemStack(i4, 1, 0), new ItemStack(Item.bJ, 2 + this.ab.nextInt(3), 0), EnchantmentHelper.a(this.ab, new ItemStack(i4, 1, 0), 5 + this.ab.nextInt(15))));
                    }

                    ++i1;
                }

            case 3:
                a(merchantrecipelist, Item.o.cv, this.ab, this.p(0.7F));
                a(merchantrecipelist, Item.q.cv, this.ab, this.p(0.5F));
                a(merchantrecipelist, Item.r.cv, this.ab, this.p(0.5F));
                a(merchantrecipelist, Item.p.cv, this.ab, this.p(0.5F));
                b(merchantrecipelist, Item.s.cv, this.ab, this.p(0.5F));
                b(merchantrecipelist, Item.B.cv, this.ab, this.p(0.5F));
                b(merchantrecipelist, Item.j.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.E.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.i.cv, this.ab, this.p(0.5F));
                b(merchantrecipelist, Item.D.cv, this.ab, this.p(0.5F));
                b(merchantrecipelist, Item.h.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.C.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.R.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.S.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.ai.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.am.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.af.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.aj.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.ag.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.ak.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.ah.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.al.cv, this.ab, this.p(0.2F));
                b(merchantrecipelist, Item.ae.cv, this.ab, this.p(0.1F));
                b(merchantrecipelist, Item.ab.cv, this.ab, this.p(0.1F));
                b(merchantrecipelist, Item.ac.cv, this.ab, this.p(0.1F));
                b(merchantrecipelist, Item.ad.cv, this.ab, this.p(0.1F));
                break;

            case 4:
                a(merchantrecipelist, Item.o.cv, this.ab, this.p(0.7F));
                a(merchantrecipelist, Item.as.cv, this.ab, this.p(0.5F));
                a(merchantrecipelist, Item.bk.cv, this.ab, this.p(0.5F));
                b(merchantrecipelist, Item.aC.cv, this.ab, this.p(0.1F));
                b(merchantrecipelist, Item.Y.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.aa.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.X.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.Z.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.at.cv, this.ab, this.p(0.3F));
                b(merchantrecipelist, Item.bl.cv, this.ab, this.p(0.3F));
        }

        if (merchantrecipelist.isEmpty()) {
            a(merchantrecipelist, Item.r.cv, this.ab, 1.0F);
        }

        Collections.shuffle(merchantrecipelist);
        if (this.bu == null) {
            this.bu = new MerchantRecipeList();
        }

        for (int i5 = 0; i5 < i0 && i5 < merchantrecipelist.size(); ++i5) {
            MerchantRecipe recipe = (MerchantRecipe)merchantrecipelist.get(i5);
            // CanaryMod: VillagerTradeUnlock
            VillagerTradeUnlockHook hook = (VillagerTradeUnlockHook)new VillagerTradeUnlockHook((Villager)getCanaryEntity(), new CanaryVillagerTrade(recipe)).call();
            if (!hook.isCanceled()) {
                this.bu.a(recipe);
            }
            //
        }
    }

    private static void a(MerchantRecipeList merchantrecipelist, int i0, Random random, float f0) {
        if (random.nextFloat() < f0) {
            merchantrecipelist.add(new MerchantRecipe(a(i0, random), Item.bJ));
        }
    }

    private static ItemStack a(int i0, Random random) {
        return new ItemStack(i0, b(i0, random), 0);
    }

    private static int b(int i0, Random random) {
        Tuple tuple = (Tuple)bB.get(Integer.valueOf(i0));

        return tuple == null ? 1 : (((Integer)tuple.a()).intValue() >= ((Integer)tuple.b()).intValue() ? ((Integer)tuple.a()).intValue() : ((Integer)tuple.a()).intValue() + random.nextInt(((Integer)tuple.b()).intValue() - ((Integer)tuple.a()).intValue()));
    }

    private static void b(MerchantRecipeList merchantrecipelist, int i0, Random random, float f0) {
        if (random.nextFloat() < f0) {
            int i1 = c(i0, random);
            ItemStack itemstack;
            ItemStack itemstack1;

            if (i1 < 0) {
                itemstack = new ItemStack(Item.bJ.cv, 1, 0);
                itemstack1 = new ItemStack(i0, -i1, 0);
            }
            else {
                itemstack = new ItemStack(Item.bJ.cv, i1, 0);
                itemstack1 = new ItemStack(i0, 1, 0);
            }

            merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
        }
    }

    private static int c(int i0, Random random) {
        Tuple tuple = (Tuple)bC.get(Integer.valueOf(i0));

        return tuple == null ? 1 : (((Integer)tuple.a()).intValue() >= ((Integer)tuple.b()).intValue() ? ((Integer)tuple.a()).intValue() : ((Integer)tuple.a()).intValue() + random.nextInt(((Integer)tuple.b()).intValue() - ((Integer)tuple.a()).intValue()));
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        entitylivingdata = super.a(entitylivingdata);
        this.p(this.q.s.nextInt(5));
        return entitylivingdata;
    }

    public void bX() {
        this.bz = true;
    }

    public EntityVillager b(EntityAgeable entityageable) {
        EntityVillager entityvillager = new EntityVillager(this.q);

        entityvillager.a((EntityLivingData)null);
        return entityvillager;
    }

    public boolean bG() {
        return false;
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }

    static {
        bB.put(Integer.valueOf(Item.o.cv), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        bB.put(Integer.valueOf(Item.q.cv), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Integer.valueOf(Item.r.cv), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Integer.valueOf(Item.p.cv), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bB.put(Integer.valueOf(Item.aM.cv), new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
        bB.put(Integer.valueOf(Item.aN.cv), new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
        bB.put(Integer.valueOf(Item.bI.cv), new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
        bB.put(Integer.valueOf(Item.bp.cv), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bB.put(Integer.valueOf(Item.bC.cv), new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
        bB.put(Integer.valueOf(Item.as.cv), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(Item.bk.cv), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(Item.bm.cv), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(Item.aX.cv), new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
        bB.put(Integer.valueOf(Item.U.cv), new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
        bB.put(Integer.valueOf(Item.bj.cv), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Integer.valueOf(Item.bi.cv), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Integer.valueOf(Item.V.cv), new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
        bB.put(Integer.valueOf(Block.ag.cF), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
        bB.put(Integer.valueOf(Item.bo.cv), new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
        bC.put(Integer.valueOf(Item.k.cv), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.bg.cv), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.s.cv), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Integer.valueOf(Item.B.cv), new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
        bC.put(Integer.valueOf(Item.j.cv), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.E.cv), new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Item.i.cv), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
        bC.put(Integer.valueOf(Item.D.cv), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Item.h.cv), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.C.cv), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.R.cv), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.S.cv), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.ai.cv), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.am.cv), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.af.cv), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.aj.cv), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.ag.cv), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
        bC.put(Integer.valueOf(Item.ak.cv), new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
        bC.put(Integer.valueOf(Item.ah.cv), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bC.put(Integer.valueOf(Item.al.cv), new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
        bC.put(Integer.valueOf(Item.ae.cv), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Integer.valueOf(Item.ab.cv), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Integer.valueOf(Item.ac.cv), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
        bC.put(Integer.valueOf(Item.ad.cv), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
        bC.put(Integer.valueOf(Item.W.cv), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bC.put(Integer.valueOf(Item.bh.cv), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Integer.valueOf(Item.l.cv), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Integer.valueOf(Item.be.cv), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bC.put(Integer.valueOf(Block.R.cF), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bC.put(Integer.valueOf(Block.as.cF), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.Y.cv), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
        bC.put(Integer.valueOf(Item.aa.cv), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.X.cv), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.Z.cv), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.aC.cv), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.bF.cv), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(Item.aE.cv), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(Item.aS.cv), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Item.aU.cv), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Block.bi.cF), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(Item.at.cv), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Integer.valueOf(Item.bl.cv), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Integer.valueOf(Item.bn.cv), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bC.put(Integer.valueOf(Item.bC.cv), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Integer.valueOf(Item.n.cv), new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
