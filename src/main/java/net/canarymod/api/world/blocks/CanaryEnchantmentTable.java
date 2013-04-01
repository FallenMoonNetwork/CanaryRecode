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

public class CanaryEnchantmentTable extends CanaryContainerBlock implements EnchantmentTable {
    private ContainerEnchantment container;

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

    @Override
    public void update() {
        container.b();
    }

    public boolean enchantItem(Player player, int slot) {
        return container.a(((CanaryPlayer) player).getHandle(), slot);
    }

    public int[] getEnchantLevels() {
        return container.g;
    }

    @Override
    public void clearContents() {
        Arrays.fill(getInventory().c, null);
    }

    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getInventory().c, getSize());
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getInventory().c);
    }

    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getInventory().c, 0, getSize());
    }

    @Override
    public void setInventoryName(String value) {
        // getInventory().setName(value);
    }

    @Override
    public TileEntityEnchantmentTable getTileEntity() {
        return (TileEntityEnchantmentTable) getWorld().getComplexBlockAt(getX(), getY(), getZ());
    }

    /**
     * @throws UnsupportedOperationException
     *             this isn't a Minecraft Container instance
     */
    @Override
    public ContainerEnchantment getContainer() {
        return container;
    }

    private InventoryBasic getInventory() {
        return (InventoryBasic) container.a;
    }
}
