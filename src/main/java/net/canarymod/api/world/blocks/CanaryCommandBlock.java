package net.canarymod.api.world.blocks;


import net.canarymod.Canary;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.hook.system.PermissionCheckHook;
import net.minecraft.server.TileEntityCommandBlock;


/**
 * CommandBlock wrapper implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryCommandBlock extends CanaryComplexBlock implements CommandBlock {

    /**
     * Constructs a wrapper for TileEntityCommandBlock
     *
     * @param tileentity
     *            the TileEntityCommandBlock to wrap
     */
    public CanaryCommandBlock(TileEntityCommandBlock tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getTileEntity().c_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notice(String message) {
        getTileEntity().a(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void message(String message) {
        getTileEntity().a(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(String node) {
        PermissionCheckHook hook = new PermissionCheckHook(node, this, true);
        Canary.hooks().callHook(hook);
        return hook.getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean safeHasPermission(String node) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommand(String command) {
        getTileEntity().b(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        return getTileEntity().getCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate() {
        getTileEntity().a(((CanaryWorld) getWorld()).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrefix(String prefix) {
        getTileEntity().c(prefix);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return getTileEntity().c_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityCommandBlock getTileEntity() {
        return (TileEntityCommandBlock) tileentity;
    }

}
