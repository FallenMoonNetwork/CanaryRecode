package net.canarymod.api.entity;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.EntityItemFrame;


/**
 * ItemFrame wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryItemFrame extends CanaryHangingEntity implements ItemFrame {

    public CanaryItemFrame(EntityItemFrame entity) {
        super(entity);
    }

    @Override
    public Item getItemInFrame() {
        return ((EntityItemFrame) entity).i().getCanaryItem();
    }

    @Override
    public void setItemInFrame(Item item) {
        ((EntityItemFrame) entity).a(((CanaryItem) item).getHandle());
    }

    @Override
    public int getItemRotation() {
        return ((EntityItemFrame) entity).j();
    }

    @Override
    public void setItemRotation(int rot) {
        ((EntityItemFrame) entity).b(rot);
    }

    @Override
    public float getItemDropChance() {
        return ((EntityItemFrame) entity).e;
    }

    @Override
    public void setItemDropChance(float chance) {
        ((EntityItemFrame) entity).e = chance;
    }

    @Override
    public EntityItemFrame getHandle() {
        return (EntityItemFrame) entity;
    }

}
