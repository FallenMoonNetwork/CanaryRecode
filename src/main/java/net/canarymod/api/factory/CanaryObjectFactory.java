package net.canarymod.api.factory;

import net.canarymod.api.CanaryMobSpawnerEntry;
import net.canarymod.api.CanaryVillagerTrade;
import net.canarymod.api.MobSpawnerEntry;
import net.canarymod.api.VillagerTrade;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.CustomStorageInventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.NativeCustomStorageInventory;
import net.canarymod.api.inventory.PlayerInventory;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.blocks.Workbench;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.ContainerWorkbench;

/**
 * Object Factory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryObjectFactory implements ObjectFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public VillagerTrade newVillagerTrade(Item buying, Item selling) {
        return new CanaryVillagerTrade(buying, selling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VillagerTrade newVillagerTrade(Item buyingOne, Item buyingTwo, Item selling) {
        return new CanaryVillagerTrade(buyingOne, buyingTwo, selling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MobSpawnerEntry newMobSpawnerEntry(String livingEntityName) {
        return new CanaryMobSpawnerEntry(livingEntityName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MobSpawnerEntry newMobSpawnerEntry(Entity entity) {
        return new CanaryMobSpawnerEntry(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MobSpawnerEntry newMobSpawnerEntry(Item item) {
        return new CanaryMobSpawnerEntry(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomStorageInventory newCustomStorageInventory(int size) {
        return new NativeCustomStorageInventory(size).getCanaryCustomInventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomStorageInventory newCustomStorageInventory(String name, int size) {
        return new NativeCustomStorageInventory(size, name).getCanaryCustomInventory();
    }
}
