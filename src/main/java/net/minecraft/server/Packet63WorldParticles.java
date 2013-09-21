package net.minecraft.server;

import net.canarymod.api.world.effects.Particle;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

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

    public Packet63WorldParticles() {
    }

    // CanaryMod: our special constructor
    public Packet63WorldParticles(Particle particle) {
        this.a = particle.type.getMcName();
        this.b = (float)particle.x;
        this.c = (float)particle.y;
        this.d = (float)particle.z;
        this.e = (float)particle.velocityX;
        this.f = (float)particle.velocityY;
        this.g = (float)particle.velocityZ;
        this.h = particle.speed;
        this.i = particle.quantity;
    }//

    public void a(DataInput datainput) throws IOException {
        this.a = a(datainput, 64);
        this.b = datainput.readFloat();
        this.c = datainput.readFloat();
        this.d = datainput.readFloat();
        this.e = datainput.readFloat();
        this.f = datainput.readFloat();
        this.g = datainput.readFloat();
        this.h = datainput.readFloat();
        this.i = datainput.readInt();
    }

    public void a(DataOutput dataoutput) throws IOException {
        a(this.a, dataoutput);
        dataoutput.writeFloat(this.b);
        dataoutput.writeFloat(this.c);
        dataoutput.writeFloat(this.d);
        dataoutput.writeFloat(this.e);
        dataoutput.writeFloat(this.f);
        dataoutput.writeFloat(this.g);
        dataoutput.writeFloat(this.h);
        dataoutput.writeInt(this.i);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 64;
    }
}
