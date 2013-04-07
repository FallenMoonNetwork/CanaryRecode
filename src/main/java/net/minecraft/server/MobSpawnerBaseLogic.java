package net.minecraft.server;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.canarymod.api.CanaryMobSpawnerLogic;
import net.canarymod.api.MobSpawnerLogic;


public abstract class MobSpawnerBaseLogic {

    public int b = 20;
    private String a = "Pig";
    private List e = null;
    private WeightedRandomMinecart f = null;
    public double c;
    public double d = 0.0D;
    public int g = 200; // CanaryMod: private >> public
    public int h = 800; // CanaryMod: private >> public
    public int i = 4; // CanaryMod: private >> public
    private Entity j;
    public int k = 6; // CanaryMod: private >> public
    public int l = 16; // CanaryMod: private >> public
    public int m = 4; // CanaryMod: private >> public

    // CanaryMod: Variable Declaration
    public MobSpawnerLogic logic = (MobSpawnerLogic) new CanaryMobSpawnerLogic(this);
    // CanaryMod: End

    public MobSpawnerBaseLogic() {}

    public String e() {
        if (this.i() == null) {
            if (this.a.equals("Minecart")) {
                this.a = "MinecartRideable";
            }

            return this.a;
        } else {
            return this.i().c;
        }
    }

    public void a(String s0) {
        this.a = s0;
    }

    public boolean f() {
        return this.a().a((double) this.b() + 0.5D, (double) this.c() + 0.5D, (double) this.d() + 0.5D, (double) this.l) != null;
    }

    public void g() {
        if (this.f()) {
            double d0;

            if (this.a().I) {
                double d1 = (double) ((float) this.b() + this.a().s.nextFloat());
                double d2 = (double) ((float) this.c() + this.a().s.nextFloat());

                d0 = (double) ((float) this.d() + this.a().s.nextFloat());
                this.a().a("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                this.a().a("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                if (this.b > 0) {
                    --this.b;
                }

                this.d = this.c;
                this.c = (this.c + (double) (1000.0F / ((float) this.b + 200.0F))) % 360.0D;
            } else {
                if (this.b == -1) {
                    this.j();
                }

                if (this.b > 0) {
                    --this.b;
                    return;
                }

                boolean flag0 = false;

                for (int i0 = 0; i0 < this.i; ++i0) {
                    Entity entity = EntityList.a(this.e(), this.a());

                    if (entity == null) {
                        return;
                    }

                    int i1 = this.a().a(entity.getClass(), AxisAlignedBB.a().a((double) this.b(), (double) this.c(), (double) this.d(), (double) (this.b() + 1), (double) (this.c() + 1), (double) (this.d() + 1)).b((double) (this.m * 2), 4.0D, (double) (this.m * 2))).size();

                    if (i1 >= this.k) {
                        this.j();
                        return;
                    }

                    d0 = (double) this.b() + (this.a().s.nextDouble() - this.a().s.nextDouble()) * (double) this.m;
                    double d3 = (double) (this.c() + this.a().s.nextInt(3) - 1);
                    double d4 = (double) this.d() + (this.a().s.nextDouble() - this.a().s.nextDouble()) * (double) this.m;
                    EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving) entity : null;

                    entity.b(d0, d3, d4, this.a().s.nextFloat() * 360.0F, 0.0F);
                    if (entityliving == null || entityliving.bv()) {
                        this.a(entity);
                        this.a().e(2004, this.b(), this.c(), this.d(), 0);
                        if (entityliving != null) {
                            entityliving.aU();
                        }

                        flag0 = true;
                    }
                }

                if (flag0) {
                    this.j();
                }
            }
        }
    }

