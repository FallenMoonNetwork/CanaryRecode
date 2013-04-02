package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.inventory.CanaryEnderChestInventory;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.hook.player.EntityRightClickHook;
import net.canarymod.hook.player.LevelUpHook;

public abstract class EntityPlayer extends EntityLiving implements ICommandSender {

    public InventoryPlayer bK = new InventoryPlayer(this);
    private InventoryEnderChest a = new InventoryEnderChest();
    public Container bL;
    public Container bM;
    protected FoodStats bN = new FoodStats(this); // CanaryMod: pass player
    protected int bO = 0;
    public byte bP = 0;
    public float bQ;
    public float bR;
    public String bS;
    public int bT = 0;
    public double bU;
    public double bV;
    public double bW;
    public double bX;
    public double bY;
    public double bZ;
    protected boolean ca;
    public ChunkCoordinates cb;
    private int b;
    public float cc;
    public float cd;
    private ChunkCoordinates c;
    private boolean d;
    private ChunkCoordinates e;
    public PlayerCapabilities ce = new PlayerCapabilities();
    public int cf; //level
    public int cg; //total points
    public float ch;
    private ItemStack f;
    private int g;
    protected float ci = 0.1F;
    protected float cj = 0.02F;
    private int h = 0;
    public EntityFishHook ck = null;
    private String displayName; // CanaryMod: custom display names

    public EntityPlayer(World world) {
        super(world);
        this.bL = new ContainerPlayer(this.bK, !world.I, this);
        this.bM = this.bL;
        this.N = 1.62F;
        ChunkCoordinates chunkcoordinates = world.I();

        this.b((double) chunkcoordinates.a + 0.5D, (double) (chunkcoordinates.b + 1), (double) chunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.aK = "humanoid";
        this.aJ = 180.0F;
        this.ad = 20;
        this.aH = "/mob/char.png";
        this.maxHealth = 20; // CanaryMod: initialize
    }

    @Override
    public int aW() {
        return this.maxHealth; // CanaryMod: custom Max
    }

    @Override
    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
        this.ah.a(17, Byte.valueOf((byte) 0));
        this.ah.a(18, Integer.valueOf(0));
    }

    public boolean bV() {
        return this.f != null;
    }

    public void bX() {
        if (this.f != null) {
            this.f.b(this.q, this, this.g);
        }

        this.bY();
    }

    public void bY() {
        this.f = null;
        this.g = 0;
        if (!this.q.I) {
            this.e(false);
        }
    }

    @Override
    public boolean bk() {
        return this.bV() && Item.f[this.f.c].b_(this.f) == EnumAction.d;
    }

