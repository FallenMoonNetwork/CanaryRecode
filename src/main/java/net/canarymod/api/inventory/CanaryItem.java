package net.canarymod.api.inventory;


import net.canarymod.api.Enchantment;
import net.minecraft.server.ItemStack;

/**
 * Item wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryItem implements Item {

    private ItemType type;
    private int slot = -1;
    private ItemStack item;

    /**
     * Constructs a new CanaryItem
     * 
     * @param oItemStack
     */
    public CanaryItem(ItemStack oItemStack) {
        type = ItemType.fromId(oItemStack.c);
        item = oItemStack;
    }

    public CanaryItem(int id, int amount) {
        this.type = ItemType.fromId(id);
        item = new ItemStack(id, amount, 0);
    }

    public CanaryItem(int itemId, int amount, int targetSlot) {
        item = new ItemStack(itemId, amount, 0);
        slot = targetSlot;
        type = ItemType.fromId(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return type.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(int id) {
        type = ItemType.fromId(id);
        item.c = type.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return item.k();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAmount() {
        return item.a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSlot() {
        return slot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAmount(int amount) {
        item.a = amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamage(int damage) {
        item.b(damage);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxAmount() {
        return item.getBaseItem().getMaxStackSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxAmount(int amount) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnchanted() {
        return item.x();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enchantment getEnchantment() {
        return getEnchantment(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enchantment getEnchantment(int index) {
        // NBT implementation needed
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enchantment[] getEnchantments() {
        // NBT implementation needed
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEnchantments(Enchantment... enchantments) {
        // NBT implementation needed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnchantments(Enchantment... enchantments) {
        // NBT implementation needed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEnchantment(Enchantment enchantment) {
        // NBT implementation needed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllEnchantments() {
        // NBT implementation needed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseItem getBaseItem() {
        return item.getBaseItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDisplayName() {
        return item.t();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return item.s();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDisplayName(String name) {
        item.c(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDisplayName() {
        // NBT implementation needed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRepairCost() {
        return item.B();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRepairCost(int cost) {
        item.c(cost);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getLore() {
        // NBT implementation needed
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLore(String... lore) {
        // NBT implementation needed
    }

    /**
     * {@inheritDoc}
     */
    public ItemStack getHandle() {
        return item;
    }
}
