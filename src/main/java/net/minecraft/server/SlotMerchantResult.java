package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.CanaryVillagerTrade;
import net.canarymod.api.entity.living.humanoid.Villager;
import net.canarymod.hook.player.VillagerTradeHook;


public class SlotMerchantResult extends Slot {

    private final InventoryMerchant a;
    private EntityPlayer b;
    private int c;
    private final IMerchant d;

    public SlotMerchantResult(EntityPlayer entityplayer, IMerchant imerchant, InventoryMerchant inventorymerchant, int i0, int i1, int i2) {
        super(inventorymerchant, i0, i1, i2);
        this.b = entityplayer;
        this.d = imerchant;
        this.a = inventorymerchant;
    }

    public boolean a(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i0) {
        if (this.d()) {
            this.c += Math.min(i0, this.c().a);
        }

        return super.a(i0);
    }

    protected void a(ItemStack itemstack, int i0) {
        this.c += i0;
        this.b(itemstack);
    }

    protected void b(ItemStack itemstack) {
        itemstack.a(this.b.q, this.b, this.c);
        this.c = 0;
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        this.a(entityplayer, itemstack, false);
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack, boolean heldShift) {
        MerchantRecipe merchantrecipe = this.a.i();
        // CanaryMod: VillagerTradeHook
        VillagerTradeHook hook = new VillagerTradeHook(((EntityPlayerMP) entityplayer).getPlayer(), (Villager) ((EntityVillager) this.d).getCanaryEntity(), new CanaryVillagerTrade(merchantrecipe));

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            if (heldShift) {
                ((EntityPlayerMP) entityplayer).getPlayer().getInventory().decreaseItemStackSize(merchantrecipe.d().getCanaryItem());
            } else {
                entityplayer.bK.b((ItemStack) null);
            }
        }
        //
        this.b(itemstack);

        if (merchantrecipe != null) {
            ItemStack itemstack1 = this.a.a(0);
            ItemStack itemstack2 = this.a.a(1);

            if (this.a(merchantrecipe, itemstack1, itemstack2) || this.a(merchantrecipe, itemstack2, itemstack1)) {
                if (itemstack1 != null && itemstack1.a <= 0) {
                    itemstack1 = null;
                }

                if (itemstack2 != null && itemstack2.a <= 0) {
                    itemstack2 = null;
                }

                this.a.a(0, itemstack1);
                this.a.a(1, itemstack2);
                this.d.a(merchantrecipe);
            }
        }
    }

    private boolean a(MerchantRecipe merchantrecipe, ItemStack itemstack, ItemStack itemstack1) {
        ItemStack itemstack2 = merchantrecipe.a();
        ItemStack itemstack3 = merchantrecipe.b();

        if (itemstack != null && itemstack.c == itemstack2.c) {
            if (itemstack3 != null && itemstack1 != null && itemstack3.c == itemstack1.c) {
                itemstack.a -= itemstack2.a;
                itemstack1.a -= itemstack3.a;
                return true;
            }

            if (itemstack3 == null && itemstack1 == null) {
                itemstack.a -= itemstack2.a;
                return true;
            }
        }

        return false;
    }
}
