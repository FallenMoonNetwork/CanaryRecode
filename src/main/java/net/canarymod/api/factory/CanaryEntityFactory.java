package net.canarymod.api.factory;


import net.canarymod.Canary;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.CanaryNonPlayableCharacter;
import net.canarymod.api.entity.living.humanoid.NonPlayableCharacter;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.entity.throwable.EntityThrowable;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityBat;
import net.minecraft.server.EntityBlaze;
import net.minecraft.server.EntityBoat;
import net.minecraft.server.EntityCaveSpider;
import net.minecraft.server.EntityChicken;
import net.minecraft.server.EntityCow;
import net.minecraft.server.EntityCreeper;
import net.minecraft.server.EntityDragon;
import net.minecraft.server.EntityEgg;
import net.minecraft.server.EntityEnderCrystal;
import net.minecraft.server.EntityEnderEye;
import net.minecraft.server.EntityEnderPearl;
import net.minecraft.server.EntityEnderman;
import net.minecraft.server.EntityExpBottle;
import net.minecraft.server.EntityFallingSand;
import net.minecraft.server.EntityFireworkRocket;
import net.minecraft.server.EntityGhast;
import net.minecraft.server.EntityGiantZombie;
import net.minecraft.server.EntityIronGolem;
import net.minecraft.server.EntityItem;
import net.minecraft.server.EntityItemFrame;
import net.minecraft.server.EntityLargeFireball;
import net.minecraft.server.EntityLightningBolt;
import net.minecraft.server.EntityMagmaCube;
import net.minecraft.server.EntityMinecartChest;
import net.minecraft.server.EntityMinecartEmpty;
import net.minecraft.server.EntityMinecartFurnace;
import net.minecraft.server.EntityMinecartHopper;
import net.minecraft.server.EntityMinecartMobSpawner;
import net.minecraft.server.EntityMinecartTNT;
import net.minecraft.server.EntityMooshroom;
import net.minecraft.server.EntityOcelot;
import net.minecraft.server.EntityPainting;
import net.minecraft.server.EntityPig;
import net.minecraft.server.EntityPigZombie;
import net.minecraft.server.EntityPotion;
import net.minecraft.server.EntitySheep;
import net.minecraft.server.EntitySilverfish;
import net.minecraft.server.EntitySkeleton;
import net.minecraft.server.EntitySlime;
import net.minecraft.server.EntitySmallFireball;
import net.minecraft.server.EntitySnowman;
import net.minecraft.server.EntitySpider;
import net.minecraft.server.EntitySquid;
import net.minecraft.server.EntityTNTPrimed;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.EntityWitch;
import net.minecraft.server.EntityWither;
import net.minecraft.server.EntityWolf;
import net.minecraft.server.EntityXPOrb;
import net.minecraft.server.EntityZombie;

