package net.minecraft.server;

public class OStepSound {

    public final String a;
    public final float b;
    public final float c;

    public OStepSound(String var1, float var2, float var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public float a() {
        return this.b;
    }

    public float b() {
        return this.c;
    }

    public String c() {
        return "step." + this.a;
    }
}
