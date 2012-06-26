package net.canarymod.api.inventory;

import net.canarymod.api.CanaryEnchantment;
import net.canarymod.api.Enchantment;
import net.minecraft.server.OEnchantment;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;

public class CanaryItem implements Item {

    private ItemType type;
    private int slot = -1;
    private OItemStack item;
    
    public CanaryItem(OItemStack oItemStack) {
        type = ItemType.fromId(oItemStack.c);
        item = oItemStack;
    }

    public CanaryItem(int id, int amount) {
        this.type = ItemType.fromId(id);
        item = new OItemStack(id, amount, 0);
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
        return item.g();
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
    public void setMaxAmount(int amount) {
    	
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;

    }

    @Override
    public boolean isEnchanted() {
        return item.r();
    }

    @Override
    public Enchantment getEnchantment() {
    	return getEnchantment(0);
    }

    @Override
    public Enchantment getEnchantment(int index) {
    	if (this.isEnchanted() && index < item.p().d() && index > -1) {
            ONBTTagCompound tag = (ONBTTagCompound) item.p().a(index);
            return new CanaryEnchantment(Enchantment.Type.fromId(tag.e("id")), tag.e("lvl"));
        }
        return null;
    }

    @Override
    public Enchantment[] getEnchantments() {
    	CanaryEnchantment[] enchantments = null;
        if (this.isEnchanted()) {
            enchantments = new CanaryEnchantment[item.p().d()];
            for (int index = 0; index < item.p().d(); index++) {
                ONBTTagCompound tag = (ONBTTagCompound) item.p().a(index);
                enchantments[index] = new CanaryEnchantment(Enchantment.Type.fromId(tag.e("id")), tag.e("lvl"));
            }
        }
        return enchantments;
    }

    @Override
    public void addEnchantment(Enchantment enchantment) {
        if (enchantment != null && enchantment.getType().getId() >= 0 && enchantment.getType().getId() < OEnchantment.b.length) {
            OEnchantment enchantmentType = OEnchantment.b[enchantment.getType().getId()];
            if (enchantmentType != null){
                item.a(enchantmentType, enchantment.getLevel());
            }
        }
    }

    @Override
    public void addEnchantments(Enchantment[] enchantments) {
    	for(Enchantment enchantment : enchantments){
    		addEnchantment(enchantment);
    	}
    }

    @Override
    public void setEnchantment(Enchantment enchantment) {
        removeAllEnchantments();
        addEnchantment(enchantment);
    }

    @Override
    public void setEnchantments(Enchantment[] enchantments) {
        removeAllEnchantments();
        addEnchantments(enchantments);
    }

    @Override
    public void removeEnchantment(Enchantment enchantment) {
        Enchantment[] enchants = getEnchantments();
        removeAllEnchantments();
        for(Enchantment ench : enchants){
            if(!ench.getType().equals(enchantment.getType())){
                addEnchantment(ench);
            }
        }
    }

    @Override
    public void removeAllEnchantments() {
        item.d(null);
    }

    @Override
    public BaseItem getBaseItem() {
        return item.getBaseItem();
    }
    
    public OItemStack getHandle(){
        return item;
    }

    @Override
    public ItemType getType() {
        return type;
    }
}
