package net.minecraft.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ORConOutputStream {

   private ByteArrayOutputStream a;
   private DataOutputStream b;


   public ORConOutputStream(int var1) {
      super();
      this.a = new ByteArrayOutputStream(var1);
      this.b = new DataOutputStream(this.a);
   }

   public void a(byte[] var1) {
      try {
        this.b.write(var1, 0, var1.length);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }

   public void a(String var1) {
      try {
        this.b.writeBytes(var1);
        this.b.write(0);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
      
   }

   public void a(int var1) {
      try {
        this.b.write(var1);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }

   public void a(short var1) {
      try {
        this.b.writeShort(Short.reverseBytes(var1));
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }

   public byte[] a() {
      return this.a.toByteArray();
   }

   public void b() {
      this.a.reset();
   }
}
