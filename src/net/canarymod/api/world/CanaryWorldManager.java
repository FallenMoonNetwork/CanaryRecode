package net.canarymod.api.world;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
        int worldNum = worlds.listFiles().length;
        existingWorlds = new ArrayList<String>(worldNum);
        loadedWorlds = new HashMap<String, CanaryWorld>(worldNum);
        for(String f : worlds.list()) {
            existingWorlds.add(f);
        }
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
        File file = new File("worlds/"+name);
        File dir = new File("worldsbackups/"+name);
        boolean success = file.renameTo(new File(dir, file.getName()));
        if (!success) {
            Logman.logSevere("Attempted to move world "+name+" but it appeared to be still in use! Worlds should get unloaded before they are removed!");
        }
    }

    public World[] getAllWorlds() {
        return this.loadedWorlds.values().toArray(new CanaryWorld[3]);
    }

    @Override
    public World loadWorld(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void unloadWorld(String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean worldIsLoaded(String name) {
        return loadedWorlds.containsKey(name);
    }

    @Override
    public boolean worldExists(String name) {
        return existingWorlds.contains(name);
    }
}
