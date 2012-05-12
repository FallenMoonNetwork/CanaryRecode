package net.canarymod.api.world;

import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.IBlock;

public class Block implements IBlock {
    protected short _type;
    protected byte _data;
    protected int _x,_y,_z;
    protected IDimension dimension;
    
    public Block(int x, int y, int z) {
        _x = x;
        _y = y;
        _z = z;
        _type=0;
        _data=0;
        dimension=null; //TODO: get default world
    }
    
    public Block(short type, byte data) {
        _x = 0;
        _y = 0;
        _z = 0;
        _type=type;
        _data=data;
        dimension=null; //TODO: get default world
    }
    
    public Block(short type, byte data, int x, int y, int z) {
        _x = x;
        _y = y;
        _z = z;
        _type=type;
        _data=data;
        dimension=null; //TODO: get default world
    }
    
    public Block(short type, byte data, int x, int y, int z, IDimension dimension) {
        _x = x;
        _y = y;
        _z = z;
        _type=type;
        _data=data;
        this.dimension=dimension;
    }
    
    @Override
    public short getType() {
        return _type;
    }

    @Override
    public void setType(short type) {
        _type = type;

    }

    @Override
    public void setType(int type) {
        _type = (short)type;

    }

    @Override
    public byte getData() {
        return _data;
    }

    @Override
    public IDimension getWorld() {
        return dimension;
    }

    @Override
    public void setWorld(IDimension world) {
        dimension = world;
    }

    @Override
    public BlockFace getFaceClicked() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFaceClicked(BlockFace face) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        dimension.updateBlockAt(_x, _y, _z);

    }

    @Override
    public int getX() {
        return _x;
    }

    @Override
    public int getY() {
        return _y;
    }

    @Override
    public int getZ() {
        return _z;
    }

}
