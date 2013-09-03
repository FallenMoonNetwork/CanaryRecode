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
public class CanaryAnimalInventory extends CanaryEntityInventory implements AnimalInventory {
    private final EntityAnimal owner;

    public CanaryAnimalInventory(AnimalChest animalChest, EntityAnimal owner) {
        super(animalChest);
        this.owner = owner;
    }

    public InventoryType getInventoryType() {
        return InventoryType.ANIMAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getHandle().c, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getHandle().c, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getHandle().c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        getHandle().c = Arrays.copyOf(CanaryItem.itemArrayToStackArray(items), getHandle().c.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getHandle().setName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        getHandle().k_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityAnimal getOwner() {
        return owner;
    }

    @Override
    public AnimalChest getHandle() {
        return (AnimalChest) inventory;
    }
}
