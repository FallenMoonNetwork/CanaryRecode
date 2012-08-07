package net.minecraft.server;


import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVillage;
import net.minecraft.server.OVillageDoorInfo;


public class OEntityAIRestrictOpenDoor extends OEntityAIBase {

    private OEntityCreature a;
    private OVillageDoorInfo b;

    public OEntityAIRestrictOpenDoor(OEntityCreature var1) {
        super();
        this.a = var1;
    }

    @Override
    public boolean a() {
        if (this.a.bi.e()) {
            return false;
        } else {
            OVillage var1 = this.a.bi.A.a(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo), 16);

            if (var1 == null) {
                return false;
            } else {
                this.b = var1.b(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo));
                return this.b == null ? false : this.b.b(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo)) < 2.25D;
            }
        }
    }

    @Override
    public boolean b() {
        return this.a.bi.e() ? false : !this.b.g && this.b.a(OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bo));
    }

    @Override
    public void c() {
        this.a.al().b(false);
        this.a.al().c(false);
    }

    @Override
    public void d() {
        this.a.al().b(true);
        this.a.al().c(true);
        this.b = null;
    }

    @Override
    public void e() {
        this.b.e();
    }
}
