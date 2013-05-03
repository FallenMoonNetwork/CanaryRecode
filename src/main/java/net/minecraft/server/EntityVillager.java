package net.minecraft.server;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.api.CanaryVillagerTrade;
import net.canarymod.api.entity.living.humanoid.CanaryVillager;
import net.canarymod.api.entity.living.humanoid.Villager;
import net.canarymod.hook.entity.VillagerTradeUnlockHook;


public class EntityVillager extends EntityAgeable implements INpc, IMerchant {

    private int e;
    private boolean f;
    private boolean g;
    public Village d; // CanaryMod: package => public
    private EntityPlayer h;
    private MerchantRecipeList i;
    private int j;
    private boolean bK;
    private int bL;
    private String bM;
    private boolean bN;
    private float bO;
    private static final Map bP = new HashMap();
    private static final Map bQ = new HashMap();

    public EntityVillager(World world) {
        this(world, 0);
    }

    public EntityVillager(World world, int i0) {
        super(world);
        this.e = 0;
        this.f = false;
        this.g = false;
        this.d = null;
        this.s(i0);
        this.aH = "/mob/villager/villager.png";
        this.bI = 0.5F;
        this.a(0.6F, 1.8F);
        this.aC().b(true);
        this.aC().a(true);
        this.bo.a(0, new EntityAISwimming(this));
        this.bo.a(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.bo.a(1, new EntityAITradePlayer(this));
        this.bo.a(1, new EntityAILookAtTradePlayer(this));
        this.bo.a(2, new EntityAIMoveIndoors(this));
        this.bo.a(3, new EntityAIRestrictOpenDoor(this));
        this.bo.a(4, new EntityAIOpenDoor(this, true));
        this.bo.a(5, new EntityAIMoveTwardsRestriction(this, 0.3F));
        this.bo.a(6, new EntityAIVillagerMate(this));
        this.bo.a(7, new EntityAIFollowGolem(this));
        this.bo.a(8, new EntityAIPlay(this, 0.32F));
        this.bo.a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.bo.a(9, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
        this.bo.a(9, new EntityAIWander(this, 0.3F));
        this.bo.a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.entity = new CanaryVillager(this); // CanaryMod: Wrap Entity
    }

    public boolean bh() {
        return true;
    }

    protected void bp() {
        if (--this.e <= 0) {
            this.q.A.a(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w));
            this.e = 70 + this.ab.nextInt(50);
            this.d = this.q.A.a(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w), 32);
            if (this.d == null) {
                this.aO();
            } else {
                ChunkCoordinates chunkcoordinates = this.d.a();

                this.b(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, (int) ((float) this.d.b() * 0.6F));
                if (this.bN) {
                    this.bN = false;
                    this.d.b(5);
                }
            }
        }

        if (!this.p() && this.j > 0) {
            --this.j;
            if (this.j <= 0) {
                if (this.bK) {
                    if (this.i.size() > 1) {
                        Iterator iterator = this.i.iterator();

                        while (iterator.hasNext()) {
                            MerchantRecipe merchantrecipe = (MerchantRecipe) iterator.next();

                            if (merchantrecipe.g()) {
                                merchantrecipe.a(this.ab.nextInt(6) + this.ab.nextInt(6) + 2);
                            }
                        }
                    }

                    this.t(1);
                    this.bK = false;
                    if (this.d != null && this.bM != null) {
                        this.q.a((Entity) this, (byte) 14);
                        this.d.a(this.bM, 1);
                    }
                }

                this.d(new PotionEffect(Potion.l.H, 200, 0));
            }
        }

        super.bp();
    }

    public boolean a_(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bK.h();
        boolean flag0 = itemstack != null && itemstack.c == Item.bD.cp;

        if (!flag0 && this.R() && !this.p() && !this.h_()) {
            if (!this.q.I) {
                this.a(entityplayer);
                entityplayer.a((IMerchant) this, this.bP());
            }

            return true;
        } else {
            return super.a_(entityplayer);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Integer.valueOf(0));
    }

    public int aW() {
        return maxHealth == 0 ? 20 : maxHealth; // CanaryMod: custom Max Health
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Profession", this.m());
        nbttagcompound.a("Riches", this.bL);
        if (this.i != null) {
            nbttagcompound.a("Offers", this.i.a());
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.s(nbttagcompound.e("Profession"));
        this.bL = nbttagcompound.e("Riches");
        if (nbttagcompound.b("Offers")) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.l("Offers");

            this.i = new MerchantRecipeList(nbttagcompound1);
        }
    }

