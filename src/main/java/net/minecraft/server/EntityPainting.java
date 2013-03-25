package net.minecraft.server;

import java.util.ArrayList;
import net.canarymod.api.entity.CanaryPainting;

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
            this.e = (EnumArt) arraylist.get(this.ab.nextInt(arraylist.size()));
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

    public int g() {
        return this.e.D;
    }

    public void h() {
        this.a(new ItemStack(Item.at), 0.0F);
    }
}
