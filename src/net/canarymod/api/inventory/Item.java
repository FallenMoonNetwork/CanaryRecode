package net.canarymod.api.inventory;

import net.canarymod.api.Enchantment;
import net.canarymod.api.IEnchantment;
import net.minecraft.server.OEnchantment;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
//TODO Correctly implement this
public class Item implements IItem {

    private ItemType type;
    private int slot = -1;
    private OItemStack item;
    
    public Item(OItemStack oItemStack) {
        type = ItemType.fromId(oItemStack.c);
    }

    @Override
    public int getId() {
        return type.getId();
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
    public IEnchantment getEnchantment() {
    	return getEnchantment(0);
    }

    @Override
    public IEnchantment getEnchantment(int index) {
    	if (this.isEnchanted() && index < item.p().d() && index > -1) {
            ONBTTagCompound tag = (ONBTTagCompound) item.p().a(index);
            return new Enchantment(IEnchantment.Type.fromId(tag.e("id")), tag.e("lvl"));
        }
        return null;
    }

    @Override
    public IEnchantment[] getEnchantments() {
    	Enchantment[] enchantments = null;
        if (this.isEnchanted()) {
            enchantments = new Enchantment[item.p().d()];
            for (int index = 0; index < item.p().d(); index++) {
                ONBTTagCompound tag = (ONBTTagCompound) item.p().a(index);
                enchantments[index] = new Enchantment(IEnchantment.Type.fromId(tag.e("id")), tag.e("lvl"));
            }
        }
        return enchantments;
    }

    @Override
    public void addEnchantment(IEnchantment enchantment) {
        if (enchantment != null && enchantment.getType().getType() >= 0 && enchantment.getType().getType() < OEnchantment.b.length) {
            OEnchantment enchantmentType = OEnchantment.b[enchantment.getType().getType()];
            if (enchantmentType != null){
                item.a(enchantmentType, enchantment.getLevel());
            }
        }
    }

    @Override
    public void addEnchantments(IEnchantment[] enchantments) {
    	for(IEnchantment enchantment : enchantments){
    		addEnchantment(enchantment);
    	}
    }

    @Override
    public void setEnchantment(IEnchantment enchantment) {
        removeAllEnchantments();
        addEnchantment(enchantment);
    }

    @Override
    public void setEnchantments(IEnchantment[] enchantments) {
        removeAllEnchantments();
        addEnchantments(enchantments);
    }

    @Override
    public void removeEnchantment(IEnchantment enchantment) {
        IEnchantment[] enchants = getEnchantments();
        removeAllEnchantments();
        for(IEnchantment ench : enchants){
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
    public IBaseItem getBaseItem() {
        return item.getBaseItem();
    }
}
