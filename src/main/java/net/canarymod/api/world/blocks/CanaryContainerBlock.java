package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.IInventory;

public abstract class CanaryContainerBlock extends CanaryComplexBlock implements Inventory {

    public CanaryContainerBlock(IInventory inventory) {
        super(inventory);
    }

    @Override
    public void addItem(ItemType type) {
        this.addItem(type.getId(), 1, (short) 0);
    }

    @Override
    public void addItem(int itemId) {
        this.addItem(itemId, 1, (short) 0);
    }

    @Override
    public void addItem(int itemId, short damage) {
        this.addItem(itemId, 1, damage);
    }

    @Override
    public void addItem(int itemId, int amount) {
        this.addItem(itemId, amount, (short) 0);
    }

    @Override
    public void addItem(ItemType type, int amount) {
        this.addItem(type.getId(), amount, (short) 0);
    }

    @Override
    public void addItem(int itemId, int amount, short damage) {
        this.addItem(new CanaryItem(itemId, amount, damage));
    }

    @Override
    public void addItem(ItemType type, int amount, short damage) {
        this.addItem(type.getId(), amount, damage);
    }

    @Override
    public void addItem(Item item) {
        // TODO Auto-generated method stub

    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount, short damage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getEmptySlot() {
        for (int index = 0; index < getSize(); index++) {
            if (getSlot(index) == null) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public String getInventoryName() {
        return inventory.b();
    }

    @Override
    public int getInventoryStackLimit() {
        return inventory.d();
    }

    @Override
    public Item getItem(ItemType type) {
        return this.getItem(type.getId());
    }

    @Override
    public Item getItem(int id) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id) {
                toCheck.setSlot(index);
                return toCheck;
            }
        }
        return null;
    }

    @Override
    public Item getItem(ItemType type, int amount) {
        return this.getItem(type.getId(), amount);
    }

    @Override
    public Item getItem(int id, int amount) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id && toCheck.getAmount() == amount) {
                toCheck.setSlot(index);
                return toCheck;
            }
        }
        return null;
    }

    @Override
    public Item getItem(int id, int amount, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id && toCheck.getAmount() == amount && toCheck.getDamage() == damage) {
                toCheck.setSlot(index);
                return toCheck;
            }
        }
        return null;
    }

    @Override
    public Item getItem(ItemType type, int amount, short damage) {
        return this.getItem(type.getId(), amount, damage);
    }

    @Override
    public int getSize() {
        return inventory.j_();
    }

    @Override
    public Item getSlot(int index) {
        return inventory.a(index).getCanaryItem();
    }

    @Override
    public boolean hasItem(int itemId) {
        for (int index = 0; index < getSize(); index++) {
            if (getSlot(index) != null && getSlot(index).getId() == itemId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItem(ItemType type) {
        return this.hasItem(type.getId());
    }

    @Override
    public boolean hasItem(ItemType type, short damage) {
        return this.hasItem(type.getId(), damage);
    }

    @Override
    public boolean hasItem(int itemId, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item item = getSlot(index);
            if (item != null && item.getId() == itemId && item.getDamage() == damage) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(ItemType type, int amount) {
        return this.hasItemStack(type.getId(), amount, 64);
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return this.hasItemStack(itemId, amount, 64);
    }

    @Override
    public boolean hasItemStack(ItemType type, int amount, short damage) {
        return this.hasItemStack(type.getId(), amount, 64, damage);
    }

    @Override
    public boolean hasItemStack(int itemId, int amount, short damage) {
        return hasItemStack(itemId, amount, 64, damage);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == itemId) {
                int am = toCheck.getAmount();
                if (am > minAmount && am < maxAmount) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == itemId && toCheck.getDamage() == damage) {
                int am = toCheck.getAmount();
                if (am > minAmount && am < maxAmount) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean insertItem(Item item) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setSlot(int itemId, short damage, int slot) {
        this.setSlot(itemId, 1, damage, slot);
    }

    @Override
    public void setSlot(int itemId, int amount, short damage, int slot) {
        CanaryItem item = new CanaryItem(itemId, 1, damage);
        item.setSlot(slot);
        this.setSlot(item);
    }

    @Override
    public void setSlot(ItemType type, int amount, int slot) {
        this.setSlot(type.getId(), amount, (short) 0, slot);
    }

    @Override
    public void setSlot(ItemType type, int amount, short damage, int slot) {
        this.setSlot(type.getId(), amount, damage, slot);
    }

    @Override
    public void setSlot(Item item) {
        this.setSlot(item.getSlot(), item);
    }

    @Override
    public void setSlot(int index, Item value) {
        inventory.a(index, ((CanaryItem) value).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return this.removeItem(item.getId(), (short) item.getDamage());
    }

    @Override
    public Item removeItem(int id) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id) {
                setSlot(index, null);
                return toCheck;
            }
        }
        return null;
    }

    @Override
    public Item removeItem(int id, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id && toCheck.getDamage() == damage) {
                setSlot(index, null);
                return toCheck;
            }
        }
        return null;
    }

    @Override
    public Item removeItem(ItemType type) {
        return this.removeItem(type.getId());
    }

    @Override
    public Item removeItem(ItemType type, short damage) {
        return this.removeItem(type.getId(), damage);
    }
}
