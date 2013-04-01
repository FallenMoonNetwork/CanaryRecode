package net.canarymod.api.world.blocks;

import java.util.Arrays;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.minecraft.server.ContainerEnchantment;
import net.minecraft.server.InventoryBasic;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityEnchantmentTable;

/**
 * EnchantmentTable wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnchantmentTable extends CanaryContainerBlock implements EnchantmentTable {
    private ContainerEnchantment container;

    /**
     * Constructs a new CanaryEnchantmentTable
     * 
     * @param container
     *            the ContainerEnchantment to wrap
     */
    public CanaryEnchantmentTable(ContainerEnchantment container) {
        super(container.a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return container.i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return container.j;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getZ() {
        return container.k;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return container.h.getCanaryWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        container.b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean enchantItem(Player player, int slot) {
        return container.a(((CanaryPlayer) player).getHandle(), slot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getEnchantLevels() {
        return container.g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getInventory().c, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getInventory().c, getSize());
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getInventory().c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getInventory().c, 0, getSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getInventory().setName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityEnchantmentTable getTileEntity() {
        return (TileEntityEnchantmentTable) getWorld().getComplexBlockAt(getX(), getY(), getZ());
    }

    private InventoryBasic getInventory() {
        return (InventoryBasic) container.a;
    }
}
