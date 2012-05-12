package net.canarymod.api.inventory;

import net.canarymod.api.IEnchantment;

public class Item implements IItem {

    private ItemType _type;
    
    private int _damage;
    
    private int _amount;
    
    private int _slot;
    
    @Override
    public int getId() {
        return _type.getId();
    }

    @Override
    public int getDamage() {
        return _damage;
    }

    @Override
    public int getAmount() {
        return _amount;
    }

    @Override
    public int getSlot() {
        return _slot;
    }

    @Override
    public void setAmount(int amount) {
        _amount = amount;
    }

    @Override
    public void setDamage(int damage) {
        _damage = damage;

    }
    
    @Override
    public int getMaxAmount() {
    	return -1;
    }
    
    @Override
    public void setMaxAmount(int amount) {
    	
    }

    @Override
    public void setSlot(int slot) {
        _slot = slot;

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

}
