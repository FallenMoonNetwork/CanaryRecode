package net.minecraft.server;


public class WatchableObject {

    private final int a;
    private final int b;
    private Object c;
    private boolean d;

    public WatchableObject(int i0, int i1, Object object) {
        this.b = i1;
        this.c = object;
        this.a = i0;
        this.d = true;
    }

    public int a() {
        return this.b;
    }

    public void a(Object object) {
        this.c = object;
    }

    public Object b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean flag0) {
        this.d = flag0;
    }

    static boolean a(WatchableObject watchableobject, boolean flag0) {
        return watchableobject.d = flag0;
    }
}
