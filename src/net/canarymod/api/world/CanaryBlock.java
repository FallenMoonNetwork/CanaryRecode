package net.canarymod.api.world;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.Block;

public class CanaryBlock implements Block {
    protected short type;
    protected byte data;
    protected int x,y,z;
    protected Dimension dimension;
    protected BlockFace faceClicked;
    
    public CanaryBlock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type=0;
        this.data=0;
        dimension= Canary.getServer().getDefaultWorld().getNormal();
    }
    
    public CanaryBlock(short type, byte data) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.type = type;
        this.data = data;
        dimension = Canary.getServer().getDefaultWorld().getNormal();
    }
    
    public CanaryBlock(short type, byte data, int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.data = data;
        dimension = Canary.getServer().getDefaultWorld().getNormal();
    }
    
    public CanaryBlock(short type, byte data, int x, int y, int z, Dimension dimension) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.data = data;
        this.dimension = dimension;
    }
    
    @Override
    public short getType() {
        return type;
    }

    @Override
    public void setType(short type) {
        this.type = type;

    }

    @Override
    public void setType(int type) {
        this.type = (short)type;

    }

    @Override
    public byte getData() {
        return data;
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public BlockFace getFaceClicked() {
        return faceClicked;
    }

    @Override
    public void setFaceClicked(BlockFace face) {
        faceClicked = face;
    }

    @Override
    public void update() {
        dimension.updateBlockAt(x, y, z);

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setZ(int z) {
        this.z = z;
    }

}
