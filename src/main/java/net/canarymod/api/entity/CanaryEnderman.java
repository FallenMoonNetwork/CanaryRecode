package net.canarymod.api.entity;

import net.minecraft.server.OEntityEnderman;

public class CanaryEnderman extends CanaryEntityMob implements Enderman {

	public CanaryEnderman(OEntityEnderman entity) {
		super(entity);
	}
	
	@Override
	public int getCarriedBlock() {
		return ((OEntityEnderman)entity).E();
	}

	@Override
	public void setCarriedBlock(int blockId) {
		((OEntityEnderman)entity).e(blockId);
	}

	@Override
	public boolean randomTeleport() {
		return ((OEntityEnderman)entity).x();
	}

}
