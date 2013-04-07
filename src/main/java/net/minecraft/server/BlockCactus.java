package net.minecraft.server;


import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.hook.entity.DamageHook;


public class BlockCactus extends Block {

    protected BlockCactus(int i0) {
        super(i0, Material.y);
        this.b(true);
        this.a(CreativeTabs.c);
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (world.c(i0, i1 + 1, i2)) {
            int i3;

            for (i3 = 1; world.a(i0, i1 - i3, i2) == this.cz; ++i3) {
                ;
            }

            if (i3 < 3) {
                int i4 = world.h(i0, i1, i2);

                if (i4 == 15) {
                    world.c(i0, i1 + 1, i2, this.cz);
                    world.b(i0, i1, i2, 0, 4);
                    this.a(world, i0, i1 + 1, i2, this.cz);
                } else {
                    world.b(i0, i1, i2, i4 + 1, 4);
                }
            }
        }
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        float f0 = 0.0625F;

        return AxisAlignedBB.a().a((double) ((float) i0 + f0), (double) i1, (double) ((float) i2 + f0), (double) ((float) (i0 + 1) - f0), (double) ((float) (i1 + 1) - f0), (double) ((float) (i2 + 1) - f0));
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 13;
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return !super.c(world, i0, i1, i2) ? false : this.f(world, i0, i1, i2);
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (!this.f(world, i0, i1, i2)) {
            world.a(i0, i1, i2, true);
        }
    }

    public boolean f(World world, int i0, int i1, int i2) {
        if (world.g(i0 - 1, i1, i2).a()) {
            return false;
        } else if (world.g(i0 + 1, i1, i2).a()) {
            return false;
        } else if (world.g(i0, i1, i2 - 1).a()) {
            return false;
        } else if (world.g(i0, i1, i2 + 1).a()) {
            return false;
        } else {
            int i3 = world.a(i0, i1 - 1, i2);

            return i3 == Block.aZ.cz || i3 == Block.I.cz;
        }
    }

    public void a(World world, int i0, int i1, int i2, Entity entity) {
        // CanaryMod: Damage (Craptus)
        DamageHook hook = new DamageHook(null, entity.getCanaryEntity(), new CanaryDamageSource(DamageSource.g), 1);

        Canary.hooks().callHook(hook);
        if (!hook.isCanceled()) {
            entity.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
        }
        //
    }
}
