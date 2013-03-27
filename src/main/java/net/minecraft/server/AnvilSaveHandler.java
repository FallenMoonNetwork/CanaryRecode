package net.minecraft.server;


import java.io.File;

import net.canarymod.api.world.WorldType;

public class AnvilSaveHandler extends SaveHandler {

    public AnvilSaveHandler(File file1, String s0, boolean flag0, WorldType type) {
        super(file1, s0, flag0, type);
    }

    @Override
    public IChunkLoader a(WorldProvider worldprovider) {
        // CanaryMod changed the whole thing since we have recollection of the world type we're serving.
        // This means we can spare us the checks for instanceof generator
        // And just put together the proper save path
        return new AnvilChunkLoader(new File(getWorldBaseDir(), getBaseName() + "/" + getBaseName() + "_" + this.type.getName()));
    }

    @Override
    public void a() {
        try {
            ThreadedFileIOBase.a.a();
        } catch (InterruptedException interruptedexception) {
            interruptedexception.printStackTrace();
        }

        RegionFileCache.a();
    }
}
