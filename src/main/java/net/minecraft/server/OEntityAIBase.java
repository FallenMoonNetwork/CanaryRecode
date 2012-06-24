package net.minecraft.server;

public abstract class OEntityAIBase {

    private int a = 0;

    public OEntityAIBase() {
        super();
    }

    public abstract boolean a();

    public boolean b() {
        return this.a();
    }

    public boolean g() {
        return true;
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public void a(int var1) {
        this.a = var1;
    }

    public int h() {
        return this.a;
    }
}
