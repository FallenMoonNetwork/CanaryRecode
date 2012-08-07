package net.minecraft.server;


import java.io.File;
import java.util.List;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.OIPlayerFileData;
import net.minecraft.server.OWorldInfo;
import net.minecraft.server.OWorldProvider;


public interface OISaveHandler {

    OWorldInfo c();

    void b();

    OIChunkLoader a(OWorldProvider var1);

    void a(OWorldInfo var1, List var2);

    void a(OWorldInfo var1);

    OIPlayerFileData d();

    void e();

    File b(String var1);
}
