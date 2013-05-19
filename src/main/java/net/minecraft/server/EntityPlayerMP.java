package net.minecraft.server;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.CanaryNetServerHandler;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.EntityNonPlayableCharacter;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.vehicle.CanaryChestMinecart;
import net.canarymod.api.inventory.CanaryEnderChestInventory;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.statistics.CanaryStat;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.blocks.CanaryDoubleChest;
import net.canarymod.api.world.blocks.CanaryEnchantmentTable;
import net.canarymod.api.world.blocks.CanaryWorkbench;
import net.canarymod.api.world.position.Location;
import net.canarymod.config.Configuration;
import net.canarymod.config.WorldConfiguration;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.DimensionSwitch;
import net.canarymod.hook.player.ExperienceHook;
import net.canarymod.hook.player.HealthChangeHook;
import net.canarymod.hook.player.InventoryHook;
import net.canarymod.hook.player.PlayerDeathHook;
import net.canarymod.hook.player.PortalUseHook;
import net.canarymod.hook.player.SignShowHook;
import net.canarymod.hook.player.StatGainedHook;


public class EntityPlayerMP extends EntityPlayer implements ICrafting {

    private StringTranslate cl = new StringTranslate("en_US");
    public NetServerHandler a;
    public MinecraftServer b;
    public ItemInWorldManager c;
    public double d;
    public double e;
    public final List f = new LinkedList();
    public final List g = new LinkedList();
    private int cm = -99999999;
    private int cn = -99999999;
    private boolean co = true;
    private int cp = -99999999;
    private int cq = 60;
    private int cr = 0;
    private int cs = 0;
    private boolean ct = true;
    private int cu = 0;
    public boolean h;
    public int i;
    public boolean j = false;

    public EntityPlayerMP(MinecraftServer minecraftserver, World world, String s0, ItemInWorldManager iteminworldmanager) {
        super(world);
        WorldConfiguration cfg = Configuration.getWorldConfig(world.getCanaryWorld().getFqName());
        iteminworldmanager.b = this;
        this.c = iteminworldmanager;
        this.cr = minecraftserver.ad().o();
        ChunkCoordinates chunkcoordinates = world.J();
        int i0 = chunkcoordinates.a;
        int i1 = chunkcoordinates.c;
        int i2 = chunkcoordinates.b;

        if (!world.t.f && world.M().r() != EnumGameType.d) {
            int i3 = Math.max(5, cfg.getSpawnProtectionSize() - 6);

            i0 += this.ab.nextInt(i3 * 2) - i3;
            i1 += this.ab.nextInt(i3 * 2) - i3;
            i2 = world.i(i0, i1);
        }

        this.b = minecraftserver;
        this.Y = 0.0F;
        this.bS = s0;
        this.N = 0.0F;
        this.b((double) i0 + 0.5D, (double) i2, (double) i1 + 0.5D, 0.0F, 0.0F);

        while (!world.a((Entity) this, this.E).isEmpty()) {
            this.b(this.u, this.v + 1.0D, this.w);
        }

        // CanaryMod: create wrapper
        if (!(this instanceof EntityNonPlayableCharacter)) { // We might be an NPC
            this.entity = new CanaryPlayer(this);
        }
        //
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("playerGameType")) {
//XXX Check this, it was only the last call to this.c.a
            if (MinecraftServer.D().am()) {
                this.c.a(MinecraftServer.D().g());
            } else {
                this.c.a(EnumGameType.a(nbttagcompound.e("playerGameType")));
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("playerGameType", this.c.b().a());
    }

    public void a(int i0) {
        super.a(i0);
        this.cp = -1;
    }

    public void d_() {
        this.bM.a((ICrafting) this);
    }

    protected void e_() {
        this.N = 0.0F;
    }

    public float e() {
        return 1.62F;
    }

    public void l_() {
        this.c.a();
        --this.cq;
        this.bM.b();

        while (!this.g.isEmpty()) {
            int i0 = Math.min(this.g.size(), 127);
            int[] aint = new int[i0];
            Iterator iterator = this.g.iterator();
            int i1 = 0;

            while (iterator.hasNext() && i1 < i0) {
                aint[i1++] = ((Integer) iterator.next()).intValue();
                iterator.remove();
            }

            this.a.b(new Packet29DestroyEntity(aint));
        }

        if (!this.f.isEmpty()) {
            ArrayList arraylist = new ArrayList();
            Iterator iterator1 = this.f.iterator();
            ArrayList arraylist1 = new ArrayList();

            while (iterator1.hasNext() && arraylist.size() < 5) {
                ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator1.next();

                iterator1.remove();
                if (chunkcoordintpair != null && this.q.f(chunkcoordintpair.a << 4, 0, chunkcoordintpair.b << 4)) {
                    arraylist.add(this.q.e(chunkcoordintpair.a, chunkcoordintpair.b));
                    arraylist1.addAll(((WorldServer) this.q).c(chunkcoordintpair.a * 16, 0, chunkcoordintpair.b * 16, chunkcoordintpair.a * 16 + 16, 256, chunkcoordintpair.b * 16 + 16));
                }
            }

            if (!arraylist.isEmpty()) {
                this.a.b(new Packet56MapChunks(arraylist));
                Iterator iterator2 = arraylist1.iterator();

                while (iterator2.hasNext()) {
                    TileEntity tileentity = (TileEntity) iterator2.next();

                    this.b(tileentity);
                }

                iterator2 = arraylist.iterator();

                while (iterator2.hasNext()) {
                    Chunk chunk = (Chunk) iterator2.next();

                    this.o().q().a(this, chunk);
                }
            }
        }
    }

    public void b(int i0) {
        super.b(i0);
        Collection collection = this.cr().a(ScoreObjectiveCriteria.f);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreObjective scoreobjective = (ScoreObjective) iterator.next();

            this.cr().a(this.am(), scoreobjective).a(Arrays.asList(new EntityPlayer[] { this}));
        }
    }

