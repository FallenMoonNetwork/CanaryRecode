package net.minecraft.server;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.canarymod.api.CanaryEntityTrackerEntry;


public class EntityTrackerEntry {

    public Entity a;
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
    private Entity v;
    private boolean w = false;
    public boolean n = false;
    public Set o = new HashSet();

    private CanaryEntityTrackerEntry canaryEntry;

    public EntityTrackerEntry(Entity entity, int i0, int i1, boolean flag0) {
        this.a = entity;
        this.b = i0;
        this.c = i1;
        this.t = flag0;
        this.d = MathHelper.c(entity.u * 32.0D);
        this.e = MathHelper.c(entity.v * 32.0D);
        this.f = MathHelper.c(entity.w * 32.0D);
        this.g = MathHelper.d(entity.A * 256.0F / 360.0F);
        this.h = MathHelper.d(entity.B * 256.0F / 360.0F);
        this.i = MathHelper.d(entity.ao() * 256.0F / 360.0F);

        canaryEntry = new CanaryEntityTrackerEntry(this);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof EntityTrackerEntry ? ((EntityTrackerEntry) object).a.k == this.a.k : false;
    }

    @Override
    public int hashCode() {
        return this.a.k;
    }

    public void a(List list) {
        this.n = false;
        if (!this.s || this.a.e(this.p, this.q, this.r) > 16.0D) {
            this.p = this.a.u;
            this.q = this.a.v;
            this.r = this.a.w;
            this.s = true;
            this.n = true;
            this.b(list);
        }

        if (this.v != this.a.o || this.a.o != null && this.m % 60 == 0) {
            this.v = this.a.o;
            this.a((Packet) (new Packet39AttachEntity(this.a, this.a.o)));
        }

        if (this.a instanceof EntityItemFrame && this.m % 10 == 0) {
            EntityItemFrame i04 = (EntityItemFrame) this.a;
            ItemStack i05 = i04.i();

            if (i05 != null && i05.b() instanceof ItemMap) {
                MapData i07 = Item.be.a(i05, this.a.q);
                Iterator i10 = list.iterator();

                while (i10.hasNext()) {
                    EntityPlayer i11 = (EntityPlayer) i10.next();
                    EntityPlayerMP i12 = (EntityPlayerMP) i11;

                    i07.a(i12, i05);
                    if (i12.a.e() <= 5) {
                        Packet i13 = Item.be.c(i05, this.a.q, i12);

                        if (i13 != null) {
                            i12.a.b(i13);
                        }
                    }
                }
            }

            DataWatcher i09 = this.a.u();

            if (i09.a()) {
                this.b((Packet) (new Packet40EntityMetadata(this.a.k, i09, false)));
            }
        } else if (this.m % this.c == 0 || this.a.an || this.a.u().a()) {
            int i0;
            int i1;

            if (this.a.o == null) {
                ++this.u;
                i0 = this.a.at.a(this.a.u);
                i1 = MathHelper.c(this.a.v * 32.0D);
                int i2 = this.a.at.a(this.a.w);
                int i3 = MathHelper.d(this.a.A * 256.0F / 360.0F);
                int i4 = MathHelper.d(this.a.B * 256.0F / 360.0F);
                int i5 = i0 - this.d;
                int i6 = i1 - this.e;
                int i7 = i2 - this.f;
                Object object = null;
                boolean flag0 = Math.abs(i5) >= 4 || Math.abs(i6) >= 4 || Math.abs(i7) >= 4 || this.m % 60 == 0;
                boolean flag1 = Math.abs(i3 - this.g) >= 4 || Math.abs(i4 - this.h) >= 4;

                if (this.m > 0) {
                    if (i5 >= -128 && i5 < 128 && i6 >= -128 && i6 < 128 && i7 >= -128 && i7 < 128 && this.u <= 400 && !this.w) {
                        if (flag0 && flag1) {
                            object = new Packet33RelEntityMoveLook(this.a.k, (byte) i5, (byte) i6, (byte) i7, (byte) i3, (byte) i4);
                        } else if (flag0) {
                            object = new Packet31RelEntityMove(this.a.k, (byte) i5, (byte) i6, (byte) i7);
                        } else if (flag1) {
                            object = new Packet32EntityLook(this.a.k, (byte) i3, (byte) i4);
                        }
                    } else {
                        this.u = 0;
                        object = new Packet34EntityTeleport(this.a.k, i0, i1, i2, (byte) i3, (byte) i4);
                    }
                }

                if (this.t) {
                    double d0 = this.a.x - this.j;
                    double d1 = this.a.y - this.k;
                    double d2 = this.a.z - this.l;
                    double d3 = 0.02D;
                    double d4 = d0 * d0 + d1 * d1 + d2 * d2;

                    if (d4 > d3 * d3 || d4 > 0.0D && this.a.x == 0.0D && this.a.y == 0.0D && this.a.z == 0.0D) {
                        this.j = this.a.x;
                        this.k = this.a.y;
                        this.l = this.a.z;
                        this.a((Packet) (new Packet28EntityVelocity(this.a.k, this.j, this.k, this.l)));
                    }
                }

                if (object != null) {
                    this.a((Packet) object);
                }

                DataWatcher datawatcher1 = this.a.u();

                if (datawatcher1.a()) {
                    this.b((Packet) (new Packet40EntityMetadata(this.a.k, datawatcher1, false)));
                }

                if (flag0) {
                    this.d = i0;
                    this.e = i1;
                    this.f = i2;
                }

                if (flag1) {
                    this.g = i3;
                    this.h = i4;
                }

                this.w = false;
            } else {
                i0 = MathHelper.d(this.a.A * 256.0F / 360.0F);
                i1 = MathHelper.d(this.a.B * 256.0F / 360.0F);
                boolean flag2 = Math.abs(i0 - this.g) >= 4 || Math.abs(i1 - this.h) >= 4;

                if (flag2) {
                    this.a((Packet) (new Packet32EntityLook(this.a.k, (byte) i0, (byte) i1)));
                    this.g = i0;
                    this.h = i1;
                }

                this.d = this.a.at.a(this.a.u);
                this.e = MathHelper.c(this.a.v * 32.0D);
                this.f = this.a.at.a(this.a.w);
                DataWatcher datawatcher2 = this.a.u();

                if (datawatcher2.a()) {
                    this.b((Packet) (new Packet40EntityMetadata(this.a.k, datawatcher2, false)));
                }

                this.w = true;
            }

            i0 = MathHelper.d(this.a.ao() * 256.0F / 360.0F);
            if (Math.abs(i0 - this.i) >= 4) {
                this.a((Packet) (new Packet35EntityHeadRotation(this.a.k, (byte) i0)));
                this.i = i0;
            }

            this.a.an = false;
        }

        ++this.m;
        if (this.a.J) {
            this.b((Packet) (new Packet28EntityVelocity(this.a)));
            this.a.J = false;
        }
    }

