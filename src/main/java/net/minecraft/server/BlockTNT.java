package net.minecraft.server;

import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.hook.world.TNTActivateHook;
import net.canarymod.hook.world.TNTActivateHook.ActivationCause;

import java.util.Random;

public class BlockTNT extends Block {

    public BlockTNT(int i0) {
        super(i0, Material.u);
        this.a(CreativeTabs.d);
    }

    public void a(World world, int i0, int i1, int i2) {
        super.a(world, i0, i1, i2);
        if (world.C(i0, i1, i2)) {
            // this.g(world, i0, i1, i2, 1);
            this.a(world, i0, i1, i2, 1, null, ActivationCause.REDSTONE); // CanaryMod: REDSTONE cause
            world.i(i0, i1, i2);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (world.C(i0, i1, i2)) {
            // this.g(world, i0, i1, i2, 1);
            this.a(world, i0, i1, i2, 1, null, ActivationCause.REDSTONE); // CanaryMod: REDSTONE cause
            world.i(i0, i1, i2);
        }
    }

    public int a(Random random) {
        return 1;
    }

    public void a(World world, int i0, int i1, int i2, Explosion explosion) {
        if (!world.I) {
            // CanaryMod: TNTActivateHook
            TNTActivateHook tah = (TNTActivateHook)new TNTActivateHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), null, ActivationCause.EXPLOSION).call();
            if (!tah.isCanceled()) {
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double)((float)i0 + 0.5F), (double)((float)i1 + 0.5F), (double)((float)i2 + 0.5F), explosion.c());

                entitytntprimed.a = world.s.nextInt(entitytntprimed.a / 4) + entitytntprimed.a / 8;
                world.d((Entity)entitytntprimed);
            }
            //
        }
    }

    public void g(World world, int i0, int i1, int i2, int i3) {
        this.a(world, i0, i1, i2, i3, (EntityLivingBase)null, ActivationCause.UNKNOWN); // CanaryMod: Can't say for sure what calls this...
    }

    public void a(World world, int i0, int i1, int i2, int i3, EntityLivingBase entitylivingbase) { // Original Method
        this.a(world, i0, i1, i2, i3, entitylivingbase, ActivationCause.UNKNOWN);
    }

    public void a(World world, int i0, int i1, int i2, int i3, EntityLivingBase entitylivingbase, ActivationCause cause) { // CanaryMod: Signature Change to incorporate ActivationCause
        if (!world.I) {
            if ((i3 & 1) == 1) {
                // CanaryMod: TNTActivateHook
                TNTActivateHook tah = (TNTActivateHook)new TNTActivateHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), (LivingBase)(entitylivingbase == null ? null : entitylivingbase.getCanaryEntity()), cause).call();
                if (!tah.isCanceled()) {
                    EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double)((float)i0 + 0.5F), (double)((float)i1 + 0.5F), (double)((float)i2 + 0.5F), entitylivingbase);

                    world.d((Entity)entitytntprimed);
                    world.a((Entity)entitytntprimed, "random.fuse", 1.0F, 1.0F);
                }
                //
            }
        }
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        if (entityplayer.by() != null && entityplayer.by().d == Item.k.cv) {
            this.a(world, i0, i1, i2, 1, entityplayer, ActivationCause.FIRE); // CanaryMod: Add FIRE cause
            world.i(i0, i1, i2);
            entityplayer.by().a(1, (EntityLivingBase)entityplayer);
            return true;
        }
        else {
            return super.a(world, i0, i1, i2, entityplayer, i3, f0, f1, f2);
        }
    }

    public void a(World world, int i0, int i1, int i2, Entity entity) {
        if (entity instanceof EntityArrow && !world.I) {
            EntityArrow entityarrow = (EntityArrow)entity;

            if (entityarrow.af()) {
                this.a(world, i0, i1, i2, 1, entityarrow.c instanceof EntityLivingBase ? (EntityLivingBase)entityarrow.c : null, ActivationCause.FIRE); // CanaryMod: add FIRE cause
                world.i(i0, i1, i2);
            }
        }
    }

    public boolean a(Explosion explosion) {
        return false;
    }
}
