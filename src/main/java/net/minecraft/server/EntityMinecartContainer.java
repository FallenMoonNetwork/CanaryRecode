package net.minecraft.server;

import java.util.Arrays;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public abstract class EntityMinecartContainer extends EntityMinecart implements IInventory {

    private ItemStack[] a = new ItemStack[36];
    private boolean b = true;

    public EntityMinecartContainer(World world) {
        super(world);
    }

    public EntityMinecartContainer(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);

        for (int i0 = 0; i0 < this.j_(); ++i0) {
            ItemStack itemstack = this.a(i0);

            if (itemstack != null) {
                float f0 = this.ab.nextFloat() * 0.8F + 0.1F;
                float f1 = this.ab.nextFloat() * 0.8F + 0.1F;
                float f2 = this.ab.nextFloat() * 0.8F + 0.1F;

                while (itemstack.a > 0) {
                    int i1 = this.ab.nextInt(21) + 10;

                    if (i1 > itemstack.a) {
                        i1 = itemstack.a;
                    }

                    itemstack.a -= i1;
                    EntityItem entityitem = new EntityItem(this.q, this.u + (double) f0, this.v + (double) f1, this.w + (double) f2, new ItemStack(itemstack.c, i1, itemstack.k()));
                    float f3 = 0.05F;

                    entityitem.x = (double) ((float) this.ab.nextGaussian() * f3);
                    entityitem.y = (double) ((float) this.ab.nextGaussian() * f3 + 0.2F);
                    entityitem.z = (double) ((float) this.ab.nextGaussian() * f3);
                    this.q.d((Entity) entityitem);
                }
            }
        }
    }

    public ItemStack a(int i0) {
        return this.a[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.a[i0] != null) {
            ItemStack itemstack;

            if (this.a[i0].a <= i1) {
                itemstack = this.a[i0];
                this.a[i0] = null;
                return itemstack;
            } else {
                itemstack = this.a[i0].a(i1);
                if (this.a[i0].a == 0) {
                    this.a[i0] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
        if (this.a[i0] != null) {
            ItemStack itemstack = this.a[i0];

            this.a[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.a[i0] = itemstack;
        if (itemstack != null && itemstack.a > this.d()) {
            itemstack.a = this.d();
        }
    }

    public void k_() {}

    public boolean a(EntityPlayer entityplayer) {
        return this.M ? false : entityplayer.e(this) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    public String b() {
        return this.c() ? this.t() : "container.minecart";
    }

    public int d() {
        return 64;
    }

    public void c(int i0) {
        this.b = false;
        super.c(i0);
    }

    public void w() {
        if (this.b) {
            for (int i0 = 0; i0 < this.j_(); ++i0) {
                ItemStack itemstack = this.a(i0);

                if (itemstack != null) {
                    float f0 = this.ab.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.ab.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.ab.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.a > 0) {
                        int i1 = this.ab.nextInt(21) + 10;

                        if (i1 > itemstack.a) {
                            i1 = itemstack.a;
                        }

                        itemstack.a -= i1;
                        EntityItem entityitem = new EntityItem(this.q, this.u + (double) f0, this.v + (double) f1, this.w + (double) f2, new ItemStack(itemstack.c, i1, itemstack.k()));

                        if (itemstack.p()) {
                            entityitem.d().d((NBTTagCompound) itemstack.q().b());
                        }

                        float f3 = 0.05F;

                        entityitem.x = (double) ((float) this.ab.nextGaussian() * f3);
                        entityitem.y = (double) ((float) this.ab.nextGaussian() * f3 + 0.2F);
                        entityitem.z = (double) ((float) this.ab.nextGaussian() * f3);
                        this.q.d((Entity) entityitem);
                    }
                }
            }
        }

        super.w();
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i0);
                this.a[i0].b(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Items");

        this.a = new ItemStack[this.j_()];

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
            int i1 = nbttagcompound1.c("Slot") & 255;

            if (i1 >= 0 && i1 < this.a.length) {
                this.a[i1] = ItemStack.a(nbttagcompound1);
            }
        }
    }

    public boolean a_(EntityPlayer entityplayer) {
        if (!this.q.I) {
            entityplayer.a((IInventory) this);
        }

        return true;
    }

    protected void h() {
        int i0 = 15 - Container.b((IInventory) this);
        float f0 = 0.98F + (float) i0 * 0.001F;

        this.x *= (double) f0;
        this.y *= 0.0D;
        this.z *= (double) f0;
    }

    // CanaryMod: Container<ItemStack>
    @Override
    public ItemStack[] getContents() {
        return a;
    }

    @Override
    public void setContents(ItemStack[] items) {
        System.arraycopy(items, 0, a, 0, items.length < getInventorySize() ? items.length : getInventorySize());
    }

    @Override
    public int getInventorySize() {
        return j_();
    }

    @Override
    public ItemStack getSlot(int index) {
        if (!(index < 0 || index > getInventorySize())) {
            return a[index];
        }
        return null;
    }

    @Override
    public void addItem(int itemId, int amount) {
        this.addItem(new CanaryItem(itemId, amount));
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        int slot = item.getSlot();
        int size = getInventorySize();

        if (slot < size && slot >= 0) {
            if (item.getAmount() <= 0) {
                removeItem(slot);
            } else if (ItemType.fromId(item.getId()) != null) {
                setSlot(slot, ((CanaryItem) item).getHandle());
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();

            if (newSlot != -1) {
                setSlot(newSlot, ((CanaryItem) item).getHandle());
                item.setSlot(newSlot);
            }
        }
    }

    @Override
    public int getEmptySlot() {
        int size = getInventorySize();

        for (int index = 0; size > index; index++) {
            if (getSlot(index) != null) {
                continue;
            }
            return index;
        }

        return -1;
    }

    @Override
    public void setSlot(int index, ItemStack value) {
        if (!(index < 0 || index > getInventorySize())) {
            this.a[index] = value;
        }
    }

    @Override
    public String getInventoryName() {
        return this.b();
    }

    @Override
    public void setInventoryName(String value) {
        this.a(value);
    }

    @Override
    public void clearContents() {
        Arrays.fill(a, null);
    }

    @Override
    public Item getItem(int id, int amount) {
        for (ItemStack stack : getContents()) {
            if (stack != null && stack.c == id && stack.a == amount) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public Item getItem(int id) {
        for (ItemStack stack : getContents()) {
            if (stack != null && stack.c == id) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public Item removeItem(Item item) {
        return removeItem(item.getId());
    }

    @Override
    public Item removeItem(int id) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.c == id) {
                setSlot(index, null);
                return toCheck.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public ItemStack decreaseItemStackSize(int itemId, int amount) {
        return this.a(itemId, amount);
    }

    @Override
    public int getInventoryStackLimit() {
        return this.d();
    }

    @Override
    public boolean hasItemStack(ItemStack oItemStack) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.a(oItemStack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.c == itemId && toCheck.a == amount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.c == itemId && toCheck.a >= minAmount && toCheck.a <= maxAmount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        for (int index = 0; index < getInventorySize(); index++) {
            if (getSlot(index) != null && getSlot(index).c == itemId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        // Needs No Update; as k_() does nothing
    }
    //
}
