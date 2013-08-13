package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.CanaryLivingBase;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.PlayerInventory;
import net.minecraft.server.EntityPlayer;

/**
 * Human implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryHuman extends CanaryLivingBase implements Human {
    protected String prefix = null;

    public CanaryHuman(EntityPlayer entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getHandle().c_();
    }

    /**
     * {@inheritDoc}
     */
    public String getDisplayName() {
        return getHandle().getDisplayName();
    }

    /**
     * {@inheritDoc}
     */
    public void setDisplayName(String name) {
        getHandle().setDisplayName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void kill() {
        super.kill();
        dropInventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityItem[] dropInventory() {
        Item[] items = getInventory().getContents();
        EntityItem[] drops = new EntityItem[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                continue;
            }
            drops[i] = getWorld().dropItem(getPosition(), items[i]);

        }
        getInventory().clearContents();
        return drops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBlocking() {
        return getHandle().bq();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyItemHeld() {
        getHandle().bu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItemHeld() {
        Item item = ((CanaryPlayerInventory) getInventory()).getItemInHand();

        if (item != null) {
            item.setSlot(((CanaryPlayerInventory) getInventory()).getSelectedHotbarSlotId());
            return item;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dropItem(Item item) {
        getWorld().dropItem(getPosition(), item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventory getInventory() {
        return getHandle().getPlayerInventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void giveItem(Item item) {
        getHandle().getPlayerInventory().addItem(item);
        getHandle().getPlayerInventory().update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HumanCapabilities getCapabilities() {
        return getHandle().bG.getCanaryCapabilities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityPlayer getHandle() {
        return (EntityPlayer) entity;
    }
}
