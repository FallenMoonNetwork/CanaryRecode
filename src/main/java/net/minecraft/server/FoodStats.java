package net.minecraft.server;

public class FoodStats {

    private int a = 20;
    private float b = 5.0F;
    private float c;
    private int d = 0;
    private int e = 20;
    private EntityPlayer entityplayer;

    public FoodStats(EntityPlayer entityplayer) {
        this.entityplayer = entityplayer;
    }

    public void a(int i0, float f0) {
        this.a = Math.min(i0 + this.a, 20);
        this.b = Math.min(this.b + (float) i0 * f0 * 2.0F, (float) this.a);
    }

    public void a(ItemFood itemfood) {
        this.a(itemfood.g(), itemfood.h());
    }

    public void a(EntityPlayer entityplayer) {
        int i0 = entityplayer.q.r;

        this.e = this.a;
        if (this.c > 4.0F) {
            this.c -= 4.0F;
            if (this.b > 0.0F) {
                this.b = Math.max(this.b - 1.0F, 0.0F);
            } else if (i0 > 0) {
                this.a = Math.max(this.a - 1, 0);
            }
        }

        if (this.a >= 18 && entityplayer.cm()) {
            ++this.d;
            if (this.d >= 80) {
                entityplayer.j(1);
                this.d = 0;
            }
        } else if (this.a <= 0) {
            ++this.d;
            if (this.d >= 80) {
                if (entityplayer.aX() > 10 || i0 >= 3 || entityplayer.aX() > 1 && i0 >= 2) {
                    entityplayer.a(DamageSource.f, 1);
                }

                this.d = 0;
            }
        } else {
            this.d = 0;
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.b("foodLevel")) {
            this.a = nbttagcompound.e("foodLevel");
            this.d = nbttagcompound.e("foodTickTimer");
            this.b = nbttagcompound.g("foodSaturationLevel");
            this.c = nbttagcompound.g("foodExhaustionLevel");
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("foodLevel", this.a);
        nbttagcompound.a("foodTickTimer", this.d);
        nbttagcompound.a("foodSaturationLevel", this.b);
        nbttagcompound.a("foodExhaustionLevel", this.c);
    }

    public int a() {
        return this.a;
    }

    public boolean c() {
        return this.a < 20;
    }

    public void a(float f0) {
        this.c = Math.min(this.c + f0, 40.0F);
    }

    public float e() {
        return this.b;
    }

    //CanaryMod
    /**
     * Set the exhaustion leven, overriding the old value
     * @param f
     */
    public void setExhaustionLevel(float f) {
        this.c = Math.min(f, 40f);
    }

    public float getExhaustionLevel() {
        return this.c;
    }

    public void setFoodLevel(int food) {
        this.a = Math.min(food, 20);
    }
}
