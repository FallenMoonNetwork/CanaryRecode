package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.DamageHook;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemFood;
import net.minecraft.server.ONBTTagCompound;

public class OFoodStats {

    // CanaryMod Refactored names
    private int foodLevel = 20;
    private float foodSaturationLevel = 5.0F;
    private float foodExhaustionLevel;
    private int foodTimer = 0;
    private int previousFoodLevel = 20; //Thafuq?
    //CanaryMod end

    public OFoodStats() {
        super();
    }

    public void addStats(int var1, float var2) {
        this.foodLevel = Math.min(var1 + this.foodLevel, 20);
        this.foodSaturationLevel = Math.min(this.foodSaturationLevel + var1 * var2 * 2.0F, this.foodLevel);
    }

    public void addStats(OItemFood var1) {
        this.addStats(var1.o(), var1.p());
    }

    public void onUpdate(OEntityPlayer var1) {
        int var2 = var1.bi.q;
        this.previousFoodLevel = this.foodLevel;
        if (this.foodExhaustionLevel > 4.0F) {
            this.foodExhaustionLevel -= 4.0F;
            if (this.foodSaturationLevel > 0.0F) {
                this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1.0F, 0.0F);
            } else if (var2 > 0) {
                this.foodLevel = Math.max(this.foodLevel - 1, 0);
            }
        }

        if (this.foodLevel >= 18 && var1.ag()) {
            ++this.foodTimer;
            if (this.foodTimer >= 80) {
                var1.d(1);
                this.foodTimer = 0;
            }
        } else if (this.foodLevel <= 0) {
            ++this.foodTimer;
            if (this.foodTimer >= 80) {
                if (var1.aD() > 10 || var2 >= 3 || var1.aD() > 1 && var2 >= 2) {
                    // CanaryMod - starving damage.
                    DamageHook hook = new DamageHook(null, var1.getCanaryEntityLiving(), new CanaryDamageSource(ODamageSource.g), 1);
                    Canary.hooks().callHook(hook);
                    if (!hook.isCanceled()) {
                        var1.a(ODamageSource.g, 1);
                    }
                    // CanaryMod - end.
                }

                this.foodTimer = 0;
            }
        } else {
            this.foodTimer = 0;
        }

    }

    //CanaryMod start: Refactored method names
    public void readNbt(ONBTTagCompound var1) {
        if (var1.c("foodLevel")) {
            this.foodLevel = var1.f("foodLevel");
            this.foodTimer = var1.f("foodTickTimer");
            this.foodSaturationLevel = var1.h("foodSaturationLevel");
            this.foodExhaustionLevel = var1.h("foodExhaustionLevel");
        }

    }

    public void writeNbt(ONBTTagCompound var1) {
        var1.a("foodLevel", this.foodLevel);
        var1.a("foodTickTimer", this.foodTimer);
        var1.a("foodSaturationLevel", this.foodSaturationLevel);
        var1.a("foodExhaustionLevel", this.foodExhaustionLevel);
    }

    public int getFoodLevel() {
        return this.foodLevel;
    }

    public boolean needsFood() {
        return this.foodLevel < 20;
    }

    public void addExhaustion(float var1) {
        this.foodExhaustionLevel = Math.min(this.foodExhaustionLevel + var1, 40.0F);
    }

    public float getFoodSaturationLevel() {
        return this.foodSaturationLevel;
    }
    //CanaryMod End
    
    //CanaryMod added set saturation
    public void setFoodSaturationLevel(float food) {
        this.foodSaturationLevel = food;
    }
    
    public void setFoodExhaustionLevel(float level) {
        this.foodExhaustionLevel = level;
    }
    //CanaryMod added set food level
    public void setFoodLevel(int level) {
        this.foodLevel = level;
    }
    
}
