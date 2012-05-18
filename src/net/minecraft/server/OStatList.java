package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.server.OAchievementList;
import net.minecraft.server.OBlock;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OFurnaceRecipes;
import net.minecraft.server.OIRecipe;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OStatBase;
import net.minecraft.server.OStatBasic;
import net.minecraft.server.OStatCollector;
import net.minecraft.server.OStatCrafting;

public class OStatList {

    protected static Map a = new HashMap();
    public static List b = new ArrayList();
    public static List c = new ArrayList();
    public static List d = new ArrayList();
    public static List e = new ArrayList();
    public static OStatBase f = (new OStatBasic(1000, "stat.startGame")).e().d();
    public static OStatBase g = (new OStatBasic(1001, "stat.createWorld")).e().d();
    public static OStatBase h = (new OStatBasic(1002, "stat.loadWorld")).e().d();
    public static OStatBase i = (new OStatBasic(1003, "stat.joinMultiplayer")).e().d();
    public static OStatBase j = (new OStatBasic(1004, "stat.leaveGame")).e().d();
    public static OStatBase k = (new OStatBasic(1100, "stat.playOneMinute", OStatBase.i)).e().d();
    public static OStatBase l = (new OStatBasic(2000, "stat.walkOneCm", OStatBase.j)).e().d();
    public static OStatBase m = (new OStatBasic(2001, "stat.swimOneCm", OStatBase.j)).e().d();
    public static OStatBase n = (new OStatBasic(2002, "stat.fallOneCm", OStatBase.j)).e().d();
    public static OStatBase o = (new OStatBasic(2003, "stat.climbOneCm", OStatBase.j)).e().d();
    public static OStatBase p = (new OStatBasic(2004, "stat.flyOneCm", OStatBase.j)).e().d();
    public static OStatBase q = (new OStatBasic(2005, "stat.diveOneCm", OStatBase.j)).e().d();
    public static OStatBase r = (new OStatBasic(2006, "stat.minecartOneCm", OStatBase.j)).e().d();
    public static OStatBase s = (new OStatBasic(2007, "stat.boatOneCm", OStatBase.j)).e().d();
    public static OStatBase t = (new OStatBasic(2008, "stat.pigOneCm", OStatBase.j)).e().d();
    public static OStatBase u = (new OStatBasic(2010, "stat.jump")).e().d();
    public static OStatBase v = (new OStatBasic(2011, "stat.drop")).e().d();
    public static OStatBase w = (new OStatBasic(2020, "stat.damageDealt")).d();
    public static OStatBase x = (new OStatBasic(2021, "stat.damageTaken")).d();
    public static OStatBase y = (new OStatBasic(2022, "stat.deaths")).d();
    public static OStatBase z = (new OStatBasic(2023, "stat.mobKills")).d();
    public static OStatBase A = (new OStatBasic(2024, "stat.playerKills")).d();
    public static OStatBase B = (new OStatBasic(2025, "stat.fishCaught")).d();
    public static OStatBase[] C = a("stat.mineBlock", 16777216);
    public static OStatBase[] D;
    public static OStatBase[] E;
    public static OStatBase[] F;
    private static boolean G;
    private static boolean H;

    public OStatList() {
        super();
    }

    public static void a() {
    }

    public static void b() {
        E = a(E, "stat.useItem", 16908288, 0, 256);
        F = b(F, "stat.breakItem", 16973824, 0, 256);
        G = true;
        d();
    }

    public static void c() {
        E = a(E, "stat.useItem", 16908288, 256, 32000);
        F = b(F, "stat.breakItem", 16973824, 256, 32000);
        H = true;
        d();
    }

    public static void d() {
        if (G && H) {
            HashSet var0 = new HashSet();
            Iterator var1 = OCraftingManager.a().b().iterator();

            while (var1.hasNext()) {
                OIRecipe var2 = (OIRecipe) var1.next();
                var0.add(Integer.valueOf(var2.b().c));
            }

            var1 = OFurnaceRecipes.a().b().values().iterator();

            while (var1.hasNext()) {
                OItemStack var4 = (OItemStack) var1.next();
                var0.add(Integer.valueOf(var4.c));
            }

            D = new OStatBase[32000];
            var1 = var0.iterator();

            while (var1.hasNext()) {
                Integer var5 = (Integer) var1.next();
                if (OItem.d[var5.intValue()] != null) {
                    String var3 = OStatCollector.a("stat.craftItem", new Object[] { OItem.d[var5.intValue()].l() });
                    D[var5.intValue()] = (new OStatCrafting(16842752 + var5.intValue(), var3, var5.intValue())).d();
                }
            }

            a(D);
        }
    }

    private static OStatBase[] a(String var0, int var1) {
        OStatBase[] var2 = new OStatBase[256];

        for (int var3 = 0; var3 < 256; ++var3) {
            if (OBlock.m[var3] != null && OBlock.m[var3].r()) {
                String var4 = OStatCollector.a(var0, new Object[] { OBlock.m[var3].p() });
                var2[var3] = (new OStatCrafting(var1 + var3, var4, var3)).d();
                e.add(var2[var3]);
            }
        }

        a(var2);
        return var2;
    }

    private static OStatBase[] a(OStatBase[] var0, String var1, int var2, int var3, int var4) {
        if (var0 == null) {
            var0 = new OStatBase[32000];
        }

        for (int var5 = var3; var5 < var4; ++var5) {
            if (OItem.d[var5] != null) {
                String var6 = OStatCollector.a(var1, new Object[] { OItem.d[var5].l() });
                var0[var5] = (new OStatCrafting(var2 + var5, var6, var5)).d();
                if (var5 >= 256) {
                    d.add(var0[var5]);
                }
            }
        }

        a(var0);
        return var0;
    }

    private static OStatBase[] b(OStatBase[] var0, String var1, int var2, int var3, int var4) {
        if (var0 == null) {
            var0 = new OStatBase[32000];
        }

        for (int var5 = var3; var5 < var4; ++var5) {
            if (OItem.d[var5] != null && OItem.d[var5].g()) {
                String var6 = OStatCollector.a(var1, new Object[] { OItem.d[var5].l() });
                var0[var5] = (new OStatCrafting(var2 + var5, var6, var5)).d();
            }
        }

        a(var0);
        return var0;
    }

    private static void a(OStatBase[] var0) {
        a(var0, OBlock.B.bO, OBlock.A.bO);
        a(var0, OBlock.D.bO, OBlock.D.bO);
        a(var0, OBlock.bf.bO, OBlock.ba.bO);
        a(var0, OBlock.aC.bO, OBlock.aB.bO);
        a(var0, OBlock.aO.bO, OBlock.aN.bO);
        a(var0, OBlock.bi.bO, OBlock.bh.bO);
        a(var0, OBlock.aQ.bO, OBlock.aP.bO);
        a(var0, OBlock.ag.bO, OBlock.af.bO);
        a(var0, OBlock.aj.bO, OBlock.ak.bO);
        a(var0, OBlock.u.bO, OBlock.v.bO);
        a(var0, OBlock.aA.bO, OBlock.v.bO);
    }

    private static void a(OStatBase[] var0, int var1, int var2) {
        if (var0[var1] != null && var0[var2] == null) {
            var0[var2] = var0[var1];
        } else {
            b.remove(var0[var1]);
            e.remove(var0[var1]);
            c.remove(var0[var1]);
            var0[var1] = var0[var2];
        }
    }

    static {
        OAchievementList.a();
        G = false;
        H = false;
    }
}
