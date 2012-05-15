package net.canarymod.api.inventory;

import net.canarymod.api.IEnchantment;
import net.minecraft.server.OItemStack;
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IEnchantment getEnchantment() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IEnchantment getEnchantment(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IEnchantment[] getEnchantments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addEnchantment(IEnchantment enchantment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addEnchantments(IEnchantment[] enchantments) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEnchantment(IEnchantment enchantment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEnchantments(IEnchantment[] enchantment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeEnchantment(IEnchantment enchantment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeAllEnchantments() {
        // TODO Auto-generated method stub

    }

    @Override
    public IBaseItem getBaseItem() {
        return item.getBaseItem();
    }

}
