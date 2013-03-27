package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.minecraft.server.ContainerWorkbench;


public class CanaryWorkbench implements Workbench {

    private ContainerWorkbench container;

    public CanaryWorkbench(ContainerWorkbench container) {
        this.container = container;
    }

    @Override
    public Inventory getInventory() {
        return container.getInventory();
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public int getX() {
        return container.h;
    }

    @Override
    public int getY() {
        return container.i;
    }

    @Override
    public int getZ() {
        return container.j;
    }

    @Override
    public World getWorld() {
        return container.g.getCanaryWorld();
    }

    @Override
    public void update() {
        getInventory().update();
    }

    @Override
    public void clearContents() {
        getInventory().clearContents();
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        return getInventory().decreaseItemStackSize(itemId, amount);
    }

    @Override
    public Item[] getContents() {
        return getInventory().getContents();
    }

    @Override
    public String getInventoryName() {
        return getInventory().getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return getInventory().getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return getInventory().getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return getInventory().getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
        return getInventory().getItem(id, amount);
    }

    @Override
    public Item getSlot(int index) {
        return getInventory().getSlot(index);
    }

    @Override
    public boolean hasItem(int itemId) {
        return getInventory().hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
        return getInventory().hasItemStack(item);
    }

    @Override
    public Item removeItem(Item item) {
        return getInventory().removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return getInventory().removeItem(id);
    }

    @Override
    public void setContents(Item[] items) {
        getInventory().setContents(items);
    }

    @Override
    public void setInventoryName(String value) {
        getInventory().setInventoryName(value);
    }

    @Override
    public void setSlot(int index, Item item) {
        getInventory().setSlot(index, item);
    }

    @Override
    public void addItem(int itemId, int amount) {
        getInventory().addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        getInventory().addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return getInventory().getEmptySlot();
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return getInventory().hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return getInventory().hasItemStack(itemId, minAmount, maxAmount);
    }

    public ContainerWorkbench getHandle() {
        return container;
    }

}
