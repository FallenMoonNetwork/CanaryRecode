package net.minecraft.server;

import java.text.DecimalFormat;
import java.util.Random;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.canarymod.api.inventory.CanaryBaseItem;
import net.canarymod.api.inventory.CanaryItem;

public final class ItemStack {

    public static final DecimalFormat a = new DecimalFormat("#.###");
    public int b;
    public int c;
    public int d;
    public NBTTagCompound e;
    private int f;
    private EntityItemFrame g;

    public ItemStack(Block block) {
        this(block, 1);
    }

    public ItemStack(Block block, int i0) {
        this(block.cF, i0, 0);
    }

    public ItemStack(Block block, int i0, int i1) {
        this(block.cF, i0, i1);
    }

    public ItemStack(Item item) {
        this(item.cv, 1, 0);
    }

    public ItemStack(Item item, int i0) {
        this(item.cv, i0, 0);
    }

    public ItemStack(Item item, int i0, int i1) {
        this(item.cv, i0, i1);
    }

    public ItemStack(int i0, int i1, int i2) {
        this.d = i0;
        this.b = i1;
        this.f = i2;
        if (this.f < 0) {
            this.f = 0;
        }
    }

    public static ItemStack a(NBTTagCompound nbttagcompound) {
        ItemStack itemstack = new ItemStack();

        itemstack.c(nbttagcompound);
        return itemstack.b() != null ? itemstack : null;
    }

    private ItemStack() {}

    public ItemStack a(int i0) {
        ItemStack itemstack = new ItemStack(this.d, i0, this.f);

        if (this.e != null) {
            itemstack.e = (NBTTagCompound) this.e.b();
        }

        this.b -= i0;
        return itemstack;
    }

    public Item b() {
        return Item.g[this.d];
    }

    public boolean a(EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        boolean flag0 = this.b().a(this, entityplayer, world, i0, i1, i2, i3, f0, f1, f2);

        if (flag0) {
            entityplayer.a(StatList.E[this.d], 1);
        }

        return flag0;
    }

    public float a(Block block) {
        return this.b().a(this, block);
    }

    public ItemStack a(World world, EntityPlayer entityplayer) {
        return this.b().a(this, world, entityplayer);
    }

    public ItemStack b(World world, EntityPlayer entityplayer) {
        return this.b().b(this, world, entityplayer);
    }

    public NBTTagCompound b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("id", (short) this.d);
        nbttagcompound.a("Count", (byte) this.b);
        nbttagcompound.a("Damage", (short) this.f);
        if (this.e != null) {
            nbttagcompound.a("tag", (NBTBase) this.e);
        }

