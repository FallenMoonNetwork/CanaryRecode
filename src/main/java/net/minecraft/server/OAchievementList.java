package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.OAchievement;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;

public class OAchievementList {

    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static List e = new ArrayList();
    public static OAchievement f = (new OAchievement(0, "openInventory", 0, 0, OItem.aK, (OAchievement) null)).a().c();
    public static OAchievement g = (new OAchievement(1, "mineWood", 2, 1, OBlock.J, f)).c();
    public static OAchievement h = (new OAchievement(2, "buildWorkBench", 4, -1, OBlock.ay, g)).c();
    public static OAchievement i = (new OAchievement(3, "buildPickaxe", 4, 2, OItem.s, h)).c();
    public static OAchievement j = (new OAchievement(4, "buildFurnace", 3, 4, OBlock.aC, i)).c();
    public static OAchievement k = (new OAchievement(5, "acquireIron", 1, 4, OItem.n, j)).c();
    public static OAchievement l = (new OAchievement(6, "buildHoe", 2, -3, OItem.M, h)).c();
    public static OAchievement m = (new OAchievement(7, "makeBread", -1, -3, OItem.T, l)).c();
    public static OAchievement n = (new OAchievement(8, "bakeCake", 0, -5, OItem.aY, l)).c();
    public static OAchievement o = (new OAchievement(9, "buildBetterPickaxe", 6, 2, OItem.w, i)).c();
    public static OAchievement p = (new OAchievement(10, "cookFish", 2, 6, OItem.aU, j)).c();
    public static OAchievement q = (new OAchievement(11, "onARail", 2, 3, OBlock.aG, k)).b().c();
    public static OAchievement r = (new OAchievement(12, "buildSword", 6, -1, OItem.q, h)).c();
    public static OAchievement s = (new OAchievement(13, "killEnemy", 8, -1, OItem.aW, r)).c();
    public static OAchievement t = (new OAchievement(14, "killCow", 7, -3, OItem.aE, r)).c();
    public static OAchievement u = (new OAchievement(15, "flyPig", 8, -4, OItem.az, t)).b().c();
    public static OAchievement v = (new OAchievement(16, "snipeSkeleton", 7, 0, OItem.j, s)).b().c();
    public static OAchievement w = (new OAchievement(17, "diamonds", -1, 5, OItem.m, k)).c();
    public static OAchievement x = (new OAchievement(18, "portal", -1, 7, OBlock.ap, w)).c();
    public static OAchievement y = (new OAchievement(19, "ghast", -4, 8, OItem.bo, x)).b().c();
    public static OAchievement z = (new OAchievement(20, "blazeRod", 0, 9, OItem.bn, x)).c();
    public static OAchievement A = (new OAchievement(21, "potion", 2, 8, OItem.br, z)).c();
    public static OAchievement B = (new OAchievement(22, "theEnd", 3, 10, OItem.bz, z)).b().c();
    public static OAchievement C = (new OAchievement(23, "theEnd2", 4, 13, OBlock.bK, B)).b().c();
    public static OAchievement D = (new OAchievement(24, "enchantments", -4, 4, OBlock.bE, w)).c();
    public static OAchievement E = (new OAchievement(25, "overkill", -4, 1, OItem.y, D)).b().c();
    public static OAchievement F = (new OAchievement(26, "bookcase", -3, 6, OBlock.an, D)).c();

    public OAchievementList() {
        super();
    }

    public static void a() {
    }

    static {
        System.out.println(e.size() + " achievements");
    }
}
