package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.hook.world.ExplosionHook;

public class Explosion {

    public boolean a = false;
    public boolean b = true;
    private int i = 16;
    private Random j = new Random();
    private World k;
    public double c;
    public double d;
    public double e;
    public Entity f;
    public float g;
    public List h = new ArrayList();
    private Map l = new HashMap();

    public Explosion(World world, Entity entity, double d0, double d1, double d2, float f0) {
        this.k = world;
        this.f = entity;
        this.g = f0;
        this.c = d0;
        this.d = d1;
        this.e = d2;
    }

    @SuppressWarnings("unchecked")
    public void a() {
        float f0 = this.g;
        HashSet hashset = new HashSet();

        // CanaryMod: Ground Zero
        CanaryBlock gzero = (CanaryBlock) this.k.getCanaryWorld().getBlockAt((int) Math.floor(c), (int) Math.floor(d), (int) Math.floor(e));
        //

        int i0;
        int i1;
        int i2;
        double d0;
        double d1;
        double d2;

        for (i0 = 0; i0 < this.i; ++i0) {
            for (i1 = 0; i1 < this.i; ++i1) {
                for (i2 = 0; i2 < this.i; ++i2) {
                    if (i0 == 0 || i0 == this.i - 1 || i1 == 0 || i1 == this.i - 1 || i2 == 0 || i2 == this.i - 1) {
                        double d3 = (double) ((float) i0 / ((float) this.i - 1.0F) * 2.0F - 1.0F);
                        double d4 = (double) ((float) i1 / ((float) this.i - 1.0F) * 2.0F - 1.0F);
                        double d5 = (double) ((float) i2 / ((float) this.i - 1.0F) * 2.0F - 1.0F);
                        double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);

                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        float f1 = this.g * (0.7F + this.k.s.nextFloat() * 0.6F);

                        d0 = this.c;
                        d1 = this.d;
                        d2 = this.e;

                        for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
                            int i3 = MathHelper.c(d0);
                            int i4 = MathHelper.c(d1);
                            int i5 = MathHelper.c(d2);
                            int i6 = this.k.a(i3, i4, i5);

                            if (i6 > 0) {
                                Block block = Block.r[i6];
                                float f3 = this.f != null ? this.f.a(this, this.k, i3, i4, i5, block) : block.a(this.f);

                                f1 -= (f3 + 0.3F) * f2;
                            }

                            if (f1 > 0.0F && (this.f == null || this.f.a(this, this.k, i3, i4, i5, i6, f1))) {
                                hashset.add(new ChunkPosition(i3, i4, i5));
                            }

                            d0 += d3 * (double) f2;
                            d1 += d4 * (double) f2;
                            d2 += d5 * (double) f2;
                        }
                    }
                }
            }
        }

        // CanaryMod: Add affected blocks into a List of Blocks.
        List<net.canarymod.api.world.blocks.Block> blkAff = new ArrayList<net.canarymod.api.world.blocks.Block>(hashset.size());
        for (ChunkPosition ocp : (HashSet<ChunkPosition>) hashset) {
            blkAff.add(this.k.getCanaryWorld().getBlockAt(ocp.a, ocp.b, ocp.c));
        }
        // Explosion call
        ExplosionHook exp = new ExplosionHook(gzero, this.f != null ? this.f.getCanaryEntity() : null, blkAff);
        Canary.hooks().callHook(exp);
        // if cancelled, don't populate this.h at all.
        if (!exp.isCanceled()) {
            // Repopulate hashset according to blocksAffected.
            hashset.clear();
            for (net.canarymod.api.world.blocks.Block affected : exp.getAffectedBlocks()) {
                hashset.add(new ChunkPosition(affected.getX(), affected.getY(), affected.getZ()));
            }
            this.h.addAll(hashset);
        }
        //
        this.g *= 2.0F;
        i0 = MathHelper.c(this.c - (double) this.g - 1.0D);
        i1 = MathHelper.c(this.c + (double) this.g + 1.0D);
        i2 = MathHelper.c(this.d - (double) this.g - 1.0D);
        int i7 = MathHelper.c(this.d + (double) this.g + 1.0D);
        int i8 = MathHelper.c(this.e - (double) this.g - 1.0D);
        int i9 = MathHelper.c(this.e + (double) this.g + 1.0D);
        List list = this.k.b(this.f, AxisAlignedBB.a().a((double) i0, (double) i2, (double) i8, (double) i1, (double) i7, (double) i9));
        Vec3 vec3 = this.k.T().a(this.c, this.d, this.e);

        for (int i10 = 0; i10 < list.size(); ++i10) {
            Entity entity = (Entity) list.get(i10);
            double d7 = entity.f(this.c, this.d, this.e) / (double) this.g;

            if (d7 <= 1.0D) {
                d0 = entity.u - this.c;
                d1 = entity.v + (double) entity.e() - this.d;
                d2 = entity.w - this.e;
                double d8 = (double) MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

                if (d8 != 0.0D) {
                    d0 /= d8;
                    d1 /= d8;
                    d2 /= d8;
                    double d9 = (double) this.k.a(vec3, entity.E);
                    double d10 = (1.0D - d7) * d9;

                    // CanaryMod Damage hook: Explosions
                    int damage = (int) ((d10 * d10 + d10) / 2.0D * 8.0D * (double) this.g + 1.0D);
                    CanaryDamageSource source = DamageSource.a(this).getCanaryDamageSource();
                    DamageHook dmg = new DamageHook(this.f != null ? this.f.getCanaryEntity() : null, entity.getCanaryEntity(), source, damage);
                    Canary.hooks().callHook(dmg);
                    if (!dmg.isCanceled()) {
                        entity.a((((CanaryDamageSource) dmg.getDamageSource()).getHandle()), damage);
                    }
                    //

                    double d11 = EnchantmentProtection.a(entity, d10);

                    entity.x += d0 * d11;
                    entity.y += d1 * d11;
                    entity.z += d2 * d11;
                    if (entity instanceof EntityPlayer) {
                        this.l.put((EntityPlayer) entity, this.k.T().a(d0 * d10, d1 * d10, d2 * d10));
                    }
                }
            }
        }

        this.g = f0;
    }

    public void a(boolean flag0) {
        this.k.a(this.c, this.d, this.e, "random.explode", 4.0F, (1.0F + (this.k.s.nextFloat() - this.k.s.nextFloat()) * 0.2F) * 0.7F);
        if (this.g >= 2.0F && this.b) {
            this.k.a("hugeexplosion", this.c, this.d, this.e, 1.0D, 0.0D, 0.0D);
        } else {
            this.k.a("largeexplode", this.c, this.d, this.e, 1.0D, 0.0D, 0.0D);
        }

        Iterator iterator;
        ChunkPosition chunkposition;
        int i0;
        int i1;
        int i2;
        int i3;

        if (this.b) {
            iterator = this.h.iterator();

            while (iterator.hasNext()) {
                chunkposition = (ChunkPosition) iterator.next();
                i0 = chunkposition.a;
                i1 = chunkposition.b;
                i2 = chunkposition.c;
                i3 = this.k.a(i0, i1, i2);
                if (flag0) {
                    double d0 = (double) ((float) i0 + this.k.s.nextFloat());
                    double d1 = (double) ((float) i1 + this.k.s.nextFloat());
                    double d2 = (double) ((float) i2 + this.k.s.nextFloat());
                    double d3 = d0 - this.c;
                    double d4 = d1 - this.d;
                    double d5 = d2 - this.e;
                    double d6 = (double) MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5D / (d6 / (double) this.g + 0.1D);

                    d7 *= (double) (this.k.s.nextFloat() * this.k.s.nextFloat() + 0.3F);
                    d3 *= d7;
                    d4 *= d7;
                    d5 *= d7;
                    this.k.a("explode", (d0 + this.c * 1.0D) / 2.0D, (d1 + this.d * 1.0D) / 2.0D, (d2 + this.e * 1.0D) / 2.0D, d3, d4, d5);
                    this.k.a("smoke", d0, d1, d2, d3, d4, d5);
                }

                if (i3 > 0) {
                    Block block = Block.r[i3];

                    if (block.a(this)) {
                        block.a(this.k, i0, i1, i2, this.k.h(i0, i1, i2), 1.0F / this.g, 0);
                    }

                    this.k.f(i0, i1, i2, 0, 0, 3);
                    block.a(this.k, i0, i1, i2, this);
                }
            }
        }

        if (this.a) {
            iterator = this.h.iterator();

            while (iterator.hasNext()) {
                chunkposition = (ChunkPosition) iterator.next();
                i0 = chunkposition.a;
                i1 = chunkposition.b;
                i2 = chunkposition.c;
                i3 = this.k.a(i0, i1, i2);
                int i4 = this.k.a(i0, i1 - 1, i2);

                if (i3 == 0 && Block.s[i4] && this.j.nextInt(3) == 0) {
                    this.k.c(i0, i1, i2, Block.av.cz);
                }
            }
        }
    }

    public Map b() {
        return this.l;
    }

    public EntityLiving c() {
        return this.f == null ? null : (this.f instanceof EntityTNTPrimed ? ((EntityTNTPrimed) this.f).c() : (this.f instanceof EntityLiving ? (EntityLiving) this.f : null));
    }
}
