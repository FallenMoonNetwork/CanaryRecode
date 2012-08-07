package net.minecraft.server;


import net.minecraft.server.OAchievementList;
import net.minecraft.server.OContainerBrewingStand;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;


class OSlotBrewingStandPotion extends OSlot {

    private OEntityPlayer f;
    // $FF: synthetic field
    final OContainerBrewingStand a;

    public OSlotBrewingStandPotion(OContainerBrewingStand var1, OEntityPlayer var2, OIInventory var3, int var4, int var5, int var6) {
        super(var3, var4, var5, var6);
        this.a = var1;
        this.f = var2;
    }

    @Override
    public boolean a(OItemStack var1) {
        return var1 != null && (var1.c == OItem.br.bP || var1.c == OItem.bs.bP);
    }

    @Override
    public int a() {
        return 1;
    }

    @Override
    public void c(OItemStack var1) {
        if (var1.c == OItem.br.bP && var1.h() > 0) {
            this.f.a(OAchievementList.A, 1);
        }

        super.c(var1);
    }
}
