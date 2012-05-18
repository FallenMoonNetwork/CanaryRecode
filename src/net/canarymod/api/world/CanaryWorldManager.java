package net.canarymod.api.world;

/**
 * This is a container for all of the worlds.
 * @author Jos Kuijpers
 *
 */
public class CanaryWorldManager implements WorldManager {
   
    private CanaryWorld[] worlds;
    
    public CanaryWorldManager() {
        //this.worlds = 
    }
    
    public World getWorld(String name) {
    	return null;
    }
	
    public boolean createWorld(String name, long seed) {
    	return false;
    }
    
    public boolean createWorld(String name) {
    	return false;
    }
	
    public Dimension getDimension(String world, int dimension) {
    	return null;
    }
    
	public void destroyWorld(String name) {

	}
	    
    public World[] getAllWorlds() {
        return this.worlds;
    }
}
