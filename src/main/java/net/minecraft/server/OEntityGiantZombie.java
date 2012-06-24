package net.minecraft.server;

import net.canarymod.api.entity.CanaryGiantZombie;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OWorld;

public class OEntityGiantZombie extends OEntityMob {

    //CanaryMod giant zombie handler
    private CanaryGiantZombie canaryGiantZombie;
    
    public OEntityGiantZombie(OWorld var1) {
        super(var1);
        this.ae = "/mob/zombie.png";
        this.bb = 0.5F;
        this.c = 50;
        this.bF *= 6.0F;
        this.b(this.bG * 6.0F, this.bH * 6.0F);
        canaryGiantZombie = new CanaryGiantZombie(this);
    }

    /**
     * CanaryMod get giant zombie handler
     * @return
     */
    public CanaryGiantZombie getCanaryGiantZombie() {
        return canaryGiantZombie;
    }
    
    @Override
    public int d() {
        return 100;
    }

    @Override
    public float a(int var1, int var2, int var3) {
        return this.bi.p(var1, var2, var3) - 0.5F;
    }

}
