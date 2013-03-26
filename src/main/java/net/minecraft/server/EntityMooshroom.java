package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanaryMooshroom;

public class EntityMooshroom extends EntityCow {

    public EntityMooshroom(World world) {
        super(world);
        this.aH = "/mob/redcow.png";
        this.a(0.9F, 1.3F);
        this.entity = new CanaryMooshroom(this); // CanaryMod: Wrap Entity
    }

    public boolean a_(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bK.h();

        if (itemstack != null && itemstack.c == Item.F.cp && this.b() >= 0) {
            if (itemstack.a == 1) {
                entityplayer.bK.a(entityplayer.bK.c, new ItemStack(Item.G));
                return true;
            }

            if (entityplayer.bK.a(new ItemStack(Item.G)) && !entityplayer.ce.d) {
                entityplayer.bK.a(entityplayer.bK.c, 1);
                return true;
            }
        }

        if (itemstack != null && itemstack.c == Item.bf.cp && this.b() >= 0) {
            this.w();
            this.q.a("largeexplode", this.u, this.v + (double) (this.P / 2.0F), this.w, 0.0D, 0.0D, 0.0D);
            if (!this.q.I) {
                EntityCow entitycow = new EntityCow(this.q);

                entitycow.b(this.u, this.v, this.w, this.A, this.B);
                entitycow.b(this.aX());
                entitycow.ay = this.ay;
                this.q.d((Entity) entitycow);

                for (int i0 = 0; i0 < 5; ++i0) {
                    this.q.d((Entity) (new EntityItem(this.q, this.u, this.v + (double) this.P, this.w, new ItemStack(Block.ak))));
                }
            }

            return true;
        } else {
            return super.a_(entityplayer);
        }
    }

    public EntityMooshroom c(EntityAgeable entityageable) {
        return new EntityMooshroom(this.q);
    }

    public EntityCow b(EntityAgeable entityageable) {
        return this.c(entityageable);
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.c(entityageable);
    }
}