        return nbttagcompound;
    }

    public void c(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.d("id");
        this.b = nbttagcompound.c("Count");
        this.f = nbttagcompound.d("Damage");
        if (this.f < 0) {
            this.f = 0;
        }

        if (nbttagcompound.b("tag")) {
            this.e = nbttagcompound.l("tag");
        }
    }

    public int e() {
        return this.b().m();
    }

    public boolean f() {
        return this.e() > 1 && (!this.g() || !this.i());
    }

    public boolean g() {
        return Item.g[this.d].o() > 0;
    }

    public boolean h() {
        return Item.g[this.d].n();
    }

    public boolean i() {
        return this.g() && this.f > 0;
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.f;
    }

    public void b(int i0) {
        this.f = i0;
        if (this.f < 0) {
            this.f = 0;
        }
    }

    public int l() {
        return Item.g[this.d].o();
    }

    public boolean a(int i0, Random random) {
        if (!this.g()) {
            return false;
        } else {
            if (i0 > 0) {
                int i1 = EnchantmentHelper.a(Enchantment.t.z, this);
                int i2 = 0;

                for (int i3 = 0; i1 > 0 && i3 < i0; ++i3) {
                    if (EnchantmentDurability.a(this, i1, random)) {
                        ++i2;
                    }
                }

                i0 -= i2;
                if (i0 <= 0) {
                    return false;
                }
            }

            this.f += i0;
            return this.f > this.l();
        }
    }

    public void a(int i0, EntityLivingBase entitylivingbase) {
        if (!(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).bG.d) {
            if (this.g()) {
                if (this.a(i0, entitylivingbase.aC())) {
                    entitylivingbase.a(this);
                    --this.b;
                    if (entitylivingbase instanceof EntityPlayer) {
                        EntityPlayer entityplayer = (EntityPlayer) entitylivingbase;

                        entityplayer.a(StatList.F[this.d], 1);
                        if (this.b == 0 && this.b() instanceof ItemBow) {
                            entityplayer.by();
                        }
                    }

                    if (this.b < 0) {
                        this.b = 0;
                    }

                    this.f = 0;
                }
            }
        }
    }

    public void a(EntityLivingBase entitylivingbase, EntityPlayer entityplayer) {
        boolean flag0 = Item.g[this.d].a(this, entitylivingbase, (EntityLivingBase) entityplayer);

        if (flag0) {
            entityplayer.a(StatList.E[this.d], 1);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, EntityPlayer entityplayer) {
        boolean flag0 = Item.g[this.d].a(this, world, i0, i1, i2, i3, entityplayer);

        if (flag0) {
            entityplayer.a(StatList.E[this.d], 1);
        }
    }

    public boolean b(Block block) {
        return Item.g[this.d].a(block);
    }

    public boolean a(EntityPlayer entityplayer, EntityLivingBase entitylivingbase) {
        return Item.g[this.d].a(this, entityplayer, entitylivingbase);
    }

    public ItemStack m() {
        ItemStack itemstack = new ItemStack(this.d, this.b, this.f);

        if (this.e != null) {
            itemstack.e = (NBTTagCompound) this.e.b();
        }

        return itemstack;
    }

    public static boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack == null && itemstack1 == null ? true : (itemstack != null && itemstack1 != null ? (itemstack.e == null && itemstack1.e != null ? false : itemstack.e == null || itemstack.e.equals(itemstack1.e)) : false);
    }

    public static boolean b(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack == null && itemstack1 == null ? true : (itemstack != null && itemstack1 != null ? itemstack.d(itemstack1) : false);
    }

    private boolean d(ItemStack itemstack) {
        return this.b != itemstack.b ? false : (this.d != itemstack.d ? false : (this.f != itemstack.f ? false : (this.e == null && itemstack.e != null ? false : this.e == null || this.e.equals(itemstack.e))));
    }

    public boolean a(ItemStack itemstack) {
        return this.d == itemstack.d && this.f == itemstack.f;
    }

    public String a() {
        return Item.g[this.d].d(this);
    }

    public static ItemStack b(ItemStack itemstack) {
        return itemstack == null ? null : itemstack.m();
    }

    public String toString() {
        return this.b + "x" + Item.g[this.d].a() + "@" + this.f;
    }

    public void a(World world, Entity entity, int i0, boolean flag0) {
        if (this.c > 0) {
            --this.c;
        }

        Item.g[this.d].a(this, world, entity, i0, flag0);
    }

    public void a(World world, EntityPlayer entityplayer, int i0) {
        entityplayer.a(StatList.D[this.d], i0);
        Item.g[this.d].d(this, world, entityplayer);
    }

    public int n() {
        return this.b().d_(this);
    }

    public EnumAction o() {
        return this.b().c_(this);
    }

    public void b(World world, EntityPlayer entityplayer, int i0) {
        this.b().a(this, world, entityplayer, i0);
    }

    public boolean p() {
        return this.e != null;
    }

    public NBTTagCompound q() {
        return this.e;
    }

    public NBTTagList r() {
        return this.e == null ? null : (NBTTagList) this.e.a("ench");
    }

    public void d(NBTTagCompound nbttagcompound) {
        this.e = nbttagcompound;
    }

    public String s() {
        String s0 = this.b().l(this);

        if (this.e != null && this.e.b("display")) {
            NBTTagCompound nbttagcompound = this.e.l("display");

            if (nbttagcompound.b("Name")) {
                s0 = nbttagcompound.i("Name");
            }
        }

        return s0;
    }

    public void c(String s0) {
        if (this.e == null) {
            this.e = new NBTTagCompound("tag");
        }

        if (!this.e.b("display")) {
            this.e.a("display", new NBTTagCompound());
        }

        this.e.l("display").a("Name", s0);
    }

    public void t() {
        if (this.e != null) {
            if (this.e.b("display")) {
                NBTTagCompound nbttagcompound = this.e.l("display");

                nbttagcompound.o("Name");
                if (nbttagcompound.d()) {
                    this.e.o("display");
                    if (this.e.d()) {
                        this.d((NBTTagCompound) null);
                    }
                }
            }
        }
    }

    public boolean u() {
        return this.e == null ? false : (!this.e.b("display") ? false : this.e.l("display").b("Name"));
    }

    public boolean x() {
        return !this.b().e_(this) ? false : !this.y();
    }

    public void a(Enchantment enchantment, int i0) {
        if (this.e == null) {
            this.d(new NBTTagCompound());
        }

        if (!this.e.b("ench")) {
            this.e.a("ench", (NBTBase) (new NBTTagList("ench")));
        }

        NBTTagList nbttaglist = (NBTTagList) this.e.a("ench");
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.a("id", (short) enchantment.z);
        nbttagcompound.a("lvl", (short) ((byte) i0));
        nbttaglist.a((NBTBase) nbttagcompound);
    }

    public boolean y() {
        return this.e != null && this.e.b("ench");
    }

    public void a(String s0, NBTBase nbtbase) {
        if (this.e == null) {
            this.d(new NBTTagCompound());
        }

        this.e.a(s0, nbtbase);
    }

    public boolean z() {
        return this.b().z();
    }

    public boolean A() {
        return this.g != null;
    }

    public void a(EntityItemFrame entityitemframe) {
        this.g = entityitemframe;
    }

    public EntityItemFrame B() {
        return this.g;
    }

    public int C() {
        return this.p() && this.e.b("RepairCost") ? this.e.e("RepairCost") : 0;
    }

    public void c(int i0) {
        if (!this.p()) {
            this.e = new NBTTagCompound("tag");
        }

        this.e.a("RepairCost", i0);
    }

    public Multimap D() {
        Object object;

        if (this.p() && this.e.b("AttributeModifiers")) {
            object = HashMultimap.create();
            NBTTagList nbttaglist = this.e.m("AttributeModifiers");

            for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.b(i0);
                AttributeModifier attributemodifier = SharedMonsterAttributes.a(nbttagcompound);

                if (attributemodifier.a().getLeastSignificantBits() != 0L && attributemodifier.a().getMostSignificantBits() != 0L) {
                    ((Multimap) object).put(nbttagcompound.i("AttributeName"), attributemodifier);
                }
            }
        } else {
            object = this.b().h();
        }

        return (Multimap) object;
    }

    // CanaryMod
    public CanaryItem getCanaryItem() {
        return new CanaryItem(this);
    }

    public CanaryBaseItem getBaseItem() {
        return this.b().getBaseItem();
    }
    //
}
