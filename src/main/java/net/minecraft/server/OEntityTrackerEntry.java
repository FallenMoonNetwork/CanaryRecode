package net.minecraft.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.canarymod.api.CanaryEntityTrackerEntry;
import net.minecraft.server.OBlock;
import net.minecraft.server.ODataWatcher;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityBoat;
import net.minecraft.server.OEntityDragon;
import net.minecraft.server.OEntityEgg;
import net.minecraft.server.OEntityEnderCrystal;
import net.minecraft.server.OEntityEnderEye;
import net.minecraft.server.OEntityEnderPearl;
import net.minecraft.server.OEntityExpBottle;
import net.minecraft.server.OEntityFallingSand;
import net.minecraft.server.OEntityFireball;
import net.minecraft.server.OEntityFishHook;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityMinecart;
import net.minecraft.server.OEntityPainting;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OEntityPotion;
import net.minecraft.server.OEntitySmallFireball;
import net.minecraft.server.OEntitySnowball;
import net.minecraft.server.OEntityTNTPrimed;
import net.minecraft.server.OEntityXPOrb;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket17Sleep;
import net.minecraft.server.OPacket20NamedEntitySpawn;
import net.minecraft.server.OPacket21PickupSpawn;
import net.minecraft.server.OPacket23VehicleSpawn;
import net.minecraft.server.OPacket24MobSpawn;
import net.minecraft.server.OPacket25EntityPainting;
import net.minecraft.server.OPacket26EntityExpOrb;
import net.minecraft.server.OPacket28EntityVelocity;
import net.minecraft.server.OPacket29DestroyEntity;
import net.minecraft.server.OPacket31RelEntityMove;
import net.minecraft.server.OPacket32EntityLook;
import net.minecraft.server.OPacket33RelEntityMoveLook;
import net.minecraft.server.OPacket34EntityTeleport;
import net.minecraft.server.OPacket35EntityHeadRotation;
import net.minecraft.server.OPacket40EntityMetadata;
import net.minecraft.server.OPacket41EntityEffect;
import net.minecraft.server.OPacket5PlayerInventory;
import net.minecraft.server.OPotionEffect;

public class OEntityTrackerEntry {

