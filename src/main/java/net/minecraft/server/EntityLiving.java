package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class EntityLiving extends EntityLivingBase {

    public int a_;
    protected int b;
    private EntityLookHelper h;
    private EntityMoveHelper i;
    private EntityJumpHelper j;
    private EntityBodyHelper bn;
    private PathNavigate bo;
    protected final EntityAITasks c;
    protected final EntityAITasks d;
    private EntityLivingBase bp;
    private EntitySenses bq;
    private ItemStack[] br = new ItemStack[5];
    protected float[] e = new float[5];
    private boolean bs;
    private boolean bt;
    protected float f;
    private Entity bu;
    protected int g;
    private boolean bv;
    private Entity bw;
    private NBTTagCompound bx;

    public EntityLiving(World world) {
        super(world);
        this.c = new EntityAITasks(world != null && world.C != null ? world.C : null);
        this.d = new EntityAITasks(world != null && world.C != null ? world.C : null);
        this.h = new EntityLookHelper(this);
        this.i = new EntityMoveHelper(this);
        this.j = new EntityJumpHelper(this);
        this.bn = new EntityBodyHelper(this);
        this.bo = new PathNavigate(this, world);
        this.bq = new EntitySenses(this);

        for (int i0 = 0; i0 < this.e.length; ++i0) {
            this.e[i0] = 0.085F;
        }
    }

    protected void ax() {
        super.ax();
        this.aT().b(SharedMonsterAttributes.b).a(16.0D);
    }

    public EntityLookHelper h() {
        return this.h;
    }

    public EntityMoveHelper i() {
        return this.i;
    }

    public EntityJumpHelper j() {
        return this.j;
    }

    public PathNavigate k() {
        return this.bo;
    }

    public EntitySenses l() {
        return this.bq;
    }

    public EntityLivingBase m() {
        return this.bp;
    }

    public void c(EntityLivingBase entitylivingbase) {
        this.bp = entitylivingbase;
    }

    public boolean a(Class oclass0) {
        return EntityCreeper.class != oclass0 && EntityGhast.class != oclass0;
    }

    public void n() {}

    protected void a() {
        super.a();
        this.ah.a(11, Byte.valueOf((byte) 0));
        this.ah.a(10, "");
    }

    public int o() {
        return 80;
    }

    public void p() {
        String s0 = this.r();

        if (s0 != null) {
            this.a(s0, this.aW(), this.aX());
        }
    }

    public void x() {
        super.x();
        this.q.C.a("mobBaseTick");
        if (this.R() && this.ab.nextInt(1000) < this.a_++) {
            this.a_ = -this.o();
            this.p();
        }
        // MERGE: Suffocation hook logic here?
        this.q.C.b();
    }

    protected int e(EntityPlayer entityplayer) {
        if (this.b > 0) {
            int i0 = this.b;
            ItemStack[] aitemstack = this.ac();

            for (int i1 = 0; i1 < aitemstack.length; ++i1) {
                if (aitemstack[i1] != null && this.e[i1] <= 1.0F) {
                    i0 += 1 + this.ab.nextInt(3);
                }
            }

            return i0;
        } else {
            return this.b;
        }
    }

    public void q() {
        for (int i0 = 0; i0 < 20; ++i0) {
            double d0 = this.ab.nextGaussian() * 0.02D;
            double d1 = this.ab.nextGaussian() * 0.02D;
            double d2 = this.ab.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.q.a("explode", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d0 * d3, this.v + (double) (this.ab.nextFloat() * this.P) - d1 * d3, this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d2 * d3, d0, d1, d2);
        }
    }

    public void l_() {
        super.l_();
        if (!this.q.I) {
            this.bB();
        }
    }

    protected float f(float f0, float f1) {
        if (this.bb()) {
            this.bn.a();
            return f1;
        } else {
            return super.f(f0, f1);
        }
    }

    protected String r() {
        return null;
    }

    protected int s() {
        return 0;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.s();

        if (i1 > 0) {
            int i2 = this.ab.nextInt(3);

            if (i0 > 0) {
                i2 += this.ab.nextInt(i0 + 1);
            }

            for (int i3 = 0; i3 < i2; ++i3) {
                this.b(i1, 1);
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("CanPickUpLoot", this.bz());
        nbttagcompound.a("PersistenceRequired", this.bt);
        NBTTagList nbttaglist = new NBTTagList();

        NBTTagCompound nbttagcompound1;

        for (int i0 = 0; i0 < this.br.length; ++i0) {
            nbttagcompound1 = new NBTTagCompound();
            if (this.br[i0] != null) {
                this.br[i0].b(nbttagcompound1);
            }

            nbttaglist.a((NBTBase) nbttagcompound1);
        }

        nbttagcompound.a("Equipment", (NBTBase) nbttaglist);
        NBTTagList nbttaglist1 = new NBTTagList();

        for (int i1 = 0; i1 < this.e.length; ++i1) {
            nbttaglist1.a((NBTBase) (new NBTTagFloat(i1 + "", this.e[i1])));
        }

        nbttagcompound.a("DropChances", (NBTBase) nbttaglist1);
        nbttagcompound.a("CustomName", this.bw());
        nbttagcompound.a("CustomNameVisible", this.by());
        nbttagcompound.a("Leashed", this.bv);
        if (this.bw != null) {
            nbttagcompound1 = new NBTTagCompound("Leash");
            if (this.bw instanceof EntityLivingBase) {
                nbttagcompound1.a("UUIDMost", this.bw.au().getMostSignificantBits());
                nbttagcompound1.a("UUIDLeast", this.bw.au().getLeastSignificantBits());
            } else if (this.bw instanceof EntityHanging) {
                EntityHanging entityhanging = (EntityHanging) this.bw;

                nbttagcompound1.a("X", entityhanging.b);
                nbttagcompound1.a("Y", entityhanging.c);
                nbttagcompound1.a("Z", entityhanging.d);
            }

            nbttagcompound.a("Leash", (NBTBase) nbttagcompound1);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.h(nbttagcompound.n("CanPickUpLoot"));
        this.bt = nbttagcompound.n("PersistenceRequired");
        if (nbttagcompound.b("CustomName") && nbttagcompound.i("CustomName").length() > 0) {
            this.a(nbttagcompound.i("CustomName"));
        }

        this.g(nbttagcompound.n("CustomNameVisible"));
        NBTTagList nbttaglist;
        int i0;

        if (nbttagcompound.b("Equipment")) {
            nbttaglist = nbttagcompound.m("Equipment");

            for (i0 = 0; i0 < this.br.length; ++i0) {
                this.br[i0] = ItemStack.a((NBTTagCompound) nbttaglist.b(i0));
            }
        }

        if (nbttagcompound.b("DropChances")) {
            nbttaglist = nbttagcompound.m("DropChances");

            for (i0 = 0; i0 < nbttaglist.c(); ++i0) {
                this.e[i0] = ((NBTTagFloat) nbttaglist.b(i0)).a;
            }
        }

        this.bv = nbttagcompound.n("Leashed");
        if (this.bv && nbttagcompound.b("Leash")) {
            this.bx = nbttagcompound.l("Leash");
        }
    }

    public void n(float f0) {
        this.bf = f0;
    }

    public void i(float f0) {
        super.i(f0);
        this.n(f0);
    }

    public void c() {
        super.c();
        this.q.C.a("looting");
        if (!this.q.I && this.bz() && !this.aU && this.q.O().b("mobGriefing")) {
            List list = this.q.a(EntityItem.class, this.E.b(1.0D, 0.0D, 1.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityItem entityitem = (EntityItem) iterator.next();

                if (!entityitem.M && entityitem.d() != null) {
                    ItemStack itemstack = entityitem.d();
                    int i0 = b(itemstack);

                    if (i0 > -1) {
                        boolean flag0 = true;
                        ItemStack itemstack1 = this.n(i0);

                        if (itemstack1 != null) {
                            if (i0 == 0) {
                                if (itemstack.b() instanceof ItemSword && !(itemstack1.b() instanceof ItemSword)) {
                                    flag0 = true;
                                } else if (itemstack.b() instanceof ItemSword && itemstack1.b() instanceof ItemSword) {
                                    ItemSword itemsword = (ItemSword) itemstack.b();
                                    ItemSword itemsword1 = (ItemSword) itemstack1.b();

                                    if (itemsword.g() == itemsword1.g()) {
                                        flag0 = itemstack.k() > itemstack1.k() || itemstack.p() && !itemstack1.p();
                                    } else {
                                        flag0 = itemsword.g() > itemsword1.g();
                                    }
                                } else {
                                    flag0 = false;
                                }
                            } else if (itemstack.b() instanceof ItemArmor && !(itemstack1.b() instanceof ItemArmor)) {
                                flag0 = true;
                            } else if (itemstack.b() instanceof ItemArmor && itemstack1.b() instanceof ItemArmor) {
                                ItemArmor itemarmor = (ItemArmor) itemstack.b();
                                ItemArmor itemarmor1 = (ItemArmor) itemstack1.b();

                                if (itemarmor.c == itemarmor1.c) {
                                    flag0 = itemstack.k() > itemstack1.k() || itemstack.p() && !itemstack1.p();
                                } else {
                                    flag0 = itemarmor.c > itemarmor1.c;
                                }
                            } else {
                                flag0 = false;
                            }
                        }

                        if (flag0) {
                            if (itemstack1 != null && this.ab.nextFloat() - 0.1F < this.e[i0]) {
                                this.a(itemstack1, 0.0F);
                            }

                            this.c(i0, itemstack);
                            this.e[i0] = 2.0F;
                            this.bt = true;
                            this.a(entityitem, 1);
                            entityitem.w();
                        }
                    }
                }
            }
        }

        this.q.C.b();
    }

    protected boolean bb() {
        return false;
    }

    protected boolean t() {
        return true;
    }

    protected void bk() {
        if (this.bt) {
            this.aV = 0;
        } else {
            EntityPlayer entityplayer = this.q.a(this, -1.0D);

            if (entityplayer != null) {
                double d0 = entityplayer.u - this.u;
                double d1 = entityplayer.v - this.v;
                double d2 = entityplayer.w - this.w;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.t() && d3 > 16384.0D) {
                    this.w();
                }

                if (this.aV > 600 && this.ab.nextInt(800) == 0 && d3 > 1024.0D && this.t()) {
                    this.w();
                } else if (d3 < 1024.0D) {
                    this.aV = 0;
                }
            }
        }
    }

    protected void be() {
        ++this.aV;
        this.q.C.a("checkDespawn");
        this.bk();
        this.q.C.b();
        this.q.C.a("sensing");
        this.bq.a();
        this.q.C.b();
        this.q.C.a("targetSelector");
        this.d.a();
        this.q.C.b();
        this.q.C.a("goalSelector");
        this.c.a();
        this.q.C.b();
        this.q.C.a("navigation");
        this.bo.f();
        this.q.C.b();
        this.q.C.a("mob tick");
        this.bg();
        this.q.C.b();
        this.q.C.a("controls");
        this.q.C.a("move");
        this.i.c();
        this.q.C.c("look");
        this.h.a();
        this.q.C.c("jump");
        this.j.b();
        this.q.C.b();
        this.q.C.b();
    }

    protected void bh() {
        super.bh();
        this.be = 0.0F;
        this.bf = 0.0F;
        this.bk();
        float f0 = 8.0F;

        if (this.ab.nextFloat() < 0.02F) {
            EntityPlayer entityplayer = this.q.a(this, (double) f0);

            if (entityplayer != null) {
                this.bu = entityplayer;
                this.g = 10 + this.ab.nextInt(20);
            } else {
                this.bg = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.bu != null) {
            this.a(this.bu, 10.0F, (float) this.bl());
            if (this.g-- <= 0 || this.bu.M || this.bu.e((Entity) this) > (double) (f0 * f0)) {
                this.bu = null;
            }
        } else {
            if (this.ab.nextFloat() < 0.05F) {
                this.bg = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }

            this.A += this.bg;
            this.B = this.f;
        }

        boolean flag0 = this.G();
        boolean flag1 = this.I();

        if (flag0 || flag1) {
            this.bd = this.ab.nextFloat() < 0.8F;
        }
    }

    public int bl() {
        return 40;
    }

    public void a(Entity entity, float f0, float f1) {
        double d0 = entity.u - this.u;
        double d1 = entity.w - this.w;
        double d2;

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase) entity;

            d2 = entitylivingbase.v + (double) entitylivingbase.f() - (this.v + (double) this.f());
        } else {
            d2 = (entity.E.b + entity.E.e) / 2.0D - (this.v + (double) this.f());
        }

        double d3 = (double) MathHelper.a(d0 * d0 + d1 * d1);
        float f2 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
        float f3 = (float) (-(Math.atan2(d2, d3) * 180.0D / 3.1415927410125732D));

        this.B = this.b(this.B, f3, f1);
        this.A = this.b(this.A, f2, f0);
    }

    private float b(float f0, float f1, float f2) {
        float f3 = MathHelper.g(f1 - f0);

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f0 + f3;
    }

    public boolean bo() {
        return this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public int br() {
        return 4;
    }

    public int aq() {
        if (this.m() == null) {
            return 3;
        } else {
            int i0 = (int) (this.aJ() - this.aP() * 0.33F);

            i0 -= (3 - this.q.r) * 4;
            if (i0 < 0) {
                i0 = 0;
            }

            return i0 + 3;
        }
    }

    public ItemStack aV() {
        return this.br[0];
    }

    public ItemStack n(int i0) {
        return this.br[i0];
    }

    public ItemStack o(int i0) {
        return this.br[i0 + 1];
    }

    public void c(int i0, ItemStack itemstack) {
        this.br[i0] = itemstack;
    }

    public ItemStack[] ac() {
        return this.br;
    }

    protected void a(boolean flag0, int i0) {
        for (int i1 = 0; i1 < this.ac().length; ++i1) {
            ItemStack itemstack = this.n(i1);
            boolean flag1 = this.e[i1] > 1.0F;

            if (itemstack != null && (flag0 || flag1) && this.ab.nextFloat() - (float) i0 * 0.01F < this.e[i1]) {
                if (!flag1 && itemstack.g()) {
                    int i2 = Math.max(itemstack.l() - 25, 1);
                    int i3 = itemstack.l() - this.ab.nextInt(this.ab.nextInt(i2) + 1);

                    if (i3 > i2) {
                        i3 = i2;
                    }

                    if (i3 < 1) {
                        i3 = 1;
                    }

                    itemstack.b(i3);
                }

                this.a(itemstack, 0.0F);
            }
        }
    }

    protected void bs() {
        if (this.ab.nextFloat() < 0.15F * this.q.b(this.u, this.v, this.w)) {
            int i0 = this.ab.nextInt(2);
            float f0 = this.q.r == 3 ? 0.1F : 0.25F;

            if (this.ab.nextFloat() < 0.095F) {
                ++i0;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i0;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i0;
            }

            for (int i1 = 3; i1 >= 0; --i1) {
                ItemStack itemstack = this.o(i1);

                if (i1 < 3 && this.ab.nextFloat() < f0) {
                    break;
                }

                if (itemstack == null) {
                    Item item = a(i1 + 1, i0);

                    if (item != null) {
                        this.c(i1 + 1, new ItemStack(item));
                    }
                }
            }
        }
    }

    public static int b(ItemStack itemstack) {
        if (itemstack.d != Block.bf.cF && itemstack.d != Item.bS.cv) {
            if (itemstack.b() instanceof ItemArmor) {
                switch (((ItemArmor) itemstack.b()).b) {
                    case 0:
                        return 4;

                    case 1:
                        return 3;

                    case 2:
                        return 2;

                    case 3:
                        return 1;
                }
            }

            return 0;
        } else {
            return 4;
        }
    }

    public static Item a(int i0, int i1) {
        switch (i0) {
            case 4:
                if (i1 == 0) {
                    return Item.X;
                } else if (i1 == 1) {
                    return Item.an;
                } else if (i1 == 2) {
                    return Item.ab;
                } else if (i1 == 3) {
                    return Item.af;
                } else if (i1 == 4) {
                    return Item.aj;
                }

            case 3:
                if (i1 == 0) {
                    return Item.Y;
                } else if (i1 == 1) {
                    return Item.ao;
                } else if (i1 == 2) {
                    return Item.ac;
                } else if (i1 == 3) {
                    return Item.ag;
                } else if (i1 == 4) {
                    return Item.ak;
                }

            case 2:
                if (i1 == 0) {
                    return Item.Z;
                } else if (i1 == 1) {
                    return Item.ap;
                } else if (i1 == 2) {
                    return Item.ad;
                } else if (i1 == 3) {
                    return Item.ah;
                } else if (i1 == 4) {
                    return Item.al;
                }

            case 1:
                if (i1 == 0) {
                    return Item.aa;
                } else if (i1 == 1) {
                    return Item.aq;
                } else if (i1 == 2) {
                    return Item.ae;
                } else if (i1 == 3) {
                    return Item.ai;
                } else if (i1 == 4) {
                    return Item.am;
                }

            default:
                return null;
        }
    }

    protected void bt() {
        float f0 = this.q.b(this.u, this.v, this.w);

        if (this.aV() != null && this.ab.nextFloat() < 0.25F * f0) {
            EnchantmentHelper.a(this.ab, this.aV(), (int) (5.0F + f0 * (float) this.ab.nextInt(18)));
        }

        for (int i0 = 0; i0 < 4; ++i0) {
            ItemStack itemstack = this.o(i0);

            if (itemstack != null && this.ab.nextFloat() < 0.5F * f0) {
                EnchantmentHelper.a(this.ab, itemstack, (int) (5.0F + f0 * (float) this.ab.nextInt(18)));
            }
        }
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        this.a(SharedMonsterAttributes.b).a(new AttributeModifier("Random spawn bonus", this.ab.nextGaussian() * 0.05D, 1));
        return entitylivingdata;
    }

    public boolean bu() {
        return false;
    }

    public String al() {
        return this.bx() ? this.bw() : super.al();
    }

    public void bv() {
        this.bt = true;
    }

    public void a(String s0) {
        this.ah.b(10, s0);
    }

    public String bw() {
        return this.ah.e(10);
    }

    public boolean bx() {
        return this.ah.e(10).length() > 0;
    }

    public void g(boolean flag0) {
        this.ah.b(11, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    public boolean by() {
        return this.ah.a(11) == 1;
    }

    public void a(int i0, float f0) {
        this.e[i0] = f0;
    }

    public boolean bz() {
        return this.bs;
    }

    public void h(boolean flag0) {
        this.bs = flag0;
    }

    public boolean bA() {
        return this.bt;
    }

    public final boolean c(EntityPlayer entityplayer) {
        if (this.bD() && this.bE() == entityplayer) {
            this.i(true);
            return true;
        } else {
            ItemStack itemstack = entityplayer.bn.h();

            if (itemstack != null && itemstack.d == Item.ch.cv && this.bC()) {
                if (!(this instanceof EntityTameable) || !((EntityTameable) this).bP()) {
                    this.b(entityplayer, true);
                    --itemstack.b;
                    return true;
                }

                if (entityplayer.c_().equalsIgnoreCase(((EntityTameable) this).h_())) {
                    this.b(entityplayer, true);
                    --itemstack.b;
                    return true;
                }
            }

            return this.a(entityplayer) ? true : super.c(entityplayer);
        }
    }

    protected boolean a(EntityPlayer entityplayer) {
        return false;
    }

    protected void bB() {
        if (this.bx != null) {
            this.bF();
        }

        if (this.bv) {
            if (this.bw == null || this.bw.M) {
                this.i(true);
            }
        }
    }

    public void i(boolean flag0) {
        if (this.bv) {
            this.bv = false;
            this.bw = null;
            if (!this.q.I) {
                this.b(Item.ch.cv, 1);
            }

            if (!this.q.I && flag0 && this.q instanceof WorldServer) {
                ((WorldServer) this.q).q().a((Entity) this, (Packet) (new Packet39AttachEntity(1, this, (Entity) null)));
            }
        }
    }

    public boolean bC() {
        return !this.bD() && !(this instanceof IMob);
    }

    public boolean bD() {
        return this.bv;
    }

    public Entity bE() {
        return this.bw;
    }

    public void b(Entity entity, boolean flag0) {
        this.bv = true;
        this.bw = entity;
        if (!this.q.I && flag0 && this.q instanceof WorldServer) {
            ((WorldServer) this.q).q().a((Entity) this, (Packet) (new Packet39AttachEntity(1, this, this.bw)));
        }
    }

    private void bF() {
        if (this.bv && this.bx != null) {
            if (this.bx.b("UUIDMost") && this.bx.b("UUIDLeast")) {
                UUID uuid = new UUID(this.bx.f("UUIDMost"), this.bx.f("UUIDLeast"));
                List list = this.q.a(EntityLivingBase.class, this.E.b(10.0D, 10.0D, 10.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    EntityLivingBase entitylivingbase = (EntityLivingBase) iterator.next();

                    if (entitylivingbase.au().equals(uuid)) {
                        this.bw = entitylivingbase;
                        break;
                    }
                }
            } else if (this.bx.b("X") && this.bx.b("Y") && this.bx.b("Z")) {
                int i0 = this.bx.e("X");
                int i1 = this.bx.e("Y");
                int i2 = this.bx.e("Z");
                EntityLeashKnot entityleashknot = EntityLeashKnot.b(this.q, i0, i1, i2);

                if (entityleashknot == null) {
                    entityleashknot = EntityLeashKnot.a(this.q, i0, i1, i2);
                }

                this.bw = entityleashknot;
            } else {
                this.i(false);
            }
        }

        this.bx = null;
    }

    // CanaryMod
    public float getDropChanceForSlot(int slot) {
        if (slot < 0 || slot > 5) {
            return -1.0F;
        }
        return this.e[slot];
    }

    public EntityAITasks getTasks() {
        return this.c;
    }
}
