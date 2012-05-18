package net.canarymod.api.entity;

import net.minecraft.server.OEntitySlime;

// TODO in notch-code, slime extends entityliving, not mob. So the constructor has errorz
public class CanarySlime extends CanaryEntityMob implements Slime {

	public CanarySlime(OEntitySlime entity) {
		super(entity);
	}
	
	@Override
	public Size getSize() {
		// TODO Auto-generated method stub
		int size = ((OEntitySlime)entity).L();
		// TODO translate int-size to Size  BIG SMALL TINY
		return null;
	}

	@Override
	public void setSize(Size size) {
		// TODO figure out the slime-sizes from BIG SMALL TINY
//		((OEntitySlime)entity).c(size);
	}

	@Override
	public int getMaxHealth() {
		return ((OEntitySlime)entity).d();
	}
}
