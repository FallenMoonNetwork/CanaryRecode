package net.minecraft.server;

import net.minecraft.server.OContainer;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;
import net.minecraft.server.OTileEntityDispenser;

public class OContainerDispenser extends OContainer {

    private OTileEntityDispenser a;

    public OContainerDispenser(OIInventory var1, OTileEntityDispenser var2) {
        super();
        this.a = var2;

        int var3;
        int var4;
        for (var3 = 0; var3 < 3; ++var3) {
            for (var4 = 0; var4 < 3; ++var4) {
                this.a(new OSlot(var2, var4 + var3 * 3, 62 + var4 * 18, 17 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 3; ++var3) {
            for (var4 = 0; var4 < 9; ++var4) {
                this.a(new OSlot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.a(new OSlot(var1, var3, 8 + var3 * 18, 142));
        }

    }

    public boolean b(OEntityPlayer var1) {
        return this.a.a(var1);
    }

    public OItemStack a(int var1) {
        OItemStack var2 = null;
        OSlot var3 = (OSlot) this.e.get(var1);
        if (var3 != null && var3.c()) {
            OItemStack var4 = var3.b();
            var2 = var4.j();
            if (var1 < 9) {
                if (!this.a(var4, 9, 45, true)) {
                    return null;
                }
            } else if (!this.a(var4, 0, 9, false)) {
                return null;
            }

            if (var4.a == 0) {
                var3.d((OItemStack) null);
            } else {
                var3.d();
            }

            if (var4.a == var2.a) {
                return null;
            }

            var3.c(var4);
        }

        return var2;
    }
}
