package net.canarymod.api.world;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.CanaryServer;
import net.canarymod.Logman;

/**
 * This is a container for all of the worlds.
 * 
 * @author Jos Kuijpers
 * @author Chris Ksoll
 * 
 */
public class CanaryWorldManager implements WorldManager {

    private HashMap<String, CanaryWorld> loadedWorlds;
    private ArrayList<String> existingWorlds;

    public CanaryWorldManager() {
        File worlds = new File("worlds");
        if(!worlds.exists()) {
            worlds.mkdirs();
        }
        int worldNum = worlds.listFiles().length;
        if(worldNum == 0) {
            worldNum = 1;
        }
        existingWorlds = new ArrayList<String>(worldNum);
        loadedWorlds = new HashMap<String, CanaryWorld>(worldNum);
        for(String f : worlds.list()) {
            existingWorlds.add(f);
        }
    }

    /**
     * Implementation specific, do not call outside of OMCS!
     * Adds an already prepared world to the world manager
     * @param world
     */
    public void addWorld(CanaryWorld world) {
        loadedWorlds.put(world.getName(), world);
    }
    @Override
    public World getWorld(String name) {
        return loadedWorlds.get(name);
    }

    @Override
    public boolean createWorld(String name, long seed) {
        ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, seed);
        return true;
    }

    @Override
    public boolean createWorld(String name) {
        ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, new Random().nextLong());
        return true;
    }

    @Override
    public Dimension getDimension(String world, int dimension) {
        return loadedWorlds.get(world).getDimension(Dimension.Type.fromId(dimension));
    }

    @Override
    public void destroyWorld(String name) {
        File file = new File("worlds/"+name);
        File dir = new File("worldsbackups/"+name);
        boolean success = file.renameTo(new File(dir, file.getName()));
        if (!success) {
            Logman.logSevere("Attempted to move world "+name+" but it appeared to be still in use! Worlds should get unloaded before they are removed!");
        }
    }

    @Override
    public World[] getAllWorlds() {
        return this.loadedWorlds.values().toArray(new CanaryWorld[loadedWorlds.size()]);
    }

    @Override
    public World loadWorld(String name) {
        if(!worldIsLoaded(name)) {
            ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, new Random().nextLong());
            return loadedWorlds.get(name);
        }
        else {
            return loadedWorlds.get(name);
        }
    }

    @Override
    public void unloadWorld(String name) {
        loadedWorlds.remove(name);
    }

    @Override
    public boolean worldIsLoaded(String name) {
        return loadedWorlds.containsKey(name);
    }

    @Override
    public boolean worldExists(String name) {
        return existingWorlds.contains(name);
    }

    @Override
    public ArrayList<String> getExistingWorlds() {
        return existingWorlds;
    }
}
