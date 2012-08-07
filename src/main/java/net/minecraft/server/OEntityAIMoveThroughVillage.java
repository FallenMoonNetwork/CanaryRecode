package net.minecraft.server;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OVillage;
import net.minecraft.server.OVillageDoorInfo;


public class OEntityAIMoveThroughVillage extends OEntityAIBase {

    private OEntityCreature a;
    private float b;
    private OPathEntity c;
    private OVillageDoorInfo d;
    private boolean e;
    private List f = new ArrayList();

    public OEntityAIMoveThroughVillage(OEntityCreature var1, float var2, boolean var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.e = var3;
        this.a(1);
    }

    @Override
    public boolean a() {
        this.f();
        if (this.e && this.a.bi.e()) {
            return false;
        } else {
            OVillage var1 = this.a.bi.A.a(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo), 0);

            if (var1 == null) {
                return false;
            } else {
                this.d = this.a(var1);
                if (this.d == null) {
                    return false;
                } else {
                    boolean var2 = this.a.al().b();

                    this.a.al().b(false);
                    this.c = this.a.al().a(this.d.a, this.d.b, this.d.c);
                    this.a.al().b(var2);
                    if (this.c != null) {
                        return true;
                    } else {
                        OVec3D var3 = ORandomPositionGenerator.a(this.a, 10, 7, OVec3D.b(this.d.a, this.d.b, this.d.c));

                        if (var3 == null) {
                            return false;
                        } else {
                            this.a.al().b(false);
                            this.c = this.a.al().a(var3.a, var3.b, var3.c);
                            this.a.al().b(var2);
                            return this.c != null;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean b() {
        if (this.a.al().e()) {
            return false;
        } else {
            float var1 = this.a.bG + 4.0F;

            return this.a.e((double) this.d.a, (double) this.d.b, (double) this.d.c) > (var1 * var1);
        }
    }

    @Override
    public void c() {
        this.a.al().a(this.c, this.b);
    }

    @Override
    public void d() {
        if (this.a.al().e() || this.a.e((double) this.d.a, (double) this.d.b, (double) this.d.c) < 16.0D) {
            this.f.add(this.d);
        }

    }

    private OVillageDoorInfo a(OVillage var1) {
        OVillageDoorInfo var2 = null;
        int var3 = Integer.MAX_VALUE;
        List var4 = var1.f();
        Iterator var5 = var4.iterator();

        while (var5.hasNext()) {
            OVillageDoorInfo var6 = (OVillageDoorInfo) var5.next();
            int var7 = var6.a(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo));

            if (var7 < var3 && !this.a(var6)) {
                var2 = var6;
                var3 = var7;
            }
        }

        return var2;
    }

    private boolean a(OVillageDoorInfo var1) {
        Iterator var2 = this.f.iterator();

        OVillageDoorInfo var3;

        do {
            if (!var2.hasNext()) {
                return false;
            }

            var3 = (OVillageDoorInfo) var2.next();
        } while (var1.a != var3.a || var1.b != var3.b || var1.c != var3.c);

        return true;
    }

    private void f() {
        if (this.f.size() > 15) {
            this.f.remove(0);
        }

    }
}
