package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OComponentMineshaftRoom;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OWorld;

public class OStructureMineshaftStart extends OStructureStart {

    public OStructureMineshaftStart(OWorld var1, Random var2, int var3, int var4) {
        super();
        OComponentMineshaftRoom var5 = new OComponentMineshaftRoom(0, var2, (var3 << 4) + 2, (var4 << 4) + 2);
        this.a.add(var5);
        var5.a(var5, this.a, var2);
        this.d();
        this.a(var1, var2, 10);
    }
}
