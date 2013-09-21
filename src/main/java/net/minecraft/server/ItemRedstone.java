package net.minecraft.server;

import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.BlockPlaceHook;

public class ItemRedstone extends Item {

    public ItemRedstone(int i0) {
        super(i0);
        this.a(CreativeTabs.d);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        // CanaryMod: BlockPlaceHook
        CanaryBlock clicked = (CanaryBlock)world.getCanaryWorld().getBlockAt(i0, i1, i2);

        clicked.setFaceClicked(BlockFace.fromByte((byte)i3));

        if (world.a(i0, i1, i2) != Block.aX.cF) {
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

            if (!world.c(i0, i1, i2)) {
                return false;
            }
        }

        if (!entityplayer.a(i0, i1, i2, i3, itemstack)) {
            return false;
        }
        else {
            // set placed
            CanaryBlock placed = new CanaryBlock((short)BlockType.RedstoneBlock.getId(), (short)0, i0, i1, i2, world.getCanaryWorld());
            // Create and Call
            BlockPlaceHook hook = (BlockPlaceHook)new BlockPlaceHook(((EntityPlayerMP)entityplayer).getPlayer(), clicked, placed).call();
            if (hook.isCanceled()) {
                return false;
            }
            //
            if (Block.aA.c(world, i0, i1, i2)) {
                --itemstack.b;
                world.c(i0, i1, i2, Block.aA.cF);
            }

            return true;
        }
    }
}
