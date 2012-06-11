package net.canarymod.api.entity;

import net.minecraft.server.OEntityTNTPrimed;

/**
 * CanaryTNTPrimed - wrapper for OEntityTNTPrimed
 * @author Jason Jones
 */
public class CanaryTNTPrimed extends CanaryEntity implements TNTPrimed{
    
    public CanaryTNTPrimed(OEntityTNTPrimed tntprimed){
        super(tntprimed);
    }

    @Override
    public int getFuse() {
        return ((OEntityTNTPrimed)entity).a;
    }

    @Override
    public void setFuse(int fuse) {
        ((OEntityTNTPrimed)entity).a = fuse;
    }

    @Override
    public void detonate() {
        ((OEntityTNTPrimed)entity).a = 0;
    }

    //Jason: Chris, not sure how this will work exactly so I'll leave these bits to you.
    @Override
    public boolean canDamageEntities() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canDamageWorld() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCanDamageEntities(boolean arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCanDamageWorld(boolean arg0) {
        // TODO Auto-generated method stub
        
    }
}
