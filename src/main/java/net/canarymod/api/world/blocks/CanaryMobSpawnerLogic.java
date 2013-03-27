package net.canarymod.api.world.blocks;

import java.util.ArrayList;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.MobSpawnerBaseLogic;

/**
 * Implementation of MobSpawnerLogic
 *
 * @author Somners
 */
public class CanaryMobSpawnerLogic implements MobSpawnerLogic {

    private MobSpawnerBaseLogic logic;

    public CanaryMobSpawnerLogic (MobSpawnerBaseLogic msbl) {
        logic = msbl;
    }

    @Override
    public void setSpawns(String... spawn) {
        List<Entity> toSet = new ArrayList<Entity>();

        for (String s : spawn) {
            Entity entity = Canary.factory().getEntityFactory().newEntity(s);
            if (entity != null) {
                toSet.add(entity);
            }
        }
        this.setSpawnedEntity(toSet.toArray(new Entity[toSet.size()]));
    }

    @Override
    public String[] getSpawns() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDelay(int delay) {
        logic.b = delay;
    }

    @Override
    public int getMinDelay() {
        return logic.g;
    }

    @Override
    public void setMinDelay(int delay) {
        logic.g = delay;
    }

    @Override
    public int getMaxDelay() {
        return logic.h;
    }

    @Override
    public void setMaxDelay(int delay) {
        logic.h = delay;
    }

    @Override
    public int getSpawnCount() {
        return this.logic.i;
    }

    @Override
    public void setSpawnCount(int count) {
        this.logic.i = count;
    }

    @Override
    public int getMaxNearbyEntities() {
        return this.logic.k;
    }

    @Override
    public void setMaxNearbyEntities(int entities) {
        this.logic.k = entities;
    }

    @Override
    public int getRequiredPlayerRange() {
        return this.logic.l;
    }

    @Override
    public void setRequiredPlayerRange(int range) {
        this.logic.l = range;
    }

    @Override
    public int getSpawnRange() {
        return this.logic.m;
    }

    @Override
    public void setSpawnRange(int range) {
        this.logic.m = range;
    }

    @Override
    public void setSpawnedEntity(Entity... entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSpawnedEntity(Item... item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void setSpawnedEntities(net.minecraft.server.Entity... entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
