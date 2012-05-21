package net.canarymod.api.world;

import net.canarymod.api.entity.Player;
import net.minecraft.server.OWorldServer;

public class CanaryWorld implements World {
   
    private CanaryDimension[] dimensions;
    private OWorldServer[] oDimensions;
    public long[][] nanoTicks;
    private boolean enabled;
    
    /**
     * The world name
     */
    private String name;
    
    public CanaryWorld(String name, OWorldServer[] dimensions) {
        this.name = name;
        this.dimensions = new CanaryDimension[] {new CanaryDimension(dimensions[0], this),new CanaryDimension(dimensions[1],this),new CanaryDimension(dimensions[2],this)};
        nanoTicks = new long[dimensions.length][100]; //TODO: Find out what the hell this does
        oDimensions = dimensions;
    }
    
    public OWorldServer[] getDimensionArray() {
        return this.oDimensions;
    }

    /**
     * Return the name of this World (WorldContainer)
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * Get the NORMAL dimension for this world container
     * @return
     */
    @Override
    public CanaryDimension getNormal() {
        return this.dimensions[0];
    }
    
    /**
     * Get the NETHER dimension for this world container
     * @return
     */
    @Override
    public CanaryDimension getNether() {
        return this.dimensions[1];
    }
    
    /**
     * Get the END dimension for this world container
     * @return
     */
    @Override
    public CanaryDimension getEnd() {
        return this.dimensions[2];
    }
    
    @Override
    public Dimension getDimension(DimensionType dimension) {
    	return this.dimensions[dimension.getIntValue()];
    }
    
    @Override
    public void setNanoTick(DimensionType dimension, int tickIndex, long tick) {
        nanoTicks[dimension.getIntValue()][tickIndex] = tick;
    }

    @Override
	public void setEnabled(boolean enabled) {
    	this.enabled = enabled; // TODO: implement
    }
	
    @Override
	public boolean isEnabled() {
    	return this.enabled;
    }
	
    @Override
	public boolean canEnterWorld(Player player) {
    	return player.hasPermission("world.traveling."+name+".enter") && isEnabled();
    }
	
    @Override
	public boolean canLeaveWorld(Player player) {
    	return player.hasPermission("world.traveling."+name+".leave");
    }
}
