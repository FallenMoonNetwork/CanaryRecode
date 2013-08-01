package net.minecraft.server;

public class Achievement extends StatBase {

    public final int a;
    public final int b;
    public final Achievement c;
    private final String l;
    public final ItemStack d;
    private boolean n;

    public Achievement(int i0, String s0, int i1, int i2, Item item, Achievement achievement) {
        this(i0, s0, i1, i2, new ItemStack(item), achievement);
    }

    public Achievement(int i0, String s0, int i1, int i2, Block block, Achievement achievement) {
        this(i0, s0, i1, i2, new ItemStack(block), achievement);
    }

    public Achievement(int i0, String s0, int i1, int i2, ItemStack itemstack, Achievement achievement) {
        super(5242880 + i0, "achievement." + s0);
        this.d = itemstack;
        this.l = "achievement." + s0 + ".desc";
        this.a = i1;
        this.b = i2;
        if (i1 < AchievementList.a) {
            AchievementList.a = i1; // CanaryMod: Add missing declaration
        }

        if (i2 < AchievementList.b) {
            AchievementList.b = i2; // CanaryMod: Add missing declaration
        }

        if (i1 > AchievementList.c) {
            AchievementList.c = i1;
        }

        if (i2 > AchievementList.d) {
            AchievementList.d = i2;
        }

        this.c = achievement;
    }

    public Achievement a() {
        this.f = true;
        return this;
    }

    public Achievement b() {
        this.n = true;
        return this;
    }

    public Achievement c() {
        super.g();
        AchievementList.e.add(this);
        return this;
    }

    public StatBase g() {
        return this.c();
    }

    public StatBase h() {
        return this.a();
    }
}