/**
 * Entity Manufacturing Factory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEntityFactory implements EntityFactory {

    @Override
    public Entity newEntity(String name) {
        return newEntity(name, Canary.getServer().getDefaultWorld());
    }

    @Override
    public Entity newEntity(String name, World world) {
        if (name != null && world != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return newEntity(type, world);

        }
        return null;
    }

    @Override
    public Entity newEntity(String name, Location location) {
        if (name != null && location != null) {
            Entity toRet = newEntity(name, location.getWorld());
            if (toRet != null) {
                toRet.setX(location.getX());
                toRet.setY(location.getY());
                toRet.setZ(location.getZ());
                toRet.setRotation(location.getRotation());
                toRet.setPitch(location.getPitch());
            }
            return toRet;
        }
        return null;
    }

    @Override
    public Entity newEntity(EntityType type) {
        return newEntity(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public Entity newEntity(EntityType type, World world) {
        if (type != null && world != null) {
            net.minecraft.server.World mcworld = ((CanaryWorld) world).getHandle();
            switch (type) {
                case ARROW:
                    return new EntityArrow(mcworld).getCanaryEntity();
                case BAT:
                    return new EntityBat(mcworld).getCanaryEntity();
                case BLAZE:
                    return new EntityBlaze(mcworld).getCanaryEntity();
                case BOAT:
                    return new EntityBoat(mcworld).getCanaryEntity();
                case CAVESPIDER:
                    return new EntityCaveSpider(mcworld).getCanaryEntity();
                case CHESTMINECART:
                    return new EntityMinecartChest(mcworld).getCanaryEntity();
                case CHICKEN:
                    return new EntityChicken(mcworld).getCanaryEntity();
                case CHICKENEGG:
                    return new EntityEgg(mcworld).getCanaryEntity();
                case COW:
                    return new EntityCow(mcworld).getCanaryEntity();
                case CREEPER:
                    return new EntityCreeper(mcworld).getCanaryEntity();
                case EMPTYMINECART:
                    return new EntityMinecartEmpty(mcworld).getCanaryEntity();
                case ENDERCRYSTAL:
                    return new EntityEnderCrystal(mcworld).getCanaryEntity();
                case ENDERDRAGON:
                    return new EntityDragon(mcworld).getCanaryEntity();
                case ENDEREYE:
                    return new EntityEnderEye(mcworld).getCanaryEntity();
                case ENDERMAN:
                    return new EntityEnderman(mcworld).getCanaryEntity();
                case ENDERPERL:
                    return new EntityEnderPearl(mcworld).getCanaryEntity();
                case ENTITYITEM:
                    return new EntityItem(mcworld).getCanaryEntity();
                case ENTITYPOTION:
                    return new EntityPotion(mcworld).getCanaryEntity();
                case FALLINGBLOCK:
                    return new EntityFallingSand(mcworld).getCanaryEntity();
                case FIREWORKROCKET:
                    return new EntityFireworkRocket(mcworld).getCanaryEntity();
                case FURNACEMINECART:
                    return new EntityMinecartFurnace(mcworld).getCanaryEntity();
                case GHAST:
                    return new EntityGhast(mcworld).getCanaryEntity();
                case GIANTZOMBIE:
                    return new EntityGiantZombie(mcworld).getCanaryEntity();
                case HOPPERMINECART:
                    return new EntityMinecartHopper(mcworld).getCanaryEntity();
                case IRONGOLEM:
                    return new EntityIronGolem(mcworld).getCanaryEntity();
                case ITEMFRAME:
                    return new EntityItemFrame(mcworld).getCanaryEntity();
                case LARGEFIREBALL:
                    return new EntityLargeFireball(mcworld).getCanaryEntity();
                case LAVASLIME:
                    return new EntityMagmaCube(mcworld).getCanaryEntity();
                case LIGHTNINGBOLT: // There is a chance that LightningBolt isnt quite right
                    return new EntityLightningBolt(mcworld, 0, 0, 0).getCanaryEntity();
                case MOBSPAWNERMINECART:
                    return new EntityMinecartMobSpawner(mcworld).getCanaryEntity();
                case MOOSHROOM:
                    return new EntityMooshroom(mcworld).getCanaryEntity();
                case OCELOT:
                    return new EntityOcelot(mcworld).getCanaryEntity();
                case PAINTING:
                    return new EntityPainting(mcworld).getCanaryEntity();
                case PIG:
                    return new EntityPig(mcworld).getCanaryEntity();
                case PIGZOMBIE:
                    return new EntityPigZombie(mcworld).getCanaryEntity();
                case SHEEP:
                    return new EntitySheep(mcworld).getCanaryEntity();
                case SILVERFISH:
                    return new EntitySilverfish(mcworld).getCanaryEntity();
                case SKELETON:
                    return new EntitySkeleton(mcworld).getCanaryEntity();
                case SLIME:
                    return new EntitySlime(mcworld).getCanaryEntity();
                case SMALLFIREBALL:
                    return new EntitySmallFireball(mcworld).getCanaryEntity();
                case SNOWMAN:
                    return new EntitySnowman(mcworld).getCanaryEntity();
                case SPIDER:
                    return new EntitySpider(mcworld).getCanaryEntity();
                case SQUID:
                    return new EntitySquid(mcworld).getCanaryEntity();
                case TNTMINECART:
                    return new EntityMinecartTNT(mcworld).getCanaryEntity();
                case TNTPRIMED:
                    return new EntityTNTPrimed(mcworld).getCanaryEntity();
                case VILLAGER:
                    return new EntityVillager(mcworld).getCanaryEntity();
                case WITCH:
                    return new EntityWitch(mcworld).getCanaryEntity();
                case WITHER:
                    return new EntityWither(mcworld).getCanaryEntity();
                case WOLF:
                    return new EntityWolf(mcworld).getCanaryEntity();
                case XPBOTTLE:
                    return new EntityExpBottle(mcworld).getCanaryEntity();
                case XPORB:
                    return new EntityXPOrb(mcworld).getCanaryEntity();
                case ZOMBIE:
                    return new EntityZombie(mcworld).getCanaryEntity();
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public Entity newEntity(EntityType type, Location location) {
        if (type != null && location != null) {
            Entity toRet = newEntity(type, location.getWorld());
            if (toRet != null) {
                toRet.setX(location.getX());
                toRet.setY(location.getY());
                toRet.setZ(location.getZ());
                toRet.setRotation(location.getRotation());
                toRet.setPitch(location.getPitch());
            }
            return toRet;
        }
        return null;
    }

    @Override
    public EntityThrowable newThrowable(String name) {
        return newThrowable(name, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityThrowable newThrowable(String name, World world) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isThrowable() ? (EntityThrowable) newEntity(type, world) : null;
        }
        return null;
    }

    @Override
    public EntityThrowable newThrowable(String name, Location location) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isThrowable() ? (EntityThrowable) newEntity(type, location) : null;
        }
        return null;
    }

    @Override
    public EntityThrowable newThrowable(EntityType type) {
        return newThrowable(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityThrowable newThrowable(EntityType type, World world) {
        if (type != null && type.isThrowable()) {
            return (EntityThrowable) newEntity(type, world);
        }
        return null;
    }

    @Override
    public EntityThrowable newThrowable(EntityType type, Location location) {
        if (type != null && type.isThrowable()) {
            return (EntityThrowable) newEntity(type, location);
        }
        return null;
    }

    @Override
    public Vehicle newVehicle(String name) {
        return newVehicle(name, Canary.getServer().getDefaultWorld());
    }

    @Override
    public Vehicle newVehicle(String name, World world) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isVehicle() ? (Vehicle) newEntity(type, world) : null;
        }
        return null;
    }

    @Override
    public Vehicle newVehicle(String name, Location location) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isVehicle() ? (Vehicle) newEntity(type, location) : null;
        }
        return null;
    }

    @Override
    public Vehicle newVehicle(EntityType type) {
        return newVehicle(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public Vehicle newVehicle(EntityType type, World world) {
        if (type != null && type.isVehicle()) {
            return (Vehicle) newEntity(type, world);
        }
        return null;
    }

    @Override
    public Vehicle newVehicle(EntityType type, Location location) {
        if (type != null && type.isVehicle()) {
            return (Vehicle) newEntity(type, location);
        }
        return null;
    }

    @Override
    public EntityLiving newEntityLiving(String name) {
        return newEntityLiving(name, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityLiving newEntityLiving(String name, World world) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isLiving() ? (EntityLiving) newEntity(type, world) : null;
        }
        return null;
    }

    @Override
    public EntityLiving newEntityLiving(String name, Location location) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isLiving() ? (EntityLiving) newEntity(type, location) : null;
        }
        return null;
    }

    @Override
    public EntityLiving newEntityLiving(EntityType type) {
        return newEntityLiving(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityLiving newEntityLiving(EntityType type, World world) {
        if (type != null && type.isLiving()) {
            return (EntityLiving) newEntity(type, world);
        }
        return null;
    }

    @Override
    public EntityLiving newEntityLiving(EntityType type, Location location) {
        if (type != null && type.isLiving()) {
            return (EntityLiving) newEntity(type, location);
        }
        return null;
    }

    @Override
    public EntityAnimal newEntityAnimal(String name) {
        return newEntityAnimal(name, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityAnimal newEntityAnimal(String name, World world) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isAnimal() ? (EntityAnimal) newEntity(type, world) : null;
        }
        return null;
    }

    @Override
    public EntityAnimal newEntityAnimal(String name, Location location) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isAnimal() ? (EntityAnimal) newEntity(type, location) : null;
        }
        return null;
    }

    @Override
    public EntityAnimal newEntityAnimal(EntityType type) {
        return newEntityAnimal(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityAnimal newEntityAnimal(EntityType type, World world) {
        if (type != null && type.isAnimal()) {
            return (EntityAnimal) newEntity(type, world);
        }
        return null;
    }

    @Override
    public EntityAnimal newEntityAnimal(EntityType type, Location location) {
        if (type != null && type.isAnimal()) {
            return (EntityAnimal) newEntity(type, location);
        }
        return null;
    }

    @Override
    public EntityMob newEntityMob(String name) {
        return newEntityMob(name, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityMob newEntityMob(String name, World world) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isMob() ? (EntityMob) newEntity(type, world) : null;
        }
        return null;
    }

    @Override
    public EntityMob newEntityMob(String name, Location location) {
        if (name != null) {
            EntityType type;
            try {
                type = EntityType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException iaex) {
                return null;
            }
            return type.isMob() ? (EntityMob) newEntity(type, location) : null;
        }
        return null;
    }

    @Override
    public EntityMob newEntityMob(EntityType type) {
        return newEntityMob(type, Canary.getServer().getDefaultWorld());
    }

    @Override
    public EntityMob newEntityMob(EntityType type, World world) {
        if (type != null && type.isMob()) {
            return (EntityMob) newEntity(type, world);
        }
        return null;
    }

    @Override
    public EntityMob newEntityMob(EntityType type, Location location) {
        if (type != null && type.isMob()) {
            return (EntityMob) newEntity(type, location);
        }
        return null;
    }

    @Override
    public NonPlayableCharacter newNPC(String name, Location location, Item inHand) {
        if (name != null && location != null) {
            return new CanaryNonPlayableCharacter(name, location, inHand);
        }
        return null;
    }

}
