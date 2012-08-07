package net.minecraft.server;

import java.io.File;
import java.util.List;

import net.canarymod.api.world.WorldType;
import net.minecraft.server.OAnvilChunkLoader;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.ORegionFileCache;
import net.minecraft.server.OSaveHandler;
import net.minecraft.server.OThreadedFileIOBase;
import net.minecraft.server.OWorldInfo;
import net.minecraft.server.OWorldProvider;
import net.minecraft.server.OWorldProviderEnd;
import net.minecraft.server.OWorldProviderHell;

public class OAnvilSaveHandler extends OSaveHandler {

    public OAnvilSaveHandler(File var1, String var2, boolean var3, WorldType type) {
        super(var1, var2, var3, type);
    }

    @Override
    public OIChunkLoader a(OWorldProvider var1) {
        //CanaryMod changed the whole thing since we have recollection of the world type we're serving!
        return new OAnvilChunkLoader(new File(getWorldBaseDir(), getBaseName()+"/"+getBaseName()+"_"+this.type.getName()));
    }

    @Override
    public void a(OWorldInfo var1, List var2) {
        var1.setVersion(19133);
        super.a(var1, var2);
    }

    @Override
    public void e() {
        try {
            OThreadedFileIOBase.a.a();
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

        ORegionFileCache.a();
    }
}
