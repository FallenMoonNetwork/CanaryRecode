package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OEntityAIFleeSun extends OEntityAIBase {

    private OEntityCreature a;
    private double b;
    private double c;
    private double d;
    private float e;
    private OWorld f;

    public OEntityAIFleeSun(OEntityCreature var1, float var2) {
        super();
        this.a = var1;
        this.e = var2;
        this.f = var1.bi;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (!this.f.e()) {
            return false;
        } else if (!this.a.B_()) {
            return false;
        } else if (!this.f.l(OMathHelper.b(this.a.bm), (int) this.a.bw.b, OMathHelper.b(this.a.bo))) {
            return false;
        } else {
            OVec3D var1 = this.f();
            if (var1 == null) {
                return false;
            } else {
                this.b = var1.a;
                this.c = var1.b;
                this.d = var1.c;
                return true;
            }
        }
    }

    @Override
    public boolean b() {
        return !this.a.al().e();
    }

    @Override
    public void c() {
        this.a.al().a(this.b, this.c, this.d, this.e);
    }

    private OVec3D f() {
        Random var1 = this.a.an();

        for (int var2 = 0; var2 < 10; ++var2) {
            int var3 = OMathHelper.b(this.a.bm + var1.nextInt(20) - 10.0D);
            int var4 = OMathHelper.b(this.a.bw.b + var1.nextInt(6) - 3.0D);
            int var5 = OMathHelper.b(this.a.bo + var1.nextInt(20) - 10.0D);
            if (!this.f.l(var3, var4, var5) && this.a.a(var3, var4, var5) < 0.0F) {
                return OVec3D.b(var3, var4, var5);
            }
        }

        return null;
    }
}
