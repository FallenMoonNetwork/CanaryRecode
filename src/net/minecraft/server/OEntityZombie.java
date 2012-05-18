package net.minecraft.server;

import net.minecraft.server.OEntityAIAttackOnCollide;
import net.minecraft.server.OEntityAIBreakDoor;
import net.minecraft.server.OEntityAIHurtByTarget;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAIMoveThroughVillage;
import net.minecraft.server.OEntityAIMoveTwardsRestriction;
import net.minecraft.server.OEntityAINearestAttackableTarget;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityVillager;
import net.minecraft.server.OEnumCreatureAttribute;
import net.minecraft.server.OItem;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OEntityZombie extends OEntityMob {

    public OEntityZombie(OWorld var1) {
        super(var1);
        this.ae = "/mob/zombie.png";
        this.bb = 0.23F;
        this.c = 4;
        this.al().b(true);
        this.aL.a(0, new OEntityAISwimming(this));
        this.aL.a(1, new OEntityAIBreakDoor(this));
        this.aL.a(2, new OEntityAIAttackOnCollide(this, OEntityPlayer.class, this.bb, false));
        this.aL.a(3, new OEntityAIAttackOnCollide(this, OEntityVillager.class, this.bb, true));
        this.aL.a(4, new OEntityAIMoveTwardsRestriction(this, this.bb));
        this.aL.a(5, new OEntityAIMoveThroughVillage(this, this.bb, false));
        this.aL.a(6, new OEntityAIWander(this, this.bb));
        this.aL.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.aL.a(7, new OEntityAILookIdle(this));
        this.aM.a(1, new OEntityAIHurtByTarget(this, false));
        this.aM.a(2, new OEntityAINearestAttackableTarget(this, OEntityPlayer.class, 16.0F, 0, true));
        this.aM.a(2, new OEntityAINearestAttackableTarget(this, OEntityVillager.class, 16.0F, 0, false));
    }

    public int d() {
        return 20;
    }

    public int T() {
        return 2;
    }

    protected boolean c_() {
        return true;
    }

    public void e() {
        if (this.bi.e() && !this.bi.F) {
            float var1 = this.b(1.0F);
            if (var1 > 0.5F && this.bi.l(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) && this.bS.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
                this.i(8);
            }
        }

        super.e();
    }

    protected String i() {
        return "mob.zombie";
    }

    protected String j() {
        return "mob.zombiehurt";
    }

    protected String k() {
        return "mob.zombiedeath";
    }

    protected int f() {
        return OItem.bl.bP;
    }

    public OEnumCreatureAttribute v() {
        return OEnumCreatureAttribute.b;
    }

    protected void b(int var1) {
        switch (this.bS.nextInt(4)) {
        case 0:
            this.b(OItem.p.bP, 1);
            break;
        case 1:
            this.b(OItem.ac.bP, 1);
            break;
        case 2:
            this.b(OItem.n.bP, 1);
            break;
        case 3:
            this.b(OItem.e.bP, 1);
        }

    }
}
