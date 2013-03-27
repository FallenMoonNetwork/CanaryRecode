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

    @Override
    public int getId() {
        return type.getId();
    }

    @Override
    public void setId(int id) {
        type = ItemType.fromId(id);
        item.c = type.getId();
    }

    @Override
    public int getDamage() {
        return item.k();
    }

    @Override
    public int getAmount() {
        return item.a;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public void setAmount(int amount) {
        item.a = amount;
    }

    @Override
    public void setDamage(int damage) {
        item.b(damage);

    }

    @Override
    public int getMaxAmount() {
        return item.getBaseItem().getMaxStackSize();
    }

    @Override
    public void setMaxAmount(int amount) {}

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public boolean isEnchanted() {
        return item.x();
    }

    @Override
    public Enchantment getEnchantment() {
        return getEnchantment(0);
    }

    @Override
    public Enchantment getEnchantment(int index) {
        // NBT implementation needed
        return null;
    }

    @Override
    public Enchantment[] getEnchantments() {
        // NBT implementation needed
        return null;
    }
    @Override
    public void addEnchantments(Enchantment... enchantments) {
        // NBT implementation needed
    }

    @Override
    public void setEnchantments(Enchantment... enchantments) {
        // NBT implementation needed
    }

    @Override
    public void removeEnchantment(Enchantment enchantment) {
        // NBT implementation needed
    }

    @Override
    public void removeAllEnchantments() {
        // NBT implementation needed
    }

    @Override
    public BaseItem getBaseItem() {
        return item.getBaseItem();
    }

    public ItemStack getHandle() {
        return item;
    }

    @Override
    public ItemType getType() {
        return type;
    }

    @Override
    public boolean hasDisplayName() {
        return item.t();
    }

    @Override
    public String getDisplayName() {
        return item.s();
    }

    @Override
    public void setDisplayName(String name) {
        item.c(name);
    }

    @Override
    public void removeDisplayName() {
        // NBT implementation needed
    }

    @Override
    public int getRepairCost() {
        return item.B();
    }

    @Override
    public void setRepairCost(int cost) {
        item.c(cost);
    }

    @Override
    public String[] getLore() {
        // NBT implementation needed
        return null;
    }

    @Override
    public void setLore(String... lore) {
        // NBT implementation needed
    }
}
