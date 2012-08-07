package net.minecraft.server;


import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;


public class OEntityAIWander extends OEntityAIBase {

    private OEntityCreature a;
    private double b;
    private double c;
    private double d;
    private float e;

    public OEntityAIWander(OEntityCreature var1, float var2) {
        super();
        this.a = var1;
        this.e = var2;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (this.a.aq() >= 100) {
            return false;
        } else if (this.a.an().nextInt(120) != 0) {
            return false;
        } else {
            OVec3D var1 = ORandomPositionGenerator.a(this.a, 10, 7);

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
}
