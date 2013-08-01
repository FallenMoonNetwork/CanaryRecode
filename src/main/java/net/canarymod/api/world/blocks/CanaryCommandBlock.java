package net.canarymod.api.world.blocks;

import net.canarymod.Canary;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.config.Configuration;
import net.canarymod.hook.system.PermissionCheckHook;
import net.canarymod.user.Group;
import net.minecraft.server.TileEntityCommandBlock;

/**
 * CommandBlock wrapper implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryCommandBlock extends CanaryComplexBlock implements CommandBlock {

    Group group; // The group for permission checking

    /**
     * Constructs a wrapper for TileEntityCommandBlock
     *
     * @param tileentity
     *            the TileEntityCommandBlock to wrap
     */
    public CanaryCommandBlock(TileEntityCommandBlock tileentity) {
        super(tileentity);
        group = Canary.usersAndGroups().getGroup(Configuration.getServerConfig().getCommandBlockGroupName());
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
        PermissionCheckHook hook = new PermissionCheckHook(node, this, group.hasPermission(node));
        Canary.hooks().callHook(hook);
        return hook.getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean safeHasPermission(String node) {
        return group.hasPermission(node);
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
        getTileEntity().b(prefix);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getGroup() {
        return this.group;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

}
