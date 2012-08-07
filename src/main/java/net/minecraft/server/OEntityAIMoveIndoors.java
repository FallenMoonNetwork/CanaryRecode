package net.minecraft.server;


import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OVillage;
import net.minecraft.server.OVillageDoorInfo;


public class OEntityAIMoveIndoors extends OEntityAIBase {

    private OEntityCreature a;
    private OVillageDoorInfo b;
    private int c = -1;
    private int d = -1;

    public OEntityAIMoveIndoors(OEntityCreature var1) {
        super();
        this.a = var1;
        this.a(1);
    }

    @Override
    public boolean a() {
        if ((!this.a.bi.e() || this.a.bi.x()) && !this.a.bi.t.e) {
            if (this.a.an().nextInt(50) != 0) {
                return false;
            } else if (this.c != -1 && this.a.e(this.c, this.a.bn, this.d) < 4.0D) {
                return false;
            } else {
                OVillage var1 = this.a.bi.A.a(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo), 14);

                if (var1 == null) {
                    return false;
                } else {
                    this.b = var1.c(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo));
                    return this.b != null;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean b() {
        return !this.a.al().e();
    }

    @Override
    public void c() {
        this.c = -1;
        if (this.a.e((double) this.b.a(), (double) this.b.b, (double) this.b.c()) > 256.0D) {
            OVec3D var1 = ORandomPositionGenerator.a(this.a, 14, 3, OVec3D.b(this.b.a() + 0.5D, this.b.b(), this.b.c() + 0.5D));

            if (var1 != null) {
                this.a.al().a(var1.a, var1.b, var1.c, 0.3F);
            }
        } else {
            this.a.al().a(this.b.a() + 0.5D, this.b.b(), this.b.c() + 0.5D, 0.3F);
        }

    }

    @Override
    public void d() {
        this.c = this.b.a();
        this.d = this.b.c();
        this.b = null;
    }
}