    public Entity a(Entity entity) {
        if (this.i() != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            entity.d(nbttagcompound);
            Iterator iterator = this.i().b.c().iterator();

            while (iterator.hasNext()) {
                NBTBase nbtbase = (NBTBase) iterator.next();

                nbttagcompound.a(nbtbase.e(), nbtbase.b());
            }

            entity.f(nbttagcompound);
            if (entity.q != null) {
                entity.q.d(entity);
            }

            NBTTagCompound nbttagcompound1;

            for (Entity entity1 = entity; nbttagcompound.b("Riding"); nbttagcompound = nbttagcompound1) {
                nbttagcompound1 = nbttagcompound.l("Riding");
                Entity entity2 = EntityList.a(nbttagcompound1.i("id"), this.a());

                if (entity2 != null) {
                    NBTTagCompound nbttagcompound2 = new NBTTagCompound();

                    entity2.d(nbttagcompound2);
                    Iterator iterator1 = nbttagcompound1.c().iterator();

                    while (iterator1.hasNext()) {
                        NBTBase nbtbase1 = (NBTBase) iterator1.next();

                        nbttagcompound2.a(nbtbase1.e(), nbtbase1.b());
                    }

                    entity2.f(nbttagcompound2);
                    entity2.b(entity1.u, entity1.v, entity1.w, entity1.A, entity1.B);
                    this.a().d(entity2);
                    entity1.a(entity2);
                }

                entity1 = entity2;
            }
        } else if (entity instanceof EntityLiving && entity.q != null) {
            ((EntityLiving) entity).bJ();
            this.a().d(entity);
        }

        return entity;
    }

    private void j() {
        if (this.h <= this.g) {
            this.b = this.g;
        } else {
            int i0 = this.h - this.g;

            this.b = this.g + this.a().s.nextInt(i0);
        }

        if (this.e != null && this.e.size() > 0) {
            this.a((WeightedRandomMinecart) WeightedRandom.a(this.a().s, (Collection) this.e));
        }

        this.a(1);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.i("EntityId");
        this.b = nbttagcompound.d("Delay");
        if (nbttagcompound.b("SpawnPotentials")) {
            this.e = new ArrayList();
            NBTTagList nbttaglist = nbttagcompound.m("SpawnPotentials");

            for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
                this.e.add(new WeightedRandomMinecart(this, (NBTTagCompound) nbttaglist.b(i0)));
            }
        } else {
            this.e = null;
        }

        if (nbttagcompound.b("SpawnData")) {
            this.a(new WeightedRandomMinecart(this, nbttagcompound.l("SpawnData"), this.a));
        } else {
            this.a((WeightedRandomMinecart) null);
        }

        if (nbttagcompound.b("MinSpawnDelay")) {
            this.g = nbttagcompound.d("MinSpawnDelay");
            this.h = nbttagcompound.d("MaxSpawnDelay");
            this.i = nbttagcompound.d("SpawnCount");
        }

        if (nbttagcompound.b("MaxNearbyEntities")) {
            this.k = nbttagcompound.d("MaxNearbyEntities");
            this.l = nbttagcompound.d("RequiredPlayerRange");
        }

        if (nbttagcompound.b("SpawnRange")) {
            this.m = nbttagcompound.d("SpawnRange");
        }

        if (this.a() != null && this.a().I) {
            this.j = null;
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("EntityId", this.e());
        nbttagcompound.a("Delay", (short) this.b);
        nbttagcompound.a("MinSpawnDelay", (short) this.g);
        nbttagcompound.a("MaxSpawnDelay", (short) this.h);
        nbttagcompound.a("SpawnCount", (short) this.i);
        nbttagcompound.a("MaxNearbyEntities", (short) this.k);
        nbttagcompound.a("RequiredPlayerRange", (short) this.l);
        nbttagcompound.a("SpawnRange", (short) this.m);
        if (this.i() != null) {
            nbttagcompound.a("SpawnData", (NBTTagCompound) this.i().b.b());
        }

        if (this.i() != null || this.e != null && this.e.size() > 0) {
            NBTTagList nbttaglist = new NBTTagList();

            if (this.e != null && this.e.size() > 0) {
                Iterator iterator = this.e.iterator();

                while (iterator.hasNext()) {
                    WeightedRandomMinecart weightedrandomminecart = (WeightedRandomMinecart) iterator.next();

                    nbttaglist.a((NBTBase) weightedrandomminecart.a());
                }
            } else {
                nbttaglist.a((NBTBase) this.i().a());
            }

            nbttagcompound.a("SpawnPotentials", (NBTBase) nbttaglist);
        }
    }

    public boolean b(int i0) {
        if (i0 == 1 && this.a().I) {
            this.b = this.g;
            return true;
        } else {
            return false;
        }
    }

    public WeightedRandomMinecart i() {
        return this.f;
    }

    public void a(WeightedRandomMinecart weightedrandomminecart) {
        this.f = weightedrandomminecart;
    }

    public abstract void a(int i0);

    public abstract World a();

    public abstract int b();

    public abstract int c();

    public abstract int d();
}
