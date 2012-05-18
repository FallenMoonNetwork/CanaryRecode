package net.canarymod.api;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.Player;
import net.minecraft.server.ODamageSource;

public class CanaryDamageSource implements DamageSource {
    ODamageSource handle;
    
    public CanaryDamageSource(ODamageSource handle) {
        this.handle = handle;
    }
    
    public ODamageSource getHandle() {
        return handle;
    }
    @Override
    public Entity getDamageDealer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDamagetype() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDeathMessage(Player arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getHungerDamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isFireDamage() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isProjectile() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isUnblockable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCustomDeathMessage(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHungerDamage(float arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setUnblockable(boolean arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean validInCreativeMode() {
        // TODO Auto-generated method stub
        return false;
    }

}
