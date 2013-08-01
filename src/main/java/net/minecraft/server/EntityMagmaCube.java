package net.minecraft.server;

import net.canarymod.api.entity.living.monster.CanaryMagmaCube;

public class EntityMagmaCube extends EntitySlime {

    public EntityMagmaCube(World world) {
        super(world);
        this.ag = true;
        this.aO = 0.2F;
        this.entity = new CanaryMagmaCube(this); // CanaryMod: Wrap Entity
    }

    protected void ay() {
        super.ay();
        this.a(SharedMonsterAttributes.d).a(0.20000000298023224D);
    }

    public boolean bs() {
        return this.q.r > 0 && this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public int aP() {
        return this.bR() * 3;
    }

    public float d(float f0) {
        return 1.0F;
    }

    protected String bJ() {
        return "flame";
    }

    protected EntitySlime bK() {
        return new EntityMagmaCube(this.q);
    }

    protected int s() {
        return Item.bz.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.s();

        if (i1 > 0 && this.bR() > 1) {
            int i2 = this.ab.nextInt(4) - 2;

            if (i0 > 0) {
                i2 += this.ab.nextInt(i0 + 1);
            }

            for (int i3 = 0; i3 < i2; ++i3) {
                this.b(i1, 1);
            }
        }
    }

    public boolean ae() {
        return false;
    }

    protected int bL() {
        return super.bL() * 4;
    }

    protected void bM() {
        this.h *= 0.9F;
    }

    protected void bd() {
        this.y = (double) (0.42F + (float) this.bR() * 0.1F);
        this.an = true;
    }

    protected void b(float f0) {}

    protected boolean bN() {
        return true;
    }

    protected int bO() {
        return super.bO() + 2;
    }

    protected String aN() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    protected String aO() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    protected String bP() {
        return this.bR() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
    }

    public boolean I() {
        return false;
    }

    protected boolean bQ() {
        return true;
    }
}
