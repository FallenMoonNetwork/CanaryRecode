package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.hook.player.RecipeMatchHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.OIRecipe;
import net.minecraft.server.OInventoryCrafting;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ORecipeSorter;
import net.minecraft.server.ORecipesArmor;
import net.minecraft.server.ORecipesCrafting;
import net.minecraft.server.ORecipesDyes;
import net.minecraft.server.ORecipesFood;
import net.minecraft.server.ORecipesIngots;
import net.minecraft.server.ORecipesTools;
import net.minecraft.server.ORecipesWeapons;
import net.minecraft.server.OShapedRecipes;
import net.minecraft.server.OShapelessRecipes;

public class OCraftingManager {

    private static final OCraftingManager a = new OCraftingManager();
    private List b = new ArrayList();

    public static final OCraftingManager a() {
        return a;
    }

    private OCraftingManager() {
        super();
        (new ORecipesTools()).a(this);
        (new ORecipesWeapons()).a(this);
        (new ORecipesIngots()).a(this);
        (new ORecipesFood()).a(this);
        (new ORecipesCrafting()).a(this);
        (new ORecipesArmor()).a(this);
        (new ORecipesDyes()).a(this);
        this.a(new OItemStack(OItem.aJ, 3), new Object[] { "###", Character.valueOf('#'), OItem.aI });
        this.a(new OItemStack(OItem.aK, 1), new Object[] { "#", "#", "#", Character.valueOf('#'), OItem.aJ });
        this.a(new OItemStack(OBlock.aZ, 2), new Object[] { "###", "###", Character.valueOf('#'), OItem.C });
        this.a(new OItemStack(OBlock.bB, 6), new Object[] { "###", "###", Character.valueOf('#'), OBlock.bA });
        this.a(new OItemStack(OBlock.bv, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), OItem.C, Character.valueOf('W'), OBlock.x });
        this.a(new OItemStack(OBlock.aY, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), OBlock.x, Character.valueOf('X'), OItem.m });
        this.a(new OItemStack(OBlock.R, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), OBlock.x, Character.valueOf('X'), OItem.aB });
        this.a(new OItemStack(OBlock.an, 1), new Object[] { "###", "XXX", "###", Character.valueOf('#'), OBlock.x, Character.valueOf('X'), OItem.aK });
        this.a(new OItemStack(OBlock.aU, 1), new Object[] { "##", "##", Character.valueOf('#'), OItem.aC });
        this.a(new OItemStack(OBlock.aW, 1), new Object[] { "##", "##", Character.valueOf('#'), OItem.aH });
        this.a(new OItemStack(OBlock.al, 1), new Object[] { "##", "##", Character.valueOf('#'), OItem.aG });
        this.a(new OItemStack(OBlock.bd, 1), new Object[] { "##", "##", Character.valueOf('#'), OItem.aS });
        this.a(new OItemStack(OBlock.ab, 1), new Object[] { "##", "##", Character.valueOf('#'), OItem.J });
        this.a(new OItemStack(OBlock.am, 1), new Object[] { "X#X", "#X#", "X#X", Character.valueOf('X'), OItem.L, Character.valueOf('#'), OBlock.E });
        this.a(new OItemStack(OBlock.ak, 6, 3), new Object[] { "###", Character.valueOf('#'), OBlock.w });
        this.a(new OItemStack(OBlock.ak, 6, 0), new Object[] { "###", Character.valueOf('#'), OBlock.t });
        this.a(new OItemStack(OBlock.ak, 6, 1), new Object[] { "###", Character.valueOf('#'), OBlock.Q });
        this.a(new OItemStack(OBlock.ak, 6, 2), new Object[] { "###", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OBlock.ak, 6, 4), new Object[] { "###", Character.valueOf('#'), OBlock.al });
        this.a(new OItemStack(OBlock.ak, 6, 5), new Object[] { "###", Character.valueOf('#'), OBlock.bm });
        this.a(new OItemStack(OBlock.aF, 3), new Object[] { "# #", "###", "# #", Character.valueOf('#'), OItem.C });
        this.a(new OItemStack(OItem.au, 1), new Object[] { "##", "##", "##", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OBlock.bk, 2), new Object[] { "###", "###", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OItem.aA, 1), new Object[] { "##", "##", "##", Character.valueOf('#'), OItem.n });
        this.a(new OItemStack(OItem.at, 1), new Object[] { "###", "###", " X ", Character.valueOf('#'), OBlock.x, Character.valueOf('X'), OItem.C });
        this.a(new OItemStack(OItem.aY, 1), new Object[] { "AAA", "BEB", "CCC", Character.valueOf('A'), OItem.aF, Character.valueOf('B'), OItem.aX, Character.valueOf('C'), OItem.S, Character.valueOf('E'), OItem.aO });
        this.a(new OItemStack(OItem.aX, 1), new Object[] { "#", Character.valueOf('#'), OItem.aI });
        this.a(new OItemStack(OBlock.x, 4, 0), new Object[] { "#", Character.valueOf('#'), new OItemStack(OBlock.J, 1, 0) });
        this.a(new OItemStack(OBlock.x, 4, 1), new Object[] { "#", Character.valueOf('#'), new OItemStack(OBlock.J, 1, 1) });
        this.a(new OItemStack(OBlock.x, 4, 2), new Object[] { "#", Character.valueOf('#'), new OItemStack(OBlock.J, 1, 2) });
        this.a(new OItemStack(OBlock.x, 4, 3), new Object[] { "#", Character.valueOf('#'), new OItemStack(OBlock.J, 1, 3) });
        this.a(new OItemStack(OItem.C, 4), new Object[] { "#", "#", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OBlock.aq, 4), new Object[] { "X", "#", Character.valueOf('X'), OItem.l, Character.valueOf('#'), OItem.C });
        this.a(new OItemStack(OBlock.aq, 4), new Object[] { "X", "#", Character.valueOf('X'), new OItemStack(OItem.l, 1, 1), Character.valueOf('#'), OItem.C });
        this.a(new OItemStack(OItem.D, 4), new Object[] { "# #", " # ", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OItem.bs, 3), new Object[] { "# #", " # ", Character.valueOf('#'), OBlock.M });
        this.a(new OItemStack(OBlock.aG, 16), new Object[] { "X X", "X#X", "X X", Character.valueOf('X'), OItem.n, Character.valueOf('#'), OItem.C });
        this.a(new OItemStack(OBlock.T, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), OItem.o, Character.valueOf('R'), OItem.aB, Character.valueOf('#'), OItem.C });
        this.a(new OItemStack(OBlock.U, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), OItem.n, Character.valueOf('R'), OItem.aB, Character.valueOf('#'), OBlock.aK });
        this.a(new OItemStack(OItem.ay, 1), new Object[] { "# #", "###", Character.valueOf('#'), OItem.n });
        this.a(new OItemStack(OItem.by, 1), new Object[] { "# #", "# #", "###", Character.valueOf('#'), OItem.n });
        this.a(new OItemStack(OItem.bx, 1), new Object[] { " B ", "###", Character.valueOf('#'), OBlock.w, Character.valueOf('B'), OItem.bn });
        this.a(new OItemStack(OBlock.bf, 1), new Object[] { "A", "B", Character.valueOf('A'), OBlock.ba, Character.valueOf('B'), OBlock.aq });
        this.a(new OItemStack(OItem.aM, 1), new Object[] { "A", "B", Character.valueOf('A'), OBlock.au, Character.valueOf('B'), OItem.ay });
        this.a(new OItemStack(OItem.aN, 1), new Object[] { "A", "B", Character.valueOf('A'), OBlock.aB, Character.valueOf('B'), OItem.ay });
        this.a(new OItemStack(OItem.aD, 1), new Object[] { "# #", "###", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OItem.av, 1), new Object[] { "# #", " # ", Character.valueOf('#'), OItem.n });
        this.a(new OItemStack(OItem.h, 1), new Object[] { "A ", " B", Character.valueOf('A'), OItem.n, Character.valueOf('B'), OItem.ao });
        this.a(new OItemStack(OItem.T, 1), new Object[] { "###", Character.valueOf('#'), OItem.S });
        this.a(new OItemStack(OBlock.at, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OItem.aQ, 1), new Object[] { "  #", " #X", "# X", Character.valueOf('#'), OItem.C, Character.valueOf('X'), OItem.J });
        this.a(new OItemStack(OBlock.aH, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), OBlock.w });
        this.a(new OItemStack(OBlock.bw, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), OBlock.al });
        this.a(new OItemStack(OBlock.bx, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), OBlock.bm });
        this.a(new OItemStack(OBlock.bC, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), OBlock.bA });
        this.a(new OItemStack(OItem.ar, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), OItem.C, Character.valueOf('X'), OBlock.ab });
        this.a(new OItemStack(OItem.as, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), OItem.bp, Character.valueOf('X'), OItem.i });
        this.a(new OItemStack(OBlock.aJ, 1), new Object[] { "X", "#", Character.valueOf('#'), OBlock.w, Character.valueOf('X'), OItem.C });
        this.a(new OItemStack(OBlock.aQ, 1), new Object[] { "X", "#", Character.valueOf('#'), OItem.C, Character.valueOf('X'), OItem.aB });
        this.a(new OItemStack(OItem.ba, 1), new Object[] { "#X#", "III", Character.valueOf('#'), OBlock.aQ, Character.valueOf('X'), OItem.aB, Character.valueOf('I'), OBlock.t });
        this.a(new OItemStack(OItem.aR, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), OItem.o, Character.valueOf('X'), OItem.aB });
        this.a(new OItemStack(OItem.aP, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), OItem.n, Character.valueOf('X'), OItem.aB });
        this.a(new OItemStack(OItem.bc, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), OItem.aJ, Character.valueOf('X'), OItem.aP });
        this.a(new OItemStack(OBlock.aR, 1), new Object[] { "#", "#", Character.valueOf('#'), OBlock.t });
        this.a(new OItemStack(OBlock.aK, 1), new Object[] { "##", Character.valueOf('#'), OBlock.t });
        this.a(new OItemStack(OBlock.aM, 1), new Object[] { "##", Character.valueOf('#'), OBlock.x });
        this.a(new OItemStack(OBlock.P, 1), new Object[] { "###", "#X#", "#R#", Character.valueOf('#'), OBlock.w, Character.valueOf('X'), OItem.j, Character.valueOf('R'), OItem.aB });
        this.a(new OItemStack(OBlock.Z, 1), new Object[] { "TTT", "#X#", "#R#", Character.valueOf('#'), OBlock.w, Character.valueOf('X'), OItem.n, Character.valueOf('R'), OItem.aB, Character.valueOf('T'), OBlock.x });
        this.a(new OItemStack(OBlock.V, 1), new Object[] { "S", "P", Character.valueOf('S'), OItem.aL, Character.valueOf('P'), OBlock.Z });
        this.a(new OItemStack(OItem.aZ, 1), new Object[] { "###", "XXX", Character.valueOf('#'), OBlock.ab, Character.valueOf('X'), OBlock.x });
        this.a(new OItemStack(OBlock.bE, 1), new Object[] { " B ", "D#D", "###", Character.valueOf('#'), OBlock.ap, Character.valueOf('B'), OItem.aK, Character.valueOf('D'), OItem.m });
        this.b(new OItemStack(OItem.bz, 1), new Object[] { OItem.bm, OItem.bv });
        this.b(new OItemStack(OItem.bD, 3), new Object[] { OItem.L, OItem.bv, OItem.l });
        this.b(new OItemStack(OItem.bD, 3), new Object[] { OItem.L, OItem.bv, new OItemStack(OItem.l, 1, 1) });
        Collections.sort(this.b, new ORecipeSorter(this));
        System.out.println(this.b.size() + " recipes");
    }

    void a(OItemStack var1, Object... var2) {
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        if (var2[var4] instanceof String[]) {
            String[] var7 = ((String[]) var2[var4++]);

            for (int var8 = 0; var8 < var7.length; ++var8) {
                String var9 = var7[var8];
                ++var6;
                var5 = var9.length();
                var3 = var3 + var9;
            }
        } else {
            while (var2[var4] instanceof String) {
                String var11 = (String) var2[var4++];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        }

        HashMap var12;
        for (var12 = new HashMap(); var4 < var2.length; var4 += 2) {
            Character var13 = (Character) var2[var4];
            OItemStack var14 = null;
            if (var2[var4 + 1] instanceof OItem) {
                var14 = new OItemStack((OItem) var2[var4 + 1]);
            } else if (var2[var4 + 1] instanceof OBlock) {
                var14 = new OItemStack((OBlock) var2[var4 + 1], 1, -1);
            } else if (var2[var4 + 1] instanceof OItemStack) {
                var14 = (OItemStack) var2[var4 + 1];
            }

            var12.put(var13, var14);
        }

        OItemStack[] var15 = new OItemStack[var5 * var6];

        for (int var16 = 0; var16 < var5 * var6; ++var16) {
            char var10 = var3.charAt(var16);
            if (var12.containsKey(Character.valueOf(var10))) {
                var15[var16] = ((OItemStack) var12.get(Character.valueOf(var10))).j();
            } else {
                var15[var16] = null;
            }
        }

        this.b.add(new OShapedRecipes(var5, var6, var15, var1));
    }

    void b(OItemStack var1, Object... var2) {
        ArrayList var3 = new ArrayList();
        Object[] var4 = var2;
        int var5 = var2.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            Object var7 = var4[var6];
            if (var7 instanceof OItemStack) {
                var3.add(((OItemStack) var7).j());
            } else if (var7 instanceof OItem) {
                var3.add(new OItemStack((OItem) var7));
            } else {
                if (!(var7 instanceof OBlock)) {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                var3.add(new OItemStack((OBlock) var7));
            }
        }

        this.b.add(new OShapelessRecipes(var1, var3));
    }

    public OItemStack a(OInventoryCrafting var1) {
        int var2 = 0;
        OItemStack var3 = null;
        OItemStack var4 = null;

        int var5;
        for (var5 = 0; var5 < var1.getInventorySize(); ++var5) {
            OItemStack var6 = var1.g_(var5);
            if (var6 != null) {
                if (var2 == 0) {
                    var3 = var6;
                }

                if (var2 == 1) {
                    var4 = var6;
                }

                ++var2;
            }
        }

        if (var2 == 2 && var3.c == var4.c && var3.a == 1 && var4.a == 1 && OItem.d[var3.c].g()) {
            OItem var11 = OItem.d[var3.c];
            int var10 = var11.f() - var3.g();
            int var7 = var11.f() - var4.g();
            int var8 = var10 + var7 + var11.f() * 10 / 100;
            int var9 = var11.f() - var8;
            if (var9 < 0) {
                var9 = 0;
            }
            // CanaryMod start - onRecipeMatch
            OInventoryPlayer playerInventory = null;
            if (var1.c instanceof OContainerPlayer) {
                playerInventory = ((OContainerPlayer)var1.c).playerInventory;
            } else if (var1.c instanceof OContainerWorkbench) {
                playerInventory = ((OContainerWorkbench)var1.c).playerInventory;
            }
            if (playerInventory != null) {
                RecipeMatchHook hook = new RecipeMatchHook(((OEntityPlayerMP)playerInventory.d).getPlayer(), var1.c.getInventory(), new CanaryItem(new OItemStack(var3.c, 1, var9)));
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    CanaryItem result = (CanaryItem)hook.getRecipeResult();
                    if (result != null) {
                        return result.getHandle();
                    } else {
                        return null;
                    }
                }
                else {
                    return null;
                }
            } else {
                return new OItemStack(var3.c, 1, var9);
            }
            // CanaryMod end - onRecipeMatch
        } else {
            for (var5 = 0; var5 < this.b.size(); ++var5) {
                OIRecipe var12 = (OIRecipe) this.b.get(var5);
                if (var12.a(var1)) {
                    // CanaryMod start - onRecipeMatch
                    OItemStack recipeItemStack = var12.b(var1);
                    OInventoryPlayer playerInventory = null;
                    if (var1.c instanceof OContainerPlayer) {
                        playerInventory = ((OContainerPlayer)var1.c).playerInventory;
                    } else if (var1.c instanceof OContainerWorkbench) {
                        playerInventory = ((OContainerWorkbench)var1.c).playerInventory;
                    }
                    if (playerInventory != null) {
                        RecipeMatchHook hook = new RecipeMatchHook(((OEntityPlayerMP)playerInventory.d).getPlayer(), var1.c.getInventory(), new CanaryItem(recipeItemStack));
                        Canary.hooks().callHook(hook);
                        if (!hook.isCanceled()) {
                            CanaryItem result = (CanaryItem)hook.getRecipeResult();
                            if (result != null) {
                                return result.getHandle();
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    } else {
                        return recipeItemStack;
                    }
                    // CanaryMod end - onRecipeMatch
                }
            }

            return null;
        }
    }

    public List b() {
        return this.b;
    }

}
