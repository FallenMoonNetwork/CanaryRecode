package net.minecraft.server;

import net.canarymod.api.entity.CanaryMushroomCow;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityCow;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OEntityMooshroom extends OEntityCow {

    private CanaryMushroomCow canaryMushroomCow;
    public OEntityMooshroom(OWorld var1) {
        super(var1);
        this.ae = "/mob/redcow.png";
        this.b(0.9F, 1.3F);
        canaryMushroomCow = new CanaryMushroomCow(this);
    }

    /**
     * CanaryMod Get the MushroomCow handler
     * @return the canaryMushroomCow
     */
    public CanaryMushroomCow getCanaryMushroomCow() {
        return canaryMushroomCow;
    }

    @Override
    public boolean b(OEntityPlayer var1) {
        OItemStack var2 = var1.k.getItemInHand();
        if (var2 != null && var2.c == OItem.D.bP && this.K() >= 0) {
            if (var2.a == 1) {
                var1.k.setItemStackToSlot(var1.k.c, new OItemStack(OItem.E));
                return true;
            }

            if (var1.k.addItemToBackPack(new OItemStack(OItem.E)) && !var1.L.d) {
                var1.k.decreaseItemStackSize(var1.k.c, 1);
                return true;
            }
        }

        if (var2 != null && var2.c == OItem.bd.bP && this.K() >= 0) {
            this.X();
            this.bi.a("largeexplode", this.bm, this.bn + (this.bH / 2.0F), this.bo, 0.0D, 0.0D, 0.0D);
            if (!this.bi.F) {
                OEntityCow var3 = new OEntityCow(this.bi);
                var3.c(this.bm, this.bn, this.bo, this.bs, this.bt);
                var3.h(this.aD());
                var3.V = this.V;
                this.bi.b(var3);

                for (int var4 = 0; var4 < 5; ++var4) {
                    this.bi.b((new OEntityItem(this.bi, this.bm, this.bn + this.bH, this.bo, new OItemStack(OBlock.ag))));
                }
            }

            return true;
        } else {
            return super.b(var1);
        }
    }

    @Override
    public OEntityAnimal a(OEntityAnimal var1) {
        return new OEntityMooshroom(this.bi);
    }
}
