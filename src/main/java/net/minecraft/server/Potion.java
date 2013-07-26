package net.minecraft.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import com.google.common.collect.Maps;
import net.canarymod.Canary;
import net.canarymod.api.potion.CanaryPotion;
import net.canarymod.hook.entity.PotionEffectAppliedHook;

public class Potion {

    public static final Potion[] a = new Potion[32];
    public static final Potion b = null;
    public static final Potion c = (new Potion(1, false, 8171462)).b("potion.moveSpeed").b(0, 0).a(SharedMonsterAttributes.d, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
    public static final Potion d = (new Potion(2, true, 5926017)).b("potion.moveSlowdown").b(1, 0).a(SharedMonsterAttributes.d, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
    public static final Potion e = (new Potion(3, false, 14270531)).b("potion.digSpeed").b(2, 0).a(1.5D);
    public static final Potion f = (new Potion(4, true, 4866583)).b("potion.digSlowDown").b(3, 0);
    public static final Potion g = (new PotionAttackDamage(5, false, 9643043)).b("potion.damageBoost").b(4, 0).a(SharedMonsterAttributes.e, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 3.0D, 2);
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
    public static final Potion t = (new PotionAttackDamage(18, true, 4738376)).b("potion.weakness").b(5, 0).a(SharedMonsterAttributes.e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
    public static final Potion u = (new Potion(19, true, 5149489)).b("potion.poison").b(6, 0).a(0.25D);
    public static final Potion v = (new Potion(20, true, 3484199)).b("potion.wither").b(1, 2).a(0.25D);
    public static final Potion w = (new PotionHealthBoost(21, false, 16284963)).b("potion.healthBoost").b(2, 2).a(SharedMonsterAttributes.a, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0);
    public static final Potion x = (new PotionAbsoption(22, false, 2445989)).b("potion.absorption").b(2, 2);
    public static final Potion y = (new PotionHealth(23, false, 16262179)).b("potion.saturation");
    public static final Potion z = null;
    public static final Potion A = null;
    public static final Potion B = null;
    public static final Potion C = null;
    public static final Potion D = null;
    public static final Potion E = null;
    public static final Potion F = null;
    public static final Potion G = null;
    public final int H;
    private final Map I = Maps.newHashMap();
    public final boolean J; // CanaryMod: private => public
    private final int K;
    private String L = "";
    private int M = -1;
    private double N;
    private boolean O;
    private CanaryPotion canaryPotion; // CanaryMod: potion instance

    protected Potion(int i0, boolean flag0, int i1) {
        this.H = i0;
        a[i0] = this;
        this.J = flag0;
        if (flag0) {
            this.N = 0.5D;
        } else {
            this.N = 1.0D;
        }

        this.K = i1;

        canaryPotion = new CanaryPotion(this); // CanaryMod: wrap potion
    }

    protected Potion b(int i0, int i1) {
        this.M = i0 + i1 * 8;
        return this;
    }

    public int c() {
        return this.H;
    }

    public void a(EntityLivingBase entitylivingbase, int i0) {
        if (this.H == l.H) {
            if (entitylivingbase.aM() < entitylivingbase.aS()) {
                entitylivingbase.f(1.0F);
            }
        } else if (this.H == u.H) {
            if (entitylivingbase.aM() > 1.0F) {
                entitylivingbase.a(DamageSource.k, 1.0F);
            }
        } else if (this.H == v.H) {
            entitylivingbase.a(DamageSource.l, 1.0F);
        } else if (this.H == s.H && entitylivingbase instanceof EntityPlayer) {
            ((EntityPlayer) entitylivingbase).a(0.025F * (float) (i0 + 1));
        } else if (this.H == y.H && entitylivingbase instanceof EntityPlayer) {
            if (!entitylivingbase.q.I) {
                ((EntityPlayer) entitylivingbase).bH().a(i0 + 1, 1.0F);
            }
        } else if ((this.H != h.H || entitylivingbase.aL()) && (this.H != i.H || !entitylivingbase.aL())) {
            if (this.H == i.H && !entitylivingbase.aL() || this.H == h.H && entitylivingbase.aL()) {
                entitylivingbase.a(DamageSource.k, (float) (6 << i0));
            }
        } else {
            entitylivingbase.f((float) Math.max(4 << i0, 0));
        }
    }

    public void a(EntityLivingBase entitylivingbase, EntityLivingBase entitylivingbase1, int i0, double d0) {
        // CanaryMod: PotionEffectApplied
        PotionEffectAppliedHook hook = (PotionEffectAppliedHook) new PotionEffectAppliedHook((net.canarymod.api.entity.living.CanaryEntityLiving) entitylivingbase1.getCanaryEntity(), Canary.factory().getPotionFactory().newPotionEffect(this.H, 0, i0)).call();
        if (hook.getPotionEffect() == null) {
            return;
        }
        i0 = hook.getPotionEffect().getAmplifier();
        //
        int i1;

        if ((this.H != h.H || entitylivingbase1.aL()) && (this.H != i.H || !entitylivingbase1.aL())) {
            if (this.H == i.H && !entitylivingbase1.aL() || this.H == h.H && entitylivingbase1.aL()) {
                i1 = (int) (d0 * (double) (6 << i0) + 0.5D);
                if (entitylivingbase == null) {
                    entitylivingbase1.a(DamageSource.k, (float) i1);
                } else {
                    entitylivingbase1.a(DamageSource.b(entitylivingbase1, entitylivingbase), (float) i1);
                }
            }
        } else {
            i1 = (int) (d0 * (double) (4 << i0) + 0.5D);
            entitylivingbase1.f((float) i1);
        }
    }

    public boolean b() {
        return false;
    }

    public boolean a(int i0, int i1) {
        int i2;

        if (this.H == l.H) {
            i2 = 50 >> i1;
            return i2 > 0 ? i0 % i2 == 0 : true;
        } else if (this.H == u.H) {
            i2 = 25 >> i1;
            return i2 > 0 ? i0 % i2 == 0 : true;
        } else if (this.H == v.H) {
            i2 = 40 >> i1;
            return i2 > 0 ? i0 % i2 == 0 : true;
        } else {
            return this.H == s.H;
        }
    }

    public Potion b(String s0) {
        this.L = s0;
        return this;
    }

    public String a() {
        return this.L;
    }

    protected Potion a(double d0) {
        this.N = d0;
        return this;
    }

    public double g() {
        return this.N;
    }

    public boolean i() {
        return this.O;
    }

    public int j() {
        return this.K;
    }

    public Potion a(Attribute attribute, String s0, double d0, int i0) {
        AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(s0), this.a(), d0, i0);

        this.I.put(attribute, attributemodifier);
        return this;
    }

    public void a(EntityLivingBase entitylivingbase, BaseAttributeMap baseattributemap, int i0) {
        Iterator iterator = this.I.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            AttributeInstance attributeinstance = baseattributemap.a((Attribute) entry.getKey());

            if (attributeinstance != null) {
                attributeinstance.b((AttributeModifier) entry.getValue());
            }
        }
    }

    public void b(EntityLivingBase entitylivingbase, BaseAttributeMap baseattributemap, int i0) {
        Iterator iterator = this.I.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            AttributeInstance attributeinstance = baseattributemap.a((Attribute) entry.getKey());

            if (attributeinstance != null) {
                AttributeModifier attributemodifier = (AttributeModifier) entry.getValue();

                attributeinstance.b(attributemodifier);
                attributeinstance.a(new AttributeModifier(attributemodifier.a(), this.a() + " " + i0, this.a(i0, attributemodifier), attributemodifier.c()));
            }
        }
    }

    public double a(int i0, AttributeModifier attributemodifier) {
        return attributemodifier.d() * (double) (i0 + 1);
    }

    // CanaryMod
    public CanaryPotion getCanaryPotion() {
        return canaryPotion;
    }
    //
}
