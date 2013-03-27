package net.minecraft.server;


import java.util.Random;
import net.canarymod.api.inventory.CanaryBaseItem;
import net.canarymod.api.inventory.CanaryItem;


public final class ItemStack {

    public int a;
    public int b;
    public int c;
    public NBTTagCompound d;
    private int e;
    private EntityItemFrame f;

    public ItemStack(Block block) {
        this(block, 1);
    }

    public ItemStack(Block block, int i0) {
        this(block.cz, i0, 0);
    }

    public ItemStack(Block block, int i0, int i1) {
        this(block.cz, i0, i1);
    }

    public ItemStack(Item item) {
        this(item.cp, 1, 0);
    }

    public ItemStack(Item item, int i0) {
        this(item.cp, i0, 0);
    }

    public ItemStack(Item item, int i0, int i1) {
        this(item.cp, i0, i1);
    }

    public ItemStack(int i0, int i1, int i2) {
        this.a = 0;
        this.f = null;
        this.c = i0;
        this.a = i1;
        this.e = i2;
        if (this.e < 0) {
            this.e = 0;
        }
    }

    public static ItemStack a(NBTTagCompound nbttagcompound) {
        ItemStack itemstack = new ItemStack();

        itemstack.c(nbttagcompound);
        return itemstack.b() != null ? itemstack : null;
    }

    private ItemStack() {
        this.a = 0;
        this.f = null;
    }

    public ItemStack a(int i0) {
        ItemStack itemstack = new ItemStack(this.c, i0, this.e);

        if (this.d != null) {
            itemstack.d = (NBTTagCompound) this.d.b();
        }

        this.a -= i0;
        return itemstack;
    }

    public Item b() {
        return Item.f[this.c];
    }

    public boolean a(EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        boolean flag0 = this.b().a(this, entityplayer, world, i0, i1, i2, i3, f0, f1, f2);

        if (flag0) {
            entityplayer.a(StatList.E[this.c], 1);
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
        nbttagcompound.a("id", (short) this.c);
        nbttagcompound.a("Count", (byte) this.a);
        nbttagcompound.a("Damage", (short) this.e);
        if (this.d != null) {
            nbttagcompound.a("tag", (NBTBase) this.d);
        }

        return nbttagcompound;
    }

    public void c(NBTTagCompound nbttagcompound) {
        this.c = nbttagcompound.d("id");
        this.a = nbttagcompound.c("Count");
        this.e = nbttagcompound.d("Damage");
        if (this.e < 0) {
            this.e = 0;
        }

        if (nbttagcompound.b("tag")) {
            this.d = nbttagcompound.l("tag");
        }
    }

    public int e() {
        return this.b().l();
    }

    public boolean f() {
        return this.e() > 1 && (!this.g() || !this.i());
    }

    public boolean g() {
        return Item.f[this.c].n() > 0;
    }

    public boolean h() {
        return Item.f[this.c].m();
    }

    public boolean i() {
        return this.g() && this.e > 0;
    }

    public int j() {
        return this.e;
    }

    public int k() {
        return this.e;
    }

    public void b(int i0) {
        this.e = i0;
        if (this.e < 0) {
            this.e = 0;
        }
    }

    public int l() {
        return Item.f[this.c].n();
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

            this.e += i0;
            return this.e > this.l();
        }
    }

    public void a(int i0, EntityLiving entityliving) {
        if (!(entityliving instanceof EntityPlayer) || !((EntityPlayer) entityliving).ce.d) {
            if (this.g()) {
                if (this.a(i0, entityliving.aE())) {
                    entityliving.a(this);
                    if (entityliving instanceof EntityPlayer) {
                        ((EntityPlayer) entityliving).a(StatList.F[this.c], 1);
                    }

                    --this.a;
                    if (this.a < 0) {
                        this.a = 0;
                    }

                    this.e = 0;
                }
            }
        }
    }

