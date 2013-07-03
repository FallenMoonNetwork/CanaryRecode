package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.world.blocks.CanaryWorkbench;
import net.canarymod.hook.player.CraftHook;


public class ContainerWorkbench extends Container {

    public InventoryCrafting a = new InventoryCrafting(this, 3, 3);
    public IInventory f = new InventoryCraftResult();
    public World g; // CanaryMod: private -> public
    public int h; // CanaryMod: private -> public
    public int i; // CanaryMod: private -> public
    public int j; // CanaryMod: private -> public
    private CanaryWorkbench bench; // CanaryMod inventory instance

    public ContainerWorkbench(InventoryPlayer inventoryplayer, World world, int i0, int i1, int i2) {
        this.g = world;
        this.h = i0;
        this.i = i1;
        this.j = i2;
        this.a((Slot) (new SlotCrafting(inventoryplayer.d, this.a, this.f, 0, 124, 35)));

        int i3;
        int i4;

        for (i3 = 0; i3 < 3; ++i3) {
            for (i4 = 0; i4 < 3; ++i4) {
                this.a(new Slot(this.a, i4 + i3 * 3, 30 + i4 * 18, 17 + i3 * 18));
            }
        }

        for (i3 = 0; i3 < 3; ++i3) {
            for (i4 = 0; i4 < 9; ++i4) {
                this.a(new Slot(inventoryplayer, i4 + i3 * 9 + 9, 8 + i4 * 18, 84 + i3 * 18));
            }
        }

        for (i3 = 0; i3 < 9; ++i3) {
            this.a(new Slot(inventoryplayer, i3, 8 + i3 * 18, 142));
        }

        this.bench = new CanaryWorkbench(this); // CanaryMod: create once, use forever
        this.a((IInventory) this.a);
    }

    public void a(IInventory iinventory) {
        ItemStack result = CraftingManager.a().a(this.a, this.g);

        if (this.e.isEmpty()) {
            this.f.a(0, result);
            return;
        }

        // CanaryMod: Send custom recipe results to client
        EntityPlayerMP player = (EntityPlayerMP) this.e.get(0);

        // call CraftHook
        CraftHook hook = new CraftHook(player.getPlayer(), bench, result == null ? null : result.getCanaryItem());
        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            result = null;
        }
        else {
            result = hook.getRecipeResult() == null ? null : ((CanaryItem) hook.getRecipeResult()).getHandle();
        }

        // Set custom result
        this.f.a(0, result);
        // And send player custom result
        player.a.b(new Packet103SetSlot(this.d, 0, result));
        //
    }

    public void b(EntityPlayer entityplayer) {
        super.b(entityplayer);
        if (!this.g.I) {
            for (int i0 = 0; i0 < 9; ++i0) {
                ItemStack itemstack = this.a.a_(i0);

                if (itemstack != null) {
                    entityplayer.b(itemstack);
                }
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.g.a(this.h, this.i, this.j) != Block.aD.cF ? false : entityplayer.e((double) this.h + 0.5D, (double) this.i + 0.5D, (double) this.j + 0.5D) <= 64.0D;
    }

    public ItemStack b(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.c.get(i0);

        if (slot != null && slot.e()) {
            ItemStack itemstack1 = slot.d();

            itemstack = itemstack1.m();
            if (i0 == 0) {
                if (!this.a(itemstack1, 10, 46, true)) {
                    return null;
                }

                slot.a(itemstack1, itemstack);
            } else if (i0 >= 10 && i0 < 37) {
                if (!this.a(itemstack1, 37, 46, false)) {
                    return null;
                }
            } else if (i0 >= 37 && i0 < 46) {
                if (!this.a(itemstack1, 10, 37, false)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 10, 46, false)) {
                return null;
            }

            if (itemstack1.b == 0) {
                slot.c((ItemStack) null);
            } else {
                slot.f();
            }

            if (itemstack1.b == itemstack.b) {
                return null;
            }

            slot.a(entityplayer, itemstack1);
        }

        return itemstack;
    }

    public boolean a(ItemStack itemstack, Slot slot) {
        return slot.f != this.f && super.a(itemstack, slot);
    }

    // CanaryMod
    public CanaryWorkbench getCanaryWorkbench() {
        return bench;
    }
}
