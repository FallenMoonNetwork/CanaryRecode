package net.minecraft.server;

import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.BlockPlaceHook;

public class ItemBed extends Item {

    public ItemBed(int i0) {
        super(i0);
        this.a(CreativeTabs.c);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        if (world.I) {
            return true;
        } else if (i3 != 1) {
            return false;
        } else {
            // CanaryMod: ItemUseHook
            CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2); // Store Clicked

            clicked.setFaceClicked(BlockFace.fromByte((byte) i3)); // Set face clicked

            ++i1;
            BlockBed blockbed = (BlockBed) Block.X;
            int i4 = MathHelper.c((double) (entityplayer.A * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i4 == 0) {
                b1 = 1;
            }

            if (i4 == 1) {
                b0 = -1;
            }

            if (i4 == 2) {
                b1 = -1;
            }

            if (i4 == 3) {
                b0 = 1;
            }

            if (entityplayer.a(i0, i1, i2, i3, itemstack) && entityplayer.a(i0 + b0, i1, i2 + b1, i3, itemstack)) {
                if (world.c(i0, i1, i2) && world.c(i0 + b0, i1, i2 + b1) && world.w(i0, i1 - 1, i2) && world.w(i0 + b0, i1 - 1, i2 + b1)) {
                    CanaryBlock placed = new CanaryBlock((short) ItemType.Bed.getId(), (short) 0, i0, i1, i2, world.getCanaryWorld());
                    // Create Hook and call it
                    BlockPlaceHook hook = (BlockPlaceHook) new BlockPlaceHook(((EntityPlayerMP) entityplayer).getPlayer(), clicked, placed).call();
                    if (hook.isCanceled()) {
                        return false;
                    }
                    //
                    world.f(i0, i1, i2, blockbed.cF, i4, 3);
                    if (world.a(i0, i1, i2) == blockbed.cF) {
                        world.f(i0 + b0, i1, i2 + b1, blockbed.cF, i4 + 8, 3);
                    }

                    --itemstack.b;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
