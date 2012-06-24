package net.minecraft.server;

import net.minecraft.server.OIStatType;
import net.minecraft.server.OStatBase;
import net.minecraft.server.OStatList;

public class OStatBasic extends OStatBase {

    public OStatBasic(int var1, String var2, OIStatType var3) {
        super(var1, var2, var3);
    }

    public OStatBasic(int var1, String var2) {
        super(var1, var2);
    }

    @Override
    public OStatBase d() {
        super.d();
        OStatList.c.add(this);
        return this;
    }
}
