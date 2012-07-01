package net.canarymod.api.entity;

import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.World;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityEgg;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntitySnowball;
import net.minecraft.server.OEntityThrowable;
import net.minecraft.server.OWorld;

public class CanaryThrowable extends CanaryEntity implements Throwable{
OEntityThrowable thrown;
    public CanaryThrowable(OEntityThrowable entity) {
        super(entity);
        thrown = entity;
        // TODO Auto-generated constructor stub
    }

    @Override
    public CanaryEntityLiving getSource() {
        OEntityLiving sourcenotchian = thrown.getSource();
        CanaryEntityLiving entity = sourcenotchian.getCanaryEntityLiving();
        return entity;
    }

    @Override
    public String getType() {
        if (entity instanceof OEntitySnowball){
            return "snowball";
        }
        if (entity instanceof OEntityEgg){
            return "egg";
        }
        if (entity instanceof OEntityThrowable){
            return "enderpearl";
        }
        return null;
    }


}
