package net.minecraft.server;


import java.util.HashMap;
import java.util.Map;
import net.canarymod.Canary;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.ItemUseHook;


public class ItemRecord extends Item {

    private static final Map b = new HashMap();
    public final String a;

    protected ItemRecord(int i0, String s0) {
        super(i0);
        this.a = s0;
        this.cw = 1;
        this.a(CreativeTabs.f);
        b.put(s0, this);
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        // CanaryMod: ItemUse
        CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

        clicked.setFaceClicked(BlockFace.fromByte((byte) i3));
        ItemUseHook hook = new ItemUseHook(((EntityPlayerMP) entityplayer).getPlayer(), itemstack.getCanaryItem(), clicked);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return false;
        }
        //
        if (world.a(i0, i1, i2) == Block.bd.cF && world.h(i0, i1, i2) == 0) {
            if (world.I) {
                return true;
            } else {
                ((BlockJukeBox) Block.bd).a(world, i0, i1, i2, itemstack);
                world.a((EntityPlayer) null, 1005, i0, i1, i2, this.cv);
                --itemstack.b;
                return true;
            }
        } else {
            return false;
        }
    }
}
