package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryEntityItem;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.EntitySpawnHook;
import net.minecraft.server.OAchievementList;
import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OStatCollector;
import net.minecraft.server.OWorld;

public class OEntityItem extends OEntity {

    public OItemStack a;
    public int b = 0;
    public int c; //item age
    private int e = 5;
    public float d = (float) (Math.random() * 3.141592653589793D * 2.0D);
    
    //CanaryMod Item handler
    private CanaryEntityItem item;

    public OEntityItem(OWorld var1, double var2, double var4, double var6, OItemStack var8) {
        super(var1);
        this.b(0.25F, 0.25F);
        this.bF = this.bH / 2.0F;
        this.c(var2, var4, var6);
        this.a = var8;
        this.bs = (float) (Math.random() * 360.0D);
        this.bp = ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.bq = 0.20000000298023224D;
        this.br = ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }
    
    // CanaryMod start
    /**
     * Return the CanaryMod handler for this item
     * @return item
     */
    public CanaryEntityItem getItem() {
        return item;
    }
    
    /**
     * Get this itemStack
     * @return itemstack
     */
    public OItemStack getItemStack() {
        return a;
    }
    //CanaryMod end

    @Override
    protected boolean g_() {
        return false;
    }

    public OEntityItem(OWorld var1) {
        super(var1);
        this.b(0.25F, 0.25F);
        this.bF = this.bH / 2.0F;
    }

    @Override
    protected void b() {
    }

    @Override
    public void F_() {
        super.F_();
        if (this.c > 0) {
            --this.c;
        }

        this.bj = this.bm;
        this.bk = this.bn;
        this.bl = this.bo;
        this.bq -= 0.03999999910593033D;
        if (this.bi.d(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) == OMaterial.h) {
            this.bq = 0.20000000298023224D;
            this.bp = ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F);
            this.br = ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F);
            this.bi.a(this, "random.fizz", 0.4F, 2.0F + this.bS.nextFloat() * 0.4F);
        }

        this.g(this.bm, (this.bw.b + this.bw.e) / 2.0D, this.bo);
        this.a(this.bp, this.bq, this.br);
        float var1 = 0.98F;
        if (this.bx) {
            var1 = 0.58800006F;
            int var2 = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));
            if (var2 > 0) {
                var1 = OBlock.m[var2].ce * 0.98F;
            }
        }

        this.bp *= var1;
        this.bq *= 0.9800000190734863D;
        this.br *= var1;
        if (this.bx) {
            this.bq *= -0.5D;
        }

        ++this.b;
        if (this.b >= 6000) {
            EntitySpawnHook hook = new EntitySpawnHook(this.getCanaryEntity(), false);
            Canary.hooks().callHook(hook); //CanaryMod - EntityDespawn
            if(!hook.isCanceled()){
                this.X();
            }
            else{
                this.b = 0;
            }
        }

    }

    public void k() {
        this.b = 4800;
    }

    @Override
    public boolean h_() {
        return this.bi.a(this.bw, OMaterial.g, this);
    }

    @Override
    protected void a(int var1) {
        this.a(ODamageSource.b, var1);
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        this.aW();
        this.e -= var2;
        if (this.e <= 0) {
            this.X();
        }

        return false;
    }

    @Override
    public void b(ONBTTagCompound var1) {
        var1.a("Health", (short) ((byte) this.e));
        var1.a("Age", (short) this.b);
        var1.a("Item", this.a.b(new ONBTTagCompound()));
    }

    @Override
    public void a(ONBTTagCompound var1) {
        this.e = var1.e("Health") & 255;
        this.b = var1.e("Age");
        ONBTTagCompound var2 = var1.m("Item");
        this.a = OItemStack.a(var2);
        if (this.a == null) {
            this.X();
        }

    }

    @Override
    public void a_(OEntityPlayer var1) {
        if (!this.bi.F) {
            int var2 = this.a.a;
            if (this.c == 0 && var1.k.a(this.a)) {
                if (this.a.c == OBlock.J.bO) {
                    var1.a(OAchievementList.g);
                }

                if (this.a.c == OItem.aE.bP) {
                    var1.a(OAchievementList.t);
                }

                if (this.a.c == OItem.m.bP) {
                    var1.a(OAchievementList.w);
                }

                if (this.a.c == OItem.bn.bP) {
                    var1.a(OAchievementList.z);
                }

                this.bi.a(this, "random.pop", 0.2F, ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                var1.a(this, var2);
                if (this.a.a <= 0) {
                    this.X();
                }
            }

        }
    }

    @Override
    public String s() {
        return OStatCollector.a("item." + this.a.k());
    }

    @Override
    public boolean k_() {
        return false;
    }
}
