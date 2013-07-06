package net.minecraft.server;

import java.util.Iterator;
import java.util.Map;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CanaryAnvil;
import net.canarymod.hook.player.AnvilUseHook;

public class ContainerRepair extends Container {

    public IInventory f = new InventoryCraftResult(); // Canary: private -> public
    public IInventory g = new ContainerRepairINNER1(this, "Repair", true, 2); // Canary: private -> public
    public World h; // Canary: private -> public
    public int i; // Canary: private -> public
    public int j; // Canary: private -> public
    public int k; // Canary: private -> public
    public int a;
    public int l; // Canary: private -> public
    private String m;
    private final EntityPlayer n;

    public ContainerRepair(InventoryPlayer inventoryplayer, World world, int i0, int i1, int i2, EntityPlayer entityplayer) {
        this.h = world;
        this.i = i0;
        this.j = i1;
        this.k = i2;
        this.n = entityplayer;
        this.a(new Slot(this.g, 0, 27, 47));
        this.a(new Slot(this.g, 1, 76, 47));
        this.a((Slot) (new ContainerRepairINNER2(this, this.f, 2, 134, 47, world, i0, i1, i2)));

        int i3;

        for (i3 = 0; i3 < 3; ++i3) {
            for (int i4 = 0; i4 < 9; ++i4) {
                this.a(new Slot(inventoryplayer, i4 + i3 * 9 + 9, 8 + i4 * 18, 84 + i3 * 18));
            }
        }

        for (i3 = 0; i3 < 9; ++i3) {
            this.a(new Slot(inventoryplayer, i3, 8 + i3 * 18, 142));
        }

        this.inventory = new CanaryAnvil(this); // CanaryMod: Set inventory instance
    }

    public void a(IInventory iinventory) {
        super.a(iinventory);
        if (iinventory == this.g) {
            this.e();
        }
    }

    public void e() {
        ItemStack itemstack = this.g.a(0);

        this.a = 0;
        int i0 = 0;
        byte b0 = 0;
        int i1 = 0;

        if (itemstack == null) {
            this.f.a(0, (ItemStack) null);
            this.a = 0;
        } else {
            ItemStack itemstack1 = itemstack.m();
            ItemStack itemstack2 = this.g.a(1);
            Map map = EnchantmentHelper.a(itemstack1);
            boolean flag0 = false;
            int i2 = b0 + itemstack.C() + (itemstack2 == null ? 0 : itemstack2.C());

            this.l = 0;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            Iterator iterator;
            Enchantment enchantment;

            if (itemstack2 != null) {
                flag0 = itemstack2.d == Item.bY.cv && Item.bY.g(itemstack2).c() > 0;
                if (itemstack1.g() && Item.g[itemstack1.d].a(itemstack, itemstack2)) {
                    i3 = Math.min(itemstack1.j(), itemstack1.l() / 4);
                    if (i3 <= 0) {
                        this.f.a(0, (ItemStack) null);
                        this.a = 0;
                        return;
                    }

                    for (i4 = 0; i3 > 0 && i4 < itemstack2.b; ++i4) {
                        i5 = itemstack1.j() - i3;
                        itemstack1.b(i5);
                        i0 += Math.max(1, i3 / 100) + map.size();
                        i3 = Math.min(itemstack1.j(), itemstack1.l() / 4);
                    }

                    this.l = i4;
                } else {
                    if (!flag0 && (itemstack1.d != itemstack2.d || !itemstack1.g())) {
                        this.f.a(0, (ItemStack) null);
                        this.a = 0;
                        return;
                    }

                    if (itemstack1.g() && !flag0) {
                        i3 = itemstack.l() - itemstack.j();
                        i4 = itemstack2.l() - itemstack2.j();
                        i5 = i4 + itemstack1.l() * 12 / 100;
                        int i8 = i3 + i5;

                        i6 = itemstack1.l() - i8;
                        if (i6 < 0) {
                            i6 = 0;
                        }

                        if (i6 < itemstack1.k()) {
                            itemstack1.b(i6);
                            i0 += Math.max(1, i5 / 100);
                        }
                    }

                    Map map1 = EnchantmentHelper.a(itemstack2);

                    iterator = map1.keySet().iterator();

                    while (iterator.hasNext()) {
                        i5 = ((Integer) iterator.next()).intValue();
                        enchantment = Enchantment.b[i5];
                        i6 = map.containsKey(Integer.valueOf(i5)) ? ((Integer) map.get(Integer.valueOf(i5))).intValue() : 0;
                        i7 = ((Integer) map1.get(Integer.valueOf(i5))).intValue();
                        int i9;

                        if (i6 == i7) {
                            ++i7;
                            i9 = i7;
                        } else {
                            i9 = Math.max(i7, i6);
                        }

                        i7 = i9;
                        int i10 = i7 - i6;
                        boolean flag1 = enchantment.a(itemstack);

                        if (this.n.bG.d || itemstack.d == ItemEnchantedBook.bY.cv) {
                            flag1 = true;
                        }

                        Iterator iterator1 = map.keySet().iterator();

                        while (iterator1.hasNext()) {
                            int i11 = ((Integer) iterator1.next()).intValue();

                            if (i11 != i5 && !enchantment.a(Enchantment.b[i11])) {
                                flag1 = false;
                                i0 += i10;
                            }
                        }

                        if (flag1) {
                            if (i7 > enchantment.b()) {
                                i7 = enchantment.b();
                            }

                            map.put(Integer.valueOf(i5), Integer.valueOf(i7));
                            int i12 = 0;

                            switch (enchantment.c()) {
                                case 1:
                                    i12 = 8;
                                    break;

                                case 2:
                                    i12 = 4;

                                case 3:
                                case 4:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                default:
                                    break;

                                case 5:
                                    i12 = 2;
                                    break;

                                case 10:
                                    i12 = 1;
                            }

                            if (flag0) {
                                i12 = Math.max(1, i12 / 2);
                            }

                            i0 += i12 * i10;
                        }
                    }
                }
            }

            if (org.apache.commons.lang3.StringUtils.isBlank(this.m)) {
                if (itemstack.u()) {
                    i1 = itemstack.g() ? 7 : itemstack.b * 5;
                    i0 += i1;
                    itemstack1.t();
                }
            } else if (!this.m.equals(itemstack.s())) {
                i1 = itemstack.g() ? 7 : itemstack.b * 5;
                i0 += i1;
                if (itemstack.u()) {
                    i2 += i1 / 2;
                }

                itemstack1.c(this.m);
            }

            i3 = 0;

            for (iterator = map.keySet().iterator(); iterator.hasNext(); i2 += i3 + i6 * i7) {
                i5 = ((Integer) iterator.next()).intValue();
                enchantment = Enchantment.b[i5];
                i6 = ((Integer) map.get(Integer.valueOf(i5))).intValue();
                i7 = 0;
                ++i3;
                switch (enchantment.c()) {
                    case 1:
                        i7 = 8;
                        break;

                    case 2:
                        i7 = 4;

                    case 3:
                    case 4:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        break;

                    case 5:
                        i7 = 2;
                        break;

                    case 10:
                        i7 = 1;
                }

                if (flag0) {
                    i7 = Math.max(1, i7 / 2);
                }
            }

            if (flag0) {
                i2 = Math.max(1, i2 / 2);
            }

            this.a = i2 + i0;
            if (i0 <= 0) {
                itemstack1 = null;
            }

            if (i1 == i0 && i1 > 0 && this.a >= 40) {
                this.a = 39;
            }

            if (this.a >= 40 && !this.n.bG.d) {
                itemstack1 = null;
            }

            if (itemstack1 != null) {
                i4 = itemstack1.C();
                if (itemstack2 != null && i4 < itemstack2.C()) {
                    i4 = itemstack2.C();
                }

                if (itemstack1.u()) {
                    i4 -= 9;
                }

                if (i4 < 0) {
                    i4 = 0;
                }

                i4 += 2;
                itemstack1.c(i4);
                EnchantmentHelper.a(map, itemstack1);
            }

            this.f.a(0, itemstack1);
            // CanaryMod: AnvilUse
            new AnvilUseHook(getPlayer(), new CanaryAnvil(this)).call();
            //
            this.b();
        }
    }

