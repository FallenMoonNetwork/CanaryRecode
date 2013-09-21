package net.minecraft.server;

import net.canarymod.hook.player.EatHook;

public class ItemBucketMilk extends Item {

    public ItemBucketMilk(int i0) {
        super(i0);
        this.d(1);
        this.a(CreativeTabs.f);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        // CanaryMod: Eat
        EatHook hook = (EatHook)new EatHook(((EntityPlayerMP)entityplayer).getPlayer(), itemstack.getCanaryItem(), 0, 0, null).call();
        if (hook.isCanceled()) {
            return itemstack;
        }
        // For those Lactose intolerant
        entityplayer.bq.a(hook.getLevelGain(), hook.getSaturationGain());
        //

        if (!entityplayer.bG.d) {
            --itemstack.b;
        }

        if (!world.I) {
            entityplayer.aK();
        }

        return itemstack.b <= 0 ? new ItemStack(Item.ay) : itemstack;
    }

    public int d_(ItemStack itemstack) {
        return 32;
    }

    public EnumAction c_(ItemStack itemstack) {
        return EnumAction.c;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.a(itemstack, this.d_(itemstack));
        return itemstack;
    }
}
