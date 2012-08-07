package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityOcelot;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OInventoryLargeChest;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityChest;
import net.minecraft.server.OWorld;

public class OBlockChest extends OBlockContainer {

    private Random a = new Random();

    protected OBlockChest(int var1) {
        super(var1, OMaterial.d);
        this.bN = 26;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public int c() {
        return 22;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        super.a(var1, var2, var3, var4);
        this.b(var1, var2, var3, var4);
        int var5 = var1.a(var2, var3, var4 - 1);
        int var6 = var1.a(var2, var3, var4 + 1);
        int var7 = var1.a(var2 - 1, var3, var4);
        int var8 = var1.a(var2 + 1, var3, var4);
        if (var5 == this.bO) {
            this.b(var1, var2, var3, var4 - 1);
        }

        if (var6 == this.bO) {
            this.b(var1, var2, var3, var4 + 1);
        }

        if (var7 == this.bO) {
            this.b(var1, var2 - 1, var3, var4);
        }

        if (var8 == this.bO) {
            this.b(var1, var2 + 1, var3, var4);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
        int var6 = var1.a(var2, var3, var4 - 1);
        int var7 = var1.a(var2, var3, var4 + 1);
        int var8 = var1.a(var2 - 1, var3, var4);
        int var9 = var1.a(var2 + 1, var3, var4);
        byte var10 = 0;
        int var11 = OMathHelper.b((var5.bs * 4.0F / 360.0F) + 0.5D) & 3;
        if (var11 == 0) {
            var10 = 2;
        }

        if (var11 == 1) {
            var10 = 5;
        }

        if (var11 == 2) {
            var10 = 3;
        }

        if (var11 == 3) {
            var10 = 4;
        }

        if (var6 != this.bO && var7 != this.bO && var8 != this.bO && var9 != this.bO) {
            var1.c(var2, var3, var4, var10);
        } else {
            if ((var6 == this.bO || var7 == this.bO) && (var10 == 4 || var10 == 5)) {
                if (var6 == this.bO) {
                    var1.c(var2, var3, var4 - 1, var10);
                } else {
                    var1.c(var2, var3, var4 + 1, var10);
                }

                var1.c(var2, var3, var4, var10);
            }

            if ((var8 == this.bO || var9 == this.bO) && (var10 == 2 || var10 == 3)) {
                if (var8 == this.bO) {
                    var1.c(var2 - 1, var3, var4, var10);
                } else {
                    var1.c(var2 + 1, var3, var4, var10);
                }

                var1.c(var2, var3, var4, var10);
            }
        }

    }

    public void b(OWorld var1, int var2, int var3, int var4) {
        if (!var1.F) {
            int var5 = var1.a(var2, var3, var4 - 1);
            int var6 = var1.a(var2, var3, var4 + 1);
            int var7 = var1.a(var2 - 1, var3, var4);
            int var8 = var1.a(var2 + 1, var3, var4);
            boolean var9 = true;
            int var10;
            int var11;
            boolean var12;
            byte var13;
            int var14;
            if (var5 != this.bO && var6 != this.bO) {
                if (var7 != this.bO && var8 != this.bO) {
                    var13 = 3;
                    if (OBlock.n[var5] && !OBlock.n[var6]) {
                        var13 = 3;
                    }

                    if (OBlock.n[var6] && !OBlock.n[var5]) {
                        var13 = 2;
                    }

                    if (OBlock.n[var7] && !OBlock.n[var8]) {
                        var13 = 5;
                    }

                    if (OBlock.n[var8] && !OBlock.n[var7]) {
                        var13 = 4;
                    }
                } else {
                    var10 = var1.a(var7 == this.bO ? var2 - 1 : var2 + 1, var3, var4 - 1);
                    var11 = var1.a(var7 == this.bO ? var2 - 1 : var2 + 1, var3, var4 + 1);
                    var13 = 3;
                    var12 = true;
                    if (var7 == this.bO) {
                        var14 = var1.c(var2 - 1, var3, var4);
                    } else {
                        var14 = var1.c(var2 + 1, var3, var4);
                    }

                    if (var14 == 2) {
                        var13 = 2;
                    }

                    if ((OBlock.n[var5] || OBlock.n[var10]) && !OBlock.n[var6] && !OBlock.n[var11]) {
                        var13 = 3;
                    }

                    if ((OBlock.n[var6] || OBlock.n[var11]) && !OBlock.n[var5] && !OBlock.n[var10]) {
                        var13 = 2;
                    }
                }
            } else {
                var10 = var1.a(var2 - 1, var3, var5 == this.bO ? var4 - 1 : var4 + 1);
                var11 = var1.a(var2 + 1, var3, var5 == this.bO ? var4 - 1 : var4 + 1);
                var13 = 5;
                var12 = true;
                if (var5 == this.bO) {
                    var14 = var1.c(var2, var3, var4 - 1);
                } else {
                    var14 = var1.c(var2, var3, var4 + 1);
                }

                if (var14 == 4) {
                    var13 = 4;
                }

                if ((OBlock.n[var7] || OBlock.n[var10]) && !OBlock.n[var8] && !OBlock.n[var11]) {
                    var13 = 5;
                }

                if ((OBlock.n[var8] || OBlock.n[var11]) && !OBlock.n[var7] && !OBlock.n[var10]) {
                    var13 = 4;
                }
            }

            var1.c(var2, var3, var4, var13);
        }
    }

    @Override
    public int a(int var1) {
        return var1 == 1 ? this.bN - 1 : (var1 == 0 ? this.bN - 1 : (var1 == 3 ? this.bN + 1 : this.bN));
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        int var5 = 0;
        if (var1.a(var2 - 1, var3, var4) == this.bO) {
            ++var5;
        }

        if (var1.a(var2 + 1, var3, var4) == this.bO) {
            ++var5;
        }

        if (var1.a(var2, var3, var4 - 1) == this.bO) {
            ++var5;
        }

        if (var1.a(var2, var3, var4 + 1) == this.bO) {
            ++var5;
        }

        return var5 > 1 ? false : (this.g(var1, var2 - 1, var3, var4) ? false : (this.g(var1, var2 + 1, var3, var4) ? false : (this.g(var1, var2, var3, var4 - 1) ? false : !this.g(var1, var2, var3, var4 + 1))));
    }

    private boolean g(OWorld var1, int var2, int var3, int var4) {
        return var1.a(var2, var3, var4) != this.bO ? false : (var1.a(var2 - 1, var3, var4) == this.bO ? true : (var1.a(var2 + 1, var3, var4) == this.bO ? true : (var1.a(var2, var3, var4 - 1) == this.bO ? true : var1.a(var2, var3, var4 + 1) == this.bO)));
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        super.a(var1, var2, var3, var4, var5);
        OTileEntityChest var6 = (OTileEntityChest) var1.b(var2, var3, var4);
        if (var6 != null) {
            var6.h();
        }

    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        OTileEntityChest var5 = (OTileEntityChest) var1.b(var2, var3, var4);
        if (var5 != null) {
            for (int var6 = 0; var6 < var5.getInventorySize(); ++var6) {
                OItemStack var7 = var5.g_(var6);
                if (var7 != null) {
                    float var8 = this.a.nextFloat() * 0.8F + 0.1F;
                    float var9 = this.a.nextFloat() * 0.8F + 0.1F;

                    OEntityItem var12;
                    for (float var10 = this.a.nextFloat() * 0.8F + 0.1F; var7.a > 0; var1.b(var12)) {
                        int var11 = this.a.nextInt(21) + 10;
                        if (var11 > var7.a) {
                            var11 = var7.a;
                        }

                        var7.a -= var11;
                        var12 = new OEntityItem(var1, (var2 + var8), (var3 + var9), (var4 + var10), new OItemStack(var7.c, var11, var7.h()));
                        float var13 = 0.05F;
                        var12.bp = ((float) this.a.nextGaussian() * var13);
                        var12.bq = ((float) this.a.nextGaussian() * var13 + 0.2F);
                        var12.br = ((float) this.a.nextGaussian() * var13);
                        if (var7.n()) {
                            var12.a.d((ONBTTagCompound) var7.o().b());
                        }
                    }
                }
            }
        }

        super.d(var1, var2, var3, var4);
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        Object var6 = var1.b(var2, var3, var4);
        if (var6 == null) {
            return true;
        } else if (var1.e(var2, var3 + 1, var4)) {
            return true;
        } else if (h(var1, var2, var3, var4)) {
            return true;
        } else if (var1.a(var2 - 1, var3, var4) == this.bO && (var1.e(var2 - 1, var3 + 1, var4) || h(var1, var2 - 1, var3, var4))) {
            return true;
        } else if (var1.a(var2 + 1, var3, var4) == this.bO && (var1.e(var2 + 1, var3 + 1, var4) || h(var1, var2 + 1, var3, var4))) {
            return true;
        } else if (var1.a(var2, var3, var4 - 1) == this.bO && (var1.e(var2, var3 + 1, var4 - 1) || h(var1, var2, var3, var4 - 1))) {
            return true;
        } else if (var1.a(var2, var3, var4 + 1) == this.bO && (var1.e(var2, var3 + 1, var4 + 1) || h(var1, var2, var3, var4 + 1))) {
            return true;
        } else {
            if (var1.a(var2 - 1, var3, var4) == this.bO) {
                var6 = new OInventoryLargeChest("Large chest", (OTileEntityChest) var1.b(var2 - 1, var3, var4), (OIInventory) var6);
            }

            if (var1.a(var2 + 1, var3, var4) == this.bO) {
                var6 = new OInventoryLargeChest("Large chest", (OIInventory) var6, (OTileEntityChest) var1.b(var2 + 1, var3, var4));
            }

            if (var1.a(var2, var3, var4 - 1) == this.bO) {
                var6 = new OInventoryLargeChest("Large chest", (OTileEntityChest) var1.b(var2, var3, var4 - 1), (OIInventory) var6);
            }

            if (var1.a(var2, var3, var4 + 1) == this.bO) {
                var6 = new OInventoryLargeChest("Large chest", (OIInventory) var6, (OTileEntityChest) var1.b(var2, var3, var4 + 1));
            }

            if (var1.F) {
                return true;
            } else {
                var5.a((OIInventory) var6);
                return true;
            }
        }
    }

    @Override
    public OTileEntity a_() {
        return new OTileEntityChest();
    }

    private static boolean h(OWorld var0, int var1, int var2, int var3) {
        Iterator var4 = var0.a(OEntityOcelot.class, OAxisAlignedBB.b(var1, (var2 + 1), var3, (var1 + 1), (var2 + 2), (var3 + 1))).iterator();

        OEntityOcelot var6;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            OEntity var5 = (OEntity) var4.next();
            var6 = (OEntityOcelot) var5;
        } while (!var6.v_());

        return true;
    }
}
