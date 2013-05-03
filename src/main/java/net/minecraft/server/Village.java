package net.minecraft.server;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import net.canarymod.api.world.CanaryVillage;


public class Village {

    private World a;
    private final List b = new ArrayList();
    private final ChunkCoordinates c = new ChunkCoordinates(0, 0, 0);
    private final ChunkCoordinates d = new ChunkCoordinates(0, 0, 0);
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    public int i; // CanaryMod: private => public
    private TreeMap j = new TreeMap();
    private List k = new ArrayList();
    public int l = 0; // CanaryMod: private => public

    // CanaryMod
    private CanaryVillage ville;

    //

    public Village() {
        this.ville = new CanaryVillage(this); // CanaryMod: Wrap Village
    }

    public Village(World world) {
        this.a = world;
        this.ville = new CanaryVillage(this); // CanaryMod: Wrap Village
    }

    public void a(World world) {
        this.a = world;
    }

    public void a(int i0) {
        this.g = i0;
        this.m();
        this.l();
        if (i0 % 20 == 0) {
            this.k();
        }

        if (i0 % 30 == 0) {
            this.j();
        }

        int i1 = this.h / 10;

        if (this.l < i1 && this.b.size() > 20 && this.a.s.nextInt(7000) == 0) {
            Vec3 vec3 = this.a(MathHelper.d((float) this.d.a), MathHelper.d((float) this.d.b), MathHelper.d((float) this.d.c), 2, 4, 2);

            if (vec3 != null) {
                EntityIronGolem entityirongolem = new EntityIronGolem(this.a);

                entityirongolem.b(vec3.c, vec3.d, vec3.e);
                this.a.d((Entity) entityirongolem);
                ++this.l;
            }
        }
    }

    private Vec3 a(int i0, int i1, int i2, int i3, int i4, int i5) {
        for (int i6 = 0; i6 < 10; ++i6) {
            int i7 = i0 + this.a.s.nextInt(16) - 8;
            int i8 = i1 + this.a.s.nextInt(6) - 3;
            int i9 = i2 + this.a.s.nextInt(16) - 8;

            if (this.a(i7, i8, i9) && this.b(i7, i8, i9, i3, i4, i5)) {
                return this.a.U().a((double) i7, (double) i8, (double) i9);
            }
        }

        return null;
    }

