package net.minecraft.server;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class CraftingManager {

    private static final CraftingManager a = new CraftingManager();
    private List b = new ArrayList();

    public static final CraftingManager a() {
        return a;
    }

    private CraftingManager() {
        (new RecipesTools()).a(this);
        (new RecipesWeapons()).a(this);
        (new RecipesIngots()).a(this);
        (new RecipesFood()).a(this);
        (new RecipesCrafting()).a(this);
        (new RecipesArmor()).a(this);
        (new RecipesDyes()).a(this);
        this.b.add(new RecipesArmorDyes());
        this.b.add(new RecipesMapCloning());
        this.b.add(new RecipesMapExtending());
        this.b.add(new RecipeFireworks());
        this.a(new ItemStack(Item.aM, 3), new Object[]{ "###", Character.valueOf('#'), Item.aL });
        this.b(new ItemStack(Item.aN, 1), new Object[]{ Item.aM, Item.aM, Item.aM, Item.aH });
        this.b(new ItemStack(Item.bH, 1), new Object[]{ Item.aN, new ItemStack(Item.aY, 1, 0), Item.N });
        this.a(new ItemStack(Block.be, 2), new Object[]{ "###", "###", Character.valueOf('#'), Item.F });
        this.a(new ItemStack(Block.cg, 6, 0), new Object[]{ "###", "###", Character.valueOf('#'), Block.B });
        this.a(new ItemStack(Block.cg, 6, 1), new Object[]{ "###", "###", Character.valueOf('#'), Block.at });
        this.a(new ItemStack(Block.bG, 6), new Object[]{ "###", "###", Character.valueOf('#'), Block.bF });
        this.a(new ItemStack(Block.bA, 1), new Object[]{ "#W#", "#W#", Character.valueOf('#'), Item.F, Character.valueOf('W'), Block.C });
        this.a(new ItemStack(Block.bd, 1), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Block.C, Character.valueOf('X'), Item.p });
        this.a(new ItemStack(Item.ch, 2), new Object[]{ "~~ ", "~O ", "  ~", Character.valueOf('~'), Item.M, Character.valueOf('O'), Item.aO });
        this.a(new ItemStack(Block.cB, 1), new Object[]{ "###", "###", "###", Character.valueOf('#'), Item.V });
        this.a(new ItemStack(Block.W, 1), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Block.C, Character.valueOf('X'), Item.aE });
        this.a(new ItemStack(Block.as, 1), new Object[]{ "###", "XXX", "###", Character.valueOf('#'), Block.C, Character.valueOf('X'), Item.aN });
        this.a(new ItemStack(Block.aZ, 1), new Object[]{ "##", "##", Character.valueOf('#'), Item.aF });
        this.a(new ItemStack(Block.aX, 6), new Object[]{ "###", Character.valueOf('#'), Block.aZ });
        this.a(new ItemStack(Block.bb, 1), new Object[]{ "##", "##", Character.valueOf('#'), Item.aK });
        this.a(new ItemStack(Block.aq, 1), new Object[]{ "##", "##", Character.valueOf('#'), Item.aJ });
        this.a(new ItemStack(Block.bi, 1), new Object[]{ "##", "##", Character.valueOf('#'), Item.aV });
        this.a(new ItemStack(Block.cw, 1), new Object[]{ "##", "##", Character.valueOf('#'), Item.cb });
        this.a(new ItemStack(Block.ag, 1), new Object[]{ "##", "##", Character.valueOf('#'), Item.M });
        this.a(new ItemStack(Block.ar, 1), new Object[]{ "X#X", "#X#", "X#X", Character.valueOf('X'), Item.O, Character.valueOf('#'), Block.J });
        this.a(new ItemStack(Block.ap, 6, 3), new Object[]{ "###", Character.valueOf('#'), Block.B });
        this.a(new ItemStack(Block.ap, 6, 0), new Object[]{ "###", Character.valueOf('#'), Block.y });
        this.a(new ItemStack(Block.ap, 6, 1), new Object[]{ "###", Character.valueOf('#'), Block.V });
        this.a(new ItemStack(Block.ap, 6, 4), new Object[]{ "###", Character.valueOf('#'), Block.aq });
        this.a(new ItemStack(Block.ap, 6, 5), new Object[]{ "###", Character.valueOf('#'), Block.br });
        this.a(new ItemStack(Block.ap, 6, 6), new Object[]{ "###", Character.valueOf('#'), Block.bF });
        this.a(new ItemStack(Block.ap, 6, 7), new Object[]{ "###", Character.valueOf('#'), Block.cw });
        this.a(new ItemStack(Block.bT, 6, 0), new Object[]{ "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 0) });
        this.a(new ItemStack(Block.bT, 6, 2), new Object[]{ "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 2) });
        this.a(new ItemStack(Block.bT, 6, 1), new Object[]{ "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 1) });
        this.a(new ItemStack(Block.bT, 6, 3), new Object[]{ "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 3) });
        this.a(new ItemStack(Block.aK, 3), new Object[]{ "# #", "###", "# #", Character.valueOf('#'), Item.F });
        this.a(new ItemStack(Item.ax, 1), new Object[]{ "##", "##", "##", Character.valueOf('#'), Block.C });
        this.a(new ItemStack(Block.bp, 2), new Object[]{ "###", "###", Character.valueOf('#'), Block.C });
        this.a(new ItemStack(Item.aD, 1), new Object[]{ "##", "##", "##", Character.valueOf('#'), Item.q });
        this.a(new ItemStack(Item.aw, 3), new Object[]{ "###", "###", " X ", Character.valueOf('#'), Block.C, Character.valueOf('X'), Item.F });
        this.a(new ItemStack(Item.bb, 1), new Object[]{ "AAA", "BEB", "CCC", Character.valueOf('A'), Item.aI, Character.valueOf('B'), Item.ba, Character.valueOf('C'), Item.V, Character.valueOf('E'), Item.aR });
        this.a(new ItemStack(Item.ba, 1), new Object[]{ "#", Character.valueOf('#'), Item.aL });
        this.a(new ItemStack(Block.C, 4, 0), new Object[]{ "#", Character.valueOf('#'), new ItemStack(Block.O, 1, 0) });
        this.a(new ItemStack(Block.C, 4, 1), new Object[]{ "#", Character.valueOf('#'), new ItemStack(Block.O, 1, 1) });
        this.a(new ItemStack(Block.C, 4, 2), new Object[]{ "#", Character.valueOf('#'), new ItemStack(Block.O, 1, 2) });
        this.a(new ItemStack(Block.C, 4, 3), new Object[]{ "#", Character.valueOf('#'), new ItemStack(Block.O, 1, 3) });
        this.a(new ItemStack(Item.F, 4), new Object[]{ "#", "#", Character.valueOf('#'), Block.C });
        this.a(new ItemStack(Block.av, 4), new Object[]{ "X", "#", Character.valueOf('X'), Item.o, Character.valueOf('#'), Item.F });
        this.a(new ItemStack(Block.av, 4), new Object[]{ "X", "#", Character.valueOf('X'), new ItemStack(Item.o, 1, 1), Character.valueOf('#'), Item.F });
        this.a(new ItemStack(Item.G, 4), new Object[]{ "# #", " # ", Character.valueOf('#'), Block.C });
        this.a(new ItemStack(Item.bv, 3), new Object[]{ "# #", " # ", Character.valueOf('#'), Block.R });
        this.a(new ItemStack(Block.aL, 16), new Object[]{ "X X", "X#X", "X X", Character.valueOf('X'), Item.q, Character.valueOf('#'), Item.F });
        this.a(new ItemStack(Block.Y, 6), new Object[]{ "X X", "X#X", "XRX", Character.valueOf('X'), Item.r, Character.valueOf('R'), Item.aE, Character.valueOf('#'), Item.F });
        this.a(new ItemStack(Block.cy, 6), new Object[]{ "XSX", "X#X", "XSX", Character.valueOf('X'), Item.q, Character.valueOf('#'), Block.aV, Character.valueOf('S'), Item.F });
        this.a(new ItemStack(Block.Z, 6), new Object[]{ "X X", "X#X", "XRX", Character.valueOf('X'), Item.q, Character.valueOf('R'), Item.aE, Character.valueOf('#'), Block.aP });
        this.a(new ItemStack(Item.aB, 1), new Object[]{ "# #", "###", Character.valueOf('#'), Item.q });
        this.a(new ItemStack(Item.bB, 1), new Object[]{ "# #", "# #", "###", Character.valueOf('#'), Item.q });
        this.a(new ItemStack(Item.bA, 1), new Object[]{ " B ", "###", Character.valueOf('#'), Block.B, Character.valueOf('B'), Item.bq });
        this.a(new ItemStack(Block.bk, 1), new Object[]{ "A", "B", Character.valueOf('A'), Block.bf, Character.valueOf('B'), Block.av });
        this.a(new ItemStack(Item.aP, 1), new Object[]{ "A", "B", Character.valueOf('A'), Block.az, Character.valueOf('B'), Item.aB });
        this.a(new ItemStack(Item.aQ, 1), new Object[]{ "A", "B", Character.valueOf('A'), Block.aG, Character.valueOf('B'), Item.aB });
        this.a(new ItemStack(Item.cc, 1), new Object[]{ "A", "B", Character.valueOf('A'), Block.ar, Character.valueOf('B'), Item.aB });
        this.a(new ItemStack(Item.cd, 1), new Object[]{ "A", "B", Character.valueOf('A'), Block.cv, Character.valueOf('B'), Item.aB });
        this.a(new ItemStack(Item.aG, 1), new Object[]{ "# #", "###", Character.valueOf('#'), Block.C });
        this.a(new ItemStack(Item.ay, 1), new Object[]{ "# #", " # ", Character.valueOf('#'), Item.q });
        this.a(new ItemStack(Item.bL, 1), new Object[]{ "# #", " # ", Character.valueOf('#'), Item.aJ });
        this.a(new ItemStack(Item.k, 1), new Object[]{ "A ", " B", Character.valueOf('A'), Item.q, Character.valueOf('B'), Item.ar });
        this.a(new ItemStack(Item.W, 1), new Object[]{ "###", Character.valueOf('#'), Item.V });
        this.a(new ItemStack(Block.ay, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 0) });
        this.a(new ItemStack(Block.cc, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 2) });
        this.a(new ItemStack(Block.cb, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 1) });
        this.a(new ItemStack(Block.cd, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.C, 1, 3) });
        this.a(new ItemStack(Item.aT, 1), new Object[]{ "  #", " #X", "# X", Character.valueOf('#'), Item.F, Character.valueOf('X'), Item.M });
        this.a(new ItemStack(Item.bT, 1), new Object[]{ "# ", " X", Character.valueOf('#'), Item.aT, Character.valueOf('X'), Item.bM }).c();
        this.a(new ItemStack(Block.aM, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), Block.B });
        this.a(new ItemStack(Block.bB, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), Block.aq });
        this.a(new ItemStack(Block.bC, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), Block.br });
        this.a(new ItemStack(Block.bH, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), Block.bF });
        this.a(new ItemStack(Block.bV, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), Block.V });
        this.a(new ItemStack(Block.cx, 4), new Object[]{ "#  ", "## ", "###", Character.valueOf('#'), Block.cw });
        this.a(new ItemStack(Item.au, 1), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Item.F, Character.valueOf('X'), Block.ag });
        this.a(new ItemStack(Item.bK, 1), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Item.F, Character.valueOf('X'), Item.aH });
        this.a(new ItemStack(Item.av, 1, 0), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Item.r, Character.valueOf('X'), Item.l });
        this.a(new ItemStack(Item.av, 1, 1), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Block.am, Character.valueOf('X'), Item.l });
        this.a(new ItemStack(Item.bR, 1, 0), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Item.bs, Character.valueOf('X'), Item.bM });
        this.a(new ItemStack(Item.bD, 1), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Item.bs, Character.valueOf('X'), Item.bh });
        this.a(new ItemStack(Block.aO, 1), new Object[]{ "X", "#", Character.valueOf('#'), Block.B, Character.valueOf('X'), Item.F });
        this.a(new ItemStack(Block.bY, 2), new Object[]{ "I", "S", "#", Character.valueOf('#'), Block.C, Character.valueOf('S'), Item.F, Character.valueOf('I'), Item.q });
        this.a(new ItemStack(Block.aV, 1), new Object[]{ "X", "#", Character.valueOf('#'), Item.F, Character.valueOf('X'), Item.aE });
        this.a(new ItemStack(Item.bd, 1), new Object[]{ "#X#", "III", Character.valueOf('#'), Block.aV, Character.valueOf('X'), Item.aE, Character.valueOf('I'), Block.y });
        this.a(new ItemStack(Item.bZ, 1), new Object[]{ " # ", "#X#", "III", Character.valueOf('#'), Block.aV, Character.valueOf('X'), Item.cb, Character.valueOf('I'), Block.y });
        this.a(new ItemStack(Item.aU, 1), new Object[]{ " # ", "#X#", " # ", Character.valueOf('#'), Item.r, Character.valueOf('X'), Item.aE });
        this.a(new ItemStack(Item.aS, 1), new Object[]{ " # ", "#X#", " # ", Character.valueOf('#'), Item.q, Character.valueOf('X'), Item.aE });
        this.a(new ItemStack(Item.bQ, 1), new Object[]{ "###", "#X#", "###", Character.valueOf('#'), Item.aM, Character.valueOf('X'), Item.aS });
        this.a(new ItemStack(Block.aW, 1), new Object[]{ "#", Character.valueOf('#'), Block.y });
        this.a(new ItemStack(Block.ck, 1), new Object[]{ "#", Character.valueOf('#'), Block.C });
        this.a(new ItemStack(Block.aP, 1), new Object[]{ "##", Character.valueOf('#'), Block.y });
        this.a(new ItemStack(Block.aR, 1), new Object[]{ "##", Character.valueOf('#'), Block.C });
        this.a(new ItemStack(Block.cp, 1), new Object[]{ "##", Character.valueOf('#'), Item.q });
        this.a(new ItemStack(Block.co, 1), new Object[]{ "##", Character.valueOf('#'), Item.r });
        this.a(new ItemStack(Block.U, 1), new Object[]{ "###", "#X#", "#R#", Character.valueOf('#'), Block.B, Character.valueOf('X'), Item.m, Character.valueOf('R'), Item.aE });
        this.a(new ItemStack(Block.cz, 1), new Object[]{ "###", "# #", "#R#", Character.valueOf('#'), Block.B, Character.valueOf('R'), Item.aE });
        this.a(new ItemStack(Block.ae, 1), new Object[]{ "TTT", "#X#", "#R#", Character.valueOf('#'), Block.B, Character.valueOf('X'), Item.q, Character.valueOf('R'), Item.aE, Character.valueOf('T'), Block.C });
        this.a(new ItemStack(Block.aa, 1), new Object[]{ "S", "P", Character.valueOf('S'), Item.aO, Character.valueOf('P'), Block.ae });
        this.a(new ItemStack(Item.bc, 1), new Object[]{ "###", "XXX", Character.valueOf('#'), Block.ag, Character.valueOf('X'), Block.C });
        this.a(new ItemStack(Block.bJ, 1), new Object[]{ " B ", "D#D", "###", Character.valueOf('#'), Block.au, Character.valueOf('B'), Item.aN, Character.valueOf('D'), Item.p });
        this.a(new ItemStack(Block.cm, 1), new Object[]{ "III", " i ", "iii", Character.valueOf('I'), Block.an, Character.valueOf('i'), Item.q });
        this.b(new ItemStack(Item.bC, 1), new Object[]{ Item.bp, Item.by });
        this.b(new ItemStack(Item.bG, 3), new Object[]{ Item.O, Item.by, Item.o });
        this.b(new ItemStack(Item.bG, 3), new Object[]{ Item.O, Item.by, new ItemStack(Item.o, 1, 1) });
        this.a(new ItemStack(Block.cs), new Object[]{ "GGG", "QQQ", "WWW", Character.valueOf('G'), Block.R, Character.valueOf('Q'), Item.cb, Character.valueOf('W'), Block.bT });
        this.a(new ItemStack(Block.cv), new Object[]{ "I I", "ICI", " I ", Character.valueOf('I'), Item.q, Character.valueOf('C'), Block.az });
        Collections.sort(this.b, new RecipeSorter(this));
        System.out.println(this.b.size() + " recipes");
    }

    public ShapedRecipes a(ItemStack itemstack, Object... aobject) { // CanaryMod: package -> public
        String s0 = "";
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;

        if (aobject[i0] instanceof String[]) {
            String[] astring = (String[]) ((String[]) aobject[i0++]);

            for (int i3 = 0; i3 < astring.length; ++i3) {
                String s1 = astring[i3];

                ++i2;
                i1 = s1.length();
                s0 = s0 + s1;
            }
        } else {
            while (aobject[i0] instanceof String) {
                String s2 = (String) aobject[i0++];

                ++i2;
                i1 = s2.length();
                s0 = s0 + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i0 < aobject.length; i0 += 2) {
            Character character = (Character) aobject[i0];
            ItemStack itemstack1 = null;

            if (aobject[i0 + 1] instanceof Item) {
                itemstack1 = new ItemStack((Item) aobject[i0 + 1]);
            } else if (aobject[i0 + 1] instanceof Block) {
                itemstack1 = new ItemStack((Block) aobject[i0 + 1], 1, 32767);
            } else if (aobject[i0 + 1] instanceof ItemStack) {
                itemstack1 = (ItemStack) aobject[i0 + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[i1 * i2];

        for (int i4 = 0; i4 < i1 * i2; ++i4) {
            char c0 = s0.charAt(i4);

            if (hashmap.containsKey(Character.valueOf(c0))) {
                aitemstack[i4] = ((ItemStack) hashmap.get(Character.valueOf(c0))).m();
            } else {
                aitemstack[i4] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(i1, i2, aitemstack, itemstack);

        this.b.add(shapedrecipes);
        return shapedrecipes;
    }

    void b(ItemStack itemstack, Object... aobject) {// CanaryMod: pass down to return ShapelessRecipe (signature change breaks things elsewhere)
        this.addShapeless(itemstack, aobject);
    }

    public ShapelessRecipes addShapeless(ItemStack itemstack, Object... aobject) { // CanaryMod: safe return without breakage
        ArrayList arraylist = new ArrayList();
        Object[] aobject1 = aobject;
        int i0 = aobject.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            Object object = aobject1[i1];

            if (object instanceof ItemStack) {
                arraylist.add(((ItemStack) object).m());
            } else if (object instanceof Item) {
                arraylist.add(new ItemStack((Item) object));
            } else {
                if (!(object instanceof Block)) {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                arraylist.add(new ItemStack((Block) object));
            }
        }

        // CanaryMod: Allow Shapeless to be returned
        ShapelessRecipes shapelessrecipes = new ShapelessRecipes(itemstack, arraylist);
        this.b.add(shapelessrecipes);
        return shapelessrecipes;
        //
    }

    public ItemStack a(InventoryCrafting inventorycrafting, World world) {
        int i0 = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;

        int i1;

        for (i1 = 0; i1 < inventorycrafting.j_(); ++i1) {
            ItemStack itemstack2 = inventorycrafting.a(i1);

            if (itemstack2 != null) {
                if (i0 == 0) {
                    itemstack = itemstack2;
                }

                if (i0 == 1) {
                    itemstack1 = itemstack2;
                }

                ++i0;
            }
        }

        if (i0 == 2 && itemstack.d == itemstack1.d && itemstack.b == 1 && itemstack1.b == 1 && Item.g[itemstack.d].p()) {
            Item item = Item.g[itemstack.d];
            int i2 = item.o() - itemstack.j();
            int i3 = item.o() - itemstack1.j();
            int i4 = i2 + i3 + item.o() * 5 / 100;
            int i5 = item.o() - i4;

            if (i5 < 0) {
                i5 = 0;
            }

            return new ItemStack(itemstack.d, 1, i5);
        } else {
            for (i1 = 0; i1 < this.b.size(); ++i1) {
                IRecipe irecipe = (IRecipe) this.b.get(i1);

                if (irecipe.a(inventorycrafting, world)) {
                    return irecipe.a(inventorycrafting);
                }
            }

            return null;
        }
    }

    public List b() {
        return this.b;
    }
}
