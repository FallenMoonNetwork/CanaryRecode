package net.canarymod.api.world;

import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldServer;

public class World implements IWorld {
   
    private Dimension[] dimensions;
    
    private OWorldServer[] oDimensions;
    
    public long[][] nanoTicks;
    
    /**
     * The world name
     */
    private String name;
    
    public World(String name, OWorld[] dimensions) {
        this.name = name;
        this.dimensions = new Dimension[] {new Dimension(dimensions[0], this), new Dimension(dimensions[1],this), new Dimension(dimensions[2],this)};
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
    public Dimension getNormal() {
        return dimensions[0];
    }
    
    /**
     * Get the NETHER dimension for this world container
     * @return
     */
    @Override
    public Dimension getNether() {
        return dimensions[1];
    }
    
    /**
     * Get the END dimension for this world container
     * @return
     */
    @Override
    public Dimension getEnd() {
        return dimensions[2];
    }
    
    @Override
    public void setNanoTick(int dimensionIndex, int tickIndex, long tick) {
        nanoTicks[dimensionIndex][tickIndex] = tick;
        
    }
}