    public void a(Packet packet) {
        Iterator iterator = this.o.iterator();

        while (iterator.hasNext()) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) iterator.next();

            entityplayermp.a.b(packet);
        }
    }

    public void b(Packet packet) {
        this.a(packet);
        if (this.a instanceof EntityPlayerMP) {
            ((EntityPlayerMP) this.a).a.b(packet);
        }
    }

    public void a() {
        Iterator iterator = this.o.iterator();

        while (iterator.hasNext()) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) iterator.next();

            entityplayermp.g.add(Integer.valueOf(this.a.k));
        }
    }

    public void a(EntityPlayerMP entityplayermp) {
        if (this.o.contains(entityplayermp)) {
            entityplayermp.g.add(Integer.valueOf(this.a.k));
            this.o.remove(entityplayermp);
        }
    }

    public void b(EntityPlayerMP entityplayermp) {
        if (entityplayermp != this.a) {
            double d0 = entityplayermp.u - (double) (this.d / 32);
            double d1 = entityplayermp.w - (double) (this.f / 32);

            if (d0 >= (double) (-this.b) && d0 <= (double) this.b && d1 >= (double) (-this.b) && d1 <= (double) this.b) {
                if (!this.o.contains(entityplayermp) && (this.d(entityplayermp) || this.a.p)) {
                    this.o.add(entityplayermp);
                    Packet packet = this.b();

                    entityplayermp.a.b(packet);
                    if (!this.a.u().d()) {
                        entityplayermp.a.b(new Packet40EntityMetadata(this.a.k, this.a.u(), true));
                    }

                    this.j = this.a.x;
                    this.k = this.a.y;
                    this.l = this.a.z;
                    if (this.t && !(packet instanceof Packet24MobSpawn)) {
                        entityplayermp.a.b(new Packet28EntityVelocity(this.a.k, this.a.x, this.a.y, this.a.z));
                    }

                    if (this.a.o != null) {
                        entityplayermp.a.b(new Packet39AttachEntity(this.a, this.a.o));
                    }

                    if (this.a instanceof EntityLiving) {
                        for (int i0 = 0; i0 < 5; ++i0) {
                            ItemStack itemstack = ((EntityLiving) this.a).p(i0);

                            if (itemstack != null) {
                                entityplayermp.a.b(new Packet5PlayerInventory(this.a.k, i0, itemstack));
                            }
                        }
                    }

                    if (this.a instanceof EntityPlayer) {
                        EntityPlayer entityplayer = (EntityPlayer) this.a;

                        if (entityplayer.bz()) {
                            entityplayermp.a.b(new Packet17Sleep(this.a, 0, MathHelper.c(this.a.u), MathHelper.c(this.a.v), MathHelper.c(this.a.w)));
                        }
                    }

                    if (this.a instanceof EntityLiving) {
                        EntityLiving entityliving = (EntityLiving) this.a;
                        Iterator iterator = entityliving.bC().iterator();

                        while (iterator.hasNext()) {
                            PotionEffect potioneffect = (PotionEffect) iterator.next();

                            entityplayermp.a.b(new Packet41EntityEffect(this.a.k, potioneffect));
                        }
                    }
                }
            } else if (this.o.contains(entityplayermp)) {
                this.o.remove(entityplayermp);
                entityplayermp.g.add(Integer.valueOf(this.a.k));
            }
        }
    }

    private boolean d(EntityPlayerMP entityplayermp) {
        return entityplayermp.o().r().a(entityplayermp, this.a.aj, this.a.al);
    }

    public void b(List list) {
        for (int i0 = 0; i0 < list.size(); ++i0) {
            this.b((EntityPlayerMP) list.get(i0));
        }
    }

    private Packet b() {
        if (this.a.M) {
            this.a.q.W().b("Fetching addPacket for removed entity");
        }

        if (this.a instanceof EntityItem) {
            return new Packet23VehicleSpawn(this.a, 2, 1);
        } else if (this.a instanceof EntityPlayerMP) {
            return new Packet20NamedEntitySpawn((EntityPlayer) this.a);
        } else if (this.a instanceof EntityMinecart) {
            EntityMinecart entityminecart = (EntityMinecart) this.a;

            return new Packet23VehicleSpawn(this.a, 10, entityminecart.l());
        } else if (this.a instanceof EntityBoat) {
            return new Packet23VehicleSpawn(this.a, 1);
        } else if (!(this.a instanceof IAnimals) && !(this.a instanceof EntityDragon)) {
            if (this.a instanceof EntityFishHook) {
                EntityPlayer entityplayer = ((EntityFishHook) this.a).b;

                return new Packet23VehicleSpawn(this.a, 90, entityplayer != null ? entityplayer.k : this.a.k);
            } else if (this.a instanceof EntityArrow) {
                Entity entity = ((EntityArrow) this.a).c;

                return new Packet23VehicleSpawn(this.a, 60, entity != null ? entity.k : this.a.k);
            } else if (this.a instanceof EntitySnowball) {
                return new Packet23VehicleSpawn(this.a, 61);
            } else if (this.a instanceof EntityPotion) {
                return new Packet23VehicleSpawn(this.a, 73, ((EntityPotion) this.a).i());
            } else if (this.a instanceof EntityExpBottle) {
                return new Packet23VehicleSpawn(this.a, 75);
            } else if (this.a instanceof EntityEnderPearl) {
                return new Packet23VehicleSpawn(this.a, 65);
            } else if (this.a instanceof EntityEnderEye) {
                return new Packet23VehicleSpawn(this.a, 72);
            } else if (this.a instanceof EntityFireworkRocket) {
                return new Packet23VehicleSpawn(this.a, 76);
            } else {
                Packet23VehicleSpawn packet23vehiclespawn;

                if (this.a instanceof EntityFireball) {
                    EntityFireball entityfireball = (EntityFireball) this.a;

                    packet23vehiclespawn = null;
                    byte b0 = 63;

                    if (this.a instanceof EntitySmallFireball) {
                        b0 = 64;
                    } else if (this.a instanceof EntityWitherSkull) {
                        b0 = 66;
                    }

                    if (entityfireball.a != null) {
                        packet23vehiclespawn = new Packet23VehicleSpawn(this.a, b0, ((EntityFireball) this.a).a.k);
                    } else {
                        packet23vehiclespawn = new Packet23VehicleSpawn(this.a, b0, 0);
                    }

                    packet23vehiclespawn.e = (int) (entityfireball.b * 8000.0D);
                    packet23vehiclespawn.f = (int) (entityfireball.c * 8000.0D);
                    packet23vehiclespawn.g = (int) (entityfireball.d * 8000.0D);
                    return packet23vehiclespawn;
                } else if (this.a instanceof EntityEgg) {
                    return new Packet23VehicleSpawn(this.a, 62);
                } else if (this.a instanceof EntityTNTPrimed) {
                    return new Packet23VehicleSpawn(this.a, 50);
                } else if (this.a instanceof EntityEnderCrystal) {
                    return new Packet23VehicleSpawn(this.a, 51);
                } else if (this.a instanceof EntityFallingSand) {
                    EntityFallingSand entityfallingsand = (EntityFallingSand) this.a;

                    return new Packet23VehicleSpawn(this.a, 70, entityfallingsand.a | entityfallingsand.b << 16);
                } else if (this.a instanceof EntityPainting) {
                    return new Packet25EntityPainting((EntityPainting) this.a);
                } else if (this.a instanceof EntityItemFrame) {
                    EntityItemFrame entityitemframe = (EntityItemFrame) this.a;

                    packet23vehiclespawn = new Packet23VehicleSpawn(this.a, 71, entityitemframe.a);
                    packet23vehiclespawn.b = MathHelper.d((float) (entityitemframe.b * 32));
                    packet23vehiclespawn.c = MathHelper.d((float) (entityitemframe.c * 32));
                    packet23vehiclespawn.d = MathHelper.d((float) (entityitemframe.d * 32));
                    return packet23vehiclespawn;
                } else if (this.a instanceof EntityXPOrb) {
                    return new Packet26EntityExpOrb((EntityXPOrb) this.a);
                } else {
                    throw new IllegalArgumentException("Don\'t know how to add " + this.a.getClass() + "!");
                }
            }
        } else {
            this.i = MathHelper.d(this.a.ao() * 256.0F / 360.0F);
            return new Packet24MobSpawn((EntityLiving) this.a);
        }
    }

    public void c(EntityPlayerMP entityplayermp) {
        if (this.o.contains(entityplayermp)) {
            this.o.remove(entityplayermp);
            entityplayermp.g.add(Integer.valueOf(this.a.k));
        }
    }

    /**
     * get canaryMod EntityTracker entry
     * @return
     */
    public CanaryEntityTrackerEntry getCanaryTrackerEntry() {
        return canaryEntry;
    }
}
