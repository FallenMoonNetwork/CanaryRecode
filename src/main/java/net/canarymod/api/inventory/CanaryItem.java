package net.canarymod.api.inventory;


import net.canarymod.api.Enchantment;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CanaryListTag;
import net.canarymod.api.nbt.CanaryStringTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.nbt.StringTag;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;

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
    @SuppressWarnings("unchecked")
    @Override
    public String[] getLore() {
        if (!hasLore()) {
            return null;
        }
        ListTag<StringTag> lore = (ListTag<StringTag>) getDataTag().getCompoundTag("display").getListTag("Lore");
        String[] rt = new String[lore.size()];
        for (int index = 0; index < rt.length; index++) {
            rt[index] = lore.get(index).getValue();
        }
        return rt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLore(String... lore) {
        CompoundTag tag = getDataTag();
        if (tag == null) {
            tag = new CanaryCompoundTag(new NBTTagCompound("tag"));
            setDataTag(tag);
        }
        if (!tag.containsKey("display")) {
            tag.put("display", new CanaryCompoundTag("display"));
        }
        CanaryListTag<CanaryStringTag> list = new CanaryListTag<CanaryStringTag>(new NBTTagList());
        for (String line : lore) {
            list.add(new CanaryStringTag("", line));
        }
        tag.getCompoundTag("display").put("Lore", list);
    }

    public boolean hasLore() {
        return item.p() && getDataTag().containsKey("display");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDataTag() {
        return item.p();
    }

    @Override
    public CompoundTag getDataTag() {
        return item.p() ? new CanaryCompoundTag(item.q()) : null;
    }

    public void setDataTag(CompoundTag tag) {
        getHandle().d = tag == null ? null : ((CanaryCompoundTag) tag).getHandle();
    }

    @Override
    public CompoundTag getMetaTag() {
        if (!item.p()) {
            return null;
        }
        CompoundTag dataTag = getDataTag();
        if (!dataTag.containsKey("Canary")) {
            dataTag.put("Canary", new CanaryCompoundTag("Canary"));
        }
        return dataTag.getCompoundTag("Canary");
    }

    @Override
    public CompoundTag writeToTag(CompoundTag tag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void readFromTag(CompoundTag tag) {
        // TODO Auto-generated method stub

    }

    /**
     * Gets the ItemStack being wrapped
     */
    public ItemStack getHandle() {
        return item;
    }
}
