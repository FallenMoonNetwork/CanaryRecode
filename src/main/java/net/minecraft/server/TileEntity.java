package net.minecraft.server;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.world.blocks.ComplexBlock;


public class TileEntity {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    protected World k;
    public int l;
    public int m;
    public int n;
    protected boolean o;
    public int p = -1;
    public Block q;

    // CanaryMod: Variable Declaration
    public ComplexBlock complexBlock;
    private CanaryCompoundTag meta; // hold it for extra data
    // CanaryMod: End

    public TileEntity() {}

    private static void a(Class oclass0, String s0) {
        if (a.containsKey(s0)) {
            throw new IllegalArgumentException("Duplicate id: " + s0);
        } else {
            a.put(s0, oclass0);
            b.put(oclass0, s0);
        }
    }

    public World az() {
        return this.k;
    }

    public void b(World world) {
        this.k = world;
    }

    public boolean o() {
        return this.k != null;
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.l = nbttagcompound.e("x");
        this.m = nbttagcompound.e("y");
        this.n = nbttagcompound.e("z");
        if (nbttagcompound.b("Canary")) {
            this.meta = new CanaryCompoundTag(nbttagcompound.l("Canary"));
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        String s0 = (String) b.get(this.getClass());

        if (s0 == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.a("id", s0);
            nbttagcompound.a("x", this.l);
            nbttagcompound.a("y", this.m);
            nbttagcompound.a("z", this.n);
            nbttagcompound.a("Canary", meta.getHandle());
        }
    }

    public void h() {}

    public static TileEntity c(NBTTagCompound nbttagcompound) {
        TileEntity tileentity = null;

        try {
            Class oclass0 = (Class) a.get(nbttagcompound.i("id"));

            if (oclass0 != null) {
                tileentity = (TileEntity) oclass0.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (tileentity != null) {
            tileentity.a(nbttagcompound);
        } else {
            MinecraftServer.D().al().b("Skipping TileEntity with id " + nbttagcompound.i("id"));
        }

        return tileentity;
    }

    public int p() {
        if (this.p == -1) {
            this.p = this.k.h(this.l, this.m, this.n);
        }

        return this.p;
    }

    public void k_() {
        if (this.k != null) {
            this.p = this.k.h(this.l, this.m, this.n);
            this.k.b(this.l, this.m, this.n, this);
            if (this.q() != null) {
                this.k.m(this.l, this.m, this.n, this.q().cz);
            }
        }
    }

    public Block q() {
        if (this.q == null) {
            this.q = Block.r[this.k.a(this.l, this.m, this.n)];
        }

        return this.q;
    }

    public Packet m() {
        return null;
    }

    public boolean r() {
        return this.o;
    }

    public void w_() {
        this.o = true;
    }

    public void s() {
        this.o = false;
    }

    public boolean b(int i0, int i1) {
        return false;
    }

    public void i() {
        this.q = null;
        this.p = -1;
    }

    public void a(CrashReportCategory crashreportcategory) {
        crashreportcategory.a("Name", (Callable) (new CallableTileEntityName(this)));
        CrashReportCategory.a(crashreportcategory, this.l, this.m, this.n, this.q().cz, this.p());
        crashreportcategory.a("Actual block type", (Callable) (new CallableTileEntityID(this)));
        crashreportcategory.a("Actual block data value", (Callable) (new CallableTileEntityData(this)));
    }

    static Map t() {
        return b;
    }

    static {
        a(TileEntityFurnace.class, "Furnace");
        a(TileEntityChest.class, "Chest");
        a(TileEntityEnderChest.class, "EnderChest");
        a(TileEntityRecordPlayer.class, "RecordPlayer");
        a(TileEntityDispenser.class, "Trap");
        a(TileEntityDropper.class, "Dropper");
        a(TileEntitySign.class, "Sign");
        a(TileEntityMobSpawner.class, "MobSpawner");
        a(TileEntityNote.class, "Music");
        a(TileEntityPiston.class, "Piston");
        a(TileEntityBrewingStand.class, "Cauldron");
        a(TileEntityEnchantmentTable.class, "EnchantTable");
        a(TileEntityEndPortal.class, "Airportal");
        a(TileEntityCommandBlock.class, "Control");
        a(TileEntityBeacon.class, "Beacon");
        a(TileEntitySkull.class, "Skull");
        a(TileEntityDaylightDetector.class, "DLDetector");
        a(TileEntityHopper.class, "Hopper");
        a(TileEntityComparator.class, "Comparator");
    }
}
