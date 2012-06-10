package net.canarymod.api;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryPlayer;
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
        return new CanaryEntity(handle.b());
    }

    @Override
    public String getNotchianName() {
        return handle.l();
    }

    @Override
    public String getDeathMessage(Player player) {
        return handle.a(((CanaryPlayer)player).getHandle());
    }

    @Override
    public float getHungerDamage() {
        return handle.f();
    }

    @Override
    public boolean isFireDamage() {
        return handle.k();
    }

    @Override
    public boolean isProjectile() {
        return handle.c();
    }

    @Override
    public boolean isUnblockable() {
        return handle.e();
    }

    @Override
    public void setCustomDeathMessage(String message) {
        //Not real sure how this should be implemented at this time
    }

    @Override
    public void setHungerDamage(float value) {
        handle.p = value;
    }

    @Override
    public void setUnblockable(boolean set) {
        handle.a = set;
    }

    @Override
    public boolean validInCreativeMode() {
        return handle.g();
    }
    
    @Override
    public DamageType getDamagetype() {
        return DamageType.fromDamageSource(this);
    }
    

    /**
     * Gets a damage source from a damage type
     * @param type
     */
    public static DamageSource getDamageSourceFromType(DamageType type){
        switch(type){
        case CACTUS:
            return new CanaryDamageSource(ODamageSource.h);
        //case FallingAsleepHere: return going_to_bed_will_finish_later;
        default: 
            return null;
        }
    }

}
