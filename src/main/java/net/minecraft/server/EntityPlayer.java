package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.inventory.CanaryEnderChestInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.player.EntityRightClickHook;
import net.canarymod.hook.player.ItemDropHook;
import net.canarymod.hook.player.LevelUpHook;

public abstract class EntityPlayer extends EntityLivingBase implements ICommandSender {

    public InventoryPlayer bn = new InventoryPlayer(this);
    private InventoryEnderChest a = new InventoryEnderChest();
    public Container bo;
    public Container bp;
    protected FoodStats bq = new FoodStats(this); // CanaryMod: pass player
    protected int br;
    public float bs;
    public float bt;
    protected final String bu;
    public int bv;
    public double bw;
    public double bx;
    public double by;
    public double bz;
    public double bA;
    public double bB;
    protected boolean bC;
    public ChunkCoordinates bD;
    private int b;
    public float bE;
    public float bF;
    private ChunkCoordinates c;
    private boolean d;
    private ChunkCoordinates e;
    public PlayerCapabilities bG = new PlayerCapabilities();
    public int bH; // level
    public int bI; // total points
    public float bJ;
    private ItemStack f;
    private int g;
    protected float bK = 0.1F;
    protected float bL = 0.02F;
    private int h;
    public EntityFishHook bM;
    private String respawnWorld; // CanaryMod: Respawn world (for bed spawns)
    protected String dispName; // CanaryMod: Mojang screwed us from using the methods in EntityLiving...

