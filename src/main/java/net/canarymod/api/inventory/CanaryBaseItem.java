package net.canarymod.api.inventory;

/**
 * This is a basic Item wrapper, not for Itemstack, but for Item
 * 
 * @author Chris
 */
public class CanaryBaseItem implements BaseItem {

    private net.minecraft.server.Item item;

    public CanaryBaseItem(net.minecraft.server.Item item) {
        this.item = item;
    }

    @Override
    public int getMaxStackSize() {
        return item.d();
    }

    @Override
    public int getMaxDamage() {
        return item.f();
    }

    @Override
    public void setMaxDamage(int damage) {
        item.f(damage);
    }

    @Override
    public boolean isDamageable() {
        return item.g();
    }

}
