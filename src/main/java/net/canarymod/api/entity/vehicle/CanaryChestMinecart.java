package net.canarymod.api.entity.vehicle;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.EntityMinecartChest;
import net.minecraft.server.ItemStack;

/**
 * ChestMinecart wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryChestMinecart extends CanaryMinecart implements ChestMinecart {

    public CanaryChestMinecart(EntityMinecartChest entity) {
        super(entity);
    }

    @Override
    public Item[] getContents() {
        ItemStack[] contents = ((EntityMinecartChest) entity).getContents();
        Item[] canaryItems = new CanaryItem[contents.length];

        for (int i = 0; i < contents.length; i++) {
            canaryItems[i] = contents[i].getCanaryItem();
        }
        return canaryItems;
    }

    @Override
    public void setContents(Item[] items) {
        ItemStack[] values = new ItemStack[getInventorySize()];

        clearContents();
        for (int i = 0; i < items.length; i++) {
            values[i] = ((CanaryItem) items[i]).getHandle();
        }
        ((EntityMinecartChest) entity).setContents(values);
    }

    @Override
    public Item getSlot(int index) {
        return ((EntityMinecartChest) entity).getSlot(index).getCanaryItem();
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((EntityMinecartChest) entity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((EntityMinecartChest) entity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((EntityMinecartChest) entity).getEmptySlot();
    }

    @Override
    public void setSlot(int index, Item value) {
        ((EntityMinecartChest) entity).setSlot(index, ((CanaryItem) value).getHandle());
    }

    @Override
    public int getInventorySize() {
        return ((EntityMinecartChest) entity).getInventorySize();
    }

    @Override
    public String getInventoryName() {
        return ((EntityMinecartChest) entity).getInventoryName();
    }

    @Override
    public void setInventoryName(String value) {
        ((EntityMinecartChest) entity).setInventoryName(value);
    }

    @Override
    public void clearContents() {
        ((EntityMinecartChest) entity).clearContents();
    }

    @Override
    public Item getItem(int id, int amount) {
        return ((EntityMinecartChest) entity).getItem(id, amount);
    }

    @Override
    public Item getItem(int id) {
        return ((EntityMinecartChest) entity).getItem(id);
    }

    @Override
    public Item removeItem(Item item) {
        return ((EntityMinecartChest) entity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((EntityMinecartChest) entity).removeItem(id);
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        return ((EntityMinecartChest) entity).decreaseItemStackSize(itemId, amount).getCanaryItem();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((EntityMinecartChest) entity).getInventoryStackLimit();
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((EntityMinecartChest) entity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((EntityMinecartChest) entity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((EntityMinecartChest) entity).hasItemStack(itemId, minAmount, maxAmount);
    }

    @Override
    public boolean hasItem(int itemId) {
        return hasItem(itemId);
    }

    @Override
    public void update() {
        ((EntityMinecartChest) entity).update();
    }
}
