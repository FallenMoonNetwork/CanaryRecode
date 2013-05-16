package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.canarymod.api.world.effects.Particle;

public class Packet63WorldParticles extends Packet {

    private String a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private int i;

    public Packet63WorldParticles() {}
    
    // CanaryMod: our special constructor
    public Packet63WorldParticles(Particle particle) {
        this.a = particle.type.getMcName();
        this.b = (float) particle.x;
        this.c = (float) particle.y;
        this.d = (float) particle.z;
        this.e = (float) particle.velocityX;
        this.f = (float) particle.velocityY;
        this.g = (float) particle.velocityZ;
        this.h = particle.speed;
        this.i = particle.quantity;
    }//

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = a(datainputstream, 64);
        this.b = datainputstream.readFloat();
        this.c = datainputstream.readFloat();
        this.d = datainputstream.readFloat();
        this.e = datainputstream.readFloat();
        this.f = datainputstream.readFloat();
        this.g = datainputstream.readFloat();
        this.h = datainputstream.readFloat();
        this.i = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        a(this.a, dataoutputstream);
        dataoutputstream.writeFloat(this.b);
        dataoutputstream.writeFloat(this.c);
        dataoutputstream.writeFloat(this.d);
        dataoutputstream.writeFloat(this.e);
        dataoutputstream.writeFloat(this.f);
        dataoutputstream.writeFloat(this.g);
        dataoutputstream.writeFloat(this.h);
        dataoutputstream.writeInt(this.i);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 64;
    }
}