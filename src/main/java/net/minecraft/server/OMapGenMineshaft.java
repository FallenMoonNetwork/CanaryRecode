package net.minecraft.server;


import net.minecraft.server.OMapGenStructure;
import net.minecraft.server.OStructureMineshaftStart;
import net.minecraft.server.OStructureStart;


public class OMapGenMineshaft extends OMapGenStructure {

    public OMapGenMineshaft() {
        super();
    }

    @Override
    protected boolean a(int var1, int var2) {
        return this.c.nextInt(100) == 0 && this.c.nextInt(80) < Math.max(Math.abs(var1), Math.abs(var2));
    }

    @Override
    protected OStructureStart b(int var1, int var2) {
        return new OStructureMineshaftStart(this.d, this.c, var1, var2);
    }
}
