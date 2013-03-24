package net.canarymod.api.entity.living.monster;

import net.minecraft.server.EntitySlime;

/**
 * @author Jos
 * @author Chris
 */
public class CanarySlime extends CanaryEntityMob implements Slime {

    public CanarySlime(EntitySlime entity) {
        super(entity);
    }

    @Override
    public Size getSize() {
        return Size.fromByte((byte) ((EntitySlime) entity).L());
    }

    @Override
    public void setSize(Size size) {
        ((EntitySlime) entity).c(size.getByte());
    }

    @Override
    public int getMaxHealth() {
        return ((EntitySlime) entity).d();
    }
}
