package net.canarymod.api.entity.living.monster;

import net.minecraft.server.EntityEnderman;

public class CanaryEnderman extends CanaryEntityMob implements Enderman{

    public CanaryEnderman(EntityEnderman entity){
        super(entity);
    }

    @Override
    public short getCarriedBlockID(){
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setCarriedBlockID(short blockId){
        // TODO Auto-generated method stub

    }

    @Override
    public short getCarriedBlockMetaData(){
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setCarriedBlockMetaData(short metadata){
        // TODO Auto-generated method stub

    }

    @Override
    public boolean randomTeleport(){
        return ((EntityEnderman) entity).x();
    }
}
