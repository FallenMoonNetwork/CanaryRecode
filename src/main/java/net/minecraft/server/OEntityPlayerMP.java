package net.minecraft.server;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.canarymod.Canary;
import net.canarymod.api.CanaryEntityTracker;
import net.canarymod.api.CanaryNetServerHandler;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.world.blocks.CanaryDispenser;
import net.canarymod.api.world.blocks.CanaryDoubleChest;
import net.canarymod.api.world.blocks.CanaryFurnace;
import net.canarymod.api.world.blocks.CanarySign;
import net.canarymod.api.world.blocks.CanaryWorkbench;
import net.canarymod.api.world.position.Location;
import net.canarymod.config.Configuration;
import net.canarymod.hook.Hook;
import net.canarymod.hook.player.ExperienceHook;
import net.canarymod.hook.player.InventoryHook;
import net.canarymod.hook.player.LeftClickHook;
import net.canarymod.hook.player.RightClickHook;
import net.canarymod.hook.player.TeleportHook;
import net.canarymod.hook.world.SignHook;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

    public ONetServerHandler a;
    public OMinecraftServer b;
    public OItemInWorldManager c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set g = new HashSet();
    private int cf = -99999999;
    private int cg = -99999999;
    private boolean ch = true;
    private int ci = -99999999;
    private int cj = 60;
    private OItemStack[] ck = new OItemStack[] { null, null, null, null, null };
    private int cl = 0;
    public boolean h;
    public int i;
    public boolean j = false;
    //CanaryMod player handler
    private CanaryPlayer canaryPlayer;
    //CanaryMod net server handler - is set when ONetServerHandler is instantiated
    private CanaryNetServerHandler serverHandler;

    public OEntityPlayerMP(OMinecraftServer var1, OWorld var2, String var3, OItemInWorldManager var4) {
        super(var2);
        var4.b = this;
        this.c = var4;
        OChunkCoordinates var5 = var2.p();
        int var6 = var5.a;
        int var7 = var5.c;
        int var8 = var5.b;
        if (!var2.t.e) {
            var6 += this.bS.nextInt(20) - 10;
            var8 = var2.g(var6, var7);
            var7 += this.bS.nextInt(20) - 10;
        }

        this.c(var6 + 0.5D, var8, var7 + 0.5D, 0.0F, 0.0F);
        this.b = var1;
        this.bP = 0.0F;
        this.v = var3;
        this.bF = 0.0F;
        canaryPlayer = new CanaryPlayer(this);
    }
    
    /**
     * CanaryMod get the ServerHandler handle
     * @return the serverHandler
     */
    public CanaryNetServerHandler getServerHandler() {
        return serverHandler;
    }

    /**
     * CanaryMod set the ServerHandler handle
     * @param serverHandler the serverHandler to set
     */
    public void setServerHandler(CanaryNetServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    /**
     * CanaryMod get player handler
     * @return
     */
    public CanaryPlayer getPlayer() {
        return canaryPlayer;
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        if (var1.c("playerGameType")) {
            this.c.a(var1.f("playerGameType"));
        }

    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("playerGameType", this.c.a());
    }

    @Override
    public void a(OWorld var1) {
        super.a(var1);
    }

    @Override
    public void e_(int var1) {
        super.e_(var1);
        this.ci = -1;
    }

    public void x() {
        this.m.a((OICrafting) this);
    }

    @Override
    public OItemStack[] y() {
        return this.ck;
    }

    @Override
    protected void A() {
        this.bF = 0.0F;
    }

    @Override
    public float B() {
        return 1.62F;
    }

    @Override
    public void F_() {
        this.c.c();
        --this.cj;
        this.m.a();

        for (int var1 = 0; var1 < 5; ++var1) {
            OItemStack var2 = this.c(var1);
            if (var2 != this.ck[var1]) {
                getDimension().getEntityTracker().sendPacketToTrackedPlayer(this.getPlayer(), new CanaryPacket(new OPacket5PlayerInventory(this.bd, var1, var2)));//.a(this, new OPacket5PlayerInventory(this.bd, var1, var2));
                this.ck[var1] = var2;
            }
        }

    }

    public OItemStack c(int var1) {
        return var1 == 0 ? this.k.d() : this.k.b[var1 - 1];
    }

    @Override
    public void a(ODamageSource var1) {
        this.b.h.sendPacketToAll((new OPacket3Chat(var1.a(this))));
        this.k.k();
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        if (this.cj > 0) {
            return false;
        } else {
            if (!this.b.q && var1 instanceof OEntityDamageSource) {
                OEntity var3 = var1.a();
                if (var3 instanceof OEntityPlayer) {
                    return false;
                }

                if (var3 instanceof OEntityArrow) {
                    OEntityArrow var4 = (OEntityArrow) var3;
                    if (var4.c instanceof OEntityPlayer) {
                        return false;
                    }
                }
            }

            return super.a(var1, var2);
        }
    }

    @Override
    protected boolean C() {
        return this.b.q;
    }

    @Override
    public void d(int var1) {
        super.d(var1);
    }

    public void a(boolean var1) {
        super.F_();

        for (int var2 = 0; var2 < this.k.c(); ++var2) {
            OItemStack var3 = this.k.g_(var2);
            if (var3 != null && OItem.d[var3.c].t_() && this.a.b() <= 2) {
                OPacket var4 = ((OItemMapBase) OItem.d[var3.c]).c(var3, this.bi, this);
                if (var4 != null) {
                    this.a.b(var4);
                }
            }
        }

        if (var1 && !this.f.isEmpty()) {
            OChunkCoordIntPair var14 = (OChunkCoordIntPair) this.f.get(0);
            double var5 = var14.a(this);

            for (int var7 = 0; var7 < this.f.size(); ++var7) {
                OChunkCoordIntPair var8 = (OChunkCoordIntPair) this.f.get(var7);
                double var9 = var8.a(this);
                if (var9 < var5) {
                    var14 = var8;
                    var5 = var9;
                }
            }

            if (var14 != null) {
                boolean var17 = false;
                if (this.a.b() < 4) {
                    var17 = true;
                }

                if (var17) {
                    OWorldServer var18 = (OWorldServer) getDimension().getHandle();//this.b.a(this.w);
                    if (var18.i(var14.a << 4, 0, var14.b << 4)) {
                        OChunk var11 = var18.d(var14.a, var14.b);
                        if (var11.k) {
                            this.f.remove(var14);
                            this.a.b((new OPacket51MapChunk(var18.d(var14.a, var14.b), true, 0)));
                            List var12 = var18.c(var14.a * 16, 0, var14.b * 16, var14.a * 16 + 16, 256, var14.b * 16 + 16);

                            for (int var13 = 0; var13 < var12.size(); ++var13) {
                                this.a((OTileEntity) var12.get(var13));
                            }
                        }
                    }
                }
            }
        }

        if (this.J) {
            if (this.m != this.l) {
                this.F();
            }

            if (this.bh != null) {
                this.b(this.bh);
            } else {
                this.K += 0.0125F;
                if (this.K >= 1.0F) {
                    this.K = 1.0F;
                    this.I = 10;
                    boolean var15 = false;
                    byte var16;
                    if (this.w == -1) {
                        var16 = 0;
                    } else {
                        var16 = -1;
                    }
                    Location location = getPlayer().getLocation();
                    int currDim = getPlayer().getDimension().getType().getId();
                    location.setDimensionId(currDim == 0 ? -1 : 0);
                    TeleportHook hook = new TeleportHook(getPlayer(), location, true);
                    Canary.hooks().callHook(hook);
                    if (!hook.isCanceled()) {
                        this.b.h.switchDimension(this, var16, true);
                        this.ci = -1;
                        this.cf = -1;
                        this.cg = -1;
                        this.a(OAchievementList.x);
                    }
                }
            }

            this.J = false;
        } else {
            if (this.K > 0.0F) {
                this.K -= 0.05F;
            }

            if (this.K < 0.0F) {
                this.K = 0.0F;
            }
        }

        if (this.I > 0) {
            --this.I;
        }

        if(this.ap != this.cf){
            //Check if health is enabled
            if(!Configuration.getWorldConfig(bi.getCanaryDimension().getName()).isHealthEnabled()){
                ap = 20;
                az = false;
            }
            else{
                //Call hook
                //CancelableHook hook = Canary.hooks().callCancelableHook(new HealthChangeHook());
                //if(hook.isCancelled){
                //  ap = cf;
                //}
            }
        }
        
        if (this.aD() != this.cf || this.cg != this.foodStats.getFoodLevel() || this.foodStats.getFoodSaturationLevel() == 0.0F != this.ch) {
            this.a.b((new OPacket8UpdateHealth(this.aD(), this.foodStats.getFoodLevel(), this.foodStats.getFoodSaturationLevel())));
            this.cf = this.aD();
            this.cg = this.foodStats.getFoodLevel();
            this.ch = this.foodStats.getFoodSaturationLevel() == 0.0F;
        }

        //CanaryMod start - Experience Update
        if (this.N != this.ci) {
            if(!Configuration.getWorldConfig(bi.getCanaryDimension().getName()).isExperienceEnabled()){ //CanaryMod - check if Experience is enabled
                N = 0;
                M = 0;
            }
            else{ //Call hook
                ExperienceHook hook = new ExperienceHook(canaryPlayer, ci, N);
                Canary.hooks().callHook(hook);
                if(hook.isCanceled()){
                    N = ci;
                }
            }
        }
        
        if(this.N != this.ci){
            this.ci = this.N;
            this.a.b((new OPacket43Experience(this.O, this.N, this.M)));
        }

    }

    @Override
    public void e(int var1) {
        if (this.w == 1 && var1 == 1) {
            this.a(OAchievementList.C);
            this.bi.e(this);
            this.j = true;
            this.a.b((new OPacket70Bed(4, 0)));
        } else {
            this.a(OAchievementList.B);
            OChunkCoordinates var2 = getDimension().getHandle().d();//this.b.a(var1).d();
            if (var2 != null) {
                this.a.a(var2.a, var2.b, var2.c, 0.0F, 0.0F, this.w, this.bi.getCanaryDimension().getName());
            }

            this.b.h.switchDimension(this, 1, true);
            this.ci = -1;
            this.cf = -1;
            this.cg = -1;
        }

    }

    private void a(OTileEntity var1) {
        if (var1 != null) {
            
            //CanaryMod start - onSignShow
            if (var1 instanceof OTileEntitySign) {
                CanarySign sign = new CanarySign((OTileEntitySign) var1);
                Canary.hooks().callHook(new SignHook(canaryPlayer, sign, false));
            }
            //CanaryMod end
            
            OPacket var2 = var1.d();
            if (var2 != null) {
                this.a.b(var2);
            }
        }

    }

    @Override
    public void a(OEntity var1, int var2) {
        if (!var1.bE) {
            OEntityTracker var3 = ((CanaryEntityTracker)getDimension().getEntityTracker()).getHandle();
            if (var1 instanceof OEntityItem) {
                var3.a(var1, new OPacket22Collect(var1.bd, this.bd));
            }

            if (var1 instanceof OEntityArrow) {
                var3.a(var1, new OPacket22Collect(var1.bd, this.bd));
            }

            if (var1 instanceof OEntityXPOrb) {
                var3.a(var1, new OPacket22Collect(var1.bd, this.bd));
            }
        }

        super.a(var1, var2);
        this.m.a();
    }

    @Override
    public void C_() {
        if (!this.t) {
            Canary.hooks().callHook(new LeftClickHook(getPlayer(), null)); //CanaryMod - onArmSwing
            this.u = -1;
            this.t = true;
            OEntityTracker var1 = ((CanaryEntityTracker)getDimension().getEntityTracker()).getHandle();
            var1.a(this, new OPacket18Animation(this, 1));
        }

    }

    public void E() {
    }

    @Override
    public OEnumStatus a(int var1, int var2, int var3) {
        OEnumStatus var4 = super.a(var1, var2, var3);
        if (var4 == OEnumStatus.a) {
            OEntityTracker var5 = ((CanaryEntityTracker)getDimension().getEntityTracker()).getHandle();
            OPacket17Sleep var6 = new OPacket17Sleep(this, 0, var1, var2, var3);
            var5.a(this, var6);
            this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt, this.w, this.bi.getCanaryDimension().getName());
            this.a.b(var6);
        }

        return var4;
    }

    @Override
    public void a(boolean var1, boolean var2, boolean var3) {
        if (this.Z()) {
            OEntityTracker var4 = ((CanaryEntityTracker)getDimension().getEntityTracker()).getHandle();
            var4.b(this, new OPacket18Animation(this, 3));
        }

        super.a(var1, var2, var3);
        if (this.a != null) {
            this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt, this.w, this.bi.getCanaryDimension().getName());
        }

    }

    @Override
    public void b(OEntity var1) {
        super.b(var1);
        this.a.b((new OPacket39AttachEntity(this, this.bh)));
        this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt, this.w, this.bi.getCanaryDimension().getName());
    }

    @Override
    protected void a(double var1, boolean var3) {
    }

    public void b(double var1, boolean var3) {
        super.a(var1, var3);
    }

    private void bc() {
        this.cl = this.cl % 100 + 1;
    }

    @Override
    public void b(int var1, int var2, int var3) {
        //CanaryMod - onInventoryOpen - Workbench
        CanaryWorkbench container = new CanaryWorkbench(new OContainerWorkbench(this.k, this.bi, var1, var2, var3));
        InventoryHook hook = new InventoryHook(canaryPlayer, container.getInventory(), false);
        Canary.hooks().callHook(hook);
        if(!hook.isCanceled()){
            this.bc();
            this.a.b((new OPacket100OpenWindow(this.cl, 1, container.getInventoryName(), container.getInventorySize())));
            this.m = container.getHandle();
            this.m.f = this.cl;
            this.m.a((OICrafting) this);
            this.m.setInventory(container.getInventory());
        }
    }

    @Override
    public void c(int var1, int var2, int var3) {
        this.bc();
        this.a.b((new OPacket100OpenWindow(this.cl, 4, "Enchanting", 9)));
        this.m = new OContainerEnchantment(this.k, this.bi, var1, var2, var3);
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    @Override
    public void a(OIInventory var1) {
        //CanaryMod - onOpenInventory - Chest/DoubleChest
        CanaryInventory inv = null;
        InventoryHook hook = null;
        if(var1 instanceof OTileEntityChest){
            inv = (CanaryInventory) ((OTileEntityChest)var1).getChest().getInventory();
        }
        else if (var1 instanceof OInventoryLargeChest){
            inv = (CanaryInventory) new CanaryDoubleChest((OInventoryLargeChest)var1).getInventory();
        }
        if(inv != null){
            hook = new InventoryHook(canaryPlayer, inv, false);
            Canary.hooks().callHook(hook);
        }
        if(hook == null || !hook.isCanceled()){
            this.bc();
            this.a.b((new OPacket100OpenWindow(this.cl, 0, var1.getInventoryName(), var1.getInventorySize())));
            this.m = new OContainerChest(this.k, var1);
            this.m.f = this.cl;
            this.m.a((OICrafting) this);
            this.m.setInventory(inv);
        }
    }

    @Override
    public void a(OTileEntityFurnace var1) {
        //CanaryMod - onOpenInventory - Furnace
        CanaryFurnace furnace = var1.getFurnace();
        InventoryHook hook = new InventoryHook(canaryPlayer, furnace.getInventory(), false);
        Canary.hooks().callHook(hook);
        if(!hook.isCanceled()){
            this.bc();
            this.a.b((new OPacket100OpenWindow(this.cl, 2, var1.getInventoryName(), var1.getInventorySize())));
            this.m = new OContainerFurnace(this.k, var1);
            this.m.f = this.cl;
            this.m.a((OICrafting) this);
            this.m.setInventory(furnace.getInventory());
        }
    }

    @Override
    public void a(OTileEntityDispenser var1) {
      //CanaryMod - onOpenInventory - Dispenser
        CanaryDispenser dispenser = var1.getDispenser();
        InventoryHook hook = new InventoryHook(canaryPlayer, dispenser.getInventory(), false);
        Canary.hooks().callHook(hook);
        if(!hook.isCanceled()){
            this.bc();
            this.a.b((new OPacket100OpenWindow(this.cl, 3, var1.getInventoryName(), var1.getInventorySize())));
            this.m = new OContainerDispenser(this.k, var1);
            this.m.f = this.cl;
            this.m.a((OICrafting) this);
            this.m.setInventory(dispenser.getInventory());
        }
    }

    @Override
    public void a(OTileEntityBrewingStand var1) {
        this.bc();
        this.a.b((new OPacket100OpenWindow(this.cl, 5, var1.getInventoryName(), var1.getInventorySize())));
        this.m = new OContainerBrewingStand(this.k, var1);
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    @Override
    public void a(OContainer var1, int var2, OItemStack var3) {
        if (!(var1.b(var2) instanceof OSlotCrafting)) {
            if (!this.h) {
                this.a.b((new OPacket103SetSlot(var1.f, var2, var3)));
            }
        }
    }

    public void a(OContainer var1) {
        this.a(var1, var1.b());
    }

    @Override
    public void a(OContainer var1, List var2) { 
        this.a.b((new OPacket104WindowItems(var1.f, var2)));
        this.a.b((new OPacket103SetSlot(-1, -1, this.k.l())));
    }

    @Override
    public void a(OContainer var1, int var2, int var3) {
        this.a.b((new OPacket105UpdateProgressbar(var1.f, var2, var3)));
    }

    @Override
    public void a(OItemStack var1) {
    }

    @Override
    public void F() {
        this.a.b((new OPacket101CloseWindow(this.m.f)));
        this.H();
    }

    public void G() {
        if (!this.h) {
            this.a.b((new OPacket103SetSlot(-1, -1, this.k.l())));
        }
    }

    public void H() {
        this.m.onInventoryClose((OEntityPlayer) this);
        this.m = this.l;
    }

    @Override
    public void a(OStatBase var1, int var2) {
        if (var1 != null) {
            if (!var1.f) {
                while (var2 > 100) {
                    this.a.b((new OPacket200Statistic(var1.e, 100)));
                    var2 -= 100;
                }

                this.a.b((new OPacket200Statistic(var1.e, var2)));
            }

        }
    }

    public void I() {
        if (this.bh != null) {
            this.b(this.bh);
        }

        if (this.bg != null) {
            this.bg.b((OEntity) this);
        }

        if (this.E) {
            this.a(true, false, false);
        }

    }

    public void D_() {
        this.cf = -99999999;
    }

    @Override
    public void a(String var1) {
        OStringTranslate var2 = OStringTranslate.a();
        String var3 = var2.b(var1);
        this.a.b((new OPacket3Chat(var3)));
    }

    @Override
    protected void K() {
        this.a.b((new OPacket38EntityStatus(this.bd, (byte) 9)));
        super.K();
    }

    @Override
    public void a(OItemStack var1, int var2) {
        //super.a(var1, var2);
        if (var1 != null && var1.a() != null && var1.a().d(var1) == OEnumAction.b) {
            RightClickHook hook = new RightClickHook(getPlayer(), null, null, var1.getCanaryItem(), null, Hook.Type.EAT);
            Canary.hooks().callHook(hook);
            if(!hook.isCanceled()){
                super.a(var1, var2);
                //OEntityTracker var3 = this.b.b(this.w);
                //CanaryMod get the real entity tracker
                OEntityTracker var3 = this.getDimension().getEntityTracker().getHandle();
                var3.b(this, new OPacket18Animation(this, 5));
            }
            else{
                this.a.b((OPacket) (new OPacket38EntityStatus(this.S, (byte) 9)));
                this.a.b((new OPacket8UpdateHealth(this.aD(), this.foodStats.getFoodLevel(), this.foodStats.getFoodSaturationLevel())));
            }
        }
        else{
            super.a(var1, var2);
        }
    }

    @Override
    protected void b(OPotionEffect var1) {
        super.b(var1);
        this.a.b((new OPacket41EntityEffect(this.bd, var1)));
    }

    @Override
    protected void c(OPotionEffect var1) {
        super.c(var1);
        this.a.b((new OPacket41EntityEffect(this.bd, var1)));
    }

    @Override
    protected void d(OPotionEffect var1) {
        super.d(var1);
        this.a.b((new OPacket42RemoveEntityEffect(this.bd, var1)));
    }

    @Override
    public void a_(double var1, double var3, double var5) {
        this.a.a(var1, var3, var5, this.bs, this.bt, this.w, this.bi.getCanaryDimension().getName());
    }

    @Override
    public void c(OEntity var1) {
        OEntityTracker var2 = ((CanaryEntityTracker)getDimension().getEntityTracker()).getHandle();
        var2.b(this, new OPacket18Animation(var1, 6));
    }

    @Override
    public void d(OEntity var1) {
        OEntityTracker var2 = ((CanaryEntityTracker)getDimension().getEntityTracker()).getHandle();
        var2.b(this, new OPacket18Animation(var1, 7));
    }

    @Override
    public void L() {
        if (this.a != null) {
            this.a.b((new OPacket202PlayerAbilities(this.L)));
        }
    }
}
