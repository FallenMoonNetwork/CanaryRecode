package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OItemBlock;

public class OItemMetadata extends OItemBlock {

    private OBlock a;

    public OItemMetadata(int var1, OBlock var2) {
        super(var1);
        this.a = var2;
        this.f(0);
        this.a(true);
    }

    public int a(int var1) {
        return var1;
    }
}
