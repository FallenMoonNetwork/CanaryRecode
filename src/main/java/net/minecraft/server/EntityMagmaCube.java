package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanaryMagmaCube;


public class EntityMagmaCube extends EntitySlime {

    public EntityMagmaCube(World world) {
        super(world);
        this.ag = true;
        this.aO = 0.2F;
        this.entity = new CanaryMagmaCube(this); // CanaryMod: Wrap Entity
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.d).a(0.20000000298023224D);
    }

    public boolean bo() {
        return this.q.r > 0 && this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public int aM() {
        return this.bN() * 3;
    }

    public float d(float f0) {
        return 1.0F;
    }

    protected String bF() {
        return "flame";
    }

    protected EntitySlime bG() {
        return new EntityMagmaCube(this.q);
    }

    protected int s() {
        return Item.bz.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.s();

        if (i1 > 0 && this.bN() > 1) {
            int i2 = this.ab.nextInt(4) - 2;

            if (i0 > 0) {
                i2 += this.ab.nextInt(i0 + 1);
            }

            for (int i3 = 0; i3 < i2; ++i3) {
                this.b(i1, 1);
            }
        }
    }

    public boolean ad() {
        return false;
    }

    protected int bH() {
        return super.bH() * 4;
    }

    protected void bI() {
        this.h *= 0.9F;
    }

    protected void ba() {
        this.y = (double) (0.42F + (float) this.bN() * 0.1F);
        this.an = true;
    }

    protected void b(float f0) {}

    protected boolean bJ() {
        return true;
    }

    protected int bK() {
        return super.bK() + 2;
    }

    protected String aK() {
        return "mob.slime." + (this.bN() > 1 ? "big" : "small");
    }

    protected String aL() {
        return "mob.slime." + (this.bN() > 1 ? "big" : "small");
    }

    protected String bL() {
        return this.bN() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
    }

    public boolean I() {
        return false;
    }

    protected boolean bM() {
        return true;
    }
}
