package net.canarymod.api.world.blocks;

import net.canarymod.api.world.Dimension;
import net.minecraft.server.OTileEntity;

public class CanaryComplexBlock implements ComplexBlock {
    
    protected OTileEntity tileentity;
    
    public CanaryComplexBlock(OTileEntity tileentity){
        this.tileentity = tileentity;
    }
    
    public Block getBlock(){
        return getDimension().getBlockAt(getX(), getY(), getZ());
    }
    
    public int getX(){
        return tileentity.l;
    }
    
    public int getY(){
        return tileentity.m;
    }
    
    public int getZ(){
        return tileentity.n;
    }
    
    public Dimension getDimension(){
        return tileentity.k.getCanaryDimension();
    }
    
    public void update(){
        tileentity.q_();
    }
}
