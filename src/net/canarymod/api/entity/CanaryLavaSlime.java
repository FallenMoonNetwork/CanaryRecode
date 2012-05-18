package net.canarymod.api.entity;

import net.minecraft.server.OEntityLavaSlime;

public class CanaryLavaSlime extends CanarySlime implements LavaSlime {
	
	public CanaryLavaSlime(OEntityLavaSlime entity) {
		super(entity);
	}
}
