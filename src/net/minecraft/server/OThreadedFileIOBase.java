package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.OIThreadedFileIO;

public class OThreadedFileIOBase implements Runnable {

   public static final OThreadedFileIOBase a = new OThreadedFileIOBase();
   private List b; // threaded io queue
   private volatile long c = 0L;
   private volatile long d = 0L;
   private volatile boolean e = false;


   private OThreadedFileIOBase() {
      super();
      b = Collections.synchronizedList(new ArrayList());
      Thread var1 = new Thread(this, "File IO Thread");
      var1.setPriority(1);
      var1.start();
   }

   public void run() {
	   // CanaryMod edit: adding loop here to keep thread running
	   while(true)
	   {
		   this.b();
	   }
   }

   // process queue
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

   // queue IO (threaded io)
   public void a(OIThreadedFileIO var1) {
	   if(this.b.contains(var1))
		   return;

	   ++this.c;
	   // add to the io queue
	   this.b.add(var1);
   }

   // CanaryMod edit: added throws, removed catch block
   public void a() throws InterruptedException {
      this.e = true;

      // As long as write queue counter != saved io counter
      while(this.c != this.d) {
    	  Thread.sleep(10L);
      }

      // thread is not waiting
      this.e = false;
   }

}
