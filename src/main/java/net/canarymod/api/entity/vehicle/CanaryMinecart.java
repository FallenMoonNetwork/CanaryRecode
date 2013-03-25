package net.canarymod.api.entity.vehicle;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.EntityMinecart;
import net.minecraft.server.ItemStack;


public class CanaryMinecart extends CanaryVehicle implements Minecart {

    public CanaryMinecart(EntityMinecart entity) {
        super(entity);
    }

    @Override
    public Item[] getContents() {
        ItemStack[] contents = ((EntityMinecart) entity).getContents();
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
        ((EntityMinecart) entity).setContents(values);
    }

    @Override
    public Item getSlot(int index) {
        return ((EntityMinecart) entity).getSlot(index).getCanaryItem();
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((EntityMinecart) entity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((EntityMinecart) entity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((EntityMinecart) entity).getEmptySlot();
    }

    @Override
    public void setSlot(int index, Item value) {
        ((EntityMinecart) entity).setSlot(index, ((CanaryItem) value).getHandle());
    }

    @Override
    public int getInventorySize() {
        return ((EntityMinecart) entity).getInventorySize();
    }

    @Override
    public String getInventoryName() {
        return ((EntityMinecart) entity).getInventoryName();
    }

    @Override
    public void setInventoryName(String value) {
        ((EntityMinecart) entity).setInventoryName(value);
    }

    @Override
    public void clearContents() {
        ((EntityMinecart) entity).clearContents();
    }

    @Override
    public Item getItem(int id, int amount) {
        return ((EntityMinecart) entity).getItem(id, amount);
    }

    @Override
    public Item getItem(int id) {
        return ((EntityMinecart) entity).getItem(id);
    }

    @Override
    public Item removeItem(Item item) {
        return ((EntityMinecart) entity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((EntityMinecart) entity).removeItem(id);
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        return ((EntityMinecart) entity).decreaseItemStackSize(itemId, amount).getCanaryItem();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((EntityMinecart) entity).getInventoryStackLimit();
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((EntityMinecart) entity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((EntityMinecart) entity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((EntityMinecart) entity).hasItemStack(itemId, minAmount, maxAmount);
    }

    @Override
    public boolean hasItem(int itemId) {
        return hasItem(itemId);
    }

    @Override
    public void update() {
        ((EntityMinecart) entity).update();
    }

    @Override
    public int getType() {
        return ((EntityMinecart) entity).minecartType;
    }

    public EntityMinecart getHandle() {
        return (EntityMinecart) this.entity;
    }
}
