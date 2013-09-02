package net.minecraft.server;

import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.RedstoneChangeHook;

public class BlockPoweredOre extends BlockOreStorage {

    public BlockPoweredOre(int i0) {
        super(i0);
        this.a(CreativeTabs.d);
    }

    public boolean f() {
        return true;
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return 15;
    }

    // CanaryMod: pull place method in to do RedstoneChange
    public void a(World world, int i0, int i1, int i2) {
        new RedstoneChangeHook(new CanaryBlock(BlockType.RedstoneBlock.getId(), (short) 2, i0, i1, i2, world.getCanaryWorld()), 0, 15).call();
        super.a(world, i0, i1, i2);
    }

    // CanaryMod: pull break method in to do RedstoneChange
    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        new RedstoneChangeHook(new CanaryBlock(BlockType.RedstoneBlock.getId(), (short) i3, i0, i1, i2, world.getCanaryWorld()), 15, 0).call();
        super.a(world, i0, i1, i2, i3, i4);
    }
    //
}
