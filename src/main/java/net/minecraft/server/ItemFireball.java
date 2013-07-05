package net.minecraft.server;

import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.ItemUseHook;
import net.canarymod.hook.world.IgnitionHook;
import net.canarymod.hook.world.IgnitionHook.IgnitionCause;

public class ItemFireball extends Item {

    public ItemFireball(int i0) {
        super(i0);
        this.a(CreativeTabs.f);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        // CanaryMod: get clicked
        CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

        clicked.setFaceClicked(BlockFace.fromByte((byte) i3));
        clicked.setStatus((byte) 6); // Fireball Status 6
        //
        if (world.I) {
            return true;
        } else {
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
                ItemUseHook iuh = (ItemUseHook) new ItemUseHook(((EntityPlayerMP) entityplayer).getPlayer(), itemstack.getCanaryItem(), clicked).call();
                // Create & Call IgnitionHook
                CanaryBlock ignited = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);
                IgnitionHook ih = (IgnitionHook) new IgnitionHook(ignited, ((EntityPlayerMP) entityplayer).getPlayer(), clicked, IgnitionCause.FIREBALL_CLICK).call();

                // If either hook is canceled, return
                if (iuh.isCanceled() || ih.isCanceled()) {
                    return false;
                }
                //

                if (i4 == 0) {
                    world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "fire.ignite", 1.0F, f.nextFloat() * 0.4F + 0.8F);
                    world.c(i0, i1, i2, Block.aw.cF);
                }

                if (!entityplayer.bG.d) {
                    --itemstack.b;
                }

                return true;
            }
        }
    }
}