    public EntityPlayer(World world, String s0) {
        super(world);
        this.bu = s0;
        this.bo = new ContainerPlayer(this.bn, !world.I, this);
        this.bp = this.bo;
        this.N = 1.62F;
        ChunkCoordinates chunkcoordinates = world.K();

        this.b((double) chunkcoordinates.a + 0.5D, (double) (chunkcoordinates.b + 1), (double) chunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.ba = 180.0F;
        this.ad = 20;
    }

    protected void ax() {
        super.ax();
        this.aT().b(SharedMonsterAttributes.e).a(1.0D);
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
        this.ah.a(17, Float.valueOf(0.0F));
        this.ah.a(18, Integer.valueOf(0));
    }

    public boolean bm() {
        return this.f != null;
    }

    public void bo() {
        if (this.f != null) {
            this.f.b(this.q, this, this.g);
        }

        this.bp();
    }

    public void bp() {
        this.f = null;
        this.g = 0;
        if (!this.q.I) {
            this.e(false);
        }
    }

    public boolean bq() {
        return this.bm() && Item.g[this.f.d].c_(this.f) == EnumAction.d;
    }

    public void l_() {
        if (this.f != null) {
            ItemStack itemstack = this.bn.h();

            if (itemstack == this.f) {
                if (this.g <= 25 && this.g % 4 == 0) {
                    this.c(itemstack, 5);
                }

                if (--this.g == 0 && !this.q.I) {
                    this.n();
                }
            } else {
                this.bp();
            }
        }

        if (this.bv > 0) {
            --this.bv;
        }

        if (this.bd()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }

            if (!this.q.I) {
                if (!this.h()) {
                    this.a(true, true, false);
                } else if (this.q.v()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.b > 0) {
            ++this.b;
            if (this.b >= 110) {
                this.b = 0;
            }
        }

        super.l_();
        if (!this.q.I && this.bp != null && !this.bp.a(this)) {
            this.i();
            this.bp = this.bo;
        }

        if (this.ad() && this.bG.a) {
            this.A();
        }

        this.bw = this.bz;
        this.bx = this.bA;
        this.by = this.bB;
        double d0 = this.u - this.bz;
        double d1 = this.v - this.bA;
        double d2 = this.w - this.bB;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.bw = this.bz = this.u;
        }

        if (d2 > d3) {
            this.by = this.bB = this.w;
        }

        if (d1 > d3) {
            this.bx = this.bA = this.v;
        }

        if (d0 < -d3) {
            this.bw = this.bz = this.u;
        }

        if (d2 < -d3) {
            this.by = this.bB = this.w;
        }

        if (d1 < -d3) {
            this.bx = this.bA = this.v;
        }

        this.bz += d0 * 0.25D;
        this.bB += d2 * 0.25D;
        this.bA += d1 * 0.25D;
        this.a(StatList.k, 1);
        if (this.o == null) {
            this.e = null;
        }

        if (!this.q.I) {
            this.bq.a(this);
        }
    }

    public int y() {
        return this.bG.a ? 0 : 80;
    }

    public int aa() {
        return 10;
    }

    public void a(String s0, float f0, float f1) {
        this.q.a(this, s0, f0, f1);
    }

    protected void c(ItemStack itemstack, int i0) {
        if (itemstack.o() == EnumAction.c) {
            this.a("random.drink", 0.5F, this.q.s.nextFloat() * 0.1F + 0.9F);
        }

        if (itemstack.o() == EnumAction.b) {
            for (int i1 = 0; i1 < i0; ++i1) {
                Vec3 vec3 = this.q.V().a(((double) this.ab.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                vec3.a(-this.B * 3.1415927F / 180.0F);
                vec3.b(-this.A * 3.1415927F / 180.0F);
                Vec3 vec31 = this.q.V().a(((double) this.ab.nextFloat() - 0.5D) * 0.3D, (double) (-this.ab.nextFloat()) * 0.6D - 0.3D, 0.6D);

                vec31.a(-this.B * 3.1415927F / 180.0F);
                vec31.b(-this.A * 3.1415927F / 180.0F);
                vec31 = vec31.c(this.u, this.v + (double) this.f(), this.w);
                this.q.a("iconcrack_" + itemstack.b().cv, vec31.c, vec31.d, vec31.e, vec3.c, vec3.d + 0.05D, vec3.e);
            }

            this.a("random.eat", 0.5F + 0.5F * (float) this.ab.nextInt(2), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void n() {
        if (this.f != null) {
            this.c(this.f, 16);
            int i0 = this.f.b;
            ItemStack itemstack = this.f.b(this.q, this);

            if (itemstack != this.f || itemstack != null && itemstack.b != i0) {
                this.bn.a[this.bn.c] = itemstack;
                if (itemstack.b == 0) {
                    this.bn.a[this.bn.c] = null;
                }
            }

            this.bp();
        }
    }

    protected boolean aY() {
        return this.aJ() <= 0.0F || this.bd();
    }

    protected void i() {
        this.bp = this.bo;
    }

    public void a(Entity entity) {
        if (this.o != null && entity == null) {
            if (!this.q.I) {
                this.l(this.o);
            }

            if (this.o != null) {
                this.o.n = null;
            }

            this.o = null;
        } else {
            super.a(entity);
        }
    }

    public void T() {
        if (!this.q.I && this.af()) {
            this.a((Entity) null);
            this.b(false);
        } else {
            double d0 = this.u;
            double d1 = this.v;
            double d2 = this.w;
            float f0 = this.A;
            float f1 = this.B;

            super.T();
            this.bs = this.bt;
            this.bt = 0.0F;
            this.k(this.u - d0, this.v - d1, this.w - d2);
            if (this.o instanceof EntityPig) {
                this.B = f1;
                this.A = f0;
                this.aN = ((EntityPig) this.o).aN;
            }
        }
    }

    protected void bh() {
        super.bh();
        this.aS();
    }

    public void c() {
        if (this.br > 0) {
            --this.br;
        }

        if (this.q.r == 0 && this.aJ() < this.aP() && this.q.O().b("naturalRegeneration") && this.ac % 20 * 12 == 0) {
            this.f(1.0F);
        }

        this.bn.k();
        this.bs = this.bt;
        super.c();
        AttributeInstance attributeinstance = this.a(SharedMonsterAttributes.d);

        if (!this.q.I) {
            attributeinstance.a((double) this.bG.b());
        }

        this.aR = this.bL;
        if (this.ag()) {
            this.aR = (float) ((double) this.aR + (double) this.bL * 0.3D);
        }

        this.i((float) attributeinstance.e());
        float f0 = MathHelper.a(this.x * this.x + this.z * this.z);
        float f1 = (float) Math.atan(-this.y * 0.20000000298023224D) * 15.0F;

        if (f0 > 0.1F) {
            f0 = 0.1F;
        }

        if (!this.F || this.aJ() <= 0.0F) {
            f0 = 0.0F;
        }

        if (this.F || this.aJ() <= 0.0F) {
            f1 = 0.0F;
        }

        this.bt += (f0 - this.bt) * 0.4F;
        this.aK += (f1 - this.aK) * 0.8F;
        if (this.aJ() > 0.0F) {
            AxisAlignedBB axisalignedbb = null;

            if (this.o != null && !this.o.M) {
                axisalignedbb = this.E.a(this.o.E).b(1.0D, 0.0D, 1.0D);
            } else {
                axisalignedbb = this.E.b(1.0D, 0.5D, 1.0D);
            }

            List list = this.q.b((Entity) this, axisalignedbb);

            if (list != null) {
                for (int i0 = 0; i0 < list.size(); ++i0) {
                    Entity entity = (Entity) list.get(i0);

                    if (!entity.M) {
                        this.r(entity);
                    }
                }
            }
        }
    }

    private void r(Entity entity) {
        entity.b_(this);
    }

    public int br() {
        return this.ah.c(18);
    }

    public void c(int i0) {
        this.ah.b(18, Integer.valueOf(i0));
    }

    public void p(int i0) {
        int i1 = this.br();

        this.ah.b(18, Integer.valueOf(i1 + i0));
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        this.a(0.2F, 0.2F);
        this.b(this.u, this.v, this.w);
        this.y = 0.10000000149011612D;
        if (this.bu.equals("Notch")) {
            this.a(new ItemStack(Item.l, 1), true);
        }

        if (!this.q.O().b("keepInventory")) {
            this.bn.m();
        }

        if (damagesource != null) {
            this.x = (double) (-MathHelper.b((this.aA + this.A) * 3.1415927F / 180.0F) * 0.1F);
            this.z = (double) (-MathHelper.a((this.aA + this.A) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.x = this.z = 0.0D;
        }

        this.N = 0.1F;
        this.a(StatList.y, 1);
    }

    public void b(Entity entity, int i0) {
        this.p(i0);
        Collection collection = this.bH().a(ScoreObjectiveCriteria.e);

        if (entity instanceof EntityPlayer) {
            this.a(StatList.A, 1);
            collection.addAll(this.bH().a(ScoreObjectiveCriteria.d));
        } else {
            this.a(StatList.z, 1);
        }

        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreObjective scoreobjective = (ScoreObjective) iterator.next();
            Score score = this.bH().a(this.al(), scoreobjective);

            score.a();
        }
    }

    public EntityItem a(boolean flag0) {
        return this.a(this.bn.a(this.bn.c, flag0 && this.bn.h() != null ? this.bn.h().b : 1), false);
    }

    public EntityItem b(ItemStack itemstack) {
        return this.a(itemstack, false);
    }

    public EntityItem a(ItemStack itemstack, boolean flag0) {
        if (itemstack == null) {
            return null;
        } else if (itemstack.b == 0) {
            return null;
        } else {
            EntityItem entityitem = new EntityItem(this.q, this.u, this.v - 0.30000001192092896D + (double) this.f(), this.w, itemstack);

            entityitem.b = 40;
            float f0 = 0.1F;
            float f1;

            if (flag0) {
                f1 = this.ab.nextFloat() * 0.5F;
                float f2 = this.ab.nextFloat() * 3.1415927F * 2.0F;

                entityitem.x = (double) (-MathHelper.a(f2) * f1);
                entityitem.z = (double) (MathHelper.b(f2) * f1);
                entityitem.y = 0.20000000298023224D;
            } else {
                f0 = 0.3F;
                entityitem.x = (double) (-MathHelper.a(this.A / 180.0F * 3.1415927F) * MathHelper.b(this.B / 180.0F * 3.1415927F) * f0);
                entityitem.z = (double) (MathHelper.b(this.A / 180.0F * 3.1415927F) * MathHelper.b(this.B / 180.0F * 3.1415927F) * f0);
                entityitem.y = (double) (-MathHelper.a(this.B / 180.0F * 3.1415927F) * f0 + 0.1F);
                f0 = 0.02F;
                f1 = this.ab.nextFloat() * 3.1415927F * 2.0F;
                f0 *= this.ab.nextFloat();
                entityitem.x += Math.cos((double) f1) * (double) f0;
                entityitem.y += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                entityitem.z += Math.sin((double) f1) * (double) f0;
            }

            // CanaryMod: ItemDrop
            ItemDropHook hook = (ItemDropHook) new ItemDropHook(((EntityPlayerMP) this).getPlayer(), (net.canarymod.api.entity.EntityItem) entityitem.getCanaryEntity()).call();
            if (!hook.isCanceled()) {
                CanaryItem droppedItem = entityitem.d().getCanaryItem();

                if (droppedItem.getAmount() < 0) {
                    droppedItem.setAmount(1);
                }
                this.a(entityitem);
                this.a(StatList.v, 1);
                return entityitem;
            } else {
                return null;
            }
            //
        }
    }

    protected void a(EntityItem entityitem) {
        this.q.d((Entity) entityitem);
    }

    public float a(Block block, boolean flag0) {
        float f0 = this.bn.a(block);

        if (f0 > 1.0F) {
            int i0 = EnchantmentHelper.c(this);
            ItemStack itemstack = this.bn.h();

            if (i0 > 0 && itemstack != null) {
                float f1 = (float) (i0 * i0 + 1);

                if (!itemstack.b(block) && f0 <= 1.0F) {
                    f0 += f1 * 0.08F;
                } else {
                    f0 += f1;
                }
            }
        }

        if (this.a(Potion.e)) {
            f0 *= 1.0F + (float) (this.b(Potion.e).c() + 1) * 0.2F;
        }

        if (this.a(Potion.f)) {
            f0 *= 1.0F - (float) (this.b(Potion.f).c() + 1) * 0.2F;
        }

        if (this.a(Material.h) && !EnchantmentHelper.h(this)) {
            f0 /= 5.0F;
        }

        if (!this.F) {
            f0 /= 5.0F;
        }

        return f0;
    }

    public boolean a(Block block) {
        return this.bn.b(block);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Inventory");

        this.bn.b(nbttaglist);
        this.bn.c = nbttagcompound.e("SelectedItemSlot");
        this.bC = nbttagcompound.n("Sleeping");
        this.b = nbttagcompound.d("SleepTimer");
        this.bJ = nbttagcompound.g("XpP");
        this.bH = nbttagcompound.e("XpLevel");
        this.bI = nbttagcompound.e("XpTotal");
        this.c(nbttagcompound.e("Score"));
        if (this.bC) {
            this.bD = new ChunkCoordinates(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w));
            this.a(true, true, false);
        }

        if (nbttagcompound.b("SpawnX") && nbttagcompound.b("SpawnY") && nbttagcompound.b("SpawnZ")) {
            this.c = new ChunkCoordinates(nbttagcompound.e("SpawnX"), nbttagcompound.e("SpawnY"), nbttagcompound.e("SpawnZ"));
            this.d = nbttagcompound.n("SpawnForced");
            // CanaryMod added respawn world
            this.respawnWorld = nbttagcompound.i("SpawnWorld");
        }

        this.bq.a(nbttagcompound);
        this.bG.b(nbttagcompound);
        if (nbttagcompound.b("EnderItems")) {
            NBTTagList nbttaglist1 = nbttagcompound.m("EnderItems");

            this.a.a(nbttaglist1);
        }
        if (nbttagcompound.b("CustomName")) {
            this.dispName = nbttagcompound.i("CustomName");
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.bn.a(new NBTTagList()));
        nbttagcompound.a("SelectedItemSlot", this.bn.c);
        nbttagcompound.a("Sleeping", this.bC);
        nbttagcompound.a("SleepTimer", (short) this.b);
        nbttagcompound.a("XpP", this.bJ);
        nbttagcompound.a("XpLevel", this.bH);
        nbttagcompound.a("XpTotal", this.bI);
        nbttagcompound.a("Score", this.br());
        if (this.c != null) {
            nbttagcompound.a("SpawnX", this.c.a);
            nbttagcompound.a("SpawnY", this.c.b);
            nbttagcompound.a("SpawnZ", this.c.c);
            nbttagcompound.a("SpawnForced", this.d);
            // CanaryMod add world fq name
            nbttagcompound.a("SpawnWorld", getCanaryWorld().getFqName());
        }

        this.bq.b(nbttagcompound);
        this.bG.a(nbttagcompound);
        nbttagcompound.a("EnderItems", (NBTBase) this.a.h());
        if (dispName != null) {
            nbttagcompound.a("CustomName", dispName);
        }
    }

    public void a(IInventory iinventory) {}

    public void a(TileEntityHopper tileentityhopper) {}

    public void a(EntityMinecartHopper entityminecarthopper) {}

    public void a(EntityHorse entityhorse, IInventory iinventory) {}

    public void a(int i0, int i1, int i2, String s0) {}

    public void c(int i0, int i1, int i2) {}

    public void b(int i0, int i1, int i2) {}

    public float f() {
        return 0.12F;
    }

    protected void d_() {
        this.N = 1.62F;
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ap()) {
            return false;
        } else if (this.bG.a && !damagesource.g()) {
            return false;
        } else {
            this.aV = 0;
            if (this.aJ() <= 0.0F) {
                return false;
            } else {
                if (this.bd() && !this.q.I) {
                    this.a(true, true, false);
                }

                if (damagesource.p()) {
                    if (this.q.r == 0) {
                        f0 = 0.0F;
                    }

                    if (this.q.r == 1) {
                        f0 = f0 / 2.0F + 1.0F;
                    }

                    if (this.q.r == 3) {
                        f0 = f0 * 3.0F / 2.0F;
                    }
                }

                if (f0 == 0.0F) {
                    return false;
                } else {
                    Entity entity = damagesource.i();

                    if (entity instanceof EntityArrow && ((EntityArrow) entity).c != null) {
                        entity = ((EntityArrow) entity).c;
                    }

                    if (entity instanceof EntityLivingBase) {
                        this.a((EntityLivingBase) entity, false);
                    }

                    this.a(StatList.x, Math.round(f0 * 10.0F));
                    return super.a(damagesource, f0);
                }
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        ScorePlayerTeam scoreplayerteam = this.bI();
        ScorePlayerTeam scoreplayerteam1 = entityplayer.bI();

        return scoreplayerteam != scoreplayerteam1 ? true : (scoreplayerteam != null ? scoreplayerteam.g() : true);
    }

    protected void a(EntityLivingBase entitylivingbase, boolean flag0) {
        if (!(entitylivingbase instanceof EntityCreeper) && !(entitylivingbase instanceof EntityGhast)) {
            if (entitylivingbase instanceof EntityWolf) {
                EntityWolf entitywolf = (EntityWolf) entitylivingbase;

                if (entitywolf.bP() && this.bu.equals(entitywolf.h_())) {
                    return;
                }
            }

            if (!(entitylivingbase instanceof EntityPlayer) || this.a((EntityPlayer) entitylivingbase)) {
                if (!(entitylivingbase instanceof EntityHorse) || !((EntityHorse) entitylivingbase).bS()) {
                    List list = this.q.a(EntityWolf.class, AxisAlignedBB.a().a(this.u, this.v, this.w, this.u + 1.0D, this.v + 1.0D, this.w + 1.0D).b(16.0D, 4.0D, 16.0D));
                    Iterator iterator = list.iterator();

                    while (iterator.hasNext()) {
                        EntityWolf entitywolf1 = (EntityWolf) iterator.next();

                        if (entitywolf1.bP() && entitywolf1.bJ() == null && this.bu.equals(entitywolf1.h_()) && (!flag0 || !entitywolf1.bQ())) {
                            entitywolf1.l(false);
                            entitywolf1.b((Entity) entitylivingbase);
                        }
                    }
                }
            }
        }
    }

    protected void h(float f0) {
        this.bn.a(f0);
    }

    public int aM() {
        return this.bn.l();
    }

    public float bs() {
        int i0 = 0;
        ItemStack[] aitemstack = this.bn.b;
        int i1 = aitemstack.length;

        for (int i2 = 0; i2 < i1; ++i2) {
            ItemStack itemstack = aitemstack[i2];

            if (itemstack != null) {
                ++i0;
            }
        }

        return (float) i0 / (float) this.bn.b.length;
    }

    protected void d(DamageSource damagesource, float f0) {
        if (!this.ap()) {
            if (!damagesource.e() && this.bq() && f0 > 0.0F) {
                f0 = (1.0F + f0) * 0.5F;
            }

            f0 = this.b(damagesource, f0);
            f0 = this.c(damagesource, f0);
            float f1 = f0;

            f0 = Math.max(f0 - this.bj(), 0.0F);
            this.m(this.bj() - (f1 - f0));
            if (f0 != 0.0F) {
                this.a(damagesource.f());
                float f2 = this.aJ();

                this.g(this.aJ() - f0);
                this.aN().a(damagesource, f2, f0);
            }
        }
    }

    public void a(TileEntityFurnace tileentityfurnace) {}

    public void a(TileEntityDispenser tileentitydispenser) {}

    public void a(TileEntity tileentity) {}

    public void a(TileEntityBrewingStand tileentitybrewingstand) {}

    public void a(TileEntityBeacon tileentitybeacon) {}

    public void a(IMerchant imerchant, String s0) {}

    public void c(ItemStack itemstack) {}

    public boolean p(Entity entity) {
        ItemStack itemstack = this.bt();
        ItemStack itemstack1 = itemstack != null ? itemstack.m() : null;
        if (entity.c(this)) {
            return true;
        } else {
            // CanaryMod: EntityRightClickHook
            EntityRightClickHook hook = (EntityRightClickHook) new EntityRightClickHook(entity.getCanaryEntity(), ((EntityPlayerMP) this).getPlayer()).call();
            if (hook.isCanceled()) {
                return false;
            }
            //
            if (itemstack != null && entity instanceof EntityLivingBase) {
                if (this.bG.d) {
                    itemstack = itemstack1;
                }

                if (itemstack.a(this, (EntityLivingBase) entity)) {
                    if (itemstack.b <= 0 && !this.bG.d) {
                        this.bu();
                    }

                    return true;
                }
            }
            if (itemstack != null && itemstack == this.bt()) {
                if (itemstack.b <= 0 && !this.bG.d) {
                    this.bu();
                } else if (itemstack.b < itemstack1.b && this.bG.d) {
                    itemstack.b = itemstack1.b;
                }
            }

            return true;
        }
    }

    public ItemStack bt() {
        return this.bn.h();
    }

    public void bu() {
        this.bn.a(this.bn.c, (ItemStack) null);
    }

    public double V() {
        return (double) (this.N - 0.5F);
    }

    public void q(Entity entity) {
        if (entity.ao()) {
            if (!entity.i(this)) {
                float f0 = (float) this.a(SharedMonsterAttributes.e).e();
                int i0 = 0;
                float f1 = 0.0F;

                if (entity instanceof EntityLivingBase) {
                    f1 = EnchantmentHelper.a((EntityLivingBase) this, (EntityLivingBase) entity);
                    i0 += EnchantmentHelper.b(this, (EntityLivingBase) entity);
                }

                if (this.ag()) {
                    ++i0;
                }

                if (f0 > 0.0F || f1 > 0.0F) {
                    boolean flag0 = this.T > 0.0F && !this.F && !this.e() && !this.G() && !this.a(Potion.q) && this.o == null && entity instanceof EntityLivingBase;

                    if (flag0 && f0 > 0.0F) {
                        f0 *= 1.5F;
                    }

                    f0 += f1;
                    boolean flag1 = false;
                    int i1 = EnchantmentHelper.a((EntityLivingBase) this);

                    if (entity instanceof EntityLivingBase && i1 > 0 && !entity.ad()) {
                        flag1 = true;
                        entity.d(1);
                    }

                    boolean flag2 = entity.a(DamageSource.a(this), f0);

                    if (flag2) {
                        if (i0 > 0) {
                            entity.g((double) (-MathHelper.a(this.A * 3.1415927F / 180.0F) * (float) i0 * 0.5F), 0.1D, (double) (MathHelper.b(this.A * 3.1415927F / 180.0F) * (float) i0 * 0.5F));
                            this.x *= 0.6D;
                            this.z *= 0.6D;
                            this.c(false);
                        }

                        if (flag0) {
                            this.b(entity);
                        }

                        if (f1 > 0.0F) {
                            this.c(entity);
                        }

                        if (f0 >= 18.0F) {
                            this.a((StatBase) AchievementList.E);
                        }

                        this.k(entity);
                        if (entity instanceof EntityLivingBase) {
                            EnchantmentThorns.a(this, (EntityLivingBase) entity, this.ab);
                        }
                    }

                    ItemStack itemstack = this.bt();
                    Object object = entity;

                    if (entity instanceof EntityDragonPart) {
                        IEntityMultiPart ientitymultipart = ((EntityDragonPart) entity).a;

                        if (ientitymultipart != null && ientitymultipart instanceof EntityLivingBase) {
                            object = (EntityLivingBase) ientitymultipart;
                        }
                    }

                    if (itemstack != null && object instanceof EntityLivingBase) {
                        itemstack.a((EntityLivingBase) object, this);
                        if (itemstack.b <= 0) {
                            this.bu();
                        }
                    }

                    if (entity instanceof EntityLivingBase) {
                        if (entity.R()) {
                            this.a((EntityLivingBase) entity, true);
                        }

                        this.a(StatList.w, Math.round(f0 * 10.0F));
                        if (i1 > 0 && flag2) {
                            entity.d(i1 * 4);
                        } else if (flag1) {
                            entity.A();
                        }
                    }

                    this.a(0.3F);
                }
            }
        }
    }

    public void b(Entity entity) {}

    public void c(Entity entity) {}

    public void w() {
        super.w();
        this.bo.b(this);
        if (this.bp != null) {
            this.bp.b(this);
        }
    }

    public boolean S() {
        return !this.bC && super.S();
    }

    public EnumStatus a(int i0, int i1, int i2) {
        if (!this.q.I) {
            if (this.bd() || !this.R()) {
                return EnumStatus.e;
            }

            if (!this.q.t.d()) {
                return EnumStatus.b;
            }

            if (this.q.v()) {
                return EnumStatus.c;
            }

            if (Math.abs(this.u - (double) i0) > 3.0D || Math.abs(this.v - (double) i1) > 2.0D || Math.abs(this.w - (double) i2) > 3.0D) {
                return EnumStatus.d;
            }

            double d0 = 8.0D;
            double d1 = 5.0D;
            List list = this.q.a(EntityMob.class, AxisAlignedBB.a().a((double) i0 - d0, (double) i1 - d1, (double) i2 - d0, (double) i0 + d0, (double) i1 + d1, (double) i2 + d0));

            if (!list.isEmpty()) {
                return EnumStatus.f;
            }
        }

        if (this.ae()) {
            this.a((Entity) null);
        }

        this.a(0.2F, 0.2F);
        this.N = 0.2F;
        if (this.q.f(i0, i1, i2)) {
            int i3 = this.q.h(i0, i1, i2);
            int i4 = BlockBed.j(i3);
            float f0 = 0.5F;
            float f1 = 0.5F;

            switch (i4) {
                case 0:
                    f1 = 0.9F;
                    break;

                case 1:
                    f0 = 0.1F;
                    break;

                case 2:
                    f1 = 0.1F;
                    break;

                case 3:
                    f0 = 0.9F;
            }

            this.t(i4);
            this.b((double) ((float) i0 + f0), (double) ((float) i1 + 0.9375F), (double) ((float) i2 + f1));
        } else {
            this.b((double) ((float) i0 + 0.5F), (double) ((float) i1 + 0.9375F), (double) ((float) i2 + 0.5F));
        }

        this.bC = true;
        this.b = 0;
        this.bD = new ChunkCoordinates(i0, i1, i2);
        this.x = this.z = this.y = 0.0D;
        if (!this.q.I) {
            this.q.c();
        }

        return EnumStatus.a;
    }

    private void t(int i0) {
        this.bE = 0.0F;
        this.bF = 0.0F;
        switch (i0) {
            case 0:
                this.bF = -1.8F;
                break;

            case 1:
                this.bE = 1.8F;
                break;

            case 2:
                this.bF = 1.8F;
                break;

            case 3:
                this.bE = -1.8F;
        }
    }

    public void a(boolean flag0, boolean flag1, boolean flag2) {
        this.a(0.6F, 1.8F);
        this.d_();
        ChunkCoordinates chunkcoordinates = this.bD;
        ChunkCoordinates chunkcoordinates1 = this.bD;

        if (chunkcoordinates != null && this.q.a(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c) == Block.X.cF) {
            BlockBed.a(this.q, chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, false);
            chunkcoordinates1 = BlockBed.b(this.q, chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, 0);
            if (chunkcoordinates1 == null) {
                chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.a, chunkcoordinates.b + 1, chunkcoordinates.c);
            }

            this.b((double) ((float) chunkcoordinates1.a + 0.5F), (double) ((float) chunkcoordinates1.b + this.N + 0.1F), (double) ((float) chunkcoordinates1.c + 0.5F));
        }

        this.bC = false;
        if (!this.q.I && flag1) {
            this.q.c();
        }

        if (flag0) {
            this.b = 0;
        } else {
            this.b = 100;
        }

        if (flag2) {
            this.a(this.bD, false);
        }
    }

    private boolean h() {
        return this.q.a(this.bD.a, this.bD.b, this.bD.c) == Block.X.cF;
    }

    public static ChunkCoordinates a(World world, ChunkCoordinates chunkcoordinates, boolean flag0) {
        IChunkProvider ichunkprovider = world.L();

        ichunkprovider.c(chunkcoordinates.a - 3 >> 4, chunkcoordinates.c - 3 >> 4);
        ichunkprovider.c(chunkcoordinates.a + 3 >> 4, chunkcoordinates.c - 3 >> 4);
        ichunkprovider.c(chunkcoordinates.a - 3 >> 4, chunkcoordinates.c + 3 >> 4);
        ichunkprovider.c(chunkcoordinates.a + 3 >> 4, chunkcoordinates.c + 3 >> 4);
        if (world.a(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c) == Block.X.cF) { // Bed spawn
            ChunkCoordinates chunkcoordinates1 = BlockBed.b(world, chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, 0);

            return chunkcoordinates1;
        } else { // World spawn
            Material material = world.g(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c);
            Material material1 = world.g(chunkcoordinates.a, chunkcoordinates.b + 1, chunkcoordinates.c);
            boolean flag1 = !material.a() && !material.d();
            boolean flag2 = !material1.a() && !material1.d();

            return flag0 && flag1 && flag2 ? chunkcoordinates : null;
        }
    }

    public boolean bd() {
        return this.bC;
    }

    public boolean by() {
        return this.bC && this.b >= 100;
    }

    protected void b(int i0, boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1 << i0)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & ~(1 << i0))));
        }
    }

    public void a(String s0) {}

    public ChunkCoordinates bA() {
        return this.c;
    }

    public boolean bB() {
        return this.d;
    }

    public void a(ChunkCoordinates chunkcoordinates, boolean flag0) {
        if (chunkcoordinates != null) {
            this.c = new ChunkCoordinates(chunkcoordinates);
            this.d = flag0;
        } else {
            this.c = null;
            this.d = false;
        }
    }

    public void a(StatBase statbase) {
        this.a(statbase, 1);
    }

    public void a(StatBase statbase, int i0) {}

    protected void ba() {
        super.ba();
        this.a(StatList.u, 1);
        if (this.ag()) {
            this.a(0.8F);
        } else {
            this.a(0.2F);
        }
    }

    public void e(float f0, float f1) {
        double d0 = this.u;
        double d1 = this.v;
        double d2 = this.w;

        if (this.bG.b && this.o == null) {
            double d3 = this.y;
            float f2 = this.aR;

            this.aR = this.bG.a();
            super.e(f0, f1);
            this.y = d3 * 0.6D;
            this.aR = f2;
        } else {
            super.e(f0, f1);
        }

        this.j(this.u - d0, this.v - d1, this.w - d2);
    }

    public float bc() {
        return (float) this.a(SharedMonsterAttributes.d).e();
    }

    public void j(double d0, double d1, double d2) {
        if (this.o == null) {
            int i0;

            if (this.a(Material.h)) {
                i0 = Math.round(MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
                if (i0 > 0) {
                    this.a(StatList.q, i0);
                    this.a(0.015F * (float) i0 * 0.01F);
                }
            } else if (this.G()) {
                i0 = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i0 > 0) {
                    this.a(StatList.m, i0);
                    this.a(0.015F * (float) i0 * 0.01F);
                }
            } else if (this.e()) {
                if (d1 > 0.0D) {
                    this.a(StatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.F) {
                i0 = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i0 > 0) {
                    this.a(StatList.l, i0);
                    if (this.ag()) {
                        this.a(0.099999994F * (float) i0 * 0.01F);
                    } else {
                        this.a(0.01F * (float) i0 * 0.01F);
                    }
                }
            } else {
                i0 = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i0 > 25) {
                    this.a(StatList.p, i0);
                }
            }
        }
    }

    private void k(double d0, double d1, double d2) {
        if (this.o != null) {
            int i0 = Math.round(MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);

            if (i0 > 0) {
                if (this.o instanceof EntityMinecart) {
                    this.a(StatList.r, i0);
                    if (this.e == null) {
                        this.e = new ChunkCoordinates(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w));
                    } else if ((double) this.e.e(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) >= 1000000.0D) {
                        this.a((StatBase) AchievementList.q, 1);
                    }
                } else if (this.o instanceof EntityBoat) {
                    this.a(StatList.s, i0);
                } else if (this.o instanceof EntityPig) {
                    this.a(StatList.t, i0);
                }
            }
        }
    }

