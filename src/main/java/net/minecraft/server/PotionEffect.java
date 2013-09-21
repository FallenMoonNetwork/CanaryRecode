package net.minecraft.server;

public class PotionEffect {

    private int a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;

    public PotionEffect(int i0, int i1) {
        this(i0, i1, 0);
    }

    public PotionEffect(int i0, int i1, int i2) {
        this(i0, i1, i2, false);
    }

    public PotionEffect(int i0, int i1, int i2, boolean flag0) {
        this.a = i0;
        this.b = i1;
        this.c = i2;
        this.e = flag0;
    }

    public PotionEffect(PotionEffect potioneffect) {
        this.a = potioneffect.a;
        this.b = potioneffect.b;
        this.c = potioneffect.c;
    }

    public void a(PotionEffect potioneffect) {
        if (this.a != potioneffect.a) {
            System.err.println("This method should only be called for matching effects!");
        }

        if (potioneffect.c > this.c) {
            this.c = potioneffect.c;
            this.b = potioneffect.b;
        }
        else if (potioneffect.c == this.c && this.b < potioneffect.b) {
            this.b = potioneffect.b;
        }
        else if (!potioneffect.e && this.e) {
            this.e = potioneffect.e;
        }
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public void a(boolean flag0) {
        this.d = flag0;
    }

    public boolean e() {
        return this.e;
    }

    public boolean a(EntityLivingBase entitylivingbase) {
        if (this.b > 0) {
            if (Potion.a[this.a].a(this.b, this.c)) {
                this.b(entitylivingbase);
            }

            this.h();
        }

        return this.b > 0;
    }

    private int h() {
        return --this.b;
    }

    public void b(EntityLivingBase entitylivingbase) {
        if (this.b > 0) {
            Potion.a[this.a].a(entitylivingbase, this.c);
        }
    }

    public String f() {
        return Potion.a[this.a].a();
    }

    public int hashCode() {
        return this.a;
    }

    public String toString() {
        String s0 = "";

        if (this.c() > 0) {
            s0 = this.f() + " x " + (this.c() + 1) + ", Duration: " + this.b();
        }
        else {
            s0 = this.f() + ", Duration: " + this.b();
        }

        if (this.d) {
            s0 = s0 + ", Splash: true";
        }

        return Potion.a[this.a].i() ? "(" + s0 + ")" : s0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof PotionEffect)) {
            return false;
        }
        else {
            PotionEffect potioneffect = (PotionEffect)object;

            return this.a == potioneffect.a && this.c == potioneffect.c && this.b == potioneffect.b && this.d == potioneffect.d && this.e == potioneffect.e;
        }
    }

    public NBTTagCompound a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Id", (byte)this.a());
        nbttagcompound.a("Amplifier", (byte)this.c());
        nbttagcompound.a("Duration", this.b());
        nbttagcompound.a("Ambient", this.e());
        return nbttagcompound;
    }

    public static PotionEffect b(NBTTagCompound nbttagcompound) {
        byte b0 = nbttagcompound.c("Id");
        byte b1 = nbttagcompound.c("Amplifier");
        int i0 = nbttagcompound.e("Duration");
        boolean flag0 = nbttagcompound.n("Ambient");

        return new PotionEffect(b0, i0, b1, flag0);
    }
}