    public OEntity a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public double j;
    public double k;
    public double l;
    public int m = 0;
    private double p;
    private double q;
    private double r;
    private boolean s = false;
    private boolean t;
    private int u = 0;
    public boolean n = false;
    public Set o = new HashSet();
    private CanaryEntityTrackerEntry canaryEntry;
    public OEntityTrackerEntry(OEntity var1, int var2, int var3, boolean var4) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.t = var4;
        this.d = OMathHelper.b(var1.bm * 32.0D);
        this.e = OMathHelper.b(var1.bn * 32.0D);
        this.f = OMathHelper.b(var1.bo * 32.0D);
        this.g = OMathHelper.d(var1.bs * 256.0F / 360.0F);
        this.h = OMathHelper.d(var1.bt * 256.0F / 360.0F);
        this.i = OMathHelper.d(var1.ar() * 256.0F / 360.0F);
        this.canaryEntry = new CanaryEntityTrackerEntry(this);
    }

    /**
     * CanaryMod get the entity tracker entry
     * @return the canaryEntry
     */
    public CanaryEntityTrackerEntry getCanaryTrackerEntry() {
        return canaryEntry;
    }

    @Override
    public boolean equals(Object var1) {
        return var1 instanceof OEntityTrackerEntry ? ((OEntityTrackerEntry) var1).a.bd == this.a.bd : false;
    }

    @Override
    public int hashCode() {
        return this.a.bd;
    }

    public void a(List var1) {
        this.n = false;
        if (!this.s || this.a.e(this.p, this.q, this.r) > 16.0D) {
            this.p = this.a.bm;
            this.q = this.a.bn;
            this.r = this.a.bo;
            this.s = true;
            this.n = true;
            this.b(var1);
        }

        ++this.u;
        if (this.m++ % this.c == 0 || this.a.ce) {
            int var2 = OMathHelper.b(this.a.bm * 32.0D);
            int var3 = OMathHelper.b(this.a.bn * 32.0D);
            int var4 = OMathHelper.b(this.a.bo * 32.0D);
            int var5 = OMathHelper.d(this.a.bs * 256.0F / 360.0F);
            int var6 = OMathHelper.d(this.a.bt * 256.0F / 360.0F);
            int var7 = var2 - this.d;
            int var8 = var3 - this.e;
            int var9 = var4 - this.f;
            Object var10 = null;
            boolean var11 = Math.abs(var7) >= 4 || Math.abs(var8) >= 4 || Math.abs(var9) >= 4;
            boolean var12 = Math.abs(var5 - this.g) >= 4 || Math.abs(var6 - this.h) >= 4;
            if (var7 >= -128 && var7 < 128 && var8 >= -128 && var8 < 128 && var9 >= -128 && var9 < 128 && this.u <= 400) {
                if (var11 && var12) {
                    var10 = new OPacket33RelEntityMoveLook(this.a.bd, (byte) var7, (byte) var8, (byte) var9, (byte) var5, (byte) var6);
                } else if (var11) {
                    var10 = new OPacket31RelEntityMove(this.a.bd, (byte) var7, (byte) var8, (byte) var9);
                } else if (var12) {
                    var10 = new OPacket32EntityLook(this.a.bd, (byte) var5, (byte) var6);
                }
            } else {
                this.u = 0;
                this.a.bm = var2 / 32.0D;
                this.a.bn = var3 / 32.0D;
                this.a.bo = var4 / 32.0D;
                var10 = new OPacket34EntityTeleport(this.a.bd, var2, var3, var4, (byte) var5, (byte) var6);
            }

            if (this.t) {
                double var13 = this.a.bp - this.j;
                double var15 = this.a.bq - this.k;
                double var17 = this.a.br - this.l;
                double var19 = 0.02D;
                double var21 = var13 * var13 + var15 * var15 + var17 * var17;
                if (var21 > var19 * var19 || var21 > 0.0D && this.a.bp == 0.0D && this.a.bq == 0.0D && this.a.br == 0.0D) {
                    this.j = this.a.bp;
                    this.k = this.a.bq;
                    this.l = this.a.br;
                    this.a((new OPacket28EntityVelocity(this.a.bd, this.j, this.k, this.l)));
                }
            }

            if (var10 != null) {
                this.a((OPacket) var10);
            }

            ODataWatcher var23 = this.a.aP();
            if (var23.a()) {
                this.b((new OPacket40EntityMetadata(this.a.bd, var23)));
            }

            int var24 = OMathHelper.d(this.a.ar() * 256.0F / 360.0F);
            if (Math.abs(var24 - this.i) >= 4) {
                this.a((new OPacket35EntityHeadRotation(this.a.bd, (byte) var24)));
                this.i = var24;
            }

            if (var11) {
                this.d = var2;
                this.e = var3;
                this.f = var4;
            }

            if (var12) {
                this.g = var5;
                this.h = var6;
            }
        }

        this.a.ce = false;
        if (this.a.bB) {
            this.b((new OPacket28EntityVelocity(this.a)));
            this.a.bB = false;
        }

    }

    public void a(OPacket var1) {
        Iterator var2 = this.o.iterator();

        while (var2.hasNext()) {
            OEntityPlayerMP var3 = (OEntityPlayerMP) var2.next();
            var3.a.b(var1);
        }

    }

    public void b(OPacket var1) {
        this.a(var1);
        if (this.a instanceof OEntityPlayerMP) {
            ((OEntityPlayerMP) this.a).a.b(var1);
        }

    }

    public void a() {
        this.a((new OPacket29DestroyEntity(this.a.bd)));
    }

    public void a(OEntityPlayerMP var1) {
        if (this.o.contains(var1)) {
            this.o.remove(var1);
        }

    }

    public void b(OEntityPlayerMP var1) {
        if (var1 != this.a) {
            double var2 = var1.bm - (this.d / 32);
            double var4 = var1.bo - (this.f / 32);
            if (var2 >= (-this.b) && var2 <= this.b && var4 >= (-this.b) && var4 <= this.b) {
                if (!this.o.contains(var1)) {
                    this.o.add(var1);
                    var1.a.b(this.b());
                    if (this.t) {
                        var1.a.b((new OPacket28EntityVelocity(this.a.bd, this.a.bp, this.a.bq, this.a.br)));
                    }

                    OItemStack[] var6 = this.a.y();
                    if (var6 != null) {
                        for (int var7 = 0; var7 < var6.length; ++var7) {
                            var1.a.b((new OPacket5PlayerInventory(this.a.bd, var7, var6[var7])));
                        }
                    }

                    if (this.a instanceof OEntityPlayer) {
                        OEntityPlayer var11 = (OEntityPlayer) this.a;
                        if (var11.Z()) {
                            var1.a.b((new OPacket17Sleep(this.a, 0, OMathHelper.b(this.a.bm), OMathHelper.b(this.a.bn), OMathHelper.b(this.a.bo))));
                        }
                    }

                    if (this.a instanceof OEntityLiving) {
                        OEntityLiving var10 = (OEntityLiving) this.a;
                        Iterator var8 = var10.aM().iterator();

                        while (var8.hasNext()) {
                            OPotionEffect var9 = (OPotionEffect) var8.next();
                            var1.a.b((new OPacket41EntityEffect(this.a.bd, var9)));
                        }
                    }
                }
            } else if (this.o.contains(var1)) {
                this.o.remove(var1);
                var1.a.b((new OPacket29DestroyEntity(this.a.bd)));
            }

        }
    }

    public void b(List var1) {
        for (int var2 = 0; var2 < var1.size(); ++var2) {
            this.b((OEntityPlayerMP) var1.get(var2));
        }

    }

    private OPacket b() {
        if (this.a.bE) {
            System.out.println("Fetching addPacket for removed entity");
        }

        if (this.a instanceof OEntityItem) {
            OEntityItem var7 = (OEntityItem) this.a;
            OPacket21PickupSpawn var8 = new OPacket21PickupSpawn(var7);
            var7.bm = var8.b / 32.0D;
            var7.bn = var8.c / 32.0D;
            var7.bo = var8.d / 32.0D;
            return var8;
        } else if (this.a instanceof OEntityPlayerMP) {
            return new OPacket20NamedEntitySpawn((OEntityPlayer) this.a);
        } else {
            if (this.a instanceof OEntityMinecart) {
                OEntityMinecart var1 = (OEntityMinecart) this.a;
                if (var1.minecartType == 0) {
                    return new OPacket23VehicleSpawn(this.a, 10);
                }

                if (var1.minecartType == 1) {
                    return new OPacket23VehicleSpawn(this.a, 11);
                }

                if (var1.minecartType == 2) {
                    return new OPacket23VehicleSpawn(this.a, 12);
                }
            }

            if (this.a instanceof OEntityBoat) {
                return new OPacket23VehicleSpawn(this.a, 1);
            } else if (this.a instanceof OIAnimals) {
                return new OPacket24MobSpawn((OEntityLiving) this.a);
            } else if (this.a instanceof OEntityDragon) {
                return new OPacket24MobSpawn((OEntityLiving) this.a);
            } else if (this.a instanceof OEntityFishHook) {
                return new OPacket23VehicleSpawn(this.a, 90);
            } else if (this.a instanceof OEntityArrow) {
                OEntity var6 = ((OEntityArrow) this.a).c;
                return new OPacket23VehicleSpawn(this.a, 60, var6 != null ? var6.bd : this.a.bd);
            } else if (this.a instanceof OEntitySnowball) {
                return new OPacket23VehicleSpawn(this.a, 61);
            } else if (this.a instanceof OEntityPotion) {
                return new OPacket23VehicleSpawn(this.a, 73, ((OEntityPotion) this.a).f());
            } else if (this.a instanceof OEntityExpBottle) {
                return new OPacket23VehicleSpawn(this.a, 75);
            } else if (this.a instanceof OEntityEnderPearl) {
                return new OPacket23VehicleSpawn(this.a, 65);
            } else if (this.a instanceof OEntityEnderEye) {
                return new OPacket23VehicleSpawn(this.a, 72);
            } else {
                OPacket23VehicleSpawn var2;
                if (this.a instanceof OEntitySmallFireball) {
                    OEntitySmallFireball var5 = (OEntitySmallFireball) this.a;
                    var2 = null;
                    if (var5.a != null) {
                        var2 = new OPacket23VehicleSpawn(this.a, 64, var5.a.bd);
                    } else {
                        var2 = new OPacket23VehicleSpawn(this.a, 64, 0);
                    }

                    var2.e = (int) (var5.b * 8000.0D);
                    var2.f = (int) (var5.c * 8000.0D);
                    var2.g = (int) (var5.d * 8000.0D);
                    return var2;
                } else if (this.a instanceof OEntityFireball) {
                    OEntityFireball var4 = (OEntityFireball) this.a;
                    var2 = null;
                    if (var4.a != null) {
                        var2 = new OPacket23VehicleSpawn(this.a, 63, ((OEntityFireball) this.a).a.bd);
                    } else {
                        var2 = new OPacket23VehicleSpawn(this.a, 63, 0);
                    }

                    var2.e = (int) (var4.b * 8000.0D);
                    var2.f = (int) (var4.c * 8000.0D);
                    var2.g = (int) (var4.d * 8000.0D);
                    return var2;
                } else if (this.a instanceof OEntityEgg) {
                    return new OPacket23VehicleSpawn(this.a, 62);
                } else if (this.a instanceof OEntityTNTPrimed) {
                    return new OPacket23VehicleSpawn(this.a, 50);
                } else if (this.a instanceof OEntityEnderCrystal) {
                    return new OPacket23VehicleSpawn(this.a, 51);
                } else {
                    if (this.a instanceof OEntityFallingSand) {
                        OEntityFallingSand var3 = (OEntityFallingSand) this.a;
                        if (var3.a == OBlock.E.bO) {
                            return new OPacket23VehicleSpawn(this.a, 70);
                        }

                        if (var3.a == OBlock.F.bO) {
                            return new OPacket23VehicleSpawn(this.a, 71);
                        }

                        if (var3.a == OBlock.bK.bO) {
                            return new OPacket23VehicleSpawn(this.a, 74);
                        }
                    }

                    if (this.a instanceof OEntityPainting) {
                        return new OPacket25EntityPainting((OEntityPainting) this.a);
                    } else if (this.a instanceof OEntityXPOrb) {
                        return new OPacket26EntityExpOrb((OEntityXPOrb) this.a);
                    } else {
                        throw new IllegalArgumentException("Don\'t know how to add " + this.a.getClass() + "!");
                    }
                }
            }
        }
    }

    public void c(OEntityPlayerMP var1) {
        if (this.o.contains(var1)) {
            this.o.remove(var1);
            var1.a.b((new OPacket29DestroyEntity(this.a.bd)));
        }

    }
}