    public void a(EntityLiving entityliving, EntityPlayer entityplayer) {
        boolean flag0 = Item.f[this.c].a(this, entityliving, (EntityLiving) entityplayer);

        if (flag0) {
            entityplayer.a(StatList.E[this.c], 1);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, EntityPlayer entityplayer) {
        boolean flag0 = Item.f[this.c].a(this, world, i0, i1, i2, i3, entityplayer);

        if (flag0) {
            entityplayer.a(StatList.E[this.c], 1);
        }
    }

    public int a(Entity entity) {
        return Item.f[this.c].a(entity);
    }

    public boolean b(Block block) {
        return Item.f[this.c].a(block);
    }

    public boolean a(EntityLiving entityliving) {
        return Item.f[this.c].a(this, entityliving);
    }

    public ItemStack m() {
        ItemStack itemstack = new ItemStack(this.c, this.a, this.e);

        if (this.d != null) {
            itemstack.d = (NBTTagCompound) this.d.b();
        }

        return itemstack;
    }

    public static boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack == null && itemstack1 == null ? true : (itemstack != null && itemstack1 != null ? (itemstack.d == null && itemstack1.d != null ? false : itemstack.d == null || itemstack.d.equals(itemstack1.d)) : false);
    }

    public static boolean b(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack == null && itemstack1 == null ? true : (itemstack != null && itemstack1 != null ? itemstack.d(itemstack1) : false);
    }

    private boolean d(ItemStack itemstack) {
        return this.a != itemstack.a ? false : (this.c != itemstack.c ? false : (this.e != itemstack.e ? false : (this.d == null && itemstack.d != null ? false : this.d == null || this.d.equals(itemstack.d))));
    }

    public boolean a(ItemStack itemstack) {
        return this.c == itemstack.c && this.e == itemstack.e;
    }

    public String a() {
        return Item.f[this.c].d(this);
    }

    public static ItemStack b(ItemStack itemstack) {
        return itemstack == null ? null : itemstack.m();
    }

    public String toString() {
        return this.a + "x" + Item.f[this.c].a() + "@" + this.e;
    }

    public void a(World world, Entity entity, int i0, boolean flag0) {
        if (this.b > 0) {
            --this.b;
        }

        Item.f[this.c].a(this, world, entity, i0, flag0);
    }

    public void a(World world, EntityPlayer entityplayer, int i0) {
        entityplayer.a(StatList.D[this.c], i0);
        Item.f[this.c].d(this, world, entityplayer);
    }

    public int n() {
        return this.b().c_(this);
    }

    public EnumAction o() {
        return this.b().b_(this);
    }

    public void b(World world, EntityPlayer entityplayer, int i0) {
        this.b().a(this, world, entityplayer, i0);
    }

    public boolean p() {
        return this.d != null;
    }

    public NBTTagCompound q() {
        return this.d;
    }

    public NBTTagList r() {
        return this.d == null ? null : (NBTTagList) this.d.a("ench");
    }

    public void d(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound;
    }

    public String s() {
        String s0 = this.b().l(this);

        if (this.d != null && this.d.b("display")) {
            NBTTagCompound nbttagcompound = this.d.l("display");

            if (nbttagcompound.b("Name")) {
                s0 = nbttagcompound.i("Name");
            }
        }

        return s0;
    }

    public void c(String s0) {
        if (this.d == null) {
            this.d = new NBTTagCompound("tag");
        }

        if (!this.d.b("display")) {
            this.d.a("display", new NBTTagCompound());
        }

        this.d.l("display").a("Name", s0);
    }

    public boolean t() {
        return this.d == null ? false : (!this.d.b("display") ? false : this.d.l("display").b("Name"));
    }

    public boolean w() {
        return !this.b().d_(this) ? false : !this.x();
    }

    public void a(Enchantment enchantment, int i0) {
        if (this.d == null) {
            this.d(new NBTTagCompound());
        }

        if (!this.d.b("ench")) {
            this.d.a("ench", (NBTBase) (new NBTTagList("ench")));
        }

        NBTTagList nbttaglist = (NBTTagList) this.d.a("ench");
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.a("id", (short) enchantment.z);
        nbttagcompound.a("lvl", (short) ((byte) i0));
        nbttaglist.a((NBTBase) nbttagcompound);
    }

    public boolean x() {
        return this.d != null && this.d.b("ench");
    }

    public void a(String s0, NBTBase nbtbase) {
        if (this.d == null) {
            this.d(new NBTTagCompound());
        }

        this.d.a(s0, nbtbase);
    }

    public boolean y() {
        return this.b().y();
    }

    public boolean z() {
        return this.f != null;
    }

    public void a(EntityItemFrame entityitemframe) {
        this.f = entityitemframe;
    }

    public EntityItemFrame A() {
        return this.f;
    }

    public int B() {
        return this.p() && this.d.b("RepairCost") ? this.d.e("RepairCost") : 0;
    }

    public void c(int i0) {
        if (!this.p()) {
            this.d = new NBTTagCompound("tag");
        }

        this.d.a("RepairCost", i0);
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
