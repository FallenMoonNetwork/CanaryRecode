package net.canarymod.api;

import net.canarymod.api.entity.IEntity;
import net.canarymod.api.entity.IPlayer;
import net.minecraft.server.ODamageSource;

public class DamageSource implements IDamageSource {
    ODamageSource _handle;
    
    public DamageSource(ODamageSource handle) {
        _handle = handle;
    }
    
    public ODamageSource getHandle() {
        return _handle;
    }
    @Override
    public IEntity getDamageDealer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDamagetype() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDeathMessage(IPlayer arg0) {
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
