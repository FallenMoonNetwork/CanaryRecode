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
     * Gets a damage source from a damage type<br>
     * Note: This will not work with Entity Damage Sources due to
     * the extra Entity Arguments.
     * @param type
     */
    public static DamageSource getDamageSourceFromType(DamageType type){
        switch(type){
        case CACTUS:
            return new CanaryDamageSource(ODamageSource.h);
        case EXPLOSION:
            return new CanaryDamageSource(ODamageSource.l);
        case FALL:
            return new CanaryDamageSource(ODamageSource.i);
        case FIRE:
            return new CanaryDamageSource(ODamageSource.b);
        case FIRE_TICK:
            return new CanaryDamageSource(ODamageSource.c);
        case GENERIC:
            return new CanaryDamageSource(ODamageSource.k);
        case LAVA:
            return new CanaryDamageSource(ODamageSource.d);
        case POTION:
            return new CanaryDamageSource(ODamageSource.m);
        case STARVATION:
            return new CanaryDamageSource(ODamageSource.g);
        case SUFFOCATION:
            return new CanaryDamageSource(ODamageSource.e);
        case VOID:
            return new CanaryDamageSource(ODamageSource.j);
        case WATER:
            return new CanaryDamageSource(ODamageSource.f);
        default: 
            return null;
        }
    }

}
