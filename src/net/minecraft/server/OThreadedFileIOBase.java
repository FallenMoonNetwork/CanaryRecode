package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.OIThreadedFileIO;

public class OThreadedFileIOBase implements Runnable {

   public static final OThreadedFileIOBase a = new OThreadedFileIOBase();
   private List b = Collections.synchronizedList(new ArrayList());
   private volatile long c = 0L;
   private volatile long d = 0L;
   private volatile boolean e = false;


   private OThreadedFileIOBase() {
      super();
      Thread var1 = new Thread(this, "File IO Thread");
      var1.setPriority(1);
      var1.start();
   }

   public void run() {
      this.b();
   }

   private void b() {
      for(int var1 = 0; var1 < this.b.size(); ++var1) {
         OIThreadedFileIO var2 = (OIThreadedFileIO)this.b.get(var1);
         boolean var3 = var2.c();
         if(!var3) {
            this.b.remove(var1--);
            ++this.d;
         }

         try {
            if(!this.e) {
               Thread.sleep(10L);
            } else {
               Thread.sleep(0L);
            }
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }

      if(this.b.isEmpty()) {
         try {
            Thread.sleep(25L);
         } catch (InterruptedException var5) {
            var5.printStackTrace();
         }
      }

   }

   public void a(OIThreadedFileIO var1) {
      if(!this.b.contains(var1)) {
         ++this.c;
         this.b.add(var1);
      }
   }

   public void a() {
      this.e = true;

      while(this.c != this.d) {
         try {
            Thread.sleep(10L);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
      }

      this.e = false;
   }

}
