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
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.vehicle.CanaryChestMinecart;
import net.canarymod.api.inventory.CanaryAnimalInventory;
import net.canarymod.api.inventory.CanaryEnderChestInventory;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.NativeCustomStorageInventory;
import net.canarymod.api.statistics.CanaryStat;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.blocks.CanaryAnvil;
import net.canarymod.api.world.blocks.CanaryDoubleChest;
import net.canarymod.api.world.blocks.CanaryEnchantmentTable;
import net.canarymod.api.world.blocks.CanaryWorkbench;
import net.canarymod.api.world.position.Location;
import net.canarymod.config.Configuration;
import net.canarymod.config.WorldConfiguration;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.DimensionSwitchHook;
import net.canarymod.hook.player.ExperienceHook;
import net.canarymod.hook.player.HealthChangeHook;
import net.canarymod.hook.player.InventoryHook;
import net.canarymod.hook.player.PlayerDeathHook;
import net.canarymod.hook.player.PortalUseHook;
import net.canarymod.hook.player.SignShowHook;
import net.canarymod.hook.player.StatGainedHook;
import net.canarymod.hook.player.TeleportHook;

public class EntityPlayerMP extends EntityPlayer implements ICrafting {

    private String bN = "en_US";
    public NetServerHandler a;
    public MinecraftServer b;
    public ItemInWorldManager c;
    public double d;
    public double e;
    public final List f = new LinkedList();
    public final List g = new LinkedList();
    private float bO = Float.MIN_VALUE;
    private float bP = -1.0E8F;
    private int bQ = -99999999;
    private boolean bR = true;
    private int bS = -99999999;
    private int bT = 60;
    private int bU;
    private int bV;
    private boolean bW = true;
    private int bX;
    public boolean h;
    public int i;
    public boolean j;

