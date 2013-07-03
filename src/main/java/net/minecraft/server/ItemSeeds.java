package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.BlockPlaceHook;


public class ItemSeeds extends Item {

    private int a;
    private int b;

    public ItemSeeds(int i0, int i1, int i2) {
        super(i0);
        this.a = i1;
        this.b = i2;
        this.a(CreativeTabs.l);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        if (i3 != 1) {
            return false;
        } else if (entityplayer.a(i0, i1, i2, i3, itemstack) && entityplayer.a(i0, i1 + 1, i2, i3, itemstack)) {
            int i4 = world.a(i0, i1, i2);

            if (i4 == this.b && world.c(i0, i1 + 1, i2)) {

                // CanaryMod: BlockPlaceHook
                CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

                clicked.setFaceClicked(BlockFace.fromByte((byte) 1)); // Should be 1
                CanaryBlock placed = new CanaryBlock((short) this.a, (short) 0, i0, i1, i2, world.getCanaryWorld());
                BlockPlaceHook hook = new BlockPlaceHook(((EntityPlayerMP) entityplayer).getPlayer(), clicked, placed);

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return false;
                }
                //

                world.c(i0, i1 + 1, i2, this.a);
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
