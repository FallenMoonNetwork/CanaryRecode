package net.minecraft.server;

import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityAIAvoidEntity;
import net.minecraft.server.OEntityAIFollowGolem;
import net.minecraft.server.OEntityAIMoveIndoors;
import net.minecraft.server.OEntityAIMoveTwardsRestriction;
import net.minecraft.server.OEntityAIOpenDoor;
import net.minecraft.server.OEntityAIPlay;
import net.minecraft.server.OEntityAIRestrictOpenDoor;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAIVillagerMate;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityAIWatchClosest2;
import net.minecraft.server.OEntityAgeable;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityZombie;
import net.minecraft.server.OINpc;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OVillage;
import net.minecraft.server.OWorld;

public class OEntityVillager extends OEntityAgeable implements OINpc {

    private int b;
    private boolean c;
    private boolean g;
    OVillage a;

    public OEntityVillager(OWorld var1) {
        this(var1, 0);
    }

    public OEntityVillager(OWorld var1, int var2) {
        super(var1);
        this.b = 0;
        this.c = false;
        this.g = false;
        this.a = null;
        this.f_(var2);
        this.ae = "/mob/villager/villager.png";
        this.bb = 0.5F;
        this.al().b(true);
        this.al().a(true);
        this.aL.a(0, new OEntityAISwimming(this));
        this.aL.a(1, new OEntityAIAvoidEntity(this, OEntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.aL.a(2, new OEntityAIMoveIndoors(this));
        this.aL.a(3, new OEntityAIRestrictOpenDoor(this));
        this.aL.a(4, new OEntityAIOpenDoor(this, true));
        this.aL.a(5, new OEntityAIMoveTwardsRestriction(this, 0.3F));
        this.aL.a(6, new OEntityAIVillagerMate(this));
        this.aL.a(7, new OEntityAIFollowGolem(this));
        this.aL.a(8, new OEntityAIPlay(this, 0.32F));
        this.aL.a(9, new OEntityAIWatchClosest2(this, OEntityPlayer.class, 3.0F, 1.0F));
        this.aL.a(9, new OEntityAIWatchClosest2(this, OEntityVillager.class, 5.0F, 0.02F));
        this.aL.a(9, new OEntityAIWander(this, 0.3F));
        this.aL.a(10, new OEntityAIWatchClosest(this, OEntityLiving.class, 8.0F));
    }

    public boolean c_() {
        return true;
    }

    protected void g() {
        if (--this.b <= 0) {
            this.bi.A.a(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo));
            this.b = 70 + this.bS.nextInt(50);
            this.a = this.bi.A.a(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo), 32);
            if (this.a == null) {
                this.ax();
            } else {
                OChunkCoordinates var1 = this.a.a();
                this.b(var1.a, var1.b, var1.c, this.a.b());
            }
        }

        super.g();
    }

    protected void b() {
        super.b();
        this.bY.a(16, Integer.valueOf(0));
    }

    public int d() {
        return 20;
    }

    public void e() {
        super.e();
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Profession", this.x());
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.f_(var1.f("Profession"));
    }

    protected boolean n() {
        return false;
    }

    protected String i() {
        return "mob.villager.default";
    }

    protected String j() {
        return "mob.villager.defaulthurt";
    }

    protected String k() {
        return "mob.villager.defaultdeath";
    }

    public void f_(int var1) {
        this.bY.b(16, Integer.valueOf(var1));
    }

    public int x() {
        return this.bY.c(16);
    }

    public boolean A() {
        return this.c;
    }

    public void a(boolean var1) {
        this.c = var1;
    }

    public void b(boolean var1) {
        this.g = var1;
    }

    public boolean C() {
        return this.g;
    }

    public void a(OEntityLiving var1) {
        super.a(var1);
        if (this.a != null && var1 != null) {
            this.a.a(var1);
        }

    }
}
