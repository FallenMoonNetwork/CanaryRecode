package net.canarymod.api.entity;

import net.minecraft.server.OEntityBlaze;

public class CanaryBlaze extends CanaryEntityMob implements Blaze {

    public CanaryBlaze(OEntityBlaze entity) {
    	super(entity);
    }

    @Override
    public boolean isBurning() {
        return ((OEntityBlaze)entity).B_();
    }

    @Override
    public void setBurning(boolean isBurning) {
        if(isBurning) {
            ((OEntityBlaze)entity).aP().getCanaryDataWatcher().updateObject(16, 1);
        }
        else {
            ((OEntityBlaze)entity).aP().getCanaryDataWatcher().updateObject(16, 0);
        }
        
    }
}
