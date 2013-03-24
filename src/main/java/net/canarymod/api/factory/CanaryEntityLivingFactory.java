package net.canarymod.api.factory;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.animal.CanaryChicken;
import net.canarymod.api.entity.living.animal.CanaryCow;
import net.canarymod.api.entity.living.animal.CanaryMushroomCow;
import net.canarymod.api.entity.living.animal.CanaryOcelot;
import net.canarymod.api.entity.living.animal.CanaryPig;
import net.canarymod.api.entity.living.animal.CanarySheep;
import net.canarymod.api.entity.living.animal.CanarySquid;
import net.canarymod.api.entity.living.animal.CanaryVillager;
import net.canarymod.api.entity.living.animal.CanaryWolf;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.animal.EntityAnimal.AnimalType;
import net.canarymod.api.entity.living.monster.CanaryBlaze;
import net.canarymod.api.entity.living.monster.CanaryCreeper;
import net.canarymod.api.entity.living.monster.CanaryEnderman;
import net.canarymod.api.entity.living.monster.CanaryGhast;
import net.canarymod.api.entity.living.monster.CanaryGiantZombie;
import net.canarymod.api.entity.living.monster.CanaryLavaSlime;
import net.canarymod.api.entity.living.monster.CanaryPigZombie;
import net.canarymod.api.entity.living.monster.CanarySilverfish;
import net.canarymod.api.entity.living.monster.CanarySkeleton;
import net.canarymod.api.entity.living.monster.CanarySlime;
import net.canarymod.api.entity.living.monster.CanarySpider;
import net.canarymod.api.entity.living.monster.CanaryZombie;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.entity.living.monster.EntityMob.MobType;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.minecraft.server.OEntityBlaze;
import net.minecraft.server.OEntityChicken;
import net.minecraft.server.OEntityCow;
import net.minecraft.server.OEntityCreeper;
import net.minecraft.server.OEntityEnderman;
import net.minecraft.server.OEntityGhast;
import net.minecraft.server.OEntityGiantZombie;
import net.minecraft.server.OEntityLavaSlime;
import net.minecraft.server.OEntityMooshroom;
import net.minecraft.server.OEntityOcelot;
import net.minecraft.server.OEntityPig;
import net.minecraft.server.OEntityPigZombie;
import net.minecraft.server.OEntitySheep;
import net.minecraft.server.OEntitySilverfish;
import net.minecraft.server.OEntitySkeleton;
import net.minecraft.server.OEntitySlime;
import net.minecraft.server.OEntitySpider;
import net.minecraft.server.OEntitySquid;
import net.minecraft.server.OEntityVillager;
import net.minecraft.server.OEntityWolf;
import net.minecraft.server.OEntityZombie;

public class CanaryEntityLivingFactory implements EntityLivingFactory {

    @Override
    public EntityMob newEntityMob(String name) {
        return newEntityMob(MobType.valueOf(name));
    }

    @Override
    public EntityMob newEntityMob(String name, World world) {
        return newEntityMob(MobType.valueOf(name), world);
    }

    @Override
    public EntityMob newEntityMob(MobType type) {
        return newEntityMob(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityMob newEntityMob(MobType type, World world) {
        switch(type) {
        case BLAZE:
            return new CanaryBlaze(new OEntityBlaze(((CanaryWorld)world).getHandle()));
        case CREEPER:
            return new CanaryCreeper(new OEntityCreeper(((CanaryWorld)world).getHandle()));
        case ENDERMAN:
            return new CanaryEnderman(new OEntityEnderman(((CanaryWorld)world).getHandle()));
        case GHAST:
            return new CanaryGhast(new OEntityGhast(((CanaryWorld)world).getHandle()));
        case GIANTZOMBIE:
            return new CanaryGiantZombie(new OEntityGiantZombie(((CanaryWorld)world).getHandle()));
        case LAVASLIME:
            return new CanaryLavaSlime(new OEntityLavaSlime(((CanaryWorld)world).getHandle()));
        case PIGZOMBIE:
            return new CanaryPigZombie(new OEntityPigZombie(((CanaryWorld)world).getHandle()));
        case SILVERFISH:
            return new CanarySilverfish(new OEntitySilverfish(((CanaryWorld)world).getHandle()));
        case SKELETON:
            return new CanarySkeleton(new OEntitySkeleton(((CanaryWorld)world).getHandle()));
        case SLIME:
            return new CanarySlime(new OEntitySlime(((CanaryWorld)world).getHandle()));
        case SPIDER:
            return new CanarySpider(new OEntitySpider(((CanaryWorld)world).getHandle()));
        case ZOMBIE:
            return new CanaryZombie(new OEntityZombie(((CanaryWorld)world).getHandle()));
        }
        return null;
    }

    @Override
    public EntityAnimal newEntityAnimal(String name) {
        return newEntityAnimal(AnimalType.valueOf(name));
    }

    @Override
    public EntityAnimal newEntityAnimal(String name, World world) {
        return newEntityAnimal(AnimalType.valueOf(name), world);
    }

    @Override
    public EntityAnimal newEntityAnimal(AnimalType type) {
        return newEntityAnimal(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityAnimal newEntityAnimal(AnimalType type, World world) {
        switch(type) {
        case CHICKEN:
            return new CanaryChicken(new OEntityChicken(((CanaryWorld) world).getHandle()));
        case COW:
            return new CanaryCow(new OEntityCow(((CanaryWorld) world).getHandle()));
        case MUSHROOMCOW:
            return new CanaryMushroomCow(new OEntityMooshroom(((CanaryWorld) world).getHandle()));
        case OCELOT:
            return new CanaryOcelot(new OEntityOcelot(((CanaryWorld) world).getHandle()));
        case PIG:
            return new CanaryPig(new OEntityPig(((CanaryWorld) world).getHandle()));
        case SHEEP:
            return new CanarySheep(new OEntitySheep(((CanaryWorld) world).getHandle()));
        case SQUID:
            return new CanarySquid(new OEntitySquid(((CanaryWorld) world).getHandle()));
        case VILLAGER:
            return new CanaryVillager(new OEntityVillager(((CanaryWorld) world).getHandle()));
        case WOLF:
            return new CanaryWolf(new OEntityWolf(((CanaryWorld) world).getHandle()));
        }
        return null;
    }

}
