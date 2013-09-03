package net.canarymod.api.world.blocks;

import java.util.Arrays;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.CanaryBlockInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.InventoryType;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.minecraft.server.ContainerRepair;
import net.minecraft.server.InventoryBasic;
import net.minecraft.server.InventoryCraftResult;
import net.minecraft.server.ItemStack;

/**
 * The implementation of Anvil
 * 
 * @author Somners
 */
public class CanaryAnvil extends CanaryBlockInventory implements Anvil {

    private ContainerRepair container = null;

    public CanaryAnvil(ContainerRepair container) {
        super(container.g);
        this.container = container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.ANVIL;
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
    public String getToolName() {
        return container.getToolName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToolName(String name) {
        container.a(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getResult() {
        ItemStack stack = getCraftResult().a(0xCAFEBABE);

        return stack == null ? null : stack.getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResult(Item item) {
        getCraftResult().a(0xCAFEBABE, ((CanaryItem) item).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getXPCost() {
        return this.container.a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXPCost(int level) {
        this.container.a = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.container.getPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public net.minecraft.server.TileEntity getTileEntity() {
        throw new UnsupportedOperationException("Method 'getTileEntity' in class 'CanaryAnvil' is not supported. Not a tile Entity.");
    }

    public ContainerRepair getContainer() {
        return container;
    }

    private InventoryBasic getInventory() {
        return (InventoryBasic) container.g;
    }

    private InventoryCraftResult getCraftResult() {
        return (InventoryCraftResult) container.f;
    }

}
