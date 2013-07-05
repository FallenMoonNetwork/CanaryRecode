package net.canarymod.api.inventory;

import java.util.Arrays;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.minecraft.server.AnimalChest;
import net.minecraft.server.ItemStack;

/**
 * AnimalInventory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryAnimalInventory extends CanaryContainerEntity implements AnimalInventory {
    private final EntityAnimal owner;

    public CanaryAnimalInventory(AnimalChest animalChest, EntityAnimal owner) {
        super(animalChest);
        this.owner = owner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getInventoryHandle().c, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getInventoryHandle().c, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getInventoryHandle().c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        getInventoryHandle().c = Arrays.copyOf(CanaryItem.itemArrayToStackArray(items), getInventoryHandle().c.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getInventoryHandle().setName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        getInventoryHandle().k_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityAnimal getOwner() {
        return owner;
    }

    private AnimalChest getInventoryHandle() {
        return (AnimalChest) inventory;
    }
}
