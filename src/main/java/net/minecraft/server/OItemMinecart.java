package net.minecraft.server;


import net.minecraft.server.OBlockRail;
import net.minecraft.server.OEntityMinecart;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;


public class OItemMinecart extends OItem {

    public int a;

    public OItemMinecart(int var1, int var2) {
        super(var1);
        this.bQ = 1;
        this.a = var2;
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        int var8 = var3.a(var4, var5, var6);

        if (OBlockRail.d(var8)) {
            if (!var3.F) {
                var3.b((new OEntityMinecart(var3, (var4 + 0.5F), (var5 + 0.5F), (var6 + 0.5F), this.a)));
            }

            --var1.a;
            return true;
        } else {
            return false;
        }
    }
}
