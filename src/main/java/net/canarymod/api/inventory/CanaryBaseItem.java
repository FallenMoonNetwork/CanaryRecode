package net.canarymod.api.inventory;


/**
 * This is a basic Item wrapper, not for Itemstack, but for Item
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryBaseItem implements BaseItem {

    private net.minecraft.server.Item item;

    /**
     * Constructs a new Item wrapper
     * 
     * @param item
     *            the Item to wrap
     */
    public CanaryBaseItem(net.minecraft.server.Item item) {
        this.item = item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxStackSize() {
        return item.l();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxDamage() {
        return item.n();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxDamage(int damage) {
        item.e(damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDamageable() {
        return item.o();
    }

}
