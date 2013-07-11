package net.canarymod.api;

import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryEntityItem;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.minecraft.server.EntityItem;
import net.minecraft.server.NBTTagCompound;

/**
 * MobSpawnerEntry wrapper implementation
 * 
 * @author Arron (somners)
 */
public class CanaryMobSpawnerEntry implements MobSpawnerEntry {

    private Entity entity = null;
    private int weight = 1;

    public CanaryMobSpawnerEntry() {}

    public CanaryMobSpawnerEntry(String name) {
        Entity ent = Canary.factory().getEntityFactory().newEntity(name);

        if (ent != null) {
            this.entity = ent;
        }
    }

    public CanaryMobSpawnerEntry(Entity entity) {
        this.entity = entity;
    }

    public CanaryMobSpawnerEntry(Item item) {
        entity = new CanaryEntityItem(new EntityItem(null, 0, 0, 0, ((CanaryItem) item).getHandle()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWeight(int i) {
        weight = i > 0 ? i : 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid() {
        if (this.entity != null) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseTag getSpawnPotentialsTag() {
        if (!this.isValid()) {
            return null;
        }
        net.minecraft.server.Entity ent = ((CanaryEntity) this.entity).getHandle();
        // gets the tag with the id for this entity
        CanaryCompoundTag id = new CanaryCompoundTag(new NBTTagCompound());

        ent.d(id.getHandle());

        // sets the entity and weight for this spawn
        CanaryCompoundTag entry = new CanaryCompoundTag(new NBTTagCompound());

        entry.put("Type", id.getString("id"));
        entry.put("Weight", this.getWeight());

        // sets the properties of this spawn.
        CanaryCompoundTag properties = new CanaryCompoundTag(new NBTTagCompound());

        ent.getNBTProperties(properties.getHandle());

        entry.put("Properties", properties);
        return entry;
    }

}
