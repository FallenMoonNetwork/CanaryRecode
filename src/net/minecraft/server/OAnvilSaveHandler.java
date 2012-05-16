package net.minecraft.server;

import java.io.File;
import java.util.List;
import net.minecraft.server.OAnvilChunkLoader;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.OPlayerNBTManager;
import net.minecraft.server.ORegionFileCache;
import net.minecraft.server.OThreadedFileIOBase;
import net.minecraft.server.OWorldInfo;
import net.minecraft.server.OWorldProvider;
import net.minecraft.server.OWorldProviderHell;
import net.minecraft.server.OWorldProviderSky;

public class OAnvilSaveHandler extends OPlayerNBTManager {

   public OAnvilSaveHandler(File var1, String var2, boolean var3) {
      super(var1, var2, var3);
   }

   public OIChunkLoader a(OWorldProvider var1) {
      File var2 = this.a();
      File var3;
      if(var1 instanceof OWorldProviderHell) {
         var3 = new File(var2, "DIM-1");
         var3.mkdirs();
         return new OAnvilChunkLoader(var3);
      } else if(var1 instanceof OWorldProviderSky) {
         var3 = new File(var2, "DIM1");
         var3.mkdirs();
         return new OAnvilChunkLoader(var3);
      } else {
         return new OAnvilChunkLoader(var2);
      }
   }

   public void a(OWorldInfo var1, List var2) {
      var1.a(19133);
      super.a(var1, var2);
   }

   public void e() {
      try {
         OThreadedFileIOBase.a.a();
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      ORegionFileCache.a();
   }
}