    protected boolean bm() {
        return false;
    }

    protected String bb() {
        return "mob.villager.default";
    }

    protected String bc() {
        return "mob.villager.defaulthurt";
    }

    protected String bd() {
        return "mob.villager.defaultdeath";
    }

    public void s(int i0) {
        this.ah.b(16, Integer.valueOf(i0));
    }

    public int m() {
        return this.ah.c(16);
    }

    public boolean n() {
        return this.f;
    }

    public void i(boolean flag0) {
        this.f = flag0;
    }

    public void j(boolean flag0) {
        this.g = flag0;
    }

    public boolean o() {
        return this.g;
    }

    public void c(EntityLiving entityliving) {
        super.c(entityliving);
        if (this.d != null && entityliving != null) {
            this.d.a(entityliving);
            if (entityliving instanceof EntityPlayer) {
                byte b0 = -1;

                if (this.h_()) {
                    b0 = -3;
                }

                this.d.a(((EntityPlayer) entityliving).c_(), b0);
                if (this.R()) {
                    this.q.a((Entity) this, (byte) 13);
                }
            }
        }
    }

    public void a(DamageSource damagesource) {
        if (this.d != null) {
            Entity entity = damagesource.i();

            if (entity != null) {
                if (entity instanceof EntityPlayer) {
                    this.d.a(((EntityPlayer) entity).c_(), -2);
                } else if (entity instanceof IMob) {
                    this.d.h();
                }
            } else if (entity == null) {
                EntityPlayer entityplayer = this.q.a(this, 16.0D);

                if (entityplayer != null) {
                    this.d.h();
                }
            }
        }

        super.a(damagesource);
    }

    public void a(EntityPlayer entityplayer) {
        this.h = entityplayer;
    }

    public EntityPlayer m_() {
        return this.h;
    }

    public boolean p() {
        return this.h != null;
    }

    public void a(MerchantRecipe merchantrecipe) {
        merchantrecipe.f();
        if (merchantrecipe.a((MerchantRecipe) this.i.get(this.i.size() - 1))) {
            this.j = 40;
            this.bK = true;
            if (this.h != null) {
                this.bM = this.h.c_();
            } else {
                this.bM = null;
            }
        }

        if (merchantrecipe.a().c == Item.bI.cp) {
            this.bL += merchantrecipe.a().a;
        }
    }

    public MerchantRecipeList b(EntityPlayer entityplayer) {
        if (this.i == null) {
            this.t(1);
        }

        return this.i;
    }

    private float j(float f0) {
        float f1 = f0 + this.bO;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void t(int i0) {
        if (this.i != null) {
            this.bO = MathHelper.c((float) this.i.size()) * 0.2F;
        } else {
            this.bO = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;

        merchantrecipelist = new MerchantRecipeList();
        int i1;

        label50:
        switch (this.m()) {
            case 0:
                a(merchantrecipelist, Item.U.cp, this.ab, this.j(0.9F));
                a(merchantrecipelist, Block.af.cz, this.ab, this.j(0.5F));
                a(merchantrecipelist, Item.bl.cp, this.ab, this.j(0.5F));
                a(merchantrecipelist, Item.aW.cp, this.ab, this.j(0.4F));
                b(merchantrecipelist, Item.V.cp, this.ab, this.j(0.9F));
                b(merchantrecipelist, Item.bg.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.k.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.bd.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.bf.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.j.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.bm.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.m.cp, this.ab, this.j(0.5F));
                if (this.ab.nextFloat() < this.j(0.5F)) {
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(Block.J, 10), new ItemStack(Item.bI), new ItemStack(Item.aq.cp, 4 + this.ab.nextInt(2), 0)));
                }
                break;

            case 1:
                a(merchantrecipelist, Item.aL.cp, this.ab, this.j(0.8F));
                a(merchantrecipelist, Item.aM.cp, this.ab, this.j(0.8F));
                a(merchantrecipelist, Item.bH.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Block.ar.cz, this.ab, this.j(0.8F));
                b(merchantrecipelist, Block.Q.cz, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.aR.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.aT.cp, this.ab, this.j(0.2F));
                if (this.ab.nextFloat() < this.j(0.07F)) {
                    Enchantment enchantment = Enchantment.c[this.ab.nextInt(Enchantment.c.length)];
                    int i2 = MathHelper.a(this.ab, enchantment.d(), enchantment.b());
                    ItemStack itemstack = Item.bX.a(new EnchantmentData(enchantment, i2));

                    i1 = 2 + this.ab.nextInt(5 + i2 * 10) + 3 * i2;
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(Item.aM), new ItemStack(Item.bI, i1), itemstack));
                }
                break;

