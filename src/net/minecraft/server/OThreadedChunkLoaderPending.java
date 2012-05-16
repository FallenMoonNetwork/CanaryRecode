package net.minecraft.server;

import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.ONBTTagCompound;

class OThreadedChunkLoaderPending {

   public final OChunkCoordIntPair a;
   public final ONBTTagCompound b;


   public OThreadedChunkLoaderPending(OChunkCoordIntPair var1, ONBTTagCompound var2) {
      super();
      this.a = var1;
      this.b = var2;
   }
}
