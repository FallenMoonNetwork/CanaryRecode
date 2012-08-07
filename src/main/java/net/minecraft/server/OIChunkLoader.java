package net.minecraft.server;


import java.io.IOException;

import net.minecraft.server.OChunk;
import net.minecraft.server.OWorld;


public interface OIChunkLoader {

    OChunk a(OWorld var1, int var2, int var3) throws IOException;

    void a(OWorld var1, OChunk var2);

    void b(OWorld var1, OChunk var2);

    void a();

    void b();
}
