package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.CanaryPlayerCraftingMatrix;
import net.canarymod.hook.player.CraftHook;


public class ContainerPlayer extends Container {

    public InventoryCrafting a = new InventoryCrafting(this, 2, 2);
    public IInventory f = new InventoryCraftResult();
    public boolean g;
    private final EntityPlayer h;

    public ContainerPlayer(InventoryPlayer inventoryplayer, boolean flag0, EntityPlayer entityplayer) {
        this.g = flag0;
        this.h = entityplayer;
        this.a((Slot) (new SlotCrafting(inventoryplayer.d, this.a, this.f, 0, 144, 36)));

        int i0;
        int i1;

        for (i0 = 0; i0 < 2; ++i0) {
            for (i1 = 0; i1 < 2; ++i1) {
                this.a(new Slot(this.a, i1 + i0 * 2, 88 + i1 * 18, 26 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 4; ++i0) {
            this.a((Slot) (new SlotArmor(this, inventoryplayer, inventoryplayer.j_() - 1 - i0, 8, 8 + i0 * 18, i0)));
        }

        for (i0 = 0; i0 < 3; ++i0) {
            for (i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(inventoryplayer, i1 + (i0 + 1) * 9, 8 + i1 * 18, 84 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 9; ++i0) {
            this.a(new Slot(inventoryplayer, i0, 8 + i0 * 18, 142));
        }

        this.inventory = this.h.getPlayerInventory(); // CanaryMod: Set inventory instance

        this.a((IInventory) this.a);
    }

    public void a(IInventory iinventory) {
//MERGE: This might contain wrong calls! - Chris
        ItemStack result = CraftingManager.a().a(this.a, this.h.q);

        // CanaryMod: Send custom recipe results to client
        if(this.h.getCanaryEntity() instanceof CanaryPlayer) {
            CraftHook hook = new CraftHook(((EntityPlayerMP) this.h).getPlayer(), new CanaryPlayerCraftingMatrix(this.a), result == null ? null : result.getCanaryItem());
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
            ((EntityPlayerMP) this.h).a.b(new Packet103SetSlot(this.d, 0, result));
            //
        }
    }

    public void b(EntityPlayer entityplayer) {
        super.b(entityplayer);

        for (int i0 = 0; i0 < 4; ++i0) {
            ItemStack itemstack = this.a.a_(i0);

            if (itemstack != null) {
                entityplayer.b(itemstack);
            }
        }

        this.f.a(0, (ItemStack) null);
    }

    public boolean a(EntityPlayer entityplayer) {
        return true;
    }

    public ItemStack b(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.c.get(i0);

        if (slot != null && slot.e()) {
            ItemStack itemstack1 = slot.d();

            itemstack = itemstack1.m();
            if (i0 == 0) {
                if (!this.a(itemstack1, 9, 45, true)) {
                    return null;
                }

                slot.a(itemstack1, itemstack);
            } else if (i0 >= 1 && i0 < 5) {
                if (!this.a(itemstack1, 9, 45, false)) {
                    return null;
                }
            } else if (i0 >= 5 && i0 < 9) {
                if (!this.a(itemstack1, 9, 45, false)) {
                    return null;
                }
            } else if (itemstack.b() instanceof ItemArmor
                    && !((Slot) this.c.get(5 + ((ItemArmor) itemstack.b()).b)).e()) {
                int i1 = 5 + ((ItemArmor) itemstack.b()).b;

                if (!this.a(itemstack1, i1, i1 + 1, false)) {
                    return null;
                }
            } else if (i0 >= 9 && i0 < 36) {
                if (!this.a(itemstack1, 36, 45, false)) {
                    return null;
                }
            } else if (i0 >= 36 && i0 < 45) {
                if (!this.a(itemstack1, 9, 36, false)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 9, 45, false)) {
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
}
