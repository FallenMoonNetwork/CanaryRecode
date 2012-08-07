package net.canarymod.api.entity;

import net.canarymod.api.entity.vehicle.Minecart;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.CanaryItem;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityMinecart;
import net.minecraft.server.OItemStack;

public class CanaryMinecart extends CanaryVehicle implements Minecart {

    public CanaryMinecart(OEntity entity) {
        super(entity);
    }

    @Override
    public Item[] getContents() {
        OItemStack[] contents = ((OEntityMinecart)entity).getContents();
        Item[] canaryItems = new CanaryItem[contents.length];
        for(int i = 0; i < contents.length; i++) {
            canaryItems[i] = contents[i].getCanaryItem();
        }
        return canaryItems;
    }

    @Override
    public void setContents(Item[] items) {
        OItemStack[] values = new OItemStack[getInventorySize()];
        clearContents();
        for(int i = 0; i < items.length; i++) {
            values[i] = ((CanaryItem)items[i]).getHandle();
        }
        ((OEntityMinecart)entity).setContents(values);
    }

    @Override
    public Item getSlot(int index) {
        return ((OEntityMinecart)entity).getSlot(index).getCanaryItem();
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((OEntityMinecart)entity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((OEntityMinecart)entity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((OEntityMinecart)entity).getEmptySlot();
    }

    @Override
    public void setSlot(int index, Item value) {
        ((OEntityMinecart)entity).setSlot(index, ((CanaryItem)value).getHandle());
    }

    @Override
    public int getInventorySize() {
        return ((OEntityMinecart)entity).getInventorySize();
    }

    @Override
    public String getInventoryName() {
        return ((OEntityMinecart)entity).getInventoryName();
    }

    @Override
    public void setInventoryName(String value) {
        ((OEntityMinecart)entity).setInventoryName(value);
    }

    @Override
    public void clearContents() {
        ((OEntityMinecart)entity).clearContents();
    }

    @Override
    public Item getItem(int id, int amount) {
        return ((OEntityMinecart)entity).getItem(id, amount);
    }

    @Override
    public Item getItem(int id) {
        return ((OEntityMinecart)entity).getItem(id);
    }

    @Override
    public Item removeItem(Item item) {
        return ((OEntityMinecart)entity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((OEntityMinecart)entity).removeItem(id);
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        return ((OEntityMinecart)entity).decreaseItemStackSize(itemId, amount).getCanaryItem();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((OEntityMinecart)entity).getInventoryStackLimit();
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((OEntityMinecart)entity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((OEntityMinecart)entity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((OEntityMinecart)entity).hasItemStack(itemId, minAmount, maxAmount);
    }

    @Override
    public boolean hasItem(int itemId) {
        return hasItem(itemId);
    }

    @Override
    public void update() {
        ((OEntityMinecart)entity).update();
    }

    @Override
    public int getType() {
        return ((OEntityMinecart)entity).minecartType;
    }

    public OEntityMinecart getHandle() {
        return (OEntityMinecart) this.entity;
    }
}
