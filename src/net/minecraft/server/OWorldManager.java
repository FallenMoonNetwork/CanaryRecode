package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIWorldAccess;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OPacket61DoorChange;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorldServer;

public class OWorldManager implements OIWorldAccess {

    private OMinecraftServer a;
    private OWorldServer b;

    public OWorldManager(OMinecraftServer var1, OWorldServer var2) {
        super();
        this.a = var1;
        this.b = var2;
    }

    public void a(String var1, double var2, double var4, double var6, double var8, double var10, double var12) {
    }

    public void a(OEntity var1) {
      //CanaryMod refactored to use our tracker wrapper
        this.b.getCanaryDimension().getEntityTracker().trackEntity(var1.getCanaryEntity());
    }

    public void b(OEntity var1) {
      //CanaryMod refactored to use our tracker wrapper
        this.b.getCanaryDimension().getEntityTracker().untrackEntity(var1.getCanaryEntity());
    }

    public void a(String var1, double var2, double var4, double var6, float var8, float var9) {
    }

    public void a(int var1, int var2, int var3, int var4, int var5, int var6) {
    }

    public void a(int var1, int var2, int var3) {
        //CanaryMod: Refactored to get around updated blocks per world
        this.a.getCanaryConfigurationManager().markBlockNeedsUpdate(var1, var2, var3, this.b.t.g, this.b.getCanaryDimension().getName());
//        this.a.h.a(var1, var2, var3, this.b.t.g);
    }

    public void b(int var1, int var2, int var3) {
    }

    public void a(String var1, int var2, int var3, int var4) {
    }

    public void a(int var1, int var2, int var3, OTileEntity var4) {
        this.a.h.a(var1, var2, var3, var4);
    }

    public void a(OEntityPlayer var1, int var2, int var3, int var4, int var5, int var6) {
        this.a.h.a(var1, var3, var4, var5, 64.0D, this.b.t.g, new OPacket61DoorChange(var2, var3, var4, var5, var6));
    }
}
