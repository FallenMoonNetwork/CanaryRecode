package net.canarymod.api.world;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.api.CanaryServer;
import net.canarymod.api.world.World.GeneratorType;

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
        WorldType.addType("NORMAL", 0);
        WorldType.addType("NETHER", -1);
        WorldType.addType("END", 1);
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
    public boolean createWorld(String name, long seed, WorldType type) {
        ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, seed);
        return true;
    }

    @Override
    public boolean createWorld(String name, WorldType type) {
        ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, new Random().nextLong());
        return true;
    }

    @Override
    public World getWorld(String world, WorldType type) {
        return loadedWorlds.get(world+type.getName());
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
    public World loadWorld(String name, WorldType type) {
        if(!worldIsLoaded(name+type.getName())) {
            ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name+type.getName(), new Random().nextLong()); //TODO process world type!
            return loadedWorlds.get(name+type.getName());
        }
        else {
            return loadedWorlds.get(name+type.getName());
        }
    }

    @Override
    public void unloadWorld(String name, WorldType type) {
        loadedWorlds.remove(name+type.getName());
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

    @Override
    public boolean createWorld(String name, long seed, WorldType worldType,
            GeneratorType genType) {
        // TODO Auto-generated method stub
        return false;
    }
}
