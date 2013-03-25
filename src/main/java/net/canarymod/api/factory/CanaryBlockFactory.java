package net.canarymod.api.factory;


import net.canarymod.api.factory.BlockFactory;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.CanaryBlock;


public class CanaryBlockFactory implements BlockFactory {

    @Override
    public Block newBlock(int id) {
        return new CanaryBlock((short) id, (byte) 0);
    }

    @Override
    public Block newBlock(int id, int damage) {
        return new CanaryBlock((short) id, (byte) damage);
    }

    @Override
    public Block newBlock(int id, World world) {
        return new CanaryBlock((short) id, (byte) 0, 0, 0, 0, (CanaryWorld) world);
    }

    @Override
    public Block newBlock(int id, int damage, World world) {
        return new CanaryBlock((short) id, (byte) damage, 0, 0, 0, (CanaryWorld) world);
    }

    @Override
    public Block newBlock(int id, int x, int y, int z) {
        return new CanaryBlock((short) id, (byte) 0, x, y, z);
    }

    @Override
    public Block newBlock(int id, int damage, int x, int y, int z) {
        return new CanaryBlock((short) id, (byte) damage, x, y, z);
    }

    @Override
    public Block newBlock(int id, int damage, int x, int y, int z, World world) {
        return new CanaryBlock((short) id, (byte) damage, x, y, z, (CanaryWorld) world);
    }

    @Override
    public Block newBlock(Block block) {
        return new CanaryBlock(block.getType(), block.getData(), block.getX(), block.getY(), block.getZ(), (CanaryWorld) block.getDimension());
    }

}