    private boolean b(int i0, int i1, int i2, int i3, int i4, int i5) {
        if (!this.a.w(i0, i1 - 1, i2)) {
            return false;
        } else {
            int i6 = i0 - i3 / 2;
            int i7 = i2 - i5 / 2;

            for (int i8 = i6; i8 < i6 + i3; ++i8) {
                for (int i9 = i1; i9 < i1 + i4; ++i9) {
                    for (int i10 = i7; i10 < i7 + i5; ++i10) {
                        if (this.a.u(i8, i9, i10)) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }

    private void j() {
        List list = this.a.a(EntityIronGolem.class, AxisAlignedBB.a().a((double) (this.d.a - this.e), (double) (this.d.b - 4), (double) (this.d.c - this.e), (double) (this.d.a + this.e), (double) (this.d.b + 4), (double) (this.d.c + this.e)));

        this.l = list.size();
    }

    private void k() {
        List list = this.a.a(EntityVillager.class, AxisAlignedBB.a().a((double) (this.d.a - this.e), (double) (this.d.b - 4), (double) (this.d.c - this.e), (double) (this.d.a + this.e), (double) (this.d.b + 4), (double) (this.d.c + this.e)));

        this.h = list.size();
        if (this.h == 0) {
            this.j.clear();
        }
    }

    public ChunkCoordinates a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.b.size();
    }

    public int d() {
        return this.g - this.f;
    }

    public int e() {
        return this.h;
    }

    public boolean a(int i0, int i1, int i2) {
        return this.d.e(i0, i1, i2) < (float) (this.e * this.e);
    }

    public List f() {
        return this.b;
    }

    public VillageDoorInfo b(int i0, int i1, int i2) {
        VillageDoorInfo villagedoorinfo = null;
        int i3 = Integer.MAX_VALUE;
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            VillageDoorInfo villagedoorinfo1 = (VillageDoorInfo) iterator.next();
            int i4 = villagedoorinfo1.b(i0, i1, i2);

            if (i4 < i3) {
                villagedoorinfo = villagedoorinfo1;
                i3 = i4;
            }
        }

        return villagedoorinfo;
    }

    public VillageDoorInfo c(int i0, int i1, int i2) {
        VillageDoorInfo villagedoorinfo = null;
        int i3 = Integer.MAX_VALUE;
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            VillageDoorInfo villagedoorinfo1 = (VillageDoorInfo) iterator.next();
            int i4 = villagedoorinfo1.b(i0, i1, i2);

            if (i4 > 256) {
                i4 *= 1000;
            } else {
                i4 = villagedoorinfo1.f();
            }

            if (i4 < i3) {
                villagedoorinfo = villagedoorinfo1;
                i3 = i4;
            }
        }

        return villagedoorinfo;
    }

    public VillageDoorInfo e(int i0, int i1, int i2) {
        if (this.d.e(i0, i1, i2) > (float) (this.e * this.e)) {
            return null;
        } else {
            Iterator iterator = this.b.iterator();

            VillageDoorInfo villagedoorinfo;

            do {
                if (!iterator.hasNext()) {
                    return null;
                }

                villagedoorinfo = (VillageDoorInfo) iterator.next();
            } while (villagedoorinfo.a != i0 || villagedoorinfo.c != i2 || Math.abs(villagedoorinfo.b - i1) > 1);

            return villagedoorinfo;
        }
    }

    public void a(VillageDoorInfo villagedoorinfo) {
        this.b.add(villagedoorinfo);
        this.c.a += villagedoorinfo.a;
        this.c.b += villagedoorinfo.b;
        this.c.c += villagedoorinfo.c;
        this.n();
        this.f = villagedoorinfo.f;
    }

    public boolean g() {
        return this.b.isEmpty();
    }

    public void a(EntityLiving entityliving) {
        Iterator iterator = this.k.iterator();

        VillageAgressor villageagressor;

        do {
            if (!iterator.hasNext()) {
                this.k.add(new VillageAgressor(this, entityliving, this.g));
                return;
            }

            villageagressor = (VillageAgressor) iterator.next();
        } while (villageagressor.a != entityliving);

        villageagressor.b = this.g;
    }

    public EntityLiving b(EntityLiving entityliving) {
        double d0 = Double.MAX_VALUE;
        VillageAgressor villageagressor = null;

        for (int i0 = 0; i0 < this.k.size(); ++i0) {
            VillageAgressor villageagressor1 = (VillageAgressor) this.k.get(i0);
            double d1 = villageagressor1.a.e(entityliving);

            if (d1 <= d0) {
                villageagressor = villageagressor1;
                d0 = d1;
            }
        }

        return villageagressor != null ? villageagressor.a : null;
    }

    public EntityPlayer c(EntityLiving entityliving) {
        double d0 = Double.MAX_VALUE;
        EntityPlayer entityplayer = null;
        Iterator iterator = this.j.keySet().iterator();

        while (iterator.hasNext()) {
            String s0 = (String) iterator.next();

            if (this.d(s0)) {
                EntityPlayer entityplayer1 = this.a.a(s0);

                if (entityplayer1 != null) {
                    double d1 = entityplayer1.e(entityliving);

                    if (d1 <= d0) {
                        entityplayer = entityplayer1;
                        d0 = d1;
                    }
                }
            }
        }

        return entityplayer;
    }

    private void l() {
        Iterator iterator = this.k.iterator();

        while (iterator.hasNext()) {
            VillageAgressor villageagressor = (VillageAgressor) iterator.next();

            if (!villageagressor.a.R() || Math.abs(this.g - villageagressor.b) > 300) {
                iterator.remove();
            }
        }
    }

    private void m() {
        boolean flag0 = false;
        boolean flag1 = this.a.s.nextInt(50) == 0;
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo) iterator.next();

            if (flag1) {
                villagedoorinfo.d();
            }

            if (!this.f(villagedoorinfo.a, villagedoorinfo.b, villagedoorinfo.c) || Math.abs(this.g - villagedoorinfo.f) > 1200) {
                this.c.a -= villagedoorinfo.a;
                this.c.b -= villagedoorinfo.b;
                this.c.c -= villagedoorinfo.c;
                flag0 = true;
                villagedoorinfo.g = true;
                iterator.remove();
            }
        }