    protected void b(float f0) {
        if (!this.bG.c) {
            if (f0 >= 2.0F) {
                this.a(StatList.n, (int) Math.round((double) f0 * 100.0D));
            }

            super.b(f0);
        }
    }

    public void a(EntityLivingBase entitylivingbase) {
        if (entitylivingbase instanceof IMob) {
            this.a((StatBase) AchievementList.s);
        }
    }

    public void ak() {
        if (!this.bG.b) {
            super.ak();
        }
    }

    public ItemStack o(int i0) {
        return this.bn.f(i0);
    }

    public void s(int i0) {
        this.p(i0);
        int i1 = Integer.MAX_VALUE - this.bI;

        if (i0 > i1) {
            i0 = i1;
        }

        this.bJ += (float) i0 / (float) this.bC();

        for (this.bI += i0; this.bJ >= 1.0F; this.bJ /= (float) this.bC()) {
            this.bJ = (this.bJ - 1.0F) * (float) this.bC();
            this.a(1);
        }
    }

    public void a(int i0) {
        // CanaryMod: LevelUpHook
        new LevelUpHook(((EntityPlayerMP) this).getPlayer()).call();
        //
        this.bH += i0;
        if (this.bH < 0) {
            this.bH = 0;
            this.bJ = 0.0F;
            this.bI = 0;
        }

        if (i0 > 0 && this.bH % 5 == 0 && (float) this.h < (float) this.ac - 100.0F) {
            float f0 = this.bH > 30 ? 1.0F : (float) this.bH / 30.0F;

            this.q.a((Entity) this, "random.levelup", f0 * 0.75F, 1.0F);
            this.h = this.ac;
        }
    }

