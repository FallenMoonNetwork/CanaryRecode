package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.BlockDestroyHook;
import net.canarymod.hook.player.ItemUseHook;

public class ItemInWorldManager {

    public World a;
    public EntityPlayerMP b;
    private EnumGameType c;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;

    public ItemInWorldManager(World world) {
        this.c = EnumGameType.a;
        this.o = -1;
        this.a = world;
    }

    public void a(EnumGameType enumgametype) {
        this.c = enumgametype;
        enumgametype.a(this.b.ce);
        this.b.n();
    }

    public EnumGameType b() {
        return this.c;
    }

    public boolean d() {
        return this.c.d();
    }

    public void b(EnumGameType enumgametype) {
        if (this.c == EnumGameType.a) {
            this.c = enumgametype;
        }

        this.a(this.c);
    }

    public void a() {
        ++this.i;
        int i0;
        float f0;
        int i1;

        if (this.j) {
            i0 = this.i - this.n;
            int i2 = this.a.a(this.k, this.l, this.m);

            if (i2 == 0) {
                this.j = false;
            } else {
                Block block = Block.r[i2];

                f0 = block.a(this.b, this.b.q, this.k, this.l, this.m) * (float) (i0 + 1);
                i1 = (int) (f0 * 10.0F);
                if (i1 != this.o) {
                    this.a.f(this.b.k, this.k, this.l, this.m, i1);
                    this.o = i1;
                }

                if (f0 >= 1.0F) {
                    this.j = false;
                    this.b(this.k, this.l, this.m);
                }
            }
        } else if (this.d) {
            i0 = this.a.a(this.f, this.g, this.h);
            Block block1 = Block.r[i0];

            if (block1 == null) {
                this.a.f(this.b.k, this.f, this.g, this.h, -1);
                this.o = -1;
                this.d = false;
            } else {
                int i3 = this.i - this.e;

                f0 = block1.a(this.b, this.b.q, this.f, this.g, this.h) * (float) (i3 + 1);
                i1 = (int) (f0 * 10.0F);
                if (i1 != this.o) {
                    this.a.f(this.b.k, this.f, this.g, this.h, i1);
                    this.o = i1;
                }
            }
        }
    }

    public void a(int i0, int i1, int i2, int i3) {
        if (!this.c.c() || this.b.e(i0, i1, i2)) {
            if (this.d()) {
                if (!this.a.a((EntityPlayer) null, i0, i1, i2, i3)) {
                    this.b(i0, i1, i2);
                }
            } else {
                this.a.a((EntityPlayer) null, i0, i1, i2, i3);
                this.e = this.i;
                float f0 = 1.0F;
                int i4 = this.a.a(i0, i1, i2);

                if (i4 > 0) {
                    Block.r[i4].a(this.a, i0, i1, i2, (EntityPlayer) this.b);
                    f0 = Block.r[i4].a(this.b, this.b.q, i0, i1, i2);
                }

                if (i4 > 0 && f0 >= 1.0F) {
                    this.b(i0, i1, i2);
                } else {
                    this.d = true;
                    this.f = i0;
                    this.g = i1;
                    this.h = i2;
                    int i5 = (int) (f0 * 10.0F);

                    this.a.f(this.b.k, i0, i1, i2, i5);
                    this.o = i5;
                }
            }
        }
    }

    public void a(int i0, int i1, int i2) {
        if (i0 == this.f && i1 == this.g && i2 == this.h) {
            int i3 = this.i - this.e;
            int i4 = this.a.a(i0, i1, i2);

            if (i4 != 0) {
                Block block = Block.r[i4];
                float f0 = block.a(this.b, this.b.q, i0, i1, i2) * (float) (i3 + 1);

                if (f0 >= 0.7F) {
                    this.d = false;
                    this.a.f(this.b.k, i0, i1, i2, -1);
                    this.b(i0, i1, i2);
                } else if (!this.j) {
                    this.d = false;
                    this.j = true;
                    this.k = i0;
                    this.l = i1;
                    this.m = i2;
                    this.n = this.e;
                }
            }
        }
    }

    public void c(int i0, int i1, int i2) {
        this.d = false;
        this.a.f(this.b.k, this.f, this.g, this.h, -1);
    }