    public void g() {
        try {
            super.l_();

            for (int i0 = 0; i0 < this.bK.j_(); ++i0) {
                ItemStack itemstack = this.bK.a(i0);

                if (itemstack != null && Item.f[itemstack.c].f() && this.a.e() <= 5) {
                    Packet packet = ((ItemMapBase) Item.f[itemstack.c]).c(itemstack, this.q, this);

                    if (packet != null) {
                        this.a.b(packet);
                    }
                }
            }

            // CanaryMod: HealthChange / HealthEnabled
            if (this.aX() != this.cm && cm != -99999999 && this.getPlayer() != null) {
                // updates your health when it is changed.
                if (!Configuration.getWorldConfig(getCanaryWorld().getFqName()).isHealthEnabled()) {
                    super.b(this.aW());
                    this.M = false;
                } else {
                    HealthChangeHook hook = new HealthChangeHook(getPlayer(), cm, this.aX());

                    Canary.hooks().callHook(hook);
                    if (hook.isCanceled()) {
                        super.b(this.cm);
                    }
                }
            }
            //

            if (this.aX() != this.cm || this.cn != this.bN.a() || this.bN.e() == 0.0F != this.co) {
                // CanaryMod: convert health for values above 20
                int health = (int) (this.aX() / (this.aW() / 20));

                health = (this.aX() > 0 && health == 0) ? 1 : health;
                this.a.b(new Packet8UpdateHealth(health, this.bN.a(), this.bN.e()));
                //
                this.cm = this.aX();
                this.cn = this.bN.a();
                this.co = this.bN.e() == 0.0F;
            }

            if (this.cg != this.cp) {
                // CanaryMod: ExperienceHook / ExperienceEnabled
                if (!Configuration.getWorldConfig(getCanaryWorld().getFqName()).isExperienceEnabled()) {
                    this.cg = 0;
                    this.cf = 0;
                } else if (getPlayer() != null) { // NPC?
                    ExperienceHook hook = new ExperienceHook(getPlayer(), this.cp, cg);

                    if (!hook.isCanceled()) {
                        this.cp = this.cg;
                        this.a.b(new Packet43Experience(this.ch, this.cg, this.cf));
                    }
                }
                //
            }
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Ticking player");
            CrashReportCategory crashreportcategory = crashreport.a("Player being ticked");

            this.a(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    public void a(DamageSource damagesource) {
        // CanaryMod: PlayerDeathHook
        PlayerDeathHook hook = new PlayerDeathHook(getPlayer(), this.bt.b());

        Canary.hooks().callHook(hook);

        // Check Death Message enabled
        if (Configuration.getServerConfig().isDeathMessageEnabled()) {
            this.b.ad().k(hook.getDeathMessage());
        }
        //

        if (!this.q.N().b("keepInventory")) {
            this.bK.m();
        }

        Collection collection = this.q.W().a(ScoreObjectiveCriteria.c);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreObjective scoreobjective = (ScoreObjective) iterator.next();
            Score score = this.cr().a(this.am(), scoreobjective);

            score.a();
        }

        EntityLiving entityliving = this.bN();

        if (entityliving != null) {
            entityliving.c(this, this.aM);
        }
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            //CanaryMod moved pvp to per-world config
            boolean haspvp = Configuration.getWorldConfig(getCanaryWorld().getFqName()).isPvpEnabled();
            boolean flag0 = this.b.T() && haspvp && "fall".equals(damagesource.o);

            if (!flag0 && this.cq > 0 && damagesource != DamageSource.i) {
                return false;
            } else {
                if (damagesource instanceof EntityDamageSource) {
                    Entity entity = damagesource.i();

                    if (entity instanceof EntityPlayer && !this.a((EntityPlayer) entity)) {
                        return false;
                    }

                    if (entity instanceof EntityArrow) {
                        EntityArrow entityarrow = (EntityArrow) entity;

                        if (entityarrow.c instanceof EntityPlayer && !this.a((EntityPlayer) entityarrow.c)) {
                            return false;
                        }
                    }
                }

                return super.a(damagesource, i0);
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        //CanaryMod moved pvp to per-world config
        boolean haspvp = Configuration.getWorldConfig(getCanaryWorld().getFqName()).isPvpEnabled();
        return !haspvp ? false : super.a(entityplayer);
    }

    public void c(int i0) {
        if (this.ar == 1 && i0 == 1) {
            this.a((StatBase) AchievementList.C);
            this.q.e((Entity) this);
            this.j = true;
            this.a.b(new Packet70GameEvent(4, 0));
        } else {
            if (this.ar == 1 && i0 == 0) {
                this.a((StatBase) AchievementList.B);
                ChunkCoordinates chunkcoordinates = this.b.getWorld(this.getCanaryWorld().getName(), i0).l();

                if (chunkcoordinates != null) {
                    this.a.a((double) chunkcoordinates.a, (double) chunkcoordinates.b, (double) chunkcoordinates.c, 0.0F, 0.0F, getCanaryWorld().getType().getId(), getCanaryWorld().getName());
                }

                i0 = 1;
            } else {
                this.a((StatBase) AchievementList.x);
            }

            // CanaryMod onPortalUse && onDimensionSwitch
            Location goingTo = simulatePortalUse(i0, MinecraftServer.D().getWorld(getCanaryWorld().getName(), i0));

            PortalUseHook hook = new PortalUseHook(getPlayer(), goingTo);
            CancelableHook hook1 = new DimensionSwitch(this.getCanaryEntity(), this.getCanaryEntity().getLocation(), goingTo);

            Canary.hooks().callHook(hook);
            Canary.hooks().callHook(hook1);

            if (hook.isCanceled() || hook1.isCanceled()) {
                return;
            } //
            else {
                this.b.ad().a(this, getCanaryWorld().getName(), i0);
                this.cp = -1;
                this.cm = -1;
                this.cn = -1;
            } //
        }
    }

    private void b(TileEntity tileentity) {
        if (tileentity != null) {
            // CanaryMod: SignShowHook
            if (tileentity instanceof TileEntitySign) {
                SignShowHook hook = new SignShowHook(this.getPlayer(), ((TileEntitySign) tileentity).getCanarySign());
                Canary.hooks().callHook(hook);
            }
            //
            Packet packet = tileentity.m();

            if (packet != null) {
                this.a.b(packet);
            }
        }
    }

    public void a(Entity entity, int i0) {
        super.a(entity, i0);
        this.bM.b();
    }

    public EnumStatus a(int i0, int i1, int i2) {
        EnumStatus enumstatus = super.a(i0, i1, i2);

        if (enumstatus == EnumStatus.a) {
            Packet17Sleep packet17sleep = new Packet17Sleep(this, 0, i0, i1, i2);

            this.o().q().a((Entity) this, (Packet) packet17sleep);
            this.a.a(this.u, this.v, this.w, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName());
            this.a.b(packet17sleep);
        }

        return enumstatus;
    }

    public void a(boolean flag0, boolean flag1, boolean flag2) {
        if (this.bz()) {
            this.o().q().b(this, new Packet18Animation(this, 3));
        }

        super.a(flag0, flag1, flag2);
        if (this.a != null) {
            this.a.a(this.u, this.v, this.w, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName());
        }
    }

    public void a(Entity entity) {
        super.a(entity);
        this.a.b(new Packet39AttachEntity(this, this.o));
        this.a.a(this.u, this.v, this.w, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName());
    }

    protected void a(double d0, boolean flag0) {}

    public void b(double d0, boolean flag0) {
        super.a(d0, flag0);
    }

    private void ct() {
        this.cu = this.cu % 100 + 1;
    }

    public void b(int i0, int i1, int i2) {
        // CanaryMod: InventoryHook
        ContainerWorkbench container = new ContainerWorkbench(this.bK, this.q, i0, i1, i2);
        CanaryWorkbench bench = new CanaryWorkbench(container);
        InventoryHook hook = new InventoryHook(getPlayer(), bench, false);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }
        //

        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 1, bench.getInventoryName(), 9, true));
        this.bM = container;
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(int i0, int i1, int i2, String s0) {
        // CanaryMod: InventoryHook
        ContainerEnchantment container = new ContainerEnchantment(this.bK, this.q, i0, i1, i2);
        CanaryEnchantmentTable table = new CanaryEnchantmentTable(container);
        InventoryHook hook = new InventoryHook(getPlayer(), table, false);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }
        //

        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 4, s0 == null ? "" : s0, 9, s0 != null));
        this.bM = container;
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void c(int i0, int i1, int i2) {
        // TODO: Anvil wrapper
        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 8, "Repairing", 9, true));
        this.bM = new ContainerRepair(this.bK, this.q, i0, i1, i2, this);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(IInventory iinventory) {
        if (this.bM != this.bL) {
            this.h();
        }
        // CanaryMod: InventoryHook
        Inventory inventory = null;

        if (iinventory instanceof TileEntityChest) {
            inventory = ((TileEntityChest) iinventory).getCanaryChest();
        } else if (iinventory instanceof InventoryLargeChest) {
            inventory = new CanaryDoubleChest((InventoryLargeChest) iinventory);
        } else if (iinventory instanceof InventoryEnderChest) {
            inventory = new CanaryEnderChestInventory((InventoryEnderChest) iinventory, getPlayer());
        } else if (iinventory instanceof EntityMinecartChest) {
            inventory = (CanaryChestMinecart) ((EntityMinecartChest) iinventory).getCanaryEntity();
        }

        if (inventory != null) {
            InventoryHook hook = new InventoryHook(getPlayer(), inventory, false);

            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return;
            }
        }
        //

        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 0, iinventory.b(), iinventory.j_(), iinventory.c()));
        this.bM = new ContainerChest(this.bK, iinventory);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(TileEntityHopper tileentityhopper) {
        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 9, tileentityhopper.b(), tileentityhopper.j_(), tileentityhopper.c()));
        this.bM = new ContainerHopper(this.bK, tileentityhopper);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(EntityMinecartHopper entityminecarthopper) {
        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 9, entityminecarthopper.b(), entityminecarthopper.j_(), entityminecarthopper.c()));
        this.bM = new ContainerHopper(this.bK, entityminecarthopper);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(TileEntityFurnace tileentityfurnace) {
        // CanaryMod: InventoryHook
        InventoryHook hook = new InventoryHook(getPlayer(), tileentityfurnace.getCanaryFurnace(), false);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }
        //

        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 2, tileentityfurnace.b(), tileentityfurnace.j_(), tileentityfurnace.c()));
        this.bM = new ContainerFurnace(this.bK, tileentityfurnace);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(TileEntityDispenser tileentitydispenser) {
        // CanaryMod: InventoryHook
        InventoryHook hook = new InventoryHook(getPlayer(), tileentitydispenser.getCanaryDispenser(), false);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }
        //

        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, tileentitydispenser instanceof TileEntityDropper ? 10 : 3, tileentitydispenser.b(), tileentitydispenser.j_(), tileentitydispenser.c()));
        this.bM = new ContainerDispenser(this.bK, tileentitydispenser);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(TileEntityBrewingStand tileentitybrewingstand) {
        // CanaryMod: InventoryHook
        InventoryHook hook = new InventoryHook(getPlayer(), tileentitybrewingstand.getCanaryBrewingStand(), false);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }
        //

        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 5, tileentitybrewingstand.b(), tileentitybrewingstand.j_(), tileentitybrewingstand.c()));
        this.bM = new ContainerBrewingStand(this.bK, tileentitybrewingstand);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(TileEntityBeacon tileentitybeacon) {
        // TODO: Beacon
        this.ct();
        this.a.b(new Packet100OpenWindow(this.cu, 7, tileentitybeacon.b(), tileentitybeacon.j_(), tileentitybeacon.c()));
        this.bM = new ContainerBeacon(this.bK, tileentitybeacon);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
    }

    public void a(IMerchant imerchant, String s0) {
        this.ct();
        this.bM = new ContainerMerchant(this.bK, imerchant, this.q);
        this.bM.d = this.cu;
        this.bM.a((ICrafting) this);
        InventoryMerchant inventorymerchant = ((ContainerMerchant) this.bM).e();

        this.a.b(new Packet100OpenWindow(this.cu, 6, s0 == null ? "" : s0, inventorymerchant.j_(), s0 != null));
        MerchantRecipeList merchantrecipelist = imerchant.b(this);

        if (merchantrecipelist != null) {
            try {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

                dataoutputstream.writeInt(this.cu);
                merchantrecipelist.a(dataoutputstream);
                this.a.b(new Packet250CustomPayload("MC|TrList", bytearrayoutputstream.toByteArray()));
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void a(Container container, int i0, ItemStack itemstack) {
        if (!(container.a(i0) instanceof SlotCrafting)) {
            if (!this.h) {
                this.a.b(new Packet103SetSlot(container.d, i0, itemstack));
            }
        }
    }

    public void a(Container container) {
        this.a(container, container.a());
    }

    public void a(Container container, List list) {
        this.a.b(new Packet104WindowItems(container.d, list));
        this.a.b(new Packet103SetSlot(-1, -1, this.bK.o()));
    }

    public void a(Container container, int i0, int i1) {
        this.a.b(new Packet105UpdateProgressbar(container.d, i0, i1));
    }

    public void h() {
        this.a.b(new Packet101CloseWindow(this.bM.d));
        this.j();
    }

    public void i() {
        if (!this.h) {
            this.a.b(new Packet103SetSlot(-1, -1, this.bK.o()));
        }
    }

    public void j() {
        this.bM.b((EntityPlayer) this);
        this.bM = this.bL;
    }

    public void a(StatBase statbase, int i0) {
        if (statbase != null) {
            if (!statbase.f) {
                // CanaryMod: StatGained
                StatGainedHook hook = new StatGainedHook(getPlayer(), new CanaryStat(statbase));

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return;
                }
                //
                while (i0 > 100) {
                    this.a.b(new Packet200Statistic(statbase.e, 100));
                    i0 -= 100;
                }

                this.a.b(new Packet200Statistic(statbase.e, i0));
            }
        }
    }

    public void k() {
        if (this.n != null) {
            this.n.a((Entity) this);
        }

        if (this.ca) {
            this.a(true, false, false);
        }
    }

    public void l() {
        this.cm = -99999999;
    }

    public void b(String s0) {
        StringTranslate stringtranslate = StringTranslate.a();
        String s1 = stringtranslate.a(s0);

        this.a.b(new Packet3Chat(s1));
    }

    protected void m() {
        this.a.b(new Packet38EntityStatus(this.k, (byte) 9));
        super.m();
    }

    public void a(ItemStack itemstack, int i0) {
        super.a(itemstack, i0);
        if (itemstack != null && itemstack.b() != null && itemstack.b().b_(itemstack) == EnumAction.b) {
            this.o().q().b(this, new Packet18Animation(this, 5));
        }
    }

    public void a(EntityPlayer entityplayer, boolean flag0) {
        super.a(entityplayer, flag0);
        this.cp = -1;
        this.cm = -1;
        this.cn = -1;
        this.g.addAll(((EntityPlayerMP) entityplayer).g);
    }

    protected void a(PotionEffect potioneffect) {
        super.a(potioneffect);
        this.a.b(new Packet41EntityEffect(this.k, potioneffect));
    }

    protected void b(PotionEffect potioneffect) {
        super.b(potioneffect);
        this.a.b(new Packet41EntityEffect(this.k, potioneffect));
    }

    protected void c(PotionEffect potioneffect) {
        super.c(potioneffect);
        this.a.b(new Packet42RemoveEntityEffect(this.k, potioneffect));
    }

    public void a(double d0, double d1, double d2) {
        this.a.a(d0, d1, d2, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName());
    }

    public void b(Entity entity) {
        this.o().q().b(this, new Packet18Animation(entity, 6));
    }

    public void c(Entity entity) {
        this.o().q().b(this, new Packet18Animation(entity, 7));
    }

    public void n() {
        if (this.a != null) {
            this.a.b(new Packet202PlayerAbilities(this.ce));
        }
    }

    public WorldServer o() {
        return (WorldServer) this.q;
    }

    public void a(EnumGameType enumgametype) {
        this.c.a(enumgametype);
        this.a.b(new Packet70GameEvent(3, enumgametype.a()));
    }

    public void a(String s0) {
        this.a.b(new Packet3Chat(s0));
    }

    public boolean a(int i0, String s0) {
        // CanaryMod: replace permission checking with ours
        // return "seed".equals(s0) && !this.b.T() ? true : (!"tell".equals(s0) && !"help".equals(s0) && !"me".equals(s0) ? this.b.ad().e(this.bS) : true);
        if (s0.trim().isEmpty()) { // Purely checking for permission level
            return getPlayer().hasPermission("canary.world.commandblock");
        }
        if (s0.startsWith("/")) {
            s0 = s0.substring(1);
        }
        String[] split = s0.split(" ");
        if(Canary.commands().hasCommand(split[0])) {
            return Canary.commands().canUseCommand(getPlayer(), split[0]);
        }
        // Might be vanilla, so just assume
        return getPlayer().hasPermission("canary.commands.vanilla.".concat(s0.contains(" ") ? s0.split(" ")[0] : s0));
        //
    }

    public String p() {
        String s0 = this.a.a.c().toString();

        s0 = s0.substring(s0.indexOf("/") + 1);
        s0 = s0.substring(0, s0.indexOf(":"));
        return s0;
    }

    public void a(Packet204ClientInfo packet204clientinfo) {
        if (this.cl.b().containsKey(packet204clientinfo.d())) {
            this.cl.a(packet204clientinfo.d(), false);
        }

        int i0 = 256 >> packet204clientinfo.f();

        if (i0 > 3 && i0 < 15) {
            this.cr = i0;
        }

        this.cs = packet204clientinfo.g();
        this.ct = packet204clientinfo.h();
        if (this.b.I() && this.b.H().equals(this.bS)) {
            this.b.c(packet204clientinfo.i());
        }

        this.b(1, !packet204clientinfo.j());
    }

    public StringTranslate r() {
        return this.cl;
    }

    public int t() {
        return this.cs;
    }

    public void a(String s0, int i0) {
        String s1 = s0 + "\u0000" + i0;

        this.a.b(new Packet250CustomPayload("MC|TPack", s1.getBytes()));
    }

    public ChunkCoordinates b() {
        return new ChunkCoordinates(MathHelper.c(this.u), MathHelper.c(this.v + 0.5D), MathHelper.c(this.w));
    }

    // CanaryMod
    // Start: Custom Display name
    @Override
    public void setDisplayName(String name) {
        super.setDisplayName(name);
        Packet20NamedEntitySpawn pkt = new Packet20NamedEntitySpawn(this);
        for(Player p : Canary.getServer().getPlayerList()) {
            if(!p.getName().equals(this.bS)) {
                ((CanaryPlayer)p).getHandle().a.b(pkt);
            }
        }
    }

    public void updateSlot(int windowId, int slotIndex, ItemStack item) {
        this.a.b(new Packet103SetSlot(windowId, slotIndex, item));
    }

    public boolean getColorEnabled() {
        return this.ct;
    }

    public int getViewDistance() {
        return this.cr;
    }

    //
    /**
     * Get the CanaryEntity as CanaryPlayer
     * @return
     */
    public CanaryPlayer getPlayer() {
        return (CanaryPlayer) this.entity;
    }

    public CanaryNetServerHandler getServerHandler() {
        return a.getCanaryServerHandler();
    }

    @Override
    public void setDimension(CanaryWorld world) {
        super.setDimension(world);
        this.c.a((WorldServer) world.getHandle());
    }

    public void changeWorld(WorldServer srv) {
        ChunkCoordinates chunkcoordinates = srv.l();

        if (chunkcoordinates != null) {
            this.a.a((double) chunkcoordinates.a, (double) chunkcoordinates.b, (double) chunkcoordinates.c, 0.0F, 0.0F, srv.getCanaryWorld().getType().getId(), srv.getCanaryWorld().getName());
        }

        // CanaryMod: Dimension switch hook.
        Location goingTo = this.simulatePortalUse(srv.q, MinecraftServer.D().getWorld(this.getCanaryWorld().getName(), srv.q));
        CancelableHook hook = new DimensionSwitch(this.getCanaryEntity(), this.getCanaryEntity().getLocation(), goingTo);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }//

        this.b.ad().a(this, srv.getCanaryWorld().getName(), srv.getCanaryWorld().getType().getId());
        this.cp = -1;
        this.cm = -1;
        this.cn = -1;
    }
}
