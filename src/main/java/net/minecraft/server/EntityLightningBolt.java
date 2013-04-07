package net.minecraft.server;


import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryLightningBolt;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.IgnitionHook;


public class EntityLightningBolt extends EntityWeatherEffect {

    private int b;
    public long a = 0L;
    private int c;

    public EntityLightningBolt(World world, double d0, double d1, double d2) {
        super(world);
        this.b(d0, d1, d2, 0.0F, 0.0F);
        this.b = 2;
        this.a = this.ab.nextLong();
        this.c = this.ab.nextInt(3) + 1;
        this.entity = new CanaryLightningBolt(this); // CanaryMod: Wrap Entity
        if (!world.I && world.r >= 2 && world.b(MathHelper.c(d0), MathHelper.c(d1), MathHelper.c(d2), 10)) {
            int i0 = MathHelper.c(d0);
            int i1 = MathHelper.c(d1);
            int i2 = MathHelper.c(d2);

            if (world.a(i0, i1, i2) == 0 && Block.av.c(world, i0, i1, i2)) {
                // CanaryMod: Ignition
                CanaryBlock ignited = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

                ignited.setStatus((byte) 5); // LightningBolt Status 5
                IgnitionHook hook = new IgnitionHook(ignited, null);

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    world.c(i0, i1, i2, Block.av.cz);
                }
                //

            }

            for (i0 = 0; i0 < 4; ++i0) {
                i1 = MathHelper.c(d0) + this.ab.nextInt(3) - 1;
                i2 = MathHelper.c(d1) + this.ab.nextInt(3) - 1;
                int i3 = MathHelper.c(d2) + this.ab.nextInt(3) - 1;

                if (world.a(i1, i2, i3) == 0 && Block.av.c(world, i1, i2, i3)) {
                    // CanaryMod: Ignition
                    CanaryBlock ignited = (CanaryBlock) world.getCanaryWorld().getBlockAt(i1, i2, i3);

                    ignited.setStatus((byte) 5); // LightningBolt Status 5
                    IgnitionHook hook = new IgnitionHook(ignited, null);

                    Canary.hooks().callHook(hook);
                    if (!hook.isCanceled()) {
                        world.c(i1, i2, i3, Block.av.cz);
                    }
                    //

                }
            }
        }
    }

    public void l_() {
        super.l_();
        if (this.b == 2) {
            this.q.a(this.u, this.v, this.w, "ambient.weather.thunder", 10000.0F, 0.8F + this.ab.nextFloat() * 0.2F);
            this.q.a(this.u, this.v, this.w, "random.explode", 2.0F, 0.5F + this.ab.nextFloat() * 0.2F);
        }

        --this.b;
        if (this.b < 0) {
            if (this.c == 0) {
                this.w();
            } else if (this.b < -this.ab.nextInt(10)) {
                --this.c;
                this.b = 1;
                this.a = this.ab.nextLong();
                if (!this.q.I && this.q.b(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w), 10)) {
                    int i0 = MathHelper.c(this.u);
                    int i1 = MathHelper.c(this.v);
                    int i2 = MathHelper.c(this.w);

                    if (this.q.a(i0, i1, i2) == 0 && Block.av.c(this.q, i0, i1, i2)) {
                        this.q.c(i0, i1, i2, Block.av.cz);
                    }
                }
            }
        }

        if (this.b >= 0) {
            if (this.q.I) {
                this.q.q = 2;
            } else {
                double d0 = 3.0D;
                List list = this.q.b((Entity) this, AxisAlignedBB.a().a(this.u - d0, this.v - d0, this.w - d0, this.u + d0, this.v + 6.0D + d0, this.w + d0));

                for (int i3 = 0; i3 < list.size(); ++i3) {
                    Entity entity = (Entity) list.get(i3);

                    entity.a(this);
                }
            }
        }
    }

    protected void a() {}

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}
}