            case 2:
                b(merchantrecipelist, Item.bB.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.bE.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.aD.cp, this.ab, this.j(0.4F));
                b(merchantrecipelist, Block.bh.cz, this.ab, this.j(0.3F));
                int[] aint = new int[] { Item.r.cp, Item.A.cp, Item.af.cp, Item.aj.cp, Item.i.cp, Item.D.cp, Item.h.cp, Item.C.cp};
                int[] aint1 = aint;
                int i3 = aint.length;

                i1 = 0;

                while (true) {
                    if (i1 >= i3) {
                        break label50;
                    }

                    int i4 = aint1[i1];

                    if (this.ab.nextFloat() < this.j(0.05F)) {
                        merchantrecipelist.add(new MerchantRecipe(new ItemStack(i4, 1, 0), new ItemStack(Item.bI, 2 + this.ab.nextInt(3), 0), EnchantmentHelper.a(this.ab, new ItemStack(i4, 1, 0), 5 + this.ab.nextInt(15))));
                    }

                    ++i1;
                }

            case 3:
                a(merchantrecipelist, Item.n.cp, this.ab, this.j(0.7F));
                a(merchantrecipelist, Item.p.cp, this.ab, this.j(0.5F));
                a(merchantrecipelist, Item.q.cp, this.ab, this.j(0.5F));
                a(merchantrecipelist, Item.o.cp, this.ab, this.j(0.5F));
                b(merchantrecipelist, Item.r.cp, this.ab, this.j(0.5F));
                b(merchantrecipelist, Item.A.cp, this.ab, this.j(0.5F));
                b(merchantrecipelist, Item.i.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.D.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.h.cp, this.ab, this.j(0.5F));
                b(merchantrecipelist, Item.C.cp, this.ab, this.j(0.5F));
                b(merchantrecipelist, Item.g.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.B.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.Q.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.R.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.ah.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.al.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.ae.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.ai.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.af.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.aj.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.ag.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.ak.cp, this.ab, this.j(0.2F));
                b(merchantrecipelist, Item.ad.cp, this.ab, this.j(0.1F));
                b(merchantrecipelist, Item.aa.cp, this.ab, this.j(0.1F));
                b(merchantrecipelist, Item.ab.cp, this.ab, this.j(0.1F));
                b(merchantrecipelist, Item.ac.cp, this.ab, this.j(0.1F));
                break;

