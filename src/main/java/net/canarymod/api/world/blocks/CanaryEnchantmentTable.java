package net.canarymod.api.world.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.InventoryType;
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
        this.container = container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.ENCHANTMENT;
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
    public int getNumBookshelves() {
        return getBookshelves().length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Block[] getBookshelves() {
        ArrayList<Block> bookshelves = new ArrayList<Block>();
        for (int i1 = -1; i1 <= 1; ++i1) {
            for (int i2 = -1; i2 <= 1; ++i2) {
                if ((i1 != 0 || i2 != 0) && getWorld().getBlockAt(getX() + i2, getY(), getZ() + i1).isAir() && getWorld().getBlockAt(getX() + i2, getY() + 1, getZ() + i1).isAir()) {
                    Block bookshelf = getWorld().getBlockAt(getX() + i2 * 2, getY(), getZ() + i1 * 2);
                    if (bookshelf.getType() == BlockType.Bookshelf) {
                        bookshelves.add(bookshelf);
                    }

                    bookshelf = getWorld().getBlockAt(getX() + i2 * 2, getY() + 1, getZ() + i1 * 2);
                    if (bookshelf.getType() == BlockType.Bookshelf) {
                        bookshelves.add(bookshelf);
                    }

                    if (i2 != 0 && i1 != 0) {
                        bookshelf = getWorld().getBlockAt(getX() + i2 * 2, getY(), getZ() + i1);
                        if (bookshelf.getType() == BlockType.Bookshelf) {
                            bookshelves.add(bookshelf);
                        }

                        bookshelf = getWorld().getBlockAt(getX() + i2 * 2, getY() + 1, getZ() + i1);
                        if (bookshelf.getType() == BlockType.Bookshelf) {
                            bookshelves.add(bookshelf);
                        }

                        bookshelf = getWorld().getBlockAt(getX() + i2, getY(), getZ() + i1 * 2);
                        if (bookshelf.getType() == BlockType.Bookshelf) {
                            bookshelves.add(bookshelf);
                        }

                        bookshelf = getWorld().getBlockAt(getX() + i2, getY() + 1, getZ() + i1 * 2);
                        if (bookshelf.getType() == BlockType.Bookshelf) {
                            bookshelves.add(bookshelf);
                        }
                    }
                }
            }
        }
        return bookshelves.toArray(new Block[bookshelves.size()]);
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
        return (TileEntityEnchantmentTable) getWorld().getTileEntityAt(getX(), getY(), getZ());
    }

    private InventoryBasic getInventory() {
        return (InventoryBasic) container.a;
    }
}