    private boolean d(int i0, int i1, int i2) {
        Block block = Block.r[this.a.a(i0, i1, i2)];
        int i3 = this.a.h(i0, i1, i2);

        if (block != null) {
            block.a(this.a, i0, i1, i2, i3, (EntityPlayer) this.b);
        }

        boolean flag0 = this.a.i(i0, i1, i2);

        if (block != null && flag0) {
            block.g(this.a, i0, i1, i2, i3);
        }

        return flag0;
    }

    public boolean b(int i0, int i1, int i2) {
        if (this.c.c() && !this.b.e(i0, i1, i2)) {
            return false;
        } else {

            // CanaryMod: BlockDestroyHook
            net.canarymod.api.world.blocks.Block block = ((EntityPlayerMP) b).getCanaryWorld().getBlockAt(i0, i1, i2);

            block.setStatus((byte) 1); // Block break status.
            BlockDestroyHook hook = new BlockDestroyHook(((EntityPlayerMP) b).getPlayer(), block);

            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return true;
            }
            //

            int i3 = this.a.a(i0, i1, i2);
            int i4 = this.a.h(i0, i1, i2);

            this.a.a(this.b, 2001, i0, i1, i2, i3 + (this.a.h(i0, i1, i2) << 12));
            boolean flag0 = this.d(i0, i1, i2);

            if (this.d()) {
                this.b.a.b(new Packet53BlockChange(i0, i1, i2, this.a));
            } else {
                ItemStack itemstack = this.b.cb();
                boolean flag1 = this.b.a(Block.r[i3]);

                if (itemstack != null) {
                    itemstack.a(this.a, i3, i0, i1, i2, this.b);
                    if (itemstack.a == 0) {
                        this.b.cc();
                    }
                }

                if (flag0 && flag1) {
                    Block.r[i3].a(this.a, this.b, i0, i1, i2, i4);
                }
            }

            return flag0;
        }
    }

    // CanaryMod: ItemUseHook
    public boolean itemUsed(CanaryPlayer player, World world, ItemStack itemstack, CanaryBlock clicked) {
        ItemUseHook hook = new ItemUseHook(player, itemstack.getCanaryItem(), clicked);
        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return false;
        }
        return this.a(player.getHandle(), world, itemstack);
    }

    //

    public boolean a(EntityPlayer entityplayer, World world, ItemStack itemstack) {
        int i0 = itemstack.a;
        int i1 = itemstack.k();
        ItemStack itemstack1 = itemstack.a(world, entityplayer);

        if (itemstack1 == itemstack && (itemstack1 == null || itemstack1.a == i0 && itemstack1.n() <= 0 && itemstack1.k() == i1)) {
            return false;
        } else {
            entityplayer.bK.a[entityplayer.bK.c] = itemstack1;
            if (this.d()) {
                itemstack1.a = i0;
                if (itemstack1.g()) {
                    itemstack1.b(i1);
                }
            }

            if (itemstack1.a == 0) {
                entityplayer.bK.a[entityplayer.bK.c] = null;
            }

            if (!entityplayer.bV()) {
                ((EntityPlayerMP) entityplayer).a(entityplayer.bL);
            }

            return true;
        }
    }

    public boolean a(EntityPlayer entityplayer, World world, ItemStack itemstack, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        int i4;

        if (!entityplayer.ag() || entityplayer.bG() == null) {
            i4 = world.a(i0, i1, i2);
            if (i4 > 0 && Block.r[i4].a(world, i0, i1, i2, entityplayer, i3, f0, f1, f2)) {
                return true;
            }
        }

        if (itemstack == null) {
            return false;
        } else if (this.d()) {
            i4 = itemstack.k();
            int i5 = itemstack.a;
            boolean flag0 = itemstack.a(entityplayer, world, i0, i1, i2, i3, f0, f1, f2);

            itemstack.b(i4);
            itemstack.a = i5;
            return flag0;
        } else {
            return itemstack.a(entityplayer, world, i0, i1, i2, i3, f0, f1, f2);
        }
    }

    public void a(WorldServer worldserver) {
        this.a = worldserver;
    }
}
