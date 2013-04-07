package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.potion.PotionEffectType;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityBeacon;


/**
 * Beacon wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBeacon extends CanaryContainerBlock implements Beacon {

    /**
     * Constructs a new wrapper for TileEntityBeacon
     * 
     * @param tileentity
     *            the TileEntityBeacon to wrap
     */
    public CanaryBeacon(TileEntityBeacon tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEffect(PotionEffectType effect) {
        return isValidEffectAtLevels(effect, TileEntityBeacon.a.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEffectAtLevels(PotionEffectType effect, int levels) {
        net.minecraft.server.Potion[][] potions = TileEntityBeacon.a;

        for (int i = 0; i < levels && i < potions.length; i++) {
            for (int j = 0; j < potions[i].length; j++) {
                if (potions[i][j].H == effect.getID()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PotionEffectType getPrimaryEffect() {
        return PotionEffectType.fromId(this.getTileEntity().j());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrimaryEffect(PotionEffectType effect) {
        this.getTileEntity().d(effect.getID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrimaryEffectDirectly(PotionEffectType effect) {
        this.getTileEntity().setPrimaryEffectDirectly(effect.getID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PotionEffectType getSecondaryEffect() {
        return PotionEffectType.fromId(this.getTileEntity().k());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSecondaryEffect(PotionEffectType effect) {
        this.getTileEntity().e(effect.getID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSecondaryEffectDirectly(PotionEffectType effect) {
        this.getTileEntity().setSecondaryEffectDirectly(effect.getID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevels() {
        return getTileEntity().l();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLevels(int levels) {
        getTileEntity().setLevels(levels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        getTileEntity().a(0, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack stack = getTileEntity().a(0);
        Item[] item = new Item[] { stack == null ? null : stack.getCanaryItem() };

        getTileEntity().a(0, null);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        ItemStack stack = getTileEntity().a(0);

        return new Item[] { stack == null ? null : stack.getCanaryItem() };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        getTileEntity().a(0, items[0] != null ? ((CanaryItem) items[0]).getHandle() : null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getTileEntity().a(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityBeacon getTileEntity() {
        return (TileEntityBeacon) tileentity;
    }
}
