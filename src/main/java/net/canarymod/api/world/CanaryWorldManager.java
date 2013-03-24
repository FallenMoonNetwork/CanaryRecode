package net.canarymod.api.world;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
 */
public class CanaryWorldManager implements WorldManager {

    private HashMap<String, World> loadedWorlds;
    private ArrayList<String> existingWorlds;

    public CanaryWorldManager() {
        WorldType.addType("NORMAL", 0);
        WorldType.addType("NETHER", -1);
        WorldType.addType("END", 1);
        File worlds = new File("worlds");
        if (!worlds.exists()) {
            worlds.mkdirs();
        }
        int worldNum = worlds.listFiles().length;
        if (worldNum == 0) {
            worldNum = 1;
        }
        existingWorlds = new ArrayList<String>(worldNum);
        loadedWorlds = new HashMap<String, World>(worldNum);
        for (String f : worlds.list()) {
            existingWorlds.add(f);
        }
    }

    /**
     * Implementation specific, do not call outside of OMCS!
     * Adds an already prepared world to the world manager
     * 
     * @param world
     */
    public void addWorld(CanaryWorld world) {
        loadedWorlds.put(world.getName() + "_" + world.getType().getName(), world);
    }

    @Override
    public World getWorld(String name) {
        return loadedWorlds.get(name + "_NORMAL");
    }

    @Override
    public World getWorld(String world, WorldType type, boolean autoload) {
        // Logman.println("Trying to load: "+world+"_"+type.getName());
        if (worldIsLoaded(world + "_" + type.getName())) {
            // Logman.println("Is loaded, returning world");
            return loadedWorlds.get(world + "_" + type.getName());
        }
        else {
            if (worldExists(world + "_" + type.getName())) {
                Logman.println("World exists but is not loaded. Loading ...");
                return loadWorld(world, type);
            }
            else {
                if (autoload) {
                    Logman.println("World does not exist, we can autoload, will load!");
                    createWorld(world, type);
                    return loadedWorlds.get(world + "_" + type.getName());
                }
                else {
                    Logman.logSevere("Tried to get a non-existing world: " + world + " - you must create it before you can load it or pass autoload = true");
                    return null;
                }

            }
        }

    }

    @Override
    public boolean createWorld(String name, long seed, WorldType type) {
        ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, seed);
        return true;
    }

    @Override
    public boolean createWorld(String name, WorldType type) {
        Logman.println("Creating a new world! " + name + "_" + type.getName());
        ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, new Random().nextLong(), type);
        return true;
    }

    @Override
    public boolean createWorld(String name, long seed, WorldType worldType, GeneratorType genType) {
        ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name + worldType.getName(), new Random().nextLong(), worldType, genType);
        return true;
    }

    @Override
    public World loadWorld(String name, WorldType type) {
        if (!worldIsLoaded(name + "_" + type.getName())) {
            ((CanaryServer) Canary.getServer()).getHandle().loadWorld(name, new Random().nextLong(), type);
            return loadedWorlds.get(name + "_" + type.getName());
        }
        else {
            return loadedWorlds.get(name + "_" + type.getName());
        }
    }

    @Override
    public void destroyWorld(String name) {
        File file = new File("worlds/" + name);
        File dir = new File("worldsbackups/" + name);
        boolean success = file.renameTo(new File(dir, file.getName()));
        if (!success) {
            Logman.logSevere("Attempted to move world " + name + " but it appeared to be still in use! Worlds should get unloaded before they are removed!");
        }
    }

    @Override
    public Collection<World> getAllWorlds() {
        return this.loadedWorlds.values();
    }

    @Override
    public void unloadWorld(String name, WorldType type) {
        loadedWorlds.remove(name + "_" + type.getName());
    }

    @Override
    public boolean worldIsLoaded(String name) {
        return loadedWorlds.containsKey(name);
    }

    @Override
    public boolean worldExists(String name) {
        return new File("worlds/" + name.split("_")[0] + "/" + name).isDirectory();
    }

    @Override
    public ArrayList<String> getExistingWorlds() {
        return existingWorlds; // TODO: This only reads base folders not the real dimension folders!
    }
}
