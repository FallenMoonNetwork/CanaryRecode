package net.minecraft.server;


import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;


public class OEntityAIPanic extends OEntityAIBase {

    private OEntityCreature a;
    private float b;
    private double c;
    private double d;
    private double e;

    public OEntityAIPanic(OEntityCreature var1, float var2) {
        super();
        this.a = var1;
        this.b = var2;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (this.a.ao() == null) {
            return false;
        } else {
            OVec3D var1 = ORandomPositionGenerator.a(this.a, 5, 4);

            if (var1 == null) {
                return false;
            } else {
                this.c = var1.a;
                this.d = var1.b;
                this.e = var1.c;
                return true;
            }
        }
    }

    @Override
    public void c() {
        this.a.al().a(this.c, this.d, this.e, this.b);
    }

    @Override
    public boolean b() {
        return !this.a.al().e();
    }
}
