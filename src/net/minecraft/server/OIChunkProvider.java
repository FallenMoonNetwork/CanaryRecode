package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OWorld;

public interface OIChunkProvider {

   boolean a(int var1, int var2);

   OChunk b(int var1, int var2);

   OChunk c(int var1, int var2);

   void a(OIChunkProvider var1, int var2, int var3);

   boolean a(boolean var1, OIProgressUpdate var2);

   boolean a();

   boolean b();

   List a(OEnumCreatureType var1, int var2, int var3, int var4);

   OChunkPosition a(OWorld var1, String var2, int var3, int var4, int var5);
}
