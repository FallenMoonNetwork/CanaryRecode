package net.minecraft.server;

import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.ItemUseHook;

public class ItemHoe extends Item {

    protected EnumToolMaterial a;

    public ItemHoe(int i0, EnumToolMaterial enumtoolmaterial) {
        super(i0);
        this.a = enumtoolmaterial;
        this.cw = 1;
        this.e(enumtoolmaterial.a());
        this.a(CreativeTabs.i);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        if (!entityplayer.a(i0, i1, i2, i3, itemstack)) {
            return false;
        } else {
            int i4 = world.a(i0, i1, i2);
            int i5 = world.a(i0, i1 + 1, i2);

            if ((i3 == 0 || i5 != 0 || i4 != Block.z.cF) && i4 != Block.A.cF) {
                return false;
            } else {
                // CanaryMod: ItemUse
                CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

                clicked.setFaceClicked(BlockFace.fromByte((byte) i3));
                ItemUseHook hook = (ItemUseHook) new ItemUseHook(((EntityPlayerMP) entityplayer).getPlayer(), itemstack.getCanaryItem(), clicked).call();
                if (hook.isCanceled()) {
                    return false;
                }
                //

                Block block = Block.aF;

                world.a((double) ((float) i0 + 0.5F), (double) ((float) i1 + 0.5F), (double) ((float) i2 + 0.5F), block.cS.e(), (block.cS.c() + 1.0F) / 2.0F, block.cS.d() * 0.8F);
                if (world.I) {
                    return true;
                } else {
                    world.c(i0, i1, i2, block.cF);
                    itemstack.a(1, (EntityLivingBase) entityplayer);
                    return true;
                }
            }
        }
    }

    public String g() {
        return this.a.toString();
    }
}
