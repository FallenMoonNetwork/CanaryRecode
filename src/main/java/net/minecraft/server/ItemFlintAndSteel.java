package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.ItemUseHook;
import net.canarymod.hook.world.IgnitionHook;
import net.canarymod.hook.world.IgnitionHook.IgnitionCause;


public class ItemFlintAndSteel extends Item {

    public ItemFlintAndSteel(int i0) {
        super(i0);
        this.cq = 1;
        this.e(64);
        this.a(CreativeTabs.i);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        // CanaryMod: get clicked
        CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

        clicked.setFaceClicked(BlockFace.fromByte((byte) i3));
        clicked.setStatus((byte) 2); // Flint&Steel Status 2
        //

        if (i3 == 0) {
            --i1;
        }

        if (i3 == 1) {
            ++i1;
        }

        if (i3 == 2) {
            --i2;
        }

        if (i3 == 3) {
            ++i2;
        }

        if (i3 == 4) {
            --i0;
        }

        if (i3 == 5) {
            ++i0;
        }

        if (!entityplayer.a(i0, i1, i2, i3, itemstack)) {
            return false;
        } else {
            int i4 = world.a(i0, i1, i2);

            // CanaryMod: ItemUse/Ignition
            // Create & Call ItemUseHook
            ItemUseHook iuh = new ItemUseHook(((EntityPlayerMP) entityplayer).getPlayer(), itemstack.getCanaryItem(), clicked);
            Canary.hooks().callHook(iuh);
            // Create & Call IgnitionHook
            CanaryBlock ignited = new CanaryBlock((short) Block.av.cz, (short) 0, i0, i1, i2, world.getCanaryWorld());
            IgnitionHook ih = new IgnitionHook(ignited, ((EntityPlayerMP) entityplayer).getPlayer(), clicked, IgnitionCause.FLINT_AND_STEEL);
            Canary.hooks().callHook(ih);

            // If either hook is canceled, return
            if (iuh.isCanceled() || ih.isCanceled()) {
                return false;
            }
            //

            if (i4 == 0) {
                world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "fire.ignite", 1.0F, e.nextFloat() * 0.4F + 0.8F);
                world.c(i0, i1, i2, Block.av.cz);
            }

            itemstack.a(1, (EntityLiving) entityplayer);
            return true;
        }
    }
}
