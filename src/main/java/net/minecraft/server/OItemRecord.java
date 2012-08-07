package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockJukeBox;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemRecord extends OItem {

    public final String a;

    protected OItemRecord(int var1, String var2) {
        super(var1);
        this.a = var2;
        this.bQ = 1;
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (var3.a(var4, var5, var6) == OBlock.aY.bO && var3.c(var4, var5, var6) == 0) {
            if (var3.F) {
                return true;
            } else {
                ((OBlockJukeBox) OBlock.aY).f(var3, var4, var5, var6, this.bP);
                var3.a((OEntityPlayer) null, 1005, var4, var5, var6, this.bP);
                --var1.a;
                return true;
            }
        } else {
            return false;
        }
    }
}