    public int bC() {
        return this.bH >= 30 ? 62 + (this.bH - 30) * 7 : (this.bH >= 15 ? 17 + (this.bH - 15) * 3 : 17);
    }

    public void a(float f0) {
        if (!this.bG.a) {
            if (!this.q.I) {
                this.bq.a(f0);
            }
        }
    }

    public FoodStats bD() {
        return this.bq;
    }

    public boolean g(boolean flag0) {
        return (flag0 || this.bq.c()) && !this.bG.a;
    }

    public boolean bE() {
        return this.aJ() > 0.0F && this.aJ() < this.aP();
    }

    public void a(ItemStack itemstack, int i0) {
        if (itemstack != this.f) {
            this.f = itemstack;
            this.g = i0;
            if (!this.q.I) {
                this.e(true);
            }
        }
    }

    public boolean d(int i0, int i1, int i2) {
        if (this.bG.e) {
            return true;
        } else {
            int i3 = this.q.a(i0, i1, i2);

            if (i3 > 0) {
                Block block = Block.s[i3];

                if (block.cU.q()) {
                    return true;
                }

                if (this.bt() != null) {
                    ItemStack itemstack = this.bt();

                    if (itemstack.b(block) || itemstack.a(block) > 1.0F) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean a(int i0, int i1, int i2, int i3, ItemStack itemstack) {
        return this.bG.e ? true : (itemstack != null ? itemstack.z() : false);
    }

    protected int e(EntityPlayer entityplayer) {
        if (this.q.O().b("keepInventory")) {
            return 0;
        } else {
            int i0 = this.bH * 7;

            return i0 > 100 ? 100 : i0;
        }
    }

    protected boolean aA() {
        return true;
    }

    public String al() {
        return this.bu;
    }

    public void a(EntityPlayer entityplayer, boolean flag0) {
        if (flag0) {
            this.bn.b(entityplayer.bn);
            this.g(entityplayer.aJ());
            this.bq = entityplayer.bq;
            this.bH = entityplayer.bH;
            this.bI = entityplayer.bI;
            this.bJ = entityplayer.bJ;
            this.c(entityplayer.br());
            this.as = entityplayer.as;
        } else if (this.q.O().b("keepInventory")) {
            this.bn.b(entityplayer.bn);
            this.bH = entityplayer.bH;
            this.bI = entityplayer.bI;
            this.bJ = entityplayer.bJ;
            this.c(entityplayer.br());
        }

        this.a = entityplayer.a;
    }

    protected boolean e_() {
        return !this.bG.b;
    }

    public void o() {}

    public void a(EnumGameType enumgametype) {}

    public String c_() {
        return this.bu;
    }

    public World f_() {
        return this.q;
    }

    public InventoryEnderChest bF() {
        return this.a;
    }

    public ItemStack n(int i0) {
        return i0 == 0 ? this.bn.h() : this.bn.b[i0 - 1];
    }

    public ItemStack aV() {
        return this.bn.h();
    }

    public void c(int i0, ItemStack itemstack) {
        this.bn.b[i0] = itemstack;
    }

    public ItemStack[] ac() {
        return this.bn.b;
    }

    public boolean av() {
        return !this.bG.b;
    }

    public Scoreboard bH() {
        return this.q.X();
    }

    public ScorePlayerTeam bI() {
        return this.bH().i(this.bu);
    }

    public String aw() {
        return ScorePlayerTeam.a(this.bI(), this.bu);
    }

    public void m(float f0) {
        if (f0 < 0.0F) {
            f0 = 0.0F;
        }

        this.u().b(17, Float.valueOf(f0));
    }

    public float bj() {
        return this.u().d(17);
    }

    // CanaryMod
    // Start: Custom XP methods
    public void addXP(int amount) {
        this.s(amount);
        updateXP();
    }

    public void removeXP(int i) {
        if (i > this.bI) { // Don't go below 0
            i = this.bI;
        }

        this.bI -= (float) i / (float) this.bC();

        // Inverse of for loop in this.t(int)
        for (this.bI -= i; this.bJ < 0.0F; this.bJ = this.bJ / this.bC() + 1.0F) {
            this.bJ *= this.bC();
            this.a(-1);
        }
        updateXP();
    }

    public void setXP(int i) {
        if (i < this.bH) {
            this.removeXP(this.bH - i);
        } else {
            this.t(i - this.bH);
        }
        updateXP();
    }

    public void recalculateXP() {
        this.bJ = this.bI / (float) this.bC();
        this.bH = 0;

        while (this.bJ >= 1.0F) {
            this.bJ = (this.bJ - 1.0F) * this.bC();
            this.bI++;
            this.bJ /= this.bC();
        }

        if (this instanceof EntityPlayerMP) {
            updateLevels();
            updateXP();
        }
    }

    private void updateXP() {
        CanaryPlayer player = ((CanaryPlayer) getCanaryEntity());
        Packet43Experience packet = new Packet43Experience(this.bJ, this.bI, this.bH);

        player.sendPacket(new CanaryPacket(packet));
    }

    private void updateLevels() {
        CanaryPlayer player = ((CanaryPlayer) getCanaryEntity());
        Packet8UpdateHealth packet = new Packet8UpdateHealth(((CanaryPlayer) getCanaryEntity()).getHealth(), ((CanaryPlayer) getCanaryEntity()).getHunger(), ((CanaryPlayer) getCanaryEntity()).getExhaustionLevel());

        player.sendPacket(new CanaryPacket(packet));
    }

    // End: Custom XP methods
    // Start: Inventory getters
    public Inventory getPlayerInventory() {
        return new CanaryPlayerInventory(bn);
    }

    public Inventory getEnderChestInventory() {
        return new CanaryEnderChestInventory(a, ((EntityPlayerMP) this).getPlayer());
    }

    // End: Inventory getters

    // Start: Custom Display Name
    public String getDisplayName() {
        return this.dispName != null ? dispName : this.c_();
    }

    public void setDisplayName(String name) {
        dispName = name;
    }

    // End: Custom Display Name

    /**
     * Returns a respawn location for this player.
     * Null if there is no explicitly set respawn location
     * 
     * @return
     */
    public Location getRespawnLocation() {
        if (this.c != null) {
            if (respawnWorld == null || respawnWorld.isEmpty()) {
                respawnWorld = getCanaryWorld().getFqName();
            }
            return new Location(Canary.getServer().getWorld(respawnWorld), c.a, c.b, c.c, 0, 0);
        }
        return null;
    }

    public void setRespawnLocation(Location l) {
        if (l == null) {
            c = null;
            respawnWorld = null;
            return;
        }
        if (c == null) {
            c = new ChunkCoordinates();
        }
        c.a = l.getBlockX();
        c.b = l.getBlockY();
        c.c = l.getBlockZ();
        respawnWorld = l.getWorld().getFqName();
    }
    //
}