            case 4:
                a(merchantrecipelist, Item.n.cp, this.ab, this.j(0.7F));
                a(merchantrecipelist, Item.ar.cp, this.ab, this.j(0.5F));
                a(merchantrecipelist, Item.bj.cp, this.ab, this.j(0.5F));
                b(merchantrecipelist, Item.aB.cp, this.ab, this.j(0.1F));
                b(merchantrecipelist, Item.X.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.Z.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.W.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.Y.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.as.cp, this.ab, this.j(0.3F));
                b(merchantrecipelist, Item.bk.cp, this.ab, this.j(0.3F));
        }

        if (merchantrecipelist.isEmpty()) {
            a(merchantrecipelist, Item.q.cp, this.ab, 1.0F);
        }

        Collections.shuffle(merchantrecipelist);
        if (this.i == null) {
            this.i = new MerchantRecipeList();
        }

        for (int i5 = 0; i5 < i0 && i5 < merchantrecipelist.size(); ++i5) {
            MerchantRecipe recipe = (MerchantRecipe) merchantrecipelist.get(i5);
            // CanaryMod: VillagerTradeUnlock
            VillagerTradeUnlockHook hook = new VillagerTradeUnlockHook((Villager) getCanaryEntity(), new CanaryVillagerTrade(recipe));

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                this.i.a(recipe);
            }
            //
        }
    }

    private static void a(MerchantRecipeList merchantrecipelist, int i0, Random random, float f0) {
        if (random.nextFloat() < f0) {
            merchantrecipelist.add(new MerchantRecipe(a(i0, random), Item.bI));
        }
    }

    private static ItemStack a(int i0, Random random) {
        return new ItemStack(i0, b(i0, random), 0);
    }

    private static int b(int i0, Random random) {
        Tuple tuple = (Tuple) bP.get(Integer.valueOf(i0));

        return tuple == null ? 1 : (((Integer) tuple.a()).intValue() >= ((Integer) tuple.b()).intValue() ? ((Integer) tuple.a()).intValue() : ((Integer) tuple.a()).intValue() + random.nextInt(((Integer) tuple.b()).intValue() - ((Integer) tuple.a()).intValue()));
    }

    private static void b(MerchantRecipeList merchantrecipelist, int i0, Random random, float f0) {
        if (random.nextFloat() < f0) {
            int i1 = c(i0, random);
            ItemStack itemstack;
            ItemStack itemstack1;

            if (i1 < 0) {
                itemstack = new ItemStack(Item.bI.cp, 1, 0);
                itemstack1 = new ItemStack(i0, -i1, 0);
            } else {
                itemstack = new ItemStack(Item.bI.cp, i1, 0);
                itemstack1 = new ItemStack(i0, 1, 0);
            }

            merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
        }
    }

    private static int c(int i0, Random random) {
        Tuple tuple = (Tuple) bQ.get(Integer.valueOf(i0));

        return tuple == null ? 1 : (((Integer) tuple.a()).intValue() >= ((Integer) tuple.b()).intValue() ? ((Integer) tuple.a()).intValue() : ((Integer) tuple.a()).intValue() + random.nextInt(((Integer) tuple.b()).intValue() - ((Integer) tuple.a()).intValue()));
    }

    public void bJ() {
        this.s(this.q.s.nextInt(5));
    }

    public void q() {
        this.bN = true;
    }

    public EntityVillager b(EntityAgeable entityageable) {
        EntityVillager entityvillager = new EntityVillager(this.q);

        entityvillager.bJ();
        return entityvillager;
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }

    static {
        bP.put(Integer.valueOf(Item.n.cp), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        bP.put(Integer.valueOf(Item.p.cp), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bP.put(Integer.valueOf(Item.q.cp), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bP.put(Integer.valueOf(Item.o.cp), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bP.put(Integer.valueOf(Item.aL.cp), new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
        bP.put(Integer.valueOf(Item.aM.cp), new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
        bP.put(Integer.valueOf(Item.bH.cp), new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
        bP.put(Integer.valueOf(Item.bo.cp), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bP.put(Integer.valueOf(Item.bB.cp), new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
        bP.put(Integer.valueOf(Item.ar.cp), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(Item.bj.cp), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(Item.bl.cp), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(Item.aW.cp), new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
        bP.put(Integer.valueOf(Item.T.cp), new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
        bP.put(Integer.valueOf(Item.bi.cp), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bP.put(Integer.valueOf(Item.bh.cp), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bP.put(Integer.valueOf(Item.U.cp), new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
        bP.put(Integer.valueOf(Block.af.cz), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
        bP.put(Integer.valueOf(Item.bn.cp), new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
        bQ.put(Integer.valueOf(Item.j.cp), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.bf.cp), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.r.cp), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(Item.A.cp), new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(Item.i.cp), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.D.cp), new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Item.h.cp), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
        bQ.put(Integer.valueOf(Item.C.cp), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Item.g.cp), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.B.cp), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.Q.cp), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.R.cp), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.ah.cp), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.al.cp), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.ae.cp), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.ai.cp), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.af.cp), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(Item.aj.cp), new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
        bQ.put(Integer.valueOf(Item.ag.cp), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bQ.put(Integer.valueOf(Item.ak.cp), new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(Item.ad.cp), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bQ.put(Integer.valueOf(Item.aa.cp), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bQ.put(Integer.valueOf(Item.ab.cp), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
        bQ.put(Integer.valueOf(Item.ac.cp), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(Item.V.cp), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bQ.put(Integer.valueOf(Item.bg.cp), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bQ.put(Integer.valueOf(Item.k.cp), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bQ.put(Integer.valueOf(Item.bd.cp), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bQ.put(Integer.valueOf(Block.Q.cz), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bQ.put(Integer.valueOf(Block.ar.cz), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.X.cp), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
        bQ.put(Integer.valueOf(Item.Z.cp), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.W.cp), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.Y.cp), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.aB.cp), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.bE.cp), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(Item.aD.cp), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(Item.aR.cp), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Item.aT.cp), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Block.bh.cz), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(Item.as.cp), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bQ.put(Integer.valueOf(Item.bk.cp), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bQ.put(Integer.valueOf(Item.bm.cp), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bQ.put(Integer.valueOf(Item.bB.cp), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(Item.m.cp), new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
