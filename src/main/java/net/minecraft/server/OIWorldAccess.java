package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OTileEntity;

public interface OIWorldAccess {

    void a(int var1, int var2, int var3);

    void b(int var1, int var2, int var3);

    void a(int var1, int var2, int var3, int var4, int var5, int var6);

    void a(String var1, double var2, double var4, double var6, float var8, float var9);

    void a(String var1, double var2, double var4, double var6, double var8, double var10, double var12);

    void a(OEntity var1);

    void b(OEntity var1);

    void a(String var1, int var2, int var3, int var4);

    void a(int var1, int var2, int var3, OTileEntity var4);

    void a(OEntityPlayer var1, int var2, int var3, int var4, int var5, int var6);
}
