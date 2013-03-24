package net.minecraft.server;

import java.util.Random;

public class WeightedRandomChestContent extends WeightedRandomItem{

    private ItemStack b = null;
    private int c;
    private int d;

    public WeightedRandomChestContent(int i0, int i1, int i2, int i3, int i4){
        super(i4);
        this.b = new ItemStack(i0, 1, i1);
        this.c = i2;
        this.d = i3;
    }

    public WeightedRandomChestContent(ItemStack itemstack, int i0, int i1, int i2){
        super(i2);
        this.b = itemstack;
        this.c = i0;
        this.d = i1;
    }

    public static void a(Random random, WeightedRandomChestContent[] aweightedrandomchestcontent, IInventory iinventory, int i0){
        for (int i1 = 0; i1 < i0; ++i1) {
            WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent) WeightedRandom.a(random, (WeightedRandomItem[]) aweightedrandomchestcontent);
            int i2 = weightedrandomchestcontent.c + random.nextInt(weightedrandomchestcontent.d - weightedrandomchestcontent.c + 1);

            if (weightedrandomchestcontent.b.e() >= i2) {
                ItemStack itemstack = weightedrandomchestcontent.b.m();

                itemstack.a = i2;
                iinventory.a(random.nextInt(iinventory.j_()), itemstack);
            }
            else {
                for (int i3 = 0; i3 < i2; ++i3) {
                    ItemStack itemstack1 = weightedrandomchestcontent.b.m();

                    itemstack1.a = 1;
                    iinventory.a(random.nextInt(iinventory.j_()), itemstack1);
                }
            }
        }
    }

    public static void a(Random random, WeightedRandomChestContent[] aweightedrandomchestcontent, TileEntityDispenser tileentitydispenser, int i0){
        for (int i1 = 0; i1 < i0; ++i1) {
            WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent) WeightedRandom.a(random, (WeightedRandomItem[]) aweightedrandomchestcontent);
            int i2 = weightedrandomchestcontent.c + random.nextInt(weightedrandomchestcontent.d - weightedrandomchestcontent.c + 1);

            if (weightedrandomchestcontent.b.e() >= i2) {
                ItemStack itemstack = weightedrandomchestcontent.b.m();

                itemstack.a = i2;
                tileentitydispenser.a(random.nextInt(tileentitydispenser.j_()), itemstack);
            }
            else {
                for (int i3 = 0; i3 < i2; ++i3) {
                    ItemStack itemstack1 = weightedrandomchestcontent.b.m();

                    itemstack1.a = 1;
                    tileentitydispenser.a(random.nextInt(tileentitydispenser.j_()), itemstack1);
                }
            }
        }
    }

    public static WeightedRandomChestContent[] a(WeightedRandomChestContent[] aweightedrandomchestcontent, WeightedRandomChestContent... aweightedrandomchestcontent0){
        WeightedRandomChestContent[] aweightedrandomchestcontent1 = new WeightedRandomChestContent[aweightedrandomchestcontent.length + aweightedrandomchestcontent0.length];
        int i0 = 0;

        for (int i1 = 0; i1 < aweightedrandomchestcontent.length; ++i1) {
            aweightedrandomchestcontent1[i0++] = aweightedrandomchestcontent[i1];
        }

        WeightedRandomChestContent[] aweightedrandomchestcontent2 = aweightedrandomchestcontent0;
        int i2 = aweightedrandomchestcontent0.length;

        for (int i3 = 0; i3 < i2; ++i3) {
            WeightedRandomChestContent weightedrandomchestcontent = aweightedrandomchestcontent2[i3];

            aweightedrandomchestcontent1[i0++] = weightedrandomchestcontent;
        }

        return aweightedrandomchestcontent1;
    }
}
