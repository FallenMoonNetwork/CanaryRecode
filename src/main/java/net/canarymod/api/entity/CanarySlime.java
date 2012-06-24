package net.canarymod.api.entity;

import net.minecraft.server.OEntitySlime;
/**
 * 
 * @author Jos
 * @author Chris
 *
 */
public class CanarySlime extends CanaryEntityMob implements Slime {

    public CanarySlime(OEntitySlime entity) {
        super(entity);
    }

    @Override
    public Size getSize() {
        return Size.fromByte((byte)((OEntitySlime)entity).L());
    }

    @Override
    public void setSize(Size size) {
        ((OEntitySlime)entity).c(size.getByte());
    }

    @Override
    public int getMaxHealth() {
        return ((OEntitySlime)entity).d();
    }
}
