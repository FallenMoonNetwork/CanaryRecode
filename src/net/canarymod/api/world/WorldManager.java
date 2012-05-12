package net.canarymod.api.world;

/**
 * This is a container for all of the worlds.
 * @author Jos Kuijpers
 *
 */
public class WorldManager implements IWorldManager {
   
    private World[] worlds;
    
    public WorldManager() {
        //this.worlds = 
    }
    
    public IWorld getWorld(String name) {
    	return null;
    }
	
    public boolean createWorld(String name, long seed) {
    	return false;
    }
    
    public boolean createWorld(String name) {
    	return false;
    }
	
    public IDimension getDimension(String world, int dimension) {
    	return null;
    }
    
	public void destroyWorld(String name) {

	}
	    
    public IWorld[] getAllWorlds() {
        return this.worlds;
    }
}
