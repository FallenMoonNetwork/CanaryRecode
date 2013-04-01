package net.canarymod.api.entity.vehicle;

import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.EntityMinecartHopper;

/**
 *
 * @author Somners
 */
public class CanaryHopperMinecart extends CanaryMinecart implements HopperMinecart{

    public CanaryHopperMinecart(EntityMinecartHopper minecart) {
        super(minecart);
    }

    @Override
    public void addItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addItem(ItemType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addItem(int itemId, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addItem(int itemId, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addItem(ItemType type, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addItem(int itemId, int amount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addItem(ItemType type, int amount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearContents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item[] clearInventory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item[] getContents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getEmptySlot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInventoryName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getInventoryStackLimit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getItem(ItemType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getItem(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getItem(ItemType type, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getItem(int id, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getItem(int id, int amount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getItem(ItemType type, int amount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getSlot(int slot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItem(ItemType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItem(ItemType type, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItem(int itemId, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItemStack(ItemType type, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItemStack(ItemType type, int amount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItemStack(int itemId, int amount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insertItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSlot(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSlot(int itemId, short damage, int slot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSlot(int itemId, int amount, short damage, int slot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSlot(ItemType type, int amount, int slot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSlot(ItemType type, int amount, short damage, int slot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item removeItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item removeItem(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item removeItem(int id, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item removeItem(ItemType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item removeItem(ItemType type, short damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setContents(Item[] items) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setInventoryName(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSlot(int index, Item value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getPosX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getPosY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getPosZ() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTranferCooldown() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTransferCooldown(int cooldown) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
