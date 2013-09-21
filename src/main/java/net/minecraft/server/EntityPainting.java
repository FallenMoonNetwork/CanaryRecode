package net.minecraft.server;

import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.DamageType;
import net.canarymod.api.entity.hanging.CanaryPainting;
import net.canarymod.api.entity.hanging.HangingEntity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.entity.HangingEntityDestroyHook;

import java.util.ArrayList;

public class EntityPainting extends EntityHanging {

    public EnumArt e;

    public EntityPainting(World world) {
        super(world);
        this.entity = new CanaryPainting(this); // CanaryMod: Wrap Entity
    }

    public EntityPainting(World world, int i0, int i1, int i2, int i3) {
        super(world, i0, i1, i2, i3);
        ArrayList arraylist = new ArrayList();
        EnumArt[] aenumart = EnumArt.values();
        int i4 = aenumart.length;

        for (int i5 = 0; i5 < i4; ++i5) {
            EnumArt enumart = aenumart[i5];

            this.e = enumart;
            this.a(i3);
            if (this.c()) {
                arraylist.add(enumart);
            }
        }

        if (!arraylist.isEmpty()) {
            this.e = (EnumArt)arraylist.get(this.ab.nextInt(arraylist.size()));
        }

        this.a(i3);
        this.entity = new CanaryPainting(this); // CanaryMod: Wrap Entity
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Motive", this.e.B);
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        String s0 = nbttagcompound.i("Motive");
        EnumArt[] aenumart = EnumArt.values();
        int i0 = aenumart.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            EnumArt enumart = aenumart[i1];

            if (enumart.B.equals(s0)) {
                this.e = enumart;
            }
        }

        if (this.e == null) {
            this.e = EnumArt.a;
        }

        super.a(nbttagcompound);
    }

    public int d() {
        return this.e.C;
    }

    public int e() {
        return this.e.D;
    }

    public void b(Entity entity) {
        //CanaryMod start
        HangingEntityDestroyHook hook = null;
        boolean isPlayer = false;
        if (entity instanceof EntityPlayer) {
            isPlayer = true;
            hook = (HangingEntityDestroyHook)new HangingEntityDestroyHook((HangingEntity)this.getCanaryEntity(), (Player)entity.getCanaryEntity(), CanaryDamageSource.getDamageSourceFromType(DamageType.GENERIC)).call();
        }
        else {
            hook = (HangingEntityDestroyHook)new HangingEntityDestroyHook((HangingEntity)this.getCanaryEntity(), null, CanaryDamageSource.getDamageSourceFromType(DamageType.GENERIC)).call();
        }
        if (hook.isCanceled()) {
            return;
        }
        //CanaryMod end

        //CanaryMod changed to spare an instanceof
        if (isPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entity;

            if (entityplayer.bG.d) {
                return;
            }
        }

        this.a(new ItemStack(Item.au), 0.0F);
    }
}