    @Override
    public void l_() {
        if (this.f != null) {
            ItemStack itemstack = this.bK.h();

            if (itemstack == this.f) {
                if (this.g <= 25 && this.g % 4 == 0) {
                    this.c(itemstack, 5);
                }

                if (--this.g == 0 && !this.q.I) {
                    this.m();
                }
            } else {
                this.bY();
            }
        }

        if (this.bT > 0) {
            --this.bT;
        }

        if (this.bz()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }

            if (!this.q.I) {
                if (!this.i()) {
                    this.a(true, true, false);
                } else if (this.q.u()) {
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
        if (!this.q.I && this.bM != null && !this.bM.a(this)) {
            this.h();
            this.bM = this.bL;
        }

        if (this.ae() && this.ce.a) {
            this.A();
        }

        this.bU = this.bX;
        this.bV = this.bY;
        this.bW = this.bZ;
        double d0 = this.u - this.bX;
        double d1 = this.v - this.bY;
        double d2 = this.w - this.bZ;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.bU = this.bX = this.u;
        }

        if (d2 > d3) {
            this.bW = this.bZ = this.w;
        }

        if (d1 > d3) {
            this.bV = this.bY = this.v;
        }

        if (d0 < -d3) {
            this.bU = this.bX = this.u;
        }

        if (d2 < -d3) {
            this.bW = this.bZ = this.w;
        }

        if (d1 < -d3) {
            this.bV = this.bY = this.v;
        }

        this.bX += d0 * 0.25D;
        this.bZ += d2 * 0.25D;
        this.bY += d1 * 0.25D;
        this.a(StatList.k, 1);
        if (this.o == null) {
            this.e = null;
        }

        if (!this.q.I) {
            this.bN.a(this);
        }
    }

    @Override
    public int y() {
        return this.ce.a ? 0 : 80;
    }

    @Override
    public int aa() {
        return 10;
    }

    @Override
    public void a(String s0, float f0, float f1) {
        this.q.a(this, s0, f0, f1);
    }

    protected void c(ItemStack itemstack, int i0) {
        if (itemstack.o() == EnumAction.c) {
            this.a("random.drink", 0.5F, this.q.s.nextFloat() * 0.1F + 0.9F);
        }

        if (itemstack.o() == EnumAction.b) {
            for (int i1 = 0; i1 < i0; ++i1) {
                Vec3 vec3 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                vec3.a(-this.B * 3.1415927F / 180.0F);
                vec3.b(-this.A * 3.1415927F / 180.0F);
                Vec3 vec31 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.3D, (double) (-this.ab.nextFloat()) * 0.6D - 0.3D, 0.6D);

                vec31.a(-this.B * 3.1415927F / 180.0F);
                vec31.b(-this.A * 3.1415927F / 180.0F);
                vec31 = vec31.c(this.u, this.v + (double) this.e(), this.w);
                this.q.a("iconcrack_" + itemstack.b().cp, vec31.c, vec31.d, vec31.e, vec3.c, vec3.d + 0.05D, vec3.e);
            }

            this.a("random.eat", 0.5F + 0.5F * (float) this.ab.nextInt(2), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void m() {
        if (this.f != null) {
            this.c(this.f, 16);
            int i0 = this.f.a;
            ItemStack itemstack = this.f.b(this.q, this);

            if (itemstack != this.f || itemstack != null && itemstack.a != i0) {
                this.bK.a[this.bK.c] = itemstack;
                if (itemstack.a == 0) {
                    this.bK.a[this.bK.c] = null;
                }
            }

            this.bY();
        }
    }

    @Override
    protected boolean bj() {
        return this.aX() <= 0 || this.bz();
    }

    protected void h() {
        this.bM = this.bL;
    }

    @Override
    public void a(Entity entity) {
        if (this.o == entity) {
            this.h(entity);
            if (this.o != null) {
                this.o.n = null;
            }

            this.o = null;
        } else {
            super.a(entity);
        }
    }

    @Override
    public void T() {
        double d0 = this.u;
        double d1 = this.v;
        double d2 = this.w;
        float f0 = this.A;
        float f1 = this.B;

        super.T();
        this.bQ = this.bR;
        this.bR = 0.0F;
        this.k(this.u - d0, this.v - d1, this.w - d2);
        if (this.o instanceof EntityPig) {
            this.B = f1;
            this.A = f0;
            this.ay = ((EntityPig) this.o).ay;
        }
    }

    @Override
    protected void bq() {
        this.br();
    }

    @Override
    public void c() {
        if (this.bO > 0) {
            --this.bO;
        }

        if (this.q.r == 0 && this.aX() < this.aW() && this.ac % 20 * 12 == 0) {
            this.j(1);
        }

        this.bK.k();
        this.bQ = this.bR;
        super.c();
        this.aO = this.ce.b();
        this.aP = this.cj;
        if (this.ah()) {
            this.aO = (float) ((double) this.aO + (double) this.ce.b() * 0.3D);
            this.aP = (float) ((double) this.aP + (double) this.cj * 0.3D);
        }

        float f0 = MathHelper.a(this.x * this.x + this.z * this.z);
        float f1 = (float) Math.atan(-this.y * 0.20000000298023224D) * 15.0F;

        if (f0 > 0.1F) {
            f0 = 0.1F;
        }

        if (!this.F || this.aX() <= 0) {
            f0 = 0.0F;
        }

        if (this.F || this.aX() <= 0) {
            f1 = 0.0F;
        }

        this.bR += (f0 - this.bR) * 0.4F;
        this.bc += (f1 - this.bc) * 0.8F;
        if (this.aX() > 0) {
            List list = this.q.b((Entity) this, this.E.b(1.0D, 0.5D, 1.0D));

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

    public int bZ() {
        return this.ah.c(18);
    }

    public void s(int i0) {
        this.ah.b(18, Integer.valueOf(i0));
    }

    public void t(int i0) {
        int i1 = this.bZ();

        this.ah.b(18, Integer.valueOf(i1 + i0));
    }

    @Override
    public void a(DamageSource damagesource) {
        super.a(damagesource);
        this.a(0.2F, 0.2F);
        this.b(this.u, this.v, this.w);
        this.y = 0.10000000149011612D;
        if (this.bS.equals("Notch")) {
            this.a(new ItemStack(Item.k, 1), true);
        }

        if (!this.q.M().b("keepInventory")) {
            this.bK.m();
        }

        if (damagesource != null) {
            this.x = (double) (-MathHelper.b((this.aY + this.A) * 3.1415927F / 180.0F) * 0.1F);
            this.z = (double) (-MathHelper.a((this.aY + this.A) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.x = this.z = 0.0D;
        }

        this.N = 0.1F;
        this.a(StatList.y, 1);
    }

    @Override
    public void c(Entity entity, int i0) {
        this.t(i0);
        Collection collection = this.cp().a(ScoreObjectiveCriteria.e);

        if (entity instanceof EntityPlayer) {
            this.a(StatList.A, 1);
            collection.addAll(this.cp().a(ScoreObjectiveCriteria.d));
        } else {
            this.a(StatList.z, 1);
        }

        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreObjective scoreobjective = (ScoreObjective) iterator.next();
            Score score = this.cp().a(this.am(), scoreobjective);

            score.a();
        }
    }

    public EntityItem a(boolean flag0) {
        return this.a(this.bK.a(this.bK.c, flag0 && this.bK.h() != null ? this.bK.h().a : 1), false);
    }

    public EntityItem c(ItemStack itemstack) {
        return this.a(itemstack, false);
    }

    public EntityItem a(ItemStack itemstack, boolean flag0) {
        if (itemstack == null) {
            return null;
        } else {
            EntityItem entityitem = new EntityItem(this.q, this.u, this.v - 0.30000001192092896D + (double) this.e(), this.w, itemstack);

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

            this.a(entityitem);
            this.a(StatList.v, 1);
            return entityitem;
        }
    }

    protected void a(EntityItem entityitem) {
        this.q.d((Entity) entityitem);
    }

    public float a(Block block, boolean flag0) {
        float f0 = this.bK.a(block);

        if (f0 > 1.0F) {
            int i0 = EnchantmentHelper.c(this);
            ItemStack itemstack = this.bK.h();

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
        return this.bK.b(block);
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Inventory");

        this.bK.b(nbttaglist);
        this.bK.c = nbttagcompound.e("SelectedItemSlot");
        this.ca = nbttagcompound.n("Sleeping");
        this.b = nbttagcompound.d("SleepTimer");
        this.ch = nbttagcompound.g("XpP");
        this.cf = nbttagcompound.e("XpLevel");
        this.cg = nbttagcompound.e("XpTotal");
        this.s(nbttagcompound.e("Score"));
        if (this.ca) {
            this.cb = new ChunkCoordinates(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w));
            this.a(true, true, false);
        }

        if (nbttagcompound.b("SpawnX") && nbttagcompound.b("SpawnY") && nbttagcompound.b("SpawnZ")) {
            this.c = new ChunkCoordinates(nbttagcompound.e("SpawnX"), nbttagcompound.e("SpawnY"), nbttagcompound.e("SpawnZ"));
            this.d = nbttagcompound.n("SpawnForced");
        }

        this.bN.a(nbttagcompound);
        this.ce.b(nbttagcompound);
        if (nbttagcompound.b("EnderItems")) {
            NBTTagList nbttaglist1 = nbttagcompound.m("EnderItems");

            this.a.a(nbttaglist1);
        }
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.bK.a(new NBTTagList()));
        nbttagcompound.a("SelectedItemSlot", this.bK.c);
        nbttagcompound.a("Sleeping", this.ca);
        nbttagcompound.a("SleepTimer", (short) this.b);
        nbttagcompound.a("XpP", this.ch);
        nbttagcompound.a("XpLevel", this.cf);
        nbttagcompound.a("XpTotal", this.cg);
        nbttagcompound.a("Score", this.bZ());
        if (this.c != null) {
            nbttagcompound.a("SpawnX", this.c.a);
            nbttagcompound.a("SpawnY", this.c.b);
            nbttagcompound.a("SpawnZ", this.c.c);
            nbttagcompound.a("SpawnForced", this.d);
        }

        this.bN.b(nbttagcompound);
        this.ce.a(nbttagcompound);
        nbttagcompound.a("EnderItems", (NBTBase) this.a.h());
    }

    public void a(IInventory iinventory) {}

    public void a(TileEntityHopper tileentityhopper) {}

    public void a(EntityMinecartHopper entityminecarthopper) {}

    public void a(int i0, int i1, int i2, String s0) {}

    public void c(int i0, int i1, int i2) {}

    public void b(int i0, int i1, int i2) {}

    @Override
    public float e() {
        return 0.12F;
    }

    protected void e_() {
        this.N = 1.62F;
    }

    @Override
    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else if (this.ce.a && !damagesource.g()) {
            return false;
        } else {
            this.bC = 0;
            if (this.aX() <= 0) {
                return false;
            } else {
                if (this.bz() && !this.q.I) {
                    this.a(true, true, false);
                }

                if (damagesource.p()) {
                    if (this.q.r == 0) {
                        i0 = 0;
                    }

                    if (this.q.r == 1) {
                        i0 = i0 / 2 + 1;
                    }

                    if (this.q.r == 3) {
                        i0 = i0 * 3 / 2;
                    }
                }

                if (i0 == 0) {
                    return false;
                } else {
                    Entity entity = damagesource.i();

                    if (entity instanceof EntityArrow && ((EntityArrow) entity).c != null) {
                        entity = ((EntityArrow) entity).c;
                    }

                    if (entity instanceof EntityLiving) {
                        this.a((EntityLiving) entity, false);
                    }

                    this.a(StatList.x, i0);
                    return super.a(damagesource, i0);
                }
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        ScorePlayerTeam scoreplayerteam = this.cq();
        ScorePlayerTeam scoreplayerteam1 = entityplayer.cq();

        return scoreplayerteam != scoreplayerteam1 ? true : (scoreplayerteam != null ? scoreplayerteam.g() : true);
    }

    protected void a(EntityLiving entityliving, boolean flag0) {
        if (!(entityliving instanceof EntityCreeper) && !(entityliving instanceof EntityGhast)) {
            if (entityliving instanceof EntityWolf) {
                EntityWolf entitywolf = (EntityWolf) entityliving;

                if (entitywolf.m() && this.bS.equals(entitywolf.o())) {
                    return;
                }
            }

            if (!(entityliving instanceof EntityPlayer) || this.a((EntityPlayer) entityliving)) {
                List list = this.q.a(EntityWolf.class, AxisAlignedBB.a().a(this.u, this.v, this.w, this.u + 1.0D, this.v + 1.0D, this.w + 1.0D).b(16.0D, 4.0D, 16.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    EntityWolf entitywolf1 = (EntityWolf) iterator.next();

                    if (entitywolf1.m() && entitywolf1.l() == null && this.bS.equals(entitywolf1.o()) && (!flag0 || !entitywolf1.n())) {
                        entitywolf1.k(false);
                        entitywolf1.b((Entity) entityliving);
                    }
                }
            }
        }
    }

    @Override
    protected void k(int i0) {
        this.bK.g(i0);
    }

    @Override
    public int aZ() {
        return this.bK.l();
    }

    public float ca() {
        int i0 = 0;
        ItemStack[] aitemstack = this.bK.b;
        int i1 = aitemstack.length;

        for (int i2 = 0; i2 < i1; ++i2) {
            ItemStack itemstack = aitemstack[i2];

            if (itemstack != null) {
                ++i0;
            }
        }

        return (float) i0 / (float) this.bK.b.length;
    }

    @Override
    protected void d(DamageSource damagesource, int i0) {
        if (!this.aq()) {
            if (!damagesource.e() && this.bk()) {
                i0 = 1 + i0 >> 1;
            }

            i0 = this.b(damagesource, i0);
            i0 = this.c(damagesource, i0);
            this.j(damagesource.f());
            int i1 = this.aX();

            this.b(this.aX() - i0);
            this.bt.a(damagesource, i1, i0);
        }
    }

    public void a(TileEntityFurnace tileentityfurnace) {}

    public void a(TileEntityDispenser tileentitydispenser) {}

    public void a(TileEntity tileentity) {}

    public void a(TileEntityBrewingStand tileentitybrewingstand) {}

    public void a(TileEntityBeacon tileentitybeacon) {}

    public void a(IMerchant imerchant, String s0) {}

    public void d(ItemStack itemstack) {}

    public boolean p(Entity entity) {
        if (entity.a_(this)) {
            return true;
        } else {
            // CanaryMod: EntityRightClickHook
            EntityRightClickHook hook = new EntityRightClickHook(entity.getCanaryEntity(), ((EntityPlayerMP) this).getPlayer());
            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return false;
            }
            //

            ItemStack itemstack = this.cb();

            if (itemstack != null && entity instanceof EntityLiving) {
                if (this.ce.d) {
                    itemstack = itemstack.m();
                }

                if (itemstack.a((EntityLiving) entity)) {
                    if (itemstack.a <= 0 && !this.ce.d) {
                        this.cc();
                    }

                    return true;
                }
            }

            return false;
        }
    }

    public ItemStack cb() {
        return this.bK.h();
    }

    public void cc() {
        this.bK.a(this.bK.c, (ItemStack) null);
    }

    @Override
    public double V() {
        return (double) (this.N - 0.5F);
    }

    public void q(Entity entity) {
        if (entity.ap()) {
            if (!entity.j(this)) {
                int i0 = this.bK.a(entity);

                if (this.a(Potion.g)) {
                    i0 += 3 << this.b(Potion.g).c();
                }

                if (this.a(Potion.t)) {
                    i0 -= 2 << this.b(Potion.t).c();
                }

                int i1 = 0;
                int i2 = 0;

                if (entity instanceof EntityLiving) {
                    i2 = EnchantmentHelper.a((EntityLiving) this, (EntityLiving) entity);
                    i1 += EnchantmentHelper.b(this, (EntityLiving) entity);
                }

                if (this.ah()) {
                    ++i1;
                }

                if (i0 > 0 || i2 > 0) {
                    boolean flag0 = this.T > 0.0F && !this.F && !this.g_() && !this.G() && !this.a(Potion.q) && this.o == null && entity instanceof EntityLiving;

                    if (flag0 && i0 > 0) {
                        i0 += this.ab.nextInt(i0 / 2 + 2);
                    }

                    i0 += i2;
                    boolean flag1 = false;
                    int i3 = EnchantmentHelper.a((EntityLiving) this);

                    if (entity instanceof EntityLiving && i3 > 0 && !entity.ae()) {
                        flag1 = true;
                        entity.d(1);
                    }

                    boolean flag2 = entity.a(DamageSource.a(this), i0);

                    if (flag2) {
                        if (i1 > 0) {
                            entity.g((double) (-MathHelper.a(this.A * 3.1415927F / 180.0F) * (float) i1 * 0.5F), 0.1D, (double) (MathHelper.b(this.A * 3.1415927F / 180.0F) * (float) i1 * 0.5F));
                            this.x *= 0.6D;
                            this.z *= 0.6D;
                            this.c(false);
                        }

                        if (flag0) {
                            this.b(entity);
                        }

                        if (i2 > 0) {
                            this.c(entity);
                        }

                        if (i0 >= 18) {
                            this.a((StatBase) AchievementList.E);
                        }

                        this.l(entity);
                        if (entity instanceof EntityLiving) {
                            EnchantmentThorns.a(this, (EntityLiving) entity, this.ab);
                        }
                    }

                    ItemStack itemstack = this.cb();
                    Object object = entity;

                    if (entity instanceof EntityDragonPart) {
                        IEntityMultiPart ientitymultipart = ((EntityDragonPart) entity).a;

                        if (ientitymultipart != null && ientitymultipart instanceof EntityLiving) {
                            object = (EntityLiving) ientitymultipart;
                        }
                    }

                    if (itemstack != null && object instanceof EntityLiving) {
                        itemstack.a((EntityLiving) object, this);
                        if (itemstack.a <= 0) {
                            this.cc();
                        }
                    }

                    if (entity instanceof EntityLiving) {
                        if (entity.R()) {
                            this.a((EntityLiving) entity, true);
                        }

                        this.a(StatList.w, i0);
                        if (i3 > 0 && flag2) {
                            entity.d(i3 * 4);
                        } else if (flag1) {
                            entity.A();
                        }
                    }

                    this.j(0.3F);
                }
            }
        }
    }

    public void b(Entity entity) {}

    public void c(Entity entity) {}

    @Override
    public void w() {
        super.w();
        this.bL.b(this);
        if (this.bM != null) {
            this.bM.b(this);
        }
    }

    @Override
    public boolean S() {
        return !this.ca && super.S();
    }

    public boolean ce() {
        return false;
    }

    public EnumStatus a(int i0, int i1, int i2) {
        if (!this.q.I) {
            if (this.bz() || !this.R()) {
                return EnumStatus.e;
            }

            if (!this.q.t.d()) {
                return EnumStatus.b;
            }

            if (this.q.u()) {
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

            this.x(i4);
            this.b((double) ((float) i0 + f0), (double) ((float) i1 + 0.9375F), (double) ((float) i2 + f1));
        } else {
            this.b((double) ((float) i0 + 0.5F), (double) ((float) i1 + 0.9375F), (double) ((float) i2 + 0.5F));
        }

        this.ca = true;
        this.b = 0;
        this.cb = new ChunkCoordinates(i0, i1, i2);
        this.x = this.z = this.y = 0.0D;
        if (!this.q.I) {
            this.q.c();
        }

        return EnumStatus.a;
    }

    private void x(int i0) {
        this.cc = 0.0F;
        this.cd = 0.0F;
        switch (i0) {
            case 0:
                this.cd = -1.8F;
                break;

            case 1:
                this.cc = 1.8F;
                break;

            case 2:
                this.cd = 1.8F;
                break;

            case 3:
                this.cc = -1.8F;
        }
    }

    public void a(boolean flag0, boolean flag1, boolean flag2) {
        this.a(0.6F, 1.8F);
        this.e_();
        ChunkCoordinates chunkcoordinates = this.cb;
        ChunkCoordinates chunkcoordinates1 = this.cb;

        if (chunkcoordinates != null && this.q.a(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c) == Block.W.cz) {
            BlockBed.a(this.q, chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, false);
            chunkcoordinates1 = BlockBed.b(this.q, chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, 0);
            if (chunkcoordinates1 == null) {
                chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.a, chunkcoordinates.b + 1, chunkcoordinates.c);
            }

            this.b((double) ((float) chunkcoordinates1.a + 0.5F), (double) ((float) chunkcoordinates1.b + this.N + 0.1F), (double) ((float) chunkcoordinates1.c + 0.5F));
        }

        this.ca = false;
        if (!this.q.I && flag1) {
            this.q.c();
        }

        if (flag0) {
            this.b = 0;
        } else {
            this.b = 100;
        }

        if (flag2) {
            this.a(this.cb, false);
        }
    }

    private boolean i() {
        return this.q.a(this.cb.a, this.cb.b, this.cb.c) == Block.W.cz;
    }

    public static ChunkCoordinates a(World world, ChunkCoordinates chunkcoordinates, boolean flag0) {
        IChunkProvider ichunkprovider = world.J();

        ichunkprovider.c(chunkcoordinates.a - 3 >> 4, chunkcoordinates.c - 3 >> 4);
        ichunkprovider.c(chunkcoordinates.a + 3 >> 4, chunkcoordinates.c - 3 >> 4);
        ichunkprovider.c(chunkcoordinates.a - 3 >> 4, chunkcoordinates.c + 3 >> 4);
        ichunkprovider.c(chunkcoordinates.a + 3 >> 4, chunkcoordinates.c + 3 >> 4);
        if (world.a(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c) == Block.W.cz) {
            ChunkCoordinates chunkcoordinates1 = BlockBed.b(world, chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, 0);

            return chunkcoordinates1;
        } else {
            Material material = world.g(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c);
            Material material1 = world.g(chunkcoordinates.a, chunkcoordinates.b + 1, chunkcoordinates.c);
            boolean flag1 = !material.a() && !material.d();
            boolean flag2 = !material1.a() && !material1.d();

            return flag0 && flag1 && flag2 ? chunkcoordinates : null;
        }
    }

    @Override
    public boolean bz() {
        return this.ca;
    }

    public boolean cg() {
        return this.ca && this.b >= 100;
    }

    protected void b(int i0, boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1 << i0)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & ~(1 << i0))));
        }
    }

    public void b(String s0) {}

    public ChunkCoordinates ci() {
        return this.c;
    }

    public boolean cj() {
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

    @Override
    protected void bl() {
        super.bl();
        this.a(StatList.u, 1);
        if (this.ah()) {
            this.j(0.8F);
        } else {
            this.j(0.2F);
        }
    }

    @Override
    public void e(float f0, float f1) {
        double d0 = this.u;
        double d1 = this.v;
        double d2 = this.w;

        if (this.ce.b && this.o == null) {
            double d3 = this.y;
            float f2 = this.aP;

            this.aP = this.ce.a();
            super.e(f0, f1);
            this.y = d3 * 0.6D;
            this.aP = f2;
        } else {
            super.e(f0, f1);
        }

        this.j(this.u - d0, this.v - d1, this.w - d2);
    }

    public void j(double d0, double d1, double d2) {
        if (this.o == null) {
            int i0;

            if (this.a(Material.h)) {
                i0 = Math.round(MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
                if (i0 > 0) {
                    this.a(StatList.q, i0);
                    this.j(0.015F * (float) i0 * 0.01F);
                }
            } else if (this.G()) {
                i0 = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i0 > 0) {
                    this.a(StatList.m, i0);
                    this.j(0.015F * (float) i0 * 0.01F);
                }
            } else if (this.g_()) {
                if (d1 > 0.0D) {
                    this.a(StatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.F) {
                i0 = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i0 > 0) {
                    this.a(StatList.l, i0);
                    if (this.ah()) {
                        this.j(0.099999994F * (float) i0 * 0.01F);
                    } else {
                        this.j(0.01F * (float) i0 * 0.01F);
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

    @Override
    protected void a(float f0) {
        if (!this.ce.c) {
            if (f0 >= 2.0F) {
                this.a(StatList.n, (int) Math.round((double) f0 * 100.0D));
            }

            super.a(f0);
        }
    }

    @Override
    public void a(EntityLiving entityliving) {
        if (entityliving instanceof IMob) {
            this.a((StatBase) AchievementList.s);
        }
    }

    @Override
    public void al() {
        if (!this.ce.b) {
            super.al();
        }
    }

    @Override
    public ItemStack q(int i0) {
        return this.bK.f(i0);
    }

    @Override
    protected void bH() {}

    @Override
    protected void bI() {}

    public void w(int i0) {
        this.t(i0);
        int i1 = Integer.MAX_VALUE - this.cg;

        if (i0 > i1) {
            i0 = i1;
        }

        this.ch += (float) i0 / (float) this.ck();

        for (this.cg += i0; this.ch >= 1.0F; this.ch /= (float) this.ck()) {
            this.ch = (this.ch - 1.0F) * (float) this.ck();
            this.a(1);
        }
    }

    public void a(int i0) {
        // CanaryMod: LevelUpHook
        LevelUpHook hook = new LevelUpHook(((EntityPlayerMP) this).getPlayer());
        Canary.hooks().callHook(hook);
        //
        this.cf += i0;
        if (this.cf < 0) {
            this.cf = 0;
            this.ch = 0.0F;
            this.cg = 0;
        }

        if (i0 > 0 && this.cf % 5 == 0 && (float) this.h < (float) this.ac - 100.0F) {
            float f0 = this.cf > 30 ? 1.0F : (float) this.cf / 30.0F;

            this.q.a((Entity) this, "random.levelup", f0 * 0.75F, 1.0F);
            this.h = this.ac;
        }
    }

    public int ck() {
        return this.cf >= 30 ? 62 + (this.cf - 30) * 7 : (this.cf >= 15 ? 17 + (this.cf - 15) * 3 : 17);
    }

    public void j(float f0) {
        if (!this.ce.a) {
            if (!this.q.I) {
                this.bN.a(f0);
            }
        }
    }

    public FoodStats cl() {
        return this.bN;
    }

    public boolean i(boolean flag0) {
        return (flag0 || this.bN.c()) && !this.ce.a;
    }

    public boolean cm() {
        return this.aX() > 0 && this.aX() < this.aW();
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

    public boolean e(int i0, int i1, int i2) {
        if (this.ce.e) {
            return true;
        } else {
            int i3 = this.q.a(i0, i1, i2);

            if (i3 > 0) {
                Block block = Block.r[i3];

                if (block.cO.q()) {
                    return true;
                }

                if (this.cb() != null) {
                    ItemStack itemstack = this.cb();

                    if (itemstack.b(block) || itemstack.a(block) > 1.0F) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean a(int i0, int i1, int i2, int i3, ItemStack itemstack) {
        return this.ce.e ? true : (itemstack != null ? itemstack.y() : false);
    }

    @Override
    protected int d(EntityPlayer entityplayer) {
        if (this.q.M().b("keepInventory")) {
            return 0;
        } else {
            int i0 = this.cf * 7;

            return i0 > 100 ? 100 : i0;
        }
    }

    @Override
    protected boolean aT() {
        return true;
    }

    @Override
    public String am() {
        return this.bS;
    }

    @Override
    public boolean bQ() {
        return super.bQ();
    }

    @Override
    public boolean bS() {
        return false;
    }

    public void a(EntityPlayer entityplayer, boolean flag0) {
        if (flag0) {
            this.bK.b(entityplayer.bK);
            this.aS = entityplayer.aS;
            this.bN = entityplayer.bN;
            this.cf = entityplayer.cf;
            this.cg = entityplayer.cg;
            this.ch = entityplayer.ch;
            this.s(entityplayer.bZ());
            this.as = entityplayer.as;
        } else if (this.q.M().b("keepInventory")) {
            this.bK.b(entityplayer.bK);
            this.cf = entityplayer.cf;
            this.cg = entityplayer.cg;
            this.ch = entityplayer.ch;
            this.s(entityplayer.bZ());
        }

        this.a = entityplayer.a;
    }

    @Override
    protected boolean f_() {
        return !this.ce.b;
    }

    public void n() {}

    public void a(EnumGameType enumgametype) {}

    @Override
    public String c_() {
        return this.bS;
    }

    public StringTranslate r() {
        return StringTranslate.a();
    }

    @Override
    public String a(String s0, Object... aobject) {
        return this.r().a(s0, aobject);
    }

    public InventoryEnderChest cn() {
        return this.a;
    }

    @Override
    public ItemStack p(int i0) {
        return i0 == 0 ? this.bK.h() : this.bK.b[i0 - 1];
    }

    @Override
    public ItemStack bG() {
        return this.bK.h();
    }

    @Override
    public void c(int i0, ItemStack itemstack) {
        this.bK.b[i0] = itemstack;
    }

    @Override
    public ItemStack[] ad() {
        return this.bK.b;
    }

    @Override
    public boolean aw() {
        return !this.ce.b;
    }

    public Scoreboard cp() {
        return this.q.V();
    }

    public ScorePlayerTeam cq() {
        return this.cp().i(this.bS);
    }

    @Override
    public String ax() {
        return ScorePlayerTeam.a(this.cq(), this.bS);
    }

    // CanaryMod
    // Start: Custom XP methods
    public void addXP(int amount) {
        this.w(amount);
        updateXP();
    }

    public void removeXP(int i) {
        if (i > this.cg) { // Don't go below 0
            i = this.cg;
        }

        this.cg -= (float) i / (float) this.ck();

        // Inverse of for loop in this.t(int)
        for (this.cg -= i; this.ch < 0.0F; this.ch = this.ch / this.ck() + 1.0F) {
            this.ch *= this.ck();
            this.a(-1);
        }
        updateXP();
    }

    public void setXP(int i) {
        if (i < this.cf) {
            this.removeXP(this.cf - i);
        } else {
            this.t(i - this.cf);
        }
        updateXP();
    }

    public void recalculateXP() {
        this.ch = this.cg / (float) this.ck();
        this.cf = 0;

        while (this.ch >= 1.0F) {
            this.ch = (this.ch - 1.0F) * this.ck();
            this.cf++;
            this.ch /= this.ck();
        }

        if (this instanceof EntityPlayerMP) {
            updateLevels();
            updateXP();
        }
    }

    private void updateXP() {
        CanaryPlayer player = ((CanaryPlayer)getCanaryEntity());
        Packet43Experience packet = new Packet43Experience(this.ch, this.cg, this.cf);
        player.sendPacket(new CanaryPacket(packet));
    }

    private void updateLevels() {
        CanaryPlayer player = ((CanaryPlayer)getCanaryEntity());
        Packet8UpdateHealth packet = new Packet8UpdateHealth(
                ((CanaryPlayer)getCanaryEntity()).getHealth(),
                ((CanaryPlayer)getCanaryEntity()).getHunger(),
                ((CanaryPlayer)getCanaryEntity()).getExhaustionLevel());
        player.sendPacket(new CanaryPacket(packet));
    }

    // End: Custom XP methods
    // Start: Inventory getters
    public Inventory getPlayerInventory() {
        return new CanaryPlayerInventory(bK);
    }

    public Inventory getEnderChestInventory() {
        return new CanaryEnderChestInventory(a, ((EntityPlayerMP) this).getPlayer());
    }

    // End: Inventory getters

    // Start: Custom Display Name
    public String getDisplayName() {
        return displayName == null ? this.bS : displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }
    // End: Custom Display Name
    //
}
