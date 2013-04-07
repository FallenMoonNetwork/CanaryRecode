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
        this.a(new ItemStack(Item.aL, 3), new Object[] { "###", Character.valueOf('#'), Item.aK});
        this.b(new ItemStack(Item.aM, 1), new Object[] { Item.aL, Item.aL, Item.aL, Item.aG});
        this.b(new ItemStack(Item.bG, 1), new Object[] { Item.aM, new ItemStack(Item.aX, 1, 0), Item.M});
        this.a(new ItemStack(Block.bd, 2), new Object[] { "###", "###", Character.valueOf('#'), Item.E});
        this.a(new ItemStack(Block.cf, 6, 0), new Object[] { "###", "###", Character.valueOf('#'), Block.A});
        this.a(new ItemStack(Block.cf, 6, 1), new Object[] { "###", "###", Character.valueOf('#'), Block.as});
        this.a(new ItemStack(Block.bF, 6), new Object[] { "###", "###", Character.valueOf('#'), Block.bE});
        this.a(new ItemStack(Block.bz, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Item.E, Character.valueOf('W'), Block.B});
        this.a(new ItemStack(Block.bc, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.B, Character.valueOf('X'), Item.o});
        this.a(new ItemStack(Block.V, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.B, Character.valueOf('X'), Item.aD});
        this.a(new ItemStack(Block.ar, 1), new Object[] { "###", "XXX", "###", Character.valueOf('#'), Block.B, Character.valueOf('X'), Item.aM});
        this.a(new ItemStack(Block.aY, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.aE});
        this.a(new ItemStack(Block.aW, 6), new Object[] { "###", Character.valueOf('#'), Block.aY});
        this.a(new ItemStack(Block.ba, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.aJ});
        this.a(new ItemStack(Block.ap, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.aI});
        this.a(new ItemStack(Block.bh, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.aU});
        this.a(new ItemStack(Block.cv, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.ca});
        this.a(new ItemStack(Block.af, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.L});
        this.a(new ItemStack(Block.aq, 1), new Object[] { "X#X", "#X#", "X#X", Character.valueOf('X'), Item.N, Character.valueOf('#'), Block.I});
        this.a(new ItemStack(Block.ao, 6, 3), new Object[] { "###", Character.valueOf('#'), Block.A});
        this.a(new ItemStack(Block.ao, 6, 0), new Object[] { "###", Character.valueOf('#'), Block.x});
        this.a(new ItemStack(Block.ao, 6, 1), new Object[] { "###", Character.valueOf('#'), Block.U});
        this.a(new ItemStack(Block.ao, 6, 4), new Object[] { "###", Character.valueOf('#'), Block.ap});
        this.a(new ItemStack(Block.ao, 6, 5), new Object[] { "###", Character.valueOf('#'), Block.bq});
        this.a(new ItemStack(Block.ao, 6, 6), new Object[] { "###", Character.valueOf('#'), Block.bE});
        this.a(new ItemStack(Block.ao, 6, 7), new Object[] { "###", Character.valueOf('#'), Block.cv});
        this.a(new ItemStack(Block.bS, 6, 0), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 0)});
        this.a(new ItemStack(Block.bS, 6, 2), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 2)});
        this.a(new ItemStack(Block.bS, 6, 1), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 1)});
        this.a(new ItemStack(Block.bS, 6, 3), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 3)});
        this.a(new ItemStack(Block.aJ, 3), new Object[] { "# #", "###", "# #", Character.valueOf('#'), Item.E});
        this.a(new ItemStack(Item.aw, 1), new Object[] { "##", "##", "##", Character.valueOf('#'), Block.B});
        this.a(new ItemStack(Block.bo, 2), new Object[] { "###", "###", Character.valueOf('#'), Block.B});
        this.a(new ItemStack(Item.aC, 1), new Object[] { "##", "##", "##", Character.valueOf('#'), Item.p});
        this.a(new ItemStack(Item.av, 3), new Object[] { "###", "###", " X ", Character.valueOf('#'), Block.B, Character.valueOf('X'), Item.E});
        this.a(new ItemStack(Item.ba, 1), new Object[] { "AAA", "BEB", "CCC", Character.valueOf('A'), Item.aH, Character.valueOf('B'), Item.aZ, Character.valueOf('C'), Item.U, Character.valueOf('E'), Item.aQ});
        this.a(new ItemStack(Item.aZ, 1), new Object[] { "#", Character.valueOf('#'), Item.aK});
        this.a(new ItemStack(Block.B, 4, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.N, 1, 0)});
        this.a(new ItemStack(Block.B, 4, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.N, 1, 1)});
        this.a(new ItemStack(Block.B, 4, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.N, 1, 2)});
        this.a(new ItemStack(Block.B, 4, 3), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.N, 1, 3)});
        this.a(new ItemStack(Item.E, 4), new Object[] { "#", "#", Character.valueOf('#'), Block.B});
        this.a(new ItemStack(Block.au, 4), new Object[] { "X", "#", Character.valueOf('X'), Item.n, Character.valueOf('#'), Item.E});
        this.a(new ItemStack(Block.au, 4), new Object[] { "X", "#", Character.valueOf('X'), new ItemStack(Item.n, 1, 1), Character.valueOf('#'), Item.E});
        this.a(new ItemStack(Item.F, 4), new Object[] { "# #", " # ", Character.valueOf('#'), Block.B});
        this.a(new ItemStack(Item.bu, 3), new Object[] { "# #", " # ", Character.valueOf('#'), Block.Q});
        this.a(new ItemStack(Block.aK, 16), new Object[] { "X X", "X#X", "X X", Character.valueOf('X'), Item.p, Character.valueOf('#'), Item.E});
        this.a(new ItemStack(Block.X, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Item.q, Character.valueOf('R'), Item.aD, Character.valueOf('#'), Item.E});
        this.a(new ItemStack(Block.cx, 6), new Object[] { "XSX", "X#X", "XSX", Character.valueOf('X'), Item.p, Character.valueOf('#'), Block.aU, Character.valueOf('S'), Item.E});
        this.a(new ItemStack(Block.Y, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Item.p, Character.valueOf('R'), Item.aD, Character.valueOf('#'), Block.aO});
        this.a(new ItemStack(Item.aA, 1), new Object[] { "# #", "###", Character.valueOf('#'), Item.p});
        this.a(new ItemStack(Item.bA, 1), new Object[] { "# #", "# #", "###", Character.valueOf('#'), Item.p});
        this.a(new ItemStack(Item.bz, 1), new Object[] { " B ", "###", Character.valueOf('#'), Block.A, Character.valueOf('B'), Item.bp});
        this.a(new ItemStack(Block.bj, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.be, Character.valueOf('B'), Block.au});
        this.a(new ItemStack(Item.aO, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.ay, Character.valueOf('B'), Item.aA});
        this.a(new ItemStack(Item.aP, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.aF, Character.valueOf('B'), Item.aA});
        this.a(new ItemStack(Item.cb, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.aq, Character.valueOf('B'), Item.aA});
        this.a(new ItemStack(Item.cc, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.cu, Character.valueOf('B'), Item.aA});
        this.a(new ItemStack(Item.aF, 1), new Object[] { "# #", "###", Character.valueOf('#'), Block.B});
        this.a(new ItemStack(Item.ax, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Item.p});
        this.a(new ItemStack(Item.bK, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Item.aI});
        this.a(new ItemStack(Item.j, 1), new Object[] { "A ", " B", Character.valueOf('A'), Item.p, Character.valueOf('B'), Item.aq});
        this.a(new ItemStack(Item.V, 1), new Object[] { "###", Character.valueOf('#'), Item.U});
        this.a(new ItemStack(Block.ax, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 0)});
        this.a(new ItemStack(Block.cb, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 2)});
        this.a(new ItemStack(Block.ca, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 1)});
        this.a(new ItemStack(Block.cc, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.B, 1, 3)});
        this.a(new ItemStack(Item.aS, 1), new Object[] { "  #", " #X", "# X", Character.valueOf('#'), Item.E, Character.valueOf('X'), Item.L});
        this.a(new ItemStack(Item.bS, 1), new Object[] { "# ", " X", Character.valueOf('#'), Item.aS, Character.valueOf('X'), Item.bL}).c();
        this.a(new ItemStack(Block.aL, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.A});
        this.a(new ItemStack(Block.bA, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.ap});
        this.a(new ItemStack(Block.bB, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.bq});
        this.a(new ItemStack(Block.bG, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.bE});
        this.a(new ItemStack(Block.bU, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.U});
        this.a(new ItemStack(Block.cw, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Block.cv});
        this.a(new ItemStack(Item.at, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.E, Character.valueOf('X'), Block.af});
        this.a(new ItemStack(Item.bJ, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.E, Character.valueOf('X'), Item.aG});
        this.a(new ItemStack(Item.au, 1, 0), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.br, Character.valueOf('X'), Item.k});
        this.a(new ItemStack(Item.au, 1, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.al, Character.valueOf('X'), Item.k});
        this.a(new ItemStack(Item.bQ, 1, 0), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.br, Character.valueOf('X'), Item.bL});
        this.a(new ItemStack(Block.aN, 1), new Object[] { "X", "#", Character.valueOf('#'), Block.A, Character.valueOf('X'), Item.E});
        this.a(new ItemStack(Block.bX, 2), new Object[] { "I", "S", "#", Character.valueOf('#'), Block.B, Character.valueOf('S'), Item.E, Character.valueOf('I'), Item.p});
        this.a(new ItemStack(Block.aU, 1), new Object[] { "X", "#", Character.valueOf('#'), Item.E, Character.valueOf('X'), Item.aD});
        this.a(new ItemStack(Item.bc, 1), new Object[] { "#X#", "III", Character.valueOf('#'), Block.aU, Character.valueOf('X'), Item.aD, Character.valueOf('I'), Block.x});
        this.a(new ItemStack(Item.bY, 1), new Object[] { " # ", "#X#", "III", Character.valueOf('#'), Block.aU, Character.valueOf('X'), Item.ca, Character.valueOf('I'), Block.x});
        this.a(new ItemStack(Item.aT, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Item.q, Character.valueOf('X'), Item.aD});
        this.a(new ItemStack(Item.aR, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Item.p, Character.valueOf('X'), Item.aD});
        this.a(new ItemStack(Item.bP, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.aL, Character.valueOf('X'), Item.aR});
        this.a(new ItemStack(Block.aV, 1), new Object[] { "#", Character.valueOf('#'), Block.x});
        this.a(new ItemStack(Block.cj, 1), new Object[] { "#", Character.valueOf('#'), Block.B});
        this.a(new ItemStack(Block.aO, 1), new Object[] { "##", Character.valueOf('#'), Block.x});
        this.a(new ItemStack(Block.aQ, 1), new Object[] { "##", Character.valueOf('#'), Block.B});
        this.a(new ItemStack(Block.co, 1), new Object[] { "##", Character.valueOf('#'), Item.p});
        this.a(new ItemStack(Block.cn, 1), new Object[] { "##", Character.valueOf('#'), Item.q});
        this.a(new ItemStack(Block.T, 1), new Object[] { "###", "#X#", "#R#", Character.valueOf('#'), Block.A, Character.valueOf('X'), Item.l, Character.valueOf('R'), Item.aD});
        this.a(new ItemStack(Block.cy, 1), new Object[] { "###", "# #", "#R#", Character.valueOf('#'), Block.A, Character.valueOf('R'), Item.aD});
        this.a(new ItemStack(Block.ad, 1), new Object[] { "TTT", "#X#", "#R#", Character.valueOf('#'), Block.A, Character.valueOf('X'), Item.p, Character.valueOf('R'), Item.aD, Character.valueOf('T'), Block.B});
        this.a(new ItemStack(Block.Z, 1), new Object[] { "S", "P", Character.valueOf('S'), Item.aN, Character.valueOf('P'), Block.ad});
        this.a(new ItemStack(Item.bb, 1), new Object[] { "###", "XXX", Character.valueOf('#'), Block.af, Character.valueOf('X'), Block.B});
        this.a(new ItemStack(Block.bI, 1), new Object[] { " B ", "D#D", "###", Character.valueOf('#'), Block.at, Character.valueOf('B'), Item.aM, Character.valueOf('D'), Item.o});
        this.a(new ItemStack(Block.cl, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Block.am, Character.valueOf('i'), Item.p});
        this.b(new ItemStack(Item.bB, 1), new Object[] { Item.bo, Item.bx});
        this.b(new ItemStack(Item.bF, 3), new Object[] { Item.N, Item.bx, Item.n});
        this.b(new ItemStack(Item.bF, 3), new Object[] { Item.N, Item.bx, new ItemStack(Item.n, 1, 1)});
        this.a(new ItemStack(Block.cr), new Object[] { "GGG", "QQQ", "WWW", Character.valueOf('G'), Block.Q, Character.valueOf('Q'), Item.ca, Character.valueOf('W'), Block.bS});
        this.a(new ItemStack(Block.cu), new Object[] { "I I", "ICI", " I ", Character.valueOf('I'), Item.p, Character.valueOf('C'), Block.ay});
        Collections.sort(this.b, new RecipeSorter(this));
        System.out.println(this.b.size() + " recipes");
    }

    public ShapedRecipes a(ItemStack itemstack, Object... aobject) { // CanaryMod: package => public
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

    public void b(ItemStack itemstack, Object... aobject) { // CanaryMod: package => public
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

        this.b.add(new ShapelessRecipes(itemstack, arraylist));
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

        if (i0 == 2 && itemstack.c == itemstack1.c && itemstack.a == 1 && itemstack1.a == 1 && Item.f[itemstack.c].o()) {
            Item item = Item.f[itemstack.c];
            int i2 = item.n() - itemstack.j();
            int i3 = item.n() - itemstack1.j();
            int i4 = i2 + i3 + item.n() * 5 / 100;
            int i5 = item.n() - i4;

            if (i5 < 0) {
                i5 = 0;
            }

            return new ItemStack(itemstack.c, 1, i5);
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