    public void a(ICrafting icrafting) {
        super.a(icrafting);
        icrafting.a(this, 0, this.a);
    }

    public void b(EntityPlayer entityplayer) {
        super.b(entityplayer);
        if (!this.h.I) {
            for (int i0 = 0; i0 < this.g.j_(); ++i0) {
                ItemStack itemstack = this.g.a_(i0);

                if (itemstack != null) {
                    entityplayer.b(itemstack);
                }
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.h.a(this.i, this.j, this.k) != Block.cm.cF ? false : entityplayer.e((double) this.i + 0.5D, (double) this.j + 0.5D, (double) this.k + 0.5D) <= 64.0D;
    }

    public ItemStack b(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.c.get(i0);

        if (slot != null && slot.e()) {
            ItemStack itemstack1 = slot.d();

            itemstack = itemstack1.m();
            if (i0 == 2) {
                if (!this.a(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.a(itemstack1, itemstack);
            } else if (i0 != 0 && i0 != 1) {
                if (i0 >= 3 && i0 < 39 && !this.a(itemstack1, 0, 2, false)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.b == 0) {
                slot.c((ItemStack) null);
            } else {
                slot.f();
            }

            if (itemstack1.b == itemstack.b) {
                return null;
            }

            slot.a(entityplayer, itemstack1);
        }

        return itemstack;
    }

    public void a(String s0) {
        this.m = s0;
        if (this.a(2).e()) {
            ItemStack itemstack = this.a(2).d();

            if (org.apache.commons.lang3.StringUtils.isBlank(s0)) {
                itemstack.t();
            } else {
                itemstack.c(this.m);
            }
        }

        this.e();
    }

    static IInventory a(ContainerRepair containerrepair) {
        return containerrepair.g;
    }

    static int b(ContainerRepair containerrepair) {
        return containerrepair.l;
    }

    // CanaryMod start
    public String getToolName() {
        return this.m;
    }

    public Player getPlayer() {
        return ((EntityPlayerMP) this.n).getPlayer();
    }
    // CanaryMod end
}
