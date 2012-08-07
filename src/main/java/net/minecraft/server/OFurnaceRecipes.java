package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;

public class OFurnaceRecipes {

    private static final OFurnaceRecipes a = new OFurnaceRecipes();
    private Map b = new HashMap();

    public static final OFurnaceRecipes a() {
        return a;
    }

    private OFurnaceRecipes() {
        super();
        this.a(OBlock.H.bO, new OItemStack(OItem.n));
        this.a(OBlock.G.bO, new OItemStack(OItem.o));
        this.a(OBlock.aw.bO, new OItemStack(OItem.m));
        this.a(OBlock.E.bO, new OItemStack(OBlock.M));
        this.a(OItem.ap.bP, new OItemStack(OItem.aq));
        this.a(OItem.bh.bP, new OItemStack(OItem.bi));
        this.a(OItem.bj.bP, new OItemStack(OItem.bk));
        this.a(OItem.aT.bP, new OItemStack(OItem.aU));
        this.a(OBlock.w.bO, new OItemStack(OBlock.t));
        this.a(OItem.aH.bP, new OItemStack(OItem.aG));
        this.a(OBlock.aV.bO, new OItemStack(OItem.aV, 1, 2));
        this.a(OBlock.J.bO, new OItemStack(OItem.l, 1, 1));
        this.a(OBlock.I.bO, new OItemStack(OItem.l));
        this.a(OBlock.aN.bO, new OItemStack(OItem.aB));
        this.a(OBlock.N.bO, new OItemStack(OItem.aV, 1, 4));
    }

    public void a(int var1, OItemStack var2) {
        this.b.put(Integer.valueOf(var1), var2);
    }

    public OItemStack a(int var1) {
        return (OItemStack) this.b.get(Integer.valueOf(var1));
    }

    public Map b() {
        return this.b;
    }

}
