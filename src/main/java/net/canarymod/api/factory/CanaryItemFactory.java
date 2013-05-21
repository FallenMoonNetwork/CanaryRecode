package net.canarymod.api.factory;


import net.canarymod.api.inventory.CanaryEnchantment;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Enchantment;
import net.canarymod.api.inventory.Enchantment.Type;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;


/**
 * Item Factory
 *
 * @author Brian (WWOL)
 * @author Jason (darkdiplomat)
 */
public class CanaryItemFactory implements ItemFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(int id) {
        return new CanaryItem(id, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(int id, int damage) {
        return new CanaryItem(id, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(int id, int damage, int stackSize) {
        CanaryItem item = new CanaryItem(id, damage);

        item.setAmount(stackSize);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(ItemType type) {
        return type == null ? null : new CanaryItem(type.getId(), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(ItemType type, int damage) {
        return type == null ? null : new CanaryItem(type.getId(), 0, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(ItemType type, int damage, int stackSize) {
        if (type == null) {
            return null;
        }
        
        CanaryItem item = new CanaryItem(type.getId(), stackSize, damage);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(Item item) {
        if (item == null) {
            return null;
        }
        
        CanaryItem item2 = new CanaryItem(((CanaryItem)item).getHandle().m());
        return item2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(String commandInput) {
        String[] data = commandInput.split(":");
        CanaryItem item;

        if (data[0].matches("\\d+")) {
            item = (CanaryItem) (newItem(ItemType.fromId(Integer.parseInt(data[0]))));
        } else {
            item = (CanaryItem) (newItem(ItemType.fromString(data[0])));
        }
        if (data.length == 2) {
            if (data[1].matches("\\d+") && item != null) {
                item.setDamage(Integer.parseInt(data[1]));
            }
        }
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item newItem(int id, int damage, Enchantment[] enchantments) {
        CanaryItem item = new CanaryItem(id, 0, damage);

        item.setEnchantments(enchantments);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enchantment newEnchantment(short id, short level) {
        return newEnchantment(Enchantment.Type.fromId(id), level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enchantment newEnchantment(Type type, short level) {
        if (type != null) {
            return new CanaryEnchantment(type, level);
        }
        return null;
    }
}
