package net.minecraft.server;

public class SlotCrafting extends Slot {

    private final IInventory a;
    private EntityPlayer b;
    private int c;

    public SlotCrafting(EntityPlayer entityplayer, IInventory iinventory, IInventory iinventory1, int i0, int i1, int i2) {
        super(iinventory1, i0, i1, i2);
        this.b = entityplayer;
        this.a = iinventory;
    }

    public boolean a(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i0) {
        if (this.e()) {
            this.c += Math.min(i0, this.d().b);
        }

        return super.a(i0);
    }

    protected void a(ItemStack itemstack, int i0) {
        this.c += i0;
        this.b(itemstack);
    }

    protected void b(ItemStack itemstack) {
        itemstack.a(this.b.q, this.b, this.c);
        this.c = 0;
        if (itemstack.d == Block.aD.cF) {
            this.b.a((StatBase)AchievementList.h, 1);
        }
        else if (itemstack.d == Item.v.cv) {
            this.b.a((StatBase)AchievementList.i, 1);
        }
        else if (itemstack.d == Block.aG.cF) {
            this.b.a((StatBase)AchievementList.j, 1);
        }
        else if (itemstack.d == Item.P.cv) {
            this.b.a((StatBase)AchievementList.l, 1);
        }
        else if (itemstack.d == Item.W.cv) {
            this.b.a((StatBase)AchievementList.m, 1);
        }
        else if (itemstack.d == Item.bb.cv) {
            this.b.a((StatBase)AchievementList.n, 1);
        }
        else if (itemstack.d == Item.z.cv) {
            this.b.a((StatBase)AchievementList.o, 1);
        }
        else if (itemstack.d == Item.t.cv) {
            this.b.a((StatBase)AchievementList.r, 1);
        }
        else if (itemstack.d == Block.bJ.cF) {
            this.b.a((StatBase)AchievementList.D, 1);
        }
        else if (itemstack.d == Block.as.cF) {
            this.b.a((StatBase)AchievementList.F, 1);
        }
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        this.b(itemstack);

        for (int i0 = 0; i0 < this.a.j_(); ++i0) {
            ItemStack itemstack1 = this.a.a(i0);

            if (itemstack1 != null) {
                this.a.a(i0, 1);
                if (itemstack1.b().u()) {
                    ItemStack itemstack2 = new ItemStack(itemstack1.b().t());

                    if (!itemstack1.b().j(itemstack1) || !this.b.bn.a(itemstack2)) {
                        if (this.a.a(i0) == null) {
                            this.a.a(i0, itemstack2);
                        }
                        else {
                            this.b.b(itemstack2);
                        }
                    }
                }
            }
        }
    }
}
