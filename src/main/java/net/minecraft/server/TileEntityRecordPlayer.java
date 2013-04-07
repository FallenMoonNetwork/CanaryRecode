package net.minecraft.server;


import net.canarymod.api.world.blocks.CanaryJukebox;


public class TileEntityRecordPlayer extends TileEntity {

    private ItemStack a;

    public TileEntityRecordPlayer() {
        this.complexBlock = new CanaryJukebox(this); // CanaryMod: wrap tile entity
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("RecordItem")) {
            this.a(ItemStack.a(nbttagcompound.l("RecordItem")));
        } else if (nbttagcompound.e("Record") > 0) {
            this.a(new ItemStack(nbttagcompound.e("Record"), 1, 0));
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.a() != null) {
            nbttagcompound.a("RecordItem", this.a().b(new NBTTagCompound()));
            nbttagcompound.a("Record", this.a().c);
        }
    }

    public ItemStack a() {
        return this.a;
    }

    public void a(ItemStack itemstack) {
        this.a = itemstack;
        this.k_();
    }

    // CanaryMod
    public CanaryJukebox getCanaryJukebox() {
        return (CanaryJukebox) complexBlock;
    }
}
