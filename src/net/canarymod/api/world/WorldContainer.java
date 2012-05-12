package net.canarymod.api.world;

import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldServer;

public class WorldContainer implements IWorldContainer {
   
    private World[] dimensions;
    
    private OWorldServer[] oDimensions;
    
    public long[][] nanoTicks;
    
    /**
     * The world name
     */
    private String name;
    
    public WorldContainer(String name, OWorld[] dimensions) {
        this.name = name;
        this.dimensions = new World[] {new World(dimensions[0], this), new World(dimensions[1],this), new World(dimensions[2],this)};
        nanoTicks = new long[dimensions.length][100]; //TODO: Find out what the hell this does
        oDimensions = (OWorldServer[]) dimensions;
    }
    
    public OWorldServer[] getWorldArray() {
        return oDimensions;
    }
    /**
     * Return the name of this World (WorldContainer)
     * @return
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Get the NORMAL dimension for this world container
     * @return
     */
    @Override
    public World getNormal() {
        return dimensions[0];
    }
    
    /**
     * Get the NETHER dimension for this world container
     * @return
     */
    @Override
    public World getNether() {
        return dimensions[1];
    }
    
    /**
     * Get the END dimension for this world container
     * @return
     */
    @Override
    public World getEnd() {
        return dimensions[2];
    }
    
    @Override
    public void setNanoTick(int dimensionIndex, int tickIndex, long tick) {
        nanoTicks[dimensionIndex][tickIndex] = tick;
        
    }
}
