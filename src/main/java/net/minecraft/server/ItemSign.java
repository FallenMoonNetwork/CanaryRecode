package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.BlockPlaceHook;


public class ItemSign extends Item {

    public ItemSign(int i0) {
        super(i0);
        this.cw = 16;
        this.a(CreativeTabs.c);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        if (i3 == 0) {
            return false;
        } else if (!world.g(i0, i1, i2).a()) {
            return false;
        } else {
            // CanaryMod: BlockPlaceHook
            CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

            clicked.setFaceClicked(BlockFace.fromByte((byte) i3));

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
            } else if (!Block.aI.c(world, i0, i1, i2)) {
                return false;
            } else {
                // Create and call
                CanaryBlock placed = new CanaryBlock((short) (i3 == 1 ? 63 : 68), (short) 0, i0, i1, i2, world.getCanaryWorld());
                BlockPlaceHook hook = new BlockPlaceHook(((EntityPlayerMP) entityplayer).getPlayer(), clicked, placed);

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return false;
                }
                //

                if (i3 == 1) {
                    int i4 = MathHelper.c((double) ((entityplayer.A + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;

                    world.f(i0, i1, i2, Block.aI.cF, i4, 2);
                } else {
                    world.f(i0, i1, i2, Block.aN.cF, i3, 2);
                }

                --itemstack.b;
                TileEntitySign tileentitysign = (TileEntitySign) world.r(i0, i1, i2);

                if (tileentitysign != null) {
                    entityplayer.a((TileEntity) tileentitysign);
                }

                return true;
            }
        }
    }
}
