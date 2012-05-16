package net.canarymod.api;

import net.canarymod.api.entity.IEntityLiving;
import net.minecraft.server.OEnchantment;

public class Enchantment implements IEnchantment{
	private Type type;
	private int level;
	
	public Enchantment(Type type, int level) {
		this.type = type;
		this.level = level;
	}
	
	public Enchantment(OEnchantment oEnchantment){
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean canStack(IEnchantment arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDamageDone(IEntityLiving arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDamageModifier(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IEnchantment getEnchantment(Type arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getMaxEnchantmentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinEnchantmentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
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
