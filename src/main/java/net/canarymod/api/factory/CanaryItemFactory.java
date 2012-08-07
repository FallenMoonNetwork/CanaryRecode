package net.canarymod.api.factory;

import net.canarymod.api.Enchantment;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public class CanaryItemFactory implements ItemFactory {

    @Override
    public Item newItem(int id) {
        return new CanaryItem(id, 0);
    }

    @Override
    public Item newItem(int id, int damage) {
        return new CanaryItem(id, damage);
    }

    @Override
    public Item newItem(int id, int damage, int stackSize) {
        CanaryItem item = new CanaryItem(id, damage);
        item.setAmount(stackSize);
        return item;
    }

    @Override
    public Item newItem(ItemType type) {
        return new CanaryItem(type.getId(), 0);
    }

    @Override
    public Item newItem(ItemType type, int damage) {
        return new CanaryItem(type.getId(), damage);
    }

    @Override
    public Item newItem(ItemType type, int damage, int stackSize) {
        CanaryItem item = new CanaryItem(type.getId(), damage);
        item.setAmount(stackSize);
        return item;
    }

    @Override
    public Item newItem(Item item) {
        CanaryItem item2 = new CanaryItem(item.getId(), item.getDamage());
        item2.setAmount(item.getAmount());
        item2.setEnchantments(item.getEnchantments());
        return item2;
    }

    @Override
    public Item newItem(int id, int damage, Enchantment[] enchantments) {
        CanaryItem item = new CanaryItem(id, damage);
        item.setEnchantments(enchantments);
        return item;
    }

}
