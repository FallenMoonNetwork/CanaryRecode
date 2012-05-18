package net.minecraft.server;

import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;

public interface OIBlockAccess {

    int a(int var1, int var2, int var3);

    OTileEntity b(int var1, int var2, int var3);

    int c(int var1, int var2, int var3);

    OMaterial d(int var1, int var2, int var3);

    boolean e(int var1, int var2, int var3);
}