    public EntityPlayerMP(MinecraftServer minecraftserver, World world, String s0, ItemInWorldManager iteminworldmanager) {
        super(world, s0);
        WorldConfiguration cfg = Configuration.getWorldConfig(world.getCanaryWorld().getFqName());
        iteminworldmanager.b = this;
        this.c = iteminworldmanager;
        this.bU = minecraftserver.af().o();
        ChunkCoordinates chunkcoordinates = world.K();
        int i0 = chunkcoordinates.a;
        int i1 = chunkcoordinates.c;
        int i2 = chunkcoordinates.b;

        if (!world.t.g && world.N().r() != EnumGameType.d) {
            int i3 = Math.max(5, cfg.getSpawnProtectionSize() - 6);

            i0 += this.ab.nextInt(i3 * 2) - i3;
            i1 += this.ab.nextInt(i3 * 2) - i3;
            i2 = world.i(i0, i1);
        }

        this.b = minecraftserver;
        this.Y = 0.0F;
        this.N = 0.0F;
        this.b((double) i0 + 0.5D, (double) i2, (double) i1 + 0.5D, 0.0F, 0.0F);

        while (!world.a((Entity) this, this.E).isEmpty()) {
            this.b(this.u, this.v + 1.0D, this.w);
        }

        this.entity = new CanaryPlayer(this); // CanaryMod: wrap entity
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("playerGameType")) {
            if (MinecraftServer.F().ao()) {
                this.c.a(MinecraftServer.F().h());
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
        this.bS = -1;
    }

    public void d() {
        this.bp.a((ICrafting) this);
    }

    protected void d_() {
        this.N = 0.0F;
    }

    public float f() {
        return 1.62F;
    }

    public void l_() {
        this.c.a();
        --this.bT;
        this.bp.b();
        if (!this.q.I && !this.bp.a((EntityPlayer) this)) {
            this.i();
            this.bp = this.bo;
        }

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

                    this.p().q().a(this, chunk);
                }
            }
        }
    }

    public void h() {
        try {
            super.l_();

            for (int i0 = 0; i0 < this.bn.j_(); ++i0) {
                ItemStack itemstack = this.bn.a(i0);

                if (itemstack != null && Item.g[itemstack.d].f() && this.a.f() <= 5) {
                    Packet packet = ((ItemMapBase) Item.g[itemstack.d]).c(itemstack, this.q, this);

                    if (packet != null) {
                        this.a.b(packet);
                    }
                }
            }

            // CanaryMod: HealthChange / HealthEnabled
            if (this.aM() != this.bP && bP != -99999999 && this.getPlayer() != null) {
                // updates your health when it is changed.
                if (!Configuration.getWorldConfig(getCanaryWorld().getFqName()).isHealthEnabled()) {
                    super.b(this.aY());
                    this.M = false;
                } else {
                    HealthChangeHook hook = (HealthChangeHook) new HealthChangeHook(getPlayer(), bP, this.aM()).call();
                    if (hook.isCanceled()) {
                        super.b(this.bP);
                    }
                }
            }
            //

            if (this.aM() != this.bP || this.bQ != this.bq.a() || this.bq.e() == 0.0F != this.bR) {
                this.a.b(new Packet8UpdateHealth(this.aM(), this.bq.a(), this.bq.e()));
                this.bP = this.aM();
                this.bQ = this.bq.a();
                this.bR = this.bq.e() == 0.0F;
            }

            if (this.aM() + this.bm() != this.bO) {
                this.bO = this.aM() + this.bm();
                Collection collection = this.bL().a(ScoreObjectiveCriteria.f);
                Iterator iterator = collection.iterator();

                while (iterator.hasNext()) {
                    ScoreObjective scoreobjective = (ScoreObjective) iterator.next();

                    this.bL().a(this.am(), scoreobjective).a(Arrays.asList(new EntityPlayer[]{ this }));
                }
                // CanaryMod: ExperienceHook / ExperienceEnabled
                if (!Configuration.getWorldConfig(getCanaryWorld().getFqName()).isExperienceEnabled()) {
                    this.bI = 0;
                    this.bH = 0;
                } else {
                    ExperienceHook hook = new ExperienceHook(getPlayer(), this.bS, this.bI);

                    if (!hook.isCanceled()) {
                        this.bS = this.bI;
                        this.a.b(new Packet43Experience(this.bJ, this.bI, this.bH));
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
        PlayerDeathHook hook = (PlayerDeathHook) new PlayerDeathHook(getPlayer(), damagesource.getCanaryDamageSource(), this.aQ().b().toString()).call();
        // Check Death Message enabled
        if (Configuration.getServerConfig().isDeathMessageEnabled()) {
            this.b.af().a(ChatMessageComponent.e(hook.getDeathMessage()));
        }
        if (!this.q.O().b("keepInventory")) {
            this.bn.m();
        }
        //

        Collection collection = this.q.X().a(ScoreObjectiveCriteria.c);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreObjective scoreobjective = (ScoreObjective) iterator.next();
            Score score = this.bL().a(this.am(), scoreobjective);

            score.a();
        }

        EntityLivingBase entitylivingbase = this.aR();

        if (entitylivingbase != null) {
            entitylivingbase.b(this, this.bb);
        }

        this.a(StatList.y, 1);
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.aq()) {
            return false;
        } else {
            // CanaryMod moved pvp to per-world config
            boolean haspvp = Configuration.getWorldConfig(getCanaryWorld().getFqName()).isPvpEnabled();
            boolean flag0 = this.b.V() && haspvp && "fall".equals(damagesource.o);

            if (!flag0 && this.bT > 0 && damagesource != DamageSource.i) {
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

                return super.a(damagesource, f0);
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        // CanaryMod moved pvp to per-world config
        boolean haspvp = Configuration.getWorldConfig(getCanaryWorld().getFqName()).isPvpEnabled();
        return !haspvp ? false : super.a(entityplayer);
    }

    public void b(int i0) {
        if (this.ar == 1 && i0 == 1) {
            this.a((StatBase) AchievementList.C);
            this.q.e((Entity) this);
            this.j = true;
            this.a.b(new Packet70GameEvent(4, 0));
        } else {
            if (this.ar == 0 && i0 == 1) {
                this.a((StatBase) AchievementList.B);
                ChunkCoordinates chunkcoordinates = this.b.getWorld(this.getCanaryWorld().getName(), i0).l();

                if (chunkcoordinates != null) {
                    this.a.a((double) chunkcoordinates.a, (double) chunkcoordinates.b, (double) chunkcoordinates.c, 0.0F, 0.0F, getCanaryWorld().getType().getId(), getCanaryWorld().getName(), TeleportHook.TeleportCause.PORTAL);
                }

                i0 = 1;
            } else {
                this.a((StatBase) AchievementList.x);
            }

            // CanaryMod onPortalUse && onDimensionSwitch
            Location goingTo = simulatePortalUse(i0, MinecraftServer.F().getWorld(getCanaryWorld().getName(), i0));

            PortalUseHook puh = (PortalUseHook) new PortalUseHook(getPlayer(), goingTo).call();
            DimensionSwitchHook dsh = (DimensionSwitchHook) new DimensionSwitchHook(this.getCanaryEntity(), this.getCanaryEntity().getLocation(), goingTo).call();

            if (puh.isCanceled() || dsh.isCanceled()) {
                return;
            } //
            else {
                this.b.af().a(this, getCanaryWorld().getName(), i0);
                this.bS = -1;
                this.bP = -1.0F;
                this.bQ = -1;
            } //
        }
    }

    private void b(TileEntity tileentity) {
        if (tileentity != null) {
            // CanaryMod: SignShowHook
            if (tileentity instanceof TileEntitySign) {
                new SignShowHook(this.getPlayer(), ((TileEntitySign) tileentity).getCanarySign()).call();
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
        this.bp.b();
    }

    public EnumStatus a(int i0, int i1, int i2) {
        EnumStatus enumstatus = super.a(i0, i1, i2);

        if (enumstatus == EnumStatus.a) {
            Packet17Sleep packet17sleep = new Packet17Sleep(this, 0, i0, i1, i2);

            this.p().q().a((Entity) this, (Packet) packet17sleep);
            this.a.a(this.u, this.v, this.w, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName(), TeleportHook.TeleportCause.BED);
            this.a.b(packet17sleep);
        }

        return enumstatus;
    }

    public void a(boolean flag0, boolean flag1, boolean flag2) {
        if (this.bg()) {
            this.p().q().b(this, new Packet18Animation(this, 3));
        }

        super.a(flag0, flag1, flag2);
        if (this.a != null) {
            this.a.a(this.u, this.v, this.w, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName(), TeleportHook.TeleportCause.BED);
        }
    }

    public void a(Entity entity) {
        super.a(entity);
        this.a.b(new Packet39AttachEntity(0, this, this.o));
        this.a.a(this.u, this.v, this.w, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName(), TeleportHook.TeleportCause.MOUNT_CHANGE);
    }

    protected void a(double d0, boolean flag0) {}

    public void b(double d0, boolean flag0) {
        super.a(d0, flag0);
    }

    public void a(TileEntity tileentity) {
        if (tileentity instanceof TileEntitySign) {
            ((TileEntitySign) tileentity).a((EntityPlayer) this);
            this.a.b(new Packet133TileEditorOpen(0, tileentity.l, tileentity.m, tileentity.n));
        }
    }

    private void bM() {
        this.bX = this.bX % 100 + 1;
    }

    public void b(int i0, int i1, int i2) {
        // CanaryMod: InventoryHook
        ContainerWorkbench container = new ContainerWorkbench(this.bn, this.q, i0, i1, i2);
        CanaryWorkbench bench = (CanaryWorkbench) container.getInventory();
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), bench, false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 1, bench.getInventoryName(), 9, true));
        this.bp = container;
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(int i0, int i1, int i2, String s0) {
        // CanaryMod: InventoryHook
        ContainerEnchantment container = new ContainerEnchantment(this.bn, this.q, i0, i1, i2);
        CanaryEnchantmentTable table = (CanaryEnchantmentTable) container.getInventory();
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), table, false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 4, s0 == null ? "" : s0, 9, s0 != null));
        this.bp = new ContainerEnchantment(this.bn, this.q, i0, i1, i2);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void c(int i0, int i1, int i2) {
        // CanaryMod: InventoryHook
        ContainerRepair container = new ContainerRepair(this.bn, this.q, i0, i1, i2, this);
        CanaryAnvil anvil = (CanaryAnvil) container.getInventory();
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), anvil, false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 8, "Repairing", 9, true));
        this.bp = container;
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(IInventory iinventory) { // Open Inventory
        if (this.bp != this.bo) {
            this.i();
        }
        // CanaryMod: InventoryHook
        Inventory inventory = null;
        ContainerChest container = new ContainerChest(this.bn, iinventory);

        if (iinventory instanceof TileEntityChest) {
            inventory = ((TileEntityChest) iinventory).getCanaryChest();
            container.setInventory(inventory);
        } else if (iinventory instanceof InventoryLargeChest) {
            inventory = new CanaryDoubleChest((InventoryLargeChest) iinventory);
            container.setInventory(inventory);
        } else if (iinventory instanceof InventoryEnderChest) {
            inventory = new CanaryEnderChestInventory((InventoryEnderChest) iinventory, getPlayer());
            container.setInventory(inventory);
        } else if (iinventory instanceof EntityMinecartChest) {
            inventory = (CanaryChestMinecart) ((EntityMinecartChest) iinventory).getCanaryEntity();
            container.setInventory(inventory);
        } else if (iinventory instanceof NativeCustomStorageInventory) {
            inventory = ((NativeCustomStorageInventory) iinventory).getCanaryCustomInventory();
            container.setInventory(inventory);
        }

        if (inventory != null) {
            InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), inventory, false).call();
            if (hook.isCanceled()) {
                return;
            }
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 0, iinventory.b(), iinventory.j_(), iinventory.c()));
        this.bp = container;
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(TileEntityHopper tileentityhopper) {
        // CanaryMod: InventoryHook
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), tileentityhopper.getCanaryHopper(), false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 9, tileentityhopper.b(), tileentityhopper.j_(), tileentityhopper.c()));
        this.bp = new ContainerHopper(this.bn, tileentityhopper);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(EntityMinecartHopper entityminecarthopper) {
        // CanaryMod: InventoryHook
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), (Inventory) entityminecarthopper.getCanaryEntity(), false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 9, entityminecarthopper.b(), entityminecarthopper.j_(), entityminecarthopper.c()));
        this.bp = new ContainerHopper(this.bn, entityminecarthopper);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(TileEntityFurnace tileentityfurnace) {
        // CanaryMod: InventoryHook
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), tileentityfurnace.getCanaryFurnace(), false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 2, tileentityfurnace.b(), tileentityfurnace.j_(), tileentityfurnace.c()));
        this.bp = new ContainerFurnace(this.bn, tileentityfurnace);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(TileEntityDispenser tileentitydispenser) {
        // CanaryMod: InventoryHook
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), tileentitydispenser.getCanaryDispenser(), false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, tileentitydispenser instanceof TileEntityDropper ? 10 : 3, tileentitydispenser.b(), tileentitydispenser.j_(), tileentitydispenser.c()));
        this.bp = new ContainerDispenser(this.bn, tileentitydispenser);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(TileEntityBrewingStand tileentitybrewingstand) {
        // CanaryMod: InventoryHook
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), tileentitybrewingstand.getCanaryBrewingStand(), false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 5, tileentitybrewingstand.b(), tileentitybrewingstand.j_(), tileentitybrewingstand.c()));
        this.bp = new ContainerBrewingStand(this.bn, tileentitybrewingstand);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(TileEntityBeacon tileentitybeacon) {
        // CanaryMod: InventoryHook
        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), tileentitybeacon.getCanaryBeacon(), false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 7, tileentitybeacon.b(), tileentitybeacon.j_(), tileentitybeacon.c()));
        this.bp = new ContainerBeacon(this.bn, tileentitybeacon);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
    }

    public void a(IMerchant imerchant, String s0) {
        this.bM();
        this.bp = new ContainerMerchant(this.bn, imerchant, this.q);
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
        InventoryMerchant inventorymerchant = ((ContainerMerchant) this.bp).e();

        this.a.b(new Packet100OpenWindow(this.bX, 6, s0 == null ? "" : s0, inventorymerchant.j_(), s0 != null));
        MerchantRecipeList merchantrecipelist = imerchant.b(this);

        if (merchantrecipelist != null) {
            try {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

                dataoutputstream.writeInt(this.bX);
                merchantrecipelist.a(dataoutputstream);
                this.a.b(new Packet250CustomPayload("MC|TrList", bytearrayoutputstream.toByteArray()));
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void a(EntityHorse entityhorse, IInventory iinventory) {
        if (this.bp != this.bo) {
            this.i();
        }
        // CanaryMod: InventoryHook
        Inventory inv = new CanaryAnimalInventory((AnimalChest) iinventory, (EntityAnimal) entityhorse.getCanaryEntity());

        InventoryHook hook = (InventoryHook) new InventoryHook(getPlayer(), inv, false).call();
        if (hook.isCanceled()) {
            return;
        }
        ContainerHorseInventory chi = new ContainerHorseInventory(this.bn, iinventory, entityhorse);
        chi.setInventory(inv);
        //
        this.bM();
        this.a.b(new Packet100OpenWindow(this.bX, 11, iinventory.b(), iinventory.j_(), iinventory.c(), entityhorse.k));
        this.bp = chi;
        this.bp.d = this.bX;
        this.bp.a((ICrafting) this);
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
        this.a.b(new Packet103SetSlot(-1, -1, this.bn.o()));
    }

    public void a(Container container, int i0, int i1) {
        this.a.b(new Packet105UpdateProgressbar(container.d, i0, i1));
    }

    public void i() {
        this.a.b(new Packet101CloseWindow(this.bp.d));
        this.k();
    }

    public void j() {
        if (!this.h) {
            this.a.b(new Packet103SetSlot(-1, -1, this.bn.o()));
        }
    }

    public void k() {
        this.bp.b((EntityPlayer) this);
        this.bp = this.bo;
    }

    public void a(float f0, float f1, boolean flag0, boolean flag1) {
        if (this.o != null) {
            if (f0 >= -1.0F && f0 <= 1.0F) {
                this.be = f0;
            }

            if (f1 >= -1.0F && f1 <= 1.0F) {
                this.bf = f1;
            }

            this.bd = flag0;
            this.b(flag1);
        }
    }

    public void a(StatBase statbase, int i0) {
        if (statbase != null) {
            if (!statbase.f) {
                // CanaryMod: StatGained
                StatGainedHook hook = (StatGainedHook) new StatGainedHook(getPlayer(), new CanaryStat(statbase)).call();
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

    public void l() {
        if (this.n != null) {
            this.n.a((Entity) this);
        }

        if (this.bC) {
            this.a(true, false, false);
        }
    }

    public void m() {
        this.bP = -1.0E8F;
    }

    public void a(String s0) {
        this.a.b(new Packet3Chat(ChatMessageComponent.e(s0)));
    }

    protected void n() {
        this.a.b(new Packet38EntityStatus(this.k, (byte) 9));
        super.n();
    }

    public void a(ItemStack itemstack, int i0) {
        super.a(itemstack, i0);
        if (itemstack != null && itemstack.b() != null && itemstack.b().c_(itemstack) == EnumAction.b) {
            this.p().q().b(this, new Packet18Animation(this, 5));
        }
    }

    public void a(EntityPlayer entityplayer, boolean flag0) {
        super.a(entityplayer, flag0);
        this.bS = -1;
        this.bP = -1.0F;
        this.bQ = -1;
        this.g.addAll(((EntityPlayerMP) entityplayer).g);
    }

    protected void a(PotionEffect potioneffect) {
        super.a(potioneffect);
        this.a.b(new Packet41EntityEffect(this.k, potioneffect));
    }

    protected void a(PotionEffect potioneffect, boolean flag0) {
        super.a(potioneffect, flag0);
        this.a.b(new Packet41EntityEffect(this.k, potioneffect));
    }

    protected void b(PotionEffect potioneffect) {
        super.b(potioneffect);
        this.a.b(new Packet42RemoveEntityEffect(this.k, potioneffect));
    }

    public void a(double d0, double d1, double d2) {
        this.a.a(d0, d1, d2, this.A, this.B, getCanaryWorld().getType().getId(), getCanaryWorld().getName(), TeleportHook.TeleportCause.MOVEMENT);
    }

    public void b(Entity entity) {
        this.p().q().b(this, new Packet18Animation(entity, 6));
    }

    public void c(Entity entity) {
        this.p().q().b(this, new Packet18Animation(entity, 7));
    }

    public void o() {
        if (this.a != null) {
            this.a.b(new Packet202PlayerAbilities(this.bG));
        }
    }

    public WorldServer p() {
        return (WorldServer) this.q;
    }

    public void a(EnumGameType enumgametype) {
        this.c.a(enumgametype);
        this.a.b(new Packet70GameEvent(3, enumgametype.a()));
    }

    public void a(ChatMessageComponent chatmessagecomponent) {
        this.a.b(new Packet3Chat(chatmessagecomponent));
    }

    public boolean a(int i0, String s0) {
        // CanaryMod: replace permission checking with ours
        // return "seed".equals(s0) && !this.b.V() ? true : (!"tell".equals(s0) && !"help".equals(s0) && !"me".equals(s0) ? this.b.af().e(this.bu) ? this.b.k() >= i0 : false) : true);
        if (s0.trim().isEmpty()) { // Purely checking for permission level
            return getPlayer().hasPermission("canary.world.commandblock");
        }
        if (s0.startsWith("/")) {
            s0 = s0.substring(1);
        }
        String[] split = s0.split(" ");
        if (Canary.commands().hasCommand(split[0])) {
            return Canary.commands().canUseCommand(getPlayer(), split[0]);
        }
        // Might be vanilla, so just assume
        return getPlayer().hasPermission("canary.commands.vanilla.".concat(s0.contains(" ") ? s0.split(" ")[0] : s0));
        //
    }

    public String q() {
        String s0 = this.a.a.c().toString();

        s0 = s0.substring(s0.indexOf("/") + 1);
        s0 = s0.substring(0, s0.indexOf(":"));
        return s0;
    }

    public void a(Packet204ClientInfo packet204clientinfo) {
        this.bN = packet204clientinfo.d();
        int i0 = 256 >> packet204clientinfo.f();

        if (i0 > 3 && i0 < 15) {
            this.bU = i0;
        }

        this.bV = packet204clientinfo.g();
        this.bW = packet204clientinfo.h();
        if (this.b.K() && this.b.J().equals(this.bu)) {
            this.b.c(packet204clientinfo.i());
        }

        this.b(1, !packet204clientinfo.j());
    }

    public int t() {
        return this.bV;
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
        for (Player p : Canary.getServer().getPlayerList()) {
            if (!p.getName().equals(this.bS)) {
                ((CanaryPlayer) p).getHandle().a.b(pkt);
            }
        }
    }

    public void updateSlot(int windowId, int slotIndex, ItemStack item) {
        this.a.b(new Packet103SetSlot(windowId, slotIndex, item));
    }

    public boolean getColorEnabled() {
        return this.bW;
    }

    public int getViewDistance() {
        return this.bU;
    }

    //
    /**
     * Get the CanaryEntity as CanaryPlayer
     * 
     * @return
     */
    public CanaryPlayer getPlayer() {
        return (CanaryPlayer) this.entity;
    }

    public CanaryNetServerHandler getServerHandler() {
        return a.getCanaryServerHandler();
    }

    public void setDimension(CanaryWorld world) {
        super.a(world.getHandle());
        this.c.a((WorldServer) world.getHandle());
    }

    public void changeWorld(WorldServer srv) {
        ChunkCoordinates chunkcoordinates = srv.l();

        if (chunkcoordinates != null) {
            this.a.a((double) chunkcoordinates.a, (double) chunkcoordinates.b, (double) chunkcoordinates.c, 0.0F, 0.0F, srv.getCanaryWorld().getType().getId(), srv.getCanaryWorld().getName(), TeleportHook.TeleportCause.MOVEMENT);
        }

        // CanaryMod: Dimension switch hook.
        Location goingTo = this.simulatePortalUse(srv.q, MinecraftServer.F().getWorld(this.getCanaryWorld().getName(), srv.q));
        CancelableHook hook = (CancelableHook) new DimensionSwitchHook(this.getCanaryEntity(), this.getCanaryEntity().getLocation(), goingTo).call();
        if (hook.isCanceled()) {
            return;
        }//

        this.b.af().a(this, srv.getCanaryWorld().getName(), srv.getCanaryWorld().getType().getId());
        this.bO = -1;
        this.bP = -1;
        this.bS = -1;
    }
}
