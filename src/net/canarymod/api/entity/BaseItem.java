package net.canarymod.api.entity;

import net.canarymod.api.inventory.IBaseItem;
import net.minecraft.server.OItem;

/**
 * This is a basic Item wrapper, not for Itemstack, but for Item
 * @author Chris
 *
 */
public class BaseItem implements IBaseItem{

    private OItem item;
    public BaseItem(OItem item) {
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
