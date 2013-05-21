package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.potion.CanaryPotion;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.hook.entity.PotionEffectAppliedHook;


public class Potion {

    public static final Potion[] a = new Potion[32];
    public static final Potion b = null;
    public static final Potion c = (new Potion(1, false, 8171462)).b("potion.moveSpeed").b(0, 0);
    public static final Potion d = (new Potion(2, true, 5926017)).b("potion.moveSlowdown").b(1, 0);
    public static final Potion e = (new Potion(3, false, 14270531)).b("potion.digSpeed").b(2, 0).a(1.5D);
    public static final Potion f = (new Potion(4, true, 4866583)).b("potion.digSlowDown").b(3, 0);
    public static final Potion g = (new Potion(5, false, 9643043)).b("potion.damageBoost").b(4, 0);
    public static final Potion h = (new PotionHealth(6, false, 16262179)).b("potion.heal");
    public static final Potion i = (new PotionHealth(7, true, 4393481)).b("potion.harm");
    public static final Potion j = (new Potion(8, false, 7889559)).b("potion.jump").b(2, 1);
    public static final Potion k = (new Potion(9, true, 5578058)).b("potion.confusion").b(3, 1).a(0.25D);
    public static final Potion l = (new Potion(10, false, 13458603)).b("potion.regeneration").b(7, 0).a(0.25D);
    public static final Potion m = (new Potion(11, false, 10044730)).b("potion.resistance").b(6, 1);
    public static final Potion n = (new Potion(12, false, 14981690)).b("potion.fireResistance").b(7, 1);
    public static final Potion o = (new Potion(13, false, 3035801)).b("potion.waterBreathing").b(0, 2);
    public static final Potion p = (new Potion(14, false, 8356754)).b("potion.invisibility").b(0, 1);
    public static final Potion q = (new Potion(15, true, 2039587)).b("potion.blindness").b(5, 1).a(0.25D);
    public static final Potion r = (new Potion(16, false, 2039713)).b("potion.nightVision").b(4, 1);
    public static final Potion s = (new Potion(17, true, 5797459)).b("potion.hunger").b(1, 1);
    public static final Potion t = (new Potion(18, true, 4738376)).b("potion.weakness").b(5, 0);
    public static final Potion u = (new Potion(19, true, 5149489)).b("potion.poison").b(6, 0).a(0.25D);
    public static final Potion v = (new Potion(20, true, 3484199)).b("potion.wither").b(1, 2).a(0.25D);
    public static final Potion w = null;
    public static final Potion x = null;
    public static final Potion y = null;
    public static final Potion z = null;
    public static final Potion A = null;
    public static final Potion B = null;
    public static final Potion C = null;
    public static final Potion D = null;
    public static final Potion E = null;
    public static final Potion F = null;
    public static final Potion G = null;
    public final int H;
    private String I = "";
    private int J = -1;
    public final boolean K; // CanaryMod: private => public
    private double L;
    private boolean M;
    private final int N;

    private CanaryPotion canaryPotion; // CanaryMod: potion instance

    protected Potion(int i0, boolean flag0, int i1) {
        this.H = i0;
        a[i0] = this;
        this.K = flag0;
        if (flag0) {
            this.L = 0.5D;
        } else {
            this.L = 1.0D;
        }

        this.N = i1;

        canaryPotion = new CanaryPotion(this); // CanaryMod: wrap potion
    }

    protected Potion b(int i0, int i1) {
        this.J = i0 + i1 * 8;
        return this;
    }

    public int c() {
        return this.H;
    }

    public void a(EntityLiving entityliving, int i0) {
        if (this.H == l.H) {
            if (entityliving.aX() < entityliving.aW()) {
                entityliving.j(1);
            }
        } else if (this.H == u.H) {
            if (entityliving.aX() > 1) {
                entityliving.a(DamageSource.k, 1);
            }
        } else if (this.H == v.H) {
            entityliving.a(DamageSource.l, 1);
        } else if (this.H == s.H && entityliving instanceof EntityPlayer) {
            ((EntityPlayer) entityliving).j(0.025F * (float) (i0 + 1));
        } else if ((this.H != h.H || entityliving.bD()) && (this.H != i.H || !entityliving.bD())) {
            if (this.H == i.H && !entityliving.bD() || this.H == h.H && entityliving.bD()) {
                entityliving.a(DamageSource.k, 6 << i0);
            }
        } else {
            entityliving.j(6 << i0);
        }
    }

    public void a(EntityLiving entityliving, EntityLiving entityliving1, int i0, double d0) {
        // CanaryMod: PotionEffectApplied
        PotionEffectAppliedHook hook = new PotionEffectAppliedHook((net.canarymod.api.entity.living.EntityLiving) entityliving1.getCanaryEntity(), Canary.factory().getPotionFactory().newPotionEffect(this.H, 0, i0));

        Canary.hooks().callHook(hook);
        if (hook.getPotionEffect() == null) {
            return;
        }
        i0 = hook.getPotionEffect().getAmplifier();
        //
        int i1;

        if ((this.H != h.H || entityliving1.bD()) && (this.H != i.H || !entityliving1.bD())) {
            if (this.H == i.H && !entityliving1.bD() || this.H == h.H && entityliving1.bD()) {
                i1 = (int) (d0 * (double) (6 << i0) + 0.5D);
                if (entityliving == null) {
                    entityliving1.a(DamageSource.k, i1);
                } else {
                    entityliving1.a(DamageSource.b(entityliving1, entityliving), i1);
                }
            }
        } else {
            i1 = (int) (d0 * (double) (6 << i0) + 0.5D);
            entityliving1.j(i1);
        }
    }

    public boolean b() {
        return false;
    }

    public boolean a(int i0, int i1) {
        int i2;

        if (this.H != l.H && this.H != u.H) {
            if (this.H == v.H) {
                i2 = 40 >> i1;
                return i2 > 0 ? i0 % i2 == 0 : true;
            } else {
                return this.H == s.H;
            }
        } else {
            i2 = 25 >> i1;
            return i2 > 0 ? i0 % i2 == 0 : true;
        }
    }

    public Potion b(String s0) {
        this.I = s0;
        return this;
    }

    public String a() {
        return this.I;
    }

    protected Potion a(double d0) {
        this.L = d0;
        return this;
    }

    public double g() {
        return this.L;
    }

    public boolean i() {
        return this.M;
    }

    public int j() {
        return this.N;
    }

    // CanaryMod
    public CanaryPotion getCanaryPotion() {
        return canaryPotion;
    }
    //
}
