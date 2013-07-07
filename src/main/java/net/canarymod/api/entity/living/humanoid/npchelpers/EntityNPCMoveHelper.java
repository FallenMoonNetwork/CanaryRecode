package net.canarymod.api.entity.living.humanoid.npchelpers;

import net.canarymod.api.entity.living.humanoid.EntityNonPlayableCharacter;
import net.minecraft.server.MathHelper;
import net.minecraft.server.SharedMonsterAttributes;

public class EntityNPCMoveHelper {

    private EntityNonPlayableCharacter a;
    private double b;
    private double c;
    private double d;
    private double e;
    private boolean f;

    public EntityNPCMoveHelper(EntityNonPlayableCharacter entityliving) {
        this.a = entityliving;
        this.b = entityliving.u;
        this.c = entityliving.v;
        this.d = entityliving.w;
    }

    public boolean a() {
        return this.f;
    }

    public double b() {
        return this.e;
    }

    public void a(double d0, double d1, double d2, double d3) {
        this.b = d0;
        this.c = d1;
        this.d = d2;
        this.e = d3;
        this.f = true;
    }

    public void c() {
        this.a.entityliving_n_clone(0.0F);
        if (this.f) {
            this.f = false;
            int i0 = MathHelper.c(this.a.E.b + 0.5D);
            double d0 = this.b - this.a.u;
            double d1 = this.d - this.a.w;
            double d2 = this.c - (double) i0;
            double d3 = d0 * d0 + d2 * d2 + d1 * d1;

            if (d3 >= 2.500000277905201E-7D) {
                float f0 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;

                this.a.A = this.a(this.a.A, f0, 30.0F);
                this.a.i((float) (this.e * this.a.a(SharedMonsterAttributes.d).e()));
                if (d2 > 0.0D && d0 * d0 + d1 * d1 < 1.0D) {
                    this.a.getJumpHelper().a();
                }
            }
        }
    }

    private float a(float f0, float f1, float f2) {
        float f3 = MathHelper.g(f1 - f0);

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f0 + f3;
    }
}