        if (flag0) {
            this.n();
        }
    }

    private boolean f(int i0, int i1, int i2) {
        int i3 = this.a.a(i0, i1, i2);

        return i3 <= 0 ? false : i3 == Block.aI.cz;
    }

    private void n() {
        int i0 = this.b.size();

        if (i0 == 0) {
            this.d.b(0, 0, 0);
            this.e = 0;
        } else {
            this.d.b(this.c.a / i0, this.c.b / i0, this.c.c / i0);
            int i1 = 0;

            VillageDoorInfo villagedoorinfo;

            for (Iterator iterator = this.b.iterator(); iterator.hasNext(); i1 = Math.max(villagedoorinfo.b(this.d.a, this.d.b, this.d.c), i1)) {
                villagedoorinfo = (VillageDoorInfo) iterator.next();
            }

            this.e = Math.max(32, (int) Math.sqrt((double) i1) + 1);
        }
    }

    public int a(String s0) {
        Integer integer = (Integer) this.j.get(s0);

        return integer != null ? integer.intValue() : 0;
    }

    public int a(String s0, int i0) {
        int i1 = this.a(s0);
        int i2 = MathHelper.a(i1 + i0, -30, 10);

        this.j.put(s0, Integer.valueOf(i2));
        return i2;
    }

    public boolean d(String s0) {
        return this.a(s0) <= -15;
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.h = nbttagcompound.e("PopSize");
        this.e = nbttagcompound.e("Radius");
        this.l = nbttagcompound.e("Golems");
        this.f = nbttagcompound.e("Stable");
        this.g = nbttagcompound.e("Tick");
        this.i = nbttagcompound.e("MTick");
        this.d.a = nbttagcompound.e("CX");
        this.d.b = nbttagcompound.e("CY");
        this.d.c = nbttagcompound.e("CZ");
        this.c.a = nbttagcompound.e("ACX");
        this.c.b = nbttagcompound.e("ACY");
        this.c.c = nbttagcompound.e("ACZ");
        NBTTagList nbttaglist = nbttagcompound.m("Doors");

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
            VillageDoorInfo villagedoorinfo = new VillageDoorInfo(nbttagcompound1.e("X"), nbttagcompound1.e("Y"), nbttagcompound1.e("Z"), nbttagcompound1.e("IDX"), nbttagcompound1.e("IDZ"), nbttagcompound1.e("TS"));

            this.b.add(villagedoorinfo);
        }

        NBTTagList nbttaglist1 = nbttagcompound.m("Players");

        for (int i1 = 0; i1 < nbttaglist1.c(); ++i1) {
            NBTTagCompound nbttagcompound2 = (NBTTagCompound) nbttaglist1.b(i1);

            this.j.put(nbttagcompound2.i("Name"), Integer.valueOf(nbttagcompound2.e("S")));
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("PopSize", this.h);
        nbttagcompound.a("Radius", this.e);
        nbttagcompound.a("Golems", this.l);
        nbttagcompound.a("Stable", this.f);
        nbttagcompound.a("Tick", this.g);
        nbttagcompound.a("MTick", this.i);
        nbttagcompound.a("CX", this.d.a);
        nbttagcompound.a("CY", this.d.b);
        nbttagcompound.a("CZ", this.d.c);
        nbttagcompound.a("ACX", this.c.a);
        nbttagcompound.a("ACY", this.c.b);
        nbttagcompound.a("ACZ", this.c.c);
        NBTTagList nbttaglist = new NBTTagList("Doors");
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo) iterator.next();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound("Door");

            nbttagcompound1.a("X", villagedoorinfo.a);
            nbttagcompound1.a("Y", villagedoorinfo.b);
            nbttagcompound1.a("Z", villagedoorinfo.c);
            nbttagcompound1.a("IDX", villagedoorinfo.d);
            nbttagcompound1.a("IDZ", villagedoorinfo.e);
            nbttagcompound1.a("TS", villagedoorinfo.f);
            nbttaglist.a((NBTBase) nbttagcompound1);
        }

        nbttagcompound.a("Doors", (NBTBase) nbttaglist);
        NBTTagList nbttaglist1 = new NBTTagList("Players");
        Iterator iterator1 = this.j.keySet().iterator();

        while (iterator1.hasNext()) {
            String s0 = (String) iterator1.next();
            NBTTagCompound nbttagcompound2 = new NBTTagCompound(s0);

            nbttagcompound2.a("Name", s0);
            nbttagcompound2.a("S", ((Integer) this.j.get(s0)).intValue());
            nbttaglist1.a((NBTBase) nbttagcompound2);
        }

        nbttagcompound.a("Players", (NBTBase) nbttaglist1);
    }

    public void h() {
        this.i = this.g;
    }

    public boolean i() {
        return this.i == 0 || this.g - this.i >= 3600;
    }

    public void b(int i0) {
        Iterator iterator = this.j.keySet().iterator();

        while (iterator.hasNext()) {
            String s0 = (String) iterator.next();

            this.a(s0, i0);
        }
    }

    // CanaryMod
    public CanaryVillage getCanaryVillage() {
        return ville;
    }
    //
}
