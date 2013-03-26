package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanaryLavaSlime;


public class EntityMagmaCube extends EntitySlime {

    public EntityMagmaCube(World world) {
        super(world);
        this.aH = "/mob/lava.png";
        this.ag = true;
        this.aO = 0.2F;
        this.entity = new CanaryLavaSlime(this); // CanaryMod: Wrap Entity
    }

    public boolean bv() {
        return this.q.r > 0 && this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public int aZ() {
        return this.p() * 3;
    }

    public float c(float f0) {
        return 1.0F;
    }

    protected String h() {
        return "flame";
    }

    protected EntitySlime i() {
        return new EntityMagmaCube(this.q);
    }

    protected int be() {
        return Item.by.cp;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.be();

        if (i1 > 0 && this.p() > 1) {
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

    protected int j() {
        return super.j() * 4;
    }

    protected void k() {
        this.b *= 0.9F;
    }

    protected void bl() {
        this.y = (double) (0.42F + (float) this.p() * 0.1F);
        this.an = true;
    }

    protected void a(float f0) {}

    protected boolean l() {
        return true;
    }

    protected int m() {
        return super.m() + 2;
    }

    protected String bc() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected String bd() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected String n() {
        return this.p() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
    }

    public boolean I() {
        return false;
    }

    protected boolean o() {
        return true;
    }
}
