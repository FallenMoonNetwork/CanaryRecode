package net.canarymod.api.inventory;

import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CanaryListTag;
import net.canarymod.api.nbt.CanaryStringTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.nbt.StringTag;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;

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
     * @param itemStack
     */
    public CanaryItem(ItemStack itemStack) {
        this.type = ItemType.fromIdAndData(itemStack.d, itemStack.k());
        if (this.type == null) {
            // Seems to be an unregistered item type, go ahead an pass an new unnamed itemtype
            this.type = new ItemType(itemStack.d, itemStack.k());
        }
        this.item = itemStack;
    }

    public CanaryItem(int id, int amount) {
        this.type = ItemType.fromId(id);
        if (this.type == null) {
            // Seems to be an unregistered item type, go ahead an pass an new unnamed itemtype
            this.type = new ItemType(id);
        }
        this.item = new ItemStack(id, amount, 0);
    }

    public CanaryItem(int itemId, int amount, int damage) {
        this.item = new ItemStack(itemId, amount, damage);
        this.type = ItemType.fromIdAndData(itemId, damage);
        if (this.type == null) {
            // Seems to be an unregistered item type, go ahead an pass an new unnamed itemtype
            this.type = new ItemType(itemId, damage);
        }
    }

    public CanaryItem(int itemId, int amount, int damage, int slot) {
        this.item = new ItemStack(itemId, amount, damage);
        this.slot = slot;
        this.type = ItemType.fromIdAndData(itemId, damage);
        if (this.type == null) {
            // Seems to be an unregistered item type, go ahead an pass an new unnamed itemtype
            this.type = new ItemType(itemId, damage);
        }
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
        type = ItemType.fromIdAndData(id, type.getData());
        item.d = type.getId();
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
        return item.b;
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
        item.b = amount;
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
    public ItemType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDisplayName() {
        return item.u();
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
        getDataTag().getCompoundTag("display").remove("Name");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRepairCost() {
        return item.C();
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
        if (!hasLore()) {
            return null;
        }
        ListTag<StringTag> lore = getDataTag().getCompoundTag("display").getListTag("Lore");
        String[] rt = new String[lore.size()];

        for (int index = 0; index < rt.length; index++) {
            rt[index] = ((CanaryStringTag) lore.get(index)).getValue();
        }
        return rt;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void setLore(String... lore) {
        CompoundTag tag = getDataTag();

        if (tag == null) {
            tag = new CanaryCompoundTag("tag");
            setDataTag(tag);
        }
        if (!tag.containsKey("display")) {
            tag.put("display", new CanaryCompoundTag("display"));
        }
        CanaryListTag<StringTag> list = new CanaryListTag("Lore");

        for (String line : lore) {
            list.add(new CanaryStringTag("line" + list.size(), line));
        }
        tag.getCompoundTag("display").put("Lore", list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasLore() {
        if (hasDataTag()) {
            if (getDataTag().containsKey("display")) {
                if (getDataTag().getCompoundTag("display").containsKey("Lore")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnchanted() {
        return item.y();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnchantable() {
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
        if (isEnchanted()) {
            int size = getHandle().r().c();

            if (index >= size) {
                index = 0;
            }
            CompoundTag tag = new CanaryCompoundTag((NBTTagCompound) getHandle().r().b(index));

            return new CanaryEnchantment(Enchantment.Type.fromId(tag.getShort("id")), tag.getShort("lvl"));
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enchantment[] getEnchantments() {
        Enchantment[] enchantments = null;

        if (isEnchanted()) {
            int size = getHandle().r().c();

            enchantments = new Enchantment[size];
            CanaryListTag<CompoundTag> nbtTagList = new CanaryListTag<CompoundTag>(getHandle().r());

            for (int i = 0; i < size; i++) {
                CompoundTag tag = nbtTagList.get(i);
                enchantments[i] = new CanaryEnchantment(Enchantment.Type.fromId(tag.getShort("id")), tag.getShort("lvl"));
            }
        }
        return enchantments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEnchantments(Enchantment... enchantments) {
        if (enchantments != null) {
            for (Enchantment ench : enchantments) {
                getHandle().a(((CanaryEnchantment) ench).getHandle(), ench.getLevel());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnchantments(Enchantment... enchantments) {
        removeAllEnchantments();
        addEnchantments(enchantments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEnchantment(Enchantment enchantment) {
        Enchantment[] enchs = getEnchantments();
        removeAllEnchantments();
        for (Enchantment ench : enchs) {
            if (ench.getType() == enchantment.getType() && ench.getLevel() == enchantment.getLevel()) {
                continue;
            }
            getHandle().a(((CanaryEnchantment) ench).getHandle(), ench.getLevel());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllEnchantments() {
        if (isEnchanted()) {
            getDataTag().remove("ench");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDataTag() {
        return item.p();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag getDataTag() {
        return item.p() ? new CanaryCompoundTag(item.q()) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDataTag(CompoundTag tag) {
        getHandle().e = tag == null ? null : ((CanaryCompoundTag) tag).getHandle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasMetaTag() {
        if (hasDataTag()) {
            return getDataTag().containsKey("Canary");
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag getMetaTag() {
        CompoundTag dataTag = getDataTag();

        if (dataTag == null) {
            dataTag = new CanaryCompoundTag("tag");
            setDataTag(dataTag);
        }
        if (!dataTag.containsKey("Canary")) {
            dataTag.put("Canary", new CanaryCompoundTag("Canary"));
        }
        return dataTag.getCompoundTag("Canary");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag writeToTag(CompoundTag tag) {
        return new CanaryCompoundTag(getHandle().b(((CanaryCompoundTag) tag).getHandle()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readFromTag(CompoundTag tag) {
        getHandle().c(((CanaryCompoundTag) tag).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseItem getBaseItem() {
        return item.getBaseItem();
    }

    /**
     * Gets the ItemStack being wrapped
     */
    public ItemStack getHandle() {
        return item;
    }

    public static Item[] stackArrayToItemArray(ItemStack[] stackarray) {
        CanaryItem[] items = new CanaryItem[stackarray.length];

        for (int index = 0; index < stackarray.length; index++) {
            if (stackarray[index] != null) {
                items[index] = stackarray[index].getCanaryItem();
                items[index].setSlot(index);
            }
        }
        return items;
    }

    public static ItemStack[] itemArrayToStackArray(Item[] itemarray) {
        ItemStack[] stacks = new ItemStack[itemarray.length];

        for (int index = 0; index < itemarray.length; index++) {
            if (itemarray[index] != null) {
                stacks[index] = ((CanaryItem) itemarray[index]).getHandle();
            }
        }
        return stacks;
    }

    @Override
    public Item clone() {
        return getHandle().m().getCanaryItem();
    }

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Item[id=%d, amount=%d, slot=%d, damage=%d, Name=%s, isEnchanted=%b, hasLore=%b]", getId(), getAmount(), slot, getDamage(), getDisplayName(), isEnchanted(), hasLore());
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemStack) {
            return ItemStack.b(item, (ItemStack) obj);
        } else if (obj instanceof CanaryItem) {
            return ItemStack.b(item, ((CanaryItem) obj).getHandle());
        }
        return false;
    }

    /**
     * Returns a semi-unique hashcode for this object
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 97 * hash + getId();
        hash = 97 * hash + getAmount();
        hash = 97 * hash + slot;
        hash = 97 * hash + getDamage();
        return hash;
    }
}
