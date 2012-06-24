package net.minecraft.server;

import net.minecraft.server.OInventoryCrafting;
import net.minecraft.server.OItemStack;

public interface OIRecipe {

    boolean a(OInventoryCrafting var1);

    OItemStack b(OInventoryCrafting var1);

    int a();

    OItemStack b();
}
