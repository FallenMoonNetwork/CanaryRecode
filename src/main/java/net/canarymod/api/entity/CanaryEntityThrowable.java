package net.canarymod.api.entity;

import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.World;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityEgg;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntitySnowball;
import net.minecraft.server.OEntityThrowable;
import net.minecraft.server.OWorld;

public class CanaryEntityThrowable extends CanaryEntity implements EntityThrowable{
OEntityThrowable thrown;
    public CanaryEntityThrowable(OEntityThrowable entity) {
        super(entity);
        thrown = entity;
        // TODO Auto-generated constructor stub
    }

    @Override
    public EntityLiving getSource() {
        OEntityLiving sourcenotchian = thrown.getSource();
        CanaryEntityLiving entity = sourcenotchian.getCanaryEntityLiving();
        return entity;
    }
    public OEntityThrowable getEntity(){
        return thrown;
    }
    @Override
    public ThrowableType getType() {
        if (entity instanceof OEntitySnowball){
            return ThrowableType.SNOWBALL;
        }
        if (entity instanceof OEntityEgg){
            return ThrowableType.EGG;
        }
        if (entity instanceof OEntityThrowable){
            return ThrowableType.ENDERPEARL;
        }
        else{
            return null;
        }
    }


}
