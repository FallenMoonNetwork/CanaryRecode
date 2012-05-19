package net.canarymod.api;

import net.canarymod.api.entity.EntityLiving;
import net.minecraft.server.OEnchantment;

public class CanaryEnchantment implements Enchantment{
	private Type type;
	private int level;
	OEnchantment handle;
	
	public CanaryEnchantment(Type type, int level) {
		this.type = type;
		this.level = level;
	}
	
	public CanaryEnchantment(OEnchantment handle){
		this.handle = handle;
	}
	
	@Override
	public boolean canStack(Enchantment ench) {
		return handle.a(((CanaryEnchantment)ench).handle);
	}

	@Override
	public int getDamageDone(EntityLiving arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDamageModifier(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Enchantment getEnchantment(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getMaxEnchantmentLevel() {
		return handle.a();
	}

	@Override
	public int getMinEnchantmentLevel() {
		return handle.c();
	}

	@Override
	public Type getType() {
		return this.type;
	}

	@Override
	public int getWeight() {
		return handle.b();
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setType(Type type) {
		this.type = type;
		
	}

	@Override
	public void setType(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
