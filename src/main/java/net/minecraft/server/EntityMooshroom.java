package net.minecraft.server;


import net.canarymod.api.entity.living.animal.CanaryMooshroom;


public class EntityMooshroom extends EntityCow {

    public EntityMooshroom(World world) {
        super(world);
        this.a(0.9F, 1.3F);
        this.entity = new CanaryMooshroom(this); // CanaryMod: Wrap Entity
    }

    public boolean a(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.h();

        if (itemstack != null && itemstack.d == Item.G.cv && this.b() >= 0) {
            if (itemstack.b == 1) {
                entityplayer.bn.a(entityplayer.bn.c, new ItemStack(Item.H));
                return true;
            }

            if (entityplayer.bn.a(new ItemStack(Item.H)) && !entityplayer.bG.d) {
                entityplayer.bn.a(entityplayer.bn.c, 1);
                return true;
            }
        }

        if (itemstack != null && itemstack.d == Item.bg.cv && this.b() >= 0) {
            this.w();
            this.q.a("largeexplode", this.u, this.v + (double) (this.P / 2.0F), this.w, 0.0D, 0.0D, 0.0D);
            if (!this.q.I) {
                EntityCow entitycow = new EntityCow(this.q);

                entitycow.b(this.u, this.v, this.w, this.A, this.B);
                entitycow.g(this.aJ());
                entitycow.aN = this.aN;
                this.q.d((Entity) entitycow);

                for (int i0 = 0; i0 < 5; ++i0) {
                    this.q.d((Entity) (new EntityItem(this.q, this.u, this.v + (double) this.P, this.w, new ItemStack(Block.al))));
                }
            }

            return true;
        } else {
            return super.a(entityplayer);
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
