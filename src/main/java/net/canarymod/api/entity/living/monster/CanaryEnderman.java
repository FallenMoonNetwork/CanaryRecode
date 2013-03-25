package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntityEnderman;


/**
 * Enderman wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderman extends CanaryEntityMob implements Enderman {

    public CanaryEnderman(EntityEnderman entity) {
        super(entity);
    }

    @Override
    public short getCarriedBlockID() {
        return (short) ((EntityEnderman) entity).o();
    }

    @Override
    public void setCarriedBlockID(short blockId) {
        ((EntityEnderman) entity).a(blockId);
    }

    @Override
    public short getCarriedBlockMetaData() {
        return (short) ((EntityEnderman) entity).p();
    }

    @Override
    public void setCarriedBlockMetaData(short metadata) {
        ((EntityEnderman) entity).s(metadata);
    }

    @Override
    public boolean randomTeleport() {
        return ((EntityEnderman) entity).m();
    }
}
